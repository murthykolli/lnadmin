package com.legalnod.serviceimpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.legalnod.daoimpl.AdditionalFormsAttributesAndValuesSavingDAOImpl;
import com.legalnod.daoimpl.AdditionalFormsAttributesInfoDAOImpl;
import com.legalnod.daoimpl.AllFederalFormsDataSavingDAOImpl;
import com.legalnod.daoimpl.AllStateFormsDataSavingDAOImpl;
import com.legalnod.daoimpl.BusinessFormsAttributesAndValuesSavingDAOImpl;
import com.legalnod.daoimpl.BusinessFormsAttributesInfoDAOImpl;
import com.legalnod.daoimpl.FederalTaxIdFormsAttributesAndValuesSavingDAOImpl;
import com.legalnod.daoimpl.FederalTaxIdFormsAttributesInfoDAOImpl;
import com.legalnod.daoimpl.FivezerooneAppFormsAttributesAndValuesSavingDAOImpl;
import com.legalnod.daoimpl.FivezerooneAppFormsAttributesInfoDAOImpl;
import com.legalnod.daoimpl.FormFederalAttributesAndValuesSavingDAOImpl;
import com.legalnod.daoimpl.FormFederalDocumentsDataSavingDAOImpl;
import com.legalnod.daoimpl.ScorporationFormsAttributesAndValuesSavingDAOImpl;
import com.legalnod.daoimpl.ScorporationFormsAttributesInfoDAOImpl;
import com.legalnod.daoimpl.StateTaxFormsAttributesAndValuesSavingDAOImpl;
import com.legalnod.daoimpl.StateTaxFormsAttributesInfoDAOImpl;
import com.legalnod.daoimpl.UsersInformationDAOImpl;
import com.legalnod.model.AdditionalFormsAttributesAndValuesSaving;
import com.legalnod.model.AllFederalFormsDataSaving;
import com.legalnod.model.AllStateFormsDataSaving;
import com.legalnod.model.BusinessFormsAttributesAndValuesSaving;
import com.legalnod.model.FederalTaxIdFormsAttributesAndValuesSaving;
import com.legalnod.model.FivezerooneAppFormsAttributesAndValuesSaving;
import com.legalnod.model.FormFederalAttributesAndValuesSaving;
import com.legalnod.model.FormFederalDocumentsDataSaving;
import com.legalnod.model.Forms;
import com.legalnod.model.ScorporationFormsAttributesAndValuesSaving;
import com.legalnod.model.StateTaxFormsAttributesAndValuesSaving;
import com.legalnod.model.UsersInformation;
import com.legalnod.service.PendingFormsService;

/**
 * @author MurthyK
 *
 */

public class PendingFormsServiceImpl implements PendingFormsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PendingFormsServiceImpl.class);
	
	private String pendStateFormsCount = "pendStateFormsCount";
    private String pendingFedFormsCount = "pendingFedFormsCount";
    private String pendingFreeFedFormsCount = "pendingFreeFedFormsCount";
    
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
    
    private String federalTaxId = "Federal Tax ID";    
    private String sCorporation = "S Corporation";
    private String fzoApplication = "501 Application";
    
    private String ffLegalNameInSn = "freeLegalNameInSn";
    
    private String adminFirstName = "adminFirstName";
    private String adminFirstNameInSn = "adminFirstNameInSn";
    private String adminUserNameInSn = "adminUserNameInSn";
    
	@Autowired
    private AllStateFormsDataSavingDAOImpl allStateFormsDataSavingDAOImpl;
	
	@Autowired
    private AllFederalFormsDataSavingDAOImpl allFederalFormsDataSavingDAOImpl;
	
	@Autowired
    private FormFederalDocumentsDataSavingDAOImpl formFederalDataSavingDAOImpl;
	
	@Autowired
    private BusinessFormsAttributesInfoDAOImpl bsFormAndStateAttrInfoDAOImpl;

    @Autowired
    private BusinessFormsAttributesAndValuesSavingDAOImpl bsFormAndStateValDAOImpl;	
	
	@Autowired
    private AdditionalFormsAttributesInfoDAOImpl additionalFormsAttrInfoDAOImpl;

    @Autowired
    private AdditionalFormsAttributesAndValuesSavingDAOImpl addSerFormAttrAndValSavingDAOImpl;
    
    @Autowired
    private StateTaxFormsAttributesInfoDAOImpl stateTaxFormsAttrInfoDAOImpl;
	
	@Autowired
    private StateTaxFormsAttributesAndValuesSavingDAOImpl stateTaxAttrAndValuesSavingDAOImpl;
    
    @Autowired
    private UsersInformationDAOImpl usersInformationDAOImpl;
    
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
    private FormFederalAttributesAndValuesSavingDAOImpl formFedAttrAndValuesSavingDAOImpl;
    
    @Autowired
    private AdminHomeServiceImpl adminHomeServiceImpl;
		
	
