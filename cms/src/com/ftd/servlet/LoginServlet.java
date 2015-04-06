package com.ftd.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ftd.manage.ManagerSession;
import com.ftd.system.SysMgr;
import com.ftd.system.user.User;
import com.ftd.system.user.UserDao;
import com.ftd.util.StrUtil;

import net.sf.json.JSONObject;

@WebServlet("/managerlogin.do")
public class LoginServlet extends HttpServlet {
	public static int DEFAULT_ERROR_CODE = 1 << 16;
	public static String DEFAULT_ERROR_MESSAGE = "处理失败";

	public static int CMD_NOT_FOUND_CODE = 1 << 17;
	public static String CMD_NOT_FOUND_MESSAGE = "无效的请求";

	public static int CMD_ERROR_URL_CODE = 1 << 18;
	public static String CMD_EOOR_URL_MESSAGE = "错误的URL";

	public static String CMD_PARAM_INVALID = "参数验证未通过";
	public static String CMD_SESSION_TIME_OUT = "session超时，请重新登录。";

	private static final long serialVersionUID = 8974092876129821086L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doService(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doService(request, response);
	}

	protected void doService(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		JSONObject result = new JSONObject();

		String opr = request.getParameter("opr");
		if (!StrUtil.isEmpty(opr)) {
			switch (opr.toUpperCase()) {
			case "LOGIN":
				String username = (String) request.getParameter("username");
				String password = (String) request.getParameter("password");

				UserDao dao = new UserDao(SysMgr.getInstance().getDbClient());
				if (!StrUtil.isEmpty(username) && !StrUtil.isEmpty(password)) {
					User u = dao.select(username, password);
					if (u != null) {
						ManagerSession session = new ManagerSession();
						request.getSession().setAttribute(ManagerSession.KEY,
								session);
						result.element(Context.RET_CODE,
								Context.DEFAULT_SUCCESS_CODE);
					}
				}

				break;
			case "LOGOUT":
				request.getSession().removeAttribute(ManagerSession.KEY);
				result.element(Context.RET_CODE, Context.DEFAULT_SUCCESS_CODE);
				break;

			default:
				break;
			}
		}

		// 写入结果
		PrintWriter out = null;
		try {
			out = response.getWriter();
			result.write(out);
			out.flush();
		} catch (Exception e) {
			throw e;
		} finally {
			if (out != null)
				out.close();
		}

	}

}
