package com.ftd.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.tomcat.util.http.fileupload.FileItem;

import com.ftd.system.SysMgr;
import com.ftd.util.StrUtil;

public class Context {
	public final HttpServletRequest request;
	public final HttpServletResponse response;
	public final String cmd;
	public final String lang;
	public Map<String, Object> paramMap;
	public Connection connection;

	public static final int DEFAULT_SUCCESS_CODE = 0;

	public static final String RET_CODE = "retCode";
	public static final String RET_MSG = "retMsg";

	private OPR opr = OPR.NULL;

	private JSONObject resultMap = new JSONObject();

	private int retCode = DEFAULT_SUCCESS_CODE;
	private String retMsg;
	
	private boolean redirect;

	public Context(HttpServletRequest request, HttpServletResponse response,
			String cmd) {
		super();
		this.request = request;
		this.response = response;
		this.cmd = cmd;
		this.paramMap = getParameters();
		this.lang = StrUtil.parseStr(request.getParameter("lang"), SysMgr
				.getInstance().getDefaultLang());
		setOpr();
	}

	// 上传文件使用
	public Context(HttpServletRequest request, HttpServletResponse response,
			List<FileItem> items) {
		super();
		this.request = request;
		this.response = response;
		this.paramMap = getMultipartParameters(items);
		this.cmd = (String) paramMap.get("cmd");
		this.lang = StrUtil.parseStr(request.getParameter("lang"), SysMgr
				.getInstance().getDefaultLang());
		setOpr();
	}

	public void setTranction() throws SQLException {
		this.connection = SysMgr.getInstance().getDbClient().getConnection();
	}

	private Map<String, Object> getParameters() {
		Map<String, Object> paraMap = new HashMap<>();
		Enumeration<String> pas = request.getParameterNames();
		while (pas.hasMoreElements()) {
			String par = pas.nextElement();
			paraMap.put(par, request.getParameter(par));
		}
		return paraMap;
	}

	private Map<String, Object> getMultipartParameters(List<FileItem> items) {
		Map<String, Object> paraMap = new HashMap<>();
		Enumeration<String> pas = request.getParameterNames();
		while (pas.hasMoreElements()) {
			String par = pas.nextElement();
			paraMap.put(par, request.getParameter(par));
		}

		for (FileItem item : items) {
			if (item.isFormField()) {
				String value = "";
				// value = item.getString() 会乱码
				try {
					InputStream is = item.getInputStream();
					BufferedReader br = new BufferedReader(
							new InputStreamReader(is));
					StringBuilder sb = new StringBuilder();
					String line;
					while ((line = br.readLine()) != null) {
						sb.append(line);
					}
					value = sb.toString();
				} catch (IOException e) {
					e.printStackTrace();
				}

				paraMap.put(item.getFieldName(), value);
			}
		}

		return paraMap;
	}

	private void setOpr() {
		String oprStr = (String) paramMap.get("opr");
		if (oprStr != null && oprStr.trim().length() != 0) {
			opr = OPR.valueOf(oprStr.toUpperCase());
		}
	}

	public void putResult(String key, Object resultObj) {
		resultMap.put(key, resultObj);
	}

	public void putResult(Map<String, Object> map) {
		resultMap.putAll(map);
	}

	public Object getResult(String key) {
		return resultMap.get(key);
	}

	public JSONObject getResult() {
		return resultMap;
	}

	public int getRetCode() {
		return retCode;
	}

	public void setRetCode(int retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
	
	

	public boolean isRedirect() {
		return redirect;
	}

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}

	public OPR getOpr() {
		return opr;
	}

	public void setOpr(OPR opr) {
		this.opr = opr;
	}

	public enum OPR {
		// 0
		NULL,
		// 1查询
		QUERY,
		// 2新增
		INSERT,
		// 3删除
		DELETE,
		// 4更新
		UPDATE,
		// 5条件查询
		CONDITION_QUERY,
		// 启动任务
		TASK_START,
		// 取消任务
		TASK_CANCLE,
	}

}
