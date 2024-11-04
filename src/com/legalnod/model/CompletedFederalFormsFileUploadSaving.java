package com.legalnod.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * UsersInformation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "completed_federal_forms_file_upload_saving", schema = "ad")
public class CompletedFederalFormsFileUploadSaving {
	// Fields

		private Integer completedFederalFormsFileUploadSavingId;
		private String userName;
		private String typeOfDocument;		
		private String documentName;
		private String amount;
		private int orderNumber;
		private String subject;
		private String comments;
		private String fileUploadData;
		private String status;
		private Timestamp createdDate;
		
		// Constructors

		/** default constructor */
		public CompletedFederalFormsFileUploadSaving() {
		}

		// Property accessors
		@Id	
		@SequenceGenerator(name = "completed_federal_forms_file_upload_saving_id", sequenceName = "ad.completed_federal_forms_file_upload_saving_id_seq", allocationSize = 1)
		@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "completed_federal_forms_file_upload_saving_id")	
		@Column(name = "completed_federal_forms_file_upload_saving_id", unique = true, nullable = false)
		public Integer getCompletedFederalFormsFileUploadSavingId() {
			return completedFederalFormsFileUploadSavingId;
		}

		public void setCompletedFederalFormsFileUploadSavingId(
				Integer completedFederalFormsFileUploadSavingId) {
			this.completedFederalFormsFileUploadSavingId = completedFederalFormsFileUploadSavingId;
		}

		@Column(name = "user_name", nullable = false, length = 60)
		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		@Column(name = "type_of_document", length = 100)
		public String getTypeOfDocument() {
			return typeOfDocument;
		}

		public void setTypeOfDocument(String typeOfDocument) {
			this.typeOfDocument = typeOfDocument;
		}
		
		@Column(name = "document_name")
		public String getDocumentName() {
			return documentName;
		}

		public void setDocumentName(String documentName) {
			this.documentName = documentName;
		}

		@Column(name = "amount", length = 30)
		public String getAmount() {
			return amount;
		}

		public void setAmount(String amount) {
			this.amount = amount;
		}

		@Column(name = "order_number")
		public int getOrderNumber() {
			return orderNumber;
		}

		public void setOrderNumber(int orderNumber) {
			this.orderNumber = orderNumber;
		}

		@Column(name = "subject", length = 100)
		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

		@Column(name = "comments")
		public String getComments() {
			return comments;
		}

		public void setComments(String comments) {
			this.comments = comments;
		}

		@Column(name = "file_upload_data")
		public String getFileUploadData() {
			return fileUploadData;
		}

		public void setFileUploadData(String fileUploadData) {
			this.fileUploadData = fileUploadData;
		}

		@Column(name = "status", length = 30)
		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		@Column(name = "created_date", nullable = false)
		public Timestamp getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(Timestamp createdDate) {
			this.createdDate = createdDate;
		}


	}