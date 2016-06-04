package com.ashref.denden.pfe.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ashref.denden.pfe.backend.domain.business.ProductBusiness;
import com.ashref.denden.pfe.backend.exceptions.GuardianException;
import com.ashref.denden.pfe.backend.model.Product;
import com.ashref.denden.pfe.backend.repository.ProductRepository;
import com.ashref.denden.pfe.backend.service.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper mapper;

	public ProductBusiness addProduct(ProductBusiness productBusiness) {
		return mapper.map(productRepository.save(mapper.map(productBusiness, Product.class)), ProductBusiness.class);
	}

	public List<ProductBusiness> getManufacturerProduct(String manufacturerName) throws GuardianException {
		List<Product>  products = productRepository.findByManufacturer(manufacturerName);
		List<ProductBusiness> productBusinesses = new ArrayList<ProductBusiness>();
		for(Product product : products) {
			productBusinesses.add(mapper.map(product, ProductBusiness.class));
		}
		return productBusinesses;
	}

	@Transactional
	public List<ProductBusiness> deleteManufacturerProducts(String manufacturerName, List<Long> productsIds)
			throws GuardianException {
		for(Long productId : productsIds) {
			productRepository.delete(productId);
		}
		return getManufacturerProduct(manufacturerName);
	}

}
