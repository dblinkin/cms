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

		String url = request.getRequestURI();

		int index = url.lastIndexOf("/");
		String cmd = null;
		JSONObject json = new JSONObject();
		if (index > 0) {
			cmd = url.substring(index + 1, url.length());
			Handler handler = SysMgr.getInstance().getHandler(cmd);

			if (handler != null) {
				logger.info("received request : " + cmd);
				// 去除 .do = length = 3;
				cmd = cmd.substring(0, cmd.length() - 3);

				Context ctx = new Context(request, response, cmd);
				try {

					handler.handle(ctx);

					json.element(Context.RET_CODE, ctx.getRetCode());
					json.element(Context.RET_MSG, ctx.getRetMsg());
					json.putAll(ctx.getResult());
				} catch (FtdException fe) {
					json.element("retCode", fe.getErrorCode());
					json.element("retMsg", fe.getDesc());
					logger.error(ExceptionUtils.getStackTrace(fe));
				} catch (Exception e) {
					json.element("retCode", 0);
					json.element("retMsg", "->" + e.getClass().getName() + ":"
							+ e.getMessage());
					logger.error(ExceptionUtils.getStackTrace(e));
				}
			} else {
				json.element("retCode", -1);
				json.element("retMsg", -9);
				logger.error("cmd[" + cmd + "] not foud!");
			}
		} else {
			json.element("retCode", -1);
			json.element("retMsg", -9);
			logger.error("error url [" + url + "] !");
		}

		// 写入结果
		PrintWriter out = null;
		try {
			out = response.getWriter();
			json.write(out);
			out.flush();
		} catch (Exception e) {
			throw e;
		} finally {
			if (out != null)
				out.close();
		}

	}

}
