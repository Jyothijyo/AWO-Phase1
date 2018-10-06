package com.awo.app.service.registration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.awo.app.constant.StatusCode;
import com.awo.app.controller.address.AddrController;
import com.awo.app.controller.image.ImgController;
import com.awo.app.dao.address.AddrDaoImp;
import com.awo.app.dao.authentication.AuthDao;
import com.awo.app.dao.image.ImgDao;
import com.awo.app.dao.registration.RegDao;
import com.awo.app.domain.address.Address;
import com.awo.app.domain.authentication.Authentication;
import com.awo.app.domain.registration.Registration;
import com.awo.app.domain.registration.UpdatePassword;
import com.awo.app.mapper.address.AddressMapper;
import com.awo.app.mapper.registration.RegistrationMapper;
import com.awo.app.mapper.requestRaise.RequestRaiseMapper;
import com.awo.app.model.address.AddrModel;
import com.awo.app.model.registration.AdminFilter;
import com.awo.app.model.registration.RegAdr;
import com.awo.app.model.registration.RegistrationModel;
import com.awo.app.requestRaise.domain.RequestRaise;
import com.awo.app.requestRaise.model.ModelRequest;
import com.awo.app.response.ErrorObject;
import com.awo.app.response.Response;
import com.awo.app.servicee.address.AddrService;
import com.awo.app.utils.CommonUtils;
import com.awo.app.utils.DateUtility;
import com.awo.app.utils.SmtpMailSender;

@Service
public class RegServiceImp implements RegService {

	private static final Logger logger = LoggerFactory.getLogger(RegServiceImp.class);

	@Autowired
	ImgController imgController;

	@Autowired
	private AddrDaoImp addrDaoImp;

	@Autowired
	private RegDao regDao;

	@Autowired
	private RegistrationMapper registrationMapper;

	@Autowired
	private AddressMapper addressMapper;

	@Autowired
	private AddrService addrService;

	@Autowired
	private AddrController addrController;

	@Autowired
	private AuthDao authDao;

	@Autowired
	private ImgDao imgDao;

	@Autowired
	private SmtpMailSender mailSender;

	@Autowired
	private RequestRaiseMapper Mapper;

	@Override
	public Response saveReg(RegistrationModel model) {
		Response res = new Response();
		try {
			Registration reg = new Registration();
			BeanUtils.copyProperties(model, reg);
			reg.setRegId(CommonUtils.generateRandomId());
			reg.setActive(true);
			reg.setDob(DateUtility.getDateByStringFormat(new Date(), DateUtility.DATE_FORMAT_MM_DD_YYYY));
			reg.setDob(model.getDob());
			reg.setIma_iba(model.getIma_iba());

			Address addr = new Address();
			addr.setAddressId(CommonUtils.generateRandomId());
			addr.setCountry(model.getCountry());
			addr.setArea(model.getArea());
			addr.setCity(model.getCity());
			addr.setEmailId(model.getEmailId());
			addr.setState(model.getState());
			addr.setStreet(model.getStreet());
			addr.setWebsite(model.getWebsite());
			addr.setMobile(model.getMobile());
			addr.setPincode(model.getPincode());
			addr.setDateAndTime(
					DateUtility.getDateByStringFormat(new Date(), DateUtility.DATE_FORMAT_DD_MM_YYYY_HHMMSS));
			/*
			 * addr.setModifiedDateAndTime( DateUtility.getDateByStringFormat(new Date(),
			 * DateUtility.DATE_FORMAT_DD_MM_YYYY_HHMMSS));
			 */
			addr.setActive(true);
			addr.setRegId(reg.getRegId());
			res = addrDaoImp.addAddress(addr);

			Authentication auth = new Authentication();
			auth.setAuthId(CommonUtils.generateRandomId());
			auth.setPassword(CommonUtils.encriptString(model.getPassword()));
			auth.setHint(model.getHint());
			auth.setRegId(reg.getRegId());
			auth.setRecentLogin(
					DateUtility.getDateByStringFormat(new Date(), DateUtility.DATE_FORMAT_DD_MM_YYYY_HHMMSS));
			auth.setActive(true);
			res = authDao.addAuth(auth);

			res = regDao.saveReg(reg);

			return res;
		} catch (Exception e) {
			logger.info("Exception in add User :" + e.getMessage());
			ErrorObject err = CommonUtils.getErrorResponse("Exception", "Mobile/EmailId already exists!!");
			res.setErrors(err);
			res.setMessage(e.getMessage());
			res.setStatus(StatusCode.ERROR.name());
			return res;
			}

	}


