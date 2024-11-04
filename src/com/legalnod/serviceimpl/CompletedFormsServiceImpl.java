package com.legalnod.serviceimpl;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.legalnod.daoimpl.AdditionalFormsAttributesAndValuesSavingDAOImpl;
import com.legalnod.daoimpl.AdditionalFormsAttributesInfoDAOImpl;
import com.legalnod.daoimpl.AllFederalFormsCheckoutPaymentAndUserContactSavingDAOImpl;
import com.legalnod.daoimpl.AllFederalFormsDataSavingDAOImpl;
import com.legalnod.daoimpl.AllFederalFormsPaymentInfoSavingDAOImpl;
import com.legalnod.daoimpl.AllStateFormsCheckoutPaymentAndUserContactSavingDAOImpl;
import com.legalnod.daoimpl.AllStateFormsDataSavingDAOImpl;
import com.legalnod.daoimpl.AllStateFormsPaymentInfoSavingDAOImpl;
import com.legalnod.daoimpl.BusinessFormsAttributesAndValuesSavingDAOImpl;
import com.legalnod.daoimpl.BusinessFormsAttributesInfoDAOImpl;
import com.legalnod.daoimpl.CompletedFederalFormsFileUploadSavingDAOImpl;
import com.legalnod.daoimpl.CompletedStateFormsFileUploadSavingDAOImpl;
import com.legalnod.daoimpl.FederalFormsPriceInfoDAOImpl;
import com.legalnod.daoimpl.FederalTaxIdFormsAttributesAndValuesSavingDAOImpl;
import com.legalnod.daoimpl.FederalTaxIdFormsAttributesInfoDAOImpl;
import com.legalnod.daoimpl.FivezerooneAppFormsAttributesAndValuesSavingDAOImpl;
import com.legalnod.daoimpl.FivezerooneAppFormsAttributesInfoDAOImpl;
import com.legalnod.daoimpl.FormFederalAttributesAndValuesSavingDAOImpl;
import com.legalnod.daoimpl.FormFederalDocumentsDataSavingDAOImpl;
import com.legalnod.daoimpl.NameAvailabilityCheckSavingDAOImpl;
import com.legalnod.daoimpl.PDFDocumentsInfoDAOImpl;
import com.legalnod.daoimpl.ScorporationFormsAttributesAndValuesSavingDAOImpl;
import com.legalnod.daoimpl.ScorporationFormsAttributesInfoDAOImpl;
import com.legalnod.daoimpl.StateTaxFormsAttributesAndValuesSavingDAOImpl;
import com.legalnod.daoimpl.StateTaxFormsAttributesInfoDAOImpl;
import com.legalnod.daoimpl.UsersInformationDAOImpl;
import com.legalnod.model.AdditionalFormsAttributesAndValuesSaving;
import com.legalnod.model.AdditionalFormsAttributesInfo;
import com.legalnod.model.AllFederalFormsCheckoutPaymentAndUserContactSaving;
import com.legalnod.model.AllFederalFormsDataSaving;
import com.legalnod.model.AllFederalFormsPaymentInfoSaving;
import com.legalnod.model.AllStateFormsCheckoutPaymentAndUserContactSaving;
import com.legalnod.model.AllStateFormsDataSaving;
import com.legalnod.model.AllStateFormsPaymentInfoSaving;
import com.legalnod.model.BusinessFormsAttributesAndValuesSaving;
import com.legalnod.model.BusinessFormsAttributesInfo;
import com.legalnod.model.CompletedFederalFormsFileUploadSaving;
import com.legalnod.model.CompletedStateFormsFileUploadSaving;
import com.legalnod.model.FederalTaxIdFormsAttributesAndValuesSaving;
import com.legalnod.model.FederalTaxIdFormsAttributesInfo;
import com.legalnod.model.FivezerooneAppFormsAttributesAndValuesSaving;
import com.legalnod.model.FivezerooneAppFormsAttributesInfo;
import com.legalnod.model.FormFederalAttributesAndValuesSaving;
import com.legalnod.model.FormFederalDocumentsDataSaving;
import com.legalnod.model.Forms;
import com.legalnod.model.NameAvailabilityCheckSaving;
import com.legalnod.model.PDFDocumentsInfo;
import com.legalnod.model.ScorporationFormsAttributesAndValuesSaving;
import com.legalnod.model.ScorporationFormsAttributesInfo;
import com.legalnod.model.StateTaxFormsAttributesAndValuesSaving;
import com.legalnod.model.StateTaxFormsAttributesInfo;
import com.legalnod.model.UsersInformation;
import com.legalnod.service.CompletedFormsService;

public class CompletedFormsServiceImpl  implements CompletedFormsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CompletedFormsServiceImpl.class);
	
	private String compStateFormsOrderReceivedCount = "compStateFormsOrderReceivedCount";
	private String compStateFormsOrderProcessedCount = "compStateFormsOrderProcessedCount";
	private String compStateFormsSignatureCount = "compStateFormsSignatureCount";
	private String compStateFormsDocFiledCount = "compStateFormsDocFiledCount";
	private String compStateFormsDocAcceptedCount = "compStateFormsDocAcceptedCount";
	private String compStateFormsDocEmailedCount = "compStateFormsDocEmailedCount";
    private String completedFedFormsCount = "completedFedFormsCount";
    private String completedFreeFedFormsCount = "completedFreeFedFormsCount";
    private String completedNameAvaCheckCount = "completedNameAvaCheckCount";
    
    private String compFederalFormsOrderReceivedCount = "compFederalFormsOrderReceivedCount";
    private String compFederalFormsOrderProcessedCount = "compFederalFormsOrderProcessedCount";
    private String compFederalFormsSignatureCount = "compFederalFormsSignatureCount";
    private String compFederalFormsDocFiledCount = "compFederalFormsDocFiledCount";
    private String compFederalFormsDocAcceptedCount = "compFederalFormsDocAcceptedCount";
    private String compFederalFormsDocEmailedCount = "compFederalFormsDocEmailedCount";
    
    private String userEmailInSn = "userEmailInSn";
    private String businessStateInSn = "businessStateInSn";
    private String companyFormingInSn = "companyFormingInSn";
    private String businessNameInSn = "businessNameInSn";
    
    private String userNameInSn = "userNameInSn";    
    private String userChoiceInSn = "userChoiceInSn";
    private String typeOfDocumentInSn = "typeOfDocumentInSn";
    private String formNameInSn = "formNameInSn";
    private String formName = "formName";
    private String stateNameInSn = "stateNameInSn";
    private String stateName = "stateName";    
    private String yes = "yes";
    private String no = "no";
    private String businessForm = "Business Forms";
    private String additionalForm = "Additional Forms";
    private String stateTaxIdForm = "State Tax ID Forms";    
   
    private String adminFirstName = "adminFirstName";
    private String adminFirstNameInSn = "adminFirstNameInSn";
    private String BackMove = "BackMove";
    private String FormModify = "FormModify";    
    
    private String formIdInSn = "formIdInSn";
    private String userIdInSn = "userIdInSn";
    private String attributeTextField1 = "attributeTextField1";
    private String attribTextFieldList = "attributeTextFieldList";
    private String attribTextFieldAddrList = "attributeTextFieldAddrList";
    private String attribTextFieldZipList = "attributeTextFieldZipList";
    private String attribTextAreaList = "attributeTextAreaList";
    private String attribSelectBoxList = "attributeSelectBoxList";
    private String attribTextFieldDateList = "attributeTextFieldDateList";
    private String radioButtList = "radioButtonList";
    private String checkBoxesList = "checkBoxList";    
    private String radioButStatus = "RadioButtonStatus";
    private String attReqiTypeIdsInSn = "attrReqTypeIdsInSn";
    private String stateFormHiddenVarb = "stateFormHiddenVar";
    private String stateFormsPageValue = "stateFormPageValues";
    private String bsAttrbRequiredList = "Attribute_Required_List";
    private String bsAttrbRadioStatusList = "Attribute_RadioStatus_List";
    private String bsAttrbInnerRadioList = "Attribute_InnerRadio_List";
    private String bsAttrbAddAnotherList = "Attribute_AddAnother_List";
    private String bsAttrbNamesList = "Attribute_Names_List";
    private String bsAttrbValuesList = "Attribute_Values_List";    
    private String attribTextFieldAddr45 = "attributeTextFieldAddr45";
    private String newStateFormsCreation = "newStateFormsCreation";
        
    private String addSerFormModification = "additionalSerFormModification";
    private String textFieldsList = "textFieldList";
    private String textAreasList = "textAreaList";
    private String dateFieldsList = "dateFieldList";
    private String selectBoxesList = "selectBoxList";
    private String zipCodesList = "zipCodeList";
    private String stTaxIdFormModification = "stateTaxIdFormModification";
    private String textField1 = "textField1";
    private String asAttrbRequiredList = "AS_Attribute_Required_List";
    private String asAttrbRadioStatusList = "AS_Attribute_RadioStatus_List";
    private String asAttrbInnerRadioList = "AS_Attribute_InnerRadio_List";
    private String asAttrbAddAnotherList = "AS_Attribute_AddAnother_List";
    private String asAttrbNamesList = "AS_Attribute_Names_List";
    private String asAttrbValuesList = "AS_Attribute_Values_List";
    private String textField100 = "textField100";
    private String radioButton100 = "radioButton100";
    private String radioButtonsList = "radioButtonList";
    private String checkBoxsList = "checkBoxList";
    
    private String stiAttrbRequiredList = "STI_Attribute_Required_List";
    private String stiAttrbRadioStatusList = "STI_Attribute_RadioStatus_List";
    private String stiAttrbInnerRadioList = "STI_Attribute_InnerRadio_List";
    private String stiAttrbAddAnotherList = "STI_Attribute_AddAnother_List";
    private String stiAttrbNamesList = "STI_Attribute_Names_List";
    private String stiAttrbValuesList = "STI_Attribute_Values_List";
    
    private String federalTaxId = "Federal Tax ID";    
    private String sCorporation = "S Corporation";
    private String fzoApplication = "501 Application";
    
    private String newFedFormsCreation = "newFederalFormCreation";
    private String newSCorpFormsCreation = "newSCorpFormCreation";
    private String newFiveZeroOneFormsCreation = "newFiveZeroOneFormCreation";
    private String radioButtStatus = "RadioButtonStatus";
    private String attrbReqTypeIdsInSn = "attrReqTypeIdsInSn";
    private String totalReqAttrbCountInSn = "totalReqAttrCountInSn";
    
    private String alreadyHaveChoice = "AlreadyExitChoice";
    private String alreadyHaveUserChoice = "AlreadyExitUserChoice";
   
    private String inProgress = "In Progress";
    private String readyForCheckout = "Ready for checkout";     
    private String fzoAttrbNames = "FZO_Attr_Names";
    private String fzoAttrbValues = "FZO_Attr_Values";
    private String fzoAttrbReqList = "FZO_Attr_Req_List";
    private String fzoAttrbStatusList = "FZO_Attr_Status_List";    
    private String sfAttrbNames = "SFed_Attr_Names";
    private String sfAttrbValues = "SFed_Attr_Values";
    private String sfAttrbReqList = "SFed_Attr_Req_List";
    private String sfAttrbStatusList = "SFed_Attr_Status_List";    
    private String scAttrbNames = "SCorp_Attr_Names";
    private String scAttrbValues = "SCorp_Attr_Values";
    private String scAttrbReqList = "SCorp_Attr_Req_List";
    private String scAttrbStatusList = "SCorp_Attr_Status_List";
    private String fedFormPageValues = "federalFormPageValues";
    private String sCorpFormPageVal = "sCorpFormPageValues";    
    private String fzoFormPageVal = "fiveZeroOneFormPageValues";
    private String fedFinishOrderRef = "federalDocFinishOrderRef";
    private String scFinishOrderRef = "sCorpFinishOrderRef";
    private String fzoFinishOrderRef = "fiveZeroOneFinishOrderRef";
    private String finishedStatus = "Finished";
    
    private String ffAttrbNames = "SFed_Attr_Names";
    private String ffAttrbValues = "SFed_Attr_Values";
    private String ffAttrbReqList = "SFed_Attr_Req_List";
    private String ffAttrbStatusList = "SFed_Attr_Status_List";
    private String freeFedTaxModification = "freeFederalTaxIdModification";
    private String freeFedFormPageValue = "freeFederalFormPageValues";
    private String freeFedRadioButtStatus = "FreeFedRadioButtonStatus";
    private String freeFedAttrTextFieldList = "freeFedAttributeTextFieldList";
    private String freeFedAttrTextFieldDateList = "freeFedAttributeTextFieldDateList";
    private String freeFedAttrSelectBoxList = "freeFedAttributeSelectBoxList";
    private String freeFedRBList = "freeFedRadioButtonList";
    private String freeFedCBList = "freeFedCheckBoxList";    
    private String ffLegalNameInSn = "freeLegalNameInSn";
    private String freeFedDocFinishOrderRef = "freeFederalDocFinishOrderRef";
    
    private String adminUserNameInSn = "adminUserNameInSn";
    private String ipAddress = "https://www.legalnod.com/LegalNodPDF/";
       
	@Autowired
    private AllStateFormsDataSavingDAOImpl allStateFormsDataSavingDAOImpl;
	
	@Autowired
    private AllFederalFormsDataSavingDAOImpl allFederalFormsDataSavingDAOImpl;
	
	@Autowired
    private FormFederalDocumentsDataSavingDAOImpl formFederalDataSavingDAOImpl;
	
	@Autowired
    private AdminHomeServiceImpl adminHomeServiceImpl;
	
	@Autowired
    private NameAvailabilityCheckSavingDAOImpl nameAvailabilityCheckSavingDAOImpl;	
	
	@Autowired
    private UsersInformationDAOImpl usersInformationDAOImpl;
	
	@Autowired
    private BusinessFormsAttributesInfoDAOImpl bsFormAndStateAttrInfoDAOImpl;

    @Autowired
    private BusinessFormsAttributesAndValuesSavingDAOImpl busFormAttrAndValDAOImpl;	
	
	@Autowired
    private AdditionalFormsAttributesInfoDAOImpl additionalFormsAttrInfoDAOImpl;

    @Autowired
    private AdditionalFormsAttributesAndValuesSavingDAOImpl addSerFormAttrAndValSavingDAOImpl;
    
    @Autowired
    private StateTaxFormsAttributesInfoDAOImpl stateTaxFormsAttrInfoDAOImpl;
	
	@Autowired
    private StateTaxFormsAttributesAndValuesSavingDAOImpl stateTaxAttrAndValuesSavingDAOImpl;
	
	@Autowired
    private AllStateFormsCheckoutPaymentAndUserContactSavingDAOImpl allStateFormsCheckoutPaymentDAOImpl;
	
	@Autowired
    private AllStateFormsPaymentInfoSavingDAOImpl allStateFormsPaymentSavingDAOImpl;
	
	@Autowired
    private FederalFormsPriceInfoDAOImpl federalFormsPriceInfoDAOImpl;
	
	@Autowired
    private StateTaxFormsAttributesAndValuesSavingDAOImpl stateTaxFormsAttrAndValuesSavingDAOImpl;
	
	@Autowired
    private AllFederalFormsCheckoutPaymentAndUserContactSavingDAOImpl allFedCheckoutPaymentAndContactDAOImpl;
	
	@Autowired
    private FederalTaxIdFormsAttributesInfoDAOImpl federalTaxAttrInfoDAOImpl;
    
    @Autowired
    private FederalTaxIdFormsAttributesAndValuesSavingDAOImpl federalFormsAttrAndValuesDAOImpl;

    @Autowired
    private ScorporationFormsAttributesInfoDAOImpl sCorpAttrInfoDAOImpl;

    @Autowired
    private ScorporationFormsAttributesAndValuesSavingDAOImpl scorpoFormsAttrAndValuesDAOImpl;

    @Autowired
    private FivezerooneAppFormsAttributesInfoDAOImpl fiveZeroOneAttrInfoDAOImpl;

    @Autowired
    private FivezerooneAppFormsAttributesAndValuesSavingDAOImpl fiveZOFormsAttrAndValuesDAOImpl;
    
        
    @Autowired
    private AllFederalFormsPaymentInfoSavingDAOImpl allFederalFormsPaymentInfoDAOImpl;
    
    @Autowired
    private FormFederalAttributesAndValuesSavingDAOImpl formFedAttrAndValuesSavingDAOImpl;
    
    @Autowired
    private CompletedStateFormsFileUploadSavingDAOImpl compStateFormsFileUploadSavingDAOImpl;
    
    @Autowired
    private CompletedFederalFormsFileUploadSavingDAOImpl compFedFormsFileUploadSavingDAOImpl;
    
    @Autowired
    private PDFDocumentsInfoDAOImpl pdfDocumentsInfoDAOImpl;
    
	
//	completed Forms Redirection ServcompletedImplementation
	
	@Override
	@Transactional
	public ModelAndView completedFormsRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("completedFormsRedirection...Service");		
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute(adminUserNameInSn) != null){
		List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsDataDisplayInAdmin();
		List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsSavingInfoList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsDataDisplayInAdmin();
        List<FormFederalDocumentsDataSaving> compFreeFedFormsDataList = formFederalDataSavingDAOImpl.completedFreeFederalFormsDataDisplayInAdmin();
        List<NameAvailabilityCheckSaving> compNameAvaCheckDataList = nameAvailabilityCheckSavingDAOImpl.completedNameAvailabilityCheckDataDisplayInAdmin();
                
        if (!compStateFormsDataList.isEmpty()) {
            mav = completedStateFormsRedirection(req, sn);
        } else if (!compFederalFormsSavingInfoList.isEmpty() && compStateFormsDataList.isEmpty()) {
            mav = completedFederalFormsRedirection(req, sn);
        } else if (!compFreeFedFormsDataList.isEmpty()) {
            mav = completedFreeFederalFormsRedirection(req, sn);
        } else if (!compNameAvaCheckDataList.isEmpty()) {
            mav = completedNameAvaCheckFormsRedirection(req, sn);
        } else{        	
        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);
        }
        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		}
        return mav;
    }
	
//	completed State Forms Redirection Service Implementation
	
	@Override
	@Transactional
	public ModelAndView completedStateFormsRedirection(HttpServletRequest req, HttpSession sn) {		
		LOGGER.debug("completedFormsRedirection...Service");		
		ModelAndView mav = new ModelAndView();		
		if(sn.getAttribute(adminUserNameInSn) != null){
		List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsDataDisplayInAdmin();
		List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsOrderProcessedDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsOrderProcessedDataRedirection();
		List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsSignatureDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsSignatureRedirection();
		List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDocFiledDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsDocFiledRedirection();
		List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDocAcceptedDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsDocAcceptedRedirection();
		List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDocEmailedDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsDocEmailedRedirection();
		
        if (!compStateFormsDataList.isEmpty()) {
            List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormSavingList = new ArrayList<AllStateFormsCheckoutPaymentAndUserContactSaving>();
            for (AllStateFormsCheckoutPaymentAndUserContactSaving compStateFormsInfo : compStateFormsDataList) {
            	AllStateFormsCheckoutPaymentAndUserContactSaving compStateFormsInfoModel = new AllStateFormsCheckoutPaymentAndUserContactSaving();
                compStateFormsInfoModel.setUserName(compStateFormsInfo.getUserName());
                compStateFormsInfoModel.setStateName(compStateFormsInfo.getStateName());
                compStateFormsInfoModel.setFormName(compStateFormsInfo.getFormName());
                compStateFormsInfoModel.setUserChoice(compStateFormsInfo.getUserChoice());
                compStateFormsInfoModel.setTypeOfDocument(compStateFormsInfo.getTypeOfDocument());
                compStateFormsInfoModel.setAllStateFormsCheckoutPaymentAndUserContactSavingId(compStateFormsInfo.getAllStateFormsCheckoutPaymentAndUserContactSavingId());
                Timestamp lastEditedDate = null;
                if (compStateFormsInfo.getModifiedDate() != null) {
                    lastEditedDate = compStateFormsInfo.getModifiedDate();
                } else {
                    lastEditedDate = compStateFormsInfo.getCreatedDate();
                }
                compStateFormsInfoModel.setCreatedDate(lastEditedDate);

                compStateFormSavingList.add(compStateFormsInfoModel);
            }           
            mav = new ModelAndView("completedStateForms");
            mav.addObject("compStateFormSavingList", compStateFormSavingList);
            mav.addObject(compStateFormsOrderReceivedCount, compStateFormsDataList.size());
            mav.addObject(compStateFormsSignatureCount, compStateFormsSignatureDataList.size());
            mav.addObject(compStateFormsDocFiledCount, compStateFormsDocFiledDataList.size());
            mav.addObject(compStateFormsDocAcceptedCount, compStateFormsDocAcceptedDataList.size());
            mav.addObject(compStateFormsDocEmailedCount, compStateFormsDocEmailedDataList.size());
            mav.addObject(compStateFormsOrderProcessedCount, compStateFormsOrderProcessedDataList.size());
        } else{
        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);
        }        
        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		}
        return mav;
    }
	
//	completed Federal Forms Redirection Service Implementation
	
	@Override
	@Transactional
	public ModelAndView completedFederalFormsRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("completedFederalFormsRedirection...Service");		
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute(adminUserNameInSn) != null){
		List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsSavingInfoList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsDataDisplayInAdmin();
		List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsOrderProcessedDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsOrderProcessedDataRedirection();
		List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsSignatureDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsSignatureRedirection();
		List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsDocFiledDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsDocFiledRedirection();
		List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsDocAcceptedDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsDocAcceptedRedirection();
		List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsDocEmailedDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsDocEmailedRedirection();
        
        if (!compFederalFormsSavingInfoList.isEmpty()) {
            List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFedFormSavingList = new ArrayList<AllFederalFormsCheckoutPaymentAndUserContactSaving>();

            for (AllFederalFormsCheckoutPaymentAndUserContactSaving compFederalFormsInfo : compFederalFormsSavingInfoList) {
            	AllFederalFormsCheckoutPaymentAndUserContactSaving compFederalFormsInfoModel = new AllFederalFormsCheckoutPaymentAndUserContactSaving();
                compFederalFormsInfoModel.setUserName(compFederalFormsInfo.getUserName());
                compFederalFormsInfoModel.setFormStatus(compFederalFormsInfo.getFormStatus());
                compFederalFormsInfoModel.setLegalName(compFederalFormsInfo.getLegalName());
                compFederalFormsInfoModel.setAllFederalFormsCheckoutPaymentAndUserContactSavingId(compFederalFormsInfo.getAllFederalFormsCheckoutPaymentAndUserContactSavingId());
                Timestamp lastEditedDate = null;
                if (compFederalFormsInfo.getModifiedDate() != null) {
                    lastEditedDate = compFederalFormsInfo.getModifiedDate();
                } else {
                    lastEditedDate = compFederalFormsInfo.getCreatedDate();
                }
                compFederalFormsInfoModel.setCreatedDate(lastEditedDate);

                compFedFormSavingList.add(compFederalFormsInfoModel);
            }
            mav = new ModelAndView("completedFederalForms");
            mav.addObject("compFederalFormSavingList", compFedFormSavingList);            
            
            mav.addObject(compFederalFormsOrderReceivedCount, compFederalFormsSavingInfoList.size());
            mav.addObject(compFederalFormsOrderProcessedCount, compFederalFormsOrderProcessedDataList.size());
            mav.addObject(compFederalFormsSignatureCount, compFederalFormsSignatureDataList.size());
            mav.addObject(compFederalFormsDocFiledCount, compFederalFormsDocFiledDataList.size());
            mav.addObject(compFederalFormsDocAcceptedCount, compFederalFormsDocAcceptedDataList.size());
            mav.addObject(compFederalFormsDocEmailedCount, compFederalFormsDocEmailedDataList.size());            
        } else{
        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);
        }
        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		}
        return mav;
    }
	
//	completed Free Federal Forms Redirection Service Implementation
	
	@Override
	@Transactional
	public ModelAndView completedFreeFederalFormsRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("completedFreeFederalFormsRedirection...Service");		
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute(adminUserNameInSn) != null){
		List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsDataDisplayInAdmin();
		List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsSavingInfoList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsDataDisplayInAdmin();
        List<FormFederalDocumentsDataSaving> compFreeFedFormsDataList = formFederalDataSavingDAOImpl.completedFreeFederalFormsDataDisplayInAdmin();
        List<NameAvailabilityCheckSaving> compNameAvaCheckDataList = nameAvailabilityCheckSavingDAOImpl.completedNameAvailabilityCheckDataDisplayInAdmin();
        
        if (!compFreeFedFormsDataList.isEmpty()) {
            List<FormFederalDocumentsDataSaving> compFreeFedFormSavingList = new ArrayList<FormFederalDocumentsDataSaving>();
            for (FormFederalDocumentsDataSaving compFreeFedFormsInfo : compFreeFedFormsDataList) {
                FormFederalDocumentsDataSaving compFreeFedFormsInfoModel = new FormFederalDocumentsDataSaving();
                compFreeFedFormsInfoModel.setUserName(compFreeFedFormsInfo.getUserName());
                compFreeFedFormsInfoModel.setStateName(compFreeFedFormsInfo.getStateName());
                compFreeFedFormsInfoModel.setFormName(compFreeFedFormsInfo.getFormName());
                compFreeFedFormsInfoModel.setUserChoice(compFreeFedFormsInfo.getUserChoice());
                compFreeFedFormsInfoModel.setLegalName(compFreeFedFormsInfo.getLegalName());
                compFreeFedFormsInfoModel.setFormFederalDocumentsDataSavingId(compFreeFedFormsInfo.getFormFederalDocumentsDataSavingId());
                Timestamp lastEditedDate = null;
                if (compFreeFedFormsInfo.getModifiedDate() != null) {
                    lastEditedDate = compFreeFedFormsInfo.getModifiedDate();
                } else {
                    lastEditedDate = compFreeFedFormsInfo.getCreatedDate();
                }
                compFreeFedFormsInfoModel.setCreatedDate(lastEditedDate);

                compFreeFedFormSavingList.add(compFreeFedFormsInfoModel);
            }           
            mav = new ModelAndView("completedFreeFederalForms");
            mav.addObject("compFreeFedFormSavingList", compFreeFedFormSavingList);
            mav.addObject(compStateFormsOrderReceivedCount, compStateFormsDataList.size());
            mav.addObject(completedFedFormsCount, compFederalFormsSavingInfoList.size());
            mav.addObject(completedFreeFedFormsCount, compFreeFedFormsDataList.size());
            mav.addObject(completedNameAvaCheckCount, compNameAvaCheckDataList.size());
        } else{
        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);
        }
        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		}
        return mav;
    }
	
//	completed Name Availability Check Redirection Service Implementation
	
	@Override
	@Transactional
	public ModelAndView completedNameAvaCheckFormsRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("completedNameAvaCheckFormsRedirection...Service");		
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute(adminUserNameInSn) != null){
		List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsDataDisplayInAdmin();
		List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsSavingInfoList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsDataDisplayInAdmin();
        List<FormFederalDocumentsDataSaving> compFreeFedFormsDataList = formFederalDataSavingDAOImpl.completedFreeFederalFormsDataDisplayInAdmin();
        List<NameAvailabilityCheckSaving> compNameAvaCheckDataList = nameAvailabilityCheckSavingDAOImpl.completedNameAvailabilityCheckDataDisplayInAdmin();
        
        if (!compNameAvaCheckDataList.isEmpty()) {
            List<NameAvailabilityCheckSaving> compNameAvaCheckSavingList = new ArrayList<NameAvailabilityCheckSaving>();
            for (NameAvailabilityCheckSaving compNameAvaCheckInfo : compNameAvaCheckDataList) {
            	NameAvailabilityCheckSaving compNameAvaCheckInfoModel = new NameAvailabilityCheckSaving();
                compNameAvaCheckInfoModel.setUserEmail(compNameAvaCheckInfo.getUserEmail());
                compNameAvaCheckInfoModel.setBusinessState(compNameAvaCheckInfo.getBusinessState());
                compNameAvaCheckInfoModel.setCompanyForming(compNameAvaCheckInfo.getCompanyForming());
                compNameAvaCheckInfoModel.setBusinessName(compNameAvaCheckInfo.getBusinessName());                
                compNameAvaCheckInfoModel.setCreatedDate(compNameAvaCheckInfo.getCreatedDate());
                compNameAvaCheckInfoModel.setStatus(compNameAvaCheckInfo.getStatus());
                compNameAvaCheckInfoModel.setNameAvailabilityCheckSavingId(compNameAvaCheckInfo.getNameAvailabilityCheckSavingId());
                
                compNameAvaCheckSavingList.add(compNameAvaCheckInfoModel);
            }           
            mav = new ModelAndView("nameAvailabilityCheck");
            mav.addObject("compNameAvaCheckSavingList", compNameAvaCheckSavingList);
            mav.addObject(compStateFormsOrderReceivedCount, compStateFormsDataList.size());
            mav.addObject(completedFedFormsCount, compFederalFormsSavingInfoList.size());
            mav.addObject(completedFreeFedFormsCount, compFreeFedFormsDataList.size());
            mav.addObject(completedNameAvaCheckCount, compNameAvaCheckDataList.size());
        } else{        	
        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);
        }
        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		}
        return mav;
    }
	
//	completed Name Availability Check Data Operations Service Implementation
	
	@Override
	@Transactional
	public ModelAndView nameAvailabilityCheckDataOperations(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("nameAvailabilityCheckDataOperations...Service");		
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute(adminUserNameInSn) != null){
		String nameAvaCheckRefName = req.getParameter("nameAvaCheckRefType");
		
		String[] comValue = (req.getParameter("formName")).split(",&, ");
    	sn.setAttribute(userEmailInSn, comValue[0]);
    	sn.setAttribute(businessStateInSn, comValue[1]);
    	sn.setAttribute(companyFormingInSn, comValue[2]);
    	sn.setAttribute(businessNameInSn, comValue[3]);
    	sn.setAttribute("NameAvaCheckIDInSn", comValue[4]);
		
		if(("FormDeletion").equals(nameAvaCheckRefName)){
        	mav = nameAvailabilityCheckStatusDeletion(req, sn);
		} else if(("Complete").equals(nameAvaCheckRefName)){
			mav = nameAvailabilityCheckStatusUpdation(req, sn);
		} else if(("ViewPDF").equals(nameAvaCheckRefName)){
			mav = nameAvailabilityCheckFormsViewPDFRedirection(req, sn);
		}
		mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		}
        return mav;
    }
	
//	Name Availability Check Status Updation Service Implementation
	
	@Override
	@Transactional
	public ModelAndView nameAvailabilityCheckStatusUpdation(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("nameAvailabilityCheckStatusUpdation...Service");		
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute(adminUserNameInSn) != null){
		NameAvailabilityCheckSaving nameAvailabilityCheckModel = nameAvailabilityCheckSavingDAOImpl.nameAvailabilityCheckDataFromDB((String) sn.getAttribute(userEmailInSn), (String) sn.getAttribute(businessStateInSn), (String) sn.getAttribute(companyFormingInSn), (String) sn.getAttribute(businessNameInSn));		
		nameAvailabilityCheckModel.setStatus("Completed");        
		nameAvailabilityCheckSavingDAOImpl.merge(nameAvailabilityCheckModel);
		
        	mav = completedNameAvaCheckFormsRedirection(req, sn);
        	mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		}
        return mav;
    }
	
//	Name Availability Check Status Delete Service Implementation
	
	@Override
	@Transactional
	public ModelAndView nameAvailabilityCheckStatusDeletion(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("nameAvailabilityCheckStatusDeletion...Service");		
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute(adminUserNameInSn) != null){
		NameAvailabilityCheckSaving nameAvailabilityCheckModel = nameAvailabilityCheckSavingDAOImpl.nameAvailabilityCheckDataFromDB((String) sn.getAttribute(userEmailInSn), (String) sn.getAttribute(businessStateInSn), (String) sn.getAttribute(companyFormingInSn), (String) sn.getAttribute(businessNameInSn));		    
		nameAvailabilityCheckSavingDAOImpl.delete(nameAvailabilityCheckModel);
		  	mav = completedNameAvaCheckFormsRedirection(req, sn);
		  	mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		}
        return mav;
    }
	
//	Completed State Forms Check out Display
	
	//	All Forms Completed Checkout Data Display Finish Order
	    @Override
	    @Transactional
	    public ModelAndView compAllStateFormsDataOperationsFinishOrder(HttpServletRequest req, HttpSession sn) {
	    	LOGGER.debug("allStateFormsDataOperationsFinishOrder...Service");	    	
	    	ModelAndView mav = new ModelAndView();
	    	
			if(sn.getAttribute(adminUserNameInSn) != null){
	    	String allCompStateFormsRefType = req.getParameter("allCompStateFormsRefType");
	    	
	    	String[] comValue = (req.getParameter("formName")).split(",&, ");
	    	sn.setAttribute(userNameInSn, comValue[0]);
	    	sn.setAttribute(stateNameInSn, comValue[1]);
	    	sn.setAttribute(formNameInSn, comValue[2]);
	    	sn.setAttribute(userChoiceInSn, comValue[3]);
	    	sn.setAttribute(typeOfDocumentInSn, comValue[4]);
	    	sn.setAttribute("stateFormIdInSn", comValue[5]);
	    	if(("Order Info").equals(allCompStateFormsRefType)) {
	    		mav = allCompStateFormsOrderInfoRedirection(req, sn);
	    	} else if(("View PDF").equals(allCompStateFormsRefType)) {
	    		mav = allCompStateFormsViewPDFRedirection(req, sn);
	    	} else if(("Order Processed").equals(allCompStateFormsRefType)) {	    		
	    		mav = allCompStateFormsOrderProcessedStatusUpdation(req, sn);
	    	}
	    	mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
			}
	    	return mav;
	    }
	    
//		All Completed State Forms Order Info Redirection
	    @Override
	    @Transactional
	    public ModelAndView allCompStateFormsOrderInfoRedirection(HttpServletRequest req, HttpSession sn) {
	    	LOGGER.debug("allCompStateFormsOrderInfoRedirection...Service");
	    	ModelAndView mav = new ModelAndView();
	    	
			if(sn.getAttribute(adminUserNameInSn) != null){
	    	if ((businessForm).equals((String) sn.getAttribute(typeOfDocumentInSn))) {	        
	            mav = compBusinessStateFormsCheckouDataDisplay(req, sn);	            
	        } else if ((additionalForm).equals((String) sn.getAttribute(typeOfDocumentInSn))) {
	            mav = compAdditionalServiceFormsCheckouDataDisplay(req, sn);
	        } else if ((stateTaxIdForm).equals((String) sn.getAttribute(typeOfDocumentInSn))) {
	            mav = compStateTaxIdFormsCheckouDataDisplay(req, sn);
	        }
	    	mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
			}
    	return mav;
    }
	    
//		All Completed State Forms View PDFs Redirection
	    @Override
	    @Transactional
	    public ModelAndView allCompStateFormsViewPDFRedirection(HttpServletRequest req, HttpSession sn) {
	    	LOGGER.debug("allCompStateFormsViewPDFRedirection...Service");
	    	ModelAndView mav = new ModelAndView();
	    	
	    	if(sn.getAttribute(adminUserNameInSn) != null){
	    	List<PDFDocumentsInfo> pdfDocumentsInfoList = pdfDocumentsInfoDAOImpl.compStateFormsPDFDocChecking((String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(formNameInSn));
	    	if(pdfDocumentsInfoList.isEmpty()) {
	    		mav = completedStateFormsRedirection(req, sn);
	    		mav.addObject("pdfDocumentsCpont", "No");	
	    	} else{
	    		String stateFormId = (String) sn.getAttribute("stateFormIdInSn");
				mav = redirectToStruts2AdminModuleForStateForms(sn, stateFormId);
	    	mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));	    	
			} 
	    	}
    	return mav;
	    }
	    
//		Name Availability Check Forms View PDFs Redirection
	    @Override
	    @Transactional
	    public ModelAndView nameAvailabilityCheckFormsViewPDFRedirection(HttpServletRequest req, HttpSession sn) {
	    	LOGGER.debug("nameAvailabilityCheckFormsViewPDFRedirection...Service");
	    	ModelAndView mav = new ModelAndView();
	    	
	    	if(sn.getAttribute(adminUserNameInSn) != null){	    	
	    		String nameAvailabilityCheckId = (String) sn.getAttribute("NameAvaCheckIDInSn");
				mav = redirectToStruts2AdminModuleForNameAvailabilityCheckForms(sn, nameAvailabilityCheckId);
	    	mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));	    	
			}
    	return mav;
	    }
	    
//		All Completed Federal Forms View PDFs Redirection
	    @Override
	    @Transactional
	    public ModelAndView allCompFederalFormsViewPDFRedirection(HttpServletRequest req, HttpSession sn) {
	    	LOGGER.debug("allCompFederalFormsViewPDFRedirection...Service");
	    	ModelAndView mav = new ModelAndView();
	    	
	    	if(sn.getAttribute(adminUserNameInSn) != null){	    	
	    		String federalFormId = (String) sn.getAttribute("FederalFormIdInSn");
				mav = redirectToStruts2AdminModuleForFederalForms(sn, federalFormId);
	    	mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));	    	
			}
    	return mav;
	    }
	    
