package com.legalnod.daoimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.legalnod.model.PDFDocumentsInfo;

public class PDFDocumentsInfoDAOImpl extends HibernateDaoSupport{
	private static final Logger LOGGER = LoggerFactory
			.getLogger(PDFDocumentsInfoDAOImpl.class);

	protected void initDao() {
		// do nothing
	}
	
//	Completed State PDF Document checking

public List compStateFormsPDFDocChecking(String stateName, String formName) {
	LOGGER.debug("finding PDFDocumentsInfo instance with property: ");		
	try {
		String queryString = "from PDFDocumentsInfo WHERE stateName = '"+stateName+"' and formName = '"+formName+"' and pdfStatus is not null ";			
		return getHibernateTemplate().find(queryString);			
	} catch (RuntimeException re) {
		LOGGER.error("find by property name failed", re);
		throw re;
	}
}
	
	public void save(PDFDocumentsInfo transientInstance) {
		LOGGER.debug("saving PDFDocumentsInfo instance");
		try {
			getHibernateTemplate().save(transientInstance);			
			LOGGER.debug("save successful");
		} catch (RuntimeException re) {
			LOGGER.error("save failed", re);
			throw re;
		}
	}
	
	public void delete(PDFDocumentsInfo persistentInstance) {
		LOGGER.debug("deleting PDFDocumentsInfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			LOGGER.debug("delete successful");
		} catch (RuntimeException re) {
			LOGGER.error("delete failed", re);
			throw re;
		}
	}
	
	public List findAll() {
		LOGGER.debug("finding all PDFDocumentsInfo instances");
		try {
			String queryString = "from PDFDocumentsInfo";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			LOGGER.error("find all failed", re);
			throw re;
		}
	}

	public PDFDocumentsInfo merge(
			PDFDocumentsInfo detachedInstance) {
		LOGGER.debug("merging PDFDocumentsInfo instance");
		try {
			PDFDocumentsInfo result = (PDFDocumentsInfo) getHibernateTemplate()
					.merge(detachedInstance);
			LOGGER.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			LOGGER.error("merge failed", re);
			throw re;
		}
	}
}
