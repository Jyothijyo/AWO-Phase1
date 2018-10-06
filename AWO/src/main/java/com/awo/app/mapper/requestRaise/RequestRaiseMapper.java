package com.awo.app.mapper.requestRaise;

import org.springframework.stereotype.Component;

import com.awo.app.mapper.AbstractModelMapper;
import com.awo.app.requestRaise.domain.RequestRaise;
import com.awo.app.requestRaise.model.ModelRequest;

@Component
public class RequestRaiseMapper extends AbstractModelMapper<ModelRequest, RequestRaise>{

	@Override
	public Class<ModelRequest> entityType() {
		return ModelRequest.class;
	}

	@Override
	public Class<RequestRaise> modelType() {
		return RequestRaise.class;
	}

}
