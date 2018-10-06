package com.awo.app.dao.address;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.awo.app.constant.StatusCode;
import com.awo.app.domain.address.Address;
import com.awo.app.mapper.address.AddressMapper;
import com.awo.app.response.ErrorObject;
import com.awo.app.response.Response;
import com.awo.app.utils.CommonUtils;
import com.awo.app.utils.DateUtility;

@Transactional
@Repository
public class AddrDaoImp implements AddrDao {
	private static final Logger logger = LoggerFactory.getLogger(AddrDaoImp.class);

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	private AddressMapper addressMapper;

	@Override
	public Response addAddress(Address addr) {
		Response res = CommonUtils.getResponseObject("Save Address");
		try {
			entityManager.persist(addr);
			return res;
		} catch (Exception e) {
			logger.info("Exception in Save Address :" + e.getMessage());
			res.setStatus(StatusCode.ERROR.name());
			res.setErrors(e.getMessage());
		}
		return res;
	}

	
	 @Override 
	 public List<Address> getByregId(String regId) 
	 { 
		 try {
			 String hql = "FROM Address WHERE regId=:id and isActive=true"; 
			 return (List<Address>) entityManager.createQuery(hql).setParameter("id", regId).getResultList(); 
			 } catch (Exception e) {
	  logger.error("Exception in getAddress: " + e.getMessage()); 
	  		return null; 
	  		}
	  
	  }
	 

	@Override
	public Address getByAddressesId(String addressId) {
		try {
			String hql = "FROM Address WHERE addressId=:id and isActive=true";
			return (Address) entityManager.createQuery(hql).setParameter("id", addressId).getSingleResult();
		} catch (Exception e) {
			logger.error("Exception in getAddress: " + e.getMessage());
		}
		return null;
	}

	@Override
	public Response deleteAddressByAdr(String addressId) {
		Response res = CommonUtils.getResponseObject("Address deleted");
		try {
			Address addr = getByAddressesId(addressId);
			addr.setActive(false);
			entityManager.flush();
			return res;
		} catch (Exception e) {
			ErrorObject err = CommonUtils.getErrorResponse("Exception", "Address Not Found");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
			logger.error("Exception deleteAddress :" + e.getMessage());
			return res;
		}

	}

	@Override
	public Response updateAddress(Address addr) {
		Response res = CommonUtils.getResponseObject("Address updated: ");
		try {
			/*
			 * List<Address> address = getByAddressesId(addr.getRegId()); Address addr1 =
			 * (Address) addressMapper.entityList(address);
			 */
			Address add = getByAddressesId(addr.getAddressId());
			add.setArea(addr.getArea());
			add.setCity(addr.getCity());
			add.setDateAndTime(addr.getDateAndTime());
			add.setEmailId(addr.getEmailId());
			add.setMobile(addr.getMobile());
			add.setPincode(addr.getPincode());
			add.setStreet(addr.getStreet());
			add.setState(addr.getStreet());
			add.setWebsite(addr.getWebsite());
			add.setModifiedDateAndTime(
					DateUtility.getDateByStringFormat(new Date(), DateUtility.DATE_FORMAT_DD_MM_YYYY_HHMMSS));
			add.setActive(true);
			entityManager.flush();
			return res;
		} catch (Exception e) {

		}
		return null;
	}

/*	@Override
	public List<Address> getAddresses() {
		try {
			String hql = "FROM Address";
			List<Address> addr = entityManager.createQuery(hql).getResultList();
			return addr;
		} catch (Exception e) {
			logger.error("Exception in getAddress:" + e.getMessage());
		}
		return null;
	}*/
	
	@Override
	public Response deleteAddress(String regId) {
		Response res = CommonUtils.getResponseObject("Address deleted");
		try {
			List<Address> addr = getByregId(regId);
			if(addr!=null) {
			for (Address address : addr) {
				address.setActive(false);
			}
			entityManager.flush();
			}
			return res;
		} catch (Exception e) {
			ErrorObject err = CommonUtils.getErrorResponse("Exception", "Address Not Found");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
			logger.error("Exception deleteAddress :" + e.getMessage());
			return res;
		}
	}


	@Override
	public Address reActive(String regId) {
		 try {
			 String hql = "FROM Address WHERE regId=:id"; 
			 return (Address) entityManager.createQuery(hql).setParameter("id", regId).getSingleResult(); 
			 } catch (Exception e) {
	  logger.error("Exception in getAddress: " + e.getMessage()); 
	  		return null; 
	  		}
	  
	  }

}
