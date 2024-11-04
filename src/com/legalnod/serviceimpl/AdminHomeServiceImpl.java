package com.legalnod.serviceimpl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.legalnod.daoimpl.AdditionalFormsAttributesInfoDAOImpl;
import com.legalnod.daoimpl.AllFederalFormsCheckoutPaymentAndUserContactSavingDAOImpl;
import com.legalnod.daoimpl.AllFederalFormsDataSavingDAOImpl;
import com.legalnod.daoimpl.AllStateFormsCheckoutPaymentAndUserContactSavingDAOImpl;
import com.legalnod.daoimpl.AllStateFormsDataSavingDAOImpl;
import com.legalnod.daoimpl.BusinessFormsAttributesInfoDAOImpl;
import com.legalnod.daoimpl.CompletedFederalFormsFileUploadSavingDAOImpl;
import com.legalnod.daoimpl.CompletedStateFormsFileUploadSavingDAOImpl;
import com.legalnod.daoimpl.FederalTaxIdFormsAttributesInfoDAOImpl;
import com.legalnod.daoimpl.FivezerooneAppFormsAttributesInfoDAOImpl;
import com.legalnod.daoimpl.FormFederalDocumentsDataSavingDAOImpl;
import com.legalnod.daoimpl.FormsDAOImpl;
import com.legalnod.daoimpl.NameAvailabilityCheckSavingDAOImpl;
import com.legalnod.daoimpl.ScorporationFormsAttributesInfoDAOImpl;
import com.legalnod.daoimpl.StateTaxFormsAttributesInfoDAOImpl;
import com.legalnod.daoimpl.UsersInformationDAOImpl;
import com.legalnod.model.AdditionalFormsAttributesInfo;
import com.legalnod.model.AllFederalFormsCheckoutPaymentAndUserContactSaving;
import com.legalnod.model.AllFederalFormsDataSaving;
import com.legalnod.model.AllStateFormsCheckoutPaymentAndUserContactSaving;
import com.legalnod.model.AllStateFormsDataSaving;
import com.legalnod.model.BusinessFormsAttributesInfo;
import com.legalnod.model.CompletedFederalFormsFileUploadSaving;
import com.legalnod.model.CompletedStateFormsFileUploadSaving;
import com.legalnod.model.FederalTaxIdFormsAttributesInfo;
import com.legalnod.model.FivezerooneAppFormsAttributesInfo;
import com.legalnod.model.FormFederalDocumentsDataSaving;
import com.legalnod.model.Forms;
import com.legalnod.model.NameAvailabilityCheckSaving;
import com.legalnod.model.ScorporationFormsAttributesInfo;
import com.legalnod.model.StateTaxFormsAttributesInfo;
import com.legalnod.model.UsersInformation;
import com.legalnod.service.AdminHomeService;

/**
 * @author MurthyK
 *
 */

