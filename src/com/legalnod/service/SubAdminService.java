package com.legalnod.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

public interface SubAdminService {
	
	public ModelAndView newSubAdminCreation(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView subAdminDataSavingInDB(HttpServletRequest request, HttpSession session);
	
	public ModelAndView subAdminActiveUsersRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView subAdminInactiveUsersRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView subAdminActiveUsersPermissions(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView subAdminInactiveUsersPermissions(HttpServletRequest req, HttpSession sn);

}
