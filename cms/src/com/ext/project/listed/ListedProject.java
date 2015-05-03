package com.ext.project.listed;

import java.util.HashMap;
import java.util.Map;

import com.ftd.manage.release.AfterRelease;
import com.ftd.manage.release.Releasable;
import com.ftd.servlet.FtdException;
import com.ftd.util.StrUtil;

public class ListedProject implements Releasable {
	private int projectId;
	private String projectTitle;
	private String projectNum;
	private int projectType;
	private long listedStartTime;
	private long listedEndTime;

	private String assetTransfor;
	private String isDeal;
	private String district;
	private String transferArea;
	private String transferType;
	private long transferStartTime;
	private long transferEndTime;
	private String transferPrice;
	private String remark;

	private String releaseId;
	private String releaseUrl;

	@Override
	public String getReleaseId() {
		return this.releaseId;
	}

	@Override
	public Map<String, Object> getReleaseModel() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("listedProject", this);
		return model;
	}

	@Override
	public String getReleaseUrl() {
		return this.releaseUrl;
	}

	@Override
	public void afterRelease(AfterRelease ar) throws FtdException {
		if (this.releaseUrl != null && this.releaseUrl.equals(ar.releaseUrl)) {
			ListedProjectDao.updateRelease(this);
		}
	}

	// ----------getter setter---------------

	public String getProjectNum() {
		return projectNum;
	}

	public void setReleaseId(String releaseId) {
		this.releaseId = releaseId;
	}

	public void setReleaseUrl(String releaseUrl) {
		this.releaseUrl = releaseUrl;
	}

	public void setProjectNum(String projectNum) {
		this.projectNum = projectNum;
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

	public int getProjectType() {
		return projectType;
	}

	public void setProjectType(int projectType) {
		this.projectType = projectType;
	}

	public String getListedStartTime() {
		return StrUtil.date(listedStartTime);
	}

	public void setListedStartTime(long listedStartTime) {
		this.listedStartTime = listedStartTime;
	}

	public void setListedStartTime(String listedStartTime) {
		this.listedStartTime = StrUtil.parseDate(listedStartTime, 0);
	}

	public String getListedEndTime() {
		return StrUtil.date(listedEndTime);
	}

	public void setListedEndTime(long listedEndTime) {
		this.listedEndTime = listedEndTime;
	}

	public void setListedEndTime(String listedEndTime) {
		this.listedEndTime = StrUtil.parseDate(listedEndTime, 0);
	}

	public String getAssetTransfor() {
		return assetTransfor;
	}

	public void setAssetTransfor(String assetTransfor) {
		this.assetTransfor = assetTransfor;
	}

	public String getIsDeal() {
		return isDeal;
	}

	public void setIsDeal(String isDeal) {
		this.isDeal = isDeal;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getTransferArea() {
		return transferArea;
	}

	public void setTransferArea(String transferArea) {
		this.transferArea = transferArea;
	}

	public String getTransferType() {
		return transferType;
	}

	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}

	public String getTransferStartTime() {
		return StrUtil.date(transferStartTime);
	}

	public void setTransferStartTime(long transferStartTime) {
		this.transferStartTime = transferStartTime;
	}

	public void setTransferStartTime(String transferStartTime) {
		this.transferStartTime = StrUtil.parseDate(transferStartTime, 0);
	}

	public String getTransferEndTime() {
		return StrUtil.date(transferEndTime);
	}

	public void setTransferEndTime(long transferEndTime) {
		this.transferEndTime = transferEndTime;
	}

	public void setTransferEndTime(String transferEndTime) {
		this.transferEndTime = StrUtil.parseDate(transferEndTime, 0);
	}

	public String getTransferPrice() {
		return transferPrice;
	}

	public void setTransferPrice(String transferPrice) {
		this.transferPrice = transferPrice;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
