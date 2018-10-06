package com.awo.app.mapper.address;

import org.springframework.stereotype.Component;

import com.awo.app.domain.address.Address;
import com.awo.app.mapper.AbstractModelMapper;
import com.awo.app.model.address.AddrModel;

@Component
public class AddressMapper  extends AbstractModelMapper<AddrModel, Address>{

	@Override
	public Class<AddrModel> entityType() {
		return AddrModel.class;
	}

	@Override
	public Class<Address> modelType() {
		return Address.class;
	}

	
}
