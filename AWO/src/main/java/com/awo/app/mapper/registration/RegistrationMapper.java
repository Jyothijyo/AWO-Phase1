package com.awo.app.mapper.registration;

import org.springframework.stereotype.Component;

import com.awo.app.domain.registration.Registration;
import com.awo.app.mapper.AbstractModelMapper;
import com.awo.app.model.registration.RegistrationModel;
@Component
public class RegistrationMapper extends AbstractModelMapper<RegistrationModel, Registration> {

	@Override
	public Class<RegistrationModel> entityType() {
		return RegistrationModel.class;
	}

	@Override
	public Class<Registration> modelType() {
		return Registration.class;
	}


}
