package com.ftd.manage.release;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ftd.manage.release.Release.Src;
import com.ftd.manage.release.check.AfterRelease;
import com.ftd.manage.release.model.ModelProvider;
import com.ftd.manage.release.model.ReleaseModel;
import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.system.FilePath;
import com.ftd.util.StrUtil;
import com.ftd.util.XmlUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class ReleaseMgr {

	// 并发给以后扩展
	private Map<String, Release> releaseSrc_release = new ConcurrentHashMap<String, Release>();

	private Configuration cfg;

	private static final Logger logger = LoggerFactory
			.getLogger(ReleaseMgr.class);

	private static ReleaseMgr instance = new ReleaseMgr();

	private ReleaseMgr() {
	}

	public static ReleaseMgr getInstance() {
		return instance;
	}

	public boolean init() {
		if (!initTemplate()) {
			return false;
		}
		if (!loadFromXMl()) {
			return false;
		}
		return true;
	}

	public Release getRelease(String src) {
		return releaseSrc_release.get(src);
	}

	/**
	 * 发布一个网页
	 * 
	 * @param releaseSrc
	 *            网页种类
	 * @param releaseId
	 *            栏目Id 或者文章Id
	 * @throws FtdException
	 */
	public void release(int articleId, String releaseSrc, int... channelIds)
			throws FtdException {
		Release r = releaseSrc_release.get(releaseSrc);
		if (r == null)
			throw new FtdException(null, "release.manager.not.found",
					releaseSrc);

		// 取得数据
		Map<String, Object> models = new HashMap<String, Object>();
		for (ModelProvider model : r.getModels()) {
			if (!model.isCached()) {
				model.setModel(articleId, channelIds);
			}
			models.putAll(model.getModel(articleId, channelIds));
		}

		// 得到文件名
		String filename = getReleaseFilename(releaseSrc, channelIds);
		int i = filename.lastIndexOf("/");
		if (i > 0) {
			String dirStr = FilePath.ROOT_PATH + filename.substring(0, i);
			File dir = new File(dirStr);
			if (!dir.exists())
				dir.mkdirs();
		}

		// 发布网页
		writeFile(FilePath.ROOT_PATH + filename, models, r.getTemplateName());

		// 发布网页后的数据
		ReleaseModel rm = new ReleaseModel();
		rm.setArticleId(articleId);
		rm.setReleaseFilename(filename);
		rm.setReleaseTime(System.currentTimeMillis());
		for (int j = channelIds.length; j > 0; j--) {
			if (channelIds[j - 1] > 0) {
				rm.setChannelId(channelIds[j - 1]);
				break;
			}
		}

		// 发布后需要做的一些操作
		for (AfterRelease ar : r.getAfterReleases()) {
			ar.afterRelease(rm);
		}

	}

	public String getReleaseFilename(String src, int... channelIds) {
		String filename = null;
		Release r = releaseSrc_release.get(src);
		if (r != null) {
			filename = PathFormat.parse(
					PathFormat.format(r.getFilenameFormat()), channelIds);
		}
		return filename;

	}

	public void preview(int articleId, String releaseSrc, Context ctx,
			Map<String, Object> article, int... channelIds) throws FtdException {
		Release r = releaseSrc_release.get(releaseSrc);
		if (r == null)
			throw new FtdException(null, "release.manager.not.found",
					releaseSrc);

		// 取得数据
		Map<String, Object> models = new HashMap<String, Object>();
		for (ModelProvider model : r.getModels()) {
			if (!model.isCached()) {
				model.setModel(articleId, channelIds);
			}
			models.putAll(model.getModel(articleId, channelIds));
		}
		if (article != null)
			models.putAll(article);

		writeResponse(ctx.response, models, r.getTemplateName());
	}

	// 初始化freemarker模板引擎
	private boolean initTemplate() {
		cfg = new Configuration(Configuration.VERSION_2_3_22);
		try {
			cfg.setDirectoryForTemplateLoading(new File(FilePath.TEMPLATE_PATH));
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

	private boolean loadFromXMl() {
		Document doc = XmlUtil.getDocument(FilePath.RELEASE_CONFIG_PATH);
		if (doc == null)
			return false;
		Element root = doc.getRootElement();
		List<Element> releaseElements = root.elements("Release");
		for (Element releaseElement : releaseElements) {
			HashMap<String, String> kv = XmlUtil.getAttribute(releaseElement);

			Release release = new Release();
			release.setSrc(Src.valueOf(kv.get("Src").toUpperCase()));
			release.setTemplateName(StrUtil.parseStr(kv.get("Template"), ""));
			release.setFilenameFormat(StrUtil.parseStr(
					kv.get("FilenameFormat"), ""));

			for (Element modElement : releaseElement.elements("Model")) {
				String modelClass = StrUtil.parseStr(modElement.getTextTrim(),
						"");
				if (StrUtil.isEmpty(modelClass))
					continue;
				ModelProvider mp = null;
				try {
					mp = (ModelProvider) Class.forName(modelClass)
							.newInstance();
				} catch (InstantiationException | IllegalAccessException
						| ClassNotFoundException e) {
					logger.error(ExceptionUtils.getStackTrace(e));
				}
				if (mp != null)
					release.getModels().add(mp);
			}

			for (Element aftercheckElement : releaseElement
					.elements("AfterRelease")) {
				String aftercheckClass = StrUtil.parseStr(
						aftercheckElement.getTextTrim(), "");
				if (StrUtil.isEmpty(aftercheckClass))
					continue;
				AfterRelease ac = null;
				try {
					ac = (AfterRelease) Class.forName(aftercheckClass)
							.newInstance();
				} catch (InstantiationException | IllegalAccessException
						| ClassNotFoundException e) {
					logger.error(ExceptionUtils.getStackTrace(e));
				}
				if (ac != null)
					release.getAfterReleases().add(ac);

			}
			releaseSrc_release.put(release.getSrc().toString(), release);
		}

		return true;
	}

}