public class AdminHomeServiceImpl implements AdminHomeService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminHomeServiceImpl.class);
	
	private String adminFirstName = "adminFirstName";
    private String adminFirstNameInSn = "adminFirstNameInSn";
    private String redURL = "https://www.legalnod.com/legalnod/loginPage";
    private String passwordHeading = "Password";
    private String usrPassword = "password";
	
    @Autowired
    private UsersInformationDAOImpl userInfoDAOImpl;
    
    
    
    @Autowired
    private NameAvailabilityCheckSavingDAOImpl nameAvailabilityCheckSavingDAOImpl;
    
    @Autowired
    private AllStateFormsDataSavingDAOImpl allStateFormsDataSavingDAOImpl;
	
	@Autowired
    private AllFederalFormsDataSavingDAOImpl allFederalFormsDataSavingDAOImpl;
	
	@Autowired
    private FormFederalDocumentsDataSavingDAOImpl formFederalDataSavingDAOImpl;
	
	@Autowired
    private AllStateFormsCheckoutPaymentAndUserContactSavingDAOImpl allStateFormsCheckoutPaymentDAOImpl;
	
	@Autowired
    private AllFederalFormsCheckoutPaymentAndUserContactSavingDAOImpl allFedCheckoutPaymentAndContactDAOImpl;
	
	@Autowired
    private CompletedStateFormsFileUploadSavingDAOImpl compStateFormsFileUploadSavingDAOImpl;
    
    @Autowired
    private CompletedFederalFormsFileUploadSavingDAOImpl compFedFormsFileUploadSavingDAOImpl;
    
    @Autowired
    private FederalTaxIdFormsAttributesInfoDAOImpl federalTaxAttrInfoDAOImpl;
        
    @Autowired
    private ScorporationFormsAttributesInfoDAOImpl sCorpAttrInfoDAOImpl;

    @Autowired
    private FivezerooneAppFormsAttributesInfoDAOImpl fiveZeroOneAttrInfoDAOImpl;
    
    @Autowired
    private FormsDAOImpl formsDAOImpl;
    
    @Autowired
    private BusinessFormsAttributesInfoDAOImpl busFormsAttributesInfoDAOImpl;
    
    @Autowired
    private AdditionalFormsAttributesInfoDAOImpl addFormsAttributesInfoDAOImpl;
    
    @Autowired
    private StateTaxFormsAttributesInfoDAOImpl stateTaxFormsAttributesInfoDAOImpl;
    
    
    public String mD5Encrypt(String password) {
    	LOGGER.debug("mD5Encrypt...Service");
    	String forPassword = password;
        try {        	
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());

            byte byteData[] = md.digest();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            forPassword = sb.toString();
        } catch (NoSuchAlgorithmException exception) {
        	LOGGER.error("mD5Encrypt " + exception);
        }
        return forPassword;
    }
    
//	Admin Home URL Param Redirection Service Implementation
	
	@Override
	@Transactional
	public ModelAndView adminHomeURLParamRedirection(HttpServletRequest req, HttpSession sn) {		
		LOGGER.debug("adminHomeURLParamRedirection...Service");
		ModelAndView mav = new ModelAndView();
		String userNameStr = req.getParameter("param");
		int userId = Integer.parseInt(userNameStr);
		UsersInformation userInfo = userInfoDAOImpl.findById(userId);
		String userName = userInfo.getUserName();
		
		if(userName != null){
		sn.setAttribute("adminUserNameInSn", userName);
		int userNameIndex = (userName).indexOf("@");
        String admFirstName = (userName).substring(0, userNameIndex);
        mav.addObject(adminFirstName, admFirstName);
		sn.setAttribute(adminFirstNameInSn, admFirstName);
		mav = AdminFormsCountDisplay(req, sn);
		}
        return mav;
    }
	
//	Admin Home Redirection Service Implementation
	
	@Override
	@Transactional
	public ModelAndView adminHomeRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("adminHomeRedirection...Service");
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute("adminUserNameInSn") != null){
			mav = AdminFormsCountDisplay(req, sn);
		}
        return mav;
    }	 
	
//	Admin Logout Redirection Service Implementation
	
	@Override
	@Transactional
	public ModelAndView adminUserProfileRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("adminUserProfileRedirection...Service");		
		ModelAndView mav;
		mav = new ModelAndView("adminUserProfile");
		mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
        return mav;
    }
	
	public ModelAndView adminUserProfileUpdatingInDB(HttpServletRequest request, HttpSession session) {
    	LOGGER.debug("adminUserProfileUpdatingInDB...Service");
        String userName = (String) session.getAttribute("adminUserNameInSn");
    	
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");

        String encrypCurPassword = mD5Encrypt(currentPassword);
        String curPassSalt = "Random$SaltValue#WithSpecialCharacters12@$@4&#%^$*";
        String currentPasswordSalt = mD5Encrypt(encrypCurPassword + curPassSalt);

        String encrypNewPassword = mD5Encrypt(newPassword);
        String newPassSalt = "Random$SaltValue#WithSpecialCharacters12@$@4&#%^$*";
        String newPasswordSalt = mD5Encrypt(encrypNewPassword + newPassSalt);

        UsersInformation userData = userInfoDAOImpl.findByUserInfoFromDB(userName);
        String oldPassSalt = userData.getPasswordSalt();
        int userId = userData.getUserId();

        ModelAndView mav;
        if (currentPasswordSalt.equals(oldPassSalt) && session.getAttribute("adminUserNameInSn") != null) {
            UsersInformation userInfo = userInfoDAOImpl.findById(userId);
            userInfo.setPassword(encrypNewPassword);
            userInfo.setPasswordSalt(newPasswordSalt);
            userInfoDAOImpl.merge(userInfo);

            mav = new ModelAndView("adminUserProfile");
            mav.addObject("passMessage", passwordHeading);

        } else {
            mav = new ModelAndView("adminUserProfile");
            mav.addObject("changePasswordError", " Invalid Current Password");
            mav.addObject(usrPassword, passwordHeading);
        }
        mav.addObject(adminFirstName, session.getAttribute(adminFirstNameInSn));
        return mav;
    }
	
