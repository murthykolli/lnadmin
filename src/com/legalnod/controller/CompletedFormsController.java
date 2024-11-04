package com.legalnod.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.legalnod.service.CompletedFormsService;

/**
 * @author MurthyK
 *
 */

@Controller
public class CompletedFormsController {
	
	@Autowired
	private CompletedFormsService completedFormsService;	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CompletedFormsController.class);
	
//	Completed Forms Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/completedForms")
	@ResponseBody
	public ModelAndView completedForms(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("completedForms...Controller");
		return completedFormsService.completedFormsRedirection(req, sn);
	}
	
//	Completed State Forms Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/completedStateForms")
	@ResponseBody
	public ModelAndView completedStateForms(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("completedStateForms...Controller");
		return completedFormsService.completedStateFormsRedirection(req, sn);
	}
	
//	Completed Federal Forms Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/completedFederalForms")
	@ResponseBody
	public ModelAndView completedFederalForms(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("completedFederalForms...Controller");
		return completedFormsService.completedFederalFormsRedirection(req, sn);
	}
	
//	Completed Free Federal Forms Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/completedFreeFederalForms")
	@ResponseBody
	public ModelAndView completedFreeFederalForms(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("completedFreeFederalForms...Controller");
		return completedFormsService.completedFreeFederalFormsRedirection(req, sn);
	}
	
//	Completed Name Availability Check Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/nameAvailabilityCheck")
	@ResponseBody
	public ModelAndView nameAvailabilityCheck(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("nameAvailabilityCheck...Controller");
		return completedFormsService.completedNameAvaCheckFormsRedirection(req, sn);
	}
	
//	Completed Name Availability Check Redirection Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/nameAvaCheckDataOperations")
	@ResponseBody
	public ModelAndView nameAvaCheckDataOperations(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("nameAvaCheckDataOperations...Controller");
		return completedFormsService.nameAvailabilityCheckDataOperations(req, sn);
	}
	
//	Completed All State Forms Order Received Redirection Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/compAllStateFormsOrderReceived")
	@ResponseBody
	public ModelAndView compAllStateFormsOrderReceived(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("compAllStateFormsOrderReceived...Controller");
		return completedFormsService.compAllStateFormsDataOperationsFinishOrder(req, sn);
	}
	
