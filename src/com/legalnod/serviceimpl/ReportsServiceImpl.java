package com.legalnod.serviceimpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.legalnod.daoimpl.AllFederalFormsPaymentInfoSavingDAOImpl;
import com.legalnod.daoimpl.AllStateFormsPaymentInfoSavingDAOImpl;
import com.legalnod.model.AllFederalFormsPaymentInfoSaving;
import com.legalnod.model.AllStateFormsPaymentInfoSaving;
import com.legalnod.service.ReportsService;

/**
 * @author MurthyK
 *
 */

public class ReportsServiceImpl implements ReportsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportsServiceImpl.class);
	
	private String dailyStateReportsCount = "dailyStateReportsCount";
    private String weeklyStateReportsCount = "weeklyStateReportsCount";
    private String monthlyStateReportsCount = "monthlyStateReportsCount";
    private String quarterlyStateReportsCount = "quarterlyStateReportsCount";
    private String yearlyStateReportsCount = "yearlyStateReportsCount";
    
    private String dailyFederalReportsCount = "dailyFederalReportsCount";
    private String weeklyFederalReportsCount = "weeklyFederalReportsCount";
    private String monthlyFederalReportsCount = "monthlyFederalReportsCount";
    private String quarterlyFederalReportsCount = "quarterlyFederalReportsCount";
    private String yearlyFederalReportsCount = "yearlyFederalReportsCount";
    
    private String adminFirstName = "adminFirstName";
    private String adminFirstNameInSn = "adminFirstNameInSn";
    private String adminUserNameInSn = "adminUserNameInSn";
       
	
	@Autowired
    private AllStateFormsPaymentInfoSavingDAOImpl allStateFormsPaymentSavingDAOImpl;
	
	@Autowired
    private AllFederalFormsPaymentInfoSavingDAOImpl allFederalFormsPaymentSavingDAOImpl;
	
	@Autowired
    private AdminHomeServiceImpl adminHomeServiceImpl;
	
//	Reports Home Page Service Implementation
	
	@Override
	@Transactional
	public ModelAndView reportsHomeRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("reportsHomeRedirection...Service");		
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute(adminUserNameInSn) != null){
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	    String todayDate = formatter.format(new Date());
	    
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -7);
	    String weeklyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -30);
	    String monthlyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -90);
	    String quarterlyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -365);
	    String yearlyDate = formatter.format(cal.getTime());
	    
	    List<AllStateFormsPaymentInfoSaving> stateFormsDailyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentDailyReports(todayDate);
	    List<AllStateFormsPaymentInfoSaving> stateFormsWeeklyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentInfoReports(todayDate, weeklyDate);
	    List<AllStateFormsPaymentInfoSaving> stateFormsMonthlyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentInfoReports(todayDate, monthlyDate);
	    List<AllStateFormsPaymentInfoSaving> stateFormsQuarterlyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentInfoReports(todayDate, quarterlyDate);
	    List<AllStateFormsPaymentInfoSaving> stateFormsYearlyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentInfoReports(todayDate, yearlyDate);
	    
	    List<AllFederalFormsPaymentInfoSaving> federalFormsDailyReportsList = allFederalFormsPaymentSavingDAOImpl.allFederalFormsPaymentDailyReports(todayDate);
	    List<AllFederalFormsPaymentInfoSaving> federalFormsWeeklyReportsList = allFederalFormsPaymentSavingDAOImpl.allFederalFormsPaymentInfoReports(todayDate, weeklyDate);
	    List<AllFederalFormsPaymentInfoSaving> federalFormsMonthlyReportsList = allFederalFormsPaymentSavingDAOImpl.allFederalFormsPaymentInfoReports(todayDate, monthlyDate);
	    List<AllFederalFormsPaymentInfoSaving> federalFormsQuarterlyReportsList = allFederalFormsPaymentSavingDAOImpl.allFederalFormsPaymentInfoReports(todayDate, quarterlyDate);
	    List<AllFederalFormsPaymentInfoSaving> federalFormsYearlyReportsList = allFederalFormsPaymentSavingDAOImpl.allFederalFormsPaymentInfoReports(todayDate, yearlyDate);
	    	
	    	mav = new ModelAndView("reportsHomeRedirection");
            mav.addObject(dailyStateReportsCount, stateFormsDailyReportsList.size());
            mav.addObject(weeklyStateReportsCount, stateFormsWeeklyReportsList.size());
            mav.addObject(monthlyStateReportsCount, stateFormsMonthlyReportsList.size());
            mav.addObject(quarterlyStateReportsCount, stateFormsQuarterlyReportsList.size());
            mav.addObject(yearlyStateReportsCount, stateFormsYearlyReportsList.size());
            
            mav.addObject(dailyFederalReportsCount, federalFormsDailyReportsList.size());
            mav.addObject(weeklyFederalReportsCount, federalFormsWeeklyReportsList.size());
            mav.addObject(monthlyFederalReportsCount, federalFormsMonthlyReportsList.size());
            mav.addObject(quarterlyFederalReportsCount, federalFormsQuarterlyReportsList.size());
            mav.addObject(yearlyFederalReportsCount, federalFormsYearlyReportsList.size());
            
            mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		}
        return mav;
    }
	
//	All Forms Reports Service Implementation
	
	@Override
	@Transactional
	public ModelAndView allFormsReportsRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("allFormsReportsRedirection...Service");		
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute(adminUserNameInSn) != null){
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	    String todayDate = formatter.format(new Date());
	    
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -7);
	    String weeklyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -30);
	    String monthlyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -90);
	    String quarterlyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -365);
	    String yearlyDate = formatter.format(cal.getTime());
	    
	    List<AllStateFormsPaymentInfoSaving> stateFormsDailyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentDailyReports(todayDate);
	    List<AllStateFormsPaymentInfoSaving> stateFormsWeeklyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentInfoReports(todayDate, weeklyDate);
	    List<AllStateFormsPaymentInfoSaving> stateFormsMonthlyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentInfoReports(todayDate, monthlyDate);
	    List<AllStateFormsPaymentInfoSaving> stateFormsQuarterlyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentInfoReports(todayDate, quarterlyDate);
	    List<AllStateFormsPaymentInfoSaving> stateFormsYearlyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentInfoReports(todayDate, yearlyDate);
	    
	    if (!stateFormsDailyReportsList.isEmpty()) {
	    	mav = dailyStateReportsRedirection(req, sn);
	    } else if (!stateFormsWeeklyReportsList.isEmpty()) {
	    	mav = weeklyStateReportsRedirection(req, sn);
	    } else if (!stateFormsMonthlyReportsList.isEmpty()) {
	    	mav = monthlyStateReportsRedirection(req, sn);
	    } else if (!stateFormsQuarterlyReportsList.isEmpty()) {
	    	mav = quarterlyStateReportsRedirection(req, sn);
	    } else if (!stateFormsYearlyReportsList.isEmpty()) {
	    	mav = yearlyStateReportsRedirection(req, sn);
	    } else{
	    	mav = allFederalFormsReportsRedirection(req, sn);
        }         
		}
        return mav;
    }
	