//		All Completed Free Federal Forms View PDFs Redirection
	    @Override
	    @Transactional
	    public ModelAndView allCompFreeFederalFormsViewPDFRedirection(HttpServletRequest req, HttpSession sn) {
	    	LOGGER.debug("allCompFreeFederalFormsViewPDFRedirection...Service");
	    	ModelAndView mav = new ModelAndView();
	    	
	    	if(sn.getAttribute(adminUserNameInSn) != null){	    	
	    		String freeFederalFormId = (String) sn.getAttribute("FreeFederalTaxIdInSn");
				mav = redirectToStruts2AdminModuleForFreeFederalForms(sn, freeFederalFormId);
	    	mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));	    	
			}
    	return mav;
	    }
	    
//		All Completed State Forms PDF Files redirection using Struts2	    
	    @RequestMapping(value = "/redirect", method = RequestMethod.POST)
	    public ModelAndView redirectToStruts2AdminModuleForStateForms(HttpSession session, String stateFormId) {
	    	String projectUrl = ipAddress+"viewPDFStateFormsRedirection.action?param=" + stateFormId;
	    	return new ModelAndView("redirect:" + projectUrl);
	    }
	    
//		All Completed Federal Forms PDF Files redirection using Struts2	    
	    @RequestMapping(value = "/redirect", method = RequestMethod.POST)
	    public ModelAndView redirectToStruts2AdminModuleForFederalForms(HttpSession session, String federalFormId) {    	
	    	String projectUrl = ipAddress+"viewPDFFederalFormsRedirection.action?param=" + federalFormId;
	    	return new ModelAndView("redirect:" + projectUrl);
	    }
	    
//		Name Availability Check Forms PDF Files redirection using Struts2	    
	    @RequestMapping(value = "/redirect", method = RequestMethod.POST)
	    public ModelAndView redirectToStruts2AdminModuleForNameAvailabilityCheckForms(HttpSession session, String nameAvailabilityCheckId) {    	
	    	String projectUrl = ipAddress+"viewPDFNameAvailabilityCheckFormRedirection.action?param=" + nameAvailabilityCheckId;
	    	return new ModelAndView("redirect:" + projectUrl);
	    }
	    
//		Completed Free Federal Forms PDF Files redirection using Struts2	    
	    @RequestMapping(value = "/redirect", method = RequestMethod.POST)
	    public ModelAndView redirectToStruts2AdminModuleForFreeFederalForms(HttpSession session, String freeFederalFormId) {    	
	    	String projectUrl = ipAddress+"viewPDFFreeFederalFormsRedirection.action?param=" + freeFederalFormId;
	    	return new ModelAndView("redirect:" + projectUrl);
	    }
	    
//		All Completed State Forms Order Processed Status Updation
	    @Override
	    @Transactional
	    public ModelAndView allCompStateFormsOrderProcessedStatusUpdation(HttpServletRequest req, HttpSession sn) {
	    	LOGGER.debug("allCompStateFormsOrderProcessedStatusUpdation...Service");
	    	ModelAndView mav = new ModelAndView();
	    	
			if(sn.getAttribute(adminUserNameInSn) != null){
			String[] comValue = (req.getParameter("formName")).split(",&, ");
		    sn.setAttribute(userNameInSn, comValue[0]);
		    sn.setAttribute(stateNameInSn, comValue[1]);
		    sn.setAttribute(formNameInSn, comValue[2]);
		    sn.setAttribute(userChoiceInSn, comValue[3]);
		    sn.setAttribute(typeOfDocumentInSn, comValue[4]);
		    	
	    	Timestamp currentDate = currentDate();
	    	AllStateFormsCheckoutPaymentAndUserContactSaving allStCheckoutPaymentAndUserContact = allStateFormsCheckoutPaymentDAOImpl.findByallStateFormsDataFromDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(typeOfDocumentInSn), (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(userChoiceInSn));
	    	String status = allStCheckoutPaymentAndUserContact.getOrderProcessed();
	    	allStCheckoutPaymentAndUserContact.setOrderProcessed("Done");
            allStCheckoutPaymentAndUserContact.setOrderProcessedCreatedDate(currentDate);
            allStateFormsCheckoutPaymentDAOImpl.merge(allStCheckoutPaymentAndUserContact);
            if(status == null){
            mav = completedStateFormsOrderProcessedRedirection(req, sn);
            } else{
            mav = completedStateFormsRedirection(req, sn);
            mav.addObject("stFormStatus", "This document already processed");
            }
            mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
			}
    	return mav;
    }
	    
//		completed State Forms Order Processed Redirection Service Implementation		
		@Override
		@Transactional
		public ModelAndView completedStateFormsOrderProcessedRedirection(HttpServletRequest req, HttpSession sn) {
			LOGGER.debug("completedStateFormsOrderProcessedRedirection...Service");
			ModelAndView mav = new ModelAndView();
			
			if(sn.getAttribute(adminUserNameInSn) != null){
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsDataDisplayInAdmin();
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsOrderProcessedDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsOrderProcessedDataRedirection();
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsSignatureDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsSignatureRedirection();
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDocFiledDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsDocFiledRedirection();
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDocAcceptedDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsDocAcceptedRedirection();
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDocEmailedDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsDocEmailedRedirection();
			
	        if (!compStateFormsOrderProcessedDataList.isEmpty()) {
	            List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsOrderProcessedList = new ArrayList<AllStateFormsCheckoutPaymentAndUserContactSaving>();
	            for (AllStateFormsCheckoutPaymentAndUserContactSaving compStateFormsInfo : compStateFormsOrderProcessedDataList) {
	            	AllStateFormsCheckoutPaymentAndUserContactSaving compStateFormsInfoModel = new AllStateFormsCheckoutPaymentAndUserContactSaving();
	                compStateFormsInfoModel.setUserName(compStateFormsInfo.getUserName());
	                compStateFormsInfoModel.setStateName(compStateFormsInfo.getStateName());
	                compStateFormsInfoModel.setFormName(compStateFormsInfo.getFormName());
	                compStateFormsInfoModel.setUserChoice(compStateFormsInfo.getUserChoice());
	                compStateFormsInfoModel.setTypeOfDocument(compStateFormsInfo.getTypeOfDocument());
	                Timestamp lastEditedDate = null;
	                if (compStateFormsInfo.getModifiedDate() != null) {
	                    lastEditedDate = compStateFormsInfo.getModifiedDate();
	                } else {
	                    lastEditedDate = compStateFormsInfo.getCreatedDate();
	                }
	                compStateFormsInfoModel.setCreatedDate(lastEditedDate);

	                compStateFormsOrderProcessedList.add(compStateFormsInfoModel);
	            }           
	            mav = new ModelAndView("compStateFormsOrderProcessed");
	            mav.addObject("compStateFormsOrderProcessedList", compStateFormsOrderProcessedList);
	            mav.addObject(compStateFormsOrderReceivedCount, compStateFormsDataList.size());
	            mav.addObject(compStateFormsSignatureCount, compStateFormsSignatureDataList.size());
	            mav.addObject(compStateFormsDocFiledCount, compStateFormsDocFiledDataList.size());
	            mav.addObject(compStateFormsDocAcceptedCount, compStateFormsDocAcceptedDataList.size());
	            mav.addObject(compStateFormsDocEmailedCount, compStateFormsDocEmailedDataList.size());
	            mav.addObject(compStateFormsOrderProcessedCount, compStateFormsOrderProcessedDataList.size());	            
	        } else{
	        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);
	        }
	        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
			}
	        return mav;
	    }
		
//		All Completed State Forms Signature Status Updation
	    @Override
	    @Transactional
	    public ModelAndView allCompStateFormsSignatureStatusUpdation(HttpServletRequest req, HttpSession sn) {
	    	LOGGER.debug("allCompStateFormsSignatureStatusUpdation...Service");
	    	ModelAndView mav = new ModelAndView();
	    	
			if(sn.getAttribute(adminUserNameInSn) != null){
	    	String[] comValue = (req.getParameter("formName")).split(",&, ");
	    	sn.setAttribute(userNameInSn, comValue[0]);
	    	sn.setAttribute(stateNameInSn, comValue[1]);
	    	sn.setAttribute(formNameInSn, comValue[2]);
	    	sn.setAttribute(userChoiceInSn, comValue[3]);
	    	sn.setAttribute(typeOfDocumentInSn, comValue[4]);	    	
	    	
	    	Timestamp currentDate = currentDate();
	    	AllStateFormsCheckoutPaymentAndUserContactSaving allStCheckoutPaymentAndUserContact = allStateFormsCheckoutPaymentDAOImpl.findByallStateFormsDataFromDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(typeOfDocumentInSn), (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(userChoiceInSn));
            allStCheckoutPaymentAndUserContact.setSignature("Done");
            allStCheckoutPaymentAndUserContact.setSignatureCreatedDate(currentDate);
            allStateFormsCheckoutPaymentDAOImpl.merge(allStCheckoutPaymentAndUserContact);
            mav = completedStateFormsSignatureRedirection(req, sn);
    	mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
			}
    	return mav;
    }
	    
//		completed State Forms Signature Redirection Service Implementation		
		@Override
		@Transactional
		public ModelAndView completedStateFormsSignatureRedirection(HttpServletRequest req, HttpSession sn) {
			LOGGER.debug("completedStateFormsSignatureRedirection...Service");		
			ModelAndView mav = new ModelAndView();
	    	
			if(sn.getAttribute(adminUserNameInSn) != null){
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsDataDisplayInAdmin();
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsOrderProcessedDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsOrderProcessedDataRedirection();
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsSignatureDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsSignatureRedirection();
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDocFiledDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsDocFiledRedirection();
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDocAcceptedDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsDocAcceptedRedirection();
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDocEmailedDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsDocEmailedRedirection();
			
	        if (!compStateFormsSignatureDataList.isEmpty()) {
	            List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsSignatureList = new ArrayList<AllStateFormsCheckoutPaymentAndUserContactSaving>();
	            for (AllStateFormsCheckoutPaymentAndUserContactSaving compStateFormsInfo : compStateFormsSignatureDataList) {
	            	AllStateFormsCheckoutPaymentAndUserContactSaving compStateFormsInfoModel = new AllStateFormsCheckoutPaymentAndUserContactSaving();
	                compStateFormsInfoModel.setUserName(compStateFormsInfo.getUserName());
	                compStateFormsInfoModel.setStateName(compStateFormsInfo.getStateName());
	                compStateFormsInfoModel.setFormName(compStateFormsInfo.getFormName());
	                compStateFormsInfoModel.setUserChoice(compStateFormsInfo.getUserChoice());
	                compStateFormsInfoModel.setTypeOfDocument(compStateFormsInfo.getTypeOfDocument());
	                Timestamp lastEditedDate = null;
	                if (compStateFormsInfo.getModifiedDate() != null) {
	                    lastEditedDate = compStateFormsInfo.getModifiedDate();
	                } else {
	                    lastEditedDate = compStateFormsInfo.getCreatedDate();
	                }
	                compStateFormsInfoModel.setCreatedDate(lastEditedDate);

	                compStateFormsSignatureList.add(compStateFormsInfoModel);
	            }           
	            mav = new ModelAndView("compStateFormsSignature");
	            mav.addObject("compStateFormsSignatureList", compStateFormsSignatureList);
	            mav.addObject(compStateFormsOrderReceivedCount, compStateFormsDataList.size());
	            mav.addObject(compStateFormsSignatureCount, compStateFormsSignatureDataList.size());
	            mav.addObject(compStateFormsDocFiledCount, compStateFormsDocFiledDataList.size());
	            mav.addObject(compStateFormsDocAcceptedCount, compStateFormsDocAcceptedDataList.size());
	            mav.addObject(compStateFormsDocEmailedCount, compStateFormsDocEmailedDataList.size());
	            mav.addObject(compStateFormsOrderProcessedCount, compStateFormsOrderProcessedDataList.size());	            
	        } else{
	        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);
	        }
	        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
			}
	        return mav;
	    }  
		
//		All Completed State Forms Doc Filed Status Updation
	    @Override
	    @Transactional
	    public ModelAndView allCompStateFormsDocFiledStatusUpdation(HttpServletRequest req, HttpSession sn) {
	    	LOGGER.debug("allCompStateFormsDocFiledStatusUpdation...Service");
	    	ModelAndView mav = new ModelAndView();
	    	
			if(sn.getAttribute(adminUserNameInSn) != null){
	    	String[] comValue = (req.getParameter("formName")).split(",&, ");
	    	sn.setAttribute(userNameInSn, comValue[0]);
	    	sn.setAttribute(stateNameInSn, comValue[1]);
	    	sn.setAttribute(formNameInSn, comValue[2]);
	    	sn.setAttribute(userChoiceInSn, comValue[3]);
	    	sn.setAttribute(typeOfDocumentInSn, comValue[4]);
	    	
	    	Timestamp currentDate = currentDate();
	    	AllStateFormsCheckoutPaymentAndUserContactSaving allStCheckoutPaymentAndUserContact = allStateFormsCheckoutPaymentDAOImpl.findByallStateFormsDataFromDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(typeOfDocumentInSn), (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(userChoiceInSn));
            allStCheckoutPaymentAndUserContact.setDocFiled("Done");
            allStCheckoutPaymentAndUserContact.setDocFiledCreatedDate(currentDate);
            allStateFormsCheckoutPaymentDAOImpl.merge(allStCheckoutPaymentAndUserContact);
            mav = completedStateFormsDocFiledRedirection(req, sn);
    	mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
			}
    	return mav;
    }
	    
//		completed State Forms Doc Filed Redirection Service Implementation		
		@Override
		@Transactional
		public ModelAndView completedStateFormsDocFiledRedirection(HttpServletRequest req, HttpSession sn) {
			LOGGER.debug("completedStateFormsDocFiledRedirection...Service");		
			ModelAndView mav = new ModelAndView();
	    	
			if(sn.getAttribute(adminUserNameInSn) != null){
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsDataDisplayInAdmin();
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsOrderProcessedDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsOrderProcessedDataRedirection();
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsSignatureDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsSignatureRedirection();
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDocFiledDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsDocFiledRedirection();
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDocAcceptedDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsDocAcceptedRedirection();
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDocEmailedDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsDocEmailedRedirection();
			
	        if (!compStateFormsDocFiledDataList.isEmpty()) {
	            List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDocFiledList = new ArrayList<AllStateFormsCheckoutPaymentAndUserContactSaving>();
	            for (AllStateFormsCheckoutPaymentAndUserContactSaving compStateFormsInfo : compStateFormsDocFiledDataList) {
	            	AllStateFormsCheckoutPaymentAndUserContactSaving compStateFormsInfoModel = new AllStateFormsCheckoutPaymentAndUserContactSaving();
	                compStateFormsInfoModel.setUserName(compStateFormsInfo.getUserName());
	                compStateFormsInfoModel.setStateName(compStateFormsInfo.getStateName());
	                compStateFormsInfoModel.setFormName(compStateFormsInfo.getFormName());
	                compStateFormsInfoModel.setUserChoice(compStateFormsInfo.getUserChoice());
	                compStateFormsInfoModel.setTypeOfDocument(compStateFormsInfo.getTypeOfDocument());
	                Timestamp lastEditedDate = null;
	                if (compStateFormsInfo.getModifiedDate() != null) {
	                    lastEditedDate = compStateFormsInfo.getModifiedDate();
	                } else {
	                    lastEditedDate = compStateFormsInfo.getCreatedDate();
	                }
	                compStateFormsInfoModel.setCreatedDate(lastEditedDate);

	                compStateFormsDocFiledList.add(compStateFormsInfoModel);
	            }           
	            mav = new ModelAndView("compStateFormsDocFiled");
	            mav.addObject("compStateFormsDocFiledList", compStateFormsDocFiledList);
	            mav.addObject(compStateFormsOrderReceivedCount, compStateFormsDataList.size());
	            mav.addObject(compStateFormsSignatureCount, compStateFormsSignatureDataList.size());
	            mav.addObject(compStateFormsDocFiledCount, compStateFormsDocFiledDataList.size());
	            mav.addObject(compStateFormsDocAcceptedCount, compStateFormsDocAcceptedDataList.size());
	            mav.addObject(compStateFormsDocEmailedCount, compStateFormsDocEmailedDataList.size());
	            mav.addObject(compStateFormsOrderProcessedCount, compStateFormsOrderProcessedDataList.size());	            
	        } else{
	        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);
	        }
	        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
			}
	        return mav;
	    }  
		
//		All Completed State Forms Doc Accepted Status Updation
	    @Override
	    @Transactional
	    public ModelAndView allCompStateFormsDocAcceptedStatusUpdation(HttpServletRequest req, HttpSession sn) {
	    	LOGGER.debug("allCompStateFormsDocAcceptedStatusUpdation...Service");
	    	ModelAndView mav = new ModelAndView();
	    	
			if(sn.getAttribute(adminUserNameInSn) != null){
	    	String[] comValue = (req.getParameter("formName")).split(",&, ");
	    	sn.setAttribute(userNameInSn, comValue[0]);
	    	sn.setAttribute(stateNameInSn, comValue[1]);
	    	sn.setAttribute(formNameInSn, comValue[2]);
	    	sn.setAttribute(userChoiceInSn, comValue[3]);
	    	sn.setAttribute(typeOfDocumentInSn, comValue[4]);
	    	
	    	Timestamp currentDate = currentDate();
	    	AllStateFormsCheckoutPaymentAndUserContactSaving allStCheckoutPaymentAndUserContact = allStateFormsCheckoutPaymentDAOImpl.findByallStateFormsDataFromDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(typeOfDocumentInSn), (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(userChoiceInSn));
            allStCheckoutPaymentAndUserContact.setDocAccepted("Done");
            allStCheckoutPaymentAndUserContact.setDocAcceptedCreatedDate(currentDate);
            allStateFormsCheckoutPaymentDAOImpl.merge(allStCheckoutPaymentAndUserContact);
            mav = completedStateFormsDocAcceptedRedirection(req, sn);
    	mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
			}
    	return mav;
    }
	    
//		completed State Forms Doc Accepted Redirection Service Implementation		
		@Override
		@Transactional
		public ModelAndView completedStateFormsDocAcceptedRedirection(HttpServletRequest req, HttpSession sn) {
			LOGGER.debug("completedStateFormsDocAcceptedRedirection...Service");		
			ModelAndView mav = new ModelAndView();
	    	
			if(sn.getAttribute(adminUserNameInSn) != null){
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsDataDisplayInAdmin();
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsOrderProcessedDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsOrderProcessedDataRedirection();
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsSignatureDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsSignatureRedirection();
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDocFiledDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsDocFiledRedirection();
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDocAcceptedDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsDocAcceptedRedirection();
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDocEmailedDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsDocEmailedRedirection();
			
	        if (!compStateFormsDocAcceptedDataList.isEmpty()) {
	            List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDocAcceptedList = new ArrayList<AllStateFormsCheckoutPaymentAndUserContactSaving>();
	            for (AllStateFormsCheckoutPaymentAndUserContactSaving compStateFormsInfo : compStateFormsDocAcceptedDataList) {
	            	AllStateFormsCheckoutPaymentAndUserContactSaving compStateFormsInfoModel = new AllStateFormsCheckoutPaymentAndUserContactSaving();
	                compStateFormsInfoModel.setUserName(compStateFormsInfo.getUserName());
	                compStateFormsInfoModel.setStateName(compStateFormsInfo.getStateName());
	                compStateFormsInfoModel.setFormName(compStateFormsInfo.getFormName());
	                compStateFormsInfoModel.setUserChoice(compStateFormsInfo.getUserChoice());
	                compStateFormsInfoModel.setTypeOfDocument(compStateFormsInfo.getTypeOfDocument());
	                Timestamp lastEditedDate = null;
	                if (compStateFormsInfo.getModifiedDate() != null) {
	                    lastEditedDate = compStateFormsInfo.getModifiedDate();
	                } else {
	                    lastEditedDate = compStateFormsInfo.getCreatedDate();
	                }
	                compStateFormsInfoModel.setCreatedDate(lastEditedDate);

	                compStateFormsDocAcceptedList.add(compStateFormsInfoModel);
	            }           
	            mav = new ModelAndView("compStateFormsDocAccepted");
	            mav.addObject("compStateFormsDocAcceptedList", compStateFormsDocAcceptedList);
	            mav.addObject(compStateFormsOrderReceivedCount, compStateFormsDataList.size());
	            mav.addObject(compStateFormsSignatureCount, compStateFormsSignatureDataList.size());
	            mav.addObject(compStateFormsDocFiledCount, compStateFormsDocFiledDataList.size());
	            mav.addObject(compStateFormsDocAcceptedCount, compStateFormsDocAcceptedDataList.size());
	            mav.addObject(compStateFormsDocEmailedCount, compStateFormsDocEmailedDataList.size());
	            mav.addObject(compStateFormsOrderProcessedCount, compStateFormsOrderProcessedDataList.size());	            
	        } else{
	        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);
	        }
	        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
			}
	        return mav;
	    }  
		
//		All Completed State Forms Doc Emailed Status Updation
	    @Override
	    @Transactional
	    public ModelAndView allCompStateFormsDocEmailedStatusUpdation(HttpServletRequest req, HttpSession sn) {
	    	LOGGER.debug("allCompStateFormsDocEmailedStatusUpdation...Service");
	    	ModelAndView mav = new ModelAndView();
	    	
			if(sn.getAttribute(adminUserNameInSn) != null){
	    	String[] comValue = (req.getParameter("formName")).split(",&, ");
	    	sn.setAttribute(userNameInSn, comValue[0]);
	    	sn.setAttribute(stateNameInSn, comValue[1]);
	    	sn.setAttribute(formNameInSn, comValue[2]);
	    	sn.setAttribute(userChoiceInSn, comValue[3]);
	    	sn.setAttribute(typeOfDocumentInSn, comValue[4]);
	    	
	    	Timestamp currentDate = currentDate();
	    	AllStateFormsCheckoutPaymentAndUserContactSaving allStCheckoutPaymentAndUserContact = allStateFormsCheckoutPaymentDAOImpl.findByallStateFormsDataFromDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(typeOfDocumentInSn), (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(userChoiceInSn));
            allStCheckoutPaymentAndUserContact.setDocEmailed("Done");
            allStCheckoutPaymentAndUserContact.setDocEmailedCreatedDate(currentDate);
            allStateFormsCheckoutPaymentDAOImpl.merge(allStCheckoutPaymentAndUserContact);
            mav = completedStateFormsDocEmailedRedirection(req, sn);
    	mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
			}
    	return mav;
    }
	    
//		completed State Forms Doc Emailed Redirection Service Implementation		
		@Override
		@Transactional
		public ModelAndView completedStateFormsDocEmailedRedirection(HttpServletRequest req, HttpSession sn) {
			LOGGER.debug("completedStateFormsDocEmailedRedirection...Service");		
			ModelAndView mav = new ModelAndView();
	    	
			if(sn.getAttribute(adminUserNameInSn) != null){
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsDataDisplayInAdmin();
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsOrderProcessedDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsOrderProcessedDataRedirection();
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsSignatureDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsSignatureRedirection();
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDocFiledDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsDocFiledRedirection();
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDocAcceptedDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsDocAcceptedRedirection();
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDocEmailedDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsDocEmailedRedirection();
			
			List<CompletedStateFormsFileUploadSaving> completedStateFormsFileUploadList = compStateFormsFileUploadSavingDAOImpl.completedStateFormsUploadedDataSaving();
			
	        if (!compStateFormsDocEmailedDataList.isEmpty()) {
	            List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDocEmailedList = new ArrayList<AllStateFormsCheckoutPaymentAndUserContactSaving>();
	            for (AllStateFormsCheckoutPaymentAndUserContactSaving compStateFormsInfo : compStateFormsDocEmailedDataList) {
	            	AllStateFormsCheckoutPaymentAndUserContactSaving compStateFormsInfoModel = new AllStateFormsCheckoutPaymentAndUserContactSaving();
	                compStateFormsInfoModel.setUserName(compStateFormsInfo.getUserName());
	                compStateFormsInfoModel.setStateName(compStateFormsInfo.getStateName());
	                compStateFormsInfoModel.setFormName(compStateFormsInfo.getFormName());
	                compStateFormsInfoModel.setUserChoice(compStateFormsInfo.getUserChoice());	                
	                int invNum = compStateFormsInfo.getInvoiceNum();
	                String amount = compStateFormsInfo.getAmount();	                
	                compStateFormsInfoModel.setTypeOfDocument(compStateFormsInfo.getTypeOfDocument());
	                Timestamp lastEditedDate = null;
	                if (compStateFormsInfo.getModifiedDate() != null) {
	                    lastEditedDate = compStateFormsInfo.getModifiedDate();
	                } else {
	                    lastEditedDate = compStateFormsInfo.getCreatedDate();
	                }
	                compStateFormsInfoModel.setCreatedDate(lastEditedDate);

	                compStateFormsDocEmailedList.add(compStateFormsInfoModel);
	                sn.setAttribute("docAmountInSn", amount);
		            sn.setAttribute("docInvNoInSn", invNum);
	            }           
	            mav = new ModelAndView("compStateFormsDocEmailed");
	            mav.addObject("compStateFormsDocEmailedList", compStateFormsDocEmailedList);
	            mav.addObject(compStateFormsOrderReceivedCount, compStateFormsDataList.size());
	            mav.addObject(compStateFormsSignatureCount, compStateFormsSignatureDataList.size());
	            mav.addObject(compStateFormsDocFiledCount, compStateFormsDocFiledDataList.size());
	            mav.addObject(compStateFormsDocAcceptedCount, compStateFormsDocAcceptedDataList.size());
	            mav.addObject(compStateFormsDocEmailedCount, compStateFormsDocEmailedDataList.size());
	            mav.addObject(compStateFormsOrderProcessedCount, compStateFormsOrderProcessedDataList.size());
	            mav.addObject("compStateFormsFileUploadCount", completedStateFormsFileUploadList.size());
	        } else{
	        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);
	        }
	        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
			}
	        return mav;
	    }
		
//		completed State Forms File Upload Service Implementation		
		@Override
		@Transactional
		public ModelAndView completedStateFormsFileUpload(HttpServletRequest req, HttpSession sn) {
			LOGGER.debug("completedStateFormsFileUpload...Service");		
			ModelAndView mav = new ModelAndView();
	    	
			if(sn.getAttribute(adminUserNameInSn) != null){
			String[] comValue = (req.getParameter("formName")).split(",&, ");
	    	sn.setAttribute(userNameInSn, comValue[0]);
	    	sn.setAttribute(stateNameInSn, comValue[1]);
	    	sn.setAttribute(formNameInSn, comValue[2]);
	    	sn.setAttribute(userChoiceInSn, comValue[3]);
	    	sn.setAttribute(typeOfDocumentInSn, comValue[4]);
	    	
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsDataDisplayInAdmin();
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsOrderProcessedDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsOrderProcessedDataRedirection();
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsSignatureDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsSignatureRedirection();
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDocFiledDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsDocFiledRedirection();
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDocAcceptedDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsDocAcceptedRedirection();
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDocEmailedDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsDocEmailedRedirection();
			
	        if (!compStateFormsDocEmailedDataList.isEmpty()) {	                      
	            mav = new ModelAndView("compStateFormsFileUpload");
	            mav.addObject(compStateFormsOrderReceivedCount, compStateFormsDataList.size());
	            mav.addObject(compStateFormsSignatureCount, compStateFormsSignatureDataList.size());
	            mav.addObject(compStateFormsDocFiledCount, compStateFormsDocFiledDataList.size());
	            mav.addObject(compStateFormsDocAcceptedCount, compStateFormsDocAcceptedDataList.size());
	            mav.addObject(compStateFormsDocEmailedCount, compStateFormsDocEmailedDataList.size());
	            mav.addObject(compStateFormsOrderProcessedCount, compStateFormsOrderProcessedDataList.size());	            
	        } else{
	        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);
	        }
	        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
			}
	        return mav;
	    }
		

//		State forms JSON calling for File Upload Service
	    @Override
	    @Transactional
	    public JSONArray stateFormsfileUploadJSON(String selectedData, HttpSession sn) {
	    	LOGGER.debug("stateFormsfileUploadJSON...Service");
	    	sn.setAttribute("combValInSn", selectedData);	        
	        JSONArray forms = new JSONArray();		        
	        return forms;
	    }
	
//		completed State Forms File Upload Sending and Saving Service Implementation		
		@Override
		@Transactional
		public ModelAndView completedStateFormsFileUploadSendingAndSaving(MultipartFile file, String subject, String comments, HttpServletRequest req, HttpSession sn) throws Exception {
			LOGGER.debug("completedStateFormsFileUploadSendingAndSaving...Service");			
			ModelAndView mav = new ModelAndView();
	    	
	    	if(sn.getAttribute(adminUserNameInSn) != null){
			EmailSending emailer = new EmailSending();
			
			String userName = (String) sn.getAttribute(userNameInSn);
			String[] emailList = {userName};
			int userNameIndex = (userName).indexOf("@");
	        String userFirstName = (userName).substring(0, userNameIndex);
			
			String body = "<body><table style=' border: #458B00 solid; width:  600px; height:  auto; background-color:  #ECF1EF; margin-left: 100px;'>"
				    + "<table style=' background-color: #458B00; width:600px; height: 30px; margin-top: -20px'>"
				      
				    + "</table>"
				    + "<br>"
				    + "<div style='margin-left: 20px; margin-top: -100px; width: auto; height: auto'>"
				    + "<font size='2'>"
				    + "Dear "
				    + "<b>"
				    + userFirstName
				    + ",</b><br><br>"
				    + "Comments: "+ comments+""
				    + "<br>"
				    + "</font>"
				    + "</div>"
				    + "</table></body>";
				    String type = "text/html";
				    
				    try{				    
				    String fileName = file.getOriginalFilename();
				    File convFile = new File(req.getSession().getServletContext().getRealPath(fileName));
				    file.transferTo(convFile);	
				    	
				    String stateName = (String) sn.getAttribute(stateNameInSn);
				    String formName = (String) sn.getAttribute(formNameInSn);
				    String userChoice = (String) sn.getAttribute(userChoiceInSn);
				    String typeOfDocument = (String) sn.getAttribute(typeOfDocumentInSn);				    
				    String amount = (String)sn.getAttribute("docAmountInSn"); 
				    int orderNumber = (Integer)sn.getAttribute("docInvNoInSn");
				    
				    List compStFormsFileUploadList = compStateFormsFileUploadSavingDAOImpl.findByStateFormsFileUploadRowChecking(userName, typeOfDocument, formName, stateName, userChoice);
				    if(compStFormsFileUploadList.isEmpty()) {
				    	Timestamp currentDate = currentDate();				    	
				    	CompletedStateFormsFileUploadSaving compStFormsFileUploadModel = new CompletedStateFormsFileUploadSaving();
				    	compStFormsFileUploadModel.setUserName(userName);				    	
				    	compStFormsFileUploadModel.setTypeOfDocument(typeOfDocument);
				    	compStFormsFileUploadModel.setFormName(formName);
				    	compStFormsFileUploadModel.setStateName(stateName);
				    	compStFormsFileUploadModel.setUserChoice(userChoice);
				    	compStFormsFileUploadModel.setAmount(amount);
				    	compStFormsFileUploadModel.setOrderNumber(orderNumber);
				    	compStFormsFileUploadModel.setSubject(subject);
				    	compStFormsFileUploadModel.setComments(comments);
				    	compStFormsFileUploadModel.setFileUploadData(fileName);
				    	compStFormsFileUploadModel.setStatus("Done");
				    	compStFormsFileUploadModel.setCreatedDate(currentDate);
				    	compStateFormsFileUploadSavingDAOImpl.save(compStFormsFileUploadModel);
				    	
				    	AllStateFormsCheckoutPaymentAndUserContactSaving allStCheckoutPaymentAndUserContact = allStateFormsCheckoutPaymentDAOImpl.findByallStateFormsDataFromDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(typeOfDocumentInSn), (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(userChoiceInSn));
				    	allStCheckoutPaymentAndUserContact.setFileUploadStatus("Done");			            
			            allStateFormsCheckoutPaymentDAOImpl.merge(allStCheckoutPaymentAndUserContact);
				    }
				    emailer.sendEmailWithAttachment(emailList, subject, body, type, convFile, fileName);				    
				    }catch(Exception e){
				    }
	            mav = completedStateFormsFileUploadedDataDisplay(req, sn);
	            
	        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
			}
	        return mav;
	    }		
		
//		completed State Forms File Upload Saving data display Service Implementation		
		@Override
		@Transactional
		public ModelAndView completedStateFormsFileUploadedDataDisplay(HttpServletRequest req, HttpSession sn) {
			LOGGER.debug("completedStateFormsFileUploadedDataDisplay...Service");		
			ModelAndView mav = new ModelAndView();
			
			if(sn.getAttribute(adminUserNameInSn) != null){
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsDataDisplayInAdmin();
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsOrderProcessedDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsOrderProcessedDataRedirection();
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsSignatureDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsSignatureRedirection();
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDocFiledDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsDocFiledRedirection();
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDocAcceptedDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsDocAcceptedRedirection();
			List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDocEmailedDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsDocEmailedRedirection();
						
			List<CompletedStateFormsFileUploadSaving> completedStateFormsFileUploadList = compStateFormsFileUploadSavingDAOImpl.completedStateFormsUploadedDataSaving();
			
	        if (!completedStateFormsFileUploadList.isEmpty()) {
	        	List<CompletedStateFormsFileUploadSaving> compStateFormsFileUploadList = new ArrayList<CompletedStateFormsFileUploadSaving>();
	        	int count = 1;
	            for (CompletedStateFormsFileUploadSaving compStateFormsUploadInfo : completedStateFormsFileUploadList) {
	            	CompletedStateFormsFileUploadSaving compStateFileUploadInfoModel = new CompletedStateFormsFileUploadSaving();
	            	compStateFileUploadInfoModel.setUserName(compStateFormsUploadInfo.getUserName());
	            	compStateFileUploadInfoModel.setTypeOfDocument(compStateFormsUploadInfo.getTypeOfDocument());
	            	compStateFileUploadInfoModel.setFormName(compStateFormsUploadInfo.getFormName());
	            	compStateFileUploadInfoModel.setStateName(compStateFormsUploadInfo.getStateName());
	            	compStateFileUploadInfoModel.setUserChoice(compStateFormsUploadInfo.getUserChoice());
	            	compStateFileUploadInfoModel.setAmount(compStateFormsUploadInfo.getAmount());
	            	compStateFileUploadInfoModel.setOrderNumber(compStateFormsUploadInfo.getOrderNumber());
	            	compStateFileUploadInfoModel.setSubject(compStateFormsUploadInfo.getSubject());
	            	compStateFileUploadInfoModel.setComments(compStateFormsUploadInfo.getComments());
	            	compStateFileUploadInfoModel.setFileUploadData(compStateFormsUploadInfo.getFileUploadData());		                
	            	compStateFileUploadInfoModel.setCreatedDate(compStateFormsUploadInfo.getCreatedDate());
	            	compStateFileUploadInfoModel.setCompletedStateFormsFileUploadSavingId(count);

	                compStateFormsFileUploadList.add(compStateFileUploadInfoModel);
	                count++;
	            }         
	            mav = new ModelAndView("compStateFormsUploadDataSaving");
	            mav.addObject("compStateFormsFileUploadList", compStateFormsFileUploadList);
	            
	            mav.addObject(compStateFormsOrderReceivedCount, compStateFormsDataList.size());
	            mav.addObject(compStateFormsSignatureCount, compStateFormsSignatureDataList.size());
	            mav.addObject(compStateFormsDocFiledCount, compStateFormsDocFiledDataList.size());
	            mav.addObject(compStateFormsDocAcceptedCount, compStateFormsDocAcceptedDataList.size());
	            mav.addObject(compStateFormsDocEmailedCount, compStateFormsDocEmailedDataList.size());
	            mav.addObject(compStateFormsOrderProcessedCount, compStateFormsOrderProcessedDataList.size());
	            mav.addObject("compStateFormsFileUploadCount", completedStateFormsFileUploadList.size());
	        } else{
	        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);
	        }
	        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
			}
	        return mav;
	    }		
		
//		Completed Federal Forms Check out Display
		
		//	All Forms Completed Checkout Data Display Finish Order
		    @Override
		    @Transactional
		    public ModelAndView compAllFederalFormsDataOperationsFinishOrder(HttpServletRequest req, HttpSession sn) {
		    	LOGGER.debug("compAllFederalFormsDataOperationsFinishOrder...Service");	    	
		    	ModelAndView mav = new ModelAndView();
		    	
				if(sn.getAttribute(adminUserNameInSn) != null){
		    	String allCompFederalFormsRefType = req.getParameter("allCompFederalFormsRefType");
		    	
		    	String[] comValue = (req.getParameter("formName")).split(",&, ");
		    	sn.setAttribute(userNameInSn, comValue[0]);		    	
		    	sn.setAttribute(userChoiceInSn, comValue[1]);
		    	sn.setAttribute(typeOfDocumentInSn, comValue[2]);
		    	sn.setAttribute("FederalFormIdInSn", comValue[3]);
		    	if(("Order Info").equals(allCompFederalFormsRefType)) {
		    		mav = allCompFederalFormsOrderInfoRedirection(req, sn);
		    	} else if(("View PDF").equals(allCompFederalFormsRefType)) {
		    		mav = allCompFederalFormsViewPDFRedirection(req, sn);
		    	} else if(("Order Processed").equals(allCompFederalFormsRefType)) {	    		
		    		mav = allCompFederalFormsOrderProcessedStatusUpdation(req, sn);
		    	}
		    	mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
				}
		    	return mav;
		    }
		    