//	Pending Forms Redirection Service Implementation
	
	@Override
	@Transactional
	public ModelAndView pendingFormsRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("pendingFormsRedirection...Service");		
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute(adminUserNameInSn) != null){
		List<AllStateFormsDataSaving> pendStateFormsDataList = allStateFormsDataSavingDAOImpl.pendingStateFormsDataDisplayInAdmin();                
        List<AllFederalFormsDataSaving> penFederalFormsSavingInfoList = allFederalFormsDataSavingDAOImpl.pendingFederalFormsDataDisplayInAdmin();
        List<FormFederalDocumentsDataSaving> pendFreeFedFormsDataList = formFederalDataSavingDAOImpl.pendingFreeFederalFormsDataDisplayInAdmin();
        
        if (!pendStateFormsDataList.isEmpty()) {
            mav = pendingStateFormsRedirection(req, sn);
        }else if (!penFederalFormsSavingInfoList.isEmpty() && pendStateFormsDataList.isEmpty()) {
            mav = pendingFederalFormsRedirection(req, sn);
        } else if (!pendFreeFedFormsDataList.isEmpty()) {
            mav = pendingFreeFederalFormsRedirection(req, sn);
        } else{
        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);
        }
        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		}
        return mav;
    }
	
//	Pending State Forms Redirection Service Implementation
	
	@Override
	@Transactional
	public ModelAndView pendingStateFormsRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("pendingFormsRedirection...Service");		
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute(adminUserNameInSn) != null){
		List<AllStateFormsDataSaving> pendStateFormsDataList = allStateFormsDataSavingDAOImpl.pendingStateFormsDataDisplayInAdmin();                
        List<AllFederalFormsDataSaving> penFederalFormsSavingInfoList = allFederalFormsDataSavingDAOImpl.pendingFederalFormsDataDisplayInAdmin();
        List<FormFederalDocumentsDataSaving> pendFreeFedFormsDataList = formFederalDataSavingDAOImpl.pendingFreeFederalFormsDataDisplayInAdmin();
        
        if (!pendStateFormsDataList.isEmpty()) {
            List<AllStateFormsDataSaving> pendStateFormSavingList = new ArrayList<AllStateFormsDataSaving>();
            for (AllStateFormsDataSaving pendStateFormsInfo : pendStateFormsDataList) {
                AllStateFormsDataSaving pndStateFormsInfoModel = new AllStateFormsDataSaving();
                pndStateFormsInfoModel.setUserName(pendStateFormsInfo.getUserName());
                pndStateFormsInfoModel.setStateName(pendStateFormsInfo.getStateName());
                pndStateFormsInfoModel.setFormName(pendStateFormsInfo.getFormName());
                pndStateFormsInfoModel.setUserChoice(pendStateFormsInfo.getUserChoice());
                pndStateFormsInfoModel.setTypeOfDocument(pendStateFormsInfo.getTypeOfDocument());
                Timestamp lastEditedDate = null;
                if (pendStateFormsInfo.getModifiedDate() != null) {
                    lastEditedDate = pendStateFormsInfo.getModifiedDate();
                } else {
                    lastEditedDate = pendStateFormsInfo.getCreatedDate();
                }
                pndStateFormsInfoModel.setCreatedDate(lastEditedDate);

                pendStateFormSavingList.add(pndStateFormsInfoModel);
            }           
            mav = new ModelAndView("pendingStateForms");
            mav.addObject("pendStateFormSavingList", pendStateFormSavingList);
            mav.addObject(pendStateFormsCount, pendStateFormsDataList.size());
            mav.addObject(pendingFedFormsCount, penFederalFormsSavingInfoList.size());
            mav.addObject(pendingFreeFedFormsCount, pendFreeFedFormsDataList.size());
        } else{
        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);
        }
        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		}
        return mav;
    }
	