//	All State Forms Reports Service Implementation
	
	@Override
	@Transactional
	public ModelAndView allStateFormsReportsRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("allStateFormsReportsRedirection...Service");		
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute(adminUserNameInSn) != null){
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	    String todayDate = formatter.format(new Date());
	    
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -7);
	    String weeklyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -30);
	    String monthlyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -90);
	    String quarterlyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -365);
	    String yearlyDate = formatter.format(cal.getTime());
	    
	    List<AllStateFormsPaymentInfoSaving> stateFormsDailyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentDailyReports(todayDate);
	    List<AllStateFormsPaymentInfoSaving> stateFormsWeeklyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentInfoReports(todayDate, weeklyDate);
	    List<AllStateFormsPaymentInfoSaving> stateFormsMonthlyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentInfoReports(todayDate, monthlyDate);
	    List<AllStateFormsPaymentInfoSaving> stateFormsQuarterlyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentInfoReports(todayDate, quarterlyDate);
	    List<AllStateFormsPaymentInfoSaving> stateFormsYearlyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentInfoReports(todayDate, yearlyDate);
	    
	    if (!stateFormsDailyReportsList.isEmpty()) {
	    	mav = dailyStateReportsRedirection(req, sn);
	    } else if (!stateFormsWeeklyReportsList.isEmpty()) {
	    	mav = weeklyStateReportsRedirection(req, sn);
	    } else if (!stateFormsMonthlyReportsList.isEmpty()) {
	    	mav = monthlyStateReportsRedirection(req, sn);
	    } else if (!stateFormsQuarterlyReportsList.isEmpty()) {
	    	mav = quarterlyStateReportsRedirection(req, sn);
	    } else if (!stateFormsYearlyReportsList.isEmpty()) {
	    	mav = yearlyStateReportsRedirection(req, sn);
	    } else{
	    	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);
        }  
	    
            mav.addObject(dailyStateReportsCount, stateFormsDailyReportsList.size());
            mav.addObject(weeklyStateReportsCount, stateFormsWeeklyReportsList.size());
            mav.addObject(monthlyStateReportsCount, stateFormsMonthlyReportsList.size());
            mav.addObject(quarterlyStateReportsCount, stateFormsQuarterlyReportsList.size());
            mav.addObject(yearlyStateReportsCount, stateFormsYearlyReportsList.size());
            mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		}
        return mav;
    }	
	
//	All Federal Forms Reports Service Implementation	
	
	@Override
	@Transactional
	public ModelAndView allFederalFormsReportsRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("allFederalFormsReportsRedirection...Service");		
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute(adminUserNameInSn) != null){
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	    String todayDate = formatter.format(new Date());
	    
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -7);
	    String weeklyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -30);
	    String monthlyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -90);
	    String quarterlyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -365);
	    String yearlyDate = formatter.format(cal.getTime());
	    	    
	    List<AllFederalFormsPaymentInfoSaving> federalFormsDailyReportsList = allFederalFormsPaymentSavingDAOImpl.allFederalFormsPaymentDailyReports(todayDate);
	    List<AllFederalFormsPaymentInfoSaving> federalFormsWeeklyReportsList = allFederalFormsPaymentSavingDAOImpl.allFederalFormsPaymentInfoReports(todayDate, weeklyDate);
	    List<AllFederalFormsPaymentInfoSaving> federalFormsMonthlyReportsList = allFederalFormsPaymentSavingDAOImpl.allFederalFormsPaymentInfoReports(todayDate, monthlyDate);
	    List<AllFederalFormsPaymentInfoSaving> federalFormsQuarterlyReportsList = allFederalFormsPaymentSavingDAOImpl.allFederalFormsPaymentInfoReports(todayDate, quarterlyDate);
	    List<AllFederalFormsPaymentInfoSaving> federalFormsYearlyReportsList = allFederalFormsPaymentSavingDAOImpl.allFederalFormsPaymentInfoReports(todayDate, yearlyDate);
	        
	    	if (!federalFormsDailyReportsList.isEmpty()) {
		    	mav = dailyFederalReportsRedirection(req, sn);
		    } else if (!federalFormsWeeklyReportsList.isEmpty()) {
		    	mav = weeklyFederalReportsRedirection(req, sn);
		    } else if (!federalFormsMonthlyReportsList.isEmpty()) {
		    	mav = monthlyFederalReportsRedirection(req, sn);
		    } else if (!federalFormsQuarterlyReportsList.isEmpty()) {
		    	mav = quarterlyFederalReportsRedirection(req, sn);
		    } else if (!federalFormsYearlyReportsList.isEmpty()) {
		    	mav = yearlyFederalReportsRedirection(req, sn);
		    } else {
        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);
		    }
            mav.addObject(dailyStateReportsCount, federalFormsDailyReportsList.size());
            mav.addObject(weeklyStateReportsCount, federalFormsWeeklyReportsList.size());
            mav.addObject(monthlyStateReportsCount, federalFormsMonthlyReportsList.size());
            mav.addObject(quarterlyStateReportsCount, federalFormsQuarterlyReportsList.size());
            mav.addObject(yearlyStateReportsCount, federalFormsYearlyReportsList.size());
            mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		}
        return mav;
    }
	
//	State Reports
	
