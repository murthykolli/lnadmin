package com.legalnod.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.json.simple.JSONArray;

import com.legalnod.service.AdminHomeService;

/**
 * @author MurthyK
 *
 */

@Controller
public class AdminHomeController {
	
	@Autowired
	private AdminHomeService adminHomeService;	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminHomeController.class);
	
//	Admin Home Page Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/adminHome")
	@ResponseBody
	public ModelAndView adminHome(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("adminHome...Controller");
		return adminHomeService.adminHomeURLParamRedirection(req, sn);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/adminHomeRedirection")
	@ResponseBody
	public ModelAndView adminHomeRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("adminHomeRedirection...Controller");
		return adminHomeService.adminHomeRedirection(req, sn);
	}
	
//	Admin User Profile Controller
	
	@RequestMapping(method = RequestMethod.POST, value = "/adminUserChangePassword")
	@ResponseBody
	public ModelAndView adminUserChangePassword(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("adminUserChangePassword...Controller");
		return adminHomeService.adminUserProfileUpdatingInDB(req, sn);
	}
	
//	Admin User Profile Updating Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/userMyAccountRedirection")
	@ResponseBody
	public ModelAndView userMyAccountRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("userMyAccountRedirection...Controller");
		return adminHomeService.adminUserProfileRedirection(req, sn);
	}
	
//	Admin Logout Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/logOut")
	@ResponseBody
	public ModelAndView logOut(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("logOut...Controller");
		return adminHomeService.adminLogOutRedirection(req, sn);
	}
	
//	Admin SessionTimeout Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/sessionTimeOut")
	@ResponseBody
	public ModelAndView sessionTimeOut(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("sessionTimeOut...Controller");
		return adminHomeService.adminLogOutRedirection(req, sn);
	}
	
//	Admin Federal Tax Attr Modification Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/federalTaxAttrModification")
	@ResponseBody
	public ModelAndView federalTaxAttrModification(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("federalTaxAttrModification...Controller");
		return adminHomeService.federalTaxAttrModificationRedirection(req, sn);
	}
	
//	Admin SCorp Attr Modification Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/sCorpAttrModification")
	@ResponseBody
	public ModelAndView sCorpAttrModification(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("sCorpAttrModification...Controller");
		return adminHomeService.sCorpAttrModificationRedirection(req, sn);
	}
	
//	Admin 501 Attr Modification Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/fiveZeroOneAttrModification")
	@ResponseBody
	public ModelAndView fiveZeroOneAttrModification(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("fiveZeroOneAttrModification...Controller");
		return adminHomeService.fiveZeroOneAttrModificationRedirection(req, sn);
	}
	
//	Federal Tax JSON calling for modified values Controller
	
	@RequestMapping(method = RequestMethod.POST, value = "/federalTaxAttrFieldNameUpdate")
	@ResponseBody
	public JSONArray federalTaxAttrFieldNameUpdate(@RequestParam("attributeFieldName") String attributeFieldName, HttpSession sn) {
		LOGGER.debug("federalTaxAttrFieldNameUpdate ...Controller");
		LOGGER.info("attributeFieldName : "+attributeFieldName);		
		return adminHomeService.federalTaxJSonAttrFieldNameUpdationInDB(attributeFieldName, sn);
		
	}
	
//	S Corporation JSON calling for modified values Controller
	
	@RequestMapping(method = RequestMethod.POST, value = "/sCorpAttrFieldNameUpdate")
	@ResponseBody
	public JSONArray sCorpAttrFieldNameUpdate(@RequestParam("attributeFieldName") String attributeFieldName, HttpSession sn) {
		LOGGER.debug("sCorpAttrFieldNameUpdate ...Controller");
		LOGGER.info("attributeFieldName : "+attributeFieldName);		
		return adminHomeService.sCorpJSonAttrFieldNameUpdationInDB(attributeFieldName, sn);
		
	}
	
//	501 Application JSON calling for modified values Controller
	
	@RequestMapping(method = RequestMethod.POST, value = "/fiveZeroOneAttrFieldNameUpdate")
	@ResponseBody
	public JSONArray fiveZeroOneAttrFieldNameUpdate(@RequestParam("attributeFieldName") String attributeFieldName, HttpSession sn) {
		LOGGER.debug("fiveZeroOneAttrFieldNameUpdate ...Controller");
		LOGGER.info("attributeFieldName : "+attributeFieldName);		
		return adminHomeService.fiveZeroOneJSonAttrFieldNameUpdationInDB(attributeFieldName, sn);
		
	}
	