//	Admin Logout Redirection Service Implementation
	
	@Override
	@Transactional
	public ModelAndView adminLogOutRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("adminLogOutRedirection...Service");
		ModelAndView mav;		
		sn.setAttribute("adminUserNameInSn", null);
		mav = redirectToLogout();			
        return mav;
    }
	
	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public ModelAndView redirectToLogout() {
    	String projectUrl = redURL;    	
        return new ModelAndView("redirect:" + projectUrl);
    }
	
//	completed Forms Redirection ServcompletedImplementation
	
	@Override
	@Transactional
	public ModelAndView AdminFormsCountDisplay(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("AdminFormsCountDisplay...Service");		
		ModelAndView mav = new ModelAndView();		
		if(sn.getAttribute("adminUserNameInSn") != null){
			List<AllStateFormsDataSaving> pendStateFormsDataList = allStateFormsDataSavingDAOImpl.pendingStateFormsDataDisplayInAdmin();                
	        List<AllFederalFormsDataSaving> penFederalFormsSavingInfoList = allFederalFormsDataSavingDAOImpl.pendingFederalFormsDataDisplayInAdmin();
	        List<FormFederalDocumentsDataSaving> pendFreeFedFormsDataList = formFederalDataSavingDAOImpl.pendingFreeFederalFormsDataDisplayInAdmin();
	        List<NameAvailabilityCheckSaving> compNameAvaCheckDataList = nameAvailabilityCheckSavingDAOImpl.completedNameAvailabilityCheckDataDisplayInAdmin();
	        List<UsersInformation> usersInformationList = userInfoDAOImpl.userPrimaryContactInfoDisplayInAdmin();
	        
		List<AllStateFormsCheckoutPaymentAndUserContactSaving> compStateFormsDataList = allStateFormsCheckoutPaymentDAOImpl.completedStateFormsDataDisplayInAdmin();
		List<AllFederalFormsCheckoutPaymentAndUserContactSaving> compFederalFormsSavingInfoList = allFedCheckoutPaymentAndContactDAOImpl.completedFederalFormsDataDisplayInAdmin();
        List<FormFederalDocumentsDataSaving> compFreeFedFormsDataList = formFederalDataSavingDAOImpl.completedFreeFederalFormsDataDisplayInAdmin();        
        List<CompletedStateFormsFileUploadSaving> completedStateFormsFileUploadList = compStateFormsFileUploadSavingDAOImpl.completedStateFormsUploadedDataSaving();
        List<CompletedFederalFormsFileUploadSaving> compFedFormsFileUploadList = compFedFormsFileUploadSavingDAOImpl.completedFederalFormsUploadedDataSaving();
        
        mav = new ModelAndView("adminHome");
		mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
        mav.addObject("pendStateFormsDataCount", pendStateFormsDataList.size());
        mav.addObject("penFederalFormsDataCount", penFederalFormsSavingInfoList.size());
        mav.addObject("pendFreeFedFormsDataCount", pendFreeFedFormsDataList.size());
        mav.addObject("compNameAvaCheckDataCount", compNameAvaCheckDataList.size());
        mav.addObject("usersContactInformationCount", usersInformationList.size());
                
        mav.addObject("compStateFormsDataCount", compStateFormsDataList.size());
        mav.addObject("compFederalFormsSavingInfoCount", compFederalFormsSavingInfoList.size());
        mav.addObject("compFreeFedFormsDataCount", compFreeFedFormsDataList.size());
        mav.addObject("completedStateFormsFileUploadCount", completedStateFormsFileUploadList.size());
        mav.addObject("compFedFormsFileUploadCount", compFedFormsFileUploadList.size());
		}
        return mav;
    }
	
	//Federal Forms Attributes Modification Start
	
