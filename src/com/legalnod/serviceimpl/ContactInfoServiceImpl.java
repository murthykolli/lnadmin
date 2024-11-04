package com.legalnod.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.legalnod.daoimpl.AllFederalFormsCheckoutPaymentAndUserContactSavingDAOImpl;
import com.legalnod.daoimpl.AllStateFormsCheckoutPaymentAndUserContactSavingDAOImpl;
import com.legalnod.daoimpl.UserPaymentTransactionInfoSavingDAOImpl;
import com.legalnod.daoimpl.UsersInformationDAOImpl;
import com.legalnod.model.AllFederalFormsCheckoutPaymentAndUserContactSaving;
import com.legalnod.model.AllStateFormsCheckoutPaymentAndUserContactSaving;
import com.legalnod.model.UserPaymentTransactionInfoSaving;
import com.legalnod.model.UsersInformation;
import com.legalnod.service.ContactInfoService;


/**
 * @author MurthyK
 *
 */

public class ContactInfoServiceImpl implements ContactInfoService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ContactInfoServiceImpl.class);
	
	private String userPrimaryContactInfoCount = "userPrimaryContactInfoCount";
    private String stateFormsPaymentContactInfoCount = "stateFormsPaymentContactInfoCount";
    private String federalFormsPaymentContactInfoCount = "federalFormsPaymentContactInfoCount";
    private String paymentTransactionInfoCount = "paymentTransactionInfoCount";
    
    private String adminFirstName = "adminFirstName";
    private String adminFirstNameInSn = "adminFirstNameInSn";
    private String adminUserNameInSn = "adminUserNameInSn";

	@Autowired
	private UsersInformationDAOImpl usersInformationDAOImpl;
	
	@Autowired
	private AllStateFormsCheckoutPaymentAndUserContactSavingDAOImpl allStateFormsPaymentAndUserContDAOImpl;
	
	@Autowired
	private AllFederalFormsCheckoutPaymentAndUserContactSavingDAOImpl allFederalFormsPaymentAndUserContDAOImpl;

	@Autowired
    private AdminHomeServiceImpl adminHomeServiceImpl;
	
	@Autowired
    private UserPaymentTransactionInfoSavingDAOImpl userPaymentTranSavingDAOImpl;
	
//	Contact Info Redirection Service Implementation
	
	@Override
	@Transactional
	public ModelAndView contactInfoRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("contactInfoRedirection...Service");		
		ModelAndView mav = new ModelAndView();		
		if(sn.getAttribute(adminUserNameInSn) != null){
		List<UsersInformation> usersInformationList = usersInformationDAOImpl.userPrimaryContactInfoDisplayInAdmin();		
        List<AllStateFormsCheckoutPaymentAndUserContactSaving> stateFormsPaymentContactList = allStateFormsPaymentAndUserContDAOImpl.stateFormsPaymentContactInfoDisplayInAdmin();        
        List<AllFederalFormsCheckoutPaymentAndUserContactSaving> federalFormsPaymentContactList = allFederalFormsPaymentAndUserContDAOImpl.federalFormsPaymentContactInfoDisplayInAdmin();
        List<UserPaymentTransactionInfoSaving> userPaymentTransactionContactList = userPaymentTranSavingDAOImpl.userPaymentTransactionContactInfoList();
       
        if (!usersInformationList.isEmpty()) {
            mav = primaryContactInfoRedirection(req, sn);
        }else if (!stateFormsPaymentContactList.isEmpty() && usersInformationList.isEmpty()) {
            mav = stateFormsPaymentContactRedirection(req, sn);
        } else if (!federalFormsPaymentContactList.isEmpty()) {
            mav = federalFormsPaymentContactRedirection(req, sn);
        } else if (!userPaymentTransactionContactList.isEmpty()) {
            mav = userPaymentTransactionContactRedirection(req, sn);
        } else{
        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);        	
        }
        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		}
        return mav;
    }
	
