package com.legalnod.daoimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.legalnod.model.CompletedStateFormsFileUploadSaving;

/**
 * A data access object (DAO) providing persistence and search support for
 * CompletedStateFormsFileUploadSaving entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.legalnod.model.CompletedStateFormsFileUploadSaving
 * @author MyEclipse Persistence Tools
 */

public class CompletedStateFormsFileUploadSavingDAOImpl extends HibernateDaoSupport {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CompletedStateFormsFileUploadSavingDAOImpl.class);
	
	public List findByStateFormsFileUploadRowChecking(String userName, String typeOfDocument, String formName, String stateName, String userChoice) {
		LOGGER.debug("finding CompletedStateFormsFileUploadSaving instance with property: ");		
		try {
			String queryString = "from CompletedStateFormsFileUploadSaving WHERE userName = '"+userName+"' and typeOfDocument = '"+typeOfDocument+"' and formName = '"+formName+"' and stateName = '"+stateName+"' and userChoice = '"+userChoice+"'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			LOGGER.error("find by property name failed", re);
			throw re;
		}
	}
	
//	Completed State Forms File Upload
	
	public List completedStateFormsUploadedDataSaving() {
		LOGGER.debug("finding CompletedStateFormsFileUploadSaving instance with property: ");		
		try {
			String queryString = "from CompletedStateFormsFileUploadSaving order by createdDate DESC nulls last";
			
			return getHibernateTemplate().find(queryString);			
		} catch (RuntimeException re) {
			LOGGER.error("find by property name failed", re);
			throw re;
		}
	}
	
	public void save(CompletedStateFormsFileUploadSaving transientInstance) {
		LOGGER.debug("saving CompletedStateFormsFileUploadSaving instance");
		try {
			getHibernateTemplate().save(transientInstance);
			LOGGER.debug("save successful");
		} catch (RuntimeException re) {
			LOGGER.error("save failed", re);
			throw re;
		}
	}

	public void delete(CompletedStateFormsFileUploadSaving persistentInstance) {
		LOGGER.debug("deleting CompletedStateFormsFileUploadSaving instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			LOGGER.debug("delete successful");
		} catch (RuntimeException re) {
			LOGGER.error("delete failed", re);
			throw re;
		}
	}

	public CompletedStateFormsFileUploadSaving findById(java.lang.Integer id) {
		LOGGER.debug("getting CompletedStateFormsFileUploadSaving instance with id: " + id);
		try {
			CompletedStateFormsFileUploadSaving instance = (CompletedStateFormsFileUploadSaving) getHibernateTemplate()
					.get("com.legalnod.model.CompletedStateFormsFileUploadSaving", id);
			return instance;
		} catch (RuntimeException re) {
			LOGGER.error("get failed", re);
			throw re;
		}
	}
	
	public List findAll() {
		LOGGER.debug("finding all CompletedStateFormsFileUploadSaving instances");
		try {
			String queryString = "from CompletedStateFormsFileUploadSaving";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			LOGGER.error("find all failed", re);
			throw re;
		}
	}

	public CompletedStateFormsFileUploadSaving merge(
			CompletedStateFormsFileUploadSaving detachedInstance) {
		LOGGER.debug("merging CompletedStateFormsFileUploadSaving instance");
		try {
			CompletedStateFormsFileUploadSaving result = (CompletedStateFormsFileUploadSaving) getHibernateTemplate()
					.merge(detachedInstance);
			LOGGER.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			LOGGER.error("merge failed", re);
			throw re;
		}
	}

}
