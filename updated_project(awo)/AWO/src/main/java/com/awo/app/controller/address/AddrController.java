package com.awo.app.controller.address;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.awo.app.constant.StatusCode;
import com.awo.app.domain.registration.Registration;
import com.awo.app.model.address.AddrModel;
import com.awo.app.response.ErrorObject;
import com.awo.app.response.Response;
import com.awo.app.servicee.address.AddrService;
import com.awo.app.utils.CommonUtils;

@RestController

public class AddrController {

	@Autowired
	private AddrService addrService;

	private static final Logger logger = LoggerFactory.getLogger(AddrController.class);

	@PostMapping(value="/address", produces="Application/json")
	public Response addAddress(@Valid @RequestBody AddrModel address, HttpServletRequest request, HttpServletResponse response) throws Exception{
		logger.info("addAddress: Received request URL: "+ request.getRequestURI().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		logger.info("addAddress: Received request: " + CommonUtils.getJson(address));
		return addrService.addAddress(address);
		
	}
	
	@PutMapping(value="/address", produces="Application/json")
	public Response updateAddress(@RequestBody AddrModel address, HttpServletRequest request, HttpServletResponse response) throws Exception{
		logger.info("addAddress: Received request URL: "+ request.getRequestURI().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		logger.info("addAddress: Received request: " + CommonUtils.getJson(address));
		return addrService.updateAddress(address);
	
	}
	@GetMapping(value="/address/{addressId}")
	public @ResponseBody String getByAddressesId(@PathVariable("addressId") String addressId, HttpServletRequest request, HttpServletResponse response) {
		logger.info("getAddress: Received request URL :"+ request.getRequestURI().toString()
				+((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		 AddrModel addr = addrService.getByAddressesId(addressId);
		 Response res = CommonUtils.getResponseObject("AddressDetails :");
		 if(addr == null) {
			ErrorObject err = CommonUtils.getErrorResponse("Exception", "Address not found");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
			return CommonUtils.getJson(res);
		 } else {
			res.setData(addr);
			logger.info("Address details :");
			return CommonUtils.getJson(res);	
		 }
	}

	
	@GetMapping(value="/addressId/{regId}")
	public @ResponseBody String getByregId(@PathVariable("regId") String regId, HttpServletRequest request, HttpServletResponse response) {
		logger.info("getAddress: Received request URL :"+ request.getRequestURI().toString()
				+((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		 List<AddrModel> addr = addrService.getByregId(regId);
		 Response res = CommonUtils.getResponseObject("AddressDetails :");
		 if(addr == null) {
			ErrorObject err = CommonUtils.getErrorResponse("Exception", "Address not found");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
			return CommonUtils.getJson(res);
		 } else {
			res.setData(addr);
			logger.info("Address details :");
			return CommonUtils.getJson(res);
		 }
	}

	
	@DeleteMapping(value="/address/{addressId}")
	public @ResponseBody Response deleteAddressByAdr(@PathVariable ("addressId") String addressId, HttpServletRequest request, HttpServletResponse response) throws Exception{
		logger.info("deleteAddress: Received request: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		return addrService.deleteAddressByAdr(addressId);
		
	}
	



}

	