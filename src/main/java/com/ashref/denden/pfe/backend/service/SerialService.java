package com.ashref.denden.pfe.backend.service;

import java.util.List;
import java.util.Locale;

import com.ashref.denden.pfe.backend.domain.business.ProductSerialsBusiness;
import com.ashref.denden.pfe.backend.domain.business.SerialCheckBusiness;
import com.ashref.denden.pfe.backend.domain.business.SerialCheckInfo;
import com.ashref.denden.pfe.backend.domain.business.UIDCheckResult;
import com.ashref.denden.pfe.backend.exceptions.GuardianException;

public interface SerialService {

	ProductSerialsBusiness getProductSerials(Long productId) throws GuardianException;

	ProductSerialsBusiness associate(Long productId, List<Long> serialsIds) throws GuardianException;

	ProductSerialsBusiness delete(Long productId, List<Long> serialsIds) throws GuardianException;

	UIDCheckResult checkUID(SerialCheckInfo checkInfo, Locale locale, String ip) throws GuardianException;

	List<SerialCheckBusiness> getSerialChecks(String serial) throws GuardianException;

	ProductSerialsBusiness updateProductSerials(Long productId) throws GuardianException;

}
