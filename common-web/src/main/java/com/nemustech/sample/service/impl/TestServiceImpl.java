package com.nemustech.sample.service.impl;

import com.nemustech.common.service.impl.CommonServiceImpl;
import com.nemustech.sample.Constants;
import com.nemustech.sample.model.Test;
import com.nemustech.sample.service.TestService;

import org.springframework.stereotype.Service;

/**
 * @author skoh
 */
@Service
public class TestServiceImpl extends CommonServiceImpl<Test> implements TestService {
	@Override
	public String getCacheName() {
		return Constants.CACHE_NAME;
	}
}