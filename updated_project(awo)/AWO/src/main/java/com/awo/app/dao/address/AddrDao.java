package com.awo.app.dao.address;

import java.util.List;

import com.awo.app.domain.address.Address;
import com.awo.app.domain.registration.Registration;
import com.awo.app.response.Response;

public interface AddrDao {

	Response addAddress(Address addr);

	Address getByAddressesId(String addressId);

	Response deleteAddress(String addressId);
	
	Response deleteAddressByAdr(String addressId);

//	List<Address> getAddresses();

	Response updateAddress(Address addr);

	List<Address> getByregId(String regId);

	Address reActive(String regId);


}
