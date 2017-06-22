package com.nemustech.platform.lbs.web.danger.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nemustech.platform.lbs.common.aop.PageTitle;
import com.nemustech.platform.lbs.common.controller.DefaultController;

@Controller
@RequestMapping(value = DefaultController.REQUESTMAPPING_DANGER_ROOT, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8;")
public class BeaconDangerController extends DefaultController {
	private static final Logger logger = LoggerFactory.getLogger(BeaconDangerController.class);

	@PageTitle("비콘 관리")
	@RequestMapping(value = "/sadmin/beacon.do", method = RequestMethod.GET)
	public String dangerDeviceInfo(HttpServletRequest request, Model model) throws Exception {
		logger.debug("{} {}", request.getMethod(), request.getRequestURI());
		logger.debug("model: " + model);
		return "danger/sadmin/beacon/beaconList";
	}
}