//	Primary Contact Info Redirection Service Implementation
	
	@Override
	@Transactional
	public ModelAndView primaryContactInfoRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("primaryContactInfoRedirection...Service");		
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute(adminUserNameInSn) != null){
        List<UsersInformation> usersPrimaryContactInfoList = usersInformationDAOImpl.userPrimaryContactInfoDisplayInAdmin();
        List<AllStateFormsCheckoutPaymentAndUserContactSaving> stateFormsPaymentContactList = allStateFormsPaymentAndUserContDAOImpl.stateFormsPaymentContactInfoDisplayInAdmin();        
        List<AllFederalFormsCheckoutPaymentAndUserContactSaving> federalFormsPaymentContactList = allFederalFormsPaymentAndUserContDAOImpl.federalFormsPaymentContactInfoDisplayInAdmin();
        List<UserPaymentTransactionInfoSaving> userPaymentTransactionContactList = userPaymentTranSavingDAOImpl.userPaymentTransactionContactInfoList();
        
        if (!usersPrimaryContactInfoList.isEmpty()) {
            List<UsersInformation> userPrimaryContactInfoSavingList = new ArrayList<UsersInformation>();
            int count = 1;
            for (UsersInformation primaryContactInfo : usersPrimaryContactInfoList) {
            	UsersInformation primaryContactInfoModel = new UsersInformation();
                primaryContactInfoModel.setUserName(primaryContactInfo.getUserName());                
                primaryContactInfoModel.setCreatedDate(primaryContactInfo.getCreatedDate());
                primaryContactInfoModel.setUserId(count);                
                userPrimaryContactInfoSavingList.add(primaryContactInfoModel);
                count++;
            }            
            mav = new ModelAndView("primaryContactInfo");
            mav.addObject("userPrimaryContactInfoSavingList", userPrimaryContactInfoSavingList);
            mav.addObject(userPrimaryContactInfoCount, usersPrimaryContactInfoList.size());
            mav.addObject(stateFormsPaymentContactInfoCount, stateFormsPaymentContactList.size());
            mav.addObject(federalFormsPaymentContactInfoCount, federalFormsPaymentContactList.size());
            mav.addObject(paymentTransactionInfoCount, userPaymentTransactionContactList.size());
            mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
        } else{
        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);        	
        }
		}
        return mav;
    }
	
//	State Forms Payment Contact Info Redirection Service Implementation
	
	@Override
	@Transactional
	public ModelAndView stateFormsPaymentContactRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("stateFormsPaymentContactRedirection...Service");		
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute(adminUserNameInSn) != null){
        List<UsersInformation> usersPrimaryContactInfoList = usersInformationDAOImpl.userPrimaryContactInfoDisplayInAdmin();
        List<AllStateFormsCheckoutPaymentAndUserContactSaving> stateFormsPaymentContactList = allStateFormsPaymentAndUserContDAOImpl.stateFormsPaymentContactInfoDisplayInAdmin();        
        List<AllFederalFormsCheckoutPaymentAndUserContactSaving> federalFormsPaymentContactList = allFederalFormsPaymentAndUserContDAOImpl.federalFormsPaymentContactInfoDisplayInAdmin();
        List<UserPaymentTransactionInfoSaving> userPaymentTransactionContactList = userPaymentTranSavingDAOImpl.userPaymentTransactionContactInfoList();
        
        if (!stateFormsPaymentContactList.isEmpty()) {
            List<AllStateFormsCheckoutPaymentAndUserContactSaving> stateFormsPaymentContactInfoList = new ArrayList<AllStateFormsCheckoutPaymentAndUserContactSaving>();            
            int count = 1;
            for (AllStateFormsCheckoutPaymentAndUserContactSaving stateFormsPaymentContInfoList : stateFormsPaymentContactList) {            	
            	AllStateFormsCheckoutPaymentAndUserContactSaving stateFormsPaymentContInfoModel = new AllStateFormsCheckoutPaymentAndUserContactSaving();
                stateFormsPaymentContInfoModel.setUserName(stateFormsPaymentContInfoList.getUserName());
                stateFormsPaymentContInfoModel.setUserPaymentTransactionInfoSavingId(stateFormsPaymentContInfoList.getUserPaymentTransactionInfoSavingId());
                stateFormsPaymentContInfoModel.setFormName(stateFormsPaymentContInfoList.getFormName());
                stateFormsPaymentContInfoModel.setStateName(stateFormsPaymentContInfoList.getStateName());
                stateFormsPaymentContInfoModel.setUserChoice(stateFormsPaymentContInfoList.getUserChoice());
                stateFormsPaymentContInfoModel.setTypeOfDocument(stateFormsPaymentContInfoList.getTypeOfDocument());
                
                stateFormsPaymentContInfoModel.setAmount(stateFormsPaymentContInfoList.getAmount());
                stateFormsPaymentContInfoModel.setInvoiceNum(stateFormsPaymentContInfoList.getInvoiceNum());
                stateFormsPaymentContInfoModel.setResponseReasonText(stateFormsPaymentContInfoList.getResponseReasonText());
                stateFormsPaymentContInfoModel.setFormFedLegalname(stateFormsPaymentContInfoList.getFormFedLegalname());
                stateFormsPaymentContInfoModel.setRegisteredAgentPrice(stateFormsPaymentContInfoList.getRegisteredAgentPrice());                
                stateFormsPaymentContInfoModel.setCreatedDate(stateFormsPaymentContInfoList.getCreatedDate());
                stateFormsPaymentContInfoModel.setAllStateFormsCheckoutPaymentAndUserContactSavingId(count);

                stateFormsPaymentContactInfoList.add(stateFormsPaymentContInfoModel);               
                count++;
            }
            mav = new ModelAndView("stateFormsPaymentContact");
            mav.addObject("stateFormsPaymentContactInfoList", stateFormsPaymentContactInfoList);
            
            mav.addObject(userPrimaryContactInfoCount, usersPrimaryContactInfoList.size());
            mav.addObject(stateFormsPaymentContactInfoCount, stateFormsPaymentContactList.size());
            mav.addObject(federalFormsPaymentContactInfoCount, federalFormsPaymentContactList.size());
            mav.addObject(paymentTransactionInfoCount, userPaymentTransactionContactList.size());
            mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
        } else{
        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);        	
        }
		}
        return mav;
    }
		