//	Admin Federal Tax Attr Modification Redirection Service Implementation	
	@Override
	@Transactional
	public ModelAndView federalTaxAttrModificationRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("federalTaxAttrModificationRedirection...Service");
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute("adminUserNameInSn") != null){			
//			  Federal Tax Ids Attributs Data from DB 			
            List attributesInfoIdList = new ArrayList();
            List attributeFieldNameList = new ArrayList();
            
            List<Object> formFieldsAndValuesIds = (List<Object>) federalTaxAttrInfoDAOImpl.adminHomeFederalTaxAttributesInfoList();
            Iterator formFieldsAndValuesIdsIterator = formFieldsAndValuesIds.iterator();
            while (formFieldsAndValuesIdsIterator.hasNext()) {
                Object[] formFieldsAndValuesIdsObj = (Object[]) formFieldsAndValuesIdsIterator.next();

                String attrInfoIds = String.valueOf(formFieldsAndValuesIdsObj[0]);
                String attrFieldNames = String.valueOf(formFieldsAndValuesIdsObj[1]);
                
                attributesInfoIdList.add(attrInfoIds);
                attributeFieldNameList.add(attrFieldNames + " ");                
            }
            
            mav = new ModelAndView("federalTaxAttrModification");
            mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
            sn.setAttribute("Fed_Attr_Info_ID_List", attributesInfoIdList);            
            sn.setAttribute("Fed_Attr_Field_Names_List", attributeFieldNameList);            
		}
        return mav;
    }
	
//  Federal Tax JSon Attribute Field Name Updating In DB Service
    @Override
    @Transactional
    public JSONArray federalTaxJSonAttrFieldNameUpdationInDB(String updatedAttrVal, HttpSession sn) {
        LOGGER.debug("federalTaxJSonAttrFieldNameUpdationInDB...Service");
        
        String[] roleName = updatedAttrVal.split(" _ ");
        String attrValueId = roleName[0];
        String attrFieldName = roleName[1];
        int attrInfoId = Integer.parseInt(attrValueId); 
        
        FederalTaxIdFormsAttributesInfo fedTaxFormsModel = federalTaxAttrInfoDAOImpl.federalFormAttrFieldNameUpdateInDB(attrInfoId);        
        fedTaxFormsModel.setAttributeFieldName(attrFieldName);
        federalTaxAttrInfoDAOImpl.merge(fedTaxFormsModel);
        
        String finalObj = attrFieldName + "&&" + attrInfoId;
        JSONArray forms = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("JSonObj", finalObj);
        forms.add(jsonObj);
        return forms;
    }
	
//	Admin SCorp Attr Modification Redirection Service Implementation
	
	@Override
	@Transactional
	public ModelAndView sCorpAttrModificationRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("sCorpAttrModificationRedirection...Service");
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute("adminUserNameInSn") != null){			
//			  S corp Attributs Data from DB 			
            List attributesInfoIdList = new ArrayList();
            List attributeFieldNameList = new ArrayList();
            
            List<Object> formFieldsAndValuesIds = (List<Object>) sCorpAttrInfoDAOImpl.adminHomesCorpAttributesInfoList();
            Iterator formFieldsAndValuesIdsIterator = formFieldsAndValuesIds.iterator();
            while (formFieldsAndValuesIdsIterator.hasNext()) {
                Object[] formFieldsAndValuesIdsObj = (Object[]) formFieldsAndValuesIdsIterator.next();

                String attrInfoIds = String.valueOf(formFieldsAndValuesIdsObj[0]);
                String attrFieldNames = String.valueOf(formFieldsAndValuesIdsObj[1]);
                
                attributesInfoIdList.add(attrInfoIds);
                attributeFieldNameList.add(attrFieldNames + " ");                
            }
            
            mav = new ModelAndView("sCorpAttrModification");
            mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
            sn.setAttribute("SCorp_Attr_Info_ID_List", attributesInfoIdList);            
            sn.setAttribute("SCorp_Attr_Field_Names_List", attributeFieldNameList);            
		}
        return mav;
    }
	
