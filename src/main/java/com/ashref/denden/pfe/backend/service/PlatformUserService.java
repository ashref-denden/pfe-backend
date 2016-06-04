package com.ashref.denden.pfe.backend.service;

import com.ashref.denden.pfe.backend.domain.business.ERPConnection;
import com.ashref.denden.pfe.backend.domain.business.LoginCredentials;
import com.ashref.denden.pfe.backend.domain.business.PlatformUserBusiness;
import com.ashref.denden.pfe.backend.exceptions.GuardianException;

public interface PlatformUserService {
	
	PlatformUserBusiness login(LoginCredentials credentials) throws GuardianException;

	PlatformUserBusiness signIn(PlatformUserBusiness platformUserBusiness) throws GuardianException;

	Boolean linkERP(Long manufacturerId, ERPConnection erpConnection) throws GuardianException;

	ERPConnection getERP(Long manufacturerId)throws GuardianException;

	PlatformUserBusiness editProfile(Long platformUserId, PlatformUserBusiness platformUserBusiness) throws GuardianException;

}
