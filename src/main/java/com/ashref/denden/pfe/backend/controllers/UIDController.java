package com.ashref.denden.pfe.backend.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashref.denden.pfe.backend.domain.business.CheckUID;
import com.ashref.denden.pfe.backend.domain.business.Test;
import com.ashref.denden.pfe.backend.domain.business.UIDCheckResult;

@RestController
@RequestMapping("/uid")
public class UIDController {

	@RequestMapping(value = "/check", method = RequestMethod.POST, produces = "application/json", headers = "Accept=application/json")
	public UIDCheckResult checkUID(@RequestBody CheckUID test) {
		UIDCheckResult checkResult = new UIDCheckResult();
		checkResult.setCheckResult(true);
		checkResult.setMessage("valid uid");
		return checkResult;
	}

}