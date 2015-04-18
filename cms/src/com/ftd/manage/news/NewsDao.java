package com.ftd.manage.news;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ftd.servlet.FtdException;
import com.ftd.system.SysMgr;
import com.ftd.util.StrUtil;
import com.ftd.util.StrZipUtil;
import com.ftd.util.dbclient.DBClient;

public class NewsDao {

	private static Logger logger = LoggerFactory.getLogger(NewsDao.class);

	public static void insert(News news, String content) throws FtdException {
		DBClient dbClient = SysMgr.getInstance().getDbClient();

		String sql = "insert into news(news_type, news_title, news_url, news_time, news_content) values(?, ?, ?, ?, ?)";

		try {
			dbClient.executeUpdate(
					sql,
					news.getNewsType(),
					news.getNewsTitle(),
					news.getNewsUrl(),
					news.getNewsTime(),
					StrUtil.toHexString(StrZipUtil.compress(content).getBytes()));
		} catch (SQLException e) {
			throw new FtdException(e, "db.sql.error");
		}
	}

	public static void delete(int newsId) throws FtdException {
		DBClient dbClient = SysMgr.getInstance().getDbClient();

		String sql = "delete from news where news_id = ?";

		try {
			dbClient.executeUpdate(sql, newsId);
		} catch (SQLException e) {
			throw new FtdException(e, "db.sql.error");
		}
	}

	public static void update(News news, String content) throws FtdException {
		DBClient dbClient = SysMgr.getInstance().getDbClient();

		String sql = "update news set news_type=?, news_title=?, news_url=?, news_time=?, news_content=? where news_id=?";

		try {
			dbClient.executeUpdate(
					sql,
					news.getNewsType(),
					news.getNewsTitle(),
					news.getNewsUrl(),
					news.getNewsTime(),
					StrUtil.toHexString(StrZipUtil.compress(content).getBytes()),
					news.getNewsId());
		} catch (SQLException e) {
			throw new FtdException(e, "db.sql.error");
		}
	}

	public static News select(int newsId) throws FtdException {
		DBClient dbClient = SysMgr.getInstance().getDbClient();
		String sql = "select * from news where news_id=" + newsId;

		CachedRowSet rs = null;
		try {
			rs = dbClient.executeQuery(sql);

			if (rs != null) {
				while (rs.next()) {
					News news = new News();

					news.setNewsId(rs.getInt("news_id"));
					news.setNewsType(rs.getInt("news_type"));
					news.setNewsTitle(rs.getString("news_title"));
					news.setNewsUrl(rs.getString("news_url"));
					news.setNewsTime(rs.getString("news_time"));

					byte[] content = rs.getBytes("news_content");
					if (content != null) {
						news.setNewsContent(StrZipUtil.unCompress(StrUtil
								.parseHexString(new String(content))));
					}

					return news;
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

	public static int selectNum(int newsType, String startDate, String endDate,
			int pageSize, int pageNum) throws FtdException {

		DBClient dbClient = SysMgr.getInstance().getDbClient();
		String sql = String
				.format("select count(1) from news where (0=%d or news_type=%d) and ('%s'='' or news_time > '%s') and ('%s'='' or news_time < '%s') "
						+ "order by news_time desc limit %d,%d", newsType,
						newsType, startDate, startDate, endDate, endDate,
						pageSize * (pageNum - 1), pageSize);

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

	public static List<News> selectAll(int newsType, String startDate,
			String endDate, int pageSize, int pageNum) throws FtdException {
		List<News> newsList = new ArrayList<News>();

		DBClient dbClient = SysMgr.getInstance().getDbClient();
		String sql = String
				.format("select * from news where (0=%d or news_type=%d) and ('%s'='' or news_time > '%s') and ('%s'='' or news_time < '%s') "
						+ "order by news_time desc limit %d,%d", newsType,
						newsType, startDate, startDate, endDate, endDate,
						pageSize * (pageNum - 1), pageSize);

		CachedRowSet rs = null;
		try {
			rs = dbClient.executeQuery(sql);

			if (rs != null) {
				while (rs.next()) {
					News news = new News();

					news.setNewsId(rs.getInt("news_id"));
					news.setNewsType(rs.getInt("news_type"));
					news.setNewsTitle(rs.getString("news_title"));
					news.setNewsUrl(rs.getString("news_url"));
					news.setNewsTime(rs.getString("news_time"));

					newsList.add(news);
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

		return newsList;
	}

	public static List<News> selectAll() throws FtdException {
		List<News> newsList = new ArrayList<News>();

		DBClient dbClient = SysMgr.getInstance().getDbClient();
		String sql = "select * from news order by news_time desc";

		CachedRowSet rs = null;
		try {
			rs = dbClient.executeQuery(sql);

			if (rs != null) {
				while (rs.next()) {
					News news = new News();

					news.setNewsId(rs.getInt("news_id"));
					news.setNewsType(rs.getInt("news_type"));
					news.setNewsTitle(rs.getString("news_title"));
					news.setNewsUrl(rs.getString("news_url"));
					news.setNewsTime(rs.getString("news_time"));

					newsList.add(news);
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

		return newsList;
	}

}
