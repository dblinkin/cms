package com.ext.newspoll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import com.ftd.servlet.FtdException;
import com.ftd.system.SysMgr;
import com.ftd.util.dbclient.DBClient;

public class NewsPollDao {
	public static void insert(NewsPoll np) throws FtdException {
		String sql = "insert into news_poll(news_title,poll_img_url,article_id, is_active) values(?,?,?,?)";

		DBClient dbClient = SysMgr.getInstance().getDbClient();

		try {
			dbClient.executeUpdate(sql, np.getNewsTitle(), np.getPollImgUrl(),
					np.getArticleId(), np.getActive());
		} catch (SQLException e) {
			throw new FtdException(e, "db.sql.error");
		}
	}

	public static void delete(int newsPollId) throws FtdException {
		String sql = "delete from news_poll where news_id = ?";
		DBClient dbClient = SysMgr.getInstance().getDbClient();

		try {
			dbClient.executeUpdate(sql, newsPollId);
		} catch (SQLException e) {
			throw new FtdException(e, "db.sql.error");
		}
	}

	public static void update(NewsPoll np) throws FtdException {
		String sql = "update news_poll set news_title=?, poll_img_url=?,article_id=?,is_active=? where news_id=?";
		DBClient dbClient = SysMgr.getInstance().getDbClient();

		try {
			dbClient.executeUpdate(sql, np.getNewsTitle(), np.getPollImgUrl(),
					np.getArticleId(), np.getActive(), np.getNewsId());
		} catch (SQLException e) {
			throw new FtdException(e, "db.sql.error");
		}
	}

	public static List<NewsPoll> selectAll() throws FtdException {
		List<NewsPoll> newsPolls = new ArrayList<NewsPoll>();

		DBClient dbClient = SysMgr.getInstance().getDbClient();
		String sql = "select * from news_poll";

		CachedRowSet rs = null;
		try {
			rs = dbClient.executeQuery(sql);

			if (rs != null) {
				while (rs.next()) {
					NewsPoll np = new NewsPoll();
					np.setNewsId(rs.getInt("news_id"));
					np.setNewsTitle(rs.getString("news_title"));
					np.setPollImgUrl(rs.getString("poll_img_url"));
					np.setArticleId(rs.getInt("article_id"));
					np.setActive(rs.getInt("is_active"));
					newsPolls.add(np);
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

		return newsPolls;
	}
}