//			All Completed Federal Forms Order Info Redirection
		    @Override
		    @Transactional
		    public ModelAndView allCompFederalFormsOrderInfoRedirection(HttpServletRequest req, HttpSession sn) {
		    	LOGGER.debug("allCompFederalFormsOrderInfoRedirection...Service");
		    	ModelAndView mav = new ModelAndView();
		    	
				if(sn.getAttribute(adminUserNameInSn) != null){
		    	if ((federalTaxId).equals((String) sn.getAttribute(typeOfDocumentInSn))) {	        
		            mav = compFederalTaxIdFormsCheckouDataDisplay(req, sn);
		        } else if ((sCorporation).equals((String) sn.getAttribute(typeOfDocumentInSn))) {
		            mav = compSCorporationFormsCheckouDataDisplay(req, sn);
		        } else if ((fzoApplication).equals((String) sn.getAttribute(typeOfDocumentInSn))) {
		            mav = compFiveZeroOneFormsCheckouDataDisplay(req, sn);
		        }
	    	mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
				}
	    	return mav;
	    }
		    
//			All Completed Federal Forms Order Processed Status Updation
		    @Override
		    @Transactional
		    public ModelAndView allCompFederalFormsOrderProcessedStatusUpdation(HttpServletRequest req, HttpSession sn) {
		    	LOGGER.debug("allCompFederalFormsOrderProcessedStatusUpdation...Service");
		    	ModelAndView mav = new ModelAndView();
		    	
				if(sn.getAttribute(adminUserNameInSn) != null){
		    	String[] comValue = (req.getParameter("formName")).split(",&, ");
		    	sn.setAttribute(userNameInSn, comValue[0]);		    	
		    	sn.setAttribute(userChoiceInSn, comValue[1]);
		    	sn.setAttribute(typeOfDocumentInSn, comValue[2]);
		    			    	
		    	Timestamp currentDate = currentDate();
		    	AllFederalFormsCheckoutPaymentAndUserContactSaving allFedCheckoutPaymentAndUserContact = allFedCheckoutPaymentAndContactDAOImpl.findByallFederalFormsUserChoiceFromDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(typeOfDocumentInSn), (String) sn.getAttribute(userChoiceInSn));
		    	String status = allFedCheckoutPaymentAndUserContact.getOrderProcessed();
		    	allFedCheckoutPaymentAndUserContact.setOrderProcessed("Done");
		    	allFedCheckoutPaymentAndUserContact.setOrderProcessedCreatedDate(currentDate);
	            allFedCheckoutPaymentAndContactDAOImpl.merge(allFedCheckoutPaymentAndUserContact);
	            if(status == null){
	                mav = completedFederalFormsOrderProcessedRedirection(req, sn);
	                } else{
	                mav = completedFederalFormsRedirection(req, sn);
	                mav.addObject("fedFormStatus", "This document already processed");
	                }	              	
	    	mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
				}
	    	return mav;
	    }
		    
//			completed Federal Forms Order Processed Redirection Service Implementation		
			@Override
			@Transactional
			public ModelAndView completedFederalFormsOrderProcessedRedirection(HttpServletRequest req, HttpSession sn) {
				LOGGER.debug("completedFederalFormsOrderProcessedRedirection...Service");
				ModelAndView mav = new ModelAndView();
		    	
				if(sn.getAttribute(adminUserNameInSn) != null){
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsSavingInfoList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsDataDisplayInAdmin();
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsOrderProcessedDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsOrderProcessedDataRedirection();
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsSignatureDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsSignatureRedirection();
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsDocFiledDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsDocFiledRedirection();
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsDocAcceptedDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsDocAcceptedRedirection();
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsDocEmailedDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsDocEmailedRedirection();
				
		        if (!compFederalFormsOrderProcessedDataList.isEmpty()) {
		            List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsOrderProcessedList = new ArrayList<AllFederalFormsCheckoutPaymentAndUserContactSaving>();
		            for (AllFederalFormsCheckoutPaymentAndUserContactSaving compFederalFormsInfo : compFederalFormsOrderProcessedDataList) {
		                AllFederalFormsCheckoutPaymentAndUserContactSaving compFederalFormsInfoModel = new AllFederalFormsCheckoutPaymentAndUserContactSaving();
		                compFederalFormsInfoModel.setUserName(compFederalFormsInfo.getUserName());
		                compFederalFormsInfoModel.setFormStatus(compFederalFormsInfo.getFormStatus());
		                compFederalFormsInfoModel.setLegalName(compFederalFormsInfo.getLegalName());
		                Timestamp lastEditedDate = null;
		                if (compFederalFormsInfo.getModifiedDate() != null) {
		                    lastEditedDate = compFederalFormsInfo.getModifiedDate();
		                } else {
		                    lastEditedDate = compFederalFormsInfo.getCreatedDate();
		                }
		                compFederalFormsInfoModel.setCreatedDate(lastEditedDate);

		                compFederalFormsOrderProcessedList.add(compFederalFormsInfoModel);
		            }           
		            mav = new ModelAndView("compFederalFormsOrderProcessed");
		            mav.addObject("compFederalFormsOrderProcessedList", compFederalFormsOrderProcessedList);
		            
		            mav.addObject(compFederalFormsOrderReceivedCount, compFederalFormsSavingInfoList.size());
		            mav.addObject(compFederalFormsOrderProcessedCount, compFederalFormsOrderProcessedDataList.size());
		            mav.addObject(compFederalFormsSignatureCount, compFederalFormsSignatureDataList.size());
		            mav.addObject(compFederalFormsDocFiledCount, compFederalFormsDocFiledDataList.size());
		            mav.addObject(compFederalFormsDocAcceptedCount, compFederalFormsDocAcceptedDataList.size());
		            mav.addObject(compFederalFormsDocEmailedCount, compFederalFormsDocEmailedDataList.size());	            
		        } else{
		        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);
		        }
		        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
				}
		        return mav;
		    }
			
//			All Completed Federal Forms Signature Status Updation
		    @Override
		    @Transactional
		    public ModelAndView allCompFederalFormsSignatureStatusUpdation(HttpServletRequest req, HttpSession sn) {
		    	LOGGER.debug("allCompFederalFormsSignatureStatusUpdation...Service");
		    	ModelAndView mav = new ModelAndView();
		    	
				if(sn.getAttribute(adminUserNameInSn) != null){
		    	String[] comValue = (req.getParameter("formName")).split(",&, ");
		    	sn.setAttribute(userNameInSn, comValue[0]);		    	
		    	sn.setAttribute(userChoiceInSn, comValue[1]);
		    	sn.setAttribute(typeOfDocumentInSn, comValue[2]);
		    	
		    	Timestamp currentDate = currentDate();
		    	AllFederalFormsCheckoutPaymentAndUserContactSaving allFedCheckoutPaymentAndUserContact = allFedCheckoutPaymentAndContactDAOImpl.findByallFederalFormsUserChoiceFromDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(typeOfDocumentInSn), (String) sn.getAttribute(userChoiceInSn));
		    	allFedCheckoutPaymentAndUserContact.setSignature("Done");
		    	allFedCheckoutPaymentAndUserContact.setSignatureCreatedDate(currentDate);
		    	allFedCheckoutPaymentAndContactDAOImpl.merge(allFedCheckoutPaymentAndUserContact);
	            mav = completedFederalFormsSignatureRedirection(req, sn);
	    	mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
				}
	    	return mav;
	    }
		    
//			completed Federal Forms Signature Redirection Service Implementation		
			@Override
			@Transactional
			public ModelAndView completedFederalFormsSignatureRedirection(HttpServletRequest req, HttpSession sn) {
				LOGGER.debug("completedFederalFormsSignatureRedirection...Service");		
				ModelAndView mav = new ModelAndView();
		    	
				if(sn.getAttribute(adminUserNameInSn) != null){
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsSavingInfoList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsDataDisplayInAdmin();
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsOrderProcessedDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsOrderProcessedDataRedirection();
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsSignatureDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsSignatureRedirection();
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsDocFiledDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsDocFiledRedirection();
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsDocAcceptedDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsDocAcceptedRedirection();
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsDocEmailedDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsDocEmailedRedirection();
				
		        if (!compFederalFormsSignatureDataList.isEmpty()) {
		        	List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsSignatureList = new ArrayList<AllFederalFormsCheckoutPaymentAndUserContactSaving>();
		            for (AllFederalFormsCheckoutPaymentAndUserContactSaving compFederalFormsInfo : compFederalFormsSignatureDataList) {
		                AllFederalFormsCheckoutPaymentAndUserContactSaving compFederalFormsInfoModel = new AllFederalFormsCheckoutPaymentAndUserContactSaving();
		                compFederalFormsInfoModel.setUserName(compFederalFormsInfo.getUserName());
		                compFederalFormsInfoModel.setFormStatus(compFederalFormsInfo.getFormStatus());
		                compFederalFormsInfoModel.setLegalName(compFederalFormsInfo.getLegalName());
		                Timestamp lastEditedDate = null;
		                if (compFederalFormsInfo.getModifiedDate() != null) {
		                    lastEditedDate = compFederalFormsInfo.getModifiedDate();
		                } else {
		                    lastEditedDate = compFederalFormsInfo.getCreatedDate();
		                }
		                compFederalFormsInfoModel.setCreatedDate(lastEditedDate);

		                compFederalFormsSignatureList.add(compFederalFormsInfoModel);
		            }          
		            mav = new ModelAndView("compFederalFormsSignature");
		            mav.addObject("compFederalFormsSignatureList", compFederalFormsSignatureList);
		            
		            mav.addObject(compFederalFormsOrderReceivedCount, compFederalFormsSavingInfoList.size());
		            mav.addObject(compFederalFormsOrderProcessedCount, compFederalFormsOrderProcessedDataList.size());
		            mav.addObject(compFederalFormsSignatureCount, compFederalFormsSignatureDataList.size());
		            mav.addObject(compFederalFormsDocFiledCount, compFederalFormsDocFiledDataList.size());
		            mav.addObject(compFederalFormsDocAcceptedCount, compFederalFormsDocAcceptedDataList.size());
		            mav.addObject(compFederalFormsDocEmailedCount, compFederalFormsDocEmailedDataList.size());	            
		        } else{
		        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);
		        }
		        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
				}
		        return mav;
		    }  
			
//			All Completed Federal Forms Doc Filed Status Updation
		    @Override
		    @Transactional
		    public ModelAndView allCompFederalFormsDocFiledStatusUpdation(HttpServletRequest req, HttpSession sn) {
		    	LOGGER.debug("allCompFederalFormsDocFiledStatusUpdation...Service");
		    	ModelAndView mav = new ModelAndView();
		    	
				if(sn.getAttribute(adminUserNameInSn) != null){
		    	String[] comValue = (req.getParameter("formName")).split(",&, ");
		    	sn.setAttribute(userNameInSn, comValue[0]);		    	
		    	sn.setAttribute(userChoiceInSn, comValue[1]);
		    	sn.setAttribute(typeOfDocumentInSn, comValue[2]);
		    	
		    	Timestamp currentDate = currentDate();
		    	AllFederalFormsCheckoutPaymentAndUserContactSaving allFedCheckoutPaymentAndUserContact = allFedCheckoutPaymentAndContactDAOImpl.findByallFederalFormsUserChoiceFromDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(typeOfDocumentInSn), (String) sn.getAttribute(userChoiceInSn));
		    	allFedCheckoutPaymentAndUserContact.setDocFiled("Done");
		    	allFedCheckoutPaymentAndUserContact.setDocFiledCreatedDate(currentDate);
		    	allFedCheckoutPaymentAndContactDAOImpl.merge(allFedCheckoutPaymentAndUserContact);
	            mav = completedFederalFormsDocFiledRedirection(req, sn);
	    	mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
				}
	    	return mav;
	    }
		    
//			completed Federal Forms Doc Filed Redirection Service Implementation		
			@Override
			@Transactional
			public ModelAndView completedFederalFormsDocFiledRedirection(HttpServletRequest req, HttpSession sn) {
				LOGGER.debug("completedFederalFormsDocFiledRedirection...Service");		
				ModelAndView mav = new ModelAndView();
		    	
				if(sn.getAttribute(adminUserNameInSn) != null){
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsSavingInfoList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsDataDisplayInAdmin();
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsOrderProcessedDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsOrderProcessedDataRedirection();
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsSignatureDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsSignatureRedirection();
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsDocFiledDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsDocFiledRedirection();
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsDocAcceptedDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsDocAcceptedRedirection();
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsDocEmailedDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsDocEmailedRedirection();
				
		        if (!compFederalFormsDocFiledDataList.isEmpty()) {
		        	List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsDocFiledList = new ArrayList<AllFederalFormsCheckoutPaymentAndUserContactSaving>();
		            for (AllFederalFormsCheckoutPaymentAndUserContactSaving compFederalFormsInfo : compFederalFormsDocFiledDataList) {
		                AllFederalFormsCheckoutPaymentAndUserContactSaving compFederalFormsInfoModel = new AllFederalFormsCheckoutPaymentAndUserContactSaving();
		                compFederalFormsInfoModel.setUserName(compFederalFormsInfo.getUserName());
		                compFederalFormsInfoModel.setFormStatus(compFederalFormsInfo.getFormStatus());
		                compFederalFormsInfoModel.setLegalName(compFederalFormsInfo.getLegalName());
		                Timestamp lastEditedDate = null;
		                if (compFederalFormsInfo.getModifiedDate() != null) {
		                    lastEditedDate = compFederalFormsInfo.getModifiedDate();
		                } else {
		                    lastEditedDate = compFederalFormsInfo.getCreatedDate();
		                }
		                compFederalFormsInfoModel.setCreatedDate(lastEditedDate);

		                compFederalFormsDocFiledList.add(compFederalFormsInfoModel);
		            }           
		            mav = new ModelAndView("compFederalFormsDocFiled");
		            mav.addObject("compFederalFormsDocFiledList", compFederalFormsDocFiledList);
		            
		            mav.addObject(compFederalFormsOrderReceivedCount, compFederalFormsSavingInfoList.size());
		            mav.addObject(compFederalFormsOrderProcessedCount, compFederalFormsOrderProcessedDataList.size());
		            mav.addObject(compFederalFormsSignatureCount, compFederalFormsSignatureDataList.size());
		            mav.addObject(compFederalFormsDocFiledCount, compFederalFormsDocFiledDataList.size());
		            mav.addObject(compFederalFormsDocAcceptedCount, compFederalFormsDocAcceptedDataList.size());
		            mav.addObject(compFederalFormsDocEmailedCount, compFederalFormsDocEmailedDataList.size());	            
		        } else{
		        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);
		        }
		        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
				}
		        return mav;
		    }  
			
//			All Completed Federal Forms Doc Accepted Status Updation
		    @Override
		    @Transactional
		    public ModelAndView allCompFederalFormsDocAcceptedStatusUpdation(HttpServletRequest req, HttpSession sn) {
		    	LOGGER.debug("allCompFederalFormsDocAcceptedStatusUpdation...Service");
		    	ModelAndView mav = new ModelAndView();
		    	
				if(sn.getAttribute(adminUserNameInSn) != null){
		    	String[] comValue = (req.getParameter("formName")).split(",&, ");
		    	sn.setAttribute(userNameInSn, comValue[0]);		    	
		    	sn.setAttribute(userChoiceInSn, comValue[1]);
		    	sn.setAttribute(typeOfDocumentInSn, comValue[2]);
		    	
		    	Timestamp currentDate = currentDate();
		    	AllFederalFormsCheckoutPaymentAndUserContactSaving allFedCheckoutPaymentAndUserContact = allFedCheckoutPaymentAndContactDAOImpl.findByallFederalFormsUserChoiceFromDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(typeOfDocumentInSn), (String) sn.getAttribute(userChoiceInSn));
		    	allFedCheckoutPaymentAndUserContact.setDocAccepted("Done");
		    	allFedCheckoutPaymentAndUserContact.setDocAcceptedCreatedDate(currentDate);
		    	allFedCheckoutPaymentAndContactDAOImpl.merge(allFedCheckoutPaymentAndUserContact);
	            mav = completedFederalFormsDocAcceptedRedirection(req, sn);
	    	mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
				}
	    	return mav;
	    }
		    
//			completed Federal Forms Doc Accepted Redirection Service Implementation		
			@Override
			@Transactional
			public ModelAndView completedFederalFormsDocAcceptedRedirection(HttpServletRequest req, HttpSession sn) {
				LOGGER.debug("completedFederalFormsDocAcceptedRedirection...Service");		
				ModelAndView mav = new ModelAndView();
		    	
				if(sn.getAttribute(adminUserNameInSn) != null){
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsSavingInfoList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsDataDisplayInAdmin();
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsOrderProcessedDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsOrderProcessedDataRedirection();
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsSignatureDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsSignatureRedirection();
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsDocFiledDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsDocFiledRedirection();
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsDocAcceptedDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsDocAcceptedRedirection();
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsDocEmailedDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsDocEmailedRedirection();
				
		        if (!compFederalFormsDocAcceptedDataList.isEmpty()) {
		        	List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsDocAcceptedList = new ArrayList<AllFederalFormsCheckoutPaymentAndUserContactSaving>();
		            for (AllFederalFormsCheckoutPaymentAndUserContactSaving compFederalFormsInfo : compFederalFormsDocAcceptedDataList) {
		                AllFederalFormsCheckoutPaymentAndUserContactSaving compFederalFormsInfoModel = new AllFederalFormsCheckoutPaymentAndUserContactSaving();
		                compFederalFormsInfoModel.setUserName(compFederalFormsInfo.getUserName());
		                compFederalFormsInfoModel.setFormStatus(compFederalFormsInfo.getFormStatus());
		                compFederalFormsInfoModel.setLegalName(compFederalFormsInfo.getLegalName());
		                Timestamp lastEditedDate = null;
		                if (compFederalFormsInfo.getModifiedDate() != null) {
		                    lastEditedDate = compFederalFormsInfo.getModifiedDate();
		                } else {
		                    lastEditedDate = compFederalFormsInfo.getCreatedDate();
		                }
		                compFederalFormsInfoModel.setCreatedDate(lastEditedDate);
		                compFederalFormsDocAcceptedList.add(compFederalFormsInfoModel);
		            }         
		            mav = new ModelAndView("compFederalFormsDocAccepted");
		            mav.addObject("compFederalFormsDocAcceptedList", compFederalFormsDocAcceptedList);
		            
		            mav.addObject(compFederalFormsOrderReceivedCount, compFederalFormsSavingInfoList.size());
		            mav.addObject(compFederalFormsOrderProcessedCount, compFederalFormsOrderProcessedDataList.size());
		            mav.addObject(compFederalFormsSignatureCount, compFederalFormsSignatureDataList.size());
		            mav.addObject(compFederalFormsDocFiledCount, compFederalFormsDocFiledDataList.size());
		            mav.addObject(compFederalFormsDocAcceptedCount, compFederalFormsDocAcceptedDataList.size());
		            mav.addObject(compFederalFormsDocEmailedCount, compFederalFormsDocEmailedDataList.size());
		        } else{
		        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);
		        }
		        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
				}
		        return mav;
		    }  
			
//			All Completed Federal Forms Doc Emailed Status Updation
		    @Override
		    @Transactional
		    public ModelAndView allCompFederalFormsDocEmailedStatusUpdation(HttpServletRequest req, HttpSession sn) {
		    	LOGGER.debug("allCompFederalFormsDocEmailedStatusUpdation...Service");
		    	ModelAndView mav = new ModelAndView();
		    	
				if(sn.getAttribute(adminUserNameInSn) != null){
		    	String[] comValue = (req.getParameter("formName")).split(",&, ");
		    	sn.setAttribute(userNameInSn, comValue[0]);		    	
		    	sn.setAttribute(userChoiceInSn, comValue[1]);
		    	sn.setAttribute(typeOfDocumentInSn, comValue[2]);
		    	
		    	Timestamp currentDate = currentDate();
		    	AllFederalFormsCheckoutPaymentAndUserContactSaving allFedCheckoutPaymentAndUserContact = allFedCheckoutPaymentAndContactDAOImpl.findByallFederalFormsUserChoiceFromDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(typeOfDocumentInSn), (String) sn.getAttribute(userChoiceInSn));
		    	allFedCheckoutPaymentAndUserContact.setDocEmailed("Done");
		    	allFedCheckoutPaymentAndUserContact.setDocEmaileCreatedDate(currentDate);
		    	allFedCheckoutPaymentAndContactDAOImpl.merge(allFedCheckoutPaymentAndUserContact);
	            mav = completedFederalFormsDocEmailedRedirection(req, sn);
	    	mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
				}
	    	return mav;
	    }
		    
//			completed Federal Forms Doc Emailed Redirection Service Implementation		
			@Override
			@Transactional
			public ModelAndView completedFederalFormsDocEmailedRedirection(HttpServletRequest req, HttpSession sn) {
				LOGGER.debug("completedFederalFormsDocEmailedRedirection...Service");		
				ModelAndView mav = new ModelAndView();
		    	
				if(sn.getAttribute(adminUserNameInSn) != null){
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsSavingInfoList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsDataDisplayInAdmin();
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsOrderProcessedDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsOrderProcessedDataRedirection();
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsSignatureDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsSignatureRedirection();
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsDocFiledDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsDocFiledRedirection();
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsDocAcceptedDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsDocAcceptedRedirection();
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsDocEmailedDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsDocEmailedRedirection();
				
				List<CompletedFederalFormsFileUploadSaving> compFedFormsFileUploadList = compFedFormsFileUploadSavingDAOImpl.completedFederalFormsUploadedDataSaving();
				
		        if (!compFederalFormsDocEmailedDataList.isEmpty()) {
		        	List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsDocEmailedList = new ArrayList<AllFederalFormsCheckoutPaymentAndUserContactSaving>();
		            for (AllFederalFormsCheckoutPaymentAndUserContactSaving compFederalFormsInfo : compFederalFormsDocEmailedDataList) {
		                AllFederalFormsCheckoutPaymentAndUserContactSaving compFederalFormsInfoModel = new AllFederalFormsCheckoutPaymentAndUserContactSaving();
		                compFederalFormsInfoModel.setUserName(compFederalFormsInfo.getUserName());
		                compFederalFormsInfoModel.setFormStatus(compFederalFormsInfo.getFormStatus());
		                compFederalFormsInfoModel.setLegalName(compFederalFormsInfo.getLegalName());
		                int invNum = compFederalFormsInfo.getInvoiceNum();
		                String amount = compFederalFormsInfo.getAmount();
		                Timestamp lastEditedDate = null;
		                if (compFederalFormsInfo.getModifiedDate() != null) {
		                    lastEditedDate = compFederalFormsInfo.getModifiedDate();
		                } else {
		                    lastEditedDate = compFederalFormsInfo.getCreatedDate();
		                }
		                compFederalFormsInfoModel.setCreatedDate(lastEditedDate);
		                compFederalFormsDocEmailedList.add(compFederalFormsInfoModel);
		                sn.setAttribute("docAmountInSn", amount);
			            sn.setAttribute("docInvNoInSn", invNum);
		            }           
		            mav = new ModelAndView("compFederalFormsDocEmailed");
		            mav.addObject("compFederalFormsDocEmailedList", compFederalFormsDocEmailedList);
		            
		            mav.addObject(compFederalFormsOrderReceivedCount, compFederalFormsSavingInfoList.size());
		            mav.addObject(compFederalFormsOrderProcessedCount, compFederalFormsOrderProcessedDataList.size());
		            mav.addObject(compFederalFormsSignatureCount, compFederalFormsSignatureDataList.size());
		            mav.addObject(compFederalFormsDocFiledCount, compFederalFormsDocFiledDataList.size());
		            mav.addObject(compFederalFormsDocAcceptedCount, compFederalFormsDocAcceptedDataList.size());
		            mav.addObject(compFederalFormsDocEmailedCount, compFederalFormsDocEmailedDataList.size());
		            mav.addObject("compFederalFormsFileUploadCount", compFedFormsFileUploadList.size());
		        } else{
		        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);
		        }
		        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
				}
		        return mav;
		    }
			
//			completed Federal Forms Doc Emailed Redirection Service Implementation		
			@Override
			@Transactional
			public ModelAndView completedFederalFormsFileUpload(HttpServletRequest req, HttpSession sn) {
				LOGGER.debug("completedFederalFormsFileUpload...Service");		
				ModelAndView mav = new ModelAndView();
		    	
				if(sn.getAttribute(adminUserNameInSn) != null){
				String[] comValue = (req.getParameter("formName")).split(",&, ");
		    	sn.setAttribute(userNameInSn, comValue[0]);		    	
		    	sn.setAttribute(userChoiceInSn, comValue[1]);
		    	sn.setAttribute(typeOfDocumentInSn, comValue[2]);
		    	
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsSavingInfoList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsDataDisplayInAdmin();
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsOrderProcessedDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsOrderProcessedDataRedirection();
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsSignatureDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsSignatureRedirection();
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsDocFiledDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsDocFiledRedirection();
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsDocAcceptedDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsDocAcceptedRedirection();
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsDocEmailedDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsDocEmailedRedirection();
				         
		            mav = new ModelAndView("compFederalFormsFileUpload");
		            mav.addObject(compFederalFormsOrderReceivedCount, compFederalFormsSavingInfoList.size());
		            mav.addObject(compFederalFormsOrderProcessedCount, compFederalFormsOrderProcessedDataList.size());
		            mav.addObject(compFederalFormsSignatureCount, compFederalFormsSignatureDataList.size());
		            mav.addObject(compFederalFormsDocFiledCount, compFederalFormsDocFiledDataList.size());
		            mav.addObject(compFederalFormsDocAcceptedCount, compFederalFormsDocAcceptedDataList.size());
		            mav.addObject(compFederalFormsDocEmailedCount, compFederalFormsDocEmailedDataList.size());            
		        
		        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
				}
		        return mav;
		    }

//			completed Federal Forms File Upload Sending and Saving Service Implementation		
			@Override
			@Transactional
			public ModelAndView completedFederalFormsFileUploadSendingAndSaving(MultipartFile file, String subject, String comments, HttpServletRequest req, HttpSession sn) throws Exception {
				LOGGER.debug("completedFederalFormsFileUploadSendingAndSaving...Service");			
				ModelAndView mav = new ModelAndView();
				
				if(sn.getAttribute(adminUserNameInSn) != null){
					EmailSending emailer = new EmailSending();
					String userName = (String) sn.getAttribute(userNameInSn);
					int userNameIndex = (userName).indexOf("@");
			        String userFirstName = (userName).substring(0, userNameIndex);
					
				String[] emailList = {userName};				
				String body = "<body><table style=' border: #458B00 solid; width:  600px; height:  auto; background-color:  #ECF1EF; margin-left: 100px;'>"
					    + "<table style=' background-color: #458B00; width:600px; height: 30px; margin-top: -20px'>"
					      
					    + "</table>"
					    + "<br>"
					    + "<div style='margin-left: 20px; margin-top: -100px; width: auto; height: auto'>"
					    + "<font size='2'>"
					    + "Dear "
					    + "<b>"
					    + userFirstName
					    + ",</b><br><br>"
					    + "Comments: "+ comments +""
					    + "<br>"
					    + "</font>"
					    + "</div>"
					    + "</table></body>";
					    String type = "text/html";					    
					    try{
					    	String fileName = file.getOriginalFilename();
						    File convFile = new File(req.getSession().getServletContext().getRealPath(fileName));
						    file.transferTo(convFile);
						    
					    String userChoice = (String) sn.getAttribute(userChoiceInSn);
					    String typeOfDocument = (String) sn.getAttribute(typeOfDocumentInSn);				    
					    String amount = (String)sn.getAttribute("docAmountInSn"); 
					    int orderNumber = (Integer)sn.getAttribute("docInvNoInSn");
					    
					    List compFedFormsFileUploadList = compFedFormsFileUploadSavingDAOImpl.findByFederalFormsFileUploadRowChecking(userName, typeOfDocument, userChoice);
					    if(compFedFormsFileUploadList.isEmpty()) {
					    	Timestamp currentDate = currentDate();				    	
					    	CompletedFederalFormsFileUploadSaving compFedFormsFileUploadModel = new CompletedFederalFormsFileUploadSaving();
					    	compFedFormsFileUploadModel.setUserName(userName);				    	
					    	compFedFormsFileUploadModel.setTypeOfDocument(typeOfDocument);					    	
					    	compFedFormsFileUploadModel.setDocumentName(userChoice);
					    	compFedFormsFileUploadModel.setAmount(amount);
					    	compFedFormsFileUploadModel.setOrderNumber(orderNumber);
					    	compFedFormsFileUploadModel.setSubject(subject);
					    	compFedFormsFileUploadModel.setComments(comments);
					    	compFedFormsFileUploadModel.setFileUploadData(fileName);
					    	compFedFormsFileUploadModel.setStatus("Done");
					    	compFedFormsFileUploadModel.setCreatedDate(currentDate);
					    	compFedFormsFileUploadSavingDAOImpl.save(compFedFormsFileUploadModel);
					    	
					    	AllFederalFormsCheckoutPaymentAndUserContactSaving allFedCheckoutPaymentAndUserContact = allFedCheckoutPaymentAndContactDAOImpl.findByallFederalFormsUserChoiceFromDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(typeOfDocumentInSn), (String) sn.getAttribute(userChoiceInSn));
					    	allFedCheckoutPaymentAndUserContact.setFileUploadStatus("Done");
					    	allFedCheckoutPaymentAndContactDAOImpl.merge(allFedCheckoutPaymentAndUserContact);
					    }
					    emailer.sendEmailWithAttachment(emailList, subject, body, type, convFile, fileName);				    
					    }catch(Exception e){
					    }
		            mav = completedFederalFormsFileUploadedDataDisplay(req, sn);		            
		        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
				}
		        return mav;
		    }
				
//			completed Federal Forms File Upload Saving data display Service Implementation		
			@Override
			@Transactional
			public ModelAndView completedFederalFormsFileUploadedDataDisplay(HttpServletRequest req, HttpSession sn) {
				LOGGER.debug("completedFederalFormsFileUploadedDataDisplay...Service");		
				ModelAndView mav = new ModelAndView();
				
				if(sn.getAttribute(adminUserNameInSn) != null){
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsSavingInfoList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsDataDisplayInAdmin();
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsOrderProcessedDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsOrderProcessedDataRedirection();
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsSignatureDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsSignatureRedirection();
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsDocFiledDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsDocFiledRedirection();
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsDocAcceptedDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsDocAcceptedRedirection();
				List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsDocEmailedDataList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsDocEmailedRedirection();
				
				List<CompletedFederalFormsFileUploadSaving> compFedFormsFileUploadList = compFedFormsFileUploadSavingDAOImpl.completedFederalFormsUploadedDataSaving();
				
		        if (!compFedFormsFileUploadList.isEmpty()) {
		        	List<CompletedFederalFormsFileUploadSaving> compFederalFormsFileUploadList = new ArrayList<CompletedFederalFormsFileUploadSaving>();
		        	int count = 1;
		            for (CompletedFederalFormsFileUploadSaving compFederalFormsUploadInfo : compFedFormsFileUploadList) {
		            	CompletedFederalFormsFileUploadSaving compFederalFileUploadInfoModel = new CompletedFederalFormsFileUploadSaving();
		                compFederalFileUploadInfoModel.setUserName(compFederalFormsUploadInfo.getUserName());
		                compFederalFileUploadInfoModel.setTypeOfDocument(compFederalFormsUploadInfo.getTypeOfDocument());
		                compFederalFileUploadInfoModel.setDocumentName(compFederalFormsUploadInfo.getDocumentName());
		                compFederalFileUploadInfoModel.setAmount(compFederalFormsUploadInfo.getAmount());
		                compFederalFileUploadInfoModel.setOrderNumber(compFederalFormsUploadInfo.getOrderNumber());
		                compFederalFileUploadInfoModel.setSubject(compFederalFormsUploadInfo.getSubject());
		                compFederalFileUploadInfoModel.setComments(compFederalFormsUploadInfo.getComments());
		                compFederalFileUploadInfoModel.setFileUploadData(compFederalFormsUploadInfo.getFileUploadData());		                
		                compFederalFileUploadInfoModel.setCreatedDate(compFederalFormsUploadInfo.getCreatedDate());
		                compFederalFileUploadInfoModel.setCompletedFederalFormsFileUploadSavingId(count);

		                compFederalFormsFileUploadList.add(compFederalFileUploadInfoModel);
		                count++;
		            }         
		            mav = new ModelAndView("compFederalFormsFileUploadDataSaving");
		            mav.addObject("compFederalFormsFileUploadList", compFederalFormsFileUploadList);
		            
		            mav.addObject(compFederalFormsOrderReceivedCount, compFederalFormsSavingInfoList.size());
		            mav.addObject(compFederalFormsOrderProcessedCount, compFederalFormsOrderProcessedDataList.size());
		            mav.addObject(compFederalFormsSignatureCount, compFederalFormsSignatureDataList.size());
		            mav.addObject(compFederalFormsDocFiledCount, compFederalFormsDocFiledDataList.size());
		            mav.addObject(compFederalFormsDocAcceptedCount, compFederalFormsDocAcceptedDataList.size());
		            mav.addObject(compFederalFormsDocEmailedCount, compFederalFormsDocEmailedDataList.size());
		            mav.addObject("compFederalFormsFileUploadCount", compFedFormsFileUploadList.size());
		        } else{
		        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);
		        }
		        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
				}
		        return mav;
		    }
			
//		Admin side File Uploads Data
			
//			completed Admin File Upload Redirection Service Implementation		
			@Override
			@Transactional
			public ModelAndView adminFileUploadedRedirection(HttpServletRequest req, HttpSession sn) {
				LOGGER.debug("adminFileUploadedRedirection...Service");		
				ModelAndView mav = new ModelAndView();
				
				if(sn.getAttribute(adminUserNameInSn) != null){							
				List<CompletedStateFormsFileUploadSaving> completedStateFormsFileUploadList = compStateFormsFileUploadSavingDAOImpl.completedStateFormsUploadedDataSaving();
				List<CompletedFederalFormsFileUploadSaving> compFedFormsFileUploadList = compFedFormsFileUploadSavingDAOImpl.completedFederalFormsUploadedDataSaving();
				
		        if (!completedStateFormsFileUploadList.isEmpty()) {
		        	mav = adminStateFormsFileUploadedDataDisplay(req, sn);		        	
		        } else if (!compFedFormsFileUploadList.isEmpty()) {
		        	mav = adminFederalFormsFileUploadedDataDisplay(req, sn);            
		        } else{
		        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);
		        }
		        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
				}
		        return mav;
		    }
			
//			completed Admin Side State Forms File Upload Saving data display Service Implementation		
			@Override
			@Transactional
			public ModelAndView adminStateFormsFileUploadedDataDisplay(HttpServletRequest req, HttpSession sn) {
				LOGGER.debug("adminStateFormsFileUploadedDataDisplay...Service");		
				ModelAndView mav = new ModelAndView();
				
				if(sn.getAttribute(adminUserNameInSn) != null){							
				List<CompletedStateFormsFileUploadSaving> completedStateFormsFileUploadList = compStateFormsFileUploadSavingDAOImpl.completedStateFormsUploadedDataSaving();
				List<CompletedFederalFormsFileUploadSaving> compFedFormsFileUploadList = compFedFormsFileUploadSavingDAOImpl.completedFederalFormsUploadedDataSaving();
				
		        if (!completedStateFormsFileUploadList.isEmpty()) {
		        	List<CompletedStateFormsFileUploadSaving> compStateFormsFileUploadList = new ArrayList<CompletedStateFormsFileUploadSaving>();
		        	int count = 1;
		            for (CompletedStateFormsFileUploadSaving compStateFormsUploadInfo : completedStateFormsFileUploadList) {
		            	CompletedStateFormsFileUploadSaving compStateFileUploadInfoModel = new CompletedStateFormsFileUploadSaving();
		            	compStateFileUploadInfoModel.setUserName(compStateFormsUploadInfo.getUserName());
		            	compStateFileUploadInfoModel.setTypeOfDocument(compStateFormsUploadInfo.getTypeOfDocument());
		            	compStateFileUploadInfoModel.setFormName(compStateFormsUploadInfo.getFormName());
		            	compStateFileUploadInfoModel.setStateName(compStateFormsUploadInfo.getStateName());
		            	compStateFileUploadInfoModel.setUserChoice(compStateFormsUploadInfo.getUserChoice());
		            	compStateFileUploadInfoModel.setAmount(compStateFormsUploadInfo.getAmount());
		            	compStateFileUploadInfoModel.setOrderNumber(compStateFormsUploadInfo.getOrderNumber());
		            	compStateFileUploadInfoModel.setSubject(compStateFormsUploadInfo.getSubject());
		            	compStateFileUploadInfoModel.setComments(compStateFormsUploadInfo.getComments());
		            	compStateFileUploadInfoModel.setFileUploadData(compStateFormsUploadInfo.getFileUploadData());		                
		            	compStateFileUploadInfoModel.setCreatedDate(compStateFormsUploadInfo.getCreatedDate());
		            	compStateFileUploadInfoModel.setCompletedStateFormsFileUploadSavingId(count);

		                compStateFormsFileUploadList.add(compStateFileUploadInfoModel);
		                count++;
		            }         
		            mav = new ModelAndView("adminStateFormsFileupload");
		            mav.addObject("compStateFormsFileUploadList", compStateFormsFileUploadList);
		            
		            mav.addObject("admStateFormsFileUploadCount", completedStateFormsFileUploadList.size());
		            mav.addObject("admFederalFormsFileUploadCount", compFedFormsFileUploadList.size());		            
		        } else{
		        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);
		        }
		        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
				}
		        return mav;
		    }
			