//	Daily Reports Service Implementation
	
	@Override
	@Transactional
	public ModelAndView dailyStateReportsRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("dailyStateReportsRedirection...Service");		
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute(adminUserNameInSn) != null){
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	    String todayDate = formatter.format(new Date());
	    
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -7);
	    String weeklyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -30);
	    String monthlyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -90);
	    String quarterlyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -365);
	    String yearlyDate = formatter.format(cal.getTime());
	    
	    List<AllStateFormsPaymentInfoSaving> stateFormsDailyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentDailyReports(todayDate);
	    List<AllStateFormsPaymentInfoSaving> stateFormsWeeklyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentInfoReports(todayDate, weeklyDate);
	    List<AllStateFormsPaymentInfoSaving> stateFormsMonthlyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentInfoReports(todayDate, monthlyDate);
	    List<AllStateFormsPaymentInfoSaving> stateFormsQuarterlyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentInfoReports(todayDate, quarterlyDate);
	    List<AllStateFormsPaymentInfoSaving> stateFormsYearlyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentInfoReports(todayDate, yearlyDate);
	    	    
	    if (!stateFormsDailyReportsList.isEmpty()) {
            List<AllStateFormsPaymentInfoSaving> stateFormsPaymentSavingInfoList = new ArrayList<AllStateFormsPaymentInfoSaving>();
            int count = 1;
            for (AllStateFormsPaymentInfoSaving stateFormsPaymentInfo : stateFormsDailyReportsList) {
            	AllStateFormsPaymentInfoSaving stateFormsPaymentInfoModel = new AllStateFormsPaymentInfoSaving();
            	stateFormsPaymentInfoModel.setUserName(stateFormsPaymentInfo.getUserName());
            	stateFormsPaymentInfoModel.setStateName(stateFormsPaymentInfo.getStateName());
            	stateFormsPaymentInfoModel.setFormName(stateFormsPaymentInfo.getFormName());
            	stateFormsPaymentInfoModel.setUserChoice(stateFormsPaymentInfo.getUserChoice());
            	stateFormsPaymentInfoModel.setTypeOfDocument(stateFormsPaymentInfo.getTypeOfDocument());
            	stateFormsPaymentInfoModel.setProcessingFee(stateFormsPaymentInfo.getProcessingFee()); 
            	stateFormsPaymentInfoModel.setStandardFilingFee(stateFormsPaymentInfo.getStandardFilingFee()); 
            	stateFormsPaymentInfoModel.setRegisteredAgentPrice(stateFormsPaymentInfo.getRegisteredAgentPrice()); 
            	stateFormsPaymentInfoModel.setTotalFee(stateFormsPaymentInfo.getTotalFee());
            	stateFormsPaymentInfoModel.setAllStateFormsPaymentInfoSavingId(count);
            	stateFormsPaymentInfoModel.setCreatedDate(stateFormsPaymentInfo.getCreatedDate());

            	stateFormsPaymentSavingInfoList.add(stateFormsPaymentInfoModel);
            	count++;            	
            }	    
	    
	    mav = new ModelAndView("dailyStateReports");
        mav.addObject("dailyStateReportsInfoList", stateFormsPaymentSavingInfoList);
        
        mav.addObject(dailyStateReportsCount, stateFormsDailyReportsList.size());
        mav.addObject(weeklyStateReportsCount, stateFormsWeeklyReportsList.size());
        mav.addObject(monthlyStateReportsCount, stateFormsMonthlyReportsList.size());
        mav.addObject(quarterlyStateReportsCount, stateFormsQuarterlyReportsList.size());
        mav.addObject(yearlyStateReportsCount, stateFormsYearlyReportsList.size());        
	    } else{
        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);
        }
	    mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		}
        return mav;
    }
	
//	Weekly Reports Service Implementation
	
	@Override
	@Transactional
	public ModelAndView weeklyStateReportsRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("weeklyStateReportsRedirection...Service");		
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute(adminUserNameInSn) != null){
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	    String todayDate = formatter.format(new Date());
	    
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -7);
	    String weeklyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -30);
	    String monthlyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -90);
	    String quarterlyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -365);
	    String yearlyDate = formatter.format(cal.getTime());
	    
	    List<AllStateFormsPaymentInfoSaving> stateFormsDailyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentDailyReports(todayDate);
	    List<AllStateFormsPaymentInfoSaving> stateFormsWeeklyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentInfoReports(todayDate, weeklyDate);
	    List<AllStateFormsPaymentInfoSaving> stateFormsMonthlyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentInfoReports(todayDate, monthlyDate);
	    List<AllStateFormsPaymentInfoSaving> stateFormsQuarterlyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentInfoReports(todayDate, quarterlyDate);
	    List<AllStateFormsPaymentInfoSaving> stateFormsYearlyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentInfoReports(todayDate, yearlyDate);
	    	
	    if (!stateFormsWeeklyReportsList.isEmpty()) {
            List<AllStateFormsPaymentInfoSaving> stateFormsPaymentSavingInfoList = new ArrayList<AllStateFormsPaymentInfoSaving>();            
            int count = 1;
            for (AllStateFormsPaymentInfoSaving stateFormsPaymentInfo : stateFormsWeeklyReportsList) {
            	AllStateFormsPaymentInfoSaving stateFormsPaymentInfoModel = new AllStateFormsPaymentInfoSaving();
            	stateFormsPaymentInfoModel.setUserName(stateFormsPaymentInfo.getUserName());
            	stateFormsPaymentInfoModel.setStateName(stateFormsPaymentInfo.getStateName());
            	stateFormsPaymentInfoModel.setFormName(stateFormsPaymentInfo.getFormName());
            	stateFormsPaymentInfoModel.setUserChoice(stateFormsPaymentInfo.getUserChoice());
            	stateFormsPaymentInfoModel.setTypeOfDocument(stateFormsPaymentInfo.getTypeOfDocument());
            	stateFormsPaymentInfoModel.setProcessingFee(stateFormsPaymentInfo.getProcessingFee()); 
            	stateFormsPaymentInfoModel.setStandardFilingFee(stateFormsPaymentInfo.getStandardFilingFee()); 
            	stateFormsPaymentInfoModel.setRegisteredAgentPrice(stateFormsPaymentInfo.getRegisteredAgentPrice()); 
            	stateFormsPaymentInfoModel.setTotalFee(stateFormsPaymentInfo.getTotalFee());
            	stateFormsPaymentInfoModel.setAllStateFormsPaymentInfoSavingId(count);
            	stateFormsPaymentInfoModel.setCreatedDate(stateFormsPaymentInfo.getCreatedDate());

            	stateFormsPaymentSavingInfoList.add(stateFormsPaymentInfoModel);
            	count++;            	
            }	    
	    
	    mav = new ModelAndView("weeklyStateReports");
        mav.addObject("weeklyStateReportsInfoList", stateFormsPaymentSavingInfoList);
        
        mav.addObject(dailyStateReportsCount, stateFormsDailyReportsList.size());
        mav.addObject(weeklyStateReportsCount, stateFormsWeeklyReportsList.size());
        mav.addObject(monthlyStateReportsCount, stateFormsMonthlyReportsList.size());
        mav.addObject(quarterlyStateReportsCount, stateFormsQuarterlyReportsList.size());
        mav.addObject(yearlyStateReportsCount, stateFormsYearlyReportsList.size()); 
	    } else{
        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);
        }
	    mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		}
        return mav;
    }
	
