package com.awo.app.service.image;

import org.springframework.web.multipart.MultipartFile;

import com.awo.app.domain.image.Image;
import com.awo.app.domain.registration.Registration;
import com.awo.app.model.image.ModelImg;
import com.awo.app.response.Response;

public interface ImgService {

	Response saveImg(ModelImg mImg, MultipartFile file);

	Image getImg(String regId);

}
