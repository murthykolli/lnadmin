package com.legalnod.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

public interface ContactInfoService {
	
	public ModelAndView contactInfoRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView primaryContactInfoRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView stateFormsPaymentContactRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView federalFormsPaymentContactRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView userPaymentTransactionContactRedirection(HttpServletRequest req, HttpSession sn);

}