//			completed Admin side Federal Forms File Upload Saving data display Service Implementation		
			@Override
			@Transactional
			public ModelAndView adminFederalFormsFileUploadedDataDisplay(HttpServletRequest req, HttpSession sn) {
				LOGGER.debug("adminFederalFormsFileUploadedDataDisplay...Service");		
				ModelAndView mav = new ModelAndView();
				
				if(sn.getAttribute(adminUserNameInSn) != null){
				List<CompletedStateFormsFileUploadSaving> completedStateFormsFileUploadList = compStateFormsFileUploadSavingDAOImpl.completedStateFormsUploadedDataSaving();
				List<CompletedFederalFormsFileUploadSaving> compFedFormsFileUploadList = compFedFormsFileUploadSavingDAOImpl.completedFederalFormsUploadedDataSaving();
				
		        if (!compFedFormsFileUploadList.isEmpty()) {
		        	List<CompletedFederalFormsFileUploadSaving> compFederalFormsFileUploadList = new ArrayList<CompletedFederalFormsFileUploadSaving>();
		        	int count = 1;
		            for (CompletedFederalFormsFileUploadSaving compFederalFormsUploadInfo : compFedFormsFileUploadList) {
		            	CompletedFederalFormsFileUploadSaving compFederalFileUploadInfoModel = new CompletedFederalFormsFileUploadSaving();
		                compFederalFileUploadInfoModel.setUserName(compFederalFormsUploadInfo.getUserName());
		                compFederalFileUploadInfoModel.setTypeOfDocument(compFederalFormsUploadInfo.getTypeOfDocument());
		                compFederalFileUploadInfoModel.setDocumentName(compFederalFormsUploadInfo.getDocumentName());
		                compFederalFileUploadInfoModel.setAmount(compFederalFormsUploadInfo.getAmount());
		                compFederalFileUploadInfoModel.setOrderNumber(compFederalFormsUploadInfo.getOrderNumber());
		                compFederalFileUploadInfoModel.setSubject(compFederalFormsUploadInfo.getSubject());
		                compFederalFileUploadInfoModel.setComments(compFederalFormsUploadInfo.getComments());
		                compFederalFileUploadInfoModel.setFileUploadData(compFederalFormsUploadInfo.getFileUploadData());		                
		                compFederalFileUploadInfoModel.setCreatedDate(compFederalFormsUploadInfo.getCreatedDate());
		                compFederalFileUploadInfoModel.setCompletedFederalFormsFileUploadSavingId(count);

		                compFederalFormsFileUploadList.add(compFederalFileUploadInfoModel);
		                count++;
		            }         
		            mav = new ModelAndView("adminFederalFormsFileupload");
		            mav.addObject("compFederalFormsFileUploadList", compFederalFormsFileUploadList);
		            
		            mav.addObject("admStateFormsFileUploadCount", completedStateFormsFileUploadList.size());
		            mav.addObject("admFederalFormsFileUploadCount", compFedFormsFileUploadList.size());	
		        } else{
		        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);
		        }
		        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
				}
		        return mav;
		    }		
			
		
	//	all state Forms Checkout Service Implementation 
	    @Override
	    @Transactional
	    public ModelAndView compBusinessStateFormsCheckouDataDisplay(HttpServletRequest req, HttpSession sn) {
	    	LOGGER.debug("compBusinessStateFormsCheckouDataDisplay...Service");
	        ModelAndView mav = new ModelAndView();	        
	//		Form Id getting from DB
	        int formId = takeFormIdFromDB(sn);
	        int userId = takeUserIdFromDB(sn);
	//		Required values take from DB		
	        if (sn.getAttribute(userChoiceInSn) != null) {
	            List<BusinessFormsAttributesAndValuesSaving> busStFormAttrAndValModel = busFormAttrAndValDAOImpl.findByallStateFormsCapturedInfoFromDB(userId, formId, (String) sn.getAttribute(userChoiceInSn));
	            String capturedInfoInDB = null;
	            for (BusinessFormsAttributesAndValuesSaving stateFormAttrAndValModel : busStFormAttrAndValModel) {
	                capturedInfoInDB = stateFormAttrAndValModel.getCapturedInformation();
	            }
	//          Formation From String to JSON
	            JSONObject capInfoJsonObject = convertFromStringToJSONFormat(capturedInfoInDB);
	            
	//			State forms Ids take from DB 			
	            List attrNamesList = new ArrayList();
	            List attrFieldIdsList = new ArrayList();
	            
	            List<Object> formFieldsAndValuesIds = (List<Object>) bsFormAndStateAttrInfoDAOImpl.pendingStateFormsCheckoutAttrAndValues(formId);
	
	            Iterator formFieldsAndValuesIdsIterator = formFieldsAndValuesIds.iterator();
	            while (formFieldsAndValuesIdsIterator.hasNext()) {
	                Object[] formFieldsAndValuesIdsObj = (Object[]) formFieldsAndValuesIdsIterator.next();
	
	                String attrFieldId = String.valueOf(formFieldsAndValuesIdsObj[0]);                
	                String attrNames = String.valueOf(formFieldsAndValuesIdsObj[1]);
	                
	                attrFieldIdsList.add(attrFieldId);
	                attrNamesList.add(attrNames + " ");	                
	            }
	
	            String attrFieldIDListInString = attrFieldIdsList.toString();
	            attrFieldIDListInString = attrFieldIDListInString.replace("[", "");
	            attrFieldIDListInString = attrFieldIDListInString.replace("]", "");
	            
	            String attrNamesListInString = attrNamesList.toString();
	            attrNamesListInString = attrNamesListInString.replace("[", "");
	            attrNamesListInString = attrNamesListInString.replace("]", "");
	            
	            String[] attrFieldIDInString = attrFieldIDListInString.split(", ");
	            String[] attrNamesInString = attrNamesListInString.split(" , ");
	            
	            List attrSTNamesList = new ArrayList();
	            List attrSTValuesList = new ArrayList();
	            
	            for (int i = 0; i < attrFieldIDInString.length; i++) {
	                String attrFieldId = attrFieldIDInString[i];
	                String attrNames = attrNamesInString[i];
	                
	                String jSonObjVal = (String) capInfoJsonObject.get(attrFieldId);
	                if (!("").equals(jSonObjVal)) {
	
	                    String attrValue = jSonObjVal;
	
	                    for (int k = 0; k < jSonObjVal.length() + 600; k++) {
	                        String yesVal = yes + k;
	                        String noVal = no + k;
	                        if (yesVal.equals(jSonObjVal)) {
	                            attrValue = jSonObjVal.replaceAll(yesVal, yes);
	                        } else if (noVal.equals(jSonObjVal)) {
	                            attrValue = jSonObjVal.replaceAll(noVal, no);
	                        }
	                    }
	                    attrSTNamesList.add(attrNames);	                    
	                    attrSTValuesList.add(attrValue);	                    
	                }
	            }            
	            mav = new ModelAndView("completedStateFormsCheckoutDisplay");
	            sn.setAttribute("Attr_SF_Names_CheckOutList", attrSTNamesList);	            
	            sn.setAttribute("Attr_SF_Values_CheckOutList", attrSTValuesList);
	            
	            mav.addObject(stateName, sn.getAttribute(stateNameInSn));
	            mav.addObject(formName, sn.getAttribute(formNameInSn));
	        }
	        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
			return mav;
	    }
	    
	//	Additional Service Forms Checkout Service Implementation 
	@Override
	@Transactional
	public ModelAndView compAdditionalServiceFormsCheckouDataDisplay(HttpServletRequest req, HttpSession sn) {
	    LOGGER.debug("additionalServiceFormsCheckouDataDisplay...Service");
	    ModelAndView mav = new ModelAndView();
	    if(sn.getAttribute(adminUserNameInSn) != null){
	//	Form Id getting from DB
	    int formId = takeFormIdFromDB(sn);
	    int userId = takeUserIdFromDB(sn);
	    
	//		Required values take from DB	
	
	    List<AdditionalFormsAttributesAndValuesSaving> addSerFormAttrAndValModel = addSerFormAttrAndValSavingDAOImpl.findByAddtionalSerViceFormsCapturedInfoFromDB(userId, formId, (String) sn.getAttribute(userChoiceInSn));
	    String capturedInfoInDB = null;
	    for (AdditionalFormsAttributesAndValuesSaving addSerAttrAndValModel : addSerFormAttrAndValModel) {
	        capturedInfoInDB = addSerAttrAndValModel.getCapturedInformation();
	    }
	
	    JSONParser parser = new JSONParser();
	    JSONObject capInfoJsonObject = new JSONObject();
	
	    try {
	        Object parseObj = parser.parse(capturedInfoInDB);
	        capInfoJsonObject = (JSONObject) parseObj;
	    } catch (ParseException e) {
	        LOGGER.error("additionalServiceFormsCheckouDataDisplay " + e);
	    }
	
//		State forms Ids take from DB 			
	    List attrNamesList = new ArrayList();
	    List attrFieldIdsList = new ArrayList();
	    
	    List<Object> formFieldsAndValuesIds = (List<Object>) additionalFormsAttrInfoDAOImpl.pendingAdditionalFormsCheckoutAttrAndValues(formId);
	
	    Iterator formFieldsAndValuesIdsIterator = formFieldsAndValuesIds.iterator();
	    while (formFieldsAndValuesIdsIterator.hasNext()) {
	        Object[] formFieldsAndValuesIdsObj = (Object[]) formFieldsAndValuesIdsIterator.next();
	
	        String attrFieldId = String.valueOf(formFieldsAndValuesIdsObj[0]);
	        String attrNames = String.valueOf(formFieldsAndValuesIdsObj[1]);
	        
	        attrFieldIdsList.add(attrFieldId);
	        attrNamesList.add(attrNames + " ");
	    }
	
	    String attrFieldIDListInString = attrFieldIdsList.toString();
	    attrFieldIDListInString = attrFieldIDListInString.replace("[", "");
	    attrFieldIDListInString = attrFieldIDListInString.replace("]", "");
	    
	    String attrNamesListInString = attrNamesList.toString();
	    attrNamesListInString = attrNamesListInString.replace("[", "");
	    attrNamesListInString = attrNamesListInString.replace("]", "");
	    
	    String[] attrFieldIDInString = attrFieldIDListInString.split(", ");
	    String[] attrNamesInString = attrNamesListInString.split(" , ");
	    
	    List attrSTNamesList = new ArrayList();
	    List attrSTValuesList = new ArrayList();
	    
	    for (int i = 0; i < attrFieldIDInString.length; i++) {
	        String attrFieldId = attrFieldIDInString[i];
	        String attrNames = attrNamesInString[i];
	        
	        String jSonObjVal = (String) capInfoJsonObject.get(attrFieldId);
	        if (!("").equals(jSonObjVal)) {
	
	            String attrValue = jSonObjVal;
	
	            for (int k = 0; k < jSonObjVal.length() + 600; k++) {
	                String yesVal = yes + k;
	                String noVal = no + k;
	                if (yesVal.equals(jSonObjVal)) {
	                    attrValue = jSonObjVal.replaceAll(yesVal, yes);
	                } else if (noVal.equals(jSonObjVal)) {
	                    attrValue = jSonObjVal.replaceAll(noVal, no);
	                }
	            }
	
	            attrSTNamesList.add(attrNames);
	            attrSTValuesList.add(attrValue);
	        }
	    }
	    mav = new ModelAndView("completedAdditionalFormsCheckoutDisplay");
	    sn.setAttribute("Attr_AS_Names_CheckOutList", attrSTNamesList);
	    sn.setAttribute("Attr_AS_Values_CheckOutList", attrSTValuesList);
	    
	    mav.addObject(stateName, (String) sn.getAttribute(stateNameInSn));
	    mav.addObject(formName, (String) sn.getAttribute(formNameInSn));
	    mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		}
	    return mav;
	}
	
//State Tax ID Forms Checkout Service Implementation 
	@Override
	@Transactional
	public ModelAndView compStateTaxIdFormsCheckouDataDisplay(HttpServletRequest req, HttpSession sn) {
	    LOGGER.debug("stateTaxIdFormsCheckouDataDisplay...Service");
	    ModelAndView mav = new ModelAndView();	    
//	Form Id getting from DB
	    int formId = takeFormIdFromDB(sn);
	    int userId = takeUserIdFromDB(sn);
//		Required values take from DB		
	    if (sn.getAttribute(userChoiceInSn) != null) {
	        List<StateTaxFormsAttributesAndValuesSaving> stiFormAttrAndValModel = stateTaxAttrAndValuesSavingDAOImpl.findByallStateTaxIdFormsCapturedInfoFromDB(userId, formId, (String) sn.getAttribute(userChoiceInSn));
	        String capturedInfoInDB = null;
	        for (StateTaxFormsAttributesAndValuesSaving stateTaxFormAttrAndValModel : stiFormAttrAndValModel) {
	            capturedInfoInDB = stateTaxFormAttrAndValModel.getCapturedInformation();
	        }
	        JSONParser parser = new JSONParser();
	        JSONObject capInfoJsonObject = new JSONObject();
	        try {
	            Object parseObj = parser.parse(capturedInfoInDB);
	            capInfoJsonObject = (JSONObject) parseObj;
	        } catch (ParseException e) {
	            LOGGER.error("stateTaxIdFormsCheckouDataDisplay " + e);
	        }
//		State forms Ids take from DB 			
	        List attrNamesList = new ArrayList();
	        List attrFieldIdsList = new ArrayList();
	        
	        List<Object> formFieldsAndValuesIds = (List<Object>) stateTaxFormsAttrInfoDAOImpl.pendingStateTaxFormsCheckoutAttrAndValues(formId);
	
	        Iterator formFieldsAndValuesIdsIterator = formFieldsAndValuesIds.iterator();
	        while (formFieldsAndValuesIdsIterator.hasNext()) {
	            Object[] formFieldsAndValuesIdsObj = (Object[]) formFieldsAndValuesIdsIterator.next();
	
	            String attrFieldId = String.valueOf(formFieldsAndValuesIdsObj[0]);
	            String attrNames = String.valueOf(formFieldsAndValuesIdsObj[1]);
	            
	            attrFieldIdsList.add(attrFieldId);
	            attrNamesList.add(attrNames + " ");	           
	        }
	        String attrFieldIDListInString = attrFieldIdsList.toString();
	        attrFieldIDListInString = attrFieldIDListInString.replace("[", "");
	        attrFieldIDListInString = attrFieldIDListInString.replace("]", "");
	        
	        String attrNamesListInString = attrNamesList.toString();
	        attrNamesListInString = attrNamesListInString.replace("[", "");
	        attrNamesListInString = attrNamesListInString.replace("]", "");
	        
	        String[] attrFieldIDInString = attrFieldIDListInString.split(", ");
	        String[] attrNamesInString = attrNamesListInString.split(" , ");
	        
	        List attrSTNamesList = new ArrayList();	        
	        List attrSTValuesList = new ArrayList();
	        
	        for (int i = 0; i < attrFieldIDInString.length; i++) {
	            String attrFieldId = attrFieldIDInString[i];
	            String attrNames = attrNamesInString[i];
	            
	            String jSonObjVal = (String) capInfoJsonObject.get(attrFieldId);
	            if (!("").equals(jSonObjVal)) {
	                String attrValue = jSonObjVal;
	                for (int k = 0; k < jSonObjVal.length() + 600; k++) {
	                    String yesVal = yes + k;
	                    String noVal = no + k;
	                    if (yesVal.equals(jSonObjVal)) {
	                        attrValue = jSonObjVal.replaceAll(yesVal, yes);
	                    } else if (noVal.equals(jSonObjVal)) {
	                        attrValue = jSonObjVal.replaceAll(noVal, no);
	                    }
	                }
	                attrSTNamesList.add(attrNames);
	                attrSTValuesList.add(attrValue);
	            }
	        }
	        mav = new ModelAndView("completedStateTaxIdFormsCheckoutDisplay");
	        sn.setAttribute("Attr_STI_Names_CheckOutList", attrSTNamesList);
	        sn.setAttribute("Attr_STI_Values_CheckOutList", attrSTValuesList);
	        
	        mav.addObject(stateName, (String) sn.getAttribute(stateNameInSn));
	        mav.addObject(formName, (String) sn.getAttribute(formNameInSn));
	    }
	    mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));		
	    return mav;
	}
	
//	Completed State forms checkout Order Display Redirection
	
	@Override
	@Transactional
	public ModelAndView completedCheckoutStateFormsOrderDisplay(HttpServletRequest req, HttpSession sn) {
	    LOGGER.debug("completedCheckoutStateFormsOrderDisplay...Service");
	    String compCheOrderDisplayRefType = req.getParameter("compCheOrderDisplayRefType");
    	ModelAndView mav = new ModelAndView();
    	if(sn.getAttribute(adminUserNameInSn) != null){
	    if((BackMove).equals(compCheOrderDisplayRefType)) {
	    	mav = completedFormsRedirection(req, sn);	    	
	    } else if((FormModify).equals(compCheOrderDisplayRefType)) {
	    	mav = stateFormModificationDataRetrieveFromDB(req, sn);
	    }
	    mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		}
	    return mav;
	}
	
//	Completed State forms checkout Order Display Redirection
	
	@Override
	@Transactional
	public ModelAndView completedCheckoutAdditionalFormsOrderDisplay(HttpServletRequest req, HttpSession sn) {
	    LOGGER.debug("completedCheckoutAdditionalFormsOrderDisplay...Service");
	    String compCheOrderDisplayRefType = req.getParameter("compCheOrderDisplayRefType");
    	ModelAndView mav = new ModelAndView();
    	if(sn.getAttribute(adminUserNameInSn) != null){
	    if((BackMove).equals(compCheOrderDisplayRefType)) {
	    	mav = completedFormsRedirection(req, sn);
	    } else if((FormModify).equals(compCheOrderDisplayRefType)) {
	    	mav = additionalFormModificationDataRetrieveFromDB(req, sn);
	    }
	    mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		}
	    return mav;
	}	
	
//	Completed State forms checkout Order Display Redirection
	
	@Override
	@Transactional
	public ModelAndView completedCheckoutStateTaxIdFormsOrderDisplay(HttpServletRequest req, HttpSession sn) {
	    LOGGER.debug("completedCheckoutStateTaxIdFormsOrderDisplay...Service");
	    String compCheOrderDisplayRefType = req.getParameter("compCheOrderDisplayRefType");	    
    	ModelAndView mav = new ModelAndView();
    	if(sn.getAttribute(adminUserNameInSn) != null){
	    if((BackMove).equals(compCheOrderDisplayRefType)) {
	    	mav = completedFormsRedirection(req, sn);
	    } else if((FormModify).equals(compCheOrderDisplayRefType)) {	    	
	    	mav = stateTaxIdFormModificationDataRetrieveFromDB(req, sn);
	    }
	    mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		}
	    return mav;
	}
	
//  State Forms Start Point of method
	
