package com.ext.friendlink;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import com.ftd.servlet.FtdException;
import com.ftd.system.SysMgr;
import com.ftd.util.dbclient.DBClient;

public class FriendLinkDao {
	public static void insert(FriendLink fl) throws FtdException {
		String sql = "insert into friend_link(link_title,link_url,link_img_url,is_active) values(?,?,?,?)";

		DBClient dbClient = SysMgr.getInstance().getDbClient();

		try {
			dbClient.executeUpdate(sql, fl.getLinkTitle(), fl.getLinkUrl(),
					fl.getLinkImgUrl(), fl.getActive());
		} catch (SQLException e) {
			throw new FtdException(e, "db.sql.error");
		}
	}

	public static void delete(int friendlinkId) throws FtdException {
		String sql = "delete from friend_link where link_id = ?";
		DBClient dbClient = SysMgr.getInstance().getDbClient();

		try {
			dbClient.executeUpdate(sql, friendlinkId);
		} catch (SQLException e) {
			throw new FtdException(e, "db.sql.error");
		}
	}

	public static void update(FriendLink fl) throws FtdException {
		String sql = "update friend_link set link_title=?, link_url=?,link_img_url=?,is_active=? where link_id=?";
		DBClient dbClient = SysMgr.getInstance().getDbClient();

		try {
			dbClient.executeUpdate(sql, fl.getLinkTitle(), fl.getLinkUrl(),
					fl.getLinkImgUrl(), fl.getActive(), fl.getLinkId());
		} catch (SQLException e) {
			throw new FtdException(e, "db.sql.error");
		}
	}

	public static List<FriendLink> selectAll() throws FtdException {
		List<FriendLink> friendLinks = new ArrayList<FriendLink>();

		DBClient dbClient = SysMgr.getInstance().getDbClient();
		String sql = "select * from friend_link";

		CachedRowSet rs = null;
		try {
			rs = dbClient.executeQuery(sql);

			if (rs != null) {
				while (rs.next()) {
					FriendLink fl = new FriendLink();
					fl.setLinkId(rs.getInt("link_id"));
					fl.setLinkTitle(rs.getString("link_title"));
					fl.setLinkUrl(rs.getString("link_url"));
					fl.setLinkImgUrl(rs.getString("link_img_url"));
					fl.setActive(rs.getInt("is_active"));
					friendLinks.add(fl);
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

		return friendLinks;
	}
}
