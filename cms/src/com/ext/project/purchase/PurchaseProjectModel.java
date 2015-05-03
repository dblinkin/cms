package com.ext.project.purchase;

import java.util.HashMap;
import java.util.Map;

import com.ftd.manage.release.model.ModelProvider;
import com.ftd.manage.release.model.ReleaseModel;
import com.ftd.servlet.FtdException;

public class PurchaseProjectModel implements ModelProvider {

	@Override
	public void setModelId(int modelId) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getModelId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, Object> getModel(int channelId, int articleId)
			throws FtdException {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("pchsprojects", PurchaseProjectDao.selectAll());
		return model;
	}

	@Override
	public void afterRelease(ReleaseModel rm) throws FtdException {
		// TODO Auto-generated method stub

	}

}
