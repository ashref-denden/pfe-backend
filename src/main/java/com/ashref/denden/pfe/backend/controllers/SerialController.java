package com.ashref.denden.pfe.backend.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashref.denden.pfe.backend.domain.business.SerialCheckInfo;
import com.ashref.denden.pfe.backend.domain.business.ProductSerialsBusiness;
import com.ashref.denden.pfe.backend.domain.business.SerialCheckBusiness;
import com.ashref.denden.pfe.backend.domain.business.UIDCheckResult;
import com.ashref.denden.pfe.backend.exceptions.GuardianException;
import com.ashref.denden.pfe.backend.service.SerialService;

@RestController
@RequestMapping("/serial")
public class SerialController {
	
	@Autowired
	private SerialService serialService;
	
	@RequestMapping(value = "/{productId:.+}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ProductSerialsBusiness getProductSerials(@PathVariable Long productId)
			throws GuardianException {
		System.out.println("Get " + productId + " serials");
		return serialService.getProductSerials(productId);
	}
	
	@RequestMapping(value = "/{productId:.+}/update", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ProductSerialsBusiness updateProductSerials(@PathVariable Long productId)
			throws GuardianException {
		System.out.println("Updating product " + productId + " serial numbers");
		return serialService.updateProductSerials(productId);
	}
	
	@RequestMapping(value = "/{productId:.+}/associate", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ProductSerialsBusiness associate(@PathVariable Long productId, @RequestBody List<Long> serialsIds)
			throws GuardianException {
		System.out.println("Associating Serials with UIDs");
		return serialService.associate(productId, serialsIds);
	}
	
	@RequestMapping(value = "/{productId:.+}/delete", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ProductSerialsBusiness delete(@PathVariable Long productId, @RequestBody List<Long> serialsIds)
			throws GuardianException {
		System.out.println("Deleting product " + productId + " serials");
		return serialService.delete(productId, serialsIds);
	}
		
	@RequestMapping(value = "/check", method = RequestMethod.POST, produces = "application/json")
	public UIDCheckResult checkUID(@RequestBody SerialCheckInfo checkInfo,  HttpServletRequest request) throws GuardianException {
		System.out.println("Checking UID " + checkInfo.getUid());
		return serialService.checkUID(checkInfo, request.getLocale(), request.getRemoteAddr());
	}
		
	@RequestMapping(value = "/checks/{serial:.+}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<SerialCheckBusiness> getSerialChecks(@PathVariable String serial)
			throws GuardianException {
		System.out.println("Get serial" + serial + " checks");
		return serialService.getSerialChecks(serial);
	}

}