//	Pending Federal Forms Redirection Service Implementation
	
	@Override
	@Transactional
	public ModelAndView pendingFederalFormsRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("pendingFederalFormsRedirection...Service");		
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute(adminUserNameInSn) != null){
		List<AllStateFormsDataSaving> pendStateFormsDataList = allStateFormsDataSavingDAOImpl.pendingStateFormsDataDisplayInAdmin();                
        List<AllFederalFormsDataSaving> penFederalFormsSavingInfoList = allFederalFormsDataSavingDAOImpl.pendingFederalFormsDataDisplayInAdmin();
        List<FormFederalDocumentsDataSaving> pendFreeFedFormsDataList = formFederalDataSavingDAOImpl.pendingFreeFederalFormsDataDisplayInAdmin();
        
        if (!penFederalFormsSavingInfoList.isEmpty()) {
            List<AllFederalFormsDataSaving> penFedFormSavingList = new ArrayList<AllFederalFormsDataSaving>();

            for (AllFederalFormsDataSaving penFederalFormsInfo : penFederalFormsSavingInfoList) {
                AllFederalFormsDataSaving penFederalFormsInfoModel = new AllFederalFormsDataSaving();
                penFederalFormsInfoModel.setUserName(penFederalFormsInfo.getUserName());
                penFederalFormsInfoModel.setTypeOfDocument(penFederalFormsInfo.getTypeOfDocument());
                penFederalFormsInfoModel.setDocumentName(penFederalFormsInfo.getDocumentName());
                Timestamp lastEditedDate = null;
                if (penFederalFormsInfo.getModifiedDate() != null) {
                    lastEditedDate = penFederalFormsInfo.getModifiedDate();
                } else {
                    lastEditedDate = penFederalFormsInfo.getCreatedDate();
                }
                penFederalFormsInfoModel.setCreatedDate(lastEditedDate);

                penFedFormSavingList.add(penFederalFormsInfoModel);

            }
            mav = new ModelAndView("pendingFederalForms");
            mav.addObject("fedFormSavingList", penFedFormSavingList);
            mav.addObject(pendStateFormsCount, pendStateFormsDataList.size());
            mav.addObject(pendingFedFormsCount, penFederalFormsSavingInfoList.size());
            mav.addObject(pendingFreeFedFormsCount, pendFreeFedFormsDataList.size());
        } else{
        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);
        }
        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		}
        return mav;
    }
	
//	Pending Free Federal Forms Redirection Service Implementation
	
	@Override
	@Transactional
	public ModelAndView pendingFreeFederalFormsRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("pendingFreeFederalFormsRedirection...Service");		
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute(adminUserNameInSn) != null){
		List<AllStateFormsDataSaving> pendStateFormsDataList = allStateFormsDataSavingDAOImpl.pendingStateFormsDataDisplayInAdmin();                
        List<AllFederalFormsDataSaving> penFederalFormsSavingInfoList = allFederalFormsDataSavingDAOImpl.pendingFederalFormsDataDisplayInAdmin();
        List<FormFederalDocumentsDataSaving> pendFreeFedFormsDataList = formFederalDataSavingDAOImpl.pendingFreeFederalFormsDataDisplayInAdmin();
        
        if (!pendFreeFedFormsDataList.isEmpty()) {
            List<FormFederalDocumentsDataSaving> pendFreeFedFormSavingList = new ArrayList<FormFederalDocumentsDataSaving>();
            for (FormFederalDocumentsDataSaving pendFreeFedFormsInfo : pendFreeFedFormsDataList) {
                FormFederalDocumentsDataSaving pndFreeFedFormsInfoModel = new FormFederalDocumentsDataSaving();
                pndFreeFedFormsInfoModel.setUserName(pendFreeFedFormsInfo.getUserName());
                pndFreeFedFormsInfoModel.setStateName(pendFreeFedFormsInfo.getStateName());
                pndFreeFedFormsInfoModel.setFormName(pendFreeFedFormsInfo.getFormName());
                pndFreeFedFormsInfoModel.setUserChoice(pendFreeFedFormsInfo.getUserChoice());
                pndFreeFedFormsInfoModel.setLegalName(pendFreeFedFormsInfo.getLegalName());
                Timestamp lastEditedDate = null;
                if (pendFreeFedFormsInfo.getModifiedDate() != null) {
                    lastEditedDate = pendFreeFedFormsInfo.getModifiedDate();
                } else {
                    lastEditedDate = pendFreeFedFormsInfo.getCreatedDate();
                }
                pndFreeFedFormsInfoModel.setCreatedDate(lastEditedDate);

                pendFreeFedFormSavingList.add(pndFreeFedFormsInfoModel);
            }           
            mav = new ModelAndView("pendingFreeFederalForms");
            mav.addObject("pendFreeFedFormSavingList", pendFreeFedFormSavingList);
            mav.addObject(pendStateFormsCount, pendStateFormsDataList.size());
            mav.addObject(pendingFedFormsCount, penFederalFormsSavingInfoList.size());
            mav.addObject(pendingFreeFedFormsCount, pendFreeFedFormsDataList.size());
        } else{
        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);
        }
        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		}
        return mav;
    }
	
