package com.awo.app.controller.image;

import java.io.File;
import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.awo.app.constant.StatusCode;
import com.awo.app.domain.image.Image;
import com.awo.app.domain.registration.Registration;
import com.awo.app.model.image.ModelImg;
import com.awo.app.response.ErrorObject;
import com.awo.app.response.Response;
import com.awo.app.service.image.ImgService;
import com.awo.app.utils.CommonUtils;

@RestController
public class ImgController {
	
	private static final Logger logger = LoggerFactory.getLogger(ImgController.class);
	
	@Autowired
	private ImgService imgService;
	
	@PostMapping(value="/image")
	public Response saveImg(@RequestParam("file") MultipartFile file, @RequestParam("regId") String regId, RedirectAttributes redirectAttributes) throws Exception{
		logger.info("Save User/DonorImage :" + file);
		if(file.isEmpty()) {
			Response res = CommonUtils.getResponseObject("Upload Image Exception");
			ErrorObject err = CommonUtils.getErrorResponse("File not found", "Please select a file to upload");
			res.setErrors(err);
			res.setStatus(StatusCode.ERROR.name());
			return res;
		}
		ModelImg mImg = new ModelImg();
		mImg.setRegId(regId);
		return imgService.saveImg(mImg, file);
		
	}
	
	@GetMapping(value="/image/{regId}")
	public String getImg(@PathVariable("regId") String regId){
		Image img = null;
		try {
			img = imgService.getImg(regId);
			File file = new File("F:\\img\\"+img.getImgName());
			FileOutputStream out = new FileOutputStream(file);
		out.write(img.getPic(), 0, img.getPic().length-1);
			out.close();
			
		}catch(Exception e) {
			logger.info(e.getMessage());
		}
		
		return "F:\\img\\"+img.getImgName();
		
	}
	

}
