package com.ashref.denden.pfe.backend.service.impl;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ashref.denden.pfe.backend.domain.business.ProductSerialsBusiness;
import com.ashref.denden.pfe.backend.domain.business.SerialBusiness;
import com.ashref.denden.pfe.backend.domain.business.SerialCheckBusiness;
import com.ashref.denden.pfe.backend.domain.business.SerialCheckInfo;
import com.ashref.denden.pfe.backend.domain.business.UIDCheckResult;
import com.ashref.denden.pfe.backend.exceptions.GuardianException;
import com.ashref.denden.pfe.backend.model.ERP;
import com.ashref.denden.pfe.backend.model.PlatformUser;
import com.ashref.denden.pfe.backend.model.Product;
import com.ashref.denden.pfe.backend.model.Serial;
import com.ashref.denden.pfe.backend.model.SerialCheck;
import com.ashref.denden.pfe.backend.repository.ERPRepository;
import com.ashref.denden.pfe.backend.repository.PlatformUserRepository;
import com.ashref.denden.pfe.backend.repository.ProductRepository;
import com.ashref.denden.pfe.backend.repository.SerialCheckRepository;
import com.ashref.denden.pfe.backend.repository.SerialRepository;
import com.ashref.denden.pfe.backend.service.SerialService;

@Service("serialService")
public class SerialServiceImpl implements SerialService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private PlatformUserRepository platformUserRepository;

	@Autowired
	private SerialRepository serialRepository;

	@Autowired
	private SerialCheckRepository checkRepository;

	@Autowired
	private ERPRepository erpRepository;

	@Autowired
	private ModelMapper mapper;

	public ProductSerialsBusiness getProductSerials(Long productId) {
		Product product = productRepository.findOne(productId);
		List<Serial> serials = serialRepository.findByProductId(productId);

		ProductSerialsBusiness productSerialsBusiness = mapper.map(product, ProductSerialsBusiness.class);
		for (Serial serial : serials) {
			SerialBusiness serialBusiness = mapper.map(serial, SerialBusiness.class);
			serialBusiness.setCheckNumb(checkRepository.findBySerial(serial.getSerial()).size());
			productSerialsBusiness.getSerials().add(serialBusiness);

		}

		return productSerialsBusiness;
	}

	@Transactional
	public ProductSerialsBusiness associate(Long productId, List<Long> serialsIds) throws GuardianException {
		for (Long serialId : serialsIds) {
			Serial serial = serialRepository.findOne(serialId);
			serial.setUid(UUID.randomUUID().toString());
			serialRepository.save(serial);
		}
		return getProductSerials(productId);
	}

	@Transactional
	public ProductSerialsBusiness delete(Long productId, List<Long> serialsIds) throws GuardianException {
		for (Long serialId : serialsIds) {
			serialRepository.delete(serialId);
		}
		return getProductSerials(productId);
	}

	public UIDCheckResult checkUID(SerialCheckInfo checkInfo, Locale locale, String ip) throws GuardianException {
		UIDCheckResult checkResult = new UIDCheckResult();
		Serial serial = serialRepository.findByUid(checkInfo.getUid());

		if (serial != null) {
			SerialCheck check = new SerialCheck();
			check.setSerial(serial.getSerial());
			check.setUserId(checkInfo.getUserId());
			check.setLocation(locale.getDisplayCountry());
			check.setIp(ip);
			checkRepository.save(check);
			int serialCheckNumb = checkRepository.findBySerial(serial.getSerial()).size();
			if (serialCheckNumb == 1) {
				checkResult.setCheckResult(true);
				checkResult.setMessage("Your Product is Authentic");
			} else {
				checkResult.setCheckResult(false);
				checkResult.setMessage("UID was checked more then once. Your product is counterfeited");
			}

		} else {
			checkResult.setCheckResult(false);
			checkResult.setMessage("UID was not found. Your product is counterfeited");
		}
		return checkResult;
	}

	public List<SerialCheckBusiness> getSerialChecks(String serialValue) throws GuardianException {
		List<SerialCheckBusiness> serialCheckBusinesses = new ArrayList<SerialCheckBusiness>();
		Serial serial = serialRepository.findBySerial(serialValue);
		for (SerialCheck serialCheck : checkRepository.findBySerial(serialValue)) {
			SerialCheckBusiness serialCheckBusiness = mapper.map(serialCheck, SerialCheckBusiness.class);
			serialCheckBusiness.setDestination(serial.getDestination());
			serialCheckBusinesses.add(serialCheckBusiness);
		}
		return serialCheckBusinesses;
	}

	public ProductSerialsBusiness updateProductSerials(Long productId) throws GuardianException {

		Product product = productRepository.findOne(productId);
		if (product == null) {
			throw new GuardianException("0", "Product with id " + productId + " doesn't exist");
		}
		PlatformUser manufacturer = platformUserRepository.findByUsername(product.getManufacturer());
		ERP erp = erpRepository.findByManufacturerId(manufacturer.getId());

		String url = "jdbc:postgresql://" + erp.getUrl() + "/" + erp.getDatabaseName();
		Properties props = new Properties();
		props.setProperty("user", erp.getDbUsername());
		props.setProperty("password", erp.getDbPassword());
		try {
			Class.forName("org.postgresql.Driver");
			// jdbc:postgresql://host:port/database
			java.sql.Connection conn = DriverManager.getConnection(url, props);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(
					"Select name from stock_production_lot left join product_product on product_product.id = stock_production_lot.product_id where product_product.name_template Like '"
							+ product.getName() + "';");
			ProductSerialsBusiness productSerialsBusiness = new ProductSerialsBusiness();
			productSerialsBusiness.setDescription(product.getDescription());
			productSerialsBusiness.setName(product.getName());
			while (rs.next()) {
				System.out.println(rs.getString(1));
				if (serialRepository.findBySerial(rs.getString(1)) == null) {
					Serial serial = new Serial();
					serial.setDestination("Tunisie");
					serial.setProductId(productId);
					serial.setSerial(rs.getString(1));
					serialRepository.save(serial);
				}
			}
			rs.close();
			st.close();
			return getProductSerials(productId);
		} catch (SQLException e) {
			throw new GuardianException("0", "Can't establish connection to ERP system!");
		} catch (ClassNotFoundException e) {
			throw new GuardianException("0", "Can't establish connection to ERP system!");
		}

	}

}
