package com.ashref.denden.pfe.backend.service;

import org.springframework.stereotype.Service;

import com.ashref.denden.pfe.backend.domain.business.ProductBusiness;

@Service("productService")
public interface ProductService {
	
	ProductBusiness addProduct(ProductBusiness productBusiness);

}