//  S Corp JSon Attribute Field Name Updating In DB Service
    @Override
    @Transactional
    public JSONArray sCorpJSonAttrFieldNameUpdationInDB(String updatedAttrVal, HttpSession sn) {
        LOGGER.debug("sCorpJSonAttrFieldNameUpdationInDB...Service");
        
        String[] roleName = updatedAttrVal.split(" _ ");
        String attrValueId = roleName[0];
        String attrFieldName = roleName[1];
        int attrInfoId = Integer.parseInt(attrValueId); 
        
        ScorporationFormsAttributesInfo fedTaxFormsModel = sCorpAttrInfoDAOImpl.sCorpFormAttrFieldNameUpdateInDB(attrInfoId);        
        fedTaxFormsModel.setAttributeFieldName(attrFieldName);
        sCorpAttrInfoDAOImpl.merge(fedTaxFormsModel);
        
        String finalObj = attrFieldName + "&&" + attrInfoId;
        JSONArray forms = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("JSonObj", finalObj);
        forms.add(jsonObj);
        return forms;
    }
		
//	Admin 501 Attr Modification Redirection Service Implementation
	
	@Override
	@Transactional
	public ModelAndView fiveZeroOneAttrModificationRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("fiveZeroOneAttrModificationRedirection...Service");
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute("adminUserNameInSn") != null){			
//			  501 Attributs Data from DB 			
            List attributesInfoIdList = new ArrayList();
            List attributeFieldNameList = new ArrayList();
            
            List<Object> formFieldsAndValuesIds = (List<Object>) fiveZeroOneAttrInfoDAOImpl.adminHomeFiveZeroOneAttributesInfoList();
            Iterator formFieldsAndValuesIdsIterator = formFieldsAndValuesIds.iterator();
            while (formFieldsAndValuesIdsIterator.hasNext()) {
                Object[] formFieldsAndValuesIdsObj = (Object[]) formFieldsAndValuesIdsIterator.next();

                String attrInfoIds = String.valueOf(formFieldsAndValuesIdsObj[0]);
                String attrFieldNames = String.valueOf(formFieldsAndValuesIdsObj[1]);
                
                attributesInfoIdList.add(attrInfoIds);
                attributeFieldNameList.add(attrFieldNames + " ");                
            }
            
            mav = new ModelAndView("fiveZeroOneAttrModification");
            mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
            sn.setAttribute("FZO_Attr_Info_ID_List", attributesInfoIdList);            
            sn.setAttribute("FZO_Attr_Field_Names_List", attributeFieldNameList);            
		}
        return mav;
    }
	
//  501 JSon Attribute Field Name Updating In DB Service
    @Override
    @Transactional
    public JSONArray fiveZeroOneJSonAttrFieldNameUpdationInDB(String updatedAttrVal, HttpSession sn) {
        LOGGER.debug("fiveZeroOneJSonAttrFieldNameUpdationInDB...Service");
        
        String[] roleName = updatedAttrVal.split(" _ ");
        String attrValueId = roleName[0];
        String attrFieldName = roleName[1];
        int attrInfoId = Integer.parseInt(attrValueId); 
        
        FivezerooneAppFormsAttributesInfo fedTaxFormsModel = fiveZeroOneAttrInfoDAOImpl.fiveZeroOneFormAttrFieldNameUpdateInDB(attrInfoId);        
        fedTaxFormsModel.setAttributeName(attrFieldName);
        fiveZeroOneAttrInfoDAOImpl.merge(fedTaxFormsModel);
        
        String finalObj = attrFieldName + "&&" + attrInfoId;
        JSONArray forms = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("JSonObj", finalObj);
        forms.add(jsonObj);
        return forms;
    }
    
//	Admin Federal Forms Back Moving Service Implementation
	
	@Override
	@Transactional
	public ModelAndView admPendingFederalFormsBackMove(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("admPendingFederalFormsBackMove...Service");
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute("adminUserNameInSn") != null){
            mav = new ModelAndView("adminHome");
            mav.addObject("FederalFormBack", "FederalForms");                        
		}
		mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
        return mav;
    }
	