//	Monthly Reports Service Implementation
	
	@Override
	@Transactional
	public ModelAndView monthlyStateReportsRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("monthlyStateReportsRedirection...Service");		
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute(adminUserNameInSn) != null){
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	    String todayDate = formatter.format(new Date());
	    
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -7);
	    String weeklyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -30);
	    String monthlyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -90);
	    String quarterlyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -365);
	    String yearlyDate = formatter.format(cal.getTime());
	    
	    List<AllStateFormsPaymentInfoSaving> stateFormsDailyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentDailyReports(todayDate);
	    List<AllStateFormsPaymentInfoSaving> stateFormsWeeklyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentInfoReports(todayDate, weeklyDate);
	    List<AllStateFormsPaymentInfoSaving> stateFormsMonthlyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentInfoReports(todayDate, monthlyDate);
	    List<AllStateFormsPaymentInfoSaving> stateFormsQuarterlyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentInfoReports(todayDate, quarterlyDate);
	    List<AllStateFormsPaymentInfoSaving> stateFormsYearlyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentInfoReports(todayDate, yearlyDate);
	    
	    if (!stateFormsMonthlyReportsList.isEmpty()) {
            List<AllStateFormsPaymentInfoSaving> stateFormsPaymentSavingInfoList = new ArrayList<AllStateFormsPaymentInfoSaving>();
            int count = 1;
            for (AllStateFormsPaymentInfoSaving stateFormsPaymentInfo : stateFormsMonthlyReportsList) {
            	AllStateFormsPaymentInfoSaving stateFormsPaymentInfoModel = new AllStateFormsPaymentInfoSaving();
            	stateFormsPaymentInfoModel.setUserName(stateFormsPaymentInfo.getUserName());
            	stateFormsPaymentInfoModel.setStateName(stateFormsPaymentInfo.getStateName());
            	stateFormsPaymentInfoModel.setFormName(stateFormsPaymentInfo.getFormName());
            	stateFormsPaymentInfoModel.setUserChoice(stateFormsPaymentInfo.getUserChoice());
            	stateFormsPaymentInfoModel.setTypeOfDocument(stateFormsPaymentInfo.getTypeOfDocument());
            	stateFormsPaymentInfoModel.setProcessingFee(stateFormsPaymentInfo.getProcessingFee()); 
            	stateFormsPaymentInfoModel.setStandardFilingFee(stateFormsPaymentInfo.getStandardFilingFee()); 
            	stateFormsPaymentInfoModel.setRegisteredAgentPrice(stateFormsPaymentInfo.getRegisteredAgentPrice()); 
            	stateFormsPaymentInfoModel.setTotalFee(stateFormsPaymentInfo.getTotalFee());
            	stateFormsPaymentInfoModel.setAllStateFormsPaymentInfoSavingId(count);
            	stateFormsPaymentInfoModel.setCreatedDate(stateFormsPaymentInfo.getCreatedDate());

            	stateFormsPaymentSavingInfoList.add(stateFormsPaymentInfoModel);
            	count++;            	
            }	    
	    
	    mav = new ModelAndView("monthlyStateReports");
        mav.addObject("monthlyStateReportsInfoList", stateFormsPaymentSavingInfoList);
        
        mav.addObject(dailyStateReportsCount, stateFormsDailyReportsList.size());
        mav.addObject(weeklyStateReportsCount, stateFormsWeeklyReportsList.size());
        mav.addObject(monthlyStateReportsCount, stateFormsMonthlyReportsList.size());
        mav.addObject(quarterlyStateReportsCount, stateFormsQuarterlyReportsList.size());
        mav.addObject(yearlyStateReportsCount, stateFormsYearlyReportsList.size());
	    } else{
        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);
        }
	    mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		}
        return mav;
    }
	
