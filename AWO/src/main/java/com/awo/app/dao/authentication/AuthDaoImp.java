package com.awo.app.dao.authentication;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.awo.app.constant.StatusCode;
import com.awo.app.domain.authentication.Authentication;
import com.awo.app.model.registration.RegAdr;
import com.awo.app.response.Response;
import com.awo.app.utils.CommonUtils;
import com.awo.app.utils.DateUtility;

@Transactional
@Repository
public class AuthDaoImp implements AuthDao {

	private static final Logger logger = LoggerFactory.getLogger(AuthDaoImp.class);

	@PersistenceContext
	private EntityManager entityManager;


	@Override
	public Response addAuth(Authentication auth) {
		Response res = CommonUtils.getResponseObject("Saved User Registration Dtails");
		try {
			entityManager.persist(auth);

		} catch (Exception e) {
			logger.error("Exception in saving RegistrationDetails :" + e.getMessage());
			res.setStatus(StatusCode.ERROR.name());
			res.setErrors(e.getMessage());

		}
		return res;
	}


	@Override
	public Authentication getAuth(String regId) {
		try {
			String hql = "FROM Authentication WHERE regId=:id and isActive=true";
			return  (Authentication) entityManager.createQuery(hql).setParameter("id", regId).getSingleResult();
		} catch (Exception e) {
			logger.error("Exception in getAuthentication: " + e.getMessage());
		}
		return null;
	}


	@Override
	public Response updateAuth(RegAdr regM) {
		try {
			Authentication auth = getAuth(regM.getRegId());
			//			auth.setPassword(auth.getPassword());
			//			auth.setHint(regM.getHint());
			//			auth.setRegId(regM.getRegId());
			auth.setRecentLogin(DateUtility.getDateByStringFormat(new Date(), DateUtility.DATE_FORMAT_DD_MM_YYYY_HHMMSS));
			//			auth.setActive(true);

			entityManager.flush();
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}


	@Override
	public Response deleteById(String regId) {
		Response res = CommonUtils.getResponseObject("Delete Authentication.");
		try {
			Authentication auth = getAuth(regId);
			if(auth!=null) {
				auth.setActive(false);
				entityManager.flush();
			}
			return res;
		}catch(Exception e) {
			logger.info("Exception Delete Authentication:"+ e.getMessage());
			res.setStatus(StatusCode.ERROR.name());
			res.setMessage(e.getMessage());
			return res;
		}
	}




	@Override
	public String changePassword(String regId, String encriptString) {
		try {
			Authentication auth = getAuth(regId);
			if(auth!=null) {
				auth.setPassword(encriptString);
				entityManager.flush();

			}

		}catch(Exception e) {
			logger.info("Exception Delete Authentication:"+ e.getMessage());
			return StatusCode.ERROR.name();
		}
		return StatusCode.SUCCESS.name();

	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Authentication> getAll() {
		try {
			String hql = "FROM Authentication WHERE isActive=true";
			List<Authentication> auth = (List<Authentication>)entityManager.createQuery(hql).getResultList();
			return auth;
		}catch(Exception e) {

		}
		return null;
	}


	@Override
	public void deleteExpireAccount(String regId) {
		try {
			Authentication auth = getAuth(regId);
			auth.setActive(false);

			entityManager.flush();
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
	}


	@Override
	public Authentication getActive(String regId) {
		try {
			String hql = "FROM Authentication WHERE regId=:id";
			return  (Authentication) entityManager.createQuery(hql).setParameter("id", regId).getSingleResult();
		} catch (Exception e) {
			logger.error("Exception in getAuthentication: " + e.getMessage());
		}
		return null;
	}

}
