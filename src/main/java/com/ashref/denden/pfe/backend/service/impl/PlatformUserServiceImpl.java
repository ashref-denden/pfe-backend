package com.ashref.denden.pfe.backend.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashref.denden.pfe.backend.domain.business.ERPConnection;
import com.ashref.denden.pfe.backend.domain.business.LoginCredentials;
import com.ashref.denden.pfe.backend.domain.business.PlatformUserBusiness;
import com.ashref.denden.pfe.backend.exceptions.GuardianException;
import com.ashref.denden.pfe.backend.model.ERP;
import com.ashref.denden.pfe.backend.model.PlatformUser;
import com.ashref.denden.pfe.backend.repository.ERPRepository;
import com.ashref.denden.pfe.backend.repository.PlatformUserRepository;
import com.ashref.denden.pfe.backend.service.PlatformUserService;

@Service("platformUserService")
public class PlatformUserServiceImpl implements PlatformUserService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private PlatformUserRepository platformUserRepository;

	@Autowired
	private ERPRepository erpRepository;

	public PlatformUserBusiness login(LoginCredentials credentials) throws GuardianException {
		PlatformUser user = platformUserRepository.findByUsernameAndPassword(credentials.getUsername(),
				credentials.getPassword());
		if (user == null) {
			throw new GuardianException("0", "User doesn't exist");
		}
		PlatformUserBusiness platformUserBusiness = mapper.map(user, PlatformUserBusiness.class);
		ERP erp = erpRepository.findByManufacturerId(user.getId());
		if (erp != null) {
			platformUserBusiness.setErpLinked(true);
		} else {
			platformUserBusiness.setErpLinked(false);
		}
		return platformUserBusiness;
	}

	public PlatformUserBusiness signIn(PlatformUserBusiness platformUserBusiness) throws GuardianException {
		PlatformUser user = mapper.map(platformUserBusiness, PlatformUser.class);
		PlatformUserBusiness platformUser = mapper.map(platformUserRepository.save(user), PlatformUserBusiness.class);
		ERP erp = erpRepository.findByManufacturerId(platformUser.getId());
		if (erp != null) {
			platformUser.setErpLinked(true);
		} else {
			platformUser.setErpLinked(false);
		}
		return platformUser;
	}
	
	public PlatformUserBusiness editProfile(Long platformUserId, PlatformUserBusiness platformUserBusiness)
			throws GuardianException {
		PlatformUser user = platformUserRepository.findOne(platformUserId);
		if (user == null) {
			throw new GuardianException("0", "User doesn't exist");
		}
		user.setCompanyName(platformUserBusiness.getCompanyName());
		user.setUsername(platformUserBusiness.getUsername());
		return mapper.map(platformUserRepository.save(user), PlatformUserBusiness.class);
	}
	

	public Boolean linkERP(Long manufacturerId, ERPConnection erpConnection) throws GuardianException {
		ERP erp = erpRepository.findByManufacturerId(manufacturerId);
		if (erp != null) {
			erpRepository.delete(erp);
		}
		erp = mapper.map(erpConnection, ERP.class);
		erp.setManufacturerId(manufacturerId);
		return erpRepository.save(erp) != null ? true : false;
	}
	
	public ERPConnection getERP(Long manufacturerId) throws GuardianException {
		ERP erp = erpRepository.findByManufacturerId(manufacturerId);
		return mapper.map(erp, ERPConnection.class);
	}

}