//	Admin State Forms Back Moving Service Implementation
	
	@Override
	@Transactional
	public ModelAndView admCompStateFormsBackMove(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("admCompStateFormsBackMove...Service");
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute("adminUserNameInSn") != null){
            mav = new ModelAndView("adminHome");
            mav.addObject("FederalFormBack", "StateForms");                        
		}
		mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
        return mav;
    }
	
//	Admin State Forms Attributes Modification Selection Service Implementation
	
	@Override
	@Transactional
	public ModelAndView stateFormsAttributesDisplayModification(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("stateFormsAttributesDisplayModification...Service");
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute("adminUserNameInSn") != null){
			sn.setAttribute("typeOfDocumentInSn", "Business Services");
			List stateNameList = formsDAOImpl.stateSelectionWithTypeOfDocInDB((String) sn.getAttribute("typeOfDocumentInSn"));
			mav = new ModelAndView("stateFormsAttrModification");
            mav.addObject("stateNamesList", stateNameList);
            sn.setAttribute("stateNamesListInSn", stateNameList);
		}
		mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
        return mav;
    }
	
//	Admin Additional Forms Attributes Modification Selection Service Implementation
	
	@Override
	@Transactional
	public ModelAndView additionalAttributesDisplayModification(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("additionalAttributesDisplayModification...Service");
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute("adminUserNameInSn") != null){
			sn.setAttribute("typeOfDocumentInSn", "Additional Services");
			List stateNameList = formsDAOImpl.stateSelectionWithTypeOfDocInDB((String) sn.getAttribute("typeOfDocumentInSn"));
			mav = new ModelAndView("additionalFormsAttrModification");
            mav.addObject("stateNamesList", stateNameList);
            sn.setAttribute("stateNamesListInSn", stateNameList);
		}
		mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
        return mav;
    }
	
//	Admin State Tax Id Forms Attributes Modification Selection Service Implementation
	
	@Override
	@Transactional
	public ModelAndView stateTaxIdFormsAttributesDisplayModification(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("stateTaxIdFormsAttributesDisplayModification...Service");
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute("adminUserNameInSn") != null){
			sn.setAttribute("typeOfDocumentInSn", "State Tax ID");
			List stateNameList = formsDAOImpl.stateSelectionWithTypeOfDocInDB((String) sn.getAttribute("typeOfDocumentInSn"));
			mav = new ModelAndView("stateTaxIdFormsAttrModification");
            mav.addObject("stateNamesList", stateNameList);
            sn.setAttribute("stateNamesListInSn", stateNameList);
		}
		mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
        return mav;
    }

//	Admin Dropdown Json calling select Form Name with State Service
    @Override
    @Transactional
    public JSONArray selectFormNamesWithStateData(String stateName, HttpSession sn) {
    	LOGGER.debug("selectFormNamesWithStateData...Service");    	
    	List<Forms> formsList = formsDAOImpl.formNameSelectionWithStateInDB((String) sn.getAttribute("typeOfDocumentInSn"), stateName);
        JSONArray forms = new JSONArray();
        for (Forms form : formsList) {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("jSonFormName", form.getFormName());
            forms.add(jsonObj);
        }
        return forms;
    }
    
//	Admin Dropdown Json calling select Form Name with State Service
    @Override
    @Transactional
    public JSONArray selectDataWithStateAndForm(String combValue, HttpServletRequest req, HttpSession sn) {
    	LOGGER.debug("selectDataWithStateAndForm...Service"); 
    	String[] combName = combValue.split(" _ ");
    	sn.setAttribute("stateNameInSn", combName[0]);
	    sn.setAttribute("formNameInSn", combName[1]);
//	    ModelAndView mav = new ModelAndView();    	
//    		mav = stateFormsAttrModificationRedirection(req, sn);
    		   	
        JSONArray forms = new JSONArray();        
        return forms;
    }
    