//	Quarterly Reports Service Implementation
	
	@Override
	@Transactional
	public ModelAndView quarterlyStateReportsRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("quarterlyStateReportsRedirection...Service");		
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute(adminUserNameInSn) != null){
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	    String todayDate = formatter.format(new Date());
	    
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -7);
	    String weeklyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -30);
	    String monthlyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -90);
	    String quarterlyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -365);
	    String yearlyDate = formatter.format(cal.getTime());
	    
	    List<AllStateFormsPaymentInfoSaving> stateFormsDailyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentDailyReports(todayDate);
	    List<AllStateFormsPaymentInfoSaving> stateFormsWeeklyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentInfoReports(todayDate, weeklyDate);
	    List<AllStateFormsPaymentInfoSaving> stateFormsMonthlyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentInfoReports(todayDate, monthlyDate);
	    List<AllStateFormsPaymentInfoSaving> stateFormsQuarterlyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentInfoReports(todayDate, quarterlyDate);
	    List<AllStateFormsPaymentInfoSaving> stateFormsYearlyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentInfoReports(todayDate, yearlyDate);
	    
	    if (!stateFormsQuarterlyReportsList.isEmpty()) {
            List<AllStateFormsPaymentInfoSaving> stateFormsPaymentSavingInfoList = new ArrayList<AllStateFormsPaymentInfoSaving>();
            int count = 1;
            for (AllStateFormsPaymentInfoSaving stateFormsPaymentInfo : stateFormsQuarterlyReportsList) {
            	AllStateFormsPaymentInfoSaving stateFormsPaymentInfoModel = new AllStateFormsPaymentInfoSaving();
            	stateFormsPaymentInfoModel.setUserName(stateFormsPaymentInfo.getUserName());
            	stateFormsPaymentInfoModel.setStateName(stateFormsPaymentInfo.getStateName());
            	stateFormsPaymentInfoModel.setFormName(stateFormsPaymentInfo.getFormName());
            	stateFormsPaymentInfoModel.setUserChoice(stateFormsPaymentInfo.getUserChoice());
            	stateFormsPaymentInfoModel.setTypeOfDocument(stateFormsPaymentInfo.getTypeOfDocument());
            	stateFormsPaymentInfoModel.setProcessingFee(stateFormsPaymentInfo.getProcessingFee()); 
            	stateFormsPaymentInfoModel.setStandardFilingFee(stateFormsPaymentInfo.getStandardFilingFee()); 
            	stateFormsPaymentInfoModel.setRegisteredAgentPrice(stateFormsPaymentInfo.getRegisteredAgentPrice()); 
            	stateFormsPaymentInfoModel.setTotalFee(stateFormsPaymentInfo.getTotalFee());
            	stateFormsPaymentInfoModel.setAllStateFormsPaymentInfoSavingId(count);
            	stateFormsPaymentInfoModel.setCreatedDate(stateFormsPaymentInfo.getCreatedDate());

            	stateFormsPaymentSavingInfoList.add(stateFormsPaymentInfoModel);
            	count++;            	
            }	    
	    
	    mav = new ModelAndView("quarterlyStateReports");
        mav.addObject("quarterlyStateReportsInfoList", stateFormsPaymentSavingInfoList);
        
        mav.addObject(dailyStateReportsCount, stateFormsDailyReportsList.size());
        mav.addObject(weeklyStateReportsCount, stateFormsWeeklyReportsList.size());
        mav.addObject(monthlyStateReportsCount, stateFormsMonthlyReportsList.size());
        mav.addObject(quarterlyStateReportsCount, stateFormsQuarterlyReportsList.size());
        mav.addObject(yearlyStateReportsCount, stateFormsYearlyReportsList.size());
	    } else{
        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);
        }
	    mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		}
        return mav;
    }
	
//	Yearly Reports Service Implementation
	
	@Override
	@Transactional
	public ModelAndView yearlyStateReportsRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("yearlyStateReportsRedirection...Service");		
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute(adminUserNameInSn) != null){
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	    String todayDate = formatter.format(new Date());
	    
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -7);
	    String weeklyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -30);
	    String monthlyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -90);
	    String quarterlyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -365);
	    String yearlyDate = formatter.format(cal.getTime());
	    
	    List<AllStateFormsPaymentInfoSaving> stateFormsDailyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentDailyReports(todayDate);
	    List<AllStateFormsPaymentInfoSaving> stateFormsWeeklyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentInfoReports(todayDate, weeklyDate);
	    List<AllStateFormsPaymentInfoSaving> stateFormsMonthlyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentInfoReports(todayDate, monthlyDate);
	    List<AllStateFormsPaymentInfoSaving> stateFormsQuarterlyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentInfoReports(todayDate, quarterlyDate);
	    List<AllStateFormsPaymentInfoSaving> stateFormsYearlyReportsList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentInfoReports(todayDate, yearlyDate);
	    
	    if (!stateFormsYearlyReportsList.isEmpty()) {
            List<AllStateFormsPaymentInfoSaving> stateFormsPaymentSavingInfoList = new ArrayList<AllStateFormsPaymentInfoSaving>();
            int count = 1;
            for (AllStateFormsPaymentInfoSaving stateFormsPaymentInfo : stateFormsYearlyReportsList) {
            	AllStateFormsPaymentInfoSaving stateFormsPaymentInfoModel = new AllStateFormsPaymentInfoSaving();
            	stateFormsPaymentInfoModel.setUserName(stateFormsPaymentInfo.getUserName());
            	stateFormsPaymentInfoModel.setStateName(stateFormsPaymentInfo.getStateName());
            	stateFormsPaymentInfoModel.setFormName(stateFormsPaymentInfo.getFormName());
            	stateFormsPaymentInfoModel.setUserChoice(stateFormsPaymentInfo.getUserChoice());
            	stateFormsPaymentInfoModel.setTypeOfDocument(stateFormsPaymentInfo.getTypeOfDocument());
            	stateFormsPaymentInfoModel.setProcessingFee(stateFormsPaymentInfo.getProcessingFee()); 
            	stateFormsPaymentInfoModel.setStandardFilingFee(stateFormsPaymentInfo.getStandardFilingFee()); 
            	stateFormsPaymentInfoModel.setRegisteredAgentPrice(stateFormsPaymentInfo.getRegisteredAgentPrice()); 
            	stateFormsPaymentInfoModel.setTotalFee(stateFormsPaymentInfo.getTotalFee());
            	stateFormsPaymentInfoModel.setAllStateFormsPaymentInfoSavingId(count);
            	stateFormsPaymentInfoModel.setCreatedDate(stateFormsPaymentInfo.getCreatedDate());

            	stateFormsPaymentSavingInfoList.add(stateFormsPaymentInfoModel);
            	count++;            	
            }	    
	    
	    mav = new ModelAndView("yearlyStateReports");
        mav.addObject("yearlyStateReportsInfoList", stateFormsPaymentSavingInfoList);
        
        mav.addObject(dailyStateReportsCount, stateFormsDailyReportsList.size());
        mav.addObject(weeklyStateReportsCount, stateFormsWeeklyReportsList.size());
        mav.addObject(monthlyStateReportsCount, stateFormsMonthlyReportsList.size());
        mav.addObject(quarterlyStateReportsCount, stateFormsQuarterlyReportsList.size());
        mav.addObject(yearlyStateReportsCount, stateFormsYearlyReportsList.size()); 
	    } else{
        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);
        }
	    mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		}
        return mav;
    }
	
	
//Federal Reports
	