	@Override
	public List<RegAdr> getDetailsByAreaAndPincode(RegistrationModel regAdr) {
		List<RegAdr> addressList = new ArrayList<RegAdr>();
		try {
			List<String> adr = regDao.getDetailsByAreaAndPincode(regAdr);
			for (String id : adr) {
				RegAdr regM = getDetailsById(id);
				addressList.add(regM);
			}
			return addressList;
		} catch (Exception e) {
			logger.info("Exception in get User/Donor Details :" + e.getMessage());
			return null;
		}

	}

	@Override
	public List<AdminFilter> getDetailByAreaAndPincode(RegistrationModel regAdr) {
		try {
			List<AdminFilter> fil = new ArrayList<AdminFilter>();
			AdminFilter filter =new AdminFilter();
			List<String> ids = regDao.getDetailsByAreaAndPincode(regAdr);
			for (String id : ids) {
				List<AddrModel> address = addrService.getByregId(id);
				Registration reg = regDao.getDetailsById(id);
				for(AddrModel addr:address) {
					filter.setFirstName(reg.getFirstName());
					filter.setMobile(addr.getMobile());
					filter.setAddress(addr.getArea()+", "+addr.getCity()+", "+addr.getState()+", "+addr.getPincode());
					fil.add(filter);
				}

			}
			return fil;
		} catch (Exception e) {
			logger.info("Exception in get User/Donor Details :" + e.getMessage());
			return null;
		}
	}