//	Pending State Forms Check out Display
	
	//	All Forms Pending Checkout Data Display Finish Order
	    @Override
	    @Transactional
	    public ModelAndView allStateFormsDataOperationsFinishOrder(HttpServletRequest req, HttpSession sn) {
	    	LOGGER.debug("allStateFormsDataOperationsFinishOrder...Service");
	    	ModelAndView mav = new ModelAndView();
	    	if(sn.getAttribute(adminUserNameInSn) != null){
	    	String[] comValue = (req.getParameter("formName")).split(",&, ");
	    	sn.setAttribute(userNameInSn, comValue[0]);
	    	sn.setAttribute(stateNameInSn, comValue[1]);
	    	sn.setAttribute(formNameInSn, comValue[2]);
	    	sn.setAttribute(userChoiceInSn, comValue[3]);
	    	sn.setAttribute(typeOfDocumentInSn, comValue[4]);
	    	
	    	if(("OrderInfo").equals(req.getParameter("allStateFormsRefType"))){
	    	if ((businessForm).equals((String) sn.getAttribute(typeOfDocumentInSn))) {	        
	            mav = businessStateFormsCheckouDataDisplay(req, sn);	            
	        } else if ((additionalForm).equals((String) sn.getAttribute(typeOfDocumentInSn))) {
	            mav = additionalServiceFormsCheckouDataDisplay(req, sn);
	        } else if ((stateTaxIdForm).equals((String) sn.getAttribute(typeOfDocumentInSn))) {
	            mav = stateTaxIdFormsCheckouDataDisplay(req, sn);
	        }
	    	} else if(("EmailSend").equals(req.getParameter("allStateFormsRefType"))){
	    		mav = stateFormsEmainlSendingToUser(req, sn, comValue[0], comValue[1], comValue[2], comValue[3], comValue[4]);
	    		mav = pendingStateFormsRedirection(req, sn);	    		
	    		mav.addObject("UserEmailStatus", req.getAttribute("EmailSent"));
	    	}
	    	mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
	    	}
	    	return mav;
	    }
	    
