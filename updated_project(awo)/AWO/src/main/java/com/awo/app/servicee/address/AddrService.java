package com.awo.app.servicee.address;

import java.util.List;

import com.awo.app.domain.registration.Registration;
import com.awo.app.model.address.AddrModel;
import com.awo.app.response.Response;

public interface AddrService {

	Response addAddress(AddrModel address);

	AddrModel getByAddressesId(String addressId);

	Response deleteAddressByAdr(String addressId);

//	List<AddrModel> getAddresses();

	Response updateAddress(AddrModel address);

	List<AddrModel> getByregId(String regId);

}
