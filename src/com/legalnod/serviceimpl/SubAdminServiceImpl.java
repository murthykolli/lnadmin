package com.legalnod.serviceimpl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.legalnod.daoimpl.UsersInformationDAOImpl;
import com.legalnod.model.UsersInformation;
import com.legalnod.service.SubAdminService;

/**
 * @author MurthyK
 *
 */

public class SubAdminServiceImpl implements SubAdminService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SubAdminServiceImpl.class);	
	
    private String userUserName = "userName";
    private String usrPassword = "password";
    
    private String activeUsersCount = "activeUsersCount";
    private String inactiveUsersCount = "inactiveUsersCount";
    
    private String userNameInSn = "userNameInSn";
    private String adminTypeInSn = "adminTypeInSn";
    private String typeOfUserInSn = "typeOfUserInSn";
    
    private String adminFirstName = "adminFirstName";
    private String adminFirstNameInSn = "adminFirstNameInSn";
    private String adminUserNameInSn = "adminUserNameInSn";
    
    @Autowired
    private UsersInformationDAOImpl userInfoDAOImpl;
	
//	Registration Service Implementation methods
    
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
	
//	New Sub Admin Creation Service Implementation
	
	@Override
	@Transactional
	public ModelAndView newSubAdminCreation(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("newSubAdminCreation...Service");		
		ModelAndView mav = new ModelAndView("subAdminInfo");
		mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
        return mav;
    }
	
//	Sub Admin Data Saving in DB
	
	@Override
    @Transactional
    public ModelAndView subAdminDataSavingInDB(HttpServletRequest req, HttpSession sn) {
    	LOGGER.debug("subAdminDataSavingInDB...Service");
        String userName = req.getParameter(userUserName);
        String password = req.getParameter(usrPassword);
        
        List regDataVal = userInfoDAOImpl.findByUserName(userName);

        ModelAndView mav = new ModelAndView();
        if(sn.getAttribute(adminUserNameInSn) != null){
        if (!regDataVal.isEmpty()) {
            mav = new ModelAndView("subAdminInfo");
            mav.addObject("regErrorPage", " E-mail already exists!");
        } else {
            String encrypPassword = mD5Encrypt(password);
            String salt = "Random$SaltValue#WithSpecialCharacters12@$@4&#%^$*";
            String passwordSalt = mD5Encrypt(encrypPassword + salt);

            java.util.Date date = new java.util.Date();
            Timestamp currentDate = new Timestamp(date.getTime());

            UsersInformation rgModel = new UsersInformation();
            rgModel.setUserName(userName);
            rgModel.setPassword(encrypPassword);
            rgModel.setPasswordSalt(passwordSalt);
            rgModel.setTypeOfUser("A");
            rgModel.setAdminType("Sub Admin");
            rgModel.setCreatedDate(currentDate);
            userInfoDAOImpl.save(rgModel);

            mav = subAdminActiveUsersRedirection(req, sn);
        }
        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
        }
        return mav;
    }
	
//	Sub Admin Active Users Service Implementation
	
	@Override
	@Transactional
	public ModelAndView subAdminActiveUsersRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("subAdminActiveUsersRedirection...Service");		
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute(adminUserNameInSn) != null){
		List<UsersInformation> subAdminActiveUsersDataList = userInfoDAOImpl.subAdminActiveUsersDataDisplay();                
        List<UsersInformation> subAdminInactiveUsersDataList = userInfoDAOImpl.subAdminInactiveUsersDataDisplay();
        
        if (!subAdminActiveUsersDataList.isEmpty()) {
            List<UsersInformation> activeUsersSavingList = new ArrayList<UsersInformation>();
            for (UsersInformation activeUsersInfo : subAdminActiveUsersDataList) {
            	UsersInformation activeUsersInfoModel = new UsersInformation();
            	activeUsersInfoModel.setUserName(activeUsersInfo.getUserName());
            	activeUsersInfoModel.setAdminType(activeUsersInfo.getAdminType());
            	activeUsersInfoModel.setTypeOfUser(activeUsersInfo.getTypeOfUser());            	
            	activeUsersInfoModel.setCreatedDate(activeUsersInfo.getCreatedDate());                

                activeUsersSavingList.add(activeUsersInfoModel);
            }           
            mav = new ModelAndView("subAdminActiveUsers");
            mav.addObject("activeUsersSavingList", activeUsersSavingList);
            mav.addObject(activeUsersCount, subAdminActiveUsersDataList.size());
            mav.addObject(inactiveUsersCount, subAdminInactiveUsersDataList.size());            
        } else{
        	mav = newSubAdminCreation(req, sn);
        }
        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		}
        return mav;
    }
	
