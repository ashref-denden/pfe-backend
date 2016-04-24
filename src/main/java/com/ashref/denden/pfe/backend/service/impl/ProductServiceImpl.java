package com.ashref.denden.pfe.backend.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.ashref.denden.pfe.backend.domain.business.ProductBusiness;
import com.ashref.denden.pfe.backend.model.Product;
import com.ashref.denden.pfe.backend.repository.ProductRepository;
import com.ashref.denden.pfe.backend.service.ProductService;

public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper mapper;

	public ProductBusiness addProduct(ProductBusiness productBusiness) {
		return mapper.map(productRepository.save(mapper.map(productBusiness, Product.class)), ProductBusiness.class);
	}

}