//	Federal Forms Payment Contact Info Redirection Service Implementation
	
	@Override
	@Transactional
	public ModelAndView federalFormsPaymentContactRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("federalFormsPaymentContactRedirection...Service");
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute(adminUserNameInSn) != null){
        List<UsersInformation> usersPrimaryContactInfoList = usersInformationDAOImpl.userPrimaryContactInfoDisplayInAdmin();
        List<AllStateFormsCheckoutPaymentAndUserContactSaving> stateFormsPaymentContactList = allStateFormsPaymentAndUserContDAOImpl.stateFormsPaymentContactInfoDisplayInAdmin();        
        List<AllFederalFormsCheckoutPaymentAndUserContactSaving> federalFormsPaymentContactList = allFederalFormsPaymentAndUserContDAOImpl.federalFormsPaymentContactInfoDisplayInAdmin();
        List<UserPaymentTransactionInfoSaving> userPaymentTransactionContactList = userPaymentTranSavingDAOImpl.userPaymentTransactionContactInfoList();
        
        if (!federalFormsPaymentContactList.isEmpty()) {
            List<AllFederalFormsCheckoutPaymentAndUserContactSaving> federalFormsPaymentContactInfoList = new ArrayList<AllFederalFormsCheckoutPaymentAndUserContactSaving>();
            int count = 1;
            for (AllFederalFormsCheckoutPaymentAndUserContactSaving federalFormsPaymentContInfoList : federalFormsPaymentContactList) {
            	AllFederalFormsCheckoutPaymentAndUserContactSaving federalFormsPaymentContInfoModel = new AllFederalFormsCheckoutPaymentAndUserContactSaving();
            	federalFormsPaymentContInfoModel.setUserPaymentTransactionInfoSavingId(federalFormsPaymentContInfoList.getUserPaymentTransactionInfoSavingId());
                federalFormsPaymentContInfoModel.setUserName(federalFormsPaymentContInfoList.getUserName());
                federalFormsPaymentContInfoModel.setLegalName(federalFormsPaymentContInfoList.getLegalName());
                federalFormsPaymentContInfoModel.setFormStatus(federalFormsPaymentContInfoList.getFormStatus());
                federalFormsPaymentContInfoModel.setAmount(federalFormsPaymentContInfoList.getAmount());
                federalFormsPaymentContInfoModel.setInvoiceNum(federalFormsPaymentContInfoList.getInvoiceNum());
                federalFormsPaymentContInfoModel.setResponseReasonText(federalFormsPaymentContInfoList.getResponseReasonText());                
                federalFormsPaymentContInfoModel.setCreatedDate(federalFormsPaymentContInfoList.getCreatedDate());
                federalFormsPaymentContInfoModel.setAllFederalFormsCheckoutPaymentAndUserContactSavingId(count);

                federalFormsPaymentContactInfoList.add(federalFormsPaymentContInfoModel);
                count++;
            }           
            mav = new ModelAndView("federalFormsPaymentContact");
            mav.addObject("federalFormsPaymentContactInfoList", federalFormsPaymentContactInfoList);
            mav.addObject(userPrimaryContactInfoCount, usersPrimaryContactInfoList.size());
            mav.addObject(stateFormsPaymentContactInfoCount, stateFormsPaymentContactList.size());
            mav.addObject(federalFormsPaymentContactInfoCount, federalFormsPaymentContactList.size());
            mav.addObject(paymentTransactionInfoCount, userPaymentTransactionContactList.size());
            mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
        } else{
        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);        	
        }
		}
        return mav;
    }
	