//	Sub Admin Inactive Users Service Implementation
	
	@Override
	@Transactional
	public ModelAndView subAdminInactiveUsersRedirection(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("subAdminInactiveUsersRedirection...Service");		
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute(adminUserNameInSn) != null){
		List<UsersInformation> subAdminActiveUsersDataList = userInfoDAOImpl.subAdminActiveUsersDataDisplay();                
        List<UsersInformation> subAdminInactiveUsersDataList = userInfoDAOImpl.subAdminInactiveUsersDataDisplay();
        
        if (!subAdminInactiveUsersDataList.isEmpty()) {
            List<UsersInformation> inactiveUsersSavingList = new ArrayList<UsersInformation>();
            for (UsersInformation inactiveUsersInfo : subAdminInactiveUsersDataList) {
            	UsersInformation inactiveUsersInfoModel = new UsersInformation();
            	inactiveUsersInfoModel.setUserName(inactiveUsersInfo.getUserName());
            	inactiveUsersInfoModel.setAdminType(inactiveUsersInfo.getAdminType());
            	inactiveUsersInfoModel.setTypeOfUser(inactiveUsersInfo.getTypeOfUser());            	
            	inactiveUsersInfoModel.setCreatedDate(inactiveUsersInfo.getCreatedDate());                

            	inactiveUsersSavingList.add(inactiveUsersInfoModel);
            }            
            mav = new ModelAndView("subAdminInactiveUsers");
            mav.addObject("inactiveUsersSavingList", inactiveUsersSavingList);
            mav.addObject(activeUsersCount, subAdminActiveUsersDataList.size());
            mav.addObject(inactiveUsersCount, subAdminInactiveUsersDataList.size());
        } else{
        	mav = newSubAdminCreation(req, sn);
        }
        mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		}
        return mav;
    }
	
//	Sub Admin Active Users Permissions Service Implementation
	
	@Override
	@Transactional
	public ModelAndView subAdminActiveUsersPermissions(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("subAdminActiveUsersPermissions...Service");		
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute(adminUserNameInSn) != null){
		String[] comValue = (req.getParameter("subAdminUserName")).split(",&, ");
    	sn.setAttribute(userNameInSn, comValue[0]);
    	sn.setAttribute(adminTypeInSn, comValue[1]);
    	sn.setAttribute(typeOfUserInSn, comValue[2]);
    	
    	if(sn.getAttribute(userNameInSn) != null) {
        UsersInformation subAdminPermModel = userInfoDAOImpl.subAdminUserPermissionsDataDisplay((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(typeOfUserInSn), (String) sn.getAttribute(adminTypeInSn));                
        subAdminPermModel.setTypeOfUser("I");        
        userInfoDAOImpl.merge(subAdminPermModel);                 
        	mav = subAdminInactiveUsersRedirection(req, sn);
    	} else{
    		mav = subAdminActiveUsersRedirection(req, sn);	
    	}
    	mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		}
        return mav;
    }
	
//	Sub Admin Inactive Users Permissions Service Implementation
	
	@Override
	@Transactional
	public ModelAndView subAdminInactiveUsersPermissions(HttpServletRequest req, HttpSession sn) {
		LOGGER.debug("subAdminInactiveUsersPermissions...Service");		
		ModelAndView mav = new ModelAndView();
		if(sn.getAttribute(adminUserNameInSn) != null){
		String[] comValue = (req.getParameter("subAdminUserName")).split(",&, ");
    	sn.setAttribute(userNameInSn, comValue[0]);
    	sn.setAttribute(adminTypeInSn, comValue[1]);
    	sn.setAttribute(typeOfUserInSn, comValue[2]);
    	
    	if(sn.getAttribute(userNameInSn) != null) {
        UsersInformation subAdminPermModel = userInfoDAOImpl.subAdminUserPermissionsDataDisplay((String) sn.getAttribute(userNameInSn), (String) sn.getAttribute(typeOfUserInSn), (String) sn.getAttribute(adminTypeInSn));                
        subAdminPermModel.setTypeOfUser("A");        
        userInfoDAOImpl.merge(subAdminPermModel);                 
        	mav = subAdminActiveUsersRedirection(req, sn); 
    	} else{
    		mav = subAdminInactiveUsersRedirection(req, sn);	
    	}
    	mav.addObject(adminFirstName, sn.getAttribute(adminFirstNameInSn));
		}
        return mav;
    }

}
