package com.awo.app.dao.image;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.awo.app.constant.StatusCode;
import com.awo.app.domain.image.Image;
import com.awo.app.response.Response;
import com.awo.app.utils.CommonUtils;
@Transactional
@Repository
public class ImgDaoImp implements ImgDao{
	private static final Logger logger = LoggerFactory.getLogger(ImgDaoImp.class);
	
	@Autowired
	EntityManager entityManager;

	@Override
	public Response saveImg(Image img) {
		Response res = CommonUtils.getResponseObject("Add User/Donor Image");
		try {
			entityManager.persist(img);
			res.setStatus(StatusCode.SUCCESS.name());
		}catch(Exception e) {
			logger.error("Exception in saveImage");
			res.setStatus(StatusCode.ERROR.name());
			res.setMessage(e.getMessage());
		}
		return res;
	}

	@Override
	public Image getImg(String regId) {
		Response res = CommonUtils.getResponseObject("User/Donor Image");
		try {
			String hql = "FROM Image WHERE regId=:img";
			return (Image) entityManager.createQuery(hql).setParameter("img", regId).getSingleResult();
		}catch(Exception e) {
		logger.error("Exception in Get UserImage");	
		res.setStatus(StatusCode.ERROR.name());
		res.setMessage(e.getMessage());
		}
		return null;
	}

	@Override
	public Response deleteById(String regId) {
		Response res = CommonUtils.getResponseObject("Delete DonorImage");
		try {
			Image img = getImg(regId);
			if(img!=null) {
				img.setActive(false);
				entityManager.flush();
				return res;
			}
			
		}catch(Exception e) {
			logger.info("Exception in Deleting Donor :"+e.getMessage());
			res.setMessage(e.getMessage());
			res.setStatus(StatusCode.ERROR.name());
			return res;
		}
		return null;
	}

}
