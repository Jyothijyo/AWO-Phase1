package com.awo.app.dao.registration;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.awo.app.constant.StatusCode;
import com.awo.app.dao.address.AddrDao;
import com.awo.app.dao.authentication.AuthDao;
import com.awo.app.domain.address.Address;
import com.awo.app.domain.authentication.Authentication;
import com.awo.app.domain.registration.Registration;
import com.awo.app.model.registration.RegistrationModel;
import com.awo.app.requestRaise.domain.RequestRaise;
import com.awo.app.requestRaise.domain.Status;
import com.awo.app.response.ErrorObject;
import com.awo.app.response.Response;
import com.awo.app.utils.CommonUtils;

@Transactional
@Repository
public class RegDaoImp implements RegDao {

	private static final Logger logger = LoggerFactory.getLogger(RegDaoImp.class);

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private AuthDao authDao;
	
	@Autowired
	private AddrDao addrDao;

	@Override
	public Response saveReg(Registration reg) {
		Response res = CommonUtils.getResponseObject("Saved User Registration Dtails");
		try {
			 entityManager.persist(reg);
			 
		} catch (Exception e) {
			logger.error("Exception in saving RegistrationDetails :" + e.getMessage()	);
			res.setStatus(StatusCode.ERROR.name());
			res.setErrors(e.getMessage());
			
		}
		return res;
	}

	@Override
	public List<String> getDetailsByAreaAndPincode(RegistrationModel regAdr) {
		try {
			List<String> id = new ArrayList<String>();
			if((regAdr.getState()==null) && (regAdr.getCity()==null) && (regAdr.getPincode()==null) && (regAdr.getBloodGroup()!=null)) {
				String hql = "SELECT regId FROM Registration WHERE bloodGroup=:bloodGroup AND isActive=true";
				id = entityManager.createQuery(hql).setParameter("bloodGroup", regAdr.getBloodGroup()).getResultList();
			}
			else if((regAdr.getCity()==null) && (regAdr.getPincode()==null) && (regAdr.getBloodGroup()!=null) && (regAdr.getState()!=null)) {
				String hql = "SELECT regId FROM Registration WHERE bloodGroup=:bloodGroup AND availability=true AND isActive=true AND regId in(SELECT regId FROM Address WHERE state=:state AND isActive=true)";
				id = entityManager.createQuery(hql).setParameter("bloodGroup", regAdr.getBloodGroup()).setParameter("state", regAdr.getState()).getResultList();
			}
			else if((regAdr.getPincode()==null) && (regAdr.getBloodGroup()!=null) && (regAdr.getState()!=null) && (regAdr.getCity()!=null)) {
				String hql = "SELECT regId FROM Registration WHERE bloodGroup=:bloodGroup AND availability=true AND isActive=true AND regId in(SELECT regId FROM Address WHERE state=:state AND city=:city AND isActive=true)";
				id = entityManager.createQuery(hql).setParameter("bloodGroup", regAdr.getBloodGroup()).setParameter("state", regAdr.getState()).setParameter("city", regAdr.getCity()).getResultList();
			}else {
			String hql ="SELECT regId FROM Registration WHERE bloodGroup=:bloodGroup AND availability=true AND isActive=true AND regId in(SELECT regId FROM Address WHERE state=:state AND pincode=:pincode AND city=:city AND isActive=true)";
			id =  entityManager.createQuery(hql).setParameter("bloodGroup", regAdr.getBloodGroup()).setParameter("state", regAdr.getState()).setParameter("pincode", regAdr.getPincode()).setParameter("city", regAdr.getCity()).getResultList();
			}
			
			if(id.isEmpty()) {
			return null;
			}
			return id;
		} catch (Exception e) {
			logger.error("Exception in get details:" + e.getMessage());
			return null;
		}
	}


	
	@Override
	public Registration getDetailsById(String regId) {
		try {
			String hql = "FROM Registration WHERE regId=:regId AND isActive=true";
			return (Registration) entityManager.createQuery(hql).setParameter("regId", regId).getSingleResult();
		} catch (Exception e) {
			logger.error("Exception in get details:" + e.getMessage());
			return null;
		}
	}

	@Override
	public Response deleteById(String regId) {
		Response res = CommonUtils.getResponseObject("Deleted UserDetails");
		try {
			Registration reg = getDetailsById(regId);
			reg.setActive(false);
			entityManager.flush();
			/*for(Address adr:reg.getAddress()) {
				adr.setActive(false);
				entityManager.flush();
			}*/
			
			return res; 
		/*	String hql = "DELETE FROM Registration WHERE regId=:regId";
			return (Response) entityManager.createQuery(hql).setParameter("regId", regId).getResultList();*/
		} catch (Exception e) {
			
			logger.error("Exception in Delete User :" + e.getMessage());
			res.setStatus(StatusCode.ERROR.name());
			res.setErrors(e.getMessage());
			return res;
		}
		

	}

