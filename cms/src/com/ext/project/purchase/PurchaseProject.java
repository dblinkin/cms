package com.ext.project.purchase;

import java.util.HashMap;
import java.util.Map;

import com.ftd.manage.release.AfterRelease;
import com.ftd.manage.release.Releasable;
import com.ftd.servlet.FtdException;
import com.ftd.util.StrUtil;

public class PurchaseProject implements Releasable {
	private int projectId;
	private String projectTitle;
	private String projectSrc;
	private long createTime = System.currentTimeMillis();

	private String projectType;
	private String intentionDistrict;
	private String purpose;
	private int intentionMoney;
	private String intentionArea;
	private int intentionYear;
	private String remark;

	private String demandName;
	private String demandAddress;
	private String contractName;
	private String contractPhone;
	private String contractMail;
	private String contractFax;

	private String releaseId;
	private String releaseUrl;

	@Override
	public String getReleaseId() {
		return this.releaseId;
	}

	@Override
	public Map<String, Object> getReleaseModel() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("pchsProject", this);
		return model;
	}

	@Override
	public String getReleaseUrl() {
		return this.releaseUrl;
	}

	@Override
	public void afterRelease(AfterRelease ar) throws FtdException {
		if (this.releaseUrl == null || !this.releaseUrl.equals(ar.releaseUrl)) {
			this.releaseUrl = ar.releaseUrl;
			PurchaseProjectDao.updateRelease(this);
		}

	}

	// ------------getter setter--------------

	public void setReleaseUrl(String releaseUrl) {
		this.releaseUrl = releaseUrl;

	}

	public void setReleaseId(String releaseId) {
		this.releaseId = releaseId;
	}

	public String getCreateTime() {
		return StrUtil.datetime(createTime);
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = StrUtil.parseDatetime(createTime,
				System.currentTimeMillis());
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public String getProjectSrc() {
		return projectSrc;
	}

	public void setProjectSrc(String projectSrc) {
		this.projectSrc = projectSrc;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getIntentionArea() {
		return intentionArea;
	}

	public void setIntentionArea(String intentionArea) {
		this.intentionArea = intentionArea;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getIntentionDistrict() {
		return intentionDistrict;
	}

	public void setIntentionDistrict(String intentionDistrict) {
		this.intentionDistrict = intentionDistrict;
	}

	public int getIntentionMoney() {
		return intentionMoney;
	}

	public void setIntentionMoney(int intentionMoney) {
		this.intentionMoney = intentionMoney;
	}

	public int getIntentionYear() {
		return intentionYear;
	}

	public void setIntentionYear(int intentionYear) {
		this.intentionYear = intentionYear;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDemandName() {
		return demandName;
	}

	public void setDemandName(String demandName) {
		this.demandName = demandName;
	}

	public String getDemandAddress() {
		return demandAddress;
	}

	public void setDemandAddress(String demandAddress) {
		this.demandAddress = demandAddress;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getContractPhone() {
		return contractPhone;
	}

	public void setContractPhone(String contractPhone) {
		this.contractPhone = contractPhone;
	}

	public String getContractMail() {
		return contractMail;
	}

	public void setContractMail(String contractMail) {
		this.contractMail = contractMail;
	}

	public String getContractFax() {
		return contractFax;
	}

	public void setContractFax(String contractFax) {
		this.contractFax = contractFax;
	}

}