//	Daily Federal Reports Service Implementation
	
	@Override
	@Transactional
	public ModelAndView dailyFederalReportsRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("dailyFederalReportsRedirection...Service");		
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute(adminUserNameInSn) != null){
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	    String todayDate = formatter.format(new Date());
	    
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -7);
	    String weeklyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -30);
	    String monthlyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -90);
	    String quarterlyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -365);
	    String yearlyDate = formatter.format(cal.getTime());
	    
	    List<AllFederalFormsPaymentInfoSaving> federalFormsDailyReportsList = allFederalFormsPaymentSavingDAOImpl.allFederalFormsPaymentDailyReports(todayDate);
	    List<AllFederalFormsPaymentInfoSaving> federalFormsWeeklyReportsList = allFederalFormsPaymentSavingDAOImpl.allFederalFormsPaymentInfoReports(todayDate, weeklyDate);
	    List<AllFederalFormsPaymentInfoSaving> federalFormsMonthlyReportsList = allFederalFormsPaymentSavingDAOImpl.allFederalFormsPaymentInfoReports(todayDate, monthlyDate);
	    List<AllFederalFormsPaymentInfoSaving> federalFormsQuarterlyReportsList = allFederalFormsPaymentSavingDAOImpl.allFederalFormsPaymentInfoReports(todayDate, quarterlyDate);
	    List<AllFederalFormsPaymentInfoSaving> federalFormsYearlyReportsList = allFederalFormsPaymentSavingDAOImpl.allFederalFormsPaymentInfoReports(todayDate, yearlyDate);
	    	    
	    if (!federalFormsDailyReportsList.isEmpty()) {
            List<AllFederalFormsPaymentInfoSaving> federalFormsPaymentSavingInfoList = new ArrayList<AllFederalFormsPaymentInfoSaving>();
            int count = 1;
            for (AllFederalFormsPaymentInfoSaving federalFormsPaymentInfo : federalFormsDailyReportsList) {
            	AllFederalFormsPaymentInfoSaving federalFormsPaymentInfoModel = new AllFederalFormsPaymentInfoSaving();
            	federalFormsPaymentInfoModel.setUserName(federalFormsPaymentInfo.getUserName());
            	federalFormsPaymentInfoModel.setLegalName(federalFormsPaymentInfo.getLegalName());
            	federalFormsPaymentInfoModel.setTypeOfDocument(federalFormsPaymentInfo.getTypeOfDocument());
            	federalFormsPaymentInfoModel.setProcessingFee(federalFormsPaymentInfo.getProcessingFee()); 
            	federalFormsPaymentInfoModel.setTotalFee(federalFormsPaymentInfo.getTotalFee());
            	federalFormsPaymentInfoModel.setAllFederalFormsPaymentInfoSavingId(count);
            	federalFormsPaymentInfoModel.setCreatedDate(federalFormsPaymentInfo.getCreatedDate());

            	federalFormsPaymentSavingInfoList.add(federalFormsPaymentInfoModel);
            	count++;            	
            }	    
	    
	    mav = new ModelAndView("dailyFederalReports");
        mav.addObject("dailyFederalReportsInfoList", federalFormsPaymentSavingInfoList);
        
        mav.addObject(dailyFederalReportsCount, federalFormsDailyReportsList.size());
        mav.addObject(weeklyFederalReportsCount, federalFormsWeeklyReportsList.size());
        mav.addObject(monthlyFederalReportsCount, federalFormsMonthlyReportsList.size());
        mav.addObject(quarterlyFederalReportsCount, federalFormsQuarterlyReportsList.size());
        mav.addObject(yearlyFederalReportsCount, federalFormsYearlyReportsList.size());        
	    } else{
        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);
        }
	    mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		}
        return mav;
    }
	
//	Weekly Federal Reports Service Implementation
	
	@Override
	@Transactional
	public ModelAndView weeklyFederalReportsRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("weeklyFederalReportsRedirection...Service");
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute(adminUserNameInSn) != null){
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	    String todayDate = formatter.format(new Date());
	    
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -7);
	    String weeklyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -30);
	    String monthlyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -90);
	    String quarterlyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -365);
	    String yearlyDate = formatter.format(cal.getTime());
	    
	    List<AllFederalFormsPaymentInfoSaving> federalFormsDailyReportsList = allFederalFormsPaymentSavingDAOImpl.allFederalFormsPaymentDailyReports(todayDate);
	    List<AllFederalFormsPaymentInfoSaving> federalFormsWeeklyReportsList = allFederalFormsPaymentSavingDAOImpl.allFederalFormsPaymentInfoReports(todayDate, weeklyDate);
	    List<AllFederalFormsPaymentInfoSaving> federalFormsMonthlyReportsList = allFederalFormsPaymentSavingDAOImpl.allFederalFormsPaymentInfoReports(todayDate, monthlyDate);
	    List<AllFederalFormsPaymentInfoSaving> federalFormsQuarterlyReportsList = allFederalFormsPaymentSavingDAOImpl.allFederalFormsPaymentInfoReports(todayDate, quarterlyDate);
	    List<AllFederalFormsPaymentInfoSaving> federalFormsYearlyReportsList = allFederalFormsPaymentSavingDAOImpl.allFederalFormsPaymentInfoReports(todayDate, yearlyDate);
	    	
	    if (!federalFormsWeeklyReportsList.isEmpty()) {
            List<AllFederalFormsPaymentInfoSaving> federalFormsPaymentSavingInfoList = new ArrayList<AllFederalFormsPaymentInfoSaving>();            
            int count = 1;
            for (AllFederalFormsPaymentInfoSaving federalFormsPaymentInfo : federalFormsWeeklyReportsList) {
            	AllFederalFormsPaymentInfoSaving federalFormsPaymentInfoModel = new AllFederalFormsPaymentInfoSaving();
            	federalFormsPaymentInfoModel.setUserName(federalFormsPaymentInfo.getUserName());
            	federalFormsPaymentInfoModel.setLegalName(federalFormsPaymentInfo.getLegalName());
            	federalFormsPaymentInfoModel.setTypeOfDocument(federalFormsPaymentInfo.getTypeOfDocument());
            	federalFormsPaymentInfoModel.setProcessingFee(federalFormsPaymentInfo.getProcessingFee()); 
            	federalFormsPaymentInfoModel.setTotalFee(federalFormsPaymentInfo.getTotalFee());
            	federalFormsPaymentInfoModel.setAllFederalFormsPaymentInfoSavingId(count);
            	federalFormsPaymentInfoModel.setCreatedDate(federalFormsPaymentInfo.getCreatedDate());

            	federalFormsPaymentSavingInfoList.add(federalFormsPaymentInfoModel);
            	count++;            	
            }           
	    mav = new ModelAndView("weeklyFederalReports");
        mav.addObject("weeklyFederalReportsInfoList", federalFormsPaymentSavingInfoList);
        
        mav.addObject(dailyFederalReportsCount, federalFormsDailyReportsList.size());
        mav.addObject(weeklyFederalReportsCount, federalFormsWeeklyReportsList.size());
        mav.addObject(monthlyFederalReportsCount, federalFormsMonthlyReportsList.size());
        mav.addObject(quarterlyFederalReportsCount, federalFormsQuarterlyReportsList.size());
        mav.addObject(yearlyFederalReportsCount, federalFormsYearlyReportsList.size()); 
	    } else{
        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);
        }
	    mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		}
        return mav;
    }
	
