package com.awo.app.servicee.address;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awo.app.constant.StatusCode;
import com.awo.app.dao.address.AddrDao;
import com.awo.app.domain.address.Address;
import com.awo.app.domain.registration.Registration;
import com.awo.app.mapper.address.AddressMapper;
import com.awo.app.model.address.AddrModel;
import com.awo.app.response.ErrorObject;
import com.awo.app.response.Response;
import com.awo.app.utils.CommonUtils;
import com.awo.app.utils.DateUtility;

@Service
public class AddrServiceImp implements AddrService {
	
	private static final Logger logger = LoggerFactory.getLogger(AddrServiceImp.class);
	
	@Autowired
	private AddrDao addrDao;
	
	@Autowired
	private AddressMapper addressMapper;

	@Override
	public Response addAddress(AddrModel address) {
		try {
			Address addr = new Address();
			addr.setAddressId(CommonUtils.generateRandomId());
			addr.setDateAndTime(DateUtility.getDateByStringFormat(new Date(), DateUtility.DATE_FORMAT_DD_MMM_YYYY_HHMMSS));
			addr.setActive(true);
			addr.setArea(address.getArea());
			addr.setCity(address.getCity());
			addr.setEmailId(address.getEmailId());
			addr.setMobile(address.getMobile());
			addr.setPincode(address.getPincode());
			addr.setStreet(address.getStreet());
			addr.setState(address.getStreet());
			addr.setWebsite(address.getWebsite());
			addr.setRegId(address.getRegId());
			return addrDao.addAddress(addr);
		}catch(Exception e) {
			logger.error("Exception :"+ e.getMessage());
			Response res = new Response();
			ErrorObject err = CommonUtils.getErrorResponse("Exception", "Exception in saveAddress");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
			res.setMessage(e.getMessage());
			return res;
		}
		
	}

	@Override
	public AddrModel getByAddressesId(String addressId) {
		try {
		Address addr = addrDao.getByAddressesId(addressId);
		AddrModel addrM = new AddrModel();
		if(addr == null) {
			return null;
		}else {
			addrM.setAddressId(addr.getAddressId());
			addrM.setArea(addr.getArea());
			addrM.setCity(addr.getCity());
			addrM.setDateAndTime(addr.getDateAndTime());
			addrM.setEmailId(addr.getEmailId());
			addrM.setMobile(addr.getMobile());
			addrM.setPincode(addr.getPincode());
			addrM.setStreet(addr.getStreet());
			addrM.setState(addr.getStreet());
			addrM.setActive(addr.isActive());
			addrM.setWebsite(addr.getWebsite());
			return addrM;
		}
		}catch(Exception e) {
			logger.info("Exception in getAddress :"+ e.getMessage());
			return null;
		}
		
	}
	
	@Override
	public List<AddrModel> getByregId(String regId) {
		List<Address> address = addrDao.getByregId(regId);
		return addressMapper.entityList(address);
	}
	

	@Override
	public Response deleteAddressByAdr(String addressId) {
		try {
		return addrDao.deleteAddressByAdr(addressId);
		}catch(Exception e) {
			logger.error("Exception DeleteAddress :"+e.getMessage());
			return null;
		}
	}

	@Override
	public Response updateAddress(AddrModel address) {
		try {
			Address addr = new Address();
			BeanUtils.copyProperties(address, addr);
			Response res = addrDao.updateAddress(addr);
			return res;
		}catch(Exception e) {
			logger.error("Exception in UpdateAddress :" +e.getMessage());
		}
		return null;
	}


	
	/*@Override
	public List<AddrModel> getAddresses() {
		try {
			List<Address> addr =  addrDao.getAddresses();
			return addressMapper.entityList(addr);
		}catch(Exception e) {
			logger.error("Exception in getAddresses: "+e.getMessage());
		}
		return null;
	}*/

}

	
