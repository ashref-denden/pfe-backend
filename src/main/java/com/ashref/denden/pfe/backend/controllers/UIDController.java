package com.ashref.denden.pfe.backend.controllers;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ashref.denden.pfe.backend.domain.business.CheckUID;
import com.ashref.denden.pfe.backend.domain.business.UIDCheckResult;
import com.ashref.denden.pfe.backend.exceptions.InfoErreur;


@RestController
@RequestMapping("/uid")
public class UIDController {
	
	@RequestMapping(value = "/{uid:.+}/{test:.+}", method = RequestMethod.GET, produces = "application/json")
	public String sayHelloUID(@PathVariable String uid, @PathVariable String test) {
		
		return "Hello " + uid + " " + test;
	}


	/**
	 * Gets the contrat by reference.
	 * 
	 * @param referenceContrat
	 *            reference du contrat.
	 * @return {@link ElementContractuel}.
	 * @throws TopazeException
	 *             the topaze exception {@link TopazeException}.
	 */
	@RequestMapping(value = "/check", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public UIDCheckResult checkUID(@RequestBody CheckUID uid) {
		UIDCheckResult checkResult = new UIDCheckResult();
		checkResult.setCheckResult(true);
		checkResult.setMessage("valid uid");
		return checkResult;
	}

	

	/**
	 * 
	 * Gerer le cas ou on a une {@link HttpMessageNotReadableException}.
	 * 
	 * @param req
	 *            requete HttpServletRequest.
	 * @return {@link InfoErreur}
	 * @throws Exception
	 */
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ HttpMessageNotReadableException.class })
	@ResponseBody
	InfoErreur handleMessageNotReadableException(HttpServletRequest req) {
		return new InfoErreur();
	}
}