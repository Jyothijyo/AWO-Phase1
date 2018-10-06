package com.awo.app.service.image;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.awo.app.constant.StatusCode;
import com.awo.app.dao.image.ImgDao;
import com.awo.app.domain.image.Image;
import com.awo.app.model.image.ModelImg;
import com.awo.app.response.Response;
import com.awo.app.utils.CommonUtils;
import com.awo.app.utils.DateUtility;

@Service
public class ImgServiceImp implements ImgService {
	private static final Logger logger = LoggerFactory.getLogger(ImgServiceImp.class);

	@Autowired
	ImgDao imgDao;

	@Override
	public Response saveImg(ModelImg mImg, MultipartFile file) {
		try {
			Image img = new Image();
			BeanUtils.copyProperties(mImg, img);
			img.setImgId(CommonUtils.generateRandomId());
			img.setImgName(file.getOriginalFilename());
			img.setType(file.getContentType());
			img.setRegId(mImg.getRegId());
			img.setCreatedDate(DateUtility.getDateByStringFormat(new Date(), DateUtility.DATE_FORMAT_DD_MMM_YYYY));
			img.setModifiedDate(DateUtility.getDateByStringFormat(new Date(), DateUtility.DATE_FORMAT_DD_MMM_YYYY));
			try {
				img.setPic(file.getBytes());

			} catch (Exception e) {
				Response res = CommonUtils.getResponseObject("Upload File Error");
				res.setStatus(StatusCode.ERROR.name());
				res.setMessage(e.getMessage());
				return res;
			}
			Response res = imgDao.saveImg(img);
			return res;
		} catch (Exception e) {
			logger.error("Exception in saving Image:" + e.getMessage());
		}
		return null;
	}

	@Override
	public Image getImg(String regId) {
		try {
			Image img = imgDao.getImg(regId);
			ModelImg Mimg = new ModelImg();
			BeanUtils.copyProperties(img, Mimg);
			return img;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

}