//State FormsEmail sending from admin to User	
	    
	    public ModelAndView stateFormsEmainlSendingToUser(HttpServletRequest request, HttpSession session, String userName, String stateName, String formName, String userChoice, String typeOfDoc) {
	    	LOGGER.debug("stateFormsEmainlSendingToUser...Service");
	        EmailSending userEmail = new EmailSending();
	        ModelAndView mav;        
	            String aTo = userName;
	            String aSubject = "Legal Nod Info.";            
	            String type = "text/html";            
	            String body = "<body> <div style='margin-left: 10px;margin-top:10px;background: #f1f1f1;width: 850px;box-shadow: 3px 3px 0px #7e7e7e;height: 250px;color: black;'> "
	                    + "<div style='background: #666666;height: 35px;width:851px;'>"
	                    + "<font style='color: white;font-size:20px;font-family:Georgia; vertical-align: middle;'><b>&nbsp;&nbsp; <span style='vertical-align: middle; margin-top: 15px; position: absolute;'>LegalNod <font color='yellow'>-</font> Email</span></b></font> "
	                    + "</div> "
	                    + "<div style='margin-top: 5px; width: 850px; height: 220px;font: 1.1em/1.26em sans-serif;text-align: justify;'> <br/><br/>"
	                    + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <b>Hello &nbsp;<font style=' color: #00a9f1;'>" + userName + ",</font></b> <br/><br/><br/> "
	                    + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; State Name &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + stateName + " <br/><br/>"
	                    + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Form Name &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + formName + " <br/><br/>"
	                    + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; User Choice &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + userChoice + " <br/><br/>"
	                    + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Type Of Document &nbsp; : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + typeOfDoc + " <br/><br/>"
	                    + " <br/><br/><br/> "                    
	                    + "</div><br/><br/></div>"
	                    + "</body> ";            
	            try {
	                userEmail.sendEmail(aTo, aSubject, body, type);
	                String outObj = "Email sent to "+userName+".";
	                request.setAttribute("EmailSent", outObj);
	            } catch (Exception ex) {
	            	LOGGER.error("Sending Email Checking " + ex);
	            }
	            mav = new ModelAndView();            
	        return mav;
	    }
	    
	//	all state Forms Checkout Service Implementation 
	    @Override
	    @Transactional
	    public ModelAndView businessStateFormsCheckouDataDisplay(HttpServletRequest req, HttpSession sn) {
	    	LOGGER.debug("businessStateFormsCheckouDataDisplay...Service");
	        ModelAndView mav = new ModelAndView();
	//		Form Id getting from DB
	        int formId = takeFormIdFromDB(sn);
	        int userId = takeUserIdFromDB(sn);
	//		Required values take from DB		
	        if (sn.getAttribute(userChoiceInSn) != null) {
	            List<BusinessFormsAttributesAndValuesSaving> busStFormAttrAndValModel = bsFormAndStateValDAOImpl.findByallStateFormsCapturedInfoFromDB(userId, formId, (String) sn.getAttribute(userChoiceInSn));
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
	            mav = new ModelAndView("pendingStateFormsCheckoutDisplay");
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
	public ModelAndView additionalServiceFormsCheckouDataDisplay(HttpServletRequest req, HttpSession sn) {
	    LOGGER.debug("additionalServiceFormsCheckouDataDisplay...Service");
	    ModelAndView mav = new ModelAndView();
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
	    mav = new ModelAndView("pendingAdditionalFormsCheckoutDisplay");
	    sn.setAttribute("Attr_AS_Names_CheckOutList", attrSTNamesList);
	    sn.setAttribute("Attr_AS_Values_CheckOutList", attrSTValuesList);
	    
	    mav.addObject(stateName, (String) sn.getAttribute(stateNameInSn));
	    mav.addObject(formName, (String) sn.getAttribute(formNameInSn));
	    mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
	    return mav;
	}
	
//State Tax ID Forms Checkout Service Implementation 
	@Override
	@Transactional
	public ModelAndView stateTaxIdFormsCheckouDataDisplay(HttpServletRequest req, HttpSession sn) {
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
	        mav = new ModelAndView("pendingStateTaxIdFormsCheckoutDisplay");
	        sn.setAttribute("Attr_STI_Names_CheckOutList", attrSTNamesList);
	        sn.setAttribute("Attr_STI_Values_CheckOutList", attrSTValuesList);
	        
	        mav.addObject(stateName, (String) sn.getAttribute(stateNameInSn));
	        mav.addObject(formName, (String) sn.getAttribute(formNameInSn));
	    }
	    mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
	    return mav;
	}
	
//	Pending Federal Forms Check out Display
	
	//All Federal Forms Data Operations Form Finish Order
    @Override
    @Transactional
    public ModelAndView allFederalFormsDataOperationsFormFinishOrder(HttpServletRequest req, HttpSession sn) {
        LOGGER.debug("allFederalFormsDataOperationsFormFinishOrder...Service");    	
        ModelAndView mav = new ModelAndView();
        if(sn.getAttribute(adminUserNameInSn) != null){
        String[] comValue = (req.getParameter("documentsComb")).split(",&, ");
    	sn.setAttribute(userNameInSn, comValue[0]);    	
    	sn.setAttribute(typeOfDocumentInSn, comValue[1]);
    	sn.setAttribute(userChoiceInSn, comValue[2]);
    	 
    	if(("OrderInfo").equals(req.getParameter("allFederalFormsRefType"))){
        if ((federalTaxId).equals((String) sn.getAttribute(typeOfDocumentInSn))) {        	
            mav = federalTaxIdFormsCheckouDataDisplay(req, sn);
        } else if ((sCorporation).equals((String) sn.getAttribute(typeOfDocumentInSn))) {
            mav = sCorporationFormsCheckouDataDisplay(req, sn);
        } else if ((fzoApplication).equals((String) sn.getAttribute(typeOfDocumentInSn))) {
            mav = fiveZeroOneFormsCheckouDataDisplay(req, sn);
        }
    	} else if(("EmailSend").equals(req.getParameter("allFederalFormsRefType"))){
    		mav = federalFormsEmainlSendingToUser(req, sn, comValue[0], comValue[2], comValue[1]);
    		mav = pendingFederalFormsRedirection(req, sn);	    		
    		mav.addObject("UserEmailStatus", req.getAttribute("EmailSent"));
    	}
        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
        }
        return mav;
    }
        
//State FormsEmail sending from admin to User	
    
    public ModelAndView federalFormsEmainlSendingToUser(HttpServletRequest request, HttpSession session, String userName, String userChoice, String typeOfDoc) {
    	LOGGER.debug("federalFormsEmainlSendingToUser...Service");
        EmailSending userEmail = new EmailSending();
        ModelAndView mav;        
            String aTo = userName;
            String aSubject = "Legal Nod Info.";            
            String type = "text/html";            
            String body = "<body> <div style='margin-left: 10px;margin-top:10px;background: #f1f1f1;width: 850px;box-shadow: 3px 3px 0px #7e7e7e;height: 250px;color: black;'> "
                    + "<div style='background: #666666;height: 35px;width:851px;'>"
                    + "<font style='color: white;font-size:20px;font-family:Georgia; vertical-align: middle;'><b>&nbsp;&nbsp; <span style='vertical-align: middle; margin-top: 15px; position: absolute;'>LegalNod <font color='yellow'>-</font> Email</span></b></font> "
                    + "</div> "
                    + "<div style='margin-top: 5px; width: 850px; height: 220px;font: 1.1em/1.26em sans-serif;text-align: justify;'> <br/><br/>"
                    + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <b>Hello &nbsp;<font style=' color: #00a9f1;'>" + userName + ",</font></b> <br/><br/><br/> "
                    + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; User Choice &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + userChoice + " <br/><br/>"
                    + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Type Of Document &nbsp; : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + typeOfDoc + " <br/><br/>"
                    + " <br/><br/><br/> "                    
                    + "</div><br/><br/></div>"
                    + "</body> ";            
            try {
                userEmail.sendEmail(aTo, aSubject, body, type);
                String outObj = "Email sent to "+userName+".";
                request.setAttribute("EmailSent", outObj);
            } catch (Exception ex) {
            	LOGGER.error("Sending Email Checking " + ex);
            }
            mav = new ModelAndView();            
        return mav;
    }
    
  //all Federal Tax id Forms Checkout Service Implementation 
    @Override
    @Transactional
    public ModelAndView federalTaxIdFormsCheckouDataDisplay(HttpServletRequest req, HttpSession sn) {
        LOGGER.debug("federalTaxIdFormsCheckouDataDisplay...Service");
        ModelAndView mav = new ModelAndView();

//	Required values take from DB		
        if (sn.getAttribute(userChoiceInSn) != null) {
            List<FederalTaxIdFormsAttributesAndValuesSaving> busFedFormAttrAndValModel = federalFormsAttrAndValuesDAOImpl.findByallFederalFormsCapturedInfoFromDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(userChoiceInSn));
            String capturedInfoInDB = null;
            for (FederalTaxIdFormsAttributesAndValuesSaving federalFormAttrAndValModel : busFedFormAttrAndValModel) {
                capturedInfoInDB = federalFormAttrAndValModel.getCapturedInformation();
            }
//          Formation From String to JSON
            JSONObject capInfoJsonObject = convertFromStringToJSONFormat(capturedInfoInDB);
          
//	  Federal Tax Ids take from DB 			
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
            mav = new ModelAndView("pendingFederalTaxFormsCheckoutDisplay");
            sn.setAttribute("Fed_Attr_Names_CheckOutList", attrFFNamesList);            
            sn.setAttribute("Fed_Attr_Values_CheckOutList", attrFFValuesList);            
        }
        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
        return mav;
    }
    
  //all S Corporation Forms Checkout Service Implementation 
    @Override
    @Transactional
    public ModelAndView sCorporationFormsCheckouDataDisplay(HttpServletRequest req, HttpSession sn) {
        LOGGER.debug("sCorporationFormsCheckouDataDisplay...Service");
        ModelAndView mav = new ModelAndView();
//Required values take from DB		
        if (sn.getAttribute(userChoiceInSn) != null) {
            List<ScorporationFormsAttributesAndValuesSaving> busSCorpFormAttrAndValModel = scorpoFormsAttrAndValuesDAOImpl.findByallSCorpFormsCapturedInfoFromDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(userChoiceInSn));
            String capturedInfoInDB = null;
            for (ScorporationFormsAttributesAndValuesSaving sCorpFormAttrAndValModel : busSCorpFormAttrAndValModel) {
                capturedInfoInDB = sCorpFormAttrAndValModel.getCapturedInformation();
            }
//          Formation From String to JSON
            JSONObject capInfoJsonObject = convertFromStringToJSONFormat(capturedInfoInDB);
            
//	S Corporation Ids take from DB 			
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
            mav = new ModelAndView("pendingSCorpFormsCheckoutDisplay");
            sn.setAttribute("SCorp_Attr_Names_CheckOutList", attrFFNamesList);            
            sn.setAttribute("SCorp_Attr_Values_CheckOutList", attrFFValuesList);            
        }
        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
        return mav;
    }
    
  //all 501 Application Forms Checkout Service Implementation 
    @Override
    @Transactional
    public ModelAndView fiveZeroOneFormsCheckouDataDisplay(HttpServletRequest req, HttpSession sn) {
        LOGGER.debug("fiveZeroOneFormsCheckouDataDisplay...Service");
        ModelAndView mav = new ModelAndView();

//Required values take from DB		
        if (sn.getAttribute(userChoiceInSn) != null) {
            List<FivezerooneAppFormsAttributesAndValuesSaving> busFZOFormAttrAndValModel = fiveZOFormsAttrAndValuesDAOImpl.findByallFZOFormsCapturedInfoFromDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(userChoiceInSn));
            String capturedInfoInDB = null;
            for (FivezerooneAppFormsAttributesAndValuesSaving fZOFormAttrAndValModel : busFZOFormAttrAndValModel) {
                capturedInfoInDB = fZOFormAttrAndValModel.getCapturedInformation();
            }
//          Formation From String to JSON
            JSONObject capInfoJsonObject = convertFromStringToJSONFormat(capturedInfoInDB);
            
//	501 Application Ids take from DB 			
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
            mav = new ModelAndView("pendingFiveZeroOneFormsCheckoutDisplay");
            sn.setAttribute("FZO_Attr_Names_CheckOutList", attrFFNamesList);
            sn.setAttribute("FZO_Attr_Values_CheckOutList", attrFFValuesList);            
        }
        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
        return mav;
    }
    
