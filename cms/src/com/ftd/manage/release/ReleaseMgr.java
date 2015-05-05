package com.ftd.manage.release;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ftd.manage.article.Article;
import com.ftd.manage.article.ArticleMgr;
import com.ftd.manage.channel.Channel;
import com.ftd.manage.channel.ChannelMgr;
import com.ftd.manage.release.Release.Src;
import com.ftd.manage.release.model.ModelProvider;
import com.ftd.manage.release.model.ReleaseModel;
import com.ftd.servlet.Context;
import com.ftd.servlet.FtdException;
import com.ftd.system.FilePath;
import com.ftd.system.SysMgr;
import com.ftd.util.StrUtil;
import com.ftd.util.XmlUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class ReleaseMgr {

	// 并发给以后扩展 默认发布方式 releaseSrc-Release
	private Map<String, Release> default_release = new ConcurrentHashMap<String, Release>();
	// releaseId-Release
	private Map<String, Release> releaseId_release = new ConcurrentHashMap<String, Release>();

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

	public void release(int channelId, Releasable r) throws FtdException {

		// 取得发布配置
		Release rc = releaseId_release.get(r.getReleaseId());
		if (rc == null) {
			throw new FtdException(null, "release.not.found");
		}

		// 取得数据
		Map<String, Object> models = new HashMap<String, Object>();
		for (ModelProvider model : rc.getModels()) {
			models.putAll(model.getModel(channelId, 0));
		}
		models.putAll(r.getReleaseModel());

		// 得到文件名
		String filename = r.getReleaseUrl();
		if (StrUtil.isEmpty(filename)) {
			filename = PathFormat.parse(
					PathFormat.format(rc.getFilenameFormat()), channelId);
		}
		int i = filename.lastIndexOf("/");
		if (i > 0) {
			String dirStr = FilePath.ROOT_PATH + filename.substring(0, i);
			File dir = new File(dirStr);
			if (!dir.exists())
				dir.mkdirs();
		}

		// 发布网页
		writeFile(FilePath.ROOT_PATH + filename, models, rc.getTemplateName());

		// 发布后的更新
		r.afterRelease(new AfterRelease(filename));

	}

	public void release(Release.Src src, int channelId, int articleId)
			throws FtdException {
		ReleaseMsg releaseMsg = getReleaseMsg(src, channelId, articleId);
		if (releaseMsg == null) {
			throw new FtdException(null, "release.not.found");
		}

		Release release = null;
		if (!StrUtil.isEmpty(releaseMsg.releaseId)) {
			release = releaseId_release.get(releaseMsg.releaseId);
		}
		if (release == null) {
			release = default_release.get(src.toString());
		}

		// 取得数据
		Map<String, Object> models = new HashMap<String, Object>();
		for (ModelProvider model : release.getModels()) {
			models.putAll(model.getModel(channelId, articleId));
		}

		// 得到文件名
		String filename = releaseMsg.fileName;
		int i = filename.lastIndexOf("/");
		if (i > 0) {
			String dirStr = FilePath.ROOT_PATH + filename.substring(0, i);
			File dir = new File(dirStr);
			if (!dir.exists())
				dir.mkdirs();
		}

		// 发布网页
		writeFile(FilePath.ROOT_PATH + filename, models,
				release.getTemplateName());

		// 发布网页后的数据
		ReleaseModel rm = new ReleaseModel();
		rm.setChannelId(channelId);
		rm.setArticleId(articleId);
		rm.setReleaseTime(System.currentTimeMillis());
		for (ModelProvider model : release.getModels()) {
			model.afterRelease(rm);
		}

	}

	private ReleaseMsg getReleaseMsg(Release.Src src, int channelId,
			int articleId) {
		String releaseId = null;
		if (src == Release.Src.ARTICLE) {
			Article a = ArticleMgr.getInstance().getArticle(articleId);
			if (a == null)
				return null;
			if (!StrUtil.isEmpty(a.getReleaseId())) {
				releaseId = a.getReleaseId();
			}
			return new ReleaseMsg(releaseId, a.getArticleUrl());

		} else if (src == Release.Src.FIRST_CHANNEL
				|| src == Release.Src.SECOND_CHANNEL) {
			Channel c = ChannelMgr.getInstance().getChannel(channelId);
			if (c == null)
				return null;
			if (!StrUtil.isEmpty(c.getReleaseId())) {
				releaseId = c.getReleaseId();
			}
			return new ReleaseMsg(releaseId, c.getChannelUrl());
		} else if (src == Release.Src.MAIN_PAGE) {
			int cid = SysMgr.getInstance().getMainPageChannel();
			Channel c = ChannelMgr.getInstance().getChannel(cid);
			if (c == null)
				return null;
			if (!StrUtil.isEmpty(c.getReleaseId())) {
				releaseId = c.getReleaseId();
			}
			return new ReleaseMsg(releaseId, c.getChannelUrl());
		}
		return null;
	}

	public String getReleaseFilename(Release.Src src, int channelId) {
		String filename = null;
		Release r = default_release.get(src.toString());
		if (r != null) {
			filename = PathFormat.parse(
					PathFormat.format(r.getFilenameFormat()), channelId);
		}
		return filename;

	}

	public void preview(int channelId, Releasable r, Context ctx)
			throws FtdException {

		// 取得发布配置
		Release rc = releaseId_release.get(r.getReleaseId());
		if (rc == null) {
			throw new FtdException(null, "release.not.found");
		}

		// 取得数据
		Map<String, Object> models = new HashMap<String, Object>();
		for (ModelProvider model : rc.getModels()) {
			models.putAll(model.getModel(channelId, 0));
		}
		models.putAll(r.getReleaseModel());

		writeResponse(ctx.response, models, rc.getTemplateName());

	}

	public void preview(Release.Src src, int channelId, int articleId,
			Context ctx) throws FtdException {
		ReleaseMsg releaseMsg = getReleaseMsg(src, channelId, articleId);
		if (releaseMsg == null) {
			throw new FtdException(null, "release.not.found");
		}

		Release release = null;
		if (!StrUtil.isEmpty(releaseMsg.releaseId)) {
			release = releaseId_release.get(releaseMsg.releaseId);
		}
		if (release == null) {
			release = default_release.get(src.toString());
		}

		// 取得数据
		Map<String, Object> models = new HashMap<String, Object>();
		for (ModelProvider model : release.getModels()) {
			models.putAll(model.getModel(channelId, articleId));
		}

		writeResponse(ctx.response, models, release.getTemplateName());
	}

	public void realTimePreview(Release.Src src, int channelId, int articleId,
			Context ctx) throws FtdException {

		String releaseId = StrUtil.parseStr(
				(String) ctx.paramMap.get("releaseId"), "");
		Release release = null;
		if (StrUtil.isEmpty(releaseId)) {
			release = default_release.get(src.toString());
		} else {
			release = releaseId_release.get(releaseId);
		}
		if (release == null) {
			throw new FtdException(null, "release.not.found");
		}

		// 取得数据
		Map<String, Object> models = new HashMap<String, Object>();
		for (ModelProvider model : release.getModels()) {
			models.putAll(model.getModel(channelId, articleId));
		}
		JSONObject obj = JSONObject.fromObject(ctx.paramMap);
		Article a = (Article) JSONObject.toBean(obj, Article.class);
		models.put("article", a);

		writeResponse(ctx.response, models, release.getTemplateName());
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
			release.setId(StrUtil.parseStr(kv.get("Id"), ""));
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
				if (mp != null) {
					HashMap<String, String> mpkv = XmlUtil
							.getAttribute(modElement);
					int modelId = StrUtil.parseInt(mpkv.get("ModelId"), 0);
					if (modelId != 0)
						mp.setModelId(modelId);
					release.getModels().add(mp);
				}
			}

			releaseId_release.put(release.getId(), release);
		}

		for (Element defaultElement : root.elements("Default")) {
			HashMap<String, String> kv = XmlUtil.getAttribute(defaultElement);
			String src = StrUtil.parseStr(kv.get("Src"), "");
			Src s = Release.Src.valueOf(src);
			if (s == null)
				continue;

			String id = StrUtil.parseStr(kv.get("ReleaseId"), "");
			if (StrUtil.isEmpty(id))
				continue;
			Release r = releaseId_release.get(id);
			if (r == null)
				continue;

			default_release.put(s.toString(), r);
		}

		return true;
	}

	public Release getRelease(Release.Src src) {
		return default_release.get(src.toString());
	}

	public Release getRelease(String releaseId) {
		return releaseId_release.get(releaseId);
	}

	public Collection<Release> getReleases() {
		return releaseId_release.values();
	}

	public static class ReleaseMsg {
		public final String releaseId;
		public final String fileName;

		public ReleaseMsg(String releaseId, String fileName) {
			this.releaseId = releaseId;
			this.fileName = fileName;
		}

	}

}
