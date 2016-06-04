package com.ashref.denden.pfe.backend.service;

import java.util.List;

import com.ashref.denden.pfe.backend.domain.business.ProductBusiness;
import com.ashref.denden.pfe.backend.exceptions.GuardianException;

public interface ProductService {
	
	ProductBusiness addProduct(ProductBusiness productBusiness);

	List<ProductBusiness> getManufacturerProduct(String manufacturerName) throws GuardianException;

	List<ProductBusiness> deleteManufacturerProducts(String manufacturerName, List<Long> productsIds) throws GuardianException;

}