//	Admin Federal Forms Back Moving Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/admPendingFederalForms")
	@ResponseBody
	public ModelAndView admPendingFederalForms(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("admPendingFederalForms...Controller");
		return adminHomeService.admPendingFederalFormsBackMove(req, sn);
	}
	
//	Admin State Forms Back Moving Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/admCompStateForms")
	@ResponseBody
	public ModelAndView admCompStateForms(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("admCompStateForms...Controller");
		return adminHomeService.admCompStateFormsBackMove(req, sn);
	}
	
//	Admin State Forms Attributes Modification Selection Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/stateFormsAttrModification")
	@ResponseBody
	public ModelAndView stateFormsAttrModification(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("stateFormsAttrModification...Controller");
		return adminHomeService.stateFormsAttributesDisplayModification(req, sn);
	}
	
//	Admin Additional Forms Attributes Modification Selection Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/additionalFormsAttrModification")
	@ResponseBody
	public ModelAndView additionalFormsAttrModification(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("additionalFormsAttrModification...Controller");
		return adminHomeService.additionalAttributesDisplayModification(req, sn);
	}
	
//	Admin State Tax Id Forms Attributes Modification Selection Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/stateTaxIdFormsAttrModification")
	@ResponseBody
	public ModelAndView stateTaxIdFormsAttrModification(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("stateTaxIdFormsAttrModification...Controller");
		return adminHomeService.stateTaxIdFormsAttributesDisplayModification(req, sn);
	}
	
//	Dropdown Json calling select Form Name with State and type of doc Controller
	
	@RequestMapping(method = RequestMethod.POST, value = "/jSonFormSelectionWithState")
	@ResponseBody
	public JSONArray jSonFormSelectionWithState(@RequestParam("typeOfDocument") String typeOfDocument, HttpSession sn) {
		LOGGER.debug("jSonFormSelectionWithState...Controller");
		LOGGER.info("typeOfDocument : "+typeOfDocument);
		return adminHomeService.selectFormNamesWithStateData(typeOfDocument, sn);
		
	}
	
//	Dropdown Json calling select Data with State, form and type of doc Controller
	
	@RequestMapping(method = RequestMethod.POST, value = "/jSonDataSelectionWithStateAndForm")
	@ResponseBody
	public JSONArray jSonDataSelectionWithStateAndForm(@RequestParam("typeOfDocument") String typeOfDocument, HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("jSonDataSelectionWithStateAndForm...Controller");
		LOGGER.info("typeOfDocument : "+typeOfDocument);
		return adminHomeService.selectDataWithStateAndForm(typeOfDocument, req, sn);
		
	}
	
//	State Forms JSON calling for modified values Controller
	
	@RequestMapping(method = RequestMethod.POST, value = "/stateFormsAttrFieldNameUpdate")
	@ResponseBody
	public JSONArray stateFormsAttrFieldNameUpdate(@RequestParam("attributeName") String attributeName, HttpSession sn) {
		LOGGER.debug("stateFormsAttrFieldNameUpdate ...Controller");
		LOGGER.info("attributeName : "+attributeName);		
		return adminHomeService.stateFormsAttrFieldNameUpdationInDB(attributeName, sn);
		
	}
	
//	Admin State Forms Json Attributes Data Display Selection Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/jSonStateFormsAttrDataDisplay")
	@ResponseBody
	public ModelAndView jSonStateFormsAttrDataDisplay(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("jSonStateFormsAttrDataDisplay...Controller");
		return adminHomeService.stateFormsAttrModificationRedirection(req, sn);
	}
	
//	Admin Additional Forms Json Attributes Data Display Selection Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/jSonAdditionalFormsAttrDataDisplay")
	@ResponseBody
	public ModelAndView jSonAdditionalFormsAttrDataDisplay(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("jSonAdditionalFormsAttrDataDisplay...Controller");
		return adminHomeService.additionalFormsAttrModificationRedirection(req, sn);
	}
	
//	Admin State Tax Id Forms Json Attributes Data Display Selection Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/jSonStateTaxIdFormsAttrDataDisplay")
	@ResponseBody
	public ModelAndView jSonStateTaxIdFormsAttrDataDisplay(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("jSonStateTaxIdFormsAttrDataDisplay...Controller");
		return adminHomeService.stateTaxIdFormsAttrModificationRedirection(req, sn);
	}


}
