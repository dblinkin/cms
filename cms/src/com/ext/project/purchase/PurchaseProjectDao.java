package com.ext.project.purchase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import com.ftd.servlet.FtdException;
import com.ftd.system.SysMgr;
import com.ftd.util.dbclient.DBClient;

public class PurchaseProjectDao {
	public static void insert(PurchaseProject p) throws FtdException {
		String sql = "insert into project_purchase(project_title,project_src,create_time,"
				+ "project_type, intention_district,purpose,intention_money,intention_area, intention_year, remark,"
				+ "demand_name, demand_address,contract_name, contract_phone,contract_mail, contract_fax,"
				+ "release_id) "
				+ "values(?,?,? ,?,?,?,?,?,?,?, ?,?,?,?,?,?, ?)";

		DBClient dbClient = SysMgr.getInstance().getDbClient();

		try {
			dbClient.executeUpdate(sql, p.getProjectTitle(), p.getProjectSrc(), p.getCreateTime(), 
					p.getProjectType(), p.getIntentionDistrict(), p.getPurpose(), p.getIntentionMoney(), p.getIntentionArea(), p.getIntentionYear(), p.getRemark(),
					p.getDemandName(), p.getDemandAddress(), p.getContractName(), p.getContractPhone(), p.getContractMail(), p.getContractFax(),
					p.getReleaseId());
		} catch (SQLException e) {
			throw new FtdException(e, "db.sql.error");
		}
	}

	public static void delete(int projectId) throws FtdException {
		String sql = "delete from project_purchase where project_id = ?";
		DBClient dbClient = SysMgr.getInstance().getDbClient();

		try {
			dbClient.executeUpdate(sql, projectId);
		} catch (SQLException e) {
			throw new FtdException(e, "db.sql.error");
		}
	}
	
	public static void updateRelease(PurchaseProject p) throws FtdException{
		String sql = "update project_purchase set release_url=? where project_id=?";
		DBClient dbClient = SysMgr.getInstance().getDbClient();

		try {
			dbClient.executeUpdate(sql, p.getReleaseUrl(), p.getProjectId());
		} catch (SQLException e) {
			throw new FtdException(e, "db.sql.error");
		}
	}
	
	public static void updateReleaseId(int projectId, String releaseId) throws FtdException {
		String sql = "update project_purchase set release_id=? where project_id=?";
		DBClient dbClient = SysMgr.getInstance().getDbClient();

		try {
			dbClient.executeUpdate(sql, releaseId, projectId);
		} catch (SQLException e) {
			throw new FtdException(e, "db.sql.error");
		}
	}

	public static void update(PurchaseProject p) throws FtdException {
		String sql = "update project_purchase set project_title=?,project_src=?,create_time=?,"
				+ "project_type=?, intention_district=?,purpose=?,intention_money=?,intention_area=?, intention_year=?, remark=?,"
				+ "demand_name=?, demand_address=?,contract_name=?, contract_phone=?,contract_mail=?, contract_fax=?, "
				+ "release_id=? where project_id=?";
		DBClient dbClient = SysMgr.getInstance().getDbClient();

		try {
			dbClient.executeUpdate(sql, p.getProjectTitle(), p.getProjectSrc(), p.getCreateTime(), 
					p.getProjectType(), p.getIntentionDistrict(), p.getPurpose(), p.getIntentionMoney(), p.getIntentionArea(), p.getIntentionYear(), p.getRemark(),
					p.getDemandName(), p.getDemandAddress(), p.getContractName(), p.getContractPhone(), p.getContractMail(), p.getContractFax(),
					p.getReleaseId(),
					p.getProjectId());
		} catch (SQLException e) {
			throw new FtdException(e, "db.sql.error");
		}
	}

	public static List<PurchaseProject> selectAll() throws FtdException {
		List<PurchaseProject> purchaseProjects = new ArrayList<PurchaseProject>();

		DBClient dbClient = SysMgr.getInstance().getDbClient();
		String sql = "select * from project_purchase order by project_id desc";

		CachedRowSet rs = null;
		try {
			rs = dbClient.executeQuery(sql);

			if (rs != null) {
				while (rs.next()) {
					PurchaseProject p = new PurchaseProject();
					
					p.setProjectId(rs.getInt("project_id"));
					p.setProjectTitle(rs.getString("project_title"));
					p.setProjectSrc(rs.getString("project_src"));
					p.setCreateTime(rs.getDate("create_time").getTime());
					
					p.setProjectType(rs.getString("project_type"));
					p.setIntentionDistrict(rs.getString("intention_district"));
					p.setPurpose(rs.getString("purpose"));
					p.setIntentionMoney(rs.getInt("intention_money"));
					p.setIntentionArea(rs.getString("intention_area"));
					p.setIntentionYear(rs.getInt("intention_year"));
					p.setRemark(rs.getString("remark"));
					
					p.setDemandName(rs.getString("demand_name"));
					p.setDemandAddress(rs.getString("demand_address"));
					p.setContractName(rs.getString("contract_name"));
					p.setContractPhone(rs.getString("contract_phone"));
					p.setContractMail(rs.getString("contract_mail"));
					p.setContractFax(rs.getString("contract_fax"));
					
					p.setReleaseId(rs.getString("release_id"));
					p.setReleaseUrl(rs.getString("release_url"));
					
					purchaseProjects.add(p);
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

		return purchaseProjects;
	}
	
	public static PurchaseProject select(int projectId) throws FtdException {
		DBClient dbClient = SysMgr.getInstance().getDbClient();
		String sql = String.format("select * from project_purchase where project_id=%d limit 1", projectId);

		CachedRowSet rs = null;
		try {
			rs = dbClient.executeQuery(sql);

			if (rs != null) {
				while (rs.next()) {
					PurchaseProject p = new PurchaseProject();
					
					p.setProjectId(rs.getInt("project_id"));
					p.setProjectTitle(rs.getString("project_title"));
					p.setProjectSrc(rs.getString("project_src"));
					p.setCreateTime(rs.getDate("create_time").getTime());
					
					p.setProjectType(rs.getString("project_type"));
					p.setIntentionDistrict(rs.getString("intention_district"));
					p.setPurpose(rs.getString("purpose"));
					p.setIntentionMoney(rs.getInt("intention_money"));
					p.setIntentionArea(rs.getString("intention_area"));
					p.setIntentionYear(rs.getInt("intention_year"));
					p.setRemark(rs.getString("remark"));
					
					p.setDemandName(rs.getString("demand_name"));
					p.setDemandAddress(rs.getString("demand_address"));
					p.setContractName(rs.getString("contract_name"));
					p.setContractPhone(rs.getString("contract_phone"));
					p.setContractMail(rs.getString("contract_mail"));
					p.setContractFax(rs.getString("contract_fax"));
					
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
}