	@Override
	public String authenticate(RegistrationModel model) {
		try {
			
//			SELECT (SELECT regId FROM Address WHERE (emailId=? OR mobile=?)) FROM Address WHERE regId in (SELECT regId FROM Authentication WHERE password=?)
			
			String hql ="SELECT regId FROM Authentication WHERE password=:pas AND isActive=true AND regId in (SELECT regId FROM Address WHERE (emailId=:email OR mobile=:mobile) AND isActive=true)";
			String auth= (String) entityManager.createQuery(hql).setParameter("email", model.getMobile()).setParameter("mobile", model.getMobile())
					.setParameter("pas", model.getPassword()).getSingleResult();
				if(auth.isEmpty()) {
					return null;
				}
					return auth;
		}catch (Exception e){
			
			logger.error("Exception in authenticate :" + e.getMessage());
			Response res =new Response();
			res.setMessage(e.getMessage());
			
		}
		return null;
		
	}

	@Override
	public List<Registration> getUsers() {
		try {
			String hql = "FROM Registration WHERE isActive=true";
			return (List<Registration>)entityManager.createQuery(hql).getResultList();
		}catch(Exception e) {
			logger.error("Exception in getUsers:" + e.getMessage());
			return null;
		}
		
	}

	@Override
	public String isDonorExist(RegistrationModel model) {
		try {
		String hql = "SELECT regId FROM Address WHERE emailId=:id";
		return (String) entityManager.createQuery(hql).setParameter("id", model.getEmailId()).getSingleResult();
		}catch(Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public String resetPassword(String regId, String encriptString) {
		try {
		Authentication auth = authDao.getActive(regId);
		Address adr = addrDao.reActive(regId);
		auth.setPassword(encriptString);
		adr.setActive(true);
		auth.setActive(true);
		entityManager.flush();
		return StatusCode.SUCCESS.name();
		}catch(Exception e) {
			logger.error("Exception ResetPassword:"+e.getMessage());
			return StatusCode.ERROR.name();
		}
	}

	@Override
	public Response AddRequestRaise(RequestRaise req) {
		Response response=CommonUtils.getResponseObject("Add requestRaise");
		try{
			entityManager.persist(req);
			return response;
		}
		catch(Exception e){
			response.setStatus(StatusCode.ERROR.name());
			response.setMessage("Exception :" +e.getMessage());
			logger.info("Exception in AddRequestRaise:"+e.getMessage());
			return response;
		}
		
	}

	@Override
	public List<RequestRaise> getAllRequest() {
		Response res = CommonUtils.getResponseObject("Get All RequestRaise:");
		try {
			String hql ="FROM RequestRaise WHERE status IN :status";
			List<RequestRaise> req = entityManager.createQuery(hql).setParameter("status", EnumSet.of(Status.OPEN, Status.INPROCESS)).getResultList();
			System.out.println(req);
			return req;
		}catch(Exception e) {
			logger.error("Exception in get All RequestRaise:"+e.getMessage());
			return null;
		}
	}

	@Override
	public Response updateRequest(RequestRaise req) {
		Response res = CommonUtils.getResponseObject("Updated RequestRaise:");
		try {
			RequestRaise reqR = getRequestById(req.getReqRId());
			reqR.setStatus(req.getStatus());
			entityManager.flush();
			return res;
		}catch(Exception e) {
			logger.error("Exception in Updateting RequestRaise:"+e.getMessage());
			ErrorObject err = CommonUtils.getErrorResponse("Exception", "Exception update RequestRaise");
			res.setErrors(err);
			res.setMessage("Exception :"+e.getMessage());
			res.setStatus(StatusCode.ERROR.name());
			return res;
		}
	}

	private RequestRaise getRequestById(String reqRId) {
		Response res = CommonUtils.getResponseObject("Get RequestRaise:");
		try {
			String hql ="FROM RequestRaise WHERE reqRId=:req";
			RequestRaise req = (RequestRaise) entityManager.createQuery(hql).setParameter("req", reqRId).getSingleResult();
			System.out.println(req);
			return req;
		}catch(Exception e) {
			logger.error("Exception in get All RequestRaise:"+e.getMessage());
			return null;
		}
	}
		

}
