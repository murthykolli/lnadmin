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

import com.legalnod.service.ContactInfoService;

/**
 * @author MurthyK
 *
 */

@Controller
public class ContactInfoController {
	
	@Autowired
	private ContactInfoService contactInfoService;	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ContactInfoController.class);
	
//	Contact Info Forms Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/contactInfo")
	@ResponseBody
	public ModelAndView contactInfo(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("contactInfo...Controller");
		return contactInfoService.contactInfoRedirection(req, sn);
	}
	
//	Primary Contact Info Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/primaryContactInfo")
	@ResponseBody
	public ModelAndView primaryContactInfo(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("primaryContactInfo...Controller");
		return contactInfoService.primaryContactInfoRedirection(req, sn);
	}
	
//	State Forms Payment Contact Info Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/stateFormsPaymentContact")
	@ResponseBody
	public ModelAndView stateFormsPaymentContact(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("stateFormsPaymentContact...Controller");
		return contactInfoService.stateFormsPaymentContactRedirection(req, sn);
	}
	
//	Federal Forms Payment Contact Info Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/federalFormsPaymentContact")
	@ResponseBody
	public ModelAndView federalFormsPaymentContact(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("federalFormsPaymentContact...Controller");
		return contactInfoService.federalFormsPaymentContactRedirection(req, sn);
	}
	
//	User Payment Transaction Contact Info Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/paymentTransactionContact")
	@ResponseBody
	public ModelAndView paymentTransactionContact(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("paymentTransactionContact...Controller");
		return contactInfoService.userPaymentTransactionContactRedirection(req, sn);
	}

}