//	Admin State Forms Attr Modification Redirection Service Implementation
	
	@Override
	@Transactional
	public ModelAndView stateFormsAttrModificationRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("stateFormsAttrModificationRedirection...Service");
		ModelAndView mav = new ModelAndView();		
		if(sn.getAttribute("adminUserNameInSn") != null){			
//			  State Forms Attributs Data from DB
			int formId = takeFormIdFromDB((String) sn.getAttribute("stateNameInSn"), (String) sn.getAttribute("formNameInSn"));
			
            List attributesInfoIdList = new ArrayList();
            List attributeFieldNameList = new ArrayList();
            
            List<Object> formFieldsAndValuesIds = (List<Object>) busFormsAttributesInfoDAOImpl.adminHomeStateFormsAttributesInfoList(formId);
            Iterator formFieldsAndValuesIdsIterator = formFieldsAndValuesIds.iterator();
            while (formFieldsAndValuesIdsIterator.hasNext()) {
                Object[] formFieldsAndValuesIdsObj = (Object[]) formFieldsAndValuesIdsIterator.next();

                String attrInfoIds = String.valueOf(formFieldsAndValuesIdsObj[0]);
                String attrFieldNames = String.valueOf(formFieldsAndValuesIdsObj[1]);
                
                attributesInfoIdList.add(attrInfoIds);
                attributeFieldNameList.add(attrFieldNames + " ");                
            }            
            mav = new ModelAndView("stateFormsAttrModification");
            mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
            mav.addObject("stateNamesList", sn.getAttribute("stateNamesListInSn"));
            
            sn.setAttribute("State_Forms_Attr_Info_ID_List", attributesInfoIdList);            
            sn.setAttribute("State_Forms_Attr_Field_Names_List", attributeFieldNameList);
            mav.addObject("stateName", sn.getAttribute("stateNameInSn"));
            mav.addObject("formName", sn.getAttribute("formNameInSn"));
		}
        return mav;
    }
	
//	Admin Additional Forms Attr Modification Redirection Service Implementation
	
	@Override
	@Transactional
	public ModelAndView additionalFormsAttrModificationRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("additionalFormsAttrModificationRedirection...Service");
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute("adminUserNameInSn") != null){			
//			  Additional Forms Attributs Data from DB 	
			int formId = takeFormIdFromDB((String) sn.getAttribute("stateNameInSn"), (String) sn.getAttribute("formNameInSn"));
			
            List attributesInfoIdList = new ArrayList();
            List attributeFieldNameList = new ArrayList();            
            
            List<Object> formFieldsAndValuesIds = (List<Object>) addFormsAttributesInfoDAOImpl.adminHomeAdditionalFormsAttributesInfoList(formId);
            Iterator formFieldsAndValuesIdsIterator = formFieldsAndValuesIds.iterator();
            while (formFieldsAndValuesIdsIterator.hasNext()) {
                Object[] formFieldsAndValuesIdsObj = (Object[]) formFieldsAndValuesIdsIterator.next();

                String attrInfoIds = String.valueOf(formFieldsAndValuesIdsObj[0]);
                String attrFieldNames = String.valueOf(formFieldsAndValuesIdsObj[1]);
                
                attributesInfoIdList.add(attrInfoIds);
                attributeFieldNameList.add(attrFieldNames + " ");                
            }
            
            mav = new ModelAndView("additionalFormsAttrModification");
            mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
            mav.addObject("stateNamesList", sn.getAttribute("stateNamesListInSn"));
            
            sn.setAttribute("Additional_Forms_Attr_Info_ID_List", attributesInfoIdList);            
            sn.setAttribute("Additional_Forms_Attr_Field_Names_List", attributeFieldNameList);
            mav.addObject("stateName", sn.getAttribute("stateNameInSn"));
            mav.addObject("formName", sn.getAttribute("formNameInSn"));
		}
        return mav;
    }
	
