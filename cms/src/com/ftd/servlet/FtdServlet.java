package com.ftd.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ftd.i18n.I18nMgr;
import com.ftd.i18n.Resource;
import com.ftd.system.SysMgr;

@WebServlet("*.do")
public class FtdServlet extends HttpServlet {

	private static final long serialVersionUID = 8974092876129821086L;

	private static Logger logger = LoggerFactory.getLogger(FtdServlet.class);

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

		String lang = request.getParameter("lang");
		if (lang == null) {
			lang = SysMgr.getInstance().getDefaultLang();
		}

		String url = request.getRequestURI();

		int index = url.lastIndexOf("/");
		String cmd = null;

		JSONObject result = new JSONObject();
		do {
			if (index <= 0) {
				result.element(Context.RET_CODE, 0x1000);
				String errFmt = I18nMgr.getInstance().getMsg(lang,
						Resource.ERROR_CODE_PREFIX, "request.url.error");
				result.element(Context.RET_MSG, String.format(errFmt, url));
				logger.error("error request url [{}] !", url);
				break;
			}

			cmd = url.substring(index + 1, url.length());
			Handler handler = SysMgr.getInstance().getHandler(cmd);
			if (handler == null) {
				result.element(Context.RET_CODE, 0x1001);
				String errFmt = I18nMgr.getInstance().getMsg(lang,
						Resource.ERROR_CODE_PREFIX, "handler.not.found");
				result.element(Context.RET_MSG, String.format(errFmt, cmd));
				logger.error("handler[{}] not found", cmd);
				break;
			}

			logger.info("received request[{}]", cmd);
			// 去除 .do = length = 3;
			cmd = cmd.substring(0, cmd.length() - 3);
			Context ctx = new Context(request, response, cmd);

			try {
				handler.handle(ctx);
			} catch (FtdException fe) {
				result.element(Context.RET_CODE, 0x1002);
				String errFmt = I18nMgr.getInstance().getMsg(lang,
						Resource.ERROR_CODE_PREFIX, fe.getErrorCodeId());
				result.element(Context.RET_MSG,
						String.format(errFmt, fe.getArgs()));
				if (fe.getCause() != null)
					logger.error(ExceptionUtils.getStackTrace(fe.getCause()));
				else
					logger.error(ExceptionUtils.getStackTrace(fe));
				break;
			} catch (Exception e) {
				result.element(Context.RET_CODE, 0x1003);
				String errFmt = I18nMgr.getInstance().getMsg(lang,
						Resource.ERROR_CODE_PREFIX, "server.internal.error");
				result.element(Context.RET_MSG,
						String.format(errFmt, e.getMessage()));

				logger.error(ExceptionUtils.getStackTrace(e));
				break;
			}

			ctx.putResult(Context.RET_CODE, Context.DEFAULT_SUCCESS_CODE);
			result.putAll(ctx.getResult());
		} while (false);

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
