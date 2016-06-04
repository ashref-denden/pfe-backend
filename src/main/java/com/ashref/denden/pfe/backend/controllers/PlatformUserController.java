package com.ashref.denden.pfe.backend.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ashref.denden.pfe.backend.domain.business.ERPConnection;
import com.ashref.denden.pfe.backend.domain.business.LoginCredentials;
import com.ashref.denden.pfe.backend.domain.business.PlatformUserBusiness;
import com.ashref.denden.pfe.backend.exceptions.GuardianException;
import com.ashref.denden.pfe.backend.exceptions.InfoErreur;
import com.ashref.denden.pfe.backend.service.PlatformUserService;

@RestController
@RequestMapping("/user")
public class PlatformUserController {

	@Autowired
	private PlatformUserService platformUserService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json", headers = "Accept=application/json")
	public PlatformUserBusiness addProduct(@RequestBody LoginCredentials credentials) throws GuardianException {
		return platformUserService.login(credentials);
	}
	
	@RequestMapping(value = "/{platformUserId:.+}/editProfile'", method = RequestMethod.POST, produces = "application/json", headers = "Accept=application/json")
	public PlatformUserBusiness editProfile(@PathVariable Long platformUserId, @RequestBody PlatformUserBusiness platformUserBusiness) throws GuardianException {
		return platformUserService.editProfile(platformUserId, platformUserBusiness);
	}
	
	@RequestMapping(value = "/signIn", method = RequestMethod.POST, produces = "application/json", headers = "Accept=application/json")
	public PlatformUserBusiness signIn(@RequestBody PlatformUserBusiness platformUserBusiness) throws GuardianException {
		return platformUserService.signIn(platformUserBusiness);
	}
	
	@RequestMapping(value = "/{manufacturerId:.+}/linkERP", method = RequestMethod.POST, produces = "application/json", headers = "Accept=application/json")
	public Boolean linkERP(@PathVariable Long manufacturerId, @RequestBody ERPConnection erpConnection) throws GuardianException {
		return platformUserService.linkERP(manufacturerId, erpConnection);
	}
	
	@RequestMapping(value = "/{manufacturerId:.+}/getERP", method = RequestMethod.GET, produces = "application/json")
	public ERPConnection getERP(@PathVariable Long manufacturerId) throws GuardianException {
		return platformUserService.getERP(manufacturerId);
	}
	
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	@ExceptionHandler({ GuardianException.class })
	@ResponseBody
	InfoErreur handleGuardianException(HttpServletRequest req, Exception ex) {
		return new InfoErreur(req.getRequestURI(),
				((GuardianException) ex).getErrorCode(), ex.getLocalizedMessage());
	}
}
