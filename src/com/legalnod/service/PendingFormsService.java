package com.legalnod.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.json.simple.JSONObject;

public interface PendingFormsService {
	
	public ModelAndView pendingFormsRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView pendingStateFormsRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView pendingFederalFormsRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView pendingFreeFederalFormsRedirection(HttpServletRequest req, HttpSession sn);
	
	public int takeFormIdFromDB(HttpSession sn);
	
	public JSONObject convertFromStringToJSONFormat(String capturedInfoInDB);
	
	public ModelAndView allStateFormsDataOperationsFinishOrder(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView businessStateFormsCheckouDataDisplay(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView additionalServiceFormsCheckouDataDisplay(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView stateTaxIdFormsCheckouDataDisplay(HttpServletRequest req, HttpSession sn);
	
	public int takeUserIdFromDB(HttpSession sn);
	
	public ModelAndView allFederalFormsDataOperationsFormFinishOrder(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView federalTaxIdFormsCheckouDataDisplay(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView sCorporationFormsCheckouDataDisplay(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView fiveZeroOneFormsCheckouDataDisplay(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView freeFederalTaxIdFormsCheckouDataDisplay(HttpServletRequest req, HttpSession sn);

}
