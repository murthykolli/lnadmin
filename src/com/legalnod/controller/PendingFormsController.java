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

import com.legalnod.service.PendingFormsService;

/**
 * @author MurthyK
 *
 */

@Controller
public class PendingFormsController {
	
	@Autowired
	private PendingFormsService pendingFormsService;	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PendingFormsController.class);
	
//	Pending Forms Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/pendingForms")
	@ResponseBody
	public ModelAndView pendingForms(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("pendingForms...Controller");
		return pendingFormsService.pendingFormsRedirection(req, sn);
	}
	
//	Pending State Forms Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/pendingStateForms")
	@ResponseBody
	public ModelAndView pendingStateForms(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("pendingStateForms...Controller");
		return pendingFormsService.pendingStateFormsRedirection(req, sn);
	}
	
//	Pending Federal Forms Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/pendingFederalForms")
	@ResponseBody
	public ModelAndView pendingFederalForms(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("pendingFederalForms...Controller");
		return pendingFormsService.pendingFederalFormsRedirection(req, sn);
	}
	
//	Pending Free Federal Forms Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/pendingFreeFederalForms")
	@ResponseBody
	public ModelAndView pendingFreeFederalForms(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("pendingFreeFederalForms...Controller");
		return pendingFormsService.pendingFreeFederalFormsRedirection(req, sn);
	}
	
//	Pending All State Forms Checkout Display Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/allStateFormsDataOperations")
	@ResponseBody
	public ModelAndView allStateFormsDataOperations(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("allStateFormsDataOperations...Controller");
		return pendingFormsService.allStateFormsDataOperationsFinishOrder(req, sn);
	}
	
//	Pending All Federal Forms Checkout Display Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/allFederalFormsDataOperations")
	@ResponseBody
	public ModelAndView allFederalFormsDataOperations(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("allFederalFormsDataOperations...Controller");
		return pendingFormsService.allFederalFormsDataOperationsFormFinishOrder(req, sn);
	}
	
//	Pending Free Federal Forms Checkout Display Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/allFreeFederalFormsDataOperations")
	@ResponseBody
	public ModelAndView allFreeFederalFormsDataOperations(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("allFreeFederalFormsDataOperations...Controller");
		return pendingFormsService.freeFederalTaxIdFormsCheckouDataDisplay(req, sn);
	}


}
