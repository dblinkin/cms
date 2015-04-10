package com.ftd.system;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ftd.servlet.FtdException;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class TemplateMgr {

	private Configuration cfg;

	private static final Logger logger = LoggerFactory
			.getLogger(TemplateMgr.class);

	public boolean init(String templatePath) {
		cfg = new Configuration(Configuration.VERSION_2_3_22);
		try {
			cfg.setDirectoryForTemplateLoading(new File(templatePath));
		} catch (IOException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			return false;
		}
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		return true;
	}

	public void writeFile(String filePath, Map<String, Object> data,
			String templateName) throws FtdException {
		Writer out = null;
		try {
			Template template = cfg.getTemplate(templateName, "UTF-8");
			out = new FileWriter(new File(filePath), false);
			template.process(data, out);
			out.flush();
		} catch (IOException ioe) {
			if (ioe instanceof FileNotFoundException)
				throw new FtdException(ioe, "file.not.found", templateName);
			else
				throw new FtdException(ioe, "file.write.error", templateName);
		} catch (TemplateException te) {
			throw new FtdException(te, "template.parse.error", templateName);
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					// do nothing
				}
		}
	}

	public void writeResponse(HttpServletResponse response,
			Map<String, Object> data, String templateName) throws FtdException {
		try {
			Template template = cfg.getTemplate(templateName, "UTF-8");

			template.process(data, response.getWriter());

		} catch (IOException ioe) {
			if (ioe instanceof FileNotFoundException)
				throw new FtdException(ioe, "file.not.found", templateName);
			else
				throw new FtdException(ioe, "file.write.error", templateName);
		} catch (TemplateException te) {
			throw new FtdException(te, "template.parse.error", templateName);
		}
	}

}
