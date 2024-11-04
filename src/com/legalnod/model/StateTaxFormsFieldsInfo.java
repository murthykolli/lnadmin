package com.legalnod.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * StateTaxFormsFieldsInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "state_tax_forms_fields_info", schema = "bs")
public class StateTaxFormsFieldsInfo implements java.io.Serializable {

	// Fields

	private Integer stateTaxFormsFieldsInfoId;
	private String attributeType;
	private String attributeValue;
	private Date loadedDate;
	private String loadedBy;
	private Date modifiedDate;
	private String modifiedBy;

	// Constructors

	/** default constructor */
	public StateTaxFormsFieldsInfo() {
	}

	/** minimal constructor */
	public StateTaxFormsFieldsInfo(Integer stateTaxFormsFieldsInfoId) {
		this.stateTaxFormsFieldsInfoId = stateTaxFormsFieldsInfoId;
	}

	/** full constructor */
	public StateTaxFormsFieldsInfo(Integer stateTaxFormsFieldsInfoId,
			String attributeType, String attributeValue, Date loadedDate,
			String loadedBy, Date modifiedDate, String modifiedBy) {
		this.stateTaxFormsFieldsInfoId = stateTaxFormsFieldsInfoId;
		this.attributeType = attributeType;
		this.attributeValue = attributeValue;
		this.loadedDate = loadedDate;
		this.loadedBy = loadedBy;
		this.modifiedDate = modifiedDate;
		this.modifiedBy = modifiedBy;
	}

	// Property accessors
	@Id
	@Column(name = "state_tax_forms_fields_info_id", unique = true, nullable = false)
	public Integer getStateTaxFormsFieldsInfoId() {
		return this.stateTaxFormsFieldsInfoId;
	}

	public void setStateTaxFormsFieldsInfoId(Integer stateTaxFormsFieldsInfoId) {
		this.stateTaxFormsFieldsInfoId = stateTaxFormsFieldsInfoId;
	}

	@Column(name = "attribute_type", length = 20)
	public String getAttributeType() {
		return this.attributeType;
	}

	public void setAttributeType(String attributeType) {
		this.attributeType = attributeType;
	}

	@Column(name = "attribute_value")
	public String getAttributeValue() {
		return this.attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "loaded_date", length = 13)
	public Date getLoadedDate() {
		return this.loadedDate;
	}

	public void setLoadedDate(Date loadedDate) {
		this.loadedDate = loadedDate;
	}

	@Column(name = "loaded_by", length = 20)
	public String getLoadedBy() {
		return this.loadedBy;
	}

	public void setLoadedBy(String loadedBy) {
		this.loadedBy = loadedBy;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "modified_date", length = 13)
	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Column(name = "modified_by", length = 20)
	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}