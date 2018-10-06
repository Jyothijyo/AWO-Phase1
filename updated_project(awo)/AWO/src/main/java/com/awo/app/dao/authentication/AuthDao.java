package com.awo.app.dao.authentication;

import java.util.List;

import com.awo.app.domain.authentication.Authentication;
import com.awo.app.model.registration.RegAdr;
import com.awo.app.response.Response;

public interface AuthDao {

	Response addAuth(Authentication auth);

	Authentication getAuth(String regId);

	Response updateAuth(RegAdr regM);

	Response deleteById(String regId);

	String changePassword(String regId, String encriptString);
	
	List<Authentication> getAll();
	
	public void deleteExpireAccount(String regId);

	Authentication getActive(String regId);

}
