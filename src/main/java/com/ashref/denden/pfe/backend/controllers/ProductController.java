package com.ashref.denden.pfe.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashref.denden.pfe.backend.domain.business.ProductBusiness;
import com.ashref.denden.pfe.backend.exceptions.GuardianException;
import com.ashref.denden.pfe.backend.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json", headers = "Accept=application/json")
	public ProductBusiness addProduct(@RequestBody ProductBusiness productBusiness) {
		return productService.addProduct(productBusiness);
	}
	
	@RequestMapping(value = "/{manufacturerName:.+}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<ProductBusiness> getManufacturerProduct(@PathVariable String manufacturerName)
			throws GuardianException {
		System.out.println("Get " + manufacturerName + " products");
		return productService.getManufacturerProduct(manufacturerName);
	}
	
	@RequestMapping(value = "/{manufacturerName:.+}/delete", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public List<ProductBusiness> deleteManufacturerProducts(@PathVariable String manufacturerName, @RequestBody List<Long> productsIds)
			throws GuardianException {
		System.out.println("Deleting products");
		return productService.deleteManufacturerProducts(manufacturerName, productsIds);
	}
}