//	Admin State Tax Id Forms Attr Modification Redirection Service Implementation
	
	@Override
	@Transactional
	public ModelAndView stateTaxIdFormsAttrModificationRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("stateTaxIdFormsAttrModificationRedirection...Service");
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute("adminUserNameInSn") != null){
			int formId = takeFormIdFromDB((String) sn.getAttribute("stateNameInSn"), (String) sn.getAttribute("formNameInSn"));
			
//			  State Tax Id Forms Attributs Data from DB 			
            List attributesInfoIdList = new ArrayList();
            List attributeFieldNameList = new ArrayList();
            
            List<Object> formFieldsAndValuesIds = (List<Object>) stateTaxFormsAttributesInfoDAOImpl.adminHomeStateTaxFormsAttributesInfoList(formId);
            Iterator formFieldsAndValuesIdsIterator = formFieldsAndValuesIds.iterator();
            while (formFieldsAndValuesIdsIterator.hasNext()) {
                Object[] formFieldsAndValuesIdsObj = (Object[]) formFieldsAndValuesIdsIterator.next();

                String attrInfoIds = String.valueOf(formFieldsAndValuesIdsObj[0]);
                String attrFieldNames = String.valueOf(formFieldsAndValuesIdsObj[1]);
                
                attributesInfoIdList.add(attrInfoIds);
                attributeFieldNameList.add(attrFieldNames + " ");                
            }
            
            mav = new ModelAndView("stateTaxIdFormsAttrModification");
            mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
            mav.addObject("stateNamesList", sn.getAttribute("stateNamesListInSn"));
            
            sn.setAttribute("State_Tax_Id_Forms_Attr_Info_ID_List", attributesInfoIdList);            
            sn.setAttribute("State_Tax_Id_Forms_Attr_Field_Names_List", attributeFieldNameList);
            mav.addObject("stateName", sn.getAttribute("stateNameInSn"));
            mav.addObject("formName", sn.getAttribute("formNameInSn"));
		}
        return mav;
    }
	
//  State Forms JSon Attribute Field Name Updating In DB Service
    @Override
    @Transactional
    public JSONArray stateFormsAttrFieldNameUpdationInDB(String updatedAttrVal, HttpSession sn) {
        LOGGER.debug("stateFormsAttrFieldNameUpdationInDB...Service");
        
        String[] roleName = updatedAttrVal.split(" _ ");
        String attrValueId = roleName[0];
        String attrFieldName = roleName[1];
        
        int attrInfoId = Integer.parseInt(attrValueId);
        int formId = takeFormIdFromDB((String) sn.getAttribute("stateNameInSn"), (String) sn.getAttribute("formNameInSn"));
        
        if("Business Services".equals(sn.getAttribute("typeOfDocumentInSn"))){
        	BusinessFormsAttributesInfo stateFormsModel = busFormsAttributesInfoDAOImpl.stateFormAttrFieldNameUpdateInDB(attrInfoId, formId);        
        	stateFormsModel.setAttributeName(attrFieldName);
        	busFormsAttributesInfoDAOImpl.merge(stateFormsModel);
            
    	} else if("Additional Services".equals(sn.getAttribute("typeOfDocumentInSn"))){
    		AdditionalFormsAttributesInfo additionalFormsModel = addFormsAttributesInfoDAOImpl.additionalFormAttrFieldNameUpdateInDB(attrInfoId, formId);        
    		additionalFormsModel.setAttributeName(attrFieldName);
        	addFormsAttributesInfoDAOImpl.merge(additionalFormsModel);
    		
    	} else if("State Tax ID".equals(sn.getAttribute("typeOfDocumentInSn"))){
    		StateTaxFormsAttributesInfo stateTaxIdFormsModel = stateTaxFormsAttributesInfoDAOImpl.stateTaxIdFormAttrFieldNameUpdateInDB(attrInfoId, formId);        
    		stateTaxIdFormsModel.setAttributeName(attrFieldName);
        	stateTaxFormsAttributesInfoDAOImpl.merge(stateTaxIdFormsModel);
    		
    	} 
        
        
        String finalObj = attrFieldName + "&&" + attrInfoId;
        JSONArray forms = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("JSonObj", finalObj);
        forms.add(jsonObj);
        return forms;
    }
	
	//Form Id getting from DB
	@Override
	@Transactional
	public int takeFormIdFromDB(String stateName, String formName) {
		LOGGER.debug("takeFormIdFromDB...method");		
		List<Forms> stFormId = formsDAOImpl.stateFormsIdValueFromDB(stateName, formName);
		int formId = 0;
		for (Forms formsInfo : stFormId) {
		    formId = formsInfo.getFormId();
		}	
		return formId;
	}

	    

	

}