//	Monthly Federal Reports Service Implementation
	
	@Override
	@Transactional
	public ModelAndView monthlyFederalReportsRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("monthlyFederalReportsRedirection...Service");		
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute(adminUserNameInSn) != null){
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	    String todayDate = formatter.format(new Date());
	    
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -7);
	    String weeklyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -30);
	    String monthlyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -90);
	    String quarterlyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -365);
	    String yearlyDate = formatter.format(cal.getTime());
	    
	    List<AllFederalFormsPaymentInfoSaving> federalFormsDailyReportsList = allFederalFormsPaymentSavingDAOImpl.allFederalFormsPaymentDailyReports(todayDate);
	    List<AllFederalFormsPaymentInfoSaving> federalFormsWeeklyReportsList = allFederalFormsPaymentSavingDAOImpl.allFederalFormsPaymentInfoReports(todayDate, weeklyDate);
	    List<AllFederalFormsPaymentInfoSaving> federalFormsMonthlyReportsList = allFederalFormsPaymentSavingDAOImpl.allFederalFormsPaymentInfoReports(todayDate, monthlyDate);
	    List<AllFederalFormsPaymentInfoSaving> federalFormsQuarterlyReportsList = allFederalFormsPaymentSavingDAOImpl.allFederalFormsPaymentInfoReports(todayDate, quarterlyDate);
	    List<AllFederalFormsPaymentInfoSaving> federalFormsYearlyReportsList = allFederalFormsPaymentSavingDAOImpl.allFederalFormsPaymentInfoReports(todayDate, yearlyDate);
	    
	    if (!federalFormsMonthlyReportsList.isEmpty()) {
            List<AllFederalFormsPaymentInfoSaving> federalFormsPaymentSavingInfoList = new ArrayList<AllFederalFormsPaymentInfoSaving>();
            int count = 1;
            for (AllFederalFormsPaymentInfoSaving federalFormsPaymentInfo : federalFormsMonthlyReportsList) {
            	AllFederalFormsPaymentInfoSaving federalFormsPaymentInfoModel = new AllFederalFormsPaymentInfoSaving();
            	federalFormsPaymentInfoModel.setUserName(federalFormsPaymentInfo.getUserName());
            	federalFormsPaymentInfoModel.setLegalName(federalFormsPaymentInfo.getLegalName());
            	federalFormsPaymentInfoModel.setTypeOfDocument(federalFormsPaymentInfo.getTypeOfDocument());
            	federalFormsPaymentInfoModel.setProcessingFee(federalFormsPaymentInfo.getProcessingFee()); 
            	federalFormsPaymentInfoModel.setTotalFee(federalFormsPaymentInfo.getTotalFee());
            	federalFormsPaymentInfoModel.setAllFederalFormsPaymentInfoSavingId(count);
            	federalFormsPaymentInfoModel.setCreatedDate(federalFormsPaymentInfo.getCreatedDate());

            	federalFormsPaymentSavingInfoList.add(federalFormsPaymentInfoModel);
            	count++;            	
            }	    
	    
	    mav = new ModelAndView("monthlyFederalReports");
        mav.addObject("monthlyFederalReportsInfoList", federalFormsPaymentSavingInfoList);
        
        mav.addObject(dailyFederalReportsCount, federalFormsDailyReportsList.size());
        mav.addObject(weeklyFederalReportsCount, federalFormsWeeklyReportsList.size());
        mav.addObject(monthlyFederalReportsCount, federalFormsMonthlyReportsList.size());
        mav.addObject(quarterlyFederalReportsCount, federalFormsQuarterlyReportsList.size());
        mav.addObject(yearlyFederalReportsCount, federalFormsYearlyReportsList.size());
	    } else{
        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);
        }
	    mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		}
        return mav;
    }
	
