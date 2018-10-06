package com.awo.app.controller.requestRaise;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.awo.app.constant.StatusCode;
import com.awo.app.requestRaise.model.ModelRequest;
import com.awo.app.response.ErrorObject;
import com.awo.app.response.Response;
import com.awo.app.service.registration.RegService;
import com.awo.app.utils.CommonUtils;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class RequestRaiseController {

	@Autowired
	RegService regService;

	private static final Logger logger = LoggerFactory.getLogger(RequestRaiseController.class);

	/*---------------------------------	addRequestRaise ----------------------------------------------*/

	@PostMapping(value = "/request", produces = "application/json")
	public Response addRequestRaise(@RequestBody ModelRequest model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.info("addRequestRaise:Received request URL: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		logger.info("addpatient:Received request:" + CommonUtils.getJson(model));
		return regService.AddRequestRaise(model);
	}

	/*-----------------------------------	Get All Request ----------------------------------------------*/

	@GetMapping(value = "/request/getAll", produces = "application/json")
	public @ResponseBody Response getAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("updateRequestRaise: Received request URL: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));

		Response res = CommonUtils.getResponseObject("List of RequestRaise Dertails");
		List<ModelRequest> list = regService.getAllRequest();
		if (list == null) {
			ErrorObject err = CommonUtils.getErrorResponse("User not found", "User not found");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
		} else {
			res.setData(list);
		}
		return res;
	}

	
	/*-----------------------------------	Get All Request ----------------------------------------------*/
	@PutMapping(value = "/request/update", produces ="application/json")
	public Response update(@RequestBody ModelRequest model,HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("addRequestRaise:Received request URL: " + request.getRequestURL().toString()
				+ ((request.getQueryString() == null) ? "" : "?" + request.getQueryString().toString()));
		logger.info("addpatient:Received request:" + CommonUtils.getJson(model));
		return regService.updateRequest(model);
	}
}
