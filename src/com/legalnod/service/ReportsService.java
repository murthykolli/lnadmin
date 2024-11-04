package com.legalnod.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

public interface ReportsService {
	
	public ModelAndView allFormsReportsRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView dailyStateReportsRedirection(HttpServletRequest req, HttpSession sn);	
	
	public ModelAndView weeklyStateReportsRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView monthlyStateReportsRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView quarterlyStateReportsRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView yearlyStateReportsRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView dailyFederalReportsRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView weeklyFederalReportsRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView monthlyFederalReportsRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView quarterlyFederalReportsRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView yearlyFederalReportsRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView allFederalFormsReportsRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView allStateFormsReportsRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView reportsHomeRedirection(HttpServletRequest req, HttpSession sn);

}