//	Quarterly Federal Reports Service Implementation
	
	@Override
	@Transactional
	public ModelAndView quarterlyFederalReportsRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("quarterlyFederalReportsRedirection...Service");		
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute(adminUserNameInSn) != null){
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	    String todayDate = formatter.format(new Date());
	    
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -7);
	    String weeklyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -30);
	    String monthlyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -90);
	    String quarterlyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -365);
	    String yearlyDate = formatter.format(cal.getTime());
	    
	    List<AllFederalFormsPaymentInfoSaving> federalFormsDailyReportsList = allFederalFormsPaymentSavingDAOImpl.allFederalFormsPaymentDailyReports(todayDate);
	    List<AllFederalFormsPaymentInfoSaving> federalFormsWeeklyReportsList = allFederalFormsPaymentSavingDAOImpl.allFederalFormsPaymentInfoReports(todayDate, weeklyDate);
	    List<AllFederalFormsPaymentInfoSaving> federalFormsMonthlyReportsList = allFederalFormsPaymentSavingDAOImpl.allFederalFormsPaymentInfoReports(todayDate, monthlyDate);
	    List<AllFederalFormsPaymentInfoSaving> federalFormsQuarterlyReportsList = allFederalFormsPaymentSavingDAOImpl.allFederalFormsPaymentInfoReports(todayDate, quarterlyDate);
	    List<AllFederalFormsPaymentInfoSaving> federalFormsYearlyReportsList = allFederalFormsPaymentSavingDAOImpl.allFederalFormsPaymentInfoReports(todayDate, yearlyDate);
	    
	    if (!federalFormsQuarterlyReportsList.isEmpty()) {
            List<AllFederalFormsPaymentInfoSaving> federalFormsPaymentSavingInfoList = new ArrayList<AllFederalFormsPaymentInfoSaving>();
            int count = 1;
            for (AllFederalFormsPaymentInfoSaving federalFormsPaymentInfo : federalFormsQuarterlyReportsList) {
            	AllFederalFormsPaymentInfoSaving federalFormsPaymentInfoModel = new AllFederalFormsPaymentInfoSaving();
            	federalFormsPaymentInfoModel.setUserName(federalFormsPaymentInfo.getUserName());
            	federalFormsPaymentInfoModel.setLegalName(federalFormsPaymentInfo.getLegalName());
            	federalFormsPaymentInfoModel.setTypeOfDocument(federalFormsPaymentInfo.getTypeOfDocument());
            	federalFormsPaymentInfoModel.setProcessingFee(federalFormsPaymentInfo.getProcessingFee()); 
            	federalFormsPaymentInfoModel.setTotalFee(federalFormsPaymentInfo.getTotalFee());
            	federalFormsPaymentInfoModel.setAllFederalFormsPaymentInfoSavingId(count);
            	federalFormsPaymentInfoModel.setCreatedDate(federalFormsPaymentInfo.getCreatedDate());

            	federalFormsPaymentSavingInfoList.add(federalFormsPaymentInfoModel);
            	count++;            	
            }	    
	    
	    mav = new ModelAndView("quarterlyFederalReports");
        mav.addObject("quarterlyFederalReportsInfoList", federalFormsPaymentSavingInfoList);
        
        mav.addObject(dailyFederalReportsCount, federalFormsDailyReportsList.size());
        mav.addObject(weeklyFederalReportsCount, federalFormsWeeklyReportsList.size());
        mav.addObject(monthlyFederalReportsCount, federalFormsMonthlyReportsList.size());
        mav.addObject(quarterlyFederalReportsCount, federalFormsQuarterlyReportsList.size());
        mav.addObject(yearlyFederalReportsCount, federalFormsYearlyReportsList.size());
	    } else{
        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);
        }
	    mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		}
        return mav;
    }
	
//	Yearly Federal Reports Service Implementation
	
	@Override
	@Transactional
	public ModelAndView yearlyFederalReportsRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("yearlyFederalReportsRedirection...Service");		
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute(adminUserNameInSn) != null){
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	    String todayDate = formatter.format(new Date());
	    
	    Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -7);
	    String weeklyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -30);
	    String monthlyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -90);
	    String quarterlyDate = formatter.format(cal.getTime());
	    
	    cal.add(Calendar.DATE, -365);
	    String yearlyDate = formatter.format(cal.getTime());
	    
	    List<AllFederalFormsPaymentInfoSaving> federalFormsDailyReportsList = allFederalFormsPaymentSavingDAOImpl.allFederalFormsPaymentDailyReports(todayDate);
	    List<AllFederalFormsPaymentInfoSaving> federalFormsWeeklyReportsList = allFederalFormsPaymentSavingDAOImpl.allFederalFormsPaymentInfoReports(todayDate, weeklyDate);
	    List<AllFederalFormsPaymentInfoSaving> federalFormsMonthlyReportsList = allFederalFormsPaymentSavingDAOImpl.allFederalFormsPaymentInfoReports(todayDate, monthlyDate);
	    List<AllFederalFormsPaymentInfoSaving> federalFormsQuarterlyReportsList = allFederalFormsPaymentSavingDAOImpl.allFederalFormsPaymentInfoReports(todayDate, quarterlyDate);
	    List<AllFederalFormsPaymentInfoSaving> federalFormsYearlyReportsList = allFederalFormsPaymentSavingDAOImpl.allFederalFormsPaymentInfoReports(todayDate, yearlyDate);
	    
	    if (!federalFormsYearlyReportsList.isEmpty()) {
            List<AllFederalFormsPaymentInfoSaving> federalFormsPaymentSavingInfoList = new ArrayList<AllFederalFormsPaymentInfoSaving>();
            int count = 1;
            for (AllFederalFormsPaymentInfoSaving federalFormsPaymentInfo : federalFormsYearlyReportsList) {
            	AllFederalFormsPaymentInfoSaving federalFormsPaymentInfoModel = new AllFederalFormsPaymentInfoSaving();
            	federalFormsPaymentInfoModel.setUserName(federalFormsPaymentInfo.getUserName());
            	federalFormsPaymentInfoModel.setLegalName(federalFormsPaymentInfo.getLegalName());
            	federalFormsPaymentInfoModel.setTypeOfDocument(federalFormsPaymentInfo.getTypeOfDocument());
            	federalFormsPaymentInfoModel.setProcessingFee(federalFormsPaymentInfo.getProcessingFee()); 
            	federalFormsPaymentInfoModel.setTotalFee(federalFormsPaymentInfo.getTotalFee());
            	federalFormsPaymentInfoModel.setAllFederalFormsPaymentInfoSavingId(count);
            	federalFormsPaymentInfoModel.setCreatedDate(federalFormsPaymentInfo.getCreatedDate());

            	federalFormsPaymentSavingInfoList.add(federalFormsPaymentInfoModel);
            	count++;            	
            }	    
	    
	    mav = new ModelAndView("yearlyFederalReports");
        mav.addObject("yearlyFederalReportsInfoList", federalFormsPaymentSavingInfoList);
        
        mav.addObject(dailyFederalReportsCount, federalFormsDailyReportsList.size());
        mav.addObject(weeklyFederalReportsCount, federalFormsWeeklyReportsList.size());
        mav.addObject(monthlyFederalReportsCount, federalFormsMonthlyReportsList.size());
        mav.addObject(quarterlyFederalReportsCount, federalFormsQuarterlyReportsList.size());
        mav.addObject(yearlyFederalReportsCount, federalFormsYearlyReportsList.size()); 
	    } else{
        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);
        }
	    mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		}
        return mav;
    }

}