//	State Form Modification Data Retrieve From DB Service
    @Override
    @Transactional
    public ModelAndView stateFormModificationDataRetrieveFromDB(HttpServletRequest req, HttpSession sn) {
    	LOGGER.debug("newStateFormSelectionFromDB...Service");
        ModelAndView mav;        
//			Required values take from DB
        int formId = takeFormIdFromDB(sn);
        int userId = takeUserIdFromDB(sn);        
            List<BusinessFormsAttributesInfo> busFormAttrList = bsFormAndStateAttrInfoDAOImpl.stateFormDynamicFormShowHideData(formId);

            List attRequiredList = new ArrayList();
            List attRadioStatusList = new ArrayList();
            List innerRadioList = new ArrayList();
            List addAnotherList = new ArrayList();

            for (BusinessFormsAttributesInfo busFormsAttInfo : busFormAttrList) {
                attRequiredList.add(busFormsAttInfo.getRequiredType());
                attRadioStatusList.add(busFormsAttInfo.getRadioButtonStatus());
                innerRadioList.add(busFormsAttInfo.getRadioButtonIdStatus());
                addAnotherList.add(busFormsAttInfo.getAddAnotherRbstatus());
            }

            sn.setAttribute("Attribute_Required_List", attRequiredList);
            sn.setAttribute("Attribute_RadioStatus_List", attRadioStatusList);
            sn.setAttribute("Attribute_InnerRadio_List", innerRadioList);
            sn.setAttribute("Attribute_AddAnother_List", addAnotherList);

//		    State form attribute fields and values take from DB this is simple join method
            List<Object> formFieldsAndValues = (List<Object>) bsFormAndStateAttrInfoDAOImpl.busStateFormsDynamicFieldsAndValues(formId);
            List attrId = new ArrayList();
            List attrType = new ArrayList();
            List attNames = new ArrayList();
            List attValue = new ArrayList();

            Iterator formFieldsAndValuesIterator = formFieldsAndValues.iterator();
            while (formFieldsAndValuesIterator.hasNext()) {
                Object[] formFieldsAndValuesObj = (Object[]) formFieldsAndValuesIterator.next();

                String attrbId = String.valueOf(formFieldsAndValuesObj[0]);
                String attrbType = String.valueOf(formFieldsAndValuesObj[1]);
                String attrbName = String.valueOf(formFieldsAndValuesObj[2]);
                String attrbValue = String.valueOf(formFieldsAndValuesObj[3]);
                attrId.add(attrbId);
                attrType.add(attrbType);
                attNames.add(attrbName.replace("null", ""));
                attValue.add(attrbValue.replace("null", ""));
            }
            
            sn.setAttribute("Attribute_Names_List", attNames);
            sn.setAttribute("Attribute_Values_List", attValue);

//			State forms Attribute Required type Ids take from DB 
            List<Object> attrReqTypeIdsList = (List<Object>) bsFormAndStateAttrInfoDAOImpl.attributeReqTypeIDsList(formId);
            String attrReqTypeIds = attrReqTypeIdsList.toString();
            attrReqTypeIds = attrReqTypeIds.replace("[", "");
            attrReqTypeIds = attrReqTypeIds.replace("]", "");

//			Radio Button Status take from DB 			
            List<Object> radioButtonStatus = (List<Object>) bsFormAndStateAttrInfoDAOImpl.stateFormRadioButtonStatus(formId);

//			Form Modification Code           
            List<BusinessFormsAttributesAndValuesSaving> busStFormAttrAndValModel = busFormAttrAndValDAOImpl.findByallStateFormsCapturedInfoFromDB(userId, formId, (String) sn.getAttribute(userChoiceInSn));
                String capturedInfoInDB = null;
                for (BusinessFormsAttributesAndValuesSaving stateFormAttrAndValModel : busStFormAttrAndValModel) {
                    capturedInfoInDB = stateFormAttrAndValModel.getCapturedInformation();
                }

                JSONParser parser = new JSONParser();
                JSONObject capInfoJsonObject = new JSONObject();

                try {
                    Object parseObj = parser.parse(capturedInfoInDB);
                    capInfoJsonObject = (JSONObject) parseObj;
                } catch (ParseException e) {
                	LOGGER.error("newStateFormSelectionFromDB " + e);
                }                
                mav = newStateFormAttributesInfoSelection(capInfoJsonObject, req);

                AllStateFormsDataSaving stateFormsUpdateModel = allStateFormsDataSavingDAOImpl.allStateFormsDataUpdateInDB((String)sn.getAttribute(userNameInSn), (String) sn.getAttribute(typeOfDocumentInSn), (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(userChoiceInSn));
                String stateFormPageValues = stateFormsUpdateModel.getPageVariableReference();
                
                mav = new ModelAndView(newStateFormsCreation);                           
                mav.addObject(attribTextFieldList, req.getAttribute(attribTextFieldList));
                mav.addObject(attribTextFieldAddrList, req.getAttribute(attribTextFieldAddrList));
                mav.addObject(attribTextFieldZipList, req.getAttribute(attribTextFieldZipList));
                mav.addObject(attribTextAreaList, req.getAttribute(attribTextAreaList));
                mav.addObject(attribTextFieldDateList, req.getAttribute(attribTextFieldDateList));
                mav.addObject(attribSelectBoxList, req.getAttribute(attribSelectBoxList));
                mav.addObject(radioButtList, req.getAttribute(radioButtList));
                mav.addObject(checkBoxesList, req.getAttribute(checkBoxesList));
                
                mav.addObject(stateFormsPageValue, stateFormPageValues);                    
            
            mav.addObject(radioButStatus, radioButtonStatus);            
            sn.setAttribute(radioButStatus, radioButtonStatus);
            sn.setAttribute(attReqiTypeIdsInSn, attrReqTypeIds);
            mav.addObject(stateName, sn.getAttribute(stateNameInSn));
            mav.addObject(formName, sn.getAttribute(formNameInSn));
            mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
        return mav;
    }
    
    
//	New Business Services Forms Attributes Info Selection
    @Override
    @Transactional
    public ModelAndView newStateFormAttributesInfoSelection(JSONObject capInfoJsonObject, HttpServletRequest req) {
        LOGGER.debug("newStateFormAttributesInfoSelection...Service");
        ModelAndView mav = new ModelAndView();
        
        String attributeTextFieldList = null;
        for (int i = 1; i <= 160; i++) {
            String keyVal = String.valueOf(i);
            String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
            attributeTextFieldList = attributeTextFieldList + jSonObjVal + ",$,";
        }
        attributeTextFieldList = attributeTextFieldList.replace("null", "");

        String attributeTextFieldAddrList = null;
        for (int j = 161; j <= 205; j++) {
            String keyVal = String.valueOf(j);
            String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
            attributeTextFieldAddrList = attributeTextFieldAddrList + jSonObjVal + ",$,";
        }
        attributeTextFieldAddrList = attributeTextFieldAddrList.replace("null", "");

        String attributeTextFieldZipList = null;
        for (int k = 206; k <= 285; k++) {
            String keyVal = String.valueOf(k);
            String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
            attributeTextFieldZipList = attributeTextFieldZipList + jSonObjVal + ",$,";
        }
        attributeTextFieldZipList = attributeTextFieldZipList.replace("null", "");

        String attributeTextAreaList = null;
        for (int l = 286; l <= 325; l++) {
            String keyVal = String.valueOf(l);
            String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
            jSonObjVal = jSonObjVal.replaceAll("[\n\r]", " ");
            attributeTextAreaList = attributeTextAreaList + jSonObjVal + ",$,";
        }
        attributeTextAreaList = attributeTextAreaList.replace("null", "");

        String attributeSelectBoxList = null;
        for (int n = 326; n <= 375; n++) {
            String keyVal = String.valueOf(n);
            String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
            attributeSelectBoxList = attributeSelectBoxList + jSonObjVal + ",$,";
        }
        attributeSelectBoxList = attributeSelectBoxList.replace("null", "");

        String attributeTextFieldDateList = null;
        for (int m = 376; m <= 415; m++) {
            String keyVal = String.valueOf(m);
            String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
            attributeTextFieldDateList = attributeTextFieldDateList + jSonObjVal + ",$,";
        }
        attributeTextFieldDateList = attributeTextFieldDateList.replace("null", "");

        String checkBoxList = null;
        for (int p = 416; p <= 435; p++) {
            String keyVal = String.valueOf(p);
            String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
            checkBoxList = checkBoxList + jSonObjVal + ",$,";
        }
        checkBoxList = checkBoxList.replace("null", "");

        String radioButtonList = null;
        for (int o = 436; o <= 1035; o++) {
            String keyVal = String.valueOf(o);
            String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
            radioButtonList = radioButtonList + jSonObjVal + ",$,";
        }
        radioButtonList = radioButtonList.replace("null", "");
        
        req.setAttribute(attribTextFieldList, attributeTextFieldList);
        req.setAttribute(attribTextFieldAddrList, attributeTextFieldAddrList);
        req.setAttribute(attribTextFieldZipList, attributeTextFieldZipList);
        req.setAttribute(attribTextAreaList, attributeTextAreaList);
        req.setAttribute(attribTextFieldDateList, attributeTextFieldDateList);
        req.setAttribute(attribSelectBoxList, attributeSelectBoxList);
        req.setAttribute(radioButtList, radioButtonList);
        req.setAttribute(checkBoxesList, checkBoxList);        
        return mav;
    }
    
	
//	State Form modification Saving data in DB Service Implementation
    @Override
    @Transactional
    public ModelAndView stateFormDataSavingAndUpdatingInDB(HttpServletRequest req, HttpSession sn) {
    	LOGGER.debug("stateFormDataSavingAndUpdatingInDB...Service");    	
        String regAgentPrice = registerAgentPriceTakeFromDB(req.getParameter("radioButton501"), req, sn);
        JSONObject stateFormInfoObj = stateFormsAllAttributesInfoFromJSP(req);        
        ModelAndView mav;
        int formId = takeFormIdFromDB(sn);
        int userId = takeUserIdFromDB(sn);
//Already Exit User choice when ever directly clicking enter with out using mouse        
        List userAEChoiceList = allStateFormsDataSavingDAOImpl.allStateFormsAlreadyExittUserChoiceInDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(typeOfDocumentInSn), (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(formNameInSn), req.getParameter(attributeTextField1));
        String alreadyExitChoice = null;
        
        if(!userAEChoiceList.isEmpty()){
        	AllStateFormsDataSaving stateFormsAEModel = allStateFormsDataSavingDAOImpl.findByallStateFormsDataFromDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(typeOfDocumentInSn), (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(stateNameInSn), req.getParameter(attributeTextField1));        
        alreadyExitChoice = stateFormsAEModel.getUserChoice();        
        }              
        if (alreadyExitChoice != null && !alreadyExitChoice.equals(sn.getAttribute(userChoiceInSn))) {        	 
            mav = new ModelAndView(newStateFormsCreation);
            mav.addObject("AlreadyExitChoice", "AlreadyExitUserChoice");
        } else {         	
        	String formStatus = stateFormStatusInDB(req, sn);
        	
            String jsonStringObj = stateFormInfoObj.toString();
            jsonStringObj = jsonStringObj.replace("null", "\"\"");

            Forms formsId = new Forms();
            formsId.setFormId(formId);
            Timestamp currentDate = currentDate();
            
                BusinessFormsAttributesAndValuesSaving attrAndValModel = busFormAttrAndValDAOImpl.findByBusinessFormsAttributesAndValuesFromDB(userId, formId, (String) sn.getAttribute(userChoiceInSn));                
                attrAndValModel.setUserChoice(req.getParameter(attributeTextField1));
                attrAndValModel.setCapturedInformation(jsonStringObj);
                attrAndValModel.setModifiedDate(currentDate);
                busFormAttrAndValDAOImpl.merge(attrAndValModel);
                
                AllStateFormsDataSaving stateFormsModel = allStateFormsDataSavingDAOImpl.allStateFormsDataUpdateInDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(typeOfDocumentInSn), (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(userChoiceInSn));
                stateFormsModel.setUserChoice(req.getParameter(attributeTextField1));
                stateFormsModel.setStatus(formStatus);
                stateFormsModel.setRegisteredAgentPrice(regAgentPrice);
                stateFormsModel.setModifiedDate(currentDate);
                stateFormsModel.setPageVariableReference(req.getParameter(stateFormsPageValue));
                stateFormsModel.setFormStatus(req.getParameter(stateFormHiddenVarb));
                allStateFormsDataSavingDAOImpl.merge(stateFormsModel);                
           
            	if(!(sn.getAttribute(userChoiceInSn)).equals(req.getParameter(attributeTextField1))) {            		
//              All related tables updating
                mav = stateFormsAllRelatedOtherTablesUpdate(req, sn);
                }
              
                sn.setAttribute(userChoiceInSn, req.getParameter(attributeTextField1));            
                if (req.getParameter(stateFormHiddenVarb) != null && ("Finished").equals(req.getParameter(stateFormHiddenVarb))) {                	
                    mav = completedFormsRedirection(req, sn);
                } else {                	
                    mav = new ModelAndView(newStateFormsCreation);
                }
           
            mav.addObject(stateFormsPageValue, req.getParameter(stateFormsPageValue));
            sn.setAttribute(bsAttrbRequiredList, sn.getAttribute(bsAttrbRequiredList));
            sn.setAttribute(bsAttrbRadioStatusList, sn.getAttribute(bsAttrbRadioStatusList));
            sn.setAttribute(bsAttrbInnerRadioList, sn.getAttribute(bsAttrbInnerRadioList));
            sn.setAttribute(bsAttrbAddAnotherList, sn.getAttribute(bsAttrbAddAnotherList));

            sn.setAttribute(bsAttrbNamesList, sn.getAttribute(bsAttrbNamesList));
            sn.setAttribute(bsAttrbValuesList, sn.getAttribute(bsAttrbValuesList));
            sn.setAttribute(radioButStatus, sn.getAttribute(radioButStatus));
        }
        
        mav.addObject(attribTextFieldList, req.getAttribute(attribTextFieldList));
        mav.addObject(attribTextFieldAddrList, req.getAttribute(attribTextFieldAddrList));
        mav.addObject(attribTextFieldZipList, req.getAttribute(attribTextFieldZipList));
        mav.addObject(attribTextAreaList, req.getAttribute(attribTextAreaList));
        mav.addObject(attribSelectBoxList, req.getAttribute(attribSelectBoxList));
        mav.addObject(attribTextFieldDateList, req.getAttribute(attribTextFieldDateList));
        mav.addObject(radioButtList, req.getAttribute(radioButtList));
        mav.addObject(checkBoxesList, req.getAttribute(checkBoxesList));
        
        mav.addObject(stateName, sn.getAttribute(stateNameInSn));
        mav.addObject(formName, sn.getAttribute(formNameInSn));
        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
        return mav;
    }
    
//	State Forms Register Agent Price Take From DB
    @Override
    @Transactional
    public String registerAgentPriceTakeFromDB(String regAgentValue, HttpServletRequest req, HttpSession sn) {
    	String regAgentPrice = null;
        if (regAgentValue != null && ("yes501").equals(regAgentValue)) {
            req.setAttribute(attribTextFieldAddr45, "LegalNod Registered Agent");
            List<Object> processingPrice = (List<Object>) federalFormsPriceInfoDAOImpl.federalFormPriceProperty("Registered Agent");      
            if(!processingPrice.isEmpty()){    	  
            	regAgentPrice = processingPrice.get(0).toString();    	  
            }            
        } else {
            req.setAttribute(attribTextFieldAddr45, "");
            regAgentPrice = null;
        }        
    	return regAgentPrice;
    }
   
//	State Forms Attributes Data take from JSP
    @Override
    @Transactional
    public JSONObject stateFormsFiveAttributesInfoFromJSP(HttpServletRequest req) {
        LOGGER.debug("stateFormsFiveAttributesInfoFromJSP...Service");
        JSONObject stateFormInfoObj = new JSONObject();
        String attributeTextFieldList = null;
        String[] attributeTextField = new String[161];
        for (int i = 1; i < attributeTextField.length; i++) {
            attributeTextField[i] = req.getParameter("attributeTextField" + i);
            attributeTextFieldList = attributeTextFieldList + attributeTextField[i] + ",$,";
            stateFormInfoObj.put(i, attributeTextField[i]);            
        }
        attributeTextFieldList = attributeTextFieldList.replace("null", "");

        String attributeTextFieldAddrList = null;
        String[] attributeTextFieldAddr = new String[46];
        for (int j = 1; j < attributeTextFieldAddr.length; j++) {
            if (j == 45) {
                attributeTextFieldAddrList = attributeTextFieldAddrList + req.getAttribute(attribTextFieldAddr45);
                stateFormInfoObj.put(j + 160, req.getAttribute(attribTextFieldAddr45));                
            } else {
                attributeTextFieldAddr[j] = req.getParameter("attributeTextFieldAddr" + j);
                attributeTextFieldAddrList = attributeTextFieldAddrList + attributeTextFieldAddr[j] + ",$,";
                stateFormInfoObj.put(j + 160, attributeTextFieldAddr[j]);                
            }
        }
        attributeTextFieldAddrList = attributeTextFieldAddrList.replace("null", "");

        String attributeTextFieldZipList = null;
        String[] attributeTextFieldZip = new String[81];
        for (int k = 1; k < attributeTextFieldZip.length; k++) {
            attributeTextFieldZip[k] = req.getParameter("attributeTextFieldZip" + k);
            attributeTextFieldZipList = attributeTextFieldZipList + attributeTextFieldZip[k] + ",$,";
            stateFormInfoObj.put(k + 205, attributeTextFieldZip[k]);            
        }
        attributeTextFieldZipList = attributeTextFieldZipList.replace("null", "");

        String attributeTextAreaList = null;
        String[] attributeTextArea = new String[41];
        for (int l = 1; l < attributeTextArea.length; l++) {
            attributeTextArea[l] = req.getParameter("attributeTextArea" + l);
            attributeTextAreaList = attributeTextAreaList + attributeTextArea[l] + ",$,";
            stateFormInfoObj.put(l + 285, attributeTextArea[l]);            
        }
        attributeTextAreaList = attributeTextAreaList.replace("null", "");
        attributeTextAreaList = attributeTextAreaList.replaceAll("[\n\r]", " ");

        String attributeSelectBoxList = null;
        String[] attributeSelectBox = new String[51];
        for (int n = 1; n < attributeSelectBox.length; n++) {
            attributeSelectBox[n] = req.getParameter("attributeSelectBox" + n);
            attributeSelectBoxList = attributeSelectBoxList + attributeSelectBox[n] + ",$,";
            stateFormInfoObj.put(n + 325, attributeSelectBox[n]);            
        }
        attributeSelectBoxList = attributeSelectBoxList.replace("null", "");
        
        req.setAttribute(attribTextFieldList, attributeTextFieldList);
        req.setAttribute(attribTextFieldAddrList, attributeTextFieldAddrList);
        req.setAttribute(attribTextFieldZipList, attributeTextFieldZipList);
        req.setAttribute(attribTextAreaList, attributeTextAreaList);
        req.setAttribute(attribSelectBoxList, attributeSelectBoxList);
        return stateFormInfoObj;
    }

//	State Forms All Attributes Data take from JSP
	    @Override
	    @Transactional
	    public JSONObject stateFormsAllAttributesInfoFromJSP(HttpServletRequest req) {
	        LOGGER.debug("stateFormsAllAttributesInfoFromJSP...Service");
	        JSONObject stateFormInfoObj = stateFormsFiveAttributesInfoFromJSP(req);
	
	        String attributeTextFieldDateList = null;
	        String[] attributeTextFieldDate = new String[41];
	        for (int m = 1; m < attributeTextFieldDate.length; m++) {
	            attributeTextFieldDate[m] = req.getParameter("attributeTextFieldDate" + m);
	            attributeTextFieldDateList = attributeTextFieldDateList + attributeTextFieldDate[m] + ",$,";
	            stateFormInfoObj.put(m + 375, attributeTextFieldDate[m]);            
	        }
	        attributeTextFieldDateList = attributeTextFieldDateList.replace("null", "");
	
	        String checkBoxList = null;
	        String[] checkBox = new String[21];
	        for (int p = 1; p < checkBox.length; p++) {
	            String checkSingele = req.getParameter("checkBox" + p);
	            if (checkSingele != null) {
	                String[] checkBoxVal = req.getParameterValues("checkBox" + p);
	                String ddynamicCheckValue = "";
	                for (int q = 0; q < checkBoxVal.length; q++) {
	                    ddynamicCheckValue = ddynamicCheckValue + ", " + checkBoxVal[q];
	                }
	                ddynamicCheckValue = ddynamicCheckValue.replaceFirst(", ", "");
	                checkBoxList = checkBoxList + ddynamicCheckValue + ",$,";
	                stateFormInfoObj.put(p + 415, ddynamicCheckValue);
	            } else {
	                checkBoxList = checkBoxList + checkSingele + ",$,";
	                stateFormInfoObj.put(p + 415, checkSingele);                
	            }
	        }
	        checkBoxList = checkBoxList.replace("null", "");
	
	        String radioButtonList = null;
	        String[] radioButton = new String[601];
	        for (int o = 1; o < radioButton.length; o++) {
	            radioButton[o] = req.getParameter("radioButton" + o);
	            radioButtonList = radioButtonList + radioButton[o] + ",$,";
	            stateFormInfoObj.put(o + 435, radioButton[o]);            
	        }
	        radioButtonList = radioButtonList.replace("null", "");
	        
	        req.setAttribute(attribTextFieldList, req.getAttribute(attribTextFieldList));
	        req.setAttribute(attribTextFieldAddrList, req.getAttribute(attribTextFieldAddrList));
	        req.setAttribute(attribTextFieldZipList, req.getAttribute(attribTextFieldZipList));
	        req.setAttribute(attribTextAreaList, req.getAttribute(attribTextAreaList));
	        req.setAttribute(attribSelectBoxList, req.getAttribute(attribSelectBoxList));
	        req.setAttribute(attribTextFieldDateList, attributeTextFieldDateList);
	        req.setAttribute(checkBoxesList, checkBoxList);
	        req.setAttribute(radioButtList, radioButtonList);
	        return stateFormInfoObj;
	    }
	    
	//  State Form Status
		  @Override
		  @Transactional
		  public String stateFormStatusInDB(HttpServletRequest req, HttpSession sn) {
			  LOGGER.debug("stateFormStatusInDB...Service");
		      String formStatus = null;
		      JSONObject stateFormInfoObj = stateFormsAllAttributesInfoFromJSP(req);
		      int reqAttrCount = 0;
		      String[] attributeIdsString = ((String) sn.getAttribute(attReqiTypeIdsInSn)).split(", ");
		      int totalReqAttrCount = attributeIdsString.length;
		
		      for (int i = 0; i < attributeIdsString.length; i++) {
		          String attrIdInStr = attributeIdsString[i];
		          int attrIdInt = Integer.parseInt(attrIdInStr);
		
		          String jSonObjVal = (String) stateFormInfoObj.get(attrIdInt);
		          if (jSonObjVal != null && jSonObjVal != "") {
		              reqAttrCount = reqAttrCount + 1;
		          }
		      }
		      
		      if (totalReqAttrCount == reqAttrCount) {
		          formStatus = "Ready for checkout";
		      } else {
		          formStatus = "In Progress";
		      }
		      return formStatus;
		  }
		  
//	    State Forms All Related Other Tables Update		    
		  @Override
		  @Transactional
		  public ModelAndView stateFormsAllRelatedOtherTablesUpdate(HttpServletRequest req, HttpSession sn) {
			  LOGGER.debug("stateFormsAllRelatedOtherTablesUpdate...Service");
			  ModelAndView mav = new ModelAndView();
			  Timestamp currentDate = currentDate(); 
			  List allStateFormsPaymentList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentRowVerification((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(typeOfDocumentInSn), (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(userChoiceInSn));
              if(!allStateFormsPaymentList.isEmpty()){
              AllStateFormsPaymentInfoSaving allStateFormsPaymentInfoSaving = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentModificationVerification((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(typeOfDocumentInSn), (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(userChoiceInSn));
              allStateFormsPaymentInfoSaving.setUserChoice(req.getParameter(attributeTextField1));
              allStateFormsPaymentInfoSaving.setUpdatedDate(currentDate);
              allStateFormsPaymentSavingDAOImpl.merge(allStateFormsPaymentInfoSaving);
              
              AllStateFormsCheckoutPaymentAndUserContactSaving allStCheckoutPaymentAndUserContact = allStateFormsCheckoutPaymentDAOImpl.allStateFormsCheckoutPaymentDataDeleteFromCart((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(typeOfDocumentInSn), (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(userChoiceInSn));
              allStCheckoutPaymentAndUserContact.setUserChoice(req.getParameter(attributeTextField1));
              allStCheckoutPaymentAndUserContact.setModifiedDate(currentDate);
              allStateFormsCheckoutPaymentDAOImpl.merge(allStCheckoutPaymentAndUserContact);
              
              List allStateFormsFreeFederalList = formFederalDataSavingDAOImpl.freeFederalFormsUserChoiceCheckingInDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(userChoiceInSn));
              if(!allStateFormsFreeFederalList.isEmpty()){
              FormFederalDocumentsDataSaving formFederalUpdateModel = formFederalDataSavingDAOImpl.formFederalFormsUserChoiceUpdateInDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(userChoiceInSn));                
	          formFederalUpdateModel.setUserChoice(req.getParameter(attributeTextField1));	          
	          formFederalUpdateModel.setModifiedDate(currentDate);
	          formFederalDataSavingDAOImpl.merge(formFederalUpdateModel);
	          
	          FormFederalAttributesAndValuesSaving freeFedAttrAndValModel = formFedAttrAndValuesSavingDAOImpl.freeFederalTaxUserChoiceUpdateInDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(userChoiceInSn));
	          freeFedAttrAndValModel.setUserChoice(req.getParameter(attributeTextField1));
	          freeFedAttrAndValModel.setModifiedDate(currentDate);
	          formFedAttrAndValuesSavingDAOImpl.merge(freeFedAttrAndValModel);
              }
              }              	       
		  return mav;
		  }

//	End point of method
		  
//  Additional Service Forms Start Point of method
			
//			Additional Service Form Modification Data Retrieve From DB Service
		  
		    @Override
		    @Transactional
		    public ModelAndView additionalFormModificationDataRetrieveFromDB(HttpServletRequest req, HttpSession sn) {
		    	LOGGER.debug("newAdditionalServiceFormSelectionFromDB...Service");        
		        ModelAndView mav;
		        int formId = takeFormIdFromDB(sn);
		        int userId = takeUserIdFromDB(sn);
		        sn.setAttribute(formIdInSn, formId);
		        sn.setAttribute(userIdInSn, userId);
//					Required values take from DB
		            List<AdditionalFormsAttributesInfo> addSerFormAttrList = additionalFormsAttrInfoDAOImpl.additionalServiceDynamicFormShowHideData((Integer) sn.getAttribute(formIdInSn));

		            List attRequiredList = new ArrayList();
		            List attRadioStatusList = new ArrayList();
		            List innerRadioList = new ArrayList();
		            List addAnotherList = new ArrayList();

		            for (AdditionalFormsAttributesInfo asFormsAttInfo : addSerFormAttrList) {
		                attRequiredList.add(asFormsAttInfo.getRequiredType());
		                attRadioStatusList.add(asFormsAttInfo.getRadioButtonStatus());
		                innerRadioList.add(asFormsAttInfo.getRadioButtonIdStatus());
		                addAnotherList.add(asFormsAttInfo.getAddAnotherRbstatus());
		            }

		            sn.setAttribute("AS_Attribute_Required_List", attRequiredList);
		            sn.setAttribute("AS_Attribute_RadioStatus_List", attRadioStatusList);
		            sn.setAttribute("AS_Attribute_InnerRadio_List", innerRadioList);
		            sn.setAttribute("AS_Attribute_AddAnother_List", addAnotherList);

//  Additional Service form attribute fields and values take from DB this is simple join method
		            List<Object> formFieldsAndValues = (List<Object>) additionalFormsAttrInfoDAOImpl.additionalServiceFormsDynamicFieldsAndValues((Integer) sn.getAttribute(formIdInSn));
		            List attrId = new ArrayList();
		            List attrType = new ArrayList();
		            List attNames = new ArrayList();
		            List attValue = new ArrayList();

		            Iterator formFieldsAndValuesIterator = formFieldsAndValues.iterator();
		            while (formFieldsAndValuesIterator.hasNext()) {
		                Object[] formFieldsAndValuesObj = (Object[]) formFieldsAndValuesIterator.next();

		                String attrbId = String.valueOf(formFieldsAndValuesObj[0]);
		                String attrbType = String.valueOf(formFieldsAndValuesObj[1]);
		                String attrbName = String.valueOf(formFieldsAndValuesObj[2]);
		                String attrbValue = String.valueOf(formFieldsAndValuesObj[3]);
		                attrId.add(attrbId);
		                attrType.add(attrbType);
		                attNames.add(attrbName.replace("null", ""));
		                attValue.add(attrbValue.replace("null", ""));
		            }

		            sn.setAttribute("AS_Attribute_Names_List", attNames);
		            sn.setAttribute("AS_Attribute_Values_List", attValue);

//					Additional Service forms Attribute Required type Ids take from DB 
		            List<Object> attrReqTypeIdsList = (List<Object>) additionalFormsAttrInfoDAOImpl.addSerAttributeReqTypeIDsList((Integer) sn.getAttribute(formIdInSn));
		            String attrReqTypeIds = attrReqTypeIdsList.toString();
		            attrReqTypeIds = attrReqTypeIds.replace("[", "");
		            attrReqTypeIds = attrReqTypeIds.replace("]", "");

//					Radio Button Status take from DB 			
		            List<Object> radioButtonStatus = (List<Object>) additionalFormsAttrInfoDAOImpl.additionalServiceRadioButtonStatus((Integer) sn.getAttribute(formIdInSn));

//					Form Modification Code		            
		                List<AdditionalFormsAttributesAndValuesSaving> addSerFormAttrAndValModel = addSerFormAttrAndValSavingDAOImpl.findByAddtionalSerViceFormsCapturedInfoFromDB((Integer) sn.getAttribute(userIdInSn), (Integer) sn.getAttribute(formIdInSn), (String) sn.getAttribute(userChoiceInSn));
		                String capturedInfoInDB = null;
		                for (AdditionalFormsAttributesAndValuesSaving addSerAttrAndValModel : addSerFormAttrAndValModel) {
		                    capturedInfoInDB = addSerAttrAndValModel.getCapturedInformation();
		                }

		                JSONParser parser = new JSONParser();
		                JSONObject capInfoJsonObject = new JSONObject();

		                try {
		                    Object parseObj = parser.parse(capturedInfoInDB);
		                    capInfoJsonObject = (JSONObject) parseObj;
		                } catch (ParseException e) {
		                	LOGGER.error("newAdditionalServiceFormSelectionFromDB " + e);
		                }
		                mav = newAdditionalServiceAttributesInfoSelection(capInfoJsonObject, req);                

		                AllStateFormsDataSaving stateFormsUpdateModel = allStateFormsDataSavingDAOImpl.allStateFormsDataUpdateInDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(typeOfDocumentInSn), (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(userChoiceInSn));
		                String stateFormPageValues = stateFormsUpdateModel.getPageVariableReference();

		                mav = new ModelAndView(addSerFormModification);		                
		                mav.addObject(textFieldsList, req.getAttribute(textFieldsList));
		                mav.addObject(textAreasList, req.getAttribute(textAreasList));
		                mav.addObject(dateFieldsList, req.getAttribute(dateFieldsList));
		                mav.addObject(selectBoxesList, req.getAttribute(selectBoxesList));
		                mav.addObject(zipCodesList, req.getAttribute(zipCodesList));
		                mav.addObject(radioButtList, req.getAttribute(radioButtList));
		                mav.addObject(checkBoxesList, req.getAttribute(checkBoxesList));		                
		                mav.addObject(stateFormsPageValue, stateFormPageValues);		                       
		            mav.addObject(radioButStatus, radioButtonStatus);            
		            sn.setAttribute(radioButStatus, radioButtonStatus);
		            sn.setAttribute(attReqiTypeIdsInSn, attrReqTypeIds);
		            mav.addObject(stateName, (String) sn.getAttribute(stateNameInSn));
			        mav.addObject(formName, (String) sn.getAttribute(formNameInSn));
			        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		        return mav;
		    }
		    
		    
//			New Additional Service Forms Attributes Info Selection
		    @Override
		    @Transactional
		    public ModelAndView newAdditionalServiceAttributesInfoSelection(JSONObject capInfoJsonObject, HttpServletRequest req) {
		        LOGGER.debug("newAdditionalServiceAttributesInfoSelection...Service");
		        ModelAndView mav = new ModelAndView();
		        
		        String textFieldList = null;
		        for (int i = 1; i <= 150; i++) {
		            String keyVal = String.valueOf(i);
		            String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
		            textFieldList = textFieldList + jSonObjVal + ",$,";
		        }
		        textFieldList = textFieldList.replace("null", "");

		        String textAreaList = null;
		        for (int l = 151; l <= 170; l++) {
		            String keyVal = String.valueOf(l);
		            String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
		            jSonObjVal = jSonObjVal.replaceAll("[\n\r]", " ");
		            textAreaList = textAreaList + jSonObjVal + ",$,";
		        }
		        textAreaList = textAreaList.replace("null", "");

		        String dateFieldList = null;
		        for (int m = 171; m <= 190; m++) {
		            String keyVal = String.valueOf(m);
		            String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
		            dateFieldList = dateFieldList + jSonObjVal + ",$,";
		        }
		        dateFieldList = dateFieldList.replace("null", "");

		        String selectBoxList = null;
		        for (int n = 191; n <= 240; n++) {
		            String keyVal = String.valueOf(n);
		            String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
		            selectBoxList = selectBoxList + jSonObjVal + ",$,";
		        }
		        selectBoxList = selectBoxList.replace("null", "");

		        String zipCodeList = null;
		        for (int k = 241; k <= 280; k++) {
		            String keyVal = String.valueOf(k);
		            String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
		            zipCodeList = zipCodeList + jSonObjVal + ",$,";
		        }
		        zipCodeList = zipCodeList.replace("null", "");

		        String radioButtonList = null;
		        for (int o = 281; o <= 430; o++) {
		            String keyVal = String.valueOf(o);
		            String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
		            radioButtonList = radioButtonList + jSonObjVal + ",$,";
		        }
		        radioButtonList = radioButtonList.replace("null", "");

		        String checkBoxList = null;
		        for (int p = 431; p <= 530; p++) {
		            String keyVal = String.valueOf(p);
		            String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
		            checkBoxList = checkBoxList + jSonObjVal + ",$,";
		        }
		        checkBoxList = checkBoxList.replace("null", "");
		        
		        req.setAttribute(textFieldsList, textFieldList);
		        req.setAttribute(textAreasList, textAreaList);
		        req.setAttribute(dateFieldsList, dateFieldList);
		        req.setAttribute(selectBoxesList, selectBoxList);
		        req.setAttribute(zipCodesList, zipCodeList);
		        req.setAttribute(radioButtList, radioButtonList);
		        req.setAttribute(checkBoxesList, checkBoxList);        
		        return mav;
		    }
		    
//			Additional Service Form modification Service Implementation	
		    @Override
		    @Transactional
		    public ModelAndView additionalServiceFormDataSavingAndUpdatingInDB(HttpServletRequest req, HttpSession sn) {
		        LOGGER.debug("additionalServiceFormDataSavingAndUpdatingInDB...Service");		        
		        String regAgentPrice = additionalServiceFormRegistredAgentPrice(req, sn);
		        JSONObject stateFormInfoObj = addSerFormsAllAttributesInfoFromJSP(req);
		        ModelAndView mav;
		        //Already Exit User choice when ever directly clicking enter with out using mouse
		        List userAEChoiceList = allStateFormsDataSavingDAOImpl.allStateFormsAlreadyExittUserChoiceInDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(typeOfDocumentInSn), (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(formNameInSn), req.getParameter(textField1));
		        String alreadyExitChoice = null;
		        if (!userAEChoiceList.isEmpty()) {
		        	AllStateFormsDataSaving stateFormsAEModel = allStateFormsDataSavingDAOImpl.findByallStateFormsDataFromDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(typeOfDocumentInSn), (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(stateNameInSn), req.getParameter(textField1));
		            alreadyExitChoice = stateFormsAEModel.getUserChoice();
		        }

		        if (alreadyExitChoice != null && !alreadyExitChoice.equals(sn.getAttribute(userChoiceInSn))) {
		            mav = new ModelAndView(addSerFormModification);
		            mav.addObject("AlreadyExitChoice", "AlreadyExitUserChoice");
		        } else {
		//Data Saving to DB tables
		            String formStatus = additionalServiceFormStatusInDB(req, sn);

		            String jsonStringObj = stateFormInfoObj.toString();
		            jsonStringObj = jsonStringObj.replace("null", "\"\"");
		            int formId = takeFormIdFromDB(sn);
		            Forms formsId = new Forms();
		            formsId.setFormId(formId);
		            Timestamp currentDate = currentDate();
		            
//					Only Additional Service  Forms Data Updating in DB		                
		                AdditionalFormsAttributesAndValuesSaving addAttrAndValModel = addSerFormAttrAndValSavingDAOImpl.findByAddSerFormsAttributesAndValuesFromDB((Integer) sn.getAttribute(userIdInSn), formId, (String) sn.getAttribute(userChoiceInSn));
		                addAttrAndValModel.setUserChoice(req.getParameter(textField1));
		                addAttrAndValModel.setCapturedInformation(jsonStringObj);
		                addAttrAndValModel.setModifiedDate(currentDate);
		                addSerFormAttrAndValSavingDAOImpl.merge(addAttrAndValModel);

		                AllStateFormsDataSaving stateFormsModel = allStateFormsDataSavingDAOImpl.allStateFormsDataUpdateInDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(typeOfDocumentInSn), (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(userChoiceInSn));
		                stateFormsModel.setUserChoice(req.getParameter(textField1));
		                stateFormsModel.setStatus(formStatus);
		                stateFormsModel.setModifiedDate(currentDate);
		                stateFormsModel.setRegisteredAgentPrice(regAgentPrice);
		                stateFormsModel.setPageVariableReference(req.getParameter(stateFormsPageValue));
		                stateFormsModel.setFormStatus(req.getParameter(stateFormHiddenVarb));
		                allStateFormsDataSavingDAOImpl.merge(stateFormsModel);
		           
//		            All related tables updating
	                if(!(sn.getAttribute(userChoiceInSn)).equals(req.getParameter(textField1))){		                	
	                    mav = additionalFormsAllRelatedOtherTablesUpdate(req, sn); 
	                }
		            sn.setAttribute(userChoiceInSn, req.getParameter(textField1));
		            if (req.getParameter(stateFormHiddenVarb) != null && ("Finished").equals(req.getParameter(stateFormHiddenVarb))) {
		                mav = completedFormsRedirection(req, sn);
		            } else {
		                mav = new ModelAndView(addSerFormModification);
		            }
		            mav.addObject(stateFormsPageValue, req.getParameter(stateFormsPageValue));
		            sn.setAttribute(asAttrbRequiredList, sn.getAttribute(asAttrbRequiredList));
		            sn.setAttribute(asAttrbRadioStatusList, sn.getAttribute(asAttrbRadioStatusList));
		            sn.setAttribute(asAttrbInnerRadioList, sn.getAttribute(asAttrbInnerRadioList));
		            sn.setAttribute(asAttrbAddAnotherList, sn.getAttribute(asAttrbAddAnotherList));

		            sn.setAttribute(asAttrbNamesList, sn.getAttribute(asAttrbNamesList));
		            sn.setAttribute(asAttrbValuesList, sn.getAttribute(asAttrbValuesList));
		            sn.setAttribute(radioButStatus, sn.getAttribute(radioButStatus));		            
		        }
		        mav.addObject(textFieldsList, req.getAttribute(textFieldsList));
		        mav.addObject(textAreasList, req.getAttribute(textAreasList));
		        mav.addObject(dateFieldsList, req.getAttribute(dateFieldsList));
		        mav.addObject(selectBoxesList, req.getAttribute(selectBoxesList));
		        mav.addObject(zipCodesList, req.getAttribute(zipCodesList));
		        mav.addObject(radioButtonsList, req.getAttribute(radioButtonsList));
		        mav.addObject(checkBoxsList, req.getAttribute(checkBoxsList));

		        mav.addObject(stateName, (String) sn.getAttribute(stateNameInSn));
		        mav.addObject(formName, (String) sn.getAttribute(formNameInSn));
		        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		        return mav;
		    }

//			Additional Service Registered Agent price
		    @Override
		    @Transactional
		    public String additionalServiceFormRegistredAgentPrice(HttpServletRequest req, HttpSession sn) {
		        String regAgentPrice = null;
		        if (req.getParameter(radioButton100) != null && ("yes100").equals(req.getParameter(radioButton100))) {
		            req.setAttribute(textField100, "LegalNod Registered Agent");
		            List<Object> processingPrice = (List<Object>) federalFormsPriceInfoDAOImpl.federalFormPriceProperty("Registered Agent");
		            if (!processingPrice.isEmpty()) {
		                regAgentPrice = processingPrice.get(0).toString();
		            }
		        } else {
		            req.setAttribute(textField100, "");
		            regAgentPrice = null;
		        }		        
		        return regAgentPrice;
		    }
		    
//		    Additional Service Form Status
		    @Override
		    @Transactional
		    public String additionalServiceFormStatusInDB(HttpServletRequest req, HttpSession sn) {
		        String formStatus = null;
		        JSONObject stateFormInfoObj = addSerFormsAllAttributesInfoFromJSP(req);
		        int reqAttrCount = 0;
		        String[] attributeIdsString = ((String) sn.getAttribute(attReqiTypeIdsInSn)).split(", ");
		        int totalReqAttrCount = attributeIdsString.length;

		        for (int i = 0; i < attributeIdsString.length; i++) {
		            String attrIdInStr = attributeIdsString[i];
		            int attrIdInt = Integer.parseInt(attrIdInStr);

		            String jSonObjVal = (String) stateFormInfoObj.get(attrIdInt);
		            if (jSonObjVal != null && jSonObjVal != "") {
		                reqAttrCount = reqAttrCount + 1;
		            }
		        }

		        if (totalReqAttrCount == reqAttrCount) {
		            formStatus = "Ready for checkout";
		        } else {
		            formStatus = "In Progress";
		        }
		        return formStatus;
		    }

//			Additional Service Data take from JSP
		    @Override
		    @Transactional
		    public JSONObject additionalServiceFormsFiveAttributesInfoFromJSP(HttpServletRequest req) {
		        LOGGER.debug("additionalServiceFormsInfoFromJSP...Service");
		        JSONObject stateFormInfoObj = new JSONObject();
		        String textFieldList = null;
		        String[] textField = new String[151];
		        for (int i = 1; i < textField.length; i++) {
		            if (i == 100) {
		                textFieldList = textFieldList + req.getAttribute(textField100) + ",$,";
		                stateFormInfoObj.put(i, req.getAttribute(textField100));

		            } else {
		                textField[i] = req.getParameter("textField" + i);
		                textFieldList = textFieldList + textField[i] + ",$,";
		                stateFormInfoObj.put(i, textField[i]);
		            }
		        }
		        textFieldList = textFieldList.replace("null", "");

		        String textAreaList = null;
		        String[] textArea = new String[21];
		        for (int l = 1; l < textArea.length; l++) {
		            textArea[l] = req.getParameter("textArea" + l);
		            textAreaList = textAreaList + textArea[l] + ",$,";
		            stateFormInfoObj.put(l + 150, textArea[l]);
		        }
		        textAreaList = textAreaList.replace("null", "");
		        textAreaList = textAreaList.replaceAll("[\n\r]", " ");

		        String dateFieldList = null;
		        String[] dateField = new String[21];
		        for (int m = 1; m < dateField.length; m++) {
		            dateField[m] = req.getParameter("dateField" + m);
		            dateFieldList = dateFieldList + dateField[m] + ",$,";
		            stateFormInfoObj.put(m + 170, dateField[m]);
		        }
		        dateFieldList = dateFieldList.replace("null", "");

		        String selectBoxList = null;
		        String[] selectBox = new String[51];
		        for (int n = 1; n < selectBox.length; n++) {
		            selectBox[n] = req.getParameter("selectBox" + n);
		            selectBoxList = selectBoxList + selectBox[n] + ",$,";
		            stateFormInfoObj.put(n + 190, selectBox[n]);
		        }
		        selectBoxList = selectBoxList.replace("null", "");

		        String zipCodeList = null;
		        String[] zipCode = new String[41];
		        for (int k = 1; k < zipCode.length; k++) {
		            zipCode[k] = req.getParameter("zipCode" + k);
		            zipCodeList = zipCodeList + zipCode[k] + ",$,";
		            stateFormInfoObj.put(k + 240, zipCode[k]);
		        }
		        zipCodeList = zipCodeList.replace("null", "");

		        req.setAttribute(textFieldsList, textFieldList);
		        req.setAttribute(textAreasList, textAreaList);
		        req.setAttribute(dateFieldsList, dateFieldList);
		        req.setAttribute(selectBoxesList, selectBoxList);
		        req.setAttribute(zipCodesList, zipCodeList);
		        return stateFormInfoObj;
		    }

//			Additional Service Data take from JSP
		    @Override
		    @Transactional
		    public JSONObject addSerFormsAllAttributesInfoFromJSP(HttpServletRequest req) {
		        LOGGER.debug("addSerFormsattributesCheckboxInfoFromJSP...Service");
		        JSONObject stateFormInfoObj = additionalServiceFormsFiveAttributesInfoFromJSP(req);

		        String radioButtonList = null;
		        String[] radioButton = new String[151];
		        for (int o = 1; o < radioButton.length; o++) {
		            radioButton[o] = req.getParameter("radioButton" + o);
		            radioButtonList = radioButtonList + radioButton[o] + ",$,";
		            stateFormInfoObj.put(o + 280, radioButton[o]);
		        }
		        radioButtonList = radioButtonList.replace("null", "");

		        String checkBoxList = null;
		        String[] checkBox = new String[101];
		        for (int p = 1; p < checkBox.length; p++) {
		            String checkSingele = req.getParameter("checkBox" + p);
		            if (checkSingele != null) {
		                String[] checkBoxVal = req.getParameterValues("checkBox" + p);
		                String ddynamicCheckValue = "";
		                for (int q = 0; q < checkBoxVal.length; q++) {
		                    ddynamicCheckValue = ddynamicCheckValue + ", " + checkBoxVal[q];
		                }
		                ddynamicCheckValue = ddynamicCheckValue.replaceFirst(", ", "");
		                checkBoxList = checkBoxList + ddynamicCheckValue + ",$,";
		                stateFormInfoObj.put(p + 430, ddynamicCheckValue);
		            } else {
		                checkBoxList = checkBoxList + checkSingele + ",$,";
		                stateFormInfoObj.put(p + 430, checkSingele);
		            }
		        }
		        checkBoxList = checkBoxList.replace("null", "");

		        req.setAttribute(textFieldsList, req.getAttribute(textFieldsList));
		        req.setAttribute(textAreasList, req.getAttribute(textAreasList));
		        req.setAttribute(dateFieldsList, req.getAttribute(dateFieldsList));
		        req.setAttribute(selectBoxesList, req.getAttribute(selectBoxesList));
		        req.setAttribute(zipCodesList, req.getAttribute(zipCodesList));
		        req.setAttribute(radioButtonsList, radioButtonList);
		        req.setAttribute(checkBoxsList, checkBoxList);
		        return stateFormInfoObj;
		    }		    
		    
//		    Additional Forms All Related Other Tables Update		    
			  @Override
			  @Transactional
			  public ModelAndView additionalFormsAllRelatedOtherTablesUpdate(HttpServletRequest req, HttpSession sn) {
				  LOGGER.debug("additionalFormsAllRelatedOtherTablesUpdate...Service");
				  ModelAndView mav = new ModelAndView();
				  Timestamp currentDate = currentDate(); 
				  
				  List allStateFormsPaymentList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentRowVerification((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(typeOfDocumentInSn), (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(userChoiceInSn));
	                if (!allStateFormsPaymentList.isEmpty()) {
	                    AllStateFormsPaymentInfoSaving allStateFormsPaymentInfoSaving = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentModificationVerification((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(typeOfDocumentInSn), (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(userChoiceInSn));
	                    allStateFormsPaymentInfoSaving.setUserChoice(req.getParameter(textField1));
	                    allStateFormsPaymentInfoSaving.setUpdatedDate(currentDate);
	                    allStateFormsPaymentSavingDAOImpl.merge(allStateFormsPaymentInfoSaving);
	                
	                    AllStateFormsCheckoutPaymentAndUserContactSaving allStCheckoutPaymentAndUserContact = allStateFormsCheckoutPaymentDAOImpl.allStateFormsCheckoutPaymentDataDeleteFromCart((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(typeOfDocumentInSn), (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(userChoiceInSn));
	                    allStCheckoutPaymentAndUserContact.setUserChoice(req.getParameter(textField1));
	                    allStCheckoutPaymentAndUserContact.setModifiedDate(currentDate);
	                    allStateFormsCheckoutPaymentDAOImpl.merge(allStCheckoutPaymentAndUserContact);
	                }             	       
			  return mav;
			  }
		    
//			End point of method
		  
		  
//  State Tax ID Forms Start Point of method
			
//			State Tax ID Form Modification Data Retrieve From DB Service
		    @Override
		    @Transactional
		    public ModelAndView stateTaxIdFormModificationDataRetrieveFromDB(HttpServletRequest req, HttpSession sn) {
		    	LOGGER.debug("stateTaxIdFormModificationDataRetrieveFromDB...Service");		    	
		    	ModelAndView mav;
		    	int formId = takeFormIdFromDB(sn);
		        int userId = takeUserIdFromDB(sn);
		        sn.setAttribute(formIdInSn, formId);
		        sn.setAttribute(userIdInSn, userId);
//					Required values take from DB
		            List<StateTaxFormsAttributesInfo> addSerFormAttrList = stateTaxFormsAttrInfoDAOImpl.stateTaxIdDynamicFormShowHideData((Integer) sn.getAttribute(formIdInSn));

		            List attRequiredList = new ArrayList();
		            List attRadioStatusList = new ArrayList();
		            List innerRadioList = new ArrayList();
		            List addAnotherList = new ArrayList();

		            for (StateTaxFormsAttributesInfo stiFormsAttInfo : addSerFormAttrList) {
		                attRequiredList.add(stiFormsAttInfo.getRequiredType());
		                attRadioStatusList.add(stiFormsAttInfo.getRadioButtonStatus());
		                innerRadioList.add(stiFormsAttInfo.getRadioButtonIdStatus());
		                addAnotherList.add(stiFormsAttInfo.getAddAnotherRbstatus());
		            }

		            sn.setAttribute("STI_Attribute_Required_List", attRequiredList);
		            sn.setAttribute("STI_Attribute_RadioStatus_List", attRadioStatusList);
		            sn.setAttribute("STI_Attribute_InnerRadio_List", innerRadioList);
		            sn.setAttribute("STI_Attribute_AddAnother_List", addAnotherList);

//				    State Tax Id form attribute fields and values take from DB this is simple join method
		            List<Object> formFieldsAndValues = (List<Object>) stateTaxFormsAttrInfoDAOImpl.stateTaxIdFormsDynamicFieldsAndValues((Integer) sn.getAttribute(formIdInSn));
		            List attrId = new ArrayList();
		            List attrType = new ArrayList();
		            List attNames = new ArrayList();
		            List attValue = new ArrayList();

		            Iterator formFieldsAndValuesIterator = formFieldsAndValues.iterator();
		            while (formFieldsAndValuesIterator.hasNext()) {
		                Object[] formFieldsAndValuesObj = (Object[]) formFieldsAndValuesIterator.next();

		                String attrbId = String.valueOf(formFieldsAndValuesObj[0]);
		                String attrbType = String.valueOf(formFieldsAndValuesObj[1]);
		                String attrbName = String.valueOf(formFieldsAndValuesObj[2]);
		                String attrbValue = String.valueOf(formFieldsAndValuesObj[3]);
		                attrId.add(attrbId);
		                attrType.add(attrbType);
		                attNames.add(attrbName.replace("null", ""));
		                attValue.add(attrbValue.replace("null", ""));
		            }
		            
		            sn.setAttribute("STI_Attribute_Names_List", attNames);
		            sn.setAttribute("STI_Attribute_Values_List", attValue);

//					State Tax Id forms Attribute Required type Ids take from DB 
		            List<Object> attrReqTypeIdsList = (List<Object>) stateTaxFormsAttrInfoDAOImpl.stateTaxIdAttributeReqTypeIDsList((Integer) sn.getAttribute(formIdInSn));
		            String attrReqTypeIds = attrReqTypeIdsList.toString();
		            attrReqTypeIds = attrReqTypeIds.replace("[", "");
		            attrReqTypeIds = attrReqTypeIds.replace("]", "");

//					Radio Button Status take from DB 			
		            List<Object> radioButtonStatus = (List<Object>) stateTaxFormsAttrInfoDAOImpl.stateTaxIdRadioButtonStatus((Integer) sn.getAttribute(formIdInSn));

//					Form Modification Code
		                List<StateTaxFormsAttributesAndValuesSaving> stiFormAttrAndValModel = stateTaxFormsAttrAndValuesSavingDAOImpl.findByallStateTaxIdFormsCapturedInfoFromDB((Integer) sn.getAttribute(userIdInSn), (Integer) sn.getAttribute(formIdInSn), (String) sn.getAttribute(userChoiceInSn));
		                String capturedInfoInDB = null;
		                for (StateTaxFormsAttributesAndValuesSaving stateTaxFormAttrAndValModel : stiFormAttrAndValModel) {
		                    capturedInfoInDB = stateTaxFormAttrAndValModel.getCapturedInformation();
		                }		                
		                JSONParser parser = new JSONParser();
		                JSONObject capInfoJsonObject = new JSONObject();

		                try {
		                    Object parseObj = parser.parse(capturedInfoInDB);
		                    capInfoJsonObject = (JSONObject) parseObj;
		                } catch (ParseException e) {
		                	LOGGER.error("newStateTaxIdFormSelectionFromDB " + e);
		                }
		                mav = newStateTaxIdAttributesInfoSelection(capInfoJsonObject, req);                

		                AllStateFormsDataSaving stateFormsUpdateModel = allStateFormsDataSavingDAOImpl.allStateFormsDataUpdateInDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(typeOfDocumentInSn), (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(userChoiceInSn));
		                String stateFormPageValues = stateFormsUpdateModel.getPageVariableReference();

		                mav = new ModelAndView(stTaxIdFormModification);
		                mav.addObject(textFieldsList, req.getAttribute(textFieldsList));
		                mav.addObject(textAreasList, req.getAttribute(textAreasList));
		                mav.addObject(dateFieldsList, req.getAttribute(dateFieldsList));
		                mav.addObject(selectBoxesList, req.getAttribute(selectBoxesList));
		                mav.addObject(zipCodesList, req.getAttribute(zipCodesList));
		                mav.addObject(radioButtList, req.getAttribute(radioButtList));
		                mav.addObject(checkBoxesList, req.getAttribute(checkBoxesList));		                
		                mav.addObject(stateFormsPageValue, stateFormPageValues);
		                      
		            mav.addObject(radioButStatus, radioButtonStatus);            
		            sn.setAttribute(radioButStatus, radioButtonStatus);
		            sn.setAttribute(attReqiTypeIdsInSn, attrReqTypeIds);
		            mav.addObject(stateName, (String) sn.getAttribute(stateNameInSn));
			        mav.addObject(formName, (String) sn.getAttribute(formNameInSn));
			        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		        return mav;
		    }
		    
		    
//			New State Tax Id Forms Attributes Info Selection
		    @Override
		    @Transactional
		    public ModelAndView newStateTaxIdAttributesInfoSelection(JSONObject capInfoJsonObject, HttpServletRequest req) {
		        LOGGER.debug("newStateTaxIdAttributesInfoSelection...Service");
		        ModelAndView mav = new ModelAndView();
		        
		        String textFieldList = null;
		        for (int i = 1; i <= 160; i++) {
		            String keyVal = String.valueOf(i);
		            String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
		            textFieldList = textFieldList + jSonObjVal + ",$,";
		        }
		        textFieldList = textFieldList.replace("null", "");

		        String textAreaList = null;
		        for (int l = 161; l <= 180; l++) {
		            String keyVal = String.valueOf(l);
		            String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
		            jSonObjVal = jSonObjVal.replaceAll("[\n\r]", " ");
		            textAreaList = textAreaList + jSonObjVal + ",$,";
		        }
		        textAreaList = textAreaList.replace("null", "");

		        String dateFieldList = null;
		        for (int m = 181; m <= 230; m++) {
		            String keyVal = String.valueOf(m);
		            String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
		            dateFieldList = dateFieldList + jSonObjVal + ",$,";
		        }
		        dateFieldList = dateFieldList.replace("null", "");

		        String selectBoxList = null;
		        for (int n = 231; n <= 260; n++) {
		            String keyVal = String.valueOf(n);
		            String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
		            selectBoxList = selectBoxList + jSonObjVal + ",$,";
		        }
		        selectBoxList = selectBoxList.replace("null", "");

		        String zipCodeList = null;
		        for (int k = 261; k <= 310; k++) {
		            String keyVal = String.valueOf(k);
		            String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
		            zipCodeList = zipCodeList + jSonObjVal + ",$,";
		        }
		        zipCodeList = zipCodeList.replace("null", "");

		        String radioButtonList = null;
		        for (int o = 311; o <= 610; o++) {
		            String keyVal = String.valueOf(o);
		            String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
		            radioButtonList = radioButtonList + jSonObjVal + ",$,";
		        }
		        radioButtonList = radioButtonList.replace("null", "");

		        String checkBoxList = null;
		        for (int p = 611; p <= 710; p++) {
		            String keyVal = String.valueOf(p);
		            String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
		            checkBoxList = checkBoxList + jSonObjVal + ",$,";
		        }
		        checkBoxList = checkBoxList.replace("null", "");
		        
		        req.setAttribute(textFieldsList, textFieldList);
		        req.setAttribute(textAreasList, textAreaList);
		        req.setAttribute(dateFieldsList, dateFieldList);
		        req.setAttribute(selectBoxesList, selectBoxList);
		        req.setAttribute(zipCodesList, zipCodeList);
		        req.setAttribute(radioButtList, radioButtonList);
		        req.setAttribute(checkBoxesList, checkBoxList);       
		        return mav;
		    }
		    
//			State Tax ID Form modification Service Implementation	
		    @Override
		    @Transactional
		    public ModelAndView stateTaxIdFormDataSavingAndUpdatingInDB(HttpServletRequest req, HttpSession sn) {
		        LOGGER.debug("stateTaxIdFormDataSavingAndUpdatingInDB...Service");
		        JSONObject stateFormInfoObj = stateTaxFormsAttributesInfoFromJSP(req);
		        ModelAndView mav;
		        //Already Exit User choice when ever directly clicking enter with out using mouse
		        List userAEChoiceList = allStateFormsDataSavingDAOImpl.allStateFormsAlreadyExittUserChoiceInDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(typeOfDocumentInSn), (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(formNameInSn), req.getParameter(textField1));
		        String alreadyExitChoice = null;
		        if (!userAEChoiceList.isEmpty()) {
		        	AllStateFormsDataSaving stateFormsAEModel = allStateFormsDataSavingDAOImpl.findByallStateFormsDataFromDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(typeOfDocumentInSn), (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(stateNameInSn), req.getParameter(textField1));
		            alreadyExitChoice = stateFormsAEModel.getUserChoice();
		        }
		        if (alreadyExitChoice != null && !alreadyExitChoice.equals(sn.getAttribute(userChoiceInSn))) {
		            mav = new ModelAndView(stTaxIdFormModification);
		            mav.addObject("AlreadyExitChoice", "AlreadyExitUserChoice");
		        } else {
		            //Data Saving to DB tables
		            String formStatus = stateTaxIdFormStatusInDB(req, sn);
		            String jsonStringObj = stateFormInfoObj.toString();
		            jsonStringObj = jsonStringObj.replace("null", "\"\"");
		            Forms formsId = new Forms();
		            formsId.setFormId((Integer) sn.getAttribute(formIdInSn));
		            Timestamp currentDate = currentDate();
		            
//					Only State tax id Forms Data Update in DB		                
		                StateTaxFormsAttributesAndValuesSaving stiAttrAndValModel = stateTaxAttrAndValuesSavingDAOImpl.findByStateTaxIdFormsAttributesAndValuesFromDB((Integer) sn.getAttribute(userIdInSn), (Integer) sn.getAttribute(formIdInSn), (String) sn.getAttribute(userChoiceInSn));
		                stiAttrAndValModel.setUserChoice(req.getParameter(textField1));
		                stiAttrAndValModel.setCapturedInformation(jsonStringObj);
		                stiAttrAndValModel.setModifiedDate(currentDate);
		                stateTaxAttrAndValuesSavingDAOImpl.merge(stiAttrAndValModel);

		                AllStateFormsDataSaving stateFormsModel = allStateFormsDataSavingDAOImpl.allStateFormsDataUpdateInDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(typeOfDocumentInSn), (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(userChoiceInSn));
		                stateFormsModel.setUserChoice(req.getParameter(textField1));
		                stateFormsModel.setStatus(formStatus);
		                stateFormsModel.setModifiedDate(currentDate);
		                stateFormsModel.setPageVariableReference(req.getParameter(stateFormsPageValue));
		                stateFormsModel.setFormStatus(req.getParameter(stateFormHiddenVarb));
		                allStateFormsDataSavingDAOImpl.merge(stateFormsModel);		              
		           
//		            All related tables updating
	                if(!(sn.getAttribute(userChoiceInSn)).equals(req.getParameter(textField1))){
	                    mav = stateTaxIdFormsAllRelatedOtherTablesUpdate(req, sn); 
	                }
		            sn.setAttribute(userChoiceInSn, req.getParameter(textField1));
		            if (req.getParameter(stateFormHiddenVarb) != null && ("Finished").equals(req.getParameter(stateFormHiddenVarb))) {
		                mav = completedFormsRedirection(req, sn);
		            } else {
		                mav = new ModelAndView(stTaxIdFormModification);
		            }
		            mav.addObject(stateFormsPageValue, req.getParameter(stateFormsPageValue));
		            sn.setAttribute(stiAttrbRequiredList, sn.getAttribute(stiAttrbRequiredList));
		            sn.setAttribute(stiAttrbRadioStatusList, sn.getAttribute(stiAttrbRadioStatusList));
		            sn.setAttribute(stiAttrbInnerRadioList, sn.getAttribute(stiAttrbInnerRadioList));
		            sn.setAttribute(stiAttrbAddAnotherList, sn.getAttribute(stiAttrbAddAnotherList));

		            sn.setAttribute(stiAttrbNamesList, sn.getAttribute(stiAttrbNamesList));
		            sn.setAttribute(stiAttrbValuesList, sn.getAttribute(stiAttrbValuesList));
		            sn.setAttribute(radioButStatus, sn.getAttribute(radioButStatus));		            
		        }
		        mav.addObject(textFieldsList, req.getAttribute(textFieldsList));
		        mav.addObject(textAreasList, req.getAttribute(textAreasList));
		        mav.addObject(dateFieldsList, req.getAttribute(dateFieldsList));
		        mav.addObject(selectBoxesList, req.getAttribute(selectBoxesList));
		        mav.addObject(zipCodesList, req.getAttribute(zipCodesList));
		        mav.addObject(radioButtonsList, req.getAttribute(radioButtonsList));
		        mav.addObject(checkBoxsList, req.getAttribute(checkBoxsList));

		        mav.addObject(stateName, (String) sn.getAttribute(stateNameInSn));
		        mav.addObject(formName, (String) sn.getAttribute(formNameInSn));
		        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		        return mav;
		    }

//			State Tax ID Form Attributes info from JSP
		    @Override
		    @Transactional
		    public JSONObject stateTaxFormsAttributesInfoFromJSP(HttpServletRequest req) {
		        LOGGER.debug("stateTaxFormsAttributesInfoFromJSP...Service");
		        JSONObject stateFormInfoObj = new JSONObject();
		        String textFieldList = null;
		        String[] textField = new String[161];
		        for (int i = 1; i < textField.length; i++) {
		            textField[i] = req.getParameter("textField" + i);
		            textFieldList = textFieldList + textField[i] + ",$,";
		            textFieldList = textFieldList + req.getAttribute("textField100");
		            stateFormInfoObj.put(i, textField[i]);
		        }
		        textFieldList = textFieldList.replace("null", "");

		        String textAreaList = null;
		        String[] textArea = new String[21];
		        for (int l = 1; l < textArea.length; l++) {
		            textArea[l] = req.getParameter("textArea" + l);
		            textAreaList = textAreaList + textArea[l] + ",$,";
		            stateFormInfoObj.put(l + 160, textArea[l]);
		        }
		        textAreaList = textAreaList.replace("null", "");
		        textAreaList = textAreaList.replaceAll("[\n\r]", " ");

		        String dateFieldList = null;
		        String[] dateField = new String[51];
		        for (int m = 1; m < dateField.length; m++) {
		            dateField[m] = req.getParameter("dateField" + m);
		            dateFieldList = dateFieldList + dateField[m] + ",$,";
		            stateFormInfoObj.put(m + 180, dateField[m]);
		        }
		        dateFieldList = dateFieldList.replace("null", "");

		        String selectBoxList = null;
		        String[] selectBox = new String[31];
		        for (int n = 1; n < selectBox.length; n++) {
		            selectBox[n] = req.getParameter("selectBox" + n);
		            selectBoxList = selectBoxList + selectBox[n] + ",$,";
		            stateFormInfoObj.put(n + 230, selectBox[n]);
		        }
		        selectBoxList = selectBoxList.replace("null", "");

		        String zipCodeList = null;
		        String[] zipCode = new String[51];
		        for (int k = 1; k < zipCode.length; k++) {
		            zipCode[k] = req.getParameter("zipCode" + k);
		            zipCodeList = zipCodeList + zipCode[k] + ",$,";
		            stateFormInfoObj.put(k + 260, zipCode[k]);
		        }
		        zipCodeList = zipCodeList.replace("null", "");

		        String radioButtonList = null;
		        String[] radioButton = new String[301];
		        for (int o = 1; o < radioButton.length; o++) {
		            radioButton[o] = req.getParameter("radioButton" + o);
		            radioButtonList = radioButtonList + radioButton[o] + ",$,";
		            stateFormInfoObj.put(o + 310, radioButton[o]);
		        }
		        radioButtonList = radioButtonList.replace("null", "");

		        String checkBoxList = null;
		        String[] checkBox = new String[101];
		        for (int p = 1; p < checkBox.length; p++) {
		            String checkSingele = req.getParameter("checkBox" + p);
		            if (checkSingele != null) {
		                String[] checkBoxVal = req.getParameterValues("checkBox" + p);
		                String ddynamicCheckValue = "";
		                for (int q = 0; q < checkBoxVal.length; q++) {
		                    ddynamicCheckValue = ddynamicCheckValue + ", " + checkBoxVal[q];
		                }
		                ddynamicCheckValue = ddynamicCheckValue.replaceFirst(", ", "");
		                checkBoxList = checkBoxList + ddynamicCheckValue + ",$,";
		                stateFormInfoObj.put(p + 610, ddynamicCheckValue);
		            } else {
		                checkBoxList = checkBoxList + checkSingele + ",$,";
		                stateFormInfoObj.put(p + 610, checkSingele);
		            }
		        }
		        checkBoxList = checkBoxList.replace("null", "");

		        req.setAttribute(textFieldsList, textFieldList);
		        req.setAttribute(textAreasList, textAreaList);
		        req.setAttribute(dateFieldsList, dateFieldList);
		        req.setAttribute(selectBoxesList, selectBoxList);
		        req.setAttribute(zipCodesList, zipCodeList);
		        req.setAttribute(radioButtonsList, radioButtonList);
		        req.setAttribute(checkBoxsList, checkBoxList);
		        return stateFormInfoObj;
		    }

		// State Tax ID Form Status
		    @Override
		    @Transactional
		    public String stateTaxIdFormStatusInDB(HttpServletRequest req, HttpSession sn) {
		        String formStatus = null;
		        JSONObject stateFormInfoObj = stateTaxFormsAttributesInfoFromJSP(req);
		        int reqAttrCount = 0;
		        String[] attributeIdsString = ((String) sn.getAttribute(attReqiTypeIdsInSn)).split(", ");
		        int totalReqAttrCount = attributeIdsString.length;

		        for (int i = 0; i < attributeIdsString.length; i++) {
		            String attrIdInStr = attributeIdsString[i];
		            int attrIdInt = Integer.parseInt(attrIdInStr);

		            String jSonObjVal = (String) stateFormInfoObj.get(attrIdInt);
		            if (jSonObjVal != null && jSonObjVal != "") {
		                reqAttrCount = reqAttrCount + 1;
		            }
		        }
		        if (totalReqAttrCount == reqAttrCount) {
		            formStatus = "Ready for checkout";
		        } else {
		            formStatus = "In Progress";
		        }
		        return formStatus;
		    }
		    
//		    State Tax Id Forms All Related Other Tables Update		    
			  @Override
			  @Transactional
			  public ModelAndView stateTaxIdFormsAllRelatedOtherTablesUpdate(HttpServletRequest req, HttpSession sn) {
				  LOGGER.debug("stateTaxIdFormsAllRelatedOtherTablesUpdate...Service");
				  ModelAndView mav = new ModelAndView();
				  Timestamp currentDate = currentDate(); 
				  
				  List allStateFormsPaymentList = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentRowVerification((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(typeOfDocumentInSn), (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(userChoiceInSn));
	                if (!allStateFormsPaymentList.isEmpty()) {
	                    AllStateFormsPaymentInfoSaving allStateFormsPaymentInfoSaving = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentModificationVerification((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(typeOfDocumentInSn), (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(userChoiceInSn));
	                    allStateFormsPaymentInfoSaving.setUserChoice(req.getParameter(textField1));
	                    allStateFormsPaymentInfoSaving.setUpdatedDate(currentDate);
	                    allStateFormsPaymentSavingDAOImpl.merge(allStateFormsPaymentInfoSaving);
	                
	                    AllStateFormsCheckoutPaymentAndUserContactSaving allStCheckoutPaymentAndUserContact = allStateFormsCheckoutPaymentDAOImpl.allStateFormsCheckoutPaymentDataDeleteFromCart((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(typeOfDocumentInSn), (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(userChoiceInSn));
	                    allStCheckoutPaymentAndUserContact.setUserChoice(req.getParameter(textField1));
	                    allStCheckoutPaymentAndUserContact.setModifiedDate(currentDate);
	                    allStateFormsCheckoutPaymentDAOImpl.merge(allStCheckoutPaymentAndUserContact);
	                }             	       
			  return mav;
			  }
		    
//			End point of method
			  
			  
//	Federal Forms Functionality			  
			  
//all Federal Tax id Forms Checkout Service Implementation 
  @Override
  @Transactional
  public ModelAndView compFederalTaxIdFormsCheckouDataDisplay(HttpServletRequest req, HttpSession sn) {
      LOGGER.debug("compFederalTaxIdFormsCheckouDataDisplay...Service");
      ModelAndView mav = new ModelAndView();

//				Required values take from DB		
      if (sn.getAttribute(userChoiceInSn) != null) {
          List<FederalTaxIdFormsAttributesAndValuesSaving> busFedFormAttrAndValModel = federalFormsAttrAndValuesDAOImpl.findByallFederalFormsCapturedInfoFromDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(userChoiceInSn));
          String capturedInfoInDB = null;
          for (FederalTaxIdFormsAttributesAndValuesSaving federalFormAttrAndValModel : busFedFormAttrAndValModel) {
              capturedInfoInDB = federalFormAttrAndValModel.getCapturedInformation();
          }
//        Formation From String to JSON
          JSONObject capInfoJsonObject = convertFromStringToJSONFormat(capturedInfoInDB);
        
//	Federal Tax Ids take from DB 			
          List attrNamesList = new ArrayList();
          List attrFieldIdsList = new ArrayList();
          
          List<Object> formFieldsAndValuesIds = (List<Object>) federalTaxAttrInfoDAOImpl.pendingFederalTaxCheckoutAttrAndValuesList();

          Iterator formFieldsAndValuesIdsIterator = formFieldsAndValuesIds.iterator();
          while (formFieldsAndValuesIdsIterator.hasNext()) {
              Object[] formFieldsAndValuesIdsObj = (Object[]) formFieldsAndValuesIdsIterator.next();

              String attrFieldId = String.valueOf(formFieldsAndValuesIdsObj[0]);
              String attrNames = String.valueOf(formFieldsAndValuesIdsObj[1]);
              
              attrFieldIdsList.add(attrFieldId);
              attrNamesList.add(attrNames + " ");
              
          }

          String attrFieldIDListInString = attrFieldIdsList.toString();
          attrFieldIDListInString = attrFieldIDListInString.replace("[", "");
          attrFieldIDListInString = attrFieldIDListInString.replace("]", "");
          
          String attrNamesListInString = attrNamesList.toString();
          attrNamesListInString = attrNamesListInString.replace("[", "");
          attrNamesListInString = attrNamesListInString.replace("]", "");
          
          String[] attrFieldIDInString = attrFieldIDListInString.split(", ");
          String[] attrNamesInString = attrNamesListInString.split(" , ");
          
          List attrFFNamesList = new ArrayList();            
          List attrFFValuesList = new ArrayList();
          

          for (int i = 0; i < attrFieldIDInString.length; i++) {
              String attrFieldId = attrFieldIDInString[i];
              String attrNames = attrNamesInString[i];
              
              String jSonObjVal = (String) capInfoJsonObject.get(attrFieldId);
              if (!("").equals(jSonObjVal)) {
                  String attrValue = jSonObjVal;
                  for (int k = 0; k < jSonObjVal.length() + 9; k++) {
                      String yesVal = yes + k;
                      String noVal = no + k;
                      if (yesVal.equals(jSonObjVal)) {
                          attrValue = jSonObjVal.replaceAll(yesVal, yes);
                      } else if (noVal.equals(jSonObjVal)) {
                          attrValue = jSonObjVal.replaceAll(noVal, no);
                      }
                  }
                  attrFFNamesList.add(attrNames);                    
                  attrFFValuesList.add(attrValue);                    
              }
          }
          mav = new ModelAndView("completedFederalTaxFormsCheckoutDisplay");
          sn.setAttribute("Fed_Attr_Names_CheckOutList", attrFFNamesList);            
          sn.setAttribute("Fed_Attr_Values_CheckOutList", attrFFValuesList);            
      }
      mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
      return mav;
  }
  
//all S Corporation Forms Checkout Service Implementation 
  @Override
  @Transactional
  public ModelAndView compSCorporationFormsCheckouDataDisplay(HttpServletRequest req, HttpSession sn) {
      LOGGER.debug("sCorporationFormsCheckouDataDisplay...Service");
      ModelAndView mav = new ModelAndView();
			//Required values take from DB		
      if (sn.getAttribute(userChoiceInSn) != null) {
          List<ScorporationFormsAttributesAndValuesSaving> busSCorpFormAttrAndValModel = scorpoFormsAttrAndValuesDAOImpl.findByallSCorpFormsCapturedInfoFromDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(userChoiceInSn));
          String capturedInfoInDB = null;
          for (ScorporationFormsAttributesAndValuesSaving sCorpFormAttrAndValModel : busSCorpFormAttrAndValModel) {
              capturedInfoInDB = sCorpFormAttrAndValModel.getCapturedInformation();
          }
//        Formation From String to JSON
          JSONObject capInfoJsonObject = convertFromStringToJSONFormat(capturedInfoInDB);
          
//				S Corporation Ids take from DB 			
          List attrNamesList = new ArrayList();
          List attrFieldIdsList = new ArrayList();
          
          List<Object> formFieldsAndValuesIds = (List<Object>) sCorpAttrInfoDAOImpl.pendingSCorpCheckoutAttrAndValuesList();

          Iterator formFieldsAndValuesIdsIterator = formFieldsAndValuesIds.iterator();
          while (formFieldsAndValuesIdsIterator.hasNext()) {
              Object[] formFieldsAndValuesIdsObj = (Object[]) formFieldsAndValuesIdsIterator.next();

              String attrFieldId = String.valueOf(formFieldsAndValuesIdsObj[0]);
              String attrNames = String.valueOf(formFieldsAndValuesIdsObj[1]);
              
              attrFieldIdsList.add(attrFieldId);
              attrNamesList.add(attrNames + " ");               
          }

          String attrFieldIDListInString = attrFieldIdsList.toString();
          attrFieldIDListInString = attrFieldIDListInString.replace("[", "");
          attrFieldIDListInString = attrFieldIDListInString.replace("]", "");
          
          String attrNamesListInString = attrNamesList.toString();
          attrNamesListInString = attrNamesListInString.replace("[", "");
          attrNamesListInString = attrNamesListInString.replace("]", "");
          
          String[] attrFieldIDInString = attrFieldIDListInString.split(", ");
          String[] attrNamesInString = attrNamesListInString.split(" , ");
          
          List attrFFNamesList = new ArrayList();            
          List attrFFValuesList = new ArrayList();
          
          for (int i = 0; i < attrFieldIDInString.length; i++) {
              String attrFieldId = attrFieldIDInString[i];
              String attrNames = attrNamesInString[i];
              
              String jSonObjVal = (String) capInfoJsonObject.get(attrFieldId);
              if (!("").equals(jSonObjVal)) {

                  String attrValue = jSonObjVal;

                  for (int k = 0; k < jSonObjVal.length() + 30; k++) {
                      String yesVal = yes + k;
                      String noVal = no + k;
                      if (yesVal.equals(jSonObjVal)) {
                          attrValue = jSonObjVal.replaceAll(yesVal, yes);
                      } else if (noVal.equals(jSonObjVal)) {
                          attrValue = jSonObjVal.replaceAll(noVal, no);
                      }
                  }
                  attrFFNamesList.add(attrNames);                    
                  attrFFValuesList.add(attrValue);                    
              }
          }
          mav = new ModelAndView("completedSCorpFormsCheckoutDisplay");
          sn.setAttribute("SCorp_Attr_Names_CheckOutList", attrFFNamesList);            
          sn.setAttribute("SCorp_Attr_Values_CheckOutList", attrFFValuesList);            
      }
      mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
      return mav;
  }
  
//all 501 Application Forms Checkout Service Implementation 
  @Override
  @Transactional
  public ModelAndView compFiveZeroOneFormsCheckouDataDisplay(HttpServletRequest req, HttpSession sn) {
      LOGGER.debug("fiveZeroOneFormsCheckouDataDisplay...Service");
      ModelAndView mav = new ModelAndView();

			//Required values take from DB		
      if (sn.getAttribute(userChoiceInSn) != null) {
          List<FivezerooneAppFormsAttributesAndValuesSaving> busFZOFormAttrAndValModel = fiveZOFormsAttrAndValuesDAOImpl.findByallFZOFormsCapturedInfoFromDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(userChoiceInSn));
          String capturedInfoInDB = null;
          for (FivezerooneAppFormsAttributesAndValuesSaving fZOFormAttrAndValModel : busFZOFormAttrAndValModel) {
              capturedInfoInDB = fZOFormAttrAndValModel.getCapturedInformation();
          }
//        Formation From String to JSON
          JSONObject capInfoJsonObject = convertFromStringToJSONFormat(capturedInfoInDB);
          
//				501 Application Ids take from DB 			
          List attrNamesList = new ArrayList();
          List attrFieldIdsList = new ArrayList();
          
          List<Object> formFieldsAndValuesIds = (List<Object>) fiveZeroOneAttrInfoDAOImpl.pendingFiveZeroOneCheckoutAttrAndValuesList();

          Iterator formFieldsAndValuesIdsIterator = formFieldsAndValuesIds.iterator();
          while (formFieldsAndValuesIdsIterator.hasNext()) {
              Object[] formFieldsAndValuesIdsObj = (Object[]) formFieldsAndValuesIdsIterator.next();

              String attrFieldId = String.valueOf(formFieldsAndValuesIdsObj[0]);
              String attrNames = String.valueOf(formFieldsAndValuesIdsObj[1]);
              
              attrFieldIdsList.add(attrFieldId);
              attrNamesList.add(attrNames + " ");                
          }

          String attrFieldIDListInString = attrFieldIdsList.toString();
          attrFieldIDListInString = attrFieldIDListInString.replace("[", "");
          attrFieldIDListInString = attrFieldIDListInString.replace("]", "");

          String attrNamesListInString = attrNamesList.toString();
          attrNamesListInString = attrNamesListInString.replace("[", "");
          attrNamesListInString = attrNamesListInString.replace("]", "");

          String[] attrFieldIDInString = attrFieldIDListInString.split(", ");
          String[] attrNamesInString = attrNamesListInString.split(" , ");
          
          List attrFFNamesList = new ArrayList();            
          List attrFFValuesList = new ArrayList();
          
          for (int i = 0; i < attrFieldIDInString.length; i++) {
              String attrFieldId = attrFieldIDInString[i];
              String attrNames = attrNamesInString[i];
              
              String jSonObjVal = (String) capInfoJsonObject.get(attrFieldId);
              if (!("").equals(jSonObjVal)) {
                  String attrValue = jSonObjVal;

                  for (int k = 0; k < jSonObjVal.length() + 500; k++) {
                      String yesVal = yes + k;
                      String noVal = no + k;
                      if (yesVal.equals(jSonObjVal)) {
                          attrValue = jSonObjVal.replaceAll(yesVal, yes);
                      } else if (noVal.equals(jSonObjVal)) {
                          attrValue = jSonObjVal.replaceAll(noVal, no);
                      }
                  }

                  attrFFNamesList.add(attrNames);                    
                  attrFFValuesList.add(attrValue);                    
              }
          }
          mav = new ModelAndView("completed501FormsCheckoutDisplay");
          sn.setAttribute("FZO_Attr_Names_CheckOutList", attrFFNamesList);
          sn.setAttribute("FZO_Attr_Values_CheckOutList", attrFFValuesList);            
      }
      mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
      return mav;
  }
  
//	Completed Federal forms checkout Order Display Redirection
	
	@Override
	@Transactional
	public ModelAndView completedCheckoutFederalTaxIdFormsOrderDisplay(HttpServletRequest req, HttpSession sn) {
	    LOGGER.debug("completedCheckoutFederalTaxIdFormsOrderDisplay...Service");
	    String compCheOrderDisplayRefType = req.getParameter("compCheOrderDisplayRefType");	    
  	ModelAndView mav = new ModelAndView();
	    if((BackMove).equals(compCheOrderDisplayRefType)) {
	    	mav = completedFederalFormsRedirection(req, sn);
	    } else if((FormModify).equals(compCheOrderDisplayRefType)) {	    	
	    	mav = compFederalTaxIDFormUpdation(req, sn);
	    }	    
	    return mav;
	}
	
//	Completed S Corporation forms checkout Order Display Redirection
	
	@Override
	@Transactional
	public ModelAndView completedCheckoutSCorpFormsOrderDisplay(HttpServletRequest req, HttpSession sn) {
	    LOGGER.debug("completedCheckoutSCorpFormsOrderDisplay...Service");
	    String compCheOrderDisplayRefType = req.getParameter("compCheOrderDisplayRefType");	    
  	ModelAndView mav = new ModelAndView();
	    if((BackMove).equals(compCheOrderDisplayRefType)) {
	    	mav = completedFederalFormsRedirection(req, sn);
	    } else if((FormModify).equals(compCheOrderDisplayRefType)) {	    	
	    	mav = sCorporationFormUpdation(req, sn);
	    }
	    return mav;
	}
	
//	Completed 501 forms checkout Order Display Redirection
	
	@Override
	@Transactional
	public ModelAndView completedCheckout501FormsOrderDisplay(HttpServletRequest req, HttpSession sn) {
	    LOGGER.debug("completedCheckout501FormsOrderDisplay...Service");
	    String compCheOrderDisplayRefType = req.getParameter("compCheOrderDisplayRefType");	    
  	ModelAndView mav = new ModelAndView();
	    if((BackMove).equals(compCheOrderDisplayRefType)) {
	    	mav = completedFederalFormsRedirection(req, sn);
	    } else if((FormModify).equals(compCheOrderDisplayRefType)) {	    	
	    	mav = fiveZeroOneFormUpdation(req, sn);
	    }
	    return mav;
	}
	
	
//	Federal Forms Modification
//	Single federal Starting point
//	Single Federal Form Updating Service
    @Override
    @Transactional
    public ModelAndView compFederalTaxIDFormUpdation(HttpServletRequest req, HttpSession sn) {
        LOGGER.debug("compFederalTaxIDFormUpdation...Service");        
        ModelAndView mav;        
//    		Federal form: Names, Values and Required values take from DB
            List<FederalTaxIdFormsAttributesInfo> federalTaxAttrList = federalTaxAttrInfoDAOImpl.federalFormDynamicFormShowHideData();

            List attrNamesList = new ArrayList();
            List attrValueList = new ArrayList();
            List attrReqTypeList = new ArrayList();
            List attrStatusList = new ArrayList();
            for (FederalTaxIdFormsAttributesInfo federalTaxAttrInfo : federalTaxAttrList) {
                String attrbName = String.valueOf(federalTaxAttrInfo.getAttributeFieldName());
                String attrbValue = String.valueOf(federalTaxAttrInfo.getAttributeValue());

                attrNamesList.add(attrbName.replace("null", ""));
                attrValueList.add(attrbValue.replace("null", ""));
                attrReqTypeList.add(federalTaxAttrInfo.getRequiredType());
                attrStatusList.add(federalTaxAttrInfo.getStatus());
            }
            sn.setAttribute("SFed_Attr_Names", attrNamesList);
            sn.setAttribute("SFed_Attr_Values", attrValueList);
            sn.setAttribute("SFed_Attr_Req_List", attrReqTypeList);
            sn.setAttribute("SFed_Attr_Status_List", attrStatusList);

//    		Federal forms Attribute Required type Ids take from DB 
            List<Object> attrReqTypeIdsList = (List<Object>) federalTaxAttrInfoDAOImpl.attributeReqTypeIDsList();
            int totalReqAttrCount = attrReqTypeIdsList.size();
            String attrReqTypeIds = attrReqTypeIdsList.toString();
            attrReqTypeIds = attrReqTypeIds.replace("[", "");
            attrReqTypeIds = attrReqTypeIds.replace("]", "");

//    			Radio Button Status take from DB 			
            List<Object> rbStatus = (List<Object>) federalTaxAttrInfoDAOImpl.federalFormRadioButtonStatus();

//    			Form Modification Code
                List<FederalTaxIdFormsAttributesAndValuesSaving> busFedFormAttrAndValModel = federalFormsAttrAndValuesDAOImpl.findByallFederalFormsCapturedInfoFromDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(userChoiceInSn));                
                String capturedInfoInDB = null;
                for (FederalTaxIdFormsAttributesAndValuesSaving federalFormAttrAndValModel : busFedFormAttrAndValModel) {
                    capturedInfoInDB = federalFormAttrAndValModel.getCapturedInformation();
                }                
//                Formation From String to JSON
                JSONObject capInfoJsonObject = convertFromStringToJSONFormat(capturedInfoInDB);                
                mav = federalTaxIDFormAttributesInfo(capInfoJsonObject, req);

                AllFederalFormsDataSaving federalFormsUpdateModel = allFederalFormsDataSavingDAOImpl.findByallFederalFormsDataFromDB((String) sn.getAttribute(userNameInSn), federalTaxId, (String) sn.getAttribute(userChoiceInSn));
                String federalFormPageValues = federalFormsUpdateModel.getPageVariableReference();
                mav = new ModelAndView(newFedFormsCreation);                
                mav.addObject(attribTextFieldList, req.getAttribute(attribTextFieldList));
                mav.addObject(attribTextFieldDateList, req.getAttribute(attribTextFieldDateList));
                mav.addObject(attribSelectBoxList, req.getAttribute(attribSelectBoxList));
                mav.addObject(radioButtList, req.getAttribute(radioButtList));
                mav.addObject(checkBoxesList, req.getAttribute(checkBoxesList));
                
                mav.addObject(radioButtStatus, rbStatus);
                sn.setAttribute(radioButtStatus, rbStatus);
                sn.setAttribute(attrbReqTypeIdsInSn, attrReqTypeIds);
                sn.setAttribute(totalReqAttrbCountInSn, totalReqAttrCount);                
                mav.addObject("federalFormPageValues", federalFormPageValues); 
                mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
        return mav;
    }
    
//	Single Federal Form Attributes Info
    @Override
    @Transactional
    public ModelAndView federalTaxIDFormAttributesInfo(JSONObject capInfoJsonObject, HttpServletRequest req) {
        LOGGER.debug("federalTaxIDFormAttributesInfo...Service");
        ModelAndView mav = new ModelAndView();
        String attributeTextFieldList = null;
        for (int i = 1; i <= 44; i++) {
            String keyVal = String.valueOf(i);
            String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
            attributeTextFieldList = attributeTextFieldList + jSonObjVal + ",$,";
        }
        attributeTextFieldList = attributeTextFieldList.replace("null", "");

        String attributeTextFieldDateList = null;
        for (int j = 45; j <= 46; j++) {
            String keyVal = String.valueOf(j);
            String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
            attributeTextFieldDateList = attributeTextFieldDateList + jSonObjVal + ",$,";
        }
        attributeTextFieldDateList = attributeTextFieldDateList.replace("null", "");

        String radioButtonList = null;
        for (int k = 47; k <= 54; k++) {
            String keyVal = String.valueOf(k);
            String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
            radioButtonList = radioButtonList + jSonObjVal + ",$,";
        }
        radioButtonList = radioButtonList.replace("null", "");

        String keyVal1 = String.valueOf(55);
        String jSonObjVal1 = (String) capInfoJsonObject.get(keyVal1);
        String checkBoxList = jSonObjVal1 + ",$,";
        checkBoxList = checkBoxList.replace("null", "");

        String attributeSelectBoxList = null;
        for (int k = 56; k <= 62; k++) {
            String keyVal = String.valueOf(k);
            String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
            attributeSelectBoxList = attributeSelectBoxList + jSonObjVal + ",$,";
        }
        attributeSelectBoxList = attributeSelectBoxList.replace("null", "");        
        
        req.setAttribute(attribTextFieldList, attributeTextFieldList);
        req.setAttribute(attribTextFieldDateList, attributeTextFieldDateList);
        req.setAttribute(attribSelectBoxList, attributeSelectBoxList);
        req.setAttribute(radioButtList, radioButtonList);
        req.setAttribute(checkBoxesList, checkBoxList);       
        return mav;
    }
    
//	Single Federal Form Modified data saving Service Implementation
    @Override
    @Transactional
    public ModelAndView compSingleFederalTaxModifiedDataSaving(HttpServletRequest req, HttpSession sn) {
        LOGGER.debug("compSingleFederalTaxModifiedDataSaving...Service");
        JSONObject stateFormInfoObj = singleFederalFormsAttributesInfoFromJSP(req);
        ModelAndView mav;        

//Already Exit User choice when ever directly clicking enter with out using mouse        
        List userAEChoiceList = allFederalFormsDataSavingDAOImpl.allFederalFormsAlreadyExittUserChoiceInDB((String) sn.getAttribute(userNameInSn), federalTaxId, req.getParameter(attributeTextField1));
        String alreadyExitChoice = null;        
        if (!userAEChoiceList.isEmpty()) {
            AllFederalFormsDataSaving federalFormsAEModel = allFederalFormsDataSavingDAOImpl.findByallFederalFormsDataFromDB((String) sn.getAttribute(userNameInSn), federalTaxId, req.getParameter(attributeTextField1));
            alreadyExitChoice = federalFormsAEModel.getDocumentName();            
        }        
        if (alreadyExitChoice != null  && !alreadyExitChoice.equals(sn.getAttribute(userChoiceInSn))) {
            mav = new ModelAndView(newFedFormsCreation);
            mav.addObject(alreadyHaveChoice, alreadyHaveUserChoice);
        } else {
        	String formStatus = federalTaxIDFormStatusInDB(req, sn);
            String jsonStringObj = stateFormInfoObj.toString();
            jsonStringObj = jsonStringObj.replace("null", "\"\"");

            Timestamp currentDate = currentDate();            
                FederalTaxIdFormsAttributesAndValuesSaving fedAttrAndValModel = federalFormsAttrAndValuesDAOImpl.findByFederalTaxFormsAttributesAndValuesFromDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(userChoiceInSn));
                fedAttrAndValModel.setLegalName(req.getParameter(attributeTextField1));
                fedAttrAndValModel.setCapturedInformation(jsonStringObj);
                fedAttrAndValModel.setModifiedDate(currentDate);
                federalFormsAttrAndValuesDAOImpl.merge(fedAttrAndValModel);

                AllFederalFormsDataSaving fedTaxFormsModel = allFederalFormsDataSavingDAOImpl.findByallFederalFormsDataFromDB((String) sn.getAttribute(userNameInSn), federalTaxId, (String) sn.getAttribute(userChoiceInSn));
                fedTaxFormsModel.setDocumentName(req.getParameter(attributeTextField1));
                fedTaxFormsModel.setStatus(formStatus);
                fedTaxFormsModel.setModifiedDate(currentDate);
                fedTaxFormsModel.setPageVariableReference(req.getParameter(fedFormPageValues));
                allFederalFormsDataSavingDAOImpl.merge(fedTaxFormsModel);                
                if(!(sn.getAttribute(userChoiceInSn)).equals(req.getParameter(attributeTextField1))) {
//                  All related tables updating
                    mav = singleFederalTaxFormsAllRelatedOtherTablesUpdate(req, sn);                    
                }
                
            sn.setAttribute(userChoiceInSn, req.getParameter(attributeTextField1));
            if (req.getParameter(fedFinishOrderRef) != null && (finishedStatus).equals(req.getParameter(fedFinishOrderRef))) {
                mav = completedFederalFormsRedirection(req, sn);
            } else {
                mav = new ModelAndView(newFedFormsCreation);
            }

            mav.addObject(fedFormPageValues, req.getParameter(fedFormPageValues));
            sn.setAttribute(sfAttrbNames, sn.getAttribute(sfAttrbNames));
            sn.setAttribute(sfAttrbValues, sn.getAttribute(sfAttrbValues));
            sn.setAttribute(sfAttrbReqList, sn.getAttribute(sfAttrbReqList));
            sn.setAttribute(sfAttrbStatusList, sn.getAttribute(sfAttrbStatusList));
            sn.setAttribute(radioButtStatus, sn.getAttribute(radioButtStatus));            
            sn.setAttribute(typeOfDocumentInSn, federalTaxId);
        }        
        mav.addObject(attribTextFieldList, req.getAttribute(attribTextFieldList));
        mav.addObject(attribTextFieldDateList, req.getAttribute(attribTextFieldDateList));
        mav.addObject(attribSelectBoxList, req.getAttribute(attribSelectBoxList));
        mav.addObject(radioButtList, req.getAttribute(radioButtList));
        mav.addObject(checkBoxesList, req.getAttribute(checkBoxesList));
        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
        return mav;
    }
	    
	 // Single Federal Tax ID Form Status
	    @Override
	    @Transactional
	    public String federalTaxIDFormStatusInDB(HttpServletRequest req, HttpSession sn) {
	    	LOGGER.debug("federalTaxIDFormStatusInDB...Service");
	        String formStatus = null;
	        JSONObject stateFormInfoObj = singleFederalFormsAttributesInfoFromJSP(req);
	        int reqAttrCount = 0;
            String[] attributeIdsString = ((String) sn.getAttribute(attrbReqTypeIdsInSn)).split(", ");
            int totalReqAttrCount = attributeIdsString.length;

            for (int i = 0; i < attributeIdsString.length; i++) {
                String attrIdInStr = attributeIdsString[i];
                int attrIdInt = Integer.parseInt(attrIdInStr);

                String jSonObjVal = (String) stateFormInfoObj.get(attrIdInt);
                if (jSonObjVal != null && jSonObjVal != "") {
                    reqAttrCount = reqAttrCount + 1;
                }
            }

            if (totalReqAttrCount == reqAttrCount) {
                formStatus = readyForCheckout;
            } else {
                formStatus = inProgress;
            }
	        return formStatus;
	    }
	    
//		Single Federal Tax ID Form Attributes info from JSP
	    @Override
	    @Transactional
	    public JSONObject singleFederalFormsAttributesInfoFromJSP(HttpServletRequest req) {
	        LOGGER.debug("singleFederalFormsAttributesInfoFromJSP...Service");
	        JSONObject stateFormInfoObj = new JSONObject();
	        String attributeTextFieldList = null;
	        String[] attributeTextField = new String[45];
	        for (int i = 1; i < attributeTextField.length; i++) {
	            attributeTextField[i] = req.getParameter("attributeTextField" + i);
	            attributeTextFieldList = attributeTextFieldList + attributeTextField[i] + ",$,";
	            stateFormInfoObj.put(i, attributeTextField[i]);            
	        }
	        attributeTextFieldList = attributeTextFieldList.replace("null", "");
	
	        String attributeTextFieldDateList = null;
	        String[] attributeTextFieldDate = new String[3];
	        for (int j = 1; j < attributeTextFieldDate.length; j++) {
	            attributeTextFieldDate[j] = req.getParameter("attributeTextFieldDate" + j);
	            attributeTextFieldDateList = attributeTextFieldDateList + attributeTextFieldDate[j] + ",$,";
	            stateFormInfoObj.put(j + 44, attributeTextFieldDate[j]);            
	        }
	        attributeTextFieldDateList = attributeTextFieldDateList.replace("null", "");
	
	        String radioButtonList = null;
	        String[] radioButton = new String[9];
	        for (int k = 1; k < radioButton.length; k++) {
	            radioButton[k] = req.getParameter("radioButton" + k);
	            radioButtonList = radioButtonList + radioButton[k] + ",$,";
	            stateFormInfoObj.put(k + 46, radioButton[k]);            
	        }
	        radioButtonList = radioButtonList.replace("null", "");
	
	        String checkBoxList = null;
	        String[] checkBox = new String[2];
	        for (int l = 1; l < checkBox.length; l++) {
	            checkBox[l] = req.getParameter("checkBox" + l);
	            checkBoxList = checkBoxList + checkBox[l] + ",$,";
	            stateFormInfoObj.put(l + 54, checkBox[l]);            
	        }
	        checkBoxList = checkBoxList.replace("null", "");
	
	        String attributeSelectBoxList = null;
	        String[] attributeSelectBox = new String[8];
	        for (int n = 1; n < attributeSelectBox.length; n++) {
	            attributeSelectBox[n] = req.getParameter("attributeSelectBox" + n);
	            attributeSelectBoxList = attributeSelectBoxList + attributeSelectBox[n] + ",$,";
	            stateFormInfoObj.put(n + 55, attributeSelectBox[n]);            
	        }
	        attributeSelectBoxList = attributeSelectBoxList.replace("null", "");
	        
	        req.setAttribute(attribTextFieldList, attributeTextFieldList);
	        req.setAttribute(attribTextFieldDateList, attributeTextFieldDateList);
	        req.setAttribute(attribSelectBoxList, attributeSelectBoxList);
	        req.setAttribute(radioButtList, radioButtonList);
	        req.setAttribute(checkBoxesList, checkBoxList);	        
	        return stateFormInfoObj;
	    }

//    Federal Forms All Related Other Tables Update		    
	  @Override
	  @Transactional
	  public ModelAndView singleFederalTaxFormsAllRelatedOtherTablesUpdate(HttpServletRequest req, HttpSession sn) {
		  LOGGER.debug("singleFederalTaxFormsAllRelatedOtherTablesUpdate...Service");
		  ModelAndView mav = new ModelAndView();
		  Timestamp currentDate = currentDate(); 
		  
//        All related tables updating
          List allFederaslFormsPaymentList = allFederalFormsPaymentInfoDAOImpl.allFederalFormsPaymentRowVerification((String) sn.getAttribute(userNameInSn), federalTaxId, (String) sn.getAttribute(userChoiceInSn));
          if (!allFederaslFormsPaymentList.isEmpty()) {
              AllFederalFormsPaymentInfoSaving allFedFormsPaymentInfoSaving = allFederalFormsPaymentInfoDAOImpl.allFederalFormsPaymentModificationVerification((String) sn.getAttribute(userNameInSn), federalTaxId, (String) sn.getAttribute(userChoiceInSn));
              allFedFormsPaymentInfoSaving.setLegalName(req.getParameter(attributeTextField1));
              allFedFormsPaymentInfoSaving.setUpdatedDate(currentDate);
              allFederalFormsPaymentInfoDAOImpl.merge(allFedFormsPaymentInfoSaving);
                          
              AllFederalFormsCheckoutPaymentAndUserContactSaving allFedCheckoutPaymentAndUserContact = allFedCheckoutPaymentAndContactDAOImpl.allFederalFormsCheckoutPaymentDataDeleteFromCart((String) sn.getAttribute(userNameInSn), federalTaxId, (String) sn.getAttribute(userChoiceInSn));
              allFedCheckoutPaymentAndUserContact.setLegalName(req.getParameter(attributeTextField1));
              allFedCheckoutPaymentAndUserContact.setModifiedDate(currentDate);
              allFedCheckoutPaymentAndContactDAOImpl.merge(allFedCheckoutPaymentAndUserContact);
          }              	       
	  return mav;
	  }

//	End point
    
//	S Corporation Starting point
//	S Corporation Form Updating Service
    @Override
    @Transactional
    public ModelAndView sCorporationFormUpdation(HttpServletRequest req, HttpSession sn) {
        LOGGER.debug("sCorporationFormUpdation...Service");
        ModelAndView mav;        
//    		S Corporation form: Names, Values and Required values take from DB
            List<ScorporationFormsAttributesInfo> sCorpAttrList = sCorpAttrInfoDAOImpl.sCorporationFormDynamicFormShowHideData();

            List attrNamesList = new ArrayList();
            List attrValueList = new ArrayList();
            List attrReqTypeList = new ArrayList();
            List attrStatusList = new ArrayList();

            for (ScorporationFormsAttributesInfo sCorpAttrInfo : sCorpAttrList) {
                String attrbName = String.valueOf(sCorpAttrInfo.getAttributeFieldName());
                String attrbValue = String.valueOf(sCorpAttrInfo.getAttributeValue());

                attrNamesList.add(attrbName.replace("null", ""));
                attrValueList.add(attrbValue.replace("null", ""));
                attrReqTypeList.add(sCorpAttrInfo.getRequiredType());
                attrStatusList.add(sCorpAttrInfo.getStatus());
            }

            sn.setAttribute("SCorp_Attr_Names", attrNamesList);
            sn.setAttribute("SCorp_Attr_Values", attrValueList);
            sn.setAttribute("SCorp_Attr_Req_List", attrReqTypeList);
            sn.setAttribute("SCorp_Attr_Status_List", attrStatusList);

//    			Federal forms Attribute Required type Ids take from DB 
            List<Object> attrReqTypeIdsList = (List<Object>) sCorpAttrInfoDAOImpl.attributeReqTypeIDsList();
            int totalReqAttrCount = attrReqTypeIdsList.size();
            String attrReqTypeIds = attrReqTypeIdsList.toString();
            attrReqTypeIds = attrReqTypeIds.replace("[", "");
            attrReqTypeIds = attrReqTypeIds.replace("]", "");

//    			Radio Button Status take from DB 			
            List<Object> rbStatus = (List<Object>) sCorpAttrInfoDAOImpl.sCorporationFormRadioButtonStatus();

//    			Form Modification Code                
            	List<ScorporationFormsAttributesAndValuesSaving> busSCorpFormAttrAndValModel = scorpoFormsAttrAndValuesDAOImpl.findByallSCorpFormsCapturedInfoFromDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(userChoiceInSn));
                String capturedInfoInDB = null;
                for (ScorporationFormsAttributesAndValuesSaving sCorpFormAttrAndValModel : busSCorpFormAttrAndValModel) {
                    capturedInfoInDB = sCorpFormAttrAndValModel.getCapturedInformation();
                }
//              Formation From String to JSON
                JSONObject capInfoJsonObject = convertFromStringToJSONFormat(capturedInfoInDB);
                
                mav = sCorporationFormAttributesInfo(capInfoJsonObject, req);                

                AllFederalFormsDataSaving federalFormsUpdateModel = allFederalFormsDataSavingDAOImpl.findByallFederalFormsDataFromDB((String) sn.getAttribute(userNameInSn), sCorporation, (String) sn.getAttribute(userChoiceInSn));
                String sCorpFormPageValues = federalFormsUpdateModel.getPageVariableReference();

                mav = new ModelAndView(newSCorpFormsCreation);
                mav.addObject(attribTextFieldList, req.getAttribute(attribTextFieldList));
                mav.addObject(attribTextAreaList, req.getAttribute(attribTextAreaList));
                mav.addObject(attribTextFieldDateList, req.getAttribute(attribTextFieldDateList));
                mav.addObject(attribSelectBoxList, req.getAttribute(attribSelectBoxList));
                mav.addObject(radioButtList, req.getAttribute(radioButtList));
                mav.addObject(checkBoxesList, req.getAttribute(checkBoxesList));                
                mav.addObject("sCorpFormPageValues", sCorpFormPageValues);
            
            mav.addObject(radioButtStatus, rbStatus);
            sn.setAttribute(radioButtStatus, rbStatus);
            sn.setAttribute(attrbReqTypeIdsInSn, attrReqTypeIds);
            sn.setAttribute(totalReqAttrbCountInSn, totalReqAttrCount); 
            mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
        return mav;
    }
    
//	S Corporation Form Attributes Info
	    @Override
	    @Transactional
	    public ModelAndView sCorporationFormAttributesInfo(JSONObject capInfoJsonObject, HttpServletRequest req) {
	        LOGGER.debug("sCorporationFormAttributesInfo...Service");
	        ModelAndView mav = new ModelAndView();
	        String attributeTextFieldList = null;
            for (int i = 1; i <= 66; i++) {
                String keyVal = String.valueOf(i);
                String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
                attributeTextFieldList = attributeTextFieldList + jSonObjVal + ",$,";
            }
            attributeTextFieldList = attributeTextFieldList.replace("null", "");

            String attributeTextFieldDateList = null;
            for (int j = 67; j <= 73; j++) {
                String keyVal = String.valueOf(j);
                String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
                attributeTextFieldDateList = attributeTextFieldDateList + jSonObjVal + ",$,";
            }
            attributeTextFieldDateList = attributeTextFieldDateList.replace("null", "");

            String attributeTextAreaList = null;
            for (int k = 74; k <= 75; k++) {
                String keyVal = String.valueOf(k);
                String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
                jSonObjVal = jSonObjVal.replaceAll("[\n\r]", " ");
                attributeTextAreaList = attributeTextAreaList + jSonObjVal + ",$,";
            }
            attributeTextAreaList = attributeTextAreaList.replace("null", "");

            String attributeSelectBoxList = null;
            for (int n = 76; n <= 98; n++) {
                String keyVal = String.valueOf(n);
                String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
                attributeSelectBoxList = attributeSelectBoxList + jSonObjVal + ",$,";
            }
            attributeSelectBoxList = attributeSelectBoxList.replace("null", "");

            String radioButtonList = null;
            for (int k = 99; k <= 113; k++) {
                String keyVal = String.valueOf(k);
                String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
                radioButtonList = radioButtonList + jSonObjVal + ",$,";
            }
            radioButtonList = radioButtonList.replace("null", "");

            String checkBoxList = null;
            for (int p = 114; p <= 119; p++) {
                String keyVal = String.valueOf(p);
                String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
                checkBoxList = checkBoxList + jSonObjVal + ",$,";
            }
            checkBoxList = checkBoxList.replace("null", "");
            
	        req.setAttribute(attribTextFieldList, attributeTextFieldList);
	        req.setAttribute(attribTextAreaList, attributeTextAreaList);
	        req.setAttribute(attribTextFieldDateList, attributeTextFieldDateList);
	        req.setAttribute(attribSelectBoxList, attributeSelectBoxList);
	        req.setAttribute(radioButtList, radioButtonList);
	        req.setAttribute(checkBoxesList, checkBoxList);
	        return mav;
	    }
	    
//		S Corporation Form Modified data saving Service Implementation
	    @Override
	    @Transactional
	    public ModelAndView compSCorporationTaxModifiedDataSaving(HttpServletRequest req, HttpSession sn) {
	        LOGGER.debug("compSCorporationTaxModifiedDataSaving...Service");
	        JSONObject stateFormInfoObj = sCorporationFormsAttributesInfoFromJSP(req);
	        ModelAndView mav;        

	//Already Exit User choice when ever directly clicking enter with out using mouse        
	        List userAEChoiceList = allFederalFormsDataSavingDAOImpl.allFederalFormsAlreadyExittUserChoiceInDB((String) sn.getAttribute(userNameInSn), sCorporation, req.getParameter(attributeTextField1));
	        String alreadyExitChoice = null;        
	        if (!userAEChoiceList.isEmpty()) {
	            AllFederalFormsDataSaving federalFormsAEModel = allFederalFormsDataSavingDAOImpl.findByallFederalFormsDataFromDB((String) sn.getAttribute(userNameInSn), sCorporation, req.getParameter(attributeTextField1));
	            alreadyExitChoice = federalFormsAEModel.getDocumentName();            
	        }        
	        if (alreadyExitChoice != null  && !alreadyExitChoice.equals(sn.getAttribute(userChoiceInSn))) {
	            mav = new ModelAndView(newSCorpFormsCreation);
	            mav.addObject(alreadyHaveChoice, alreadyHaveUserChoice);
	        } else {
	        	String formStatus = sCorporationFormStatusInDB(req, sn);
	            String jsonStringObj = stateFormInfoObj.toString();
	            jsonStringObj = jsonStringObj.replace("null", "\"\"");

	            Timestamp currentDate = currentDate();	            
	                ScorporationFormsAttributesAndValuesSaving sCorpAttrAndValModel = scorpoFormsAttrAndValuesDAOImpl.findBySCorpFormsAttributesAndValuesFromDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(userChoiceInSn));
	                sCorpAttrAndValModel.setCorpName(req.getParameter(attributeTextField1));
	                sCorpAttrAndValModel.setCapturedInformation(jsonStringObj);
	                sCorpAttrAndValModel.setModifiedDate(currentDate);
	                scorpoFormsAttrAndValuesDAOImpl.merge(sCorpAttrAndValModel);

	                AllFederalFormsDataSaving fedTaxFormsModel = allFederalFormsDataSavingDAOImpl.findByallFederalFormsDataFromDB((String) sn.getAttribute(userNameInSn), sCorporation, (String) sn.getAttribute(userChoiceInSn));
	                fedTaxFormsModel.setDocumentName(req.getParameter(attributeTextField1));
	                fedTaxFormsModel.setStatus(formStatus);
	                fedTaxFormsModel.setModifiedDate(currentDate);
	                fedTaxFormsModel.setPageVariableReference(req.getParameter(sCorpFormPageVal));
	                allFederalFormsDataSavingDAOImpl.merge(fedTaxFormsModel);
	                
	                if(!(sn.getAttribute(userChoiceInSn)).equals(req.getParameter(attributeTextField1))) {            		
//	                  All related tables updating
	                    mav = sCorpFormsAllRelatedOtherTablesUpdate(req, sn);
	                }
	               
	            sn.setAttribute(userChoiceInSn, req.getParameter(attributeTextField1));
	            if (req.getParameter(scFinishOrderRef) != null && (finishedStatus).equals(req.getParameter(scFinishOrderRef))) {
	                mav = completedFederalFormsRedirection(req, sn);
	            } else {
	                mav = new ModelAndView(newSCorpFormsCreation);
	            }
	            mav.addObject(sCorpFormPageVal, req.getParameter(sCorpFormPageVal));
	            sn.setAttribute(scAttrbNames, sn.getAttribute(scAttrbNames));
	            sn.setAttribute(scAttrbValues, sn.getAttribute(scAttrbValues));
	            sn.setAttribute(scAttrbReqList, sn.getAttribute(scAttrbReqList));
	            sn.setAttribute(scAttrbStatusList, sn.getAttribute(scAttrbStatusList));
	            sn.setAttribute(radioButtStatus, sn.getAttribute(radioButtStatus));            
	            sn.setAttribute(typeOfDocumentInSn, sCorporation);
	        }        
	        mav.addObject(attribTextFieldList, req.getAttribute(attribTextFieldList));
	        mav.addObject(attribTextFieldDateList, req.getAttribute(attribTextFieldDateList));
	        mav.addObject(attribTextAreaList, req.getAttribute(attribTextAreaList));
	        mav.addObject(attribSelectBoxList, req.getAttribute(attribSelectBoxList));        
	        mav.addObject(radioButtList, req.getAttribute(radioButtList));
	        mav.addObject(checkBoxesList, req.getAttribute(checkBoxesList));
	        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
	        return mav;
	    }
	        
	 // S Corporation Form Status
	    @Override
	    @Transactional
	    public String sCorporationFormStatusInDB(HttpServletRequest req, HttpSession sn) {
	    	LOGGER.debug("sCorporationFormStatusInDB...Service");
	        String formStatus = null;
	        JSONObject stateFormInfoObj = sCorporationFormsAttributesInfoFromJSP(req);
	        int reqAttrCount = 0;
	        String[] attributeIdsString = ((String) sn.getAttribute(attrbReqTypeIdsInSn)).split(", ");
	        int totalReqAttrCount = attributeIdsString.length;

	        for (int i = 0; i < attributeIdsString.length; i++) {
	            String attrIdInStr = attributeIdsString[i];
	            int attrIdInt = Integer.parseInt(attrIdInStr);

	            String jSonObjVal = (String) stateFormInfoObj.get(attrIdInt);
	            if (jSonObjVal != null && jSonObjVal != "") {
	                reqAttrCount = reqAttrCount + 1;
	            }
	        }
	        if (totalReqAttrCount == reqAttrCount) {
	            formStatus = readyForCheckout;
	        } else {
	            formStatus = inProgress;
	        }

	        return formStatus;
	    }

//		S Corporation Form Attributes info from JSP
	    @Override
	    @Transactional
	    public JSONObject sCorporationFormsAttributesInfoFromJSP(HttpServletRequest req) {
	        LOGGER.debug("sCorporationFormsAttributesInfoFromJSP...Service");
	        JSONObject stateFormInfoObj = new JSONObject();
	        String attributeTextFieldList = null;
	        String[] attributeTextField = new String[67];
	        for (int i = 1; i < attributeTextField.length; i++) {
	            attributeTextField[i] = req.getParameter("attributeTextField" + i);
	            attributeTextFieldList = attributeTextFieldList + attributeTextField[i] + ",$,";
	            stateFormInfoObj.put(i, attributeTextField[i]);            
	        }
	        attributeTextFieldList = attributeTextFieldList.replace("null", "");

	        String attributeTextFieldDateList = null;
	        String[] attributeTextFieldDate = new String[8];
	        for (int j = 1; j < attributeTextFieldDate.length; j++) {
	            attributeTextFieldDate[j] = req.getParameter("attributeTextFieldDate" + j);
	            attributeTextFieldDateList = attributeTextFieldDateList + attributeTextFieldDate[j] + ",$,";
	            stateFormInfoObj.put(j + 66, attributeTextFieldDate[j]);            
	        }
	        attributeTextFieldDateList = attributeTextFieldDateList.replace("null", "");

	        String attributeTextAreaList = null;
	        String[] attributeTextArea = new String[3];
	        for (int k = 1; k < attributeTextArea.length; k++) {
	            attributeTextArea[k] = req.getParameter("attributeTextArea" + k);
	            attributeTextAreaList = attributeTextAreaList + attributeTextArea[k] + ",$,";
	            stateFormInfoObj.put(k + 73, attributeTextArea[k]);            
	        }
	        attributeTextAreaList = attributeTextAreaList.replace("null", "");
	        attributeTextAreaList = attributeTextAreaList.replaceAll("[\n\r]", " ");

	        String attributeSelectBoxList = null;
	        String[] attributeSelectBox = new String[24];
	        for (int n = 1; n < attributeSelectBox.length; n++) {
	            attributeSelectBox[n] = req.getParameter("attributeSelectBox" + n);
	            attributeSelectBoxList = attributeSelectBoxList + attributeSelectBox[n] + ",$,";
	            stateFormInfoObj.put(n + 75, attributeSelectBox[n]);            
	        }
	        attributeSelectBoxList = attributeSelectBoxList.replace("null", "");

	        String radioButtonList = null;
	        String[] radioButton = new String[16];
	        for (int m = 1; m < radioButton.length; m++) {
	            radioButton[m] = req.getParameter("radioButton" + m);
	            radioButtonList = radioButtonList + radioButton[m] + ",$,";
	            stateFormInfoObj.put(m + 98, radioButton[m]);            
	        }
	        radioButtonList = radioButtonList.replace("null", "");

	        String checkBoxList = null;
	        String[] checkBox = new String[7];
	        for (int p = 1; p < checkBox.length; p++) {
	            String checkSingele = req.getParameter("checkBox" + p);
	            if (checkSingele != null) {
	                String[] checkBoxVal = req.getParameterValues("checkBox" + p);
	                String ddynamicCheckValue = "";
	                for (int q = 0; q < checkBoxVal.length; q++) {
	                    ddynamicCheckValue = ddynamicCheckValue + ", " + checkBoxVal[q];
	                }
	                ddynamicCheckValue = ddynamicCheckValue.replaceFirst(", ", "");
	                checkBoxList = checkBoxList + ddynamicCheckValue + ",$,";
	                stateFormInfoObj.put(p + 113, ddynamicCheckValue);
	            } else {
	                checkBoxList = checkBoxList + checkSingele + ",$,";
	                stateFormInfoObj.put(p + 113, checkSingele);                
	            }
	        }
	        checkBoxList = checkBoxList.replace("null", "");

	        req.setAttribute(attribTextFieldList, attributeTextFieldList);
	        req.setAttribute(attribTextFieldDateList, attributeTextFieldDateList);
	        req.setAttribute(attribTextAreaList, attributeTextAreaList);
	        req.setAttribute(attribSelectBoxList, attributeSelectBoxList);
	        req.setAttribute(radioButtList, radioButtonList);
	        req.setAttribute(checkBoxesList, checkBoxList);        
	        return stateFormInfoObj;
	    }
	    
//	    Federal Forms All Related Other Tables Update		    
		  @Override
		  @Transactional
		  public ModelAndView sCorpFormsAllRelatedOtherTablesUpdate(HttpServletRequest req, HttpSession sn) {
			  LOGGER.debug("singleFederalTaxFormsAllRelatedOtherTablesUpdate...Service");
			  ModelAndView mav = new ModelAndView();
			  Timestamp currentDate = currentDate(); 
			  
//	        All related tables updating
			  List allFederaslFormsPaymentList = allFederalFormsPaymentInfoDAOImpl.allFederalFormsPaymentRowVerification((String) sn.getAttribute(userNameInSn), sCorporation, (String) sn.getAttribute(userChoiceInSn));
              if (!allFederaslFormsPaymentList.isEmpty()) {
                  AllFederalFormsPaymentInfoSaving allFedFormsPaymentInfoSaving = allFederalFormsPaymentInfoDAOImpl.allFederalFormsPaymentModificationVerification((String) sn.getAttribute(userNameInSn), sCorporation, (String) sn.getAttribute(userChoiceInSn));
                  allFedFormsPaymentInfoSaving.setLegalName(req.getParameter(attributeTextField1));
                  allFedFormsPaymentInfoSaving.setUpdatedDate(currentDate);
                  allFederalFormsPaymentInfoDAOImpl.merge(allFedFormsPaymentInfoSaving);
              
                  AllFederalFormsCheckoutPaymentAndUserContactSaving allFedCheckoutPaymentAndUserContact = allFedCheckoutPaymentAndContactDAOImpl.allFederalFormsCheckoutPaymentDataDeleteFromCart((String) sn.getAttribute(userNameInSn), sCorporation, (String) sn.getAttribute(userChoiceInSn));
                  allFedCheckoutPaymentAndUserContact.setLegalName(req.getParameter(attributeTextField1));
                  allFedCheckoutPaymentAndUserContact.setModifiedDate(currentDate);
                  allFedCheckoutPaymentAndContactDAOImpl.merge(allFedCheckoutPaymentAndUserContact);
              }             	       
		  return mav;
		  }
//	End point
    
//	501 Starting point
//	501 Form Updating Service
	    @Override
	    @Transactional
	    public ModelAndView fiveZeroOneFormUpdation(HttpServletRequest req, HttpSession sn) {
	        LOGGER.debug("sCorporationFormUpdation...Service");        
	        ModelAndView mav;	        
//	    		S Corporation form: Names, Values and Required values take from DB
	            List<FivezerooneAppFormsAttributesInfo> fZOAttrList = fiveZeroOneAttrInfoDAOImpl.fiveZeroOneFormDynamicFormShowHideData();

	            List attrNamesList = new ArrayList();
	            List attrValueList = new ArrayList();
	            List attrReqTypeList = new ArrayList();
	            List attrStatusList = new ArrayList();

	            for (FivezerooneAppFormsAttributesInfo fZOAttrInfo : fZOAttrList) {
	                String attrbName = String.valueOf(fZOAttrInfo.getAttributeName());
	                String attrbValue = String.valueOf(fZOAttrInfo.getAttributeValue());

	                attrNamesList.add(attrbName.replace("null", ""));
	                attrValueList.add(attrbValue.replace("null", ""));
	                attrReqTypeList.add(fZOAttrInfo.getRequiredAttribute());
	                attrStatusList.add(fZOAttrInfo.getStatus());
	            }

	            sn.setAttribute("FZO_Attr_Names", attrNamesList);
	            sn.setAttribute("FZO_Attr_Values", attrValueList);
	            sn.setAttribute("FZO_Attr_Req_List", attrReqTypeList);
	            sn.setAttribute("FZO_Attr_Status_List", attrStatusList);

//	    			Federal forms Attribute Required type Ids take from DB 
	            List<Object> attrReqTypeIdsList = (List<Object>) fiveZeroOneAttrInfoDAOImpl.attributeReqTypeIDsList();
	            int totalReqAttrCount = attrReqTypeIdsList.size();
	            String attrReqTypeIds = attrReqTypeIdsList.toString();
	            attrReqTypeIds = attrReqTypeIds.replace("[", "");
	            attrReqTypeIds = attrReqTypeIds.replace("]", "");

//	    			Radio Button Status take from DB 			
	            List<Object> rbStatus = (List<Object>) fiveZeroOneAttrInfoDAOImpl.fiveZeroOneFormRadioButtonStatus();

//	    			Form Modification Code                
	            	List<FivezerooneAppFormsAttributesAndValuesSaving> busFZOFormAttrAndValModel = fiveZOFormsAttrAndValuesDAOImpl.findByallFZOFormsCapturedInfoFromDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(userChoiceInSn));
	                String capturedInfoInDB = null;
	                for (FivezerooneAppFormsAttributesAndValuesSaving fZOFormAttrAndValModel : busFZOFormAttrAndValModel) {
	                    capturedInfoInDB = fZOFormAttrAndValModel.getCapturedInformation();
	                }
//	              	Formation From String to JSON
	                JSONObject capInfoJsonObject = convertFromStringToJSONFormat(capturedInfoInDB);
	                               
	                mav = fiveZeroOneFormAttributesInfo(capInfoJsonObject, req);        

	                AllFederalFormsDataSaving federalFormsUpdateModel = allFederalFormsDataSavingDAOImpl.findByallFederalFormsDataFromDB((String) sn.getAttribute(userNameInSn), fzoApplication, (String) sn.getAttribute(userChoiceInSn));
	                String fiveZeroOneFormPageValues = federalFormsUpdateModel.getPageVariableReference();

	                mav = new ModelAndView(newFiveZeroOneFormsCreation);
	                mav.addObject(attribTextFieldList, req.getAttribute(attribTextFieldList));
	                mav.addObject(attribTextFieldZipList, req.getAttribute(attribTextFieldZipList));
	                mav.addObject(attribTextFieldDateList, req.getAttribute(attribTextFieldDateList));
	                mav.addObject(attribTextAreaList, req.getAttribute(attribTextAreaList));
	                mav.addObject(attribSelectBoxList, req.getAttribute(attribSelectBoxList));
	                mav.addObject(radioButtList, req.getAttribute(radioButtList));
	                mav.addObject(checkBoxesList, req.getAttribute(checkBoxesList));                
	                mav.addObject("fiveZeroOneFormPageValues", fiveZeroOneFormPageValues);	            
	            mav.addObject(radioButtStatus, rbStatus);
	            sn.setAttribute(radioButtStatus, rbStatus);
	            sn.setAttribute(attrbReqTypeIdsInSn, attrReqTypeIds);
	            sn.setAttribute(totalReqAttrbCountInSn, totalReqAttrCount);
	            mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
	        return mav;
	    }
	    
//		501 Form Attributes Info
		    @Override
		    @Transactional
		    public ModelAndView fiveZeroOneFormAttributesInfo(JSONObject capInfoJsonObject, HttpServletRequest req) {
		        LOGGER.debug("fiveZeroOneFormAttributesInfo...Service");
		        ModelAndView mav = new ModelAndView();
		        String attributeTextFieldList = null;
	            for (int i = 1; i <= 430; i++) {
	                String keyVal = String.valueOf(i);
	                String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
	                attributeTextFieldList = attributeTextFieldList + jSonObjVal + ",$,";
	            }
	            attributeTextFieldList = attributeTextFieldList.replace("null", "");

	            String attributeTextFieldZipList = null;
	            for (int j = 431; j <= 460; j++) {
	                String keyVal = String.valueOf(j);
	                String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
	                attributeTextFieldZipList = attributeTextFieldZipList + jSonObjVal + ",$,";
	            }
	            attributeTextFieldZipList = attributeTextFieldZipList.replace("null", "");

	            String attributeTextFieldDateList = null;
	            for (int k = 461; k <= 474; k++) {
	                String keyVal = String.valueOf(k);
	                String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
	                attributeTextFieldDateList = attributeTextFieldDateList + jSonObjVal + ",$,";
	            }
	            attributeTextFieldDateList = attributeTextFieldDateList.replace("null", "");

	            String attributeTextAreaList = null;
	            for (int l = 475; l <= 682; l++) {
	                String keyVal = String.valueOf(l);
	                String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
	                jSonObjVal = jSonObjVal.replaceAll("[\n\r]", " ");
	                attributeTextAreaList = attributeTextAreaList + jSonObjVal + ",$,";
	            }
	            attributeTextAreaList = attributeTextAreaList.replace("null", "");

	            String attributeSelectBoxList = null;
	            for (int m = 683; m <= 711; m++) {
	                String keyVal = String.valueOf(m);
	                String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
	                attributeSelectBoxList = attributeSelectBoxList + jSonObjVal + ",$,";
	            }
	            attributeSelectBoxList = attributeSelectBoxList.replace("null", "");

	            String radioButtonList = null;
	            for (int n = 712; n <= 964; n++) {
	                String keyVal = String.valueOf(n);
	                String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
	                radioButtonList = radioButtonList + jSonObjVal + ",$,";
	            }
	            radioButtonList = radioButtonList.replace("null", "");

	            String checkBoxList = null;
	            for (int o = 965; o <= 970; o++) {
	                String keyVal = String.valueOf(o);
	                String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
	                checkBoxList = checkBoxList + jSonObjVal + ",$,";
	            }
	            checkBoxList = checkBoxList.replace("null", "");
		        
		        req.setAttribute(attribTextFieldList, attributeTextFieldList);
		        req.setAttribute(attribTextFieldZipList, attributeTextFieldZipList);
		        req.setAttribute(attribTextFieldDateList, attributeTextFieldDateList);
		        req.setAttribute(attribTextAreaList, attributeTextAreaList);
		        req.setAttribute(attribSelectBoxList, attributeSelectBoxList);
		        req.setAttribute(radioButtList, radioButtonList);
		        req.setAttribute(checkBoxesList, checkBoxList);
		        return mav;
		    }
		    
//			501 Form Modified data saving Service Implementation
		    @Override
		    @Transactional
		    public ModelAndView comp501AppModifiedDataSaving(HttpServletRequest req, HttpSession sn) {
		        LOGGER.debug("comp501AppModifiedDataSaving...Service");
		        JSONObject stateFormInfoObj = fiveZeroOneFormsAttributesInfoFromJSP(req);
		        ModelAndView mav;        

		//Already Exit User choice when ever directly clicking enter with out using mouse        
		        List userAEChoiceList = allFederalFormsDataSavingDAOImpl.allFederalFormsAlreadyExittUserChoiceInDB((String) sn.getAttribute(userNameInSn), fzoApplication, req.getParameter(attributeTextField1));
		        String alreadyExitChoice = null;        
		        if (!userAEChoiceList.isEmpty()) {
		            AllFederalFormsDataSaving federalFormsAEModel = allFederalFormsDataSavingDAOImpl.findByallFederalFormsDataFromDB((String) sn.getAttribute(userNameInSn), fzoApplication, req.getParameter(attributeTextField1));
		            alreadyExitChoice = federalFormsAEModel.getDocumentName();            
		        }        
		        if (alreadyExitChoice != null  && !alreadyExitChoice.equals(sn.getAttribute(userChoiceInSn))) {
		            mav = new ModelAndView(newFiveZeroOneFormsCreation);
		            mav.addObject(alreadyHaveChoice, alreadyHaveUserChoice);
		        } else {
		        	String formStatus = fiveZeroOneFormStatusInDB(req, sn);
		            String jsonStringObj = stateFormInfoObj.toString();
		            jsonStringObj = jsonStringObj.replace("null", "\"\"");

		            Timestamp currentDate = currentDate();		            
		                FivezerooneAppFormsAttributesAndValuesSaving fzoAttrAndValModel = fiveZOFormsAttrAndValuesDAOImpl.findByFZOFormsAttributesAndValuesFromDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(userChoiceInSn));
		                fzoAttrAndValModel.setAppName(req.getParameter(attributeTextField1));
		                fzoAttrAndValModel.setCapturedInformation(jsonStringObj);
		                fzoAttrAndValModel.setModifiedDate(currentDate);
		                fiveZOFormsAttrAndValuesDAOImpl.merge(fzoAttrAndValModel);

		                AllFederalFormsDataSaving fedTaxFormsModel = allFederalFormsDataSavingDAOImpl.findByallFederalFormsDataFromDB((String) sn.getAttribute(userNameInSn), fzoApplication, (String) sn.getAttribute(userChoiceInSn));
		                fedTaxFormsModel.setDocumentName(req.getParameter(attributeTextField1));
		                fedTaxFormsModel.setStatus(formStatus);
		                fedTaxFormsModel.setModifiedDate(currentDate);
		                fedTaxFormsModel.setPageVariableReference(req.getParameter(fzoFormPageVal));
		                allFederalFormsDataSavingDAOImpl.merge(fedTaxFormsModel);

		                if(!(sn.getAttribute(userChoiceInSn)).equals(req.getParameter(attributeTextField1))) {            		
//			                  All related tables updating
			                  mav = fzoFormsAllRelatedOtherTablesUpdate(req, sn);
			            }
			          
		            sn.setAttribute(userChoiceInSn, req.getParameter(attributeTextField1));
		            if (req.getParameter(fzoFinishOrderRef) != null && (finishedStatus).equals(req.getParameter(fzoFinishOrderRef))) {
		                mav = completedFederalFormsRedirection(req, sn);
		            } else {
		                mav = new ModelAndView(newFiveZeroOneFormsCreation);
		            }
		            mav.addObject(fzoFormPageVal, req.getParameter(fzoFormPageVal));

		            sn.setAttribute(fzoAttrbNames, sn.getAttribute(fzoAttrbNames));
		            sn.setAttribute(fzoAttrbValues, sn.getAttribute(fzoAttrbValues));
		            sn.setAttribute(fzoAttrbReqList, sn.getAttribute(fzoAttrbReqList));
		            sn.setAttribute(fzoAttrbStatusList, sn.getAttribute(fzoAttrbStatusList));
		            sn.setAttribute(radioButtStatus, sn.getAttribute(radioButtStatus));
		            sn.setAttribute(typeOfDocumentInSn, fzoApplication);
		        }
		        mav.addObject(attribTextFieldList, req.getAttribute(attribTextFieldList));
		        mav.addObject(attribTextFieldZipList, req.getAttribute(attribTextFieldZipList));
		        mav.addObject(attribTextFieldDateList, req.getAttribute(attribTextFieldDateList));
		        mav.addObject(attribTextAreaList, req.getAttribute(attribTextAreaList));        
		        mav.addObject(attribSelectBoxList, req.getAttribute(attribSelectBoxList));
		        mav.addObject(radioButtList, req.getAttribute(radioButtList));
		        mav.addObject(checkBoxesList, req.getAttribute(checkBoxesList));
		        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		        return mav;
		    }
		    
		 // 501 Form Status
		    @Override
		    @Transactional
		    public String fiveZeroOneFormStatusInDB(HttpServletRequest req, HttpSession sn) {
		    	LOGGER.debug("fiveZeroOneFormStatusInDB...Service");
		        String formStatus = null;
		        JSONObject stateFormInfoObj = fiveZeroOneFormsAttributesInfoFromJSP(req);
		        int reqAttrCount = 0;
		        String[] attributeIdsString = ((String) sn.getAttribute(attrbReqTypeIdsInSn)).split(", ");
		        int totalReqAttrCount = attributeIdsString.length;

		        for (int i = 0; i < attributeIdsString.length; i++) {
		            String attrIdInStr = attributeIdsString[i];
		            int attrIdInt = Integer.parseInt(attrIdInStr);

		            String jSonObjVal = (String) stateFormInfoObj.get(attrIdInt);
		            if (jSonObjVal != null && jSonObjVal != "") {
		                reqAttrCount = reqAttrCount + 1;
		            }
		        }
		        if (totalReqAttrCount == reqAttrCount) {
		            formStatus = readyForCheckout;
		        } else {
		            formStatus = inProgress;
		        }
		        return formStatus;
		    }

//			501 Form Attributes info from JSP
		    @Override
		    @Transactional
		    public JSONObject fiveZeroOneFormsAttributesInfoFromJSP(HttpServletRequest req) {
		        LOGGER.debug("fiveZeroOneFormsAttributesInfoFromJSP...Service");
		        JSONObject stateFormInfoObj = new JSONObject();
		        String attributeTextFieldList = null;
		        String[] attributeTextField = new String[431];
		        for (int i = 1; i < attributeTextField.length; i++) {
		            attributeTextField[i] = req.getParameter("attributeTextField" + i);
		            attributeTextFieldList = attributeTextFieldList + attributeTextField[i] + ",$,";
		            stateFormInfoObj.put(i, attributeTextField[i]);            
		        }
		        attributeTextFieldList = attributeTextFieldList.replace("null", "");

		        String attributeTextFieldZipList = null;
		        String[] attributeTextFieldZip = new String[31];
		        for (int j = 1; j < attributeTextFieldZip.length; j++) {
		            attributeTextFieldZip[j] = req.getParameter("attributeTextFieldZip" + j);
		            attributeTextFieldZipList = attributeTextFieldZipList + attributeTextFieldZip[j] + ",$,";
		            stateFormInfoObj.put(j + 430, attributeTextFieldZip[j]);            
		        }
		        attributeTextFieldZipList = attributeTextFieldZipList.replace("null", "");

		        String attributeTextFieldDateList = null;
		        String[] attributeTextFieldDate = new String[15];
		        for (int k = 1; k < attributeTextFieldDate.length; k++) {
		            attributeTextFieldDate[k] = req.getParameter("attributeTextFieldDate" + k);
		            attributeTextFieldDateList = attributeTextFieldDateList + attributeTextFieldDate[k] + ",$,";
		            stateFormInfoObj.put(k + 460, attributeTextFieldDate[k]);            
		        }
		        attributeTextFieldDateList = attributeTextFieldDateList.replace("null", "");

		        String attributeTextAreaList = null;
		        String[] attributeTextArea = new String[209];
		        for (int l = 1; l < attributeTextArea.length; l++) {
		            attributeTextArea[l] = req.getParameter("attributeTextArea" + l);
		            attributeTextAreaList = attributeTextAreaList + attributeTextArea[l] + ",$,";
		            stateFormInfoObj.put(l + 474, attributeTextArea[l]);            
		        }
		        attributeTextAreaList = attributeTextAreaList.replace("null", "");
		        attributeTextAreaList = attributeTextAreaList.replaceAll("[\n\r]", " ");

		        String attributeSelectBoxList = null;
		        String[] attributeSelectBox = new String[30];
		        for (int m = 1; m < attributeSelectBox.length; m++) {
		            attributeSelectBox[m] = req.getParameter("attributeSelectBox" + m);
		            attributeSelectBoxList = attributeSelectBoxList + attributeSelectBox[m] + ",$,";
		            stateFormInfoObj.put(m + 682, attributeSelectBox[m]);            
		        }
		        attributeSelectBoxList = attributeSelectBoxList.replace("null", "");

		        String radioButtonList = null;
		        String[] radioButton = new String[254];
		        for (int n = 1; n < radioButton.length; n++) {
		            radioButton[n] = req.getParameter("radioButton" + n);
		            radioButtonList = radioButtonList + radioButton[n] + ",$,";
		            stateFormInfoObj.put(n + 711, radioButton[n]);            
		        }
		        radioButtonList = radioButtonList.replace("null", "");

		        String checkBoxList = null;
		        String[] checkBox = new String[7];
		        for (int o = 1; o < checkBox.length; o++) {
		            String checkSingele = req.getParameter("checkBox" + o);
		            if (checkSingele != null) {
		                String[] checkBoxVal = req.getParameterValues("checkBox" + o);
		                String ddynamicCheckValue = "";
		                for (int p = 0; p < checkBoxVal.length; p++) {
		                    ddynamicCheckValue = ddynamicCheckValue + ", " + checkBoxVal[p];
		                }
		                ddynamicCheckValue = ddynamicCheckValue.replaceFirst(", ", "");
		                checkBoxList = checkBoxList + ddynamicCheckValue + ",$,";
		                stateFormInfoObj.put(o + 964, ddynamicCheckValue);
		            } else {
		                checkBoxList = checkBoxList + checkSingele + ",$,";
		                stateFormInfoObj.put(o + 964, checkSingele);                
		            }
		        }
		        checkBoxList = checkBoxList.replace("null", "");
		        
		        req.setAttribute(attribTextFieldList, attributeTextFieldList);
		        req.setAttribute(attribTextFieldZipList, attributeTextFieldZipList);
		        req.setAttribute(attribTextFieldDateList, attributeTextFieldDateList);
		        req.setAttribute(attribTextAreaList, attributeTextAreaList);
		        req.setAttribute(attribSelectBoxList, attributeSelectBoxList);
		        req.setAttribute(radioButtList, radioButtonList);
		        req.setAttribute(checkBoxesList, checkBoxList);
		        return stateFormInfoObj;
		    }
		    
//		    501 All Related Other Tables Update		    
			  @Override
			  @Transactional
			  public ModelAndView fzoFormsAllRelatedOtherTablesUpdate(HttpServletRequest req, HttpSession sn) {
				  LOGGER.debug("fzoFormsAllRelatedOtherTablesUpdate...Service");
				  ModelAndView mav = new ModelAndView();
				  Timestamp currentDate = currentDate(); 
				  
//		        All related tables updating
				  List allFederaslFormsPaymentList = allFederalFormsPaymentInfoDAOImpl.allFederalFormsPaymentRowVerification((String) sn.getAttribute(userNameInSn), fzoApplication, (String) sn.getAttribute(userChoiceInSn));
	                if (!allFederaslFormsPaymentList.isEmpty()) {
	                    AllFederalFormsPaymentInfoSaving allFedFormsPaymentInfoSaving = allFederalFormsPaymentInfoDAOImpl.allFederalFormsPaymentModificationVerification((String) sn.getAttribute(userNameInSn), fzoApplication, (String) sn.getAttribute(userChoiceInSn));
	                    allFedFormsPaymentInfoSaving.setLegalName(req.getParameter(attributeTextField1));
	                    allFedFormsPaymentInfoSaving.setUpdatedDate(currentDate);
	                    allFederalFormsPaymentInfoDAOImpl.merge(allFedFormsPaymentInfoSaving);
	                
	                    AllFederalFormsCheckoutPaymentAndUserContactSaving allFedCheckoutPaymentAndUserContact = allFedCheckoutPaymentAndContactDAOImpl.allFederalFormsCheckoutPaymentDataDeleteFromCart((String) sn.getAttribute(userNameInSn), fzoApplication, (String) sn.getAttribute(userChoiceInSn));
	                    allFedCheckoutPaymentAndUserContact.setLegalName(req.getParameter(attributeTextField1));
	                    allFedCheckoutPaymentAndUserContact.setModifiedDate(currentDate);
	                    allFedCheckoutPaymentAndContactDAOImpl.merge(allFedCheckoutPaymentAndUserContact);
	                }
			  return mav;
			  }
		    
//	End point

//Free federal Starting Point			  

//	Free Federal form data redirection
@Override
@Transactional
public ModelAndView compFreeFederalFormsDataOperationsFinishOrder(HttpServletRequest req, HttpSession sn) {
	LOGGER.debug("compFreeFederalFormsDataOperationsFinishOrder...Service");	    	
	ModelAndView mav = new ModelAndView();
	String allCompFreeFedFormsRefType = req.getParameter("allCompFreeFedFormsRefType");
	String[] comValue = (req.getParameter("formName")).split(",&, ");
	sn.setAttribute(userNameInSn, comValue[0]);
	sn.setAttribute(stateNameInSn, comValue[1]);
	sn.setAttribute(formNameInSn, comValue[2]);
	sn.setAttribute(userChoiceInSn, comValue[3]);
	sn.setAttribute(ffLegalNameInSn, comValue[4]);
	sn.setAttribute("FreeFederalTaxIdInSn", comValue[5]);
	if(("Order Info").equals(allCompFreeFedFormsRefType)) {	
		mav = freeFederalTaxIdFormsCheckouDataDisplay(req, sn);
	} else if(("View PDF").equals(allCompFreeFedFormsRefType)) {
		mav = allCompFreeFederalFormsViewPDFRedirection(req, sn);
	}
	mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
	return mav;
}

//Free Federal Tax id Forms Checkout display Service Implementation 
@Override
@Transactional
public ModelAndView freeFederalTaxIdFormsCheckouDataDisplay(HttpServletRequest req, HttpSession sn) {
    LOGGER.debug("freeFederalTaxIdFormsCheckouDataDisplay...Service");
    ModelAndView mav = new ModelAndView();
//Required values take from DB
    	List<FormFederalAttributesAndValuesSaving> formFedAttrAndValModel = formFedAttrAndValuesSavingDAOImpl.formFederalFormsCapturedInfoFromDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(userChoiceInSn), (String) sn.getAttribute(ffLegalNameInSn));
        String capturedInfoInDB = null;
        for (FormFederalAttributesAndValuesSaving formFederalAttrAndValDataModel : formFedAttrAndValModel) {
            capturedInfoInDB = formFederalAttrAndValDataModel.getCapturedInformation();
        }            
//      Formation From String to JSON
        JSONObject capInfoJsonObject = convertFromStringToJSONFormat(capturedInfoInDB);
      
//  Federal Tax Ids take from DB 			
        List attrNamesList = new ArrayList();
        List attrFieldIdsList = new ArrayList();
       
        List<Object> formFieldsAndValuesIds = (List<Object>) federalTaxAttrInfoDAOImpl.federalTaxFormsDynamicFieldsAndValuesIDs();
        Iterator formFieldsAndValuesIdsIterator = formFieldsAndValuesIds.iterator();
        while (formFieldsAndValuesIdsIterator.hasNext()) {
            Object[] formFieldsAndValuesIdsObj = (Object[]) formFieldsAndValuesIdsIterator.next();

            String attrFieldId = String.valueOf(formFieldsAndValuesIdsObj[0]);
            String attrNames = String.valueOf(formFieldsAndValuesIdsObj[2]);
            
            attrFieldIdsList.add(attrFieldId);
            attrNamesList.add(attrNames + " ");            
        }

        String attrFieldIDListInString = attrFieldIdsList.toString();
        attrFieldIDListInString = attrFieldIDListInString.replace("[", "");
        attrFieldIDListInString = attrFieldIDListInString.replace("]", "");
        
        String attrNamesListInString = attrNamesList.toString();
        attrNamesListInString = attrNamesListInString.replace("[", "");
        attrNamesListInString = attrNamesListInString.replace("]", "");        
        
        String[] attrFieldIDInString = attrFieldIDListInString.split(", ");
        String[] attrNamesInString = attrNamesListInString.split(" , ");
        
        List attrFFNamesList = new ArrayList();        
        List attrFFValuesList = new ArrayList();
        
        for (int i = 0; i < attrFieldIDInString.length; i++) {
            String attrFieldId = attrFieldIDInString[i];
            String attrNames = attrNamesInString[i];
            
            String jSonObjVal = (String) capInfoJsonObject.get(attrFieldId);
            if (!("").equals(jSonObjVal)) {

                String attrValue = jSonObjVal;

                for (int k = 0; k < jSonObjVal.length() + 9; k++) {
                    String yesVal = yes + k;
                    String noVal = no + k;
                    if (yesVal.equals(jSonObjVal)) {
                        attrValue = jSonObjVal.replaceAll(yesVal, yes);
                    } else if (noVal.equals(jSonObjVal)) {
                        attrValue = jSonObjVal.replaceAll(noVal, no);
                    }
                }
                attrFFNamesList.add(attrNames);                
                attrFFValuesList.add(attrValue);                
            }
        }
        mav = new ModelAndView("freeFederalCheckoutDisplayCreation");
        sn.setAttribute("Fed_Attr_Names_CheckOutList", attrFFNamesList);        
        sn.setAttribute("Fed_Attr_Values_CheckOutList", attrFFValuesList); 
        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
    return mav;
}

//Completed Free Federal forms checkout Order Display Redirection

@Override
@Transactional
public ModelAndView compCheckoutFreeFederalTaxFormsDisplayRedirection(HttpServletRequest req, HttpSession sn) {
    LOGGER.debug("completedCheckoutFreeFederalTaxIdFormsOrderDisplay...Service");
    String compCheOrderDisplayRefType = req.getParameter("compCheOrderDisplayRefType");
    ModelAndView mav = new ModelAndView();
    if((BackMove).equals(compCheOrderDisplayRefType)) {    	
    	mav = completedFreeFederalFormsRedirection(req, sn);
    } else if((FormModify).equals(compCheOrderDisplayRefType)) {	    	
    	mav = compFreeFederalTaxIDFormUpdation(req, sn);
    }
    mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
    return mav;
}
		
//Free federal data update in DB Tables
@Override
@Transactional
public ModelAndView compFreeFederalTaxIDFormUpdation(HttpServletRequest req, HttpSession sn) {
	LOGGER.debug("compFreeFederalTaxIDFormUpdation...Service");
	ModelAndView mav = new ModelAndView();
//	Federal form: Names, Values and Required values take from DB
	List<FederalTaxIdFormsAttributesInfo> federalTaxAttrList = federalTaxAttrInfoDAOImpl.federalFormDynamicFormShowHideData();

	List attrNamesList = new ArrayList();
	List attrValueList = new ArrayList();
	List attrReqTypeList = new ArrayList();
	List attrStatusList = new ArrayList();

	for (FederalTaxIdFormsAttributesInfo federalTaxAttrInfo : federalTaxAttrList) {
	    String attrbName = String.valueOf(federalTaxAttrInfo.getAttributeFieldName());
	    String attrbValue = String.valueOf(federalTaxAttrInfo.getAttributeValue());

	    attrNamesList.add(attrbName.replace("null", ""));
	    attrValueList.add(attrbValue.replace("null", ""));
	    attrReqTypeList.add(federalTaxAttrInfo.getRequiredType());
	    attrStatusList.add(federalTaxAttrInfo.getStatus());
	}

	sn.setAttribute(ffAttrbNames, attrNamesList);
	sn.setAttribute(ffAttrbValues, attrValueList);
	sn.setAttribute(ffAttrbReqList, attrReqTypeList);
	sn.setAttribute(ffAttrbStatusList, attrStatusList);
//	Federal forms Attribute Required type Ids take from DB 
	List<Object> attrReqTypeIdsList = (List<Object>) federalTaxAttrInfoDAOImpl.attributeReqTypeIDsList();
	int totalReqAttrCount = attrReqTypeIdsList.size();
	String attrReqTypeIds = attrReqTypeIdsList.toString();
	attrReqTypeIds = attrReqTypeIds.replace("[", "");
	attrReqTypeIds = attrReqTypeIds.replace("]", "");

//	Radio Button Status take from DB 			
	List<Object> rbStatus = (List<Object>) federalTaxAttrInfoDAOImpl.federalFormRadioButtonStatus();

	    List<FormFederalAttributesAndValuesSaving> formFedAttrAndValModel = formFedAttrAndValuesSavingDAOImpl.formFederalFormsCapturedInfoFromDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(userChoiceInSn), (String) sn.getAttribute(ffLegalNameInSn));
	    String capturedInfoInDB = null;
	    for (FormFederalAttributesAndValuesSaving formFederalAttrAndValDataModel : formFedAttrAndValModel) {
	        capturedInfoInDB = formFederalAttrAndValDataModel.getCapturedInformation();
	    }
//	  Formation From String to JSON
	    JSONObject capInfoJsonObject = convertFromStringToJSONFormat(capturedInfoInDB);
	    
	    mav = freeFederalTaxIdAttributesInfoSelection(capInfoJsonObject, req);

	    FormFederalDocumentsDataSaving formFederalUpdateModel = formFederalDataSavingDAOImpl.formFederalFormsDataTakeFromDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(userChoiceInSn), (String) sn.getAttribute(ffLegalNameInSn));
	    String freeFederalFormPageValues = formFederalUpdateModel.getPageVariableReference();

	    mav = new ModelAndView(freeFedTaxModification);
	    
	    mav.addObject(freeFedAttrTextFieldList, req.getAttribute(attribTextFieldList));
	    mav.addObject(freeFedAttrTextFieldDateList, req.getAttribute(attribTextFieldDateList));
	    mav.addObject(freeFedAttrSelectBoxList, req.getAttribute(attribSelectBoxList));
	    mav.addObject(freeFedRBList, req.getAttribute(radioButtList));
	    mav.addObject(freeFedCBList, req.getAttribute(checkBoxesList));
	    
	    mav.addObject(freeFedFormPageValue, freeFederalFormPageValues);
	
	mav.addObject(freeFedRadioButtStatus, rbStatus);
	sn.setAttribute(freeFedRadioButtStatus, rbStatus);
	sn.setAttribute(attrbReqTypeIdsInSn, attrReqTypeIds);
	sn.setAttribute(totalReqAttrbCountInSn, totalReqAttrCount);	
	mav.addObject("firstChoice", sn.getAttribute(userChoiceInSn));
	mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
	return mav;
	}
	
//	Free Federal Tax Id Forms Attributes Info Selection
	@Override
	@Transactional
	public ModelAndView freeFederalTaxIdAttributesInfoSelection(JSONObject capInfoJsonObject, HttpServletRequest req) {
	    LOGGER.debug("freeFederalTaxIdAttributesInfoSelection...Service");
	    ModelAndView mav = new ModelAndView();
	    
	    String attributeTextFieldList = null;
	    for (int i = 1; i <= 44; i++) {
	        String keyVal = String.valueOf(i);
	        String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
	        attributeTextFieldList = attributeTextFieldList + jSonObjVal + ",$,";
	    }
	    attributeTextFieldList = attributeTextFieldList.replace("null", "");

	    String attributeTextFieldDateList = null;
	    for (int j = 45; j <= 46; j++) {
	        String keyVal = String.valueOf(j);
	        String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
	        attributeTextFieldDateList = attributeTextFieldDateList + jSonObjVal + ",$,";
	    }
	    attributeTextFieldDateList = attributeTextFieldDateList.replace("null", "");

	    String radioButtonList = null;
	    for (int k = 47; k <= 54; k++) {
	        String keyVal = String.valueOf(k);
	        String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
	        radioButtonList = radioButtonList + jSonObjVal + ",$,";
	    }
	    radioButtonList = radioButtonList.replace("null", "");

	    String keyVal1 = String.valueOf(55);
	    String jSonObjVal1 = (String) capInfoJsonObject.get(keyVal1);
	    String checkBoxList = jSonObjVal1 + ",$,";
	    checkBoxList = checkBoxList.replace("null", "");

	    String attributeSelectBoxList = null;
	    for (int k = 56; k <= 62; k++) {
	        String keyVal = String.valueOf(k);
	        String jSonObjVal = (String) capInfoJsonObject.get(keyVal);
	        attributeSelectBoxList = attributeSelectBoxList + jSonObjVal + ",$,";
	    }
	    attributeSelectBoxList = attributeSelectBoxList.replace("null", "");
	    
	    req.setAttribute(attribTextFieldList, attributeTextFieldList);
	    req.setAttribute(attribTextFieldDateList, attributeTextFieldDateList);
	    req.setAttribute(attribSelectBoxList, attributeSelectBoxList);
	    req.setAttribute(radioButtList, radioButtonList);
	    req.setAttribute(checkBoxesList, checkBoxList);             
	    return mav;
	}
	
//	Free Federal Form Modified Data Saving Service Implementation
	@Override
	@Transactional
	public ModelAndView compFreeFederalTaxModifiedDataSaving(HttpServletRequest req, HttpSession sn) {
	    LOGGER.debug("compFreeFederalTaxModifiedDataSaving...Service"); 
	    JSONObject stateFormInfoObj = freeFederalFormsAttributesInfoFromJSP(req);
	    ModelAndView mav;                
//Already Exit User choice when ever directly clicking enter with out using mouse
	    List userAEChoiceList = formFederalDataSavingDAOImpl.freeFederalFormsAlreadyExittUserChoiceInDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(userChoiceInSn), req.getParameter(attributeTextField1));        
	    String alreadyExitChoice = null;        
	    if (!userAEChoiceList.isEmpty()) {        	
	    	FormFederalDocumentsDataSaving formFederalAEModel = formFederalDataSavingDAOImpl.formFederalFormsDataTakeFromDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(userChoiceInSn), req.getParameter(attributeTextField1));
	        alreadyExitChoice = formFederalAEModel.getLegalName();            
	    }        
	    if (alreadyExitChoice != null && !alreadyExitChoice.equals(sn.getAttribute(ffLegalNameInSn))) {
	        mav = new ModelAndView(freeFedTaxModification);
	        mav.addObject("AlreadyExitChoice", "AlreadyExitUserChoice");
	    } else {
	    	String formStatus = freeFederalFormStatusInDB(req, sn);          

	        String jsonStringObj = stateFormInfoObj.toString();
	        jsonStringObj = jsonStringObj.replace("null", "\"\"");
	        Timestamp currentDate = currentDate();	        
	        	FormFederalAttributesAndValuesSaving freeFedAttrAndValModel = formFedAttrAndValuesSavingDAOImpl.freeFederalTaxFormsAttributesAndValuesFromDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(userChoiceInSn), (String) sn.getAttribute(ffLegalNameInSn));
	        	freeFedAttrAndValModel.setLegalName(req.getParameter(attributeTextField1));
	        	freeFedAttrAndValModel.setUserChoice((String) sn.getAttribute(userChoiceInSn));
	        	freeFedAttrAndValModel.setCapturedInformation(jsonStringObj);
	        	freeFedAttrAndValModel.setModifiedDate(currentDate);
	            formFedAttrAndValuesSavingDAOImpl.merge(freeFedAttrAndValModel);

	            FormFederalDocumentsDataSaving formFederalUpdateModel = formFederalDataSavingDAOImpl.formFederalFormsDataTakeFromDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(userChoiceInSn), (String) sn.getAttribute(ffLegalNameInSn));                
	            formFederalUpdateModel.setLegalName(req.getParameter(attributeTextField1));
	            formFederalUpdateModel.setUserChoice((String) sn.getAttribute(userChoiceInSn));
	            formFederalUpdateModel.setStatus(formStatus);
	            formFederalUpdateModel.setModifiedDate(currentDate);
	            formFederalUpdateModel.setPageVariableReference(req.getParameter(freeFedFormPageValue));
	            formFederalDataSavingDAOImpl.merge(formFederalUpdateModel);	               
	    	
	        List allFreeFederalLegalNameList = allStateFormsCheckoutPaymentDAOImpl.freeFederalLegalNameTakeFromPaymentContactDB((String) sn.getAttribute(userNameInSn), "Business Forms", (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(userChoiceInSn));
	        if(!allFreeFederalLegalNameList.isEmpty()){
	        AllStateFormsCheckoutPaymentAndUserContactSaving allStCheckoutPaymentAndUserContact = allStateFormsCheckoutPaymentDAOImpl.allStateFormsCheckoutPaymentDataDeleteFromCart((String) sn.getAttribute(userNameInSn), "Business Forms", (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(userChoiceInSn));
	        allStCheckoutPaymentAndUserContact.setFormFedLegalname(req.getParameter(attributeTextField1));            
	        allStateFormsCheckoutPaymentDAOImpl.merge(allStCheckoutPaymentAndUserContact);
	        
	        AllStateFormsDataSaving stateFormsModel = allStateFormsDataSavingDAOImpl.freeFederalStateFormsDataRowCheckingInDB((String) sn.getAttribute(userNameInSn), "Business Forms", (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(userChoiceInSn));
	    	stateFormsModel.setFormFederalLegalname(req.getParameter(attributeTextField1));
	    	stateFormsModel.setFederalStatus(formStatus);
	    	allStateFormsDataSavingDAOImpl.merge(stateFormsModel);
	    	
	    	AllStateFormsPaymentInfoSaving allStateFormsPaymentInfoSaving = allStateFormsPaymentSavingDAOImpl.allStateFormsPaymentModificationVerification((String) sn.getAttribute(userNameInSn), "Business Forms", (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(userChoiceInSn));
	    	allStateFormsPaymentInfoSaving.setFormFedStatus(formStatus);
            allStateFormsPaymentSavingDAOImpl.merge(allStateFormsPaymentInfoSaving);
	        }

	        sn.setAttribute(ffLegalNameInSn, req.getParameter(attributeTextField1));
	        if (req.getParameter(freeFedDocFinishOrderRef) != null && ("Finished").equals(req.getParameter(freeFedDocFinishOrderRef))) {                
	            mav = completedFreeFederalFormsRedirection(req, sn);
	        } else {
	            mav = new ModelAndView(freeFedTaxModification);
	        }

	        mav.addObject(freeFedFormPageValue, req.getParameter(freeFedFormPageValue));

	        sn.setAttribute(ffAttrbNames, sn.getAttribute(ffAttrbNames));
	        sn.setAttribute(ffAttrbValues, sn.getAttribute(ffAttrbValues));
	        sn.setAttribute(ffAttrbReqList, sn.getAttribute(ffAttrbReqList));
	        sn.setAttribute(ffAttrbStatusList, sn.getAttribute(ffAttrbStatusList));
	        sn.setAttribute(freeFedRadioButtStatus, sn.getAttribute(freeFedRadioButtStatus));                   
	    }
	    mav.addObject(freeFedAttrTextFieldList, req.getAttribute(attribTextFieldList));
	    mav.addObject(freeFedAttrTextFieldDateList, req.getAttribute(attribTextFieldDateList));
	    mav.addObject(freeFedAttrSelectBoxList, req.getAttribute(attribSelectBoxList));
	    mav.addObject(freeFedRBList, req.getAttribute(radioButtList));
	    mav.addObject(freeFedCBList, req.getAttribute(checkBoxesList));
	    mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
	    
	    return mav;
	}
	
//				Free Federal Forms Attributes Data take from JSP
	@Override
	@Transactional
	public JSONObject freeFederalFormsAttributesInfoFromJSP(HttpServletRequest req) {
	    LOGGER.debug("freeFederalFormsAttributesInfoFromJSP...Service");
	    JSONObject stateFormInfoObj = new JSONObject();
	    
	    String attributeTextFieldList = null;
	    String[] attributeTextField = new String[45];
	    for (int i = 1; i < attributeTextField.length; i++) {
	        attributeTextField[i] = req.getParameter("attributeTextField" + i);
	        attributeTextFieldList = attributeTextFieldList + attributeTextField[i] + ",$,";
	        stateFormInfoObj.put(i, attributeTextField[i]);            
	    }
	    attributeTextFieldList = attributeTextFieldList.replace("null", "");

	    String attributeTextFieldDateList = null;
	    String[] attributeTextFieldDate = new String[3];
	    for (int j = 1; j < attributeTextFieldDate.length; j++) {
	        attributeTextFieldDate[j] = req.getParameter("attributeTextFieldDate" + j);
	        attributeTextFieldDateList = attributeTextFieldDateList + attributeTextFieldDate[j] + ",$,";
	        stateFormInfoObj.put(j + 44, attributeTextFieldDate[j]);            
	    }
	    attributeTextFieldDateList = attributeTextFieldDateList.replace("null", "");

	    String radioButtonList = null;
	    String[] radioButton = new String[9];
	    for (int k = 1; k < radioButton.length; k++) {
	        radioButton[k] = req.getParameter("radioButton" + k);
	        radioButtonList = radioButtonList + radioButton[k] + ",$,";
	        stateFormInfoObj.put(k + 46, radioButton[k]);            
	    }
	    radioButtonList = radioButtonList.replace("null", "");

	    String checkBoxList = null;
	    String[] checkBox = new String[2];
	    for (int l = 1; l < checkBox.length; l++) {
	        checkBox[l] = req.getParameter("checkBox" + l);
	        checkBoxList = checkBoxList + checkBox[l] + ",$,";
	        stateFormInfoObj.put(l + 54, checkBox[l]);            
	    }
	    checkBoxList = checkBoxList.replace("null", "");

	    String attributeSelectBoxList = null;
	    String[] attributeSelectBox = new String[8];
	    for (int n = 1; n < attributeSelectBox.length; n++) {
	        attributeSelectBox[n] = req.getParameter("attributeSelectBox" + n);
	        attributeSelectBoxList = attributeSelectBoxList + attributeSelectBox[n] + ",$,";
	        stateFormInfoObj.put(n + 55, attributeSelectBox[n]);            
	    }
	    attributeSelectBoxList = attributeSelectBoxList.replace("null", "");
	    
	    req.setAttribute(attribTextFieldList, attributeTextFieldList);
	    req.setAttribute(attribTextFieldDateList, attributeTextFieldDateList);
	    req.setAttribute(attribSelectBoxList, attributeSelectBoxList);
	    req.setAttribute(radioButtList, radioButtonList);
	    req.setAttribute(checkBoxesList, checkBoxList);
	    return stateFormInfoObj;
	}
	
//  Free Federal Form Status
	@Override
	@Transactional
	public String freeFederalFormStatusInDB(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("freeFederalFormStatusInDB...Service");
			  String formStatus = null;
			  JSONObject stateFormInfoObj = freeFederalFormsAttributesInfoFromJSP(req);
			  int reqAttrCount = 0;
		        String[] attributeIdsString = ((String) sn.getAttribute(attrbReqTypeIdsInSn)).split(", ");
		        int totalReqAttrCount = attributeIdsString.length;

		        for (int i = 0; i < attributeIdsString.length; i++) {
		            String attrIdInStr = attributeIdsString[i];
		            int attrIdInt = Integer.parseInt(attrIdInStr);

		            String jSonObjVal = (String) stateFormInfoObj.get(attrIdInt);
		            if (jSonObjVal != null && jSonObjVal != "") {
		                reqAttrCount = reqAttrCount + 1;
		            }
		        }

		        if (totalReqAttrCount == reqAttrCount) {
		            formStatus = "Ready for checkout";
		        } else {
		            formStatus = "In Progress";
		        }
			  return formStatus;
	}			  
			  
//End Point			  
			  
			  
	
	//Convert From String Value To JSON Format
			@Override
			@Transactional
			public JSONObject convertFromStringToJSONFormat(String capturedInfoInDB) {
				LOGGER.debug("convertFromStringToJsonFormat...Service");
			JSONParser parser = new JSONParser();
			JSONObject capInfoJsonObject = new JSONObject();
		
			try {
			    Object parseObj = parser.parse(capturedInfoInDB);
			    capInfoJsonObject = (JSONObject) parseObj;
			} catch (ParseException e) {
			    LOGGER.error("Convert String To Json Format is Fail " + e);
			}
			return capInfoJsonObject;
			} 
		
		//Form Id getting from DB
		@Override
		@Transactional
		public int takeFormIdFromDB(HttpSession sn) {
			LOGGER.debug("takeFormIdFromDB...method");		
			List<Forms> stFormId = bsFormAndStateAttrInfoDAOImpl.stateFormsIdValueFromDB((String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(formNameInSn));
			int formId = 0;
			for (Forms formsInfo : stFormId) {
			    formId = formsInfo.getFormId();
			}	
			return formId;
		}
		
		//current date
		@Override
		@Transactional
		public Timestamp currentDate() {
			LOGGER.debug("currentDate...method");
			java.util.Date date = new java.util.Date();
			Timestamp currentDate = new Timestamp(date.getTime());    
			return currentDate;
		}
		
		//Form Id getting from DB
			@Override
			@Transactional
			public int takeUserIdFromDB(HttpSession sn) {
				LOGGER.debug("takeUserIdFromDB...method");		
				List<UsersInformation> userIdList = usersInformationDAOImpl.userIdValueFromDB((String) sn.getAttribute(userNameInSn));
				int userId = 0;
				for (UsersInformation usersInfo : userIdList) {
					userId = usersInfo.getUserId();
				}	
				return userId;
			}
	
	
	

}