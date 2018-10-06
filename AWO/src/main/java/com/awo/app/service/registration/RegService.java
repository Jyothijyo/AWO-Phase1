package com.awo.app.service.registration;

import java.util.List;

import com.awo.app.domain.authentication.Authentication;
import com.awo.app.domain.registration.UpdatePassword;
import com.awo.app.model.address.AddrModel;
import com.awo.app.model.registration.AdminFilter;
import com.awo.app.model.registration.RegAdr;
import com.awo.app.model.registration.RegistrationModel;
import com.awo.app.requestRaise.model.ModelRequest;
import com.awo.app.response.Response;

public interface RegService {

	Response saveReg(RegistrationModel model);

	List<RegAdr> getDetailsByAreaAndPincode(RegistrationModel regAdr);
	
	Response deleteById(String regId);
	
	RegAdr getDetailsById(String regId);

	RegAdr authenticate(RegistrationModel model);

	List<RegistrationModel> getUsers() throws Exception;

	RegAdr getById(String regId);

	String resetPassword(RegistrationModel model);

	String changePassword(UpdatePassword model);

	List<AdminFilter> getDetailByAreaAndPincode(RegistrationModel regAdr);

	Response AddRequestRaise(ModelRequest model);

	List<ModelRequest> getAllRequest();

	Response updateRequest(ModelRequest model);

//	List<RegAdr> getDetailsByfilter(String regId);

}