	@Override
	public RegAdr getDetailsById(String regId) {
		try {
			RegAdr model = new RegAdr();
			Registration reg = regDao.getDetailsById(regId);
			BeanUtils.copyProperties(reg, model);
			
			List<AddrModel> address = addrService.getByregId(regId);
			model.setAddress(address);

			Authentication auth = authDao.getAuth(regId);
			model.setAuthId(auth.getAuthId());
			model.setRecentLogin(auth.getRecentLogin());
			model.setActive(auth.isActive());
			try {
				String url = imgController.getImg(regId);
				if (url != null) {
					model.setUrl(imgController.getImg(regId));
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			return model;
		} catch (Exception e) {
			logger.info("Exception in get User/Donor Details :" + e.getMessage());
			return null;
		}

	}



	@Override
	public RegAdr getById(String regId) {
		try {
			Registration reg = regDao.getDetailsById(regId);
			RegAdr model = new RegAdr();
			BeanUtils.copyProperties(reg, model);
			return model;
		} catch (Exception e) {
			logger.info("Exception in get User/Donor Details :" + e.getMessage());
			return null;
		}

	}

	@Override
	public Response deleteById(String regId) {
		try {
			Response res = addrDaoImp.deleteAddress(regId);
			res = authDao.deleteById(regId);
			res = imgDao.deleteById(regId);
			res = regDao.deleteById(regId);

			return res;
		} catch (Exception e) {
			logger.info("Exception in DeleteById :" + e.getMessage());
			return null;
		}

	}

	@Override
	public RegAdr authenticate(RegistrationModel model) {
		try {
			model.setPassword(CommonUtils.encriptString(model.getPassword()));
			Authentication reg = new Authentication();
		 /*	String num = "[0-9]+";
			if(model.getMobile().matches(num)) {
				BeanUtils.copyProperties(model, reg);*/
			String id = regDao.authenticate(model);
			System.out.println(id);
			if (id == null) {
				return null;
			} else {
				RegAdr regM = getDetailsById(id);
				Authentication auth = updateAuth(id);
				return regM;
			}
		}catch(Exception e) {
			logger.info(e.getMessage());
			return null;
		}
	}
	private Authentication updateAuth(String id) {
		RegAdr regM = getDetailsById(id);
		Response res = authDao.updateAuth(regM);
		return null;
	}


	@Override
	public List<RegistrationModel> getUsers() {
		try {
			List<Registration> users = regDao.getUsers();

			return registrationMapper.entityList(users);
		} catch (Exception e) {
			logger.error("Exception getUsers:" + e.getMessage());
			return null;
		}

	}


	@Override
	public String resetPassword(RegistrationModel model) {
		try {
			String auth = regDao.isDonorExist(model);
			if(auth!=null) {
				String password = CommonUtils.resetgenerateRandomId();
				String status = regDao.resetPassword(auth, CommonUtils.encriptString(password));
				if(status.equals(StatusCode.SUCCESS.name())){
					String email = model.getEmailId();
					String pass = password;
					mailSender.send(email, "AWO team reset password", "Reset Password - "+pass);
				}
				return status;
			}else {
				return StatusCode.ERROR.name();
			}

		}catch(Exception e) {
			logger.info("Exception in ResetPassword:"+e.getMessage());
			return StatusCode.ERROR.name();
		}
	}

	@Override
	public String changePassword(UpdatePassword model) {
		try {
			Authentication auth = authDao.getAuth(model.getRegId());
			if(auth.getPassword().equals(CommonUtils.encriptString(model.getOldPassword()))) {
				if(model.getNewPassword().equals(model.getConfirmPassword())) {
					String str = authDao.changePassword(auth.getRegId(), CommonUtils.encriptString(model.getNewPassword()));
					return str;
				}else {
					logger.error("New Password and ConfirmPassword is not matching!!.");
					return StatusCode.ERROR.name();
				}
			}else {
				logger.error("You entered an invalid Password!!.");
				return StatusCode.ERROR.name();
			}
		}catch(Exception e) {
			logger.error("Exception ChangePassword:"+e.getMessage());
		}
		return null;
	}


	@Override
	public Response AddRequestRaise(ModelRequest model) {
		Response response = new Response();
		try {
			RequestRaise req =new RequestRaise();
			BeanUtils.copyProperties(model, req);
			req.setMobile(model.getMobile());
			req.setReqRId(CommonUtils.generateRandomId());
			req.setReqDateAndTime(DateUtility.getDateByStringFormat(new Date(), DateUtility.DATE_FORMAT_DD_MM_YYYY_HHMMSS));
			response=regDao.AddRequestRaise(req);
			return response;
		}catch(Exception e) {
			logger.error("Exception in AddRequest" +e.getMessage());
			response.setMessage("Exception in AddRequest:" +e.getMessage());
			return response;
		}

	}


	@Override
	public List<ModelRequest> getAllRequest() {
		try {
			List<RequestRaise> requset = regDao.getAllRequest();
			return Mapper.entityList(requset);
		}catch(Exception e) {
			logger.info("Exception in Get AllRequest: "+e.getMessage());
			return null;
		}
	}


	@Override
	public Response updateRequest(ModelRequest model) {
		try {
			RequestRaise req = new RequestRaise();
			BeanUtils.copyProperties(model, req);
			return regDao.updateRequest(req);
		}catch(Exception e) {
			logger.error("Exception in UpdateRequest:"+ e.getMessage());
			Response res = new Response();
			res.setStatus(StatusCode.ERROR.name());
			res.setMessage(e.getMessage());
			return res;
		}
	}

	@Scheduled(fixedRate=24*60*60*1000)
	public void deleteExpireAccount() {
		try {
			List<Authentication> authentication=authDao.getAll();
			for(Authentication auth:authentication)
			{
				SimpleDateFormat sdf1=new SimpleDateFormat("dd-MM-yyyy");
				sdf1.setTimeZone(TimeZone.getTimeZone("IST"));
				Date login=sdf1.parse(auth.getRecentLogin());
				System.out.println("\n\nlogin="+login+"\n\n\n");
				int days;
				Date date=new Date();
				System.out.println("\n\ndate="+date+"\n\n\n");
				long difference=(date.getTime()-login.getTime())/86400000;
				long diff=Math.abs(difference);
				days=(int)diff+1;
				
				System.out.println("\n\ndays="+days+"\n\n\n");
				if(days>=90)
				{
					authDao.deleteExpireAccount(auth.getRegId());
				}



			}

		}

		catch(Exception e)
		{
			logger.error("Exception in UpdateRequest:"+ e.getMessage());


		}
	}
}
