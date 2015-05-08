package com.ext.project.listed;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import com.ftd.servlet.FtdException;
import com.ftd.system.SysMgr;
import com.ftd.util.dbclient.DBClient;

public class ListedProjectDao {
	public static void insert(ListedProject p) throws FtdException {
		String sql = "insert into project_listed(project_title,project_num,create_time,project_type,listed_start_time, listed_end_time,"
				+ "asset_transfor, is_deal, district, transfer_area,transfer_type,transfer_start_time,transfer_end_time,transfer_price,remark)"
				+ " values(?,?,?,?,?,?,   ?,?,?,?,?,?,?,?,?)";

		DBClient dbClient = SysMgr.getInstance().getDbClient();

		try {
			dbClient.executeUpdate(sql, p.getProjectTitle(),p.getProjectNum(),p.getCreateTime(),p.getProjectType(),p.getListedStartTime(), p.getListedEndTime(),
					p.getAssetTransfor(), p.getIsDeal(), p.getDistrict(), p.getTransferArea(), p.getTransferType(), p.getTransferStartTime(), p.getTransferEndTime(), 
					p.getTransferPrice(), p.getRemark());
		} catch (SQLException e) {
			throw new FtdException(e, "db.sql.error");
		}
	}

	public static void delete(int projectId) throws FtdException {
		String sql = "delete from project_listed where project_id = ?";
		DBClient dbClient = SysMgr.getInstance().getDbClient();

		try {
			dbClient.executeUpdate(sql, projectId);
		} catch (SQLException e) {
			throw new FtdException(e, "db.sql.error");
		}
	}
	
	public static void updateRelease(ListedProject p) throws FtdException {
		String sql = "update project_listed set release_url=? where project_id=?";
		DBClient dbClient = SysMgr.getInstance().getDbClient();

		try {
			dbClient.executeUpdate(sql, p.getReleaseUrl(), p.getProjectId());
		} catch (SQLException e) {
			throw new FtdException(e, "db.sql.error");
		}
	}
	
	public static void updateReleaseId(int projectId, String releaseId) throws FtdException {
		String sql = "update project_listed set release_id=? where project_id=?";
		DBClient dbClient = SysMgr.getInstance().getDbClient();

		try {
			dbClient.executeUpdate(sql, releaseId, projectId);
		} catch (SQLException e) {
			throw new FtdException(e, "db.sql.error");
		}
	}

	public static void update(ListedProject p) throws FtdException {
		String sql = "update project_listed set project_title=?,project_num=?,project_type=?,listed_start_time=?, listed_end_time=?,"
				+ "asset_transfor=?, is_deal=?, district=?, transfer_area=?,transfer_type=?,transfer_start_time=?,transfer_end_time=?,transfer_price=?,remark=?"
				+ " where project_id=?";
		DBClient dbClient = SysMgr.getInstance().getDbClient();

		try {
			dbClient.executeUpdate(sql, p.getProjectTitle(),p.getProjectNum(),p.getProjectType(),p.getListedStartTime(), p.getListedEndTime(),
					p.getAssetTransfor(), p.getIsDeal(), p.getDistrict(), p.getTransferArea(), p.getTransferType(), p.getTransferStartTime(), p.getTransferEndTime(), 
					p.getTransferPrice(), p.getRemark(),
					p.getProjectId());
		} catch (SQLException e) {
			throw new FtdException(e, "db.sql.error");
		}
	}
	
