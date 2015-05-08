package com.ftd.manage.article;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import com.ftd.manage.release.model.ReleaseModel;
import com.ftd.servlet.FtdException;
import com.ftd.system.SysMgr;
import com.ftd.util.StrUtil;
import com.ftd.util.StrZipUtil;
import com.ftd.util.dbclient.DBClient;

public class ArticleDao {

	public static void insert(Article a) throws FtdException {
		String sql = "insert into article(channel_id, article_title,article_url,create_time,is_released,released_time,article_src,"
				+ "article_content, release_id) values(?,?,?,?,?,?,?,?,?)";

		DBClient dbClient = SysMgr.getInstance().getDbClient();

		try {
			dbClient.executeUpdate(sql, a.getChannelId(), a.getArticleTitle(),
					a.getArticleUrl(), a.getCreateTime(), a.isReleased() ? 1
							: 0, a.getReleasedTime(), a.getArticleSrc(),
					StrUtil.toHexString(StrZipUtil.compress(
							a.getArticleContent()).getBytes()), a
							.getReleaseId());
		} catch (SQLException e) {
			throw new FtdException(e, "db.sql.error");
		}
	}

	public static void delete(int articleId) throws FtdException {
		String sql = "delete from article where article_id=?";

		DBClient dbClient = SysMgr.getInstance().getDbClient();

		try {
			dbClient.executeUpdate(sql, articleId);
		} catch (SQLException e) {
			throw new FtdException(e, "db.sql.error");
		}
	}

	public static void updateReleaseId(String releaseId, int articleId)
			throws FtdException {
		String sql = "update article set release_id=? where articleId=?";

		DBClient dbClient = SysMgr.getInstance().getDbClient();
		try {
			dbClient.executeUpdate(sql, releaseId, articleId);
		} catch (SQLException e) {
			throw new FtdException(e, "db.sql.error");
		}

	}

	public static void update(Article a) throws FtdException {
		String sql = "update article set channel_id=?, article_title=?,article_src=?,"
				+ "article_content=? where article_id=?";

		DBClient dbClient = SysMgr.getInstance().getDbClient();

		try {
			dbClient.executeUpdate(
					sql,
					a.getChannelId(),
					a.getArticleTitle(),
					a.getArticleSrc(),
					StrUtil.toHexString(StrZipUtil.compress(
							a.getArticleContent()).getBytes()),
					a.getArticleId());
		} catch (SQLException e) {
			throw new FtdException(e, "db.sql.error");
		}
	}

	public static void updateRelease(ReleaseModel rm) throws FtdException {
		String sql = "update article set is_released=?,released_time=? where article_id=?";

		DBClient dbClient = SysMgr.getInstance().getDbClient();

		try {
			dbClient.executeUpdate(sql, 1,
					StrUtil.datetime(rm.getReleaseTime()), rm.getArticleId());
		} catch (SQLException e) {
			throw new FtdException(e, "db.sql.error");
		}
	}

	public static void updateReleaseId(int articleId, String releaseId)
			throws FtdException {
		String sql = "update article set release_id=? where article_id=?";

		DBClient dbClient = SysMgr.getInstance().getDbClient();

		try {
			dbClient.executeUpdate(sql, releaseId, articleId);
		} catch (SQLException e) {
			throw new FtdException(e, "db.sql.error");
		}
	}

	public static Article select(int articleId) throws FtdException {
		DBClient dbClient = SysMgr.getInstance().getDbClient();
		String sql = "select * from article where article_id=" + articleId
				+ " limit 1";

		CachedRowSet rs = null;
		try {
			rs = dbClient.executeQuery(sql);

			if (rs != null) {
				while (rs.next()) {
					Article a = new Article();
					a.setArticleId(articleId);
					a.setChannelId(rs.getInt("channel_id"));
					a.setArticleTitle(rs.getString("article_title"));
					a.setArticleUrl(rs.getString("article_url"));
					a.setCreateTime(rs.getString("create_time"));
					a.setReleased(rs.getBoolean("is_released"));
					a.setReleasedTime(rs.getString("released_time"));
					a.setArticleSrc(rs.getString("article_src"));

					byte[] content = rs.getBytes("article_content");
					if (content != null) {
						a.setArticleContent(StrZipUtil.unCompress(StrUtil
								.parseHexString(new String(content))));
					}

					return a;
				}
			}
		} catch (SQLException e) {
			throw new FtdException(e, "db.sql.error");
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// do nothing
				}
		}

