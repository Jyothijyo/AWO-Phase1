package com.awo.app.mapper.image;

import com.awo.app.domain.image.Image;
import com.awo.app.mapper.AbstractModelMapper;
import com.awo.app.model.image.ModelImg;

public class ImgMapper extends AbstractModelMapper<ModelImg, Image> {

	@Override
	public Class<ModelImg> entityType() {
		return ModelImg.class;
	}

	@Override
	public Class<Image> modelType() {
		return Image.class;
	}

}
