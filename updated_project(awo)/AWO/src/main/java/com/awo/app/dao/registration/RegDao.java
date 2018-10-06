package com.awo.app.dao.registration;

import java.util.List;

import com.awo.app.domain.authentication.Authentication;
import com.awo.app.domain.registration.Registration;
import com.awo.app.model.registration.RegAdr;
import com.awo.app.model.registration.RegistrationModel;
import com.awo.app.requestRaise.domain.RequestRaise;
import com.awo.app.response.Response;

public interface RegDao {

	

	Response saveReg(Registration reg);

	List<String> getDetailsByAreaAndPincode(RegistrationModel regAdr);
	
	Registration getDetailsById(String regId);

	Response deleteById(String regId);

	List<Registration> getUsers();

	String authenticate(RegistrationModel model);

	String isDonorExist(RegistrationModel model);

	String resetPassword(String regId, String encriptString);

	Response AddRequestRaise(RequestRaise req);

	List<RequestRaise> getAllRequest();

	Response updateRequest(RequestRaise req);

}