	public static List<ListedProject> selectForIndex() throws FtdException {
		List<ListedProject> listedProjects = new ArrayList<ListedProject>();

		DBClient dbClient = SysMgr.getInstance().getDbClient();
		String sql = "select * from project_listed order by project_type asc, project_id desc";

		CachedRowSet rs = null;
		try {
			rs = dbClient.executeQuery(sql);

			if (rs != null) {
				while (rs.next()) {
					ListedProject p = new ListedProject();
					p.setProjectId(rs.getInt("project_id"));
					p.setProjectTitle(rs.getString("project_title"));
					p.setCreateTime(rs.getString("create_time"));
					p.setProjectNum(rs.getString("project_num"));
					p.setProjectType(rs.getInt("project_type"));
					p.setListedStartTime(rs.getString("listed_start_time"));
					p.setListedEndTime(rs.getString("listed_end_time"));
					p.setTransferPrice(rs.getString("transfer_price"));
					
					p.setReleaseUrl(rs.getString("release_url"));
					
					listedProjects.add(p);
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

		return listedProjects;
	}

	public static List<ListedProject> selectAll() throws FtdException {
		List<ListedProject> listedProjects = new ArrayList<ListedProject>();

		DBClient dbClient = SysMgr.getInstance().getDbClient();
		String sql = "select * from project_listed order by project_id desc";

		CachedRowSet rs = null;
		try {
			rs = dbClient.executeQuery(sql);

			if (rs != null) {
				while (rs.next()) {
					ListedProject p = new ListedProject();
					p.setProjectId(rs.getInt("project_id"));
					p.setProjectTitle(rs.getString("project_title"));
					p.setCreateTime(rs.getString("create_time"));
					p.setProjectNum(rs.getString("project_num"));
					p.setProjectType(rs.getInt("project_type"));
					p.setListedStartTime(rs.getString("listed_start_time"));
					p.setListedEndTime(rs.getString("listed_end_time"));
					
					p.setAssetTransfor(rs.getString("asset_transfor"));
					p.setIsDeal(rs.getString("is_deal"));
					p.setDistrict(rs.getString("district"));
					p.setTransferArea(rs.getString("transfer_area"));
					p.setTransferType(rs.getString("transfer_type"));
					p.setTransferStartTime(rs.getString("transfer_start_time"));
					p.setTransferEndTime(rs.getString("transfer_end_time"));
					p.setTransferPrice(rs.getString("transfer_price"));
					p.setRemark(rs.getString("remark"));
					
					p.setReleaseId(rs.getString("release_id"));
					p.setReleaseUrl(rs.getString("release_url"));
					
					listedProjects.add(p);
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

		return listedProjects;
	}
	
	public static ListedProject select(int projectId) throws FtdException {

		DBClient dbClient = SysMgr.getInstance().getDbClient();
		String sql = String.format("select * from project_listed where project_id=%d limit 1", projectId);

		CachedRowSet rs = null;
		try {
			rs = dbClient.executeQuery(sql);

			if (rs != null) {
				while (rs.next()) {
					ListedProject p = new ListedProject();
					p.setProjectId(rs.getInt("project_id"));
					p.setProjectTitle(rs.getString("project_title"));
					p.setCreateTime(rs.getString("create_time"));
					p.setProjectNum(rs.getString("project_num"));
					p.setProjectType(rs.getInt("project_type"));
					p.setListedStartTime(rs.getDate("listed_start_time").getTime());
					p.setListedEndTime(rs.getDate("listed_end_time").getTime());
					
					p.setAssetTransfor(rs.getString("asset_transfor"));
					p.setIsDeal(rs.getString("is_deal"));
					p.setDistrict(rs.getString("district"));
					p.setTransferArea(rs.getString("transfer_area"));
					p.setTransferType(rs.getString("transfer_type"));
					p.setTransferStartTime(rs.getDate("transfer_start_time").getTime());
					p.setTransferEndTime(rs.getDate("transfer_end_time").getTime());
					p.setTransferPrice(rs.getString("transfer_price"));
					p.setRemark(rs.getString("remark"));
					
					p.setReleaseId(rs.getString("release_id"));
					p.setReleaseUrl(rs.getString("release_url"));
					
					return p;
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
	
	public static List<ListedProject> selectForPage(int type, int page, int pageSize) throws FtdException {
		List<ListedProject> listedProjects = new ArrayList<ListedProject>();

		DBClient dbClient = SysMgr.getInstance().getDbClient();
		String sql = String.format("select * from project_listed where project_type=%d order by project_id desc limit %d,%d", type, pageSize
				* (page - 1), pageSize);

		CachedRowSet rs = null;
		try {
			rs = dbClient.executeQuery(sql);

			if (rs != null) {
				while (rs.next()) {
					ListedProject p = new ListedProject();
					p.setProjectTitle(rs.getString("project_title"));
					p.setCreateTime(rs.getString("create_time"));
					p.setReleaseUrl(rs.getString("release_url"));
					listedProjects.add(p);
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

		return listedProjects;
	}
	
	public static int selectCountForPage(int type, int page, int pageSize) throws FtdException {
		DBClient dbClient = SysMgr.getInstance().getDbClient();
		String sql = String.format("select count(1) from project_listed where project_type=%d order by project_id desc limit %d,%d", type, pageSize
				* (page - 1), pageSize);

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
}
