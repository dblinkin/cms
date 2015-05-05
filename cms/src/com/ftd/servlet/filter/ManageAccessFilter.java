package com.ftd.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.ftd.i18n.I18nMgr;
import com.ftd.i18n.Resource;
import com.ftd.manage.ManagerSession;
import com.ftd.servlet.Context;
import com.ftd.system.SysMgr;
import com.ftd.util.StrUtil;

public class ManageAccessFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		ManagerSession managerSession = (ManagerSession) req.getSession()
				.getAttribute(ManagerSession.KEY);
		if (managerSession != null) {
			chain.doFilter(request, response);
		} else {
			String lang = StrUtil.parseStr((String) req.getAttribute("lang"),
					SysMgr.getInstance().getDefaultLang());

			String errStr = I18nMgr.getInstance().getMsg(lang,
					Resource.ERROR_CODE_PREFIX, "session.time.out");

			JSONObject result = new JSONObject();

			result.element(Context.RET_CODE, 0x101);
			result.element(Context.RET_MSG, errStr);

			resp.getWriter().write(result.toString());
			resp.getWriter().flush();
		}

	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
