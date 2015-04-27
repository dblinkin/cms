package com.ext.project.recommend;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import com.ftd.servlet.FtdException;
import com.ftd.system.SysMgr;
import com.ftd.util.dbclient.DBClient;

public class RcmdProjectDao {
	public static void insert(RcmdProject rp) throws FtdException {
		String sql = "insert into project_recommend(project_title,project_img_url,article_id, is_active) values(?,?,?,?)";

		DBClient dbClient = SysMgr.getInstance().getDbClient();

		try {
			dbClient.executeUpdate(sql, rp.getProjectTitle(),
					rp.getProjectImgUrl(), rp.getArticleId(), rp.getActive());
		} catch (SQLException e) {
			throw new FtdException(e, "db.sql.error");
		}
	}

	public static void delete(int projectId) throws FtdException {
		String sql = "delete from project_recommend where project_id = ?";
		DBClient dbClient = SysMgr.getInstance().getDbClient();

		try {
			dbClient.executeUpdate(sql, projectId);
		} catch (SQLException e) {
			throw new FtdException(e, "db.sql.error");
		}
	}

	public static void update(RcmdProject rp) throws FtdException {
		String sql = "update project_recommend set project_title=?, project_img_url=?,article_id=?,is_active=? where project_id=?";
		DBClient dbClient = SysMgr.getInstance().getDbClient();

		try {
			dbClient.executeUpdate(sql, rp.getProjectTitle(),
					rp.getProjectImgUrl(), rp.getArticleId(), rp.getActive(),
					rp.getProjectId());
		} catch (SQLException e) {
			throw new FtdException(e, "db.sql.error");
		}
	}

	public static List<RcmdProject> selectAll() throws FtdException {
		List<RcmdProject> rcmdProjects = new ArrayList<RcmdProject>();

		DBClient dbClient = SysMgr.getInstance().getDbClient();
		String sql = "select * from project_recommend";

		CachedRowSet rs = null;
		try {
			rs = dbClient.executeQuery(sql);

			if (rs != null) {
				while (rs.next()) {
					RcmdProject rp = new RcmdProject();
					rp.setProjectId(rs.getInt("project_id"));
					rp.setProjectTitle(rs.getString("project_title"));
					rp.setProjectImgUrl(rs.getString("project_img_url"));
					rp.setArticleId(rs.getInt("article_id"));
					rp.setActive(rs.getInt("is_active"));
					rcmdProjects.add(rp);
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

		return rcmdProjects;
	}
}
