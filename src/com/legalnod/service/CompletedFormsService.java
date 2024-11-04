package com.legalnod.service;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

public interface CompletedFormsService {
	
	public ModelAndView completedFormsRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView completedStateFormsRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView completedFederalFormsRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView completedFreeFederalFormsRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView completedNameAvaCheckFormsRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView nameAvailabilityCheckDataOperations(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView nameAvailabilityCheckStatusUpdation(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView nameAvailabilityCheckStatusDeletion(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView compAllStateFormsDataOperationsFinishOrder(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView allCompStateFormsOrderInfoRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView allCompStateFormsViewPDFRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView allCompStateFormsOrderProcessedStatusUpdation(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView compBusinessStateFormsCheckouDataDisplay(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView compAdditionalServiceFormsCheckouDataDisplay(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView compStateTaxIdFormsCheckouDataDisplay(HttpServletRequest req, HttpSession sn);
	
	public JSONObject convertFromStringToJSONFormat(String capturedInfoInDB);
	
	public int takeFormIdFromDB(HttpSession sn);
	
	public int takeUserIdFromDB(HttpSession sn);
	
	public Timestamp currentDate();
	
	public ModelAndView completedCheckoutStateFormsOrderDisplay(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView completedCheckoutAdditionalFormsOrderDisplay(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView completedCheckoutStateTaxIdFormsOrderDisplay(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView stateFormModificationDataRetrieveFromDB(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView newStateFormAttributesInfoSelection(JSONObject capInfoJsonObject, HttpServletRequest req);
	
	public ModelAndView stateFormDataSavingAndUpdatingInDB(HttpServletRequest req, HttpSession sn);
	
	public String registerAgentPriceTakeFromDB(String regAgentValue, HttpServletRequest req, HttpSession sn);
	
	public JSONObject stateFormsFiveAttributesInfoFromJSP(HttpServletRequest req);
	
	public JSONObject stateFormsAllAttributesInfoFromJSP(HttpServletRequest req);
	
	public String stateFormStatusInDB(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView stateFormsAllRelatedOtherTablesUpdate(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView additionalFormModificationDataRetrieveFromDB(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView newAdditionalServiceAttributesInfoSelection(JSONObject capInfoJsonObject, HttpServletRequest req);
	
	public ModelAndView stateTaxIdFormModificationDataRetrieveFromDB(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView newStateTaxIdAttributesInfoSelection(JSONObject capInfoJsonObject, HttpServletRequest req);
	
	public ModelAndView additionalServiceFormDataSavingAndUpdatingInDB(HttpServletRequest req, HttpSession sn);
	
	public String additionalServiceFormRegistredAgentPrice(HttpServletRequest req, HttpSession sn);
	
	public String additionalServiceFormStatusInDB(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView additionalFormsAllRelatedOtherTablesUpdate(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView stateTaxIdFormsAllRelatedOtherTablesUpdate(HttpServletRequest req, HttpSession sn);
	
	public JSONObject additionalServiceFormsFiveAttributesInfoFromJSP(HttpServletRequest req);
	
	public JSONObject addSerFormsAllAttributesInfoFromJSP(HttpServletRequest req);
	
	public ModelAndView stateTaxIdFormDataSavingAndUpdatingInDB(HttpServletRequest req, HttpSession sn);
	
	public JSONObject stateTaxFormsAttributesInfoFromJSP(HttpServletRequest req);
	
	public String stateTaxIdFormStatusInDB(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView completedStateFormsOrderProcessedRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView allCompStateFormsSignatureStatusUpdation(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView completedStateFormsSignatureRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView allCompStateFormsDocFiledStatusUpdation(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView completedStateFormsDocFiledRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView allCompStateFormsDocAcceptedStatusUpdation(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView completedStateFormsDocAcceptedRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView allCompStateFormsDocEmailedStatusUpdation(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView completedStateFormsDocEmailedRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView compAllFederalFormsDataOperationsFinishOrder(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView allCompFederalFormsOrderInfoRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView allCompFederalFormsViewPDFRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView allCompFederalFormsOrderProcessedStatusUpdation(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView completedFederalFormsOrderProcessedRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView allCompFederalFormsSignatureStatusUpdation(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView completedFederalFormsSignatureRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView allCompFederalFormsDocFiledStatusUpdation(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView completedFederalFormsDocFiledRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView allCompFederalFormsDocAcceptedStatusUpdation(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView completedFederalFormsDocAcceptedRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView allCompFederalFormsDocEmailedStatusUpdation(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView completedFederalFormsDocEmailedRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView compFederalTaxIdFormsCheckouDataDisplay(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView compSCorporationFormsCheckouDataDisplay(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView compFiveZeroOneFormsCheckouDataDisplay(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView completedCheckoutFederalTaxIdFormsOrderDisplay(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView completedCheckoutSCorpFormsOrderDisplay(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView completedCheckout501FormsOrderDisplay(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView compFederalTaxIDFormUpdation(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView federalTaxIDFormAttributesInfo(JSONObject capInfoJsonObject, HttpServletRequest req);
	
	public ModelAndView sCorporationFormUpdation(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView sCorporationFormAttributesInfo(JSONObject capInfoJsonObject, HttpServletRequest req);
	
	public ModelAndView fiveZeroOneFormUpdation(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView fiveZeroOneFormAttributesInfo(JSONObject capInfoJsonObject, HttpServletRequest req);
	
	public ModelAndView compSingleFederalTaxModifiedDataSaving(HttpServletRequest req, HttpSession sn);
	
	public String federalTaxIDFormStatusInDB(HttpServletRequest req, HttpSession sn);
	
	public JSONObject singleFederalFormsAttributesInfoFromJSP(HttpServletRequest req);
	
	public ModelAndView singleFederalTaxFormsAllRelatedOtherTablesUpdate(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView compSCorporationTaxModifiedDataSaving(HttpServletRequest req, HttpSession sn);
	
	public String sCorporationFormStatusInDB(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView sCorpFormsAllRelatedOtherTablesUpdate(HttpServletRequest req, HttpSession sn);	
	
	public JSONObject sCorporationFormsAttributesInfoFromJSP(HttpServletRequest req);
	
	public ModelAndView comp501AppModifiedDataSaving(HttpServletRequest req, HttpSession sn);
	
	public String fiveZeroOneFormStatusInDB(HttpServletRequest req, HttpSession sn);
		
	public ModelAndView fzoFormsAllRelatedOtherTablesUpdate(HttpServletRequest req, HttpSession sn);
	
	public JSONObject fiveZeroOneFormsAttributesInfoFromJSP(HttpServletRequest req);
	
	public ModelAndView compFreeFederalFormsDataOperationsFinishOrder(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView compFreeFederalTaxIDFormUpdation(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView freeFederalTaxIdAttributesInfoSelection(JSONObject capInfoJsonObject, HttpServletRequest req);
	
	public ModelAndView compFreeFederalTaxModifiedDataSaving(HttpServletRequest req, HttpSession sn);
	
	public JSONObject freeFederalFormsAttributesInfoFromJSP(HttpServletRequest req);
	
	public String freeFederalFormStatusInDB(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView freeFederalTaxIdFormsCheckouDataDisplay(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView compCheckoutFreeFederalTaxFormsDisplayRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView completedStateFormsFileUpload(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView completedStateFormsFileUploadSendingAndSaving(MultipartFile file, String subject, String comments, HttpServletRequest req, HttpSession sn) throws Exception;
	
	public ModelAndView completedFederalFormsFileUpload(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView completedFederalFormsFileUploadSendingAndSaving(MultipartFile file, String subject, String comments, HttpServletRequest req, HttpSession sn) throws Exception;
	
	public ModelAndView completedFederalFormsFileUploadedDataDisplay(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView completedStateFormsFileUploadedDataDisplay(HttpServletRequest req, HttpSession sn);
	
	public JSONArray stateFormsfileUploadJSON(String selectedCat, HttpSession sn);
	
	public ModelAndView adminFileUploadedRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView adminStateFormsFileUploadedDataDisplay(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView adminFederalFormsFileUploadedDataDisplay(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView nameAvailabilityCheckFormsViewPDFRedirection(HttpServletRequest req, HttpSession sn);
	
	public ModelAndView allCompFreeFederalFormsViewPDFRedirection(HttpServletRequest req, HttpSession sn);
	
	
	
	
	
}
