package com.legalnod.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.legalnod.service.SubAdminService;

/**
 * @author MurthyK
 *
 */

@Controller
public class SubAdminController {
	
	@Autowired
	private SubAdminService subAdminService;	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SubAdminController.class);
	
//	Admin Home Page Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/subAdminInfo")
	@ResponseBody
	public ModelAndView subAdminInfo(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("subAdminInfo...Controller");
		return subAdminService.newSubAdminCreation(req, sn);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/subAdminDataSavingInDB")
	public ModelAndView subAdminDataSavingInDB(HttpServletRequest request, HttpSession session) {	
		LOGGER.debug("subAdminDataSavingInDB ...Controller");
		return subAdminService.subAdminDataSavingInDB(request,session);
	}
	
//	Sub Admin Active Users Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/subAdminActiveUsers")
	@ResponseBody
	public ModelAndView subAdminActiveUsers(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("subAdminActiveUsers...Controller");
		return subAdminService.subAdminActiveUsersRedirection(req, sn);
	}
	
//	Sub Admin Inactive Users Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/subAdminInactiveUsers")
	@ResponseBody
	public ModelAndView subAdminInactiveUsers(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("subAdminInactiveUsers...Controller");
		return subAdminService.subAdminInactiveUsersRedirection(req, sn);
	}
	
//	Sub Admin Active Users Permissions Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/subAdminActiveUserPermissions")
	@ResponseBody
	public ModelAndView subAdminActiveUserPermissions(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("subAdminActiveUserPermissions...Controller");
		return subAdminService.subAdminActiveUsersPermissions(req, sn);
	}
	
//	Sub Admin Inactive Users Permissions Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/subAdminInactiveUserPermissions")
	@ResponseBody
	public ModelAndView subAdminInactiveUserPermissions(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("subAdminInactiveUserPermissions...Controller");
		return subAdminService.subAdminInactiveUsersPermissions(req, sn);
	}

}
