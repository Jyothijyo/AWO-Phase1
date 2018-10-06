package com.awo.app.mapper.registration;

import com.awo.app.domain.registration.Registration;
import com.awo.app.mapper.AbstractModelMapper;
import com.awo.app.model.registration.RegAdr;

public class RegAdrMapper extends AbstractModelMapper<RegAdr, Registration>{

	@Override
	public Class<RegAdr> entityType() {
		return RegAdr.class;
	}

	@Override
	public Class<Registration> modelType() {
		return Registration.class;
	}

}
