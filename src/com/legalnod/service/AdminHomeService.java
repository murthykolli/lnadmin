package com.legalnod.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import org.json.simple.JSONArray;


public interface AdminHomeService {
	
	public ModelAndView adminHomeRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView adminHomeURLParamRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView adminUserProfileRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView adminUserProfileUpdatingInDB(HttpServletRequest request, HttpSession session);
	
	public ModelAndView adminLogOutRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView AdminFormsCountDisplay(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView federalTaxAttrModificationRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView sCorpAttrModificationRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView fiveZeroOneAttrModificationRedirection(HttpServletRequest req, HttpSession sn);
	
	public JSONArray federalTaxJSonAttrFieldNameUpdationInDB(String updatedAttrVal, HttpSession sn);
	
	public JSONArray sCorpJSonAttrFieldNameUpdationInDB(String updatedAttrVal, HttpSession sn);
	
	public JSONArray fiveZeroOneJSonAttrFieldNameUpdationInDB(String updatedAttrVal, HttpSession sn);
	
	public ModelAndView admPendingFederalFormsBackMove(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView stateFormsAttributesDisplayModification(HttpServletRequest req, HttpSession sn);
		
	public JSONArray selectFormNamesWithStateData(String combValue, HttpSession sn);
	
	public JSONArray selectDataWithStateAndForm(String combValue, HttpServletRequest req, HttpSession sn);
	
	public ModelAndView stateFormsAttrModificationRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView additionalFormsAttrModificationRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView stateTaxIdFormsAttrModificationRedirection(HttpServletRequest req, HttpSession sn);
	
	public int takeFormIdFromDB(String stateName, String formName);
	
	public ModelAndView admCompStateFormsBackMove(HttpServletRequest req, HttpSession sn);
	
	public JSONArray stateFormsAttrFieldNameUpdationInDB(String updatedAttrVal, HttpSession sn);
	
	public ModelAndView additionalAttributesDisplayModification(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView stateTaxIdFormsAttributesDisplayModification(HttpServletRequest req, HttpSession sn);
	
	

}
