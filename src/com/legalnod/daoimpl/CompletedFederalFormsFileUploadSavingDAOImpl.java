package com.legalnod.daoimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.legalnod.model.CompletedFederalFormsFileUploadSaving;

/**
 * A data access object (DAO) providing persistence and search support for
 * CompletedFederalFormsFileUploadSaving entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.legalnod.model.CompletedFederalFormsFileUploadSaving
 * @author MyEclipse Persistence Tools
 */


public class CompletedFederalFormsFileUploadSavingDAOImpl extends HibernateDaoSupport {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CompletedFederalFormsFileUploadSavingDAOImpl.class);
	
	public List findByFederalFormsFileUploadRowChecking(String userName, String typeOfDocument, String userChoice) {
		LOGGER.debug("finding CompletedFederalFormsFileUploadSaving instance with property: ");		
		try {
			String queryString = "from CompletedFederalFormsFileUploadSaving WHERE userName = '"+userName+"' and typeOfDocument = '"+typeOfDocument+"' and documentName = '"+userChoice+"'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			LOGGER.error("find by property name failed", re);
			throw re;
		}
	}
	
//	Completed Federal Forms File Upload
	
	public List completedFederalFormsUploadedDataSaving() {
		LOGGER.debug("finding CompletedFederalFormsFileUploadSaving instance with property: ");		
		try {
			String queryString = "from CompletedFederalFormsFileUploadSaving order by createdDate DESC nulls last";
			
			return getHibernateTemplate().find(queryString);			
		} catch (RuntimeException re) {
			LOGGER.error("find by property name failed", re);
			throw re;
		}
	}
	
	public void save(CompletedFederalFormsFileUploadSaving transientInstance) {
		LOGGER.debug("saving CompletedFederalFormsFileUploadSaving instance");
		try {
			getHibernateTemplate().save(transientInstance);
			LOGGER.debug("save successful");
		} catch (RuntimeException re) {
			LOGGER.error("save failed", re);
			throw re;
		}
	}

	public void delete(CompletedFederalFormsFileUploadSaving persistentInstance) {
		LOGGER.debug("deleting CompletedFederalFormsFileUploadSaving instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			LOGGER.debug("delete successful");
		} catch (RuntimeException re) {
			LOGGER.error("delete failed", re);
			throw re;
		}
	}

	public CompletedFederalFormsFileUploadSaving findById(java.lang.Integer id) {
		LOGGER.debug("getting CompletedFederalFormsFileUploadSaving instance with id: " + id);
		try {
			CompletedFederalFormsFileUploadSaving instance = (CompletedFederalFormsFileUploadSaving) getHibernateTemplate()
					.get("com.legalnod.model.CompletedFederalFormsFileUploadSaving", id);
			return instance;
		} catch (RuntimeException re) {
			LOGGER.error("get failed", re);
			throw re;
		}
	}
	
	public List findAll() {
		LOGGER.debug("finding all CompletedFederalFormsFileUploadSaving instances");
		try {
			String queryString = "from CompletedFederalFormsFileUploadSaving";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			LOGGER.error("find all failed", re);
			throw re;
		}
	}

	public CompletedFederalFormsFileUploadSaving merge(
			CompletedFederalFormsFileUploadSaving detachedInstance) {
		LOGGER.debug("merging CompletedFederalFormsFileUploadSaving instance");
		try {
			CompletedFederalFormsFileUploadSaving result = (CompletedFederalFormsFileUploadSaving) getHibernateTemplate()
					.merge(detachedInstance);
			LOGGER.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			LOGGER.error("merge failed", re);
			throw re;
		}
	}

}