		return null;
	}

	public static int selectNum(int channel1Id, int channel2Id,
			String startDate, String endDate) throws FtdException {

		DBClient dbClient = SysMgr.getInstance().getDbClient();
		String sql = String
				.format("select count(1) from article where (0=%d or (channel_id>=%d and channel_id<%d+256)) "
						+ "and (0=%d or channel_id=%d) "
						+ "and ('%s'='' or released_time > '%s') and ('%s'='' or released_time < '%s') "
						+ "order by create_time desc", channel1Id, channel1Id,
						channel1Id, channel2Id, channel2Id, startDate,
						startDate, endDate, endDate);

		CachedRowSet rs = null;
		try {
			rs = dbClient.executeQuery(sql);

			if (rs != null) {
				while (rs.next()) {
					return rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			throw new FtdException(e, "db.sql.error");
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// do nothing
				}
		}

		return 0;
	}

	public static List<Article> selectAll(int channel1Id, int channel2Id,
			String startDate, String endDate, int pageSize, int pageNum)
			throws FtdException {
		List<Article> articles = new ArrayList<Article>();

		DBClient dbClient = SysMgr.getInstance().getDbClient();
		String sql = String
				.format("select article_id,channel_id,article_title,article_url,create_time,is_released,released_time,article_src,release_id from article "
						+ "where (0=%d or (channel_id>=%d and channel_id<%d+256)) "
						+ "and (0=%d or channel_id=%d) "
						+ "and ('%s'='' or released_time > '%s') and ('%s'='' or released_time < '%s') "
						+ "order by create_time desc limit %d,%d", channel1Id,
						channel1Id, channel1Id, channel2Id, channel2Id,
						startDate, startDate, endDate, endDate, pageSize
								* (pageNum - 1), pageSize);

		CachedRowSet rs = null;
		try {
			rs = dbClient.executeQuery(sql);

			if (rs != null) {
				while (rs.next()) {
					Article a = new Article();
					a.setArticleId(rs.getInt("article_id"));
					a.setChannelId(rs.getInt("channel_id"));
					a.setArticleTitle(rs.getString("article_title"));
					a.setArticleUrl(rs.getString("article_url"));
					a.setCreateTime(rs.getString("create_time"));
					a.setReleased(rs.getBoolean("is_released"));
					a.setReleasedTime(rs.getString("released_time"));
					a.setArticleSrc(rs.getString("article_src"));
					a.setReleaseId(rs.getString("release_id"));
					articles.add(a);
				}
			}
		} catch (SQLException e) {
			throw new FtdException(e, "db.sql.error");
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// do nothing
				}
		}

		return articles;
	}

	public static List<Article> selectAll(boolean loadContent, String condition)
			throws FtdException {
		List<Article> articles = new ArrayList<Article>();

		DBClient dbClient = SysMgr.getInstance().getDbClient();
		String sql = String
				.format("select * from article where (%s is null or channel_id in (%s)) order by create_time desc",
						condition, condition);

		CachedRowSet rs = null;
		try {
			rs = dbClient.executeQuery(sql);

			if (rs != null) {
				while (rs.next()) {
					Article a = new Article();
					a.setArticleId(rs.getInt("article_id"));
					a.setChannelId(rs.getInt("channel_id"));
					a.setArticleTitle(rs.getString("article_title"));
					a.setArticleUrl(rs.getString("article_url"));
					a.setCreateTime(rs.getString("create_time"));
					a.setReleased(rs.getBoolean("is_released"));
					a.setReleasedTime(rs.getString("released_time"));
					a.setArticleSrc(rs.getString("article_src"));
					a.setReleaseId(rs.getString("release_id"));

					if (loadContent) {
						byte[] content = rs.getBytes("article_content");
						if (content != null) {
							a.setArticleContent(StrZipUtil.unCompress(StrUtil
									.parseHexString(new String(content))));
						}
					}

					articles.add(a);
				}
			}
		} catch (SQLException e) {
			throw new FtdException(e, "db.sql.error");
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					// do nothing
				}
		}

		return articles;
	}

}
