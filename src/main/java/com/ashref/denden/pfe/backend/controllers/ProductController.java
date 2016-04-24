package com.ashref.denden.pfe.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ashref.denden.pfe.backend.domain.business.ProductBusiness;
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
}
