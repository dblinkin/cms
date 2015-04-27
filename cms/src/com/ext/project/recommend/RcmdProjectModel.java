package com.ext.project.recommend;

import java.util.HashMap;
import java.util.Map;

import com.ftd.manage.release.model.ModelProvider;
import com.ftd.servlet.FtdException;

public class RcmdProjectModel implements ModelProvider {

	@Override
	public boolean isCached() {
		return false;
	}

	@Override
	public void setModel(int articleId, int... channels) {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, Object> getModel(int articleId, int... channels)
			throws FtdException {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("rcmdprojects", RcmdProjectDao.selectAll());
		return model;
	}

}