//	User Payment Transaction Contact Info Redirection
	@Override
	@Transactional
	public ModelAndView userPaymentTransactionContactRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("userPaymentTransactionContactRedirection...Service");		
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute(adminUserNameInSn) != null){
        List<UsersInformation> usersPrimaryContactInfoList = usersInformationDAOImpl.userPrimaryContactInfoDisplayInAdmin();
        List<AllStateFormsCheckoutPaymentAndUserContactSaving> stateFormsPaymentContactList = allStateFormsPaymentAndUserContDAOImpl.stateFormsPaymentContactInfoDisplayInAdmin();        
        List<AllFederalFormsCheckoutPaymentAndUserContactSaving> federalFormsPaymentContactList = allFederalFormsPaymentAndUserContDAOImpl.federalFormsPaymentContactInfoDisplayInAdmin();
        List<UserPaymentTransactionInfoSaving> userPaymentTransactionContactList = userPaymentTranSavingDAOImpl.userPaymentTransactionContactInfoList();
        
        if (!userPaymentTransactionContactList.isEmpty()) {
            List<UserPaymentTransactionInfoSaving> userPaymentTransactionContactInfoList = new ArrayList<UserPaymentTransactionInfoSaving>();          
            
            for (UserPaymentTransactionInfoSaving userPaymentTranContInfoList : userPaymentTransactionContactList) {            	
            	UserPaymentTransactionInfoSaving userPaymentTransactionContInfoModel = new UserPaymentTransactionInfoSaving();
            	userPaymentTransactionContInfoModel.setUserName(userPaymentTranContInfoList.getUserName());
            	userPaymentTransactionContInfoModel.setUserPaymentTransactionInfoSavingId(userPaymentTranContInfoList.getUserPaymentTransactionInfoSavingId());            	
            	userPaymentTransactionContInfoModel.setCardNumber(userPaymentTranContInfoList.getCardNumber());
            	userPaymentTransactionContInfoModel.setTransactionNumber(userPaymentTranContInfoList.getTransactionNumber());
            	userPaymentTransactionContInfoModel.setCardExpiryDate(userPaymentTranContInfoList.getCardExpiryDate());
            	userPaymentTransactionContInfoModel.setAmount(userPaymentTranContInfoList.getAmount());
            	userPaymentTransactionContInfoModel.setOrderStatus(userPaymentTranContInfoList.getOrderStatus());
            	userPaymentTransactionContInfoModel.setAuthorizationCode(userPaymentTranContInfoList.getAuthorizationCode());
            	
            	userPaymentTransactionContInfoModel.setBillingFirstName(userPaymentTranContInfoList.getBillingFirstName());
            	userPaymentTransactionContInfoModel.setBillingLastName(userPaymentTranContInfoList.getBillingLastName());
            	userPaymentTransactionContInfoModel.setBillingAddress(userPaymentTranContInfoList.getBillingAddress());
            	userPaymentTransactionContInfoModel.setBillingCity(userPaymentTranContInfoList.getBillingCity());
            	userPaymentTransactionContInfoModel.setBillingStateName(userPaymentTranContInfoList.getBillingStateName());
            	userPaymentTransactionContInfoModel.setBillingZip(userPaymentTranContInfoList.getBillingZip());
            	userPaymentTransactionContInfoModel.setShipingFirstName(userPaymentTranContInfoList.getShipingFirstName());
            	userPaymentTransactionContInfoModel.setShipingLastName(userPaymentTranContInfoList.getShipingLastName());
            	userPaymentTransactionContInfoModel.setShipingAddress(userPaymentTranContInfoList.getShipingAddress());
            	userPaymentTransactionContInfoModel.setShipingCity(userPaymentTranContInfoList.getShipingCity());
            	userPaymentTransactionContInfoModel.setShipingStateName(userPaymentTranContInfoList.getShipingStateName());
            	userPaymentTransactionContInfoModel.setShipingZip(userPaymentTranContInfoList.getShipingZip());
            	userPaymentTransactionContInfoModel.setCreatedDate(userPaymentTranContInfoList.getCreatedDate());
            	
                userPaymentTransactionContactInfoList.add(userPaymentTransactionContInfoModel);
            }
            mav = new ModelAndView("paymentTransactionContact");
            mav.addObject("userPaymentTransactionContactInfoList", userPaymentTransactionContactInfoList);
            
            mav.addObject(userPrimaryContactInfoCount, usersPrimaryContactInfoList.size());
            mav.addObject(stateFormsPaymentContactInfoCount, stateFormsPaymentContactList.size());
            mav.addObject(federalFormsPaymentContactInfoCount, federalFormsPaymentContactList.size());
            mav.addObject(paymentTransactionInfoCount, userPaymentTransactionContactList.size());            
            mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
        } else{
        	mav = adminHomeServiceImpl.adminHomeRedirection(req, sn);        	
        }
		}
        return mav;
    }
	
}
