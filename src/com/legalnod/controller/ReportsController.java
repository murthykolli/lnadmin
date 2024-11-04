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

import com.legalnod.service.ReportsService;

/**
 * @author MurthyK
 *
 */

@Controller
public class ReportsController {
	
	@Autowired
	private ReportsService reportsService;	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportsController.class);
	
//	Reports Home Redirection Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/reportsHome")
	@ResponseBody
	public ModelAndView reportsHome(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("reportsHome...Controller");
		return reportsService.reportsHomeRedirection(req, sn);
	}
	
//	All Forms Reports Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/allFormsReports")
	@ResponseBody
	public ModelAndView allFormsReports(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("allFormsReports...Controller");
		return reportsService.allFormsReportsRedirection(req, sn);
	}
	
//	All State Forms Reports Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/allStateFormsReports")
	@ResponseBody
	public ModelAndView allStateFormsReports(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("allStateFormsReports...Controller");
		return reportsService.allStateFormsReportsRedirection(req, sn);
	}
	
//	All Federal Forms Reports Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/allFederalFormsReports")
	@ResponseBody
	public ModelAndView allFederalFormsReports(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("allFederalFormsReports...Controller");
		return reportsService.allFederalFormsReportsRedirection(req, sn);
	}
	
//	State Reports
	
//	Daily State Reports Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/dailyStateReports")
	@ResponseBody
	public ModelAndView dailyStateReports(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("dailyStateReports...Controller");
		return reportsService.dailyStateReportsRedirection(req, sn);
	}
	
//	Weekly State Reports Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/weeklyStateReports")
	@ResponseBody
	public ModelAndView weeklyStateReports(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("weeklyStateReports...Controller");
		return reportsService.weeklyStateReportsRedirection(req, sn);
	}
	
//	Monthly State Reports Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/monthlyStateReports")
	@ResponseBody
	public ModelAndView monthlyStateReports(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("monthlyStateReports...Controller");
		return reportsService.monthlyStateReportsRedirection(req, sn);
	}
	
//	Quarterly State Reports Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/quarterlyStateReports")
	@ResponseBody
	public ModelAndView quarterlyStateReports(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("quarterlyStateReports...Controller");
		return reportsService.quarterlyStateReportsRedirection(req, sn);
	}
	
//	Yearly Reports Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/yearlyStateReports")
	@ResponseBody
	public ModelAndView yearlyStateReports(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("yearlyStateReports...Controller");
		return reportsService.yearlyStateReportsRedirection(req, sn);
	}
	
//	Federal Reports
	
//	Daily Federal Reports Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/dailyFederalReports")
	@ResponseBody
	public ModelAndView dailyFederalReports(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("dailyFederalReports...Controller");
		return reportsService.dailyFederalReportsRedirection(req, sn);
	}
	
//	Weekly Federal Reports Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/weeklyFederalReports")
	@ResponseBody
	public ModelAndView weeklyFederalReports(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("weeklyFederalReports...Controller");
		return reportsService.weeklyFederalReportsRedirection(req, sn);
	}
	
//	Monthly Federal Reports Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/monthlyFederalReports")
	@ResponseBody
	public ModelAndView monthlyFederalReports(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("monthlyFederalReports...Controller");
		return reportsService.monthlyFederalReportsRedirection(req, sn);
	}
	
//	Quarterly Federal Reports Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/quarterlyFederalReports")
	@ResponseBody
	public ModelAndView quarterlyFederalReports(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("quarterlyFederalReports...Controller");
		return reportsService.quarterlyFederalReportsRedirection(req, sn);
	}
	
//	Yearly Federal Reports Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/yearlyFederalReports")
	@ResponseBody
	public ModelAndView yearlyFederalReports(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("yearlyFederalReports...Controller");
		return reportsService.yearlyFederalReportsRedirection(req, sn);
	}
	

}
