package com.ftd.manage.channel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ftd.servlet.FtdException;
import com.ftd.system.SysMgr;
import com.ftd.util.dbclient.DBClient;

public class ChannelDao {
	private static final Logger logger = LoggerFactory
			.getLogger(ChannelDao.class);

	public static void insert(Channel c) throws FtdException {
		String sql = "insert into channel values(?,?,?,?,?,?,?)";
		DBClient dbClient = SysMgr.getInstance().getDbClient();

		try {
			dbClient.executeUpdate(sql, c.getChannelId(),
					c.getParentChannelId(), c.getChannelName(),
					c.getChannelDesc(), c.getChannelUrl(), c.getIsNav(),
					c.getReleaseId());
		} catch (SQLException e) {
			throw new FtdException(e, "db.sql.error");
		}
	}

	public static void delete(int channelId) throws FtdException {
		String sql = "delete from channel where channel_id=" + channelId;
		DBClient dbClient = SysMgr.getInstance().getDbClient();
		try {
			dbClient.executeUpdate(sql);
		} catch (SQLException e) {
			throw new FtdException(e, "db.sql.error");
		}
	}

	public static void update(Channel c) throws FtdException {
		String sql = "update channel set parent_channel_id=?,channel_name=?,channel_desc=?,channel_url=?,release_id=?,is_nav=? where channel_id=?";
		DBClient dbClient = SysMgr.getInstance().getDbClient();
		try {
			dbClient.executeUpdate(sql, c.getParentChannelId(),
					c.getChannelName(), c.getChannelDesc(), c.getChannelUrl(),
					c.getReleaseId(), c.getIsNav(), c.getChannelId());
		} catch (SQLException e) {
			throw new FtdException(e, "db.sql.error");
		}
	}

	public static void updateReleaseId(int channelId, String releaseId)
			throws FtdException {
		String sql = "update channel set release_id=? where channel_id=?";
		DBClient dbClient = SysMgr.getInstance().getDbClient();
		try {
			dbClient.executeUpdate(sql, releaseId, channelId);
		} catch (SQLException e) {
			throw new FtdException(e, "db.sql.error");
		}
	}

	public static List<Channel> selectAll() throws FtdException {
		List<Channel> channels = new ArrayList<Channel>();
		String sql = "select * from channel";
		DBClient dbClient = SysMgr.getInstance().getDbClient();

		CachedRowSet rs = null;

		try {
			rs = dbClient.executeQuery(sql);
			if (rs != null) {
				while (rs.next()) {
					Channel c = new Channel();
					c.setChannelId(rs.getInt("channel_id"));
					c.setParentChannelId(rs.getInt("parent_channel_id"));
					c.setChannelName(rs.getString("channel_name"));
					c.setChannelDesc(rs.getString("channel_desc"));
					c.setChannelUrl(rs.getString("channel_url"));
					c.setReleaseId(rs.getString("release_id"));
					c.setIsNav(rs.getInt("is_nav"));
					channels.add(c);
				}
			}
		} catch (SQLException e) {
			throw new FtdException(e, "db.sql.error");
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					logger.error(ExceptionUtils.getStackTrace(e));
				}
		}
		return channels;
	}
}