//	Pending Free Federal Forms Check out Display
    
  //Free Federal Tax id Forms Checkout Service Implementation 
    @Override
    @Transactional
    public ModelAndView freeFederalTaxIdFormsCheckouDataDisplay(HttpServletRequest req, HttpSession sn) {
        LOGGER.debug("freeFederalTaxIdFormsCheckouDataDisplay...Service");
        ModelAndView mav = new ModelAndView();
       
        String[] comValue = (req.getParameter("formName")).split(",&, ");
    	sn.setAttribute(userNameInSn, comValue[0]);
    	sn.setAttribute(stateNameInSn, comValue[1]);
    	sn.setAttribute(formNameInSn, comValue[2]);
    	sn.setAttribute(userChoiceInSn, comValue[3]);
    	sn.setAttribute(ffLegalNameInSn, comValue[4]);
    	 
//	Required values take from DB		
        if (sn.getAttribute(ffLegalNameInSn) != null) {
        	List<FormFederalAttributesAndValuesSaving> formFedAttrAndValModel = formFedAttrAndValuesSavingDAOImpl.formFederalFormsCapturedInfoFromDB((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(formNameInSn), (String) sn.getAttribute(stateNameInSn), (String) sn.getAttribute(userChoiceInSn), (String) sn.getAttribute(ffLegalNameInSn));
            String capturedInfoInDB = null;
            for (FormFederalAttributesAndValuesSaving formFederalAttrAndValDataModel : formFedAttrAndValModel) {
                capturedInfoInDB = formFederalAttrAndValDataModel.getCapturedInformation();
            } 
            
//          Formation From String to JSON
            JSONObject capInfoJsonObject = convertFromStringToJSONFormat(capturedInfoInDB);
          
//	  Federal Tax Ids take from DB 			
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
            mav = new ModelAndView("pendingFreeFederalFormsCheckoutDisplay");
            sn.setAttribute("Fed_Attr_Names_CheckOutList", attrFFNamesList);
            sn.setAttribute("Fed_Attr_Values_CheckOutList", attrFFValuesList);
        }
        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
        return mav;
    }  
    
	
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
