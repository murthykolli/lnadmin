package com.legalnod.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PDFDocumentsInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "pdf_documents_info", schema = "ad")
public class PDFDocumentsInfo implements java.io.Serializable {
	
	// Fields

		private Integer pdfDocumentsInfoId;
		private String formName;
		private String stateName;
		private String pdfStatus;

		// Constructors

		/** default constructor */
		public PDFDocumentsInfo() {
		}

		// Property accessors
		@Id
		@Column(name = "pdf_documents_info_id", unique = true, nullable = false)
		public Integer getPdfDocumentsInfoId() {
			return pdfDocumentsInfoId;
		}

		public void setPdfDocumentsInfoId(Integer pdfDocumentsInfoId) {
			this.pdfDocumentsInfoId = pdfDocumentsInfoId;
		}

		@Column(name = "form_name", nullable = false, length = 100)
		public String getFormName() {
			return formName;
		}

		public void setFormName(String formName) {
			this.formName = formName;
		}

		@Column(name = "state_name", length = 20)
		public String getStateName() {
			return stateName;
		}

		public void setStateName(String stateName) {
			this.stateName = stateName;
		}

		@Column(name = "pdf_status", length = 30)
		public String getPdfStatus() {
			return pdfStatus;
		}

		public void setPdfStatus(String pdfStatus) {
			this.pdfStatus = pdfStatus;
		}
		
}