//	Completed State Forms Checkout Display Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/compCheckoutStateFormsOrderDisplay")
	@ResponseBody
	public ModelAndView compCheckoutStateFormsOrderDisplay(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("compCheckoutStateFormsOrderDisplay...Controller");
		return completedFormsService.completedCheckoutStateFormsOrderDisplay(req, sn);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/stateFormDataSavingAndUpdatingInDB")
	@ResponseBody
	public ModelAndView stateFormDataSavingAndUpdatingInDB(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("stateFormDataSavingAndUpdatingInDB...Controller");
		return completedFormsService.stateFormDataSavingAndUpdatingInDB(req, sn);
	}
	
//	Completed Additional Forms Checkout Display Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/compCheckoutAdditionalFormsOrderDisplay")
	@ResponseBody
	public ModelAndView compCheckoutAdditionalFormsOrderDisplay(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("compCheckoutAdditionalFormsOrderDisplay...Controller");
		return completedFormsService.completedCheckoutAdditionalFormsOrderDisplay(req, sn);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/additionalSerFormModification")
	@ResponseBody
	public ModelAndView additionalSerFormModification(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("additionalSerFormModification...Controller");
		return completedFormsService.additionalServiceFormDataSavingAndUpdatingInDB(req, sn);
	}
	
//	Completed State Tax ID Forms Checkout Display Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/compCheckoutStateTaxIdFormsOrderDisplay")
	@ResponseBody
	public ModelAndView compCheckoutStateTaxIdFormsOrderDisplay(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("compCheckoutStateTaxIdFormsOrderDisplay...Controller");
		return completedFormsService.completedCheckoutStateTaxIdFormsOrderDisplay(req, sn);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/stateTaxIdFormModification")
	@ResponseBody
	public ModelAndView stateTaxIdFormModification(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("stateTaxIdFormModification...Controller");
		return completedFormsService.stateTaxIdFormDataSavingAndUpdatingInDB(req, sn);
	}
	
//	Completed State Forms Order Processed Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/compStateFormsOrderProcessed")
	@ResponseBody
	public ModelAndView compStateFormsOrderProcessed(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("compStateFormsOrderProcessed...Controller");
		return completedFormsService.completedStateFormsOrderProcessedRedirection(req, sn);
	}
	
//	Completed State Forms Signature Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/compStateFormsSignature")
	@ResponseBody
	public ModelAndView compStateFormsSignature(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("compStateFormsSignature...Controller");
		return completedFormsService.completedStateFormsSignatureRedirection(req, sn);
	}
	
//	Completed State Forms Doc Filed Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/compStateFormsDocFiled")
	@ResponseBody
	public ModelAndView compStateFormsDocFiled(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("compStateFormsDocFiled...Controller");
		return completedFormsService.completedStateFormsDocFiledRedirection(req, sn);
	}
	
//	Completed State Forms Doc Accepted Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/compStateFormsDocAccepted")
	@ResponseBody
	public ModelAndView compStateFormsDocAccepted(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("compStateFormsDocAccepted...Controller");
		return completedFormsService.completedStateFormsDocAcceptedRedirection(req, sn);
	}
	
//	Completed State Forms Doc Emailed Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/compStateFormsDocEmailed")
	@ResponseBody
	public ModelAndView compStateFormsDocEmailed(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("compStateFormsDocEmailed...Controller");
		return completedFormsService.completedStateFormsDocEmailedRedirection(req, sn);
	}
	
//	Completed State Forms Order Processed Update Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/compStateFormsOrderProcessedDataUpdate")
	@ResponseBody
	public ModelAndView compStateFormsOrderProcessedDataUpdate(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("compStateFormsOrderProcessedDataUpdate...Controller");
		return completedFormsService.allCompStateFormsSignatureStatusUpdation(req, sn);
	}
	
//	Completed State Forms Signature Update Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/compStateFormsSignatureDataUpdate")
	@ResponseBody
	public ModelAndView compStateFormsSignatureDataUpdate(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("compStateFormsSignatureDataUpdate...Controller");
		return completedFormsService.allCompStateFormsDocFiledStatusUpdation(req, sn);
	}
	
//	Completed State Forms Doc Filed Update Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/compStateFormsDocFiledDataUpdate")
	@ResponseBody
	public ModelAndView compStateFormsDocFiledDataUpdate(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("compStateFormsDocFiledDataUpdate...Controller");
		return completedFormsService.allCompStateFormsDocAcceptedStatusUpdation(req, sn);
	}
	
//	Completed State Forms Doc Accepted Update Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/compStateFormsDocAcceptedDataUpdate")
	@ResponseBody
	public ModelAndView compStateFormsDocAcceptedDataUpdate(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("compStateFormsDocAcceptedDataUpdate...Controller");
		return completedFormsService.allCompStateFormsDocEmailedStatusUpdation(req, sn);
	}
	
//	Completed State Forms Doc Emailed Update Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/compStateFormsDocEmailedDataUpdate")
	@ResponseBody
	public ModelAndView compStateFormsDocEmailedDataUpdate(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("compStateFormsDocEmailedDataUpdate...Controller");
		return completedFormsService.completedStateFormsFileUpload(req, sn);
	}
	
//	Completed State Forms File Update Controller
	
	@RequestMapping(method = RequestMethod.POST, value = "/compStateFormsEmailWithUploadFile")
	@ResponseBody
	public ModelAndView compStateFormsEmailWithUploadFile(@RequestParam("file") MultipartFile file, @RequestParam("subject") String subject, @RequestParam("comments") String comments, HttpServletRequest req, HttpSession sn) throws Exception {
		LOGGER.debug("compStateFormsEmailWithUploadFile...Controller");
		return completedFormsService.completedStateFormsFileUploadSendingAndSaving(file, subject, comments, req, sn);
	}
	
	
//	All Federal Forms Controller
	
//	Completed All Federal Forms Order Received Redirection Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/compAllFederalFormsOrderReceived")
	@ResponseBody
	public ModelAndView compAllFederalFormsOrderReceived(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("compAllFederalFormsOrderReceived...Controller");
		return completedFormsService.compAllFederalFormsDataOperationsFinishOrder(req, sn);
	}
	
//	Completed Federal Forms Order Processed Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/compFederalFormsOrderProcessed")
	@ResponseBody
	public ModelAndView compFederalFormsOrderProcessed(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("compFederalFormsOrderProcessed...Controller");
		return completedFormsService.completedFederalFormsOrderProcessedRedirection(req, sn);
	}
	
//	Completed Federal Forms Signature Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/compFederalFormsSignature")
	@ResponseBody
	public ModelAndView compFederalFormsSignature(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("compFederalFormsSignature...Controller");
		return completedFormsService.completedFederalFormsSignatureRedirection(req, sn);
	}
	
//	Completed Federal Forms Doc Filed Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/compFederalFormsDocFiled")
	@ResponseBody
	public ModelAndView compFederalFormsDocFiled(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("compFederalFormsDocFiled...Controller");
		return completedFormsService.completedFederalFormsDocFiledRedirection(req, sn);
	}
	
//	Completed Federal Forms Doc Accepted Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/compFederalFormsDocAccepted")
	@ResponseBody
	public ModelAndView compFederalFormsDocAccepted(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("compFederalFormsDocAccepted...Controller");
		return completedFormsService.completedFederalFormsDocAcceptedRedirection(req, sn);
	}
	
//	Completed Federal Forms Doc Emailed Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/compFederalFormsDocEmailed")
	@ResponseBody
	public ModelAndView compFederalFormsDocEmailed(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("compFederalFormsDocEmailed...Controller");
		return completedFormsService.completedFederalFormsDocEmailedRedirection(req, sn);
	}
	
//	Completed Federal Forms File Update Controller
	
	@RequestMapping(method = RequestMethod.POST, value = "/compFederalFormsEmailWithUploadFile")
	@ResponseBody
	public ModelAndView compFederalFormsEmailWithUploadFile(@RequestParam("file") MultipartFile file, @RequestParam("subject") String subject, @RequestParam("comments") String comments, HttpServletRequest req, HttpSession sn) throws Exception {
		LOGGER.debug("compFederalFormsEmailWithUploadFile...Controller");
		return completedFormsService.completedFederalFormsFileUploadSendingAndSaving(file, subject, comments, req, sn);
	}
	
//	Completed Federal Forms Order Processed Update Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/compFederalFormsOrderProcessedDataUpdate")
	@ResponseBody
	public ModelAndView compFederalFormsOrderProcessedDataUpdate(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("compFederalFormsOrderProcessedDataUpdate...Controller");
		return completedFormsService.allCompFederalFormsSignatureStatusUpdation(req, sn);
	}
	
//	Completed Federal Forms Signature Update Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/compFederalFormsSignatureDataUpdate")
	@ResponseBody
	public ModelAndView compFederalFormsSignatureDataUpdate(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("compFederalFormsSignatureDataUpdate...Controller");
		return completedFormsService.allCompFederalFormsDocFiledStatusUpdation(req, sn);
	}
	
//	Completed Federal Forms Doc Filed Update Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/compFederalFormsDocFiledDataUpdate")
	@ResponseBody
	public ModelAndView compFederalFormsDocFiledDataUpdate(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("compFederalFormsDocFiledDataUpdate...Controller");
		return completedFormsService.allCompFederalFormsDocAcceptedStatusUpdation(req, sn);
	}
	
//	Completed Federal Forms Doc Accepted Update Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/compFederalFormsDocAcceptedDataUpdate")
	@ResponseBody
	public ModelAndView compFederalFormsDocAcceptedDataUpdate(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("compFederalFormsDocAcceptedDataUpdate...Controller");
		return completedFormsService.allCompFederalFormsDocEmailedStatusUpdation(req, sn);
	}
	
//	Completed Federal Forms Doc Emailed Update Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/compFederalFormsDocEmailedDataUpdate")
	@ResponseBody
	public ModelAndView compFederalFormsDocEmailedDataUpdate(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("compFederalFormsDocEmailedDataUpdate...Controller");
		return completedFormsService.completedFederalFormsFileUpload(req, sn);
	}
	
//	Completed Federal Tax ID Forms Checkout Display Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/compCheckoutFederalTaxIdFormsOrderDisplay")
	@ResponseBody
	public ModelAndView compCheckoutFederalTaxIdFormsOrderDisplay(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("compCheckoutFederalTaxIdFormsOrderDisplay...Controller");
		return completedFormsService.completedCheckoutFederalTaxIdFormsOrderDisplay(req, sn);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/singleFederalDataModification")
	@ResponseBody
	public ModelAndView singleFederalDataModification(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("singleFederalDataModification...Controller");
		return completedFormsService.compSingleFederalTaxModifiedDataSaving(req, sn);
	}
	
//	Completed S Corporation Forms Checkout Display Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/compCheckoutSCorpFormsOrderDisplay")
	@ResponseBody
	public ModelAndView compCheckoutSCorpFormsOrderDisplay(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("compCheckoutSCorpFormsOrderDisplay...Controller");
		return completedFormsService.completedCheckoutSCorpFormsOrderDisplay(req, sn);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/sCorporationDataModification")
	@ResponseBody
	public ModelAndView sCorporationDataModification(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("sCorporationDataModification...Controller");
		return completedFormsService.compSCorporationTaxModifiedDataSaving(req, sn);
	}
	
//	Completed 501 Forms Checkout Display Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/compCheckout501FormsOrderDisplay")
	@ResponseBody
	public ModelAndView compCheckout501FormsOrderDisplay(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("compCheckout501FormsOrderDisplay...Controller");
		return completedFormsService.completedCheckout501FormsOrderDisplay(req, sn);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/fiveZeroOneFormModification")
	@ResponseBody
	public ModelAndView fiveZeroOneFormModification(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("fiveZeroOneFormModification...Controller");
		return completedFormsService.comp501AppModifiedDataSaving(req, sn);
	}
	
//	Free Federal Form Data Redirection Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/compFreeFederalFormsDataOperations")
	@ResponseBody
	public ModelAndView compFreeFederalFormsDataOperations(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("compFreeFederalFormsDataOperations...Controller");
		return completedFormsService.compFreeFederalFormsDataOperationsFinishOrder(req, sn);
	}
	
//	Free Federal Form Data Updation Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/compCheckoutFreeFederalTaxIdUpdation")
	@ResponseBody
	public ModelAndView compCheckoutFreeFederalTaxIdUpdation(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("compCheckoutFreeFederalTaxIdUpdation...Controller");
		return completedFormsService.compCheckoutFreeFederalTaxFormsDisplayRedirection(req, sn);
	}
	
//	Free Federal Form Modified Data Saving Controller
	
	@RequestMapping(method = RequestMethod.POST, value = "/compFreeFederalDataModification")
	@ResponseBody
	public ModelAndView compFreeFederalDataModification(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("compFreeFederalDataModification...Controller");
		return completedFormsService.compFreeFederalTaxModifiedDataSaving(req, sn);
	}
	
//	Completed Federal Form Uploaded data Saving display Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/compFedFormsUploadDataSaving")
	@ResponseBody
	public ModelAndView compFedFormsUploadDataSaving(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("compFedFormsUploadDataSaving...Controller");
		return completedFormsService.completedFederalFormsFileUploadedDataDisplay(req, sn);
	}
	
//	Completed State Form Uploaded data Saving display Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/compStateFormsUploadDataSaving")
	@ResponseBody
	public ModelAndView compStateFormsUploadDataSaving(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("compStateFormsUploadDataSaving...Controller");
		return completedFormsService.completedStateFormsFileUploadedDataDisplay(req, sn);
	}
	
//	File Upload State forms JSON Controller
	
	@RequestMapping(method = RequestMethod.POST, value = "/jSonFileUploadData")
	@ResponseBody
	public JSONArray jSonFileUploadData(@RequestParam("fileUploadData") String fileUploadData, HttpSession sn) {
		LOGGER.debug("jSonFileUploadData...Controller");
		LOGGER.info("fileUploadData : "+fileUploadData);
		return completedFormsService.stateFormsfileUploadJSON(fileUploadData, sn);		
	}
	
//	Completed Admin Forms Uploaded Redirection Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/adminFormsUploadRedirection")
	@ResponseBody
	public ModelAndView adminFormsUploadRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("adminFormsUploadRedirection...Controller");
		return completedFormsService.adminFileUploadedRedirection(req, sn);
	}
	
//	Completed Admin Forms Uploaded Redirection Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/adminStateFormsFileupload")
	@ResponseBody
	public ModelAndView adminStateFormsFileupload(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("adminStateFormsFileupload...Controller");
		return completedFormsService.adminStateFormsFileUploadedDataDisplay(req, sn);
	}
	
//	Completed Admin Forms Uploaded Redirection Controller
	
	@RequestMapping(method = RequestMethod.GET, value = "/adminFederalFormsFileupload")
	@ResponseBody
	public ModelAndView adminFederalFormsFileupload(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("adminFederalFormsFileupload...Controller");
		return completedFormsService.adminFederalFormsFileUploadedDataDisplay(req, sn);
	}
	
	
	

}
