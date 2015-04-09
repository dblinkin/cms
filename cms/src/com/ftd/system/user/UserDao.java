package com.ftd.system.user;

import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ftd.servlet.FtdException;
import com.ftd.util.MD5Util;
import com.ftd.util.dbclient.DBClient;
import com.ftd.util.dbclient.SQLFilter;

public class UserDao {
	private Logger logger = LoggerFactory.getLogger(UserDao.class);

	private DBClient client;

	public UserDao(DBClient dbClient) {
		this.client = dbClient;
	}

	public void updatePassword(String password, int id) throws FtdException {
		String sql = "update user set password=? where user_id=?";
		try {
			client.executeUpdate(sql, MD5Util.getMD5String(password), id);
		} catch (SQLException e) {
			throw new FtdException(e);
		}
	}

	public User select(String username, String password) {
		String sql = String.format(
				"select * from user where user_name='%s' and password='%s'",
				SQLFilter.sql_inj(username),
				MD5Util.getMD5String(SQLFilter.sql_inj(password)));

		CachedRowSet rs = null;

		try {
			rs = client.executeQuery(sql);
			if (rs.next()) {
				User u = new User();
				u.setUserId(rs.getInt("user_id"));
				u.setUsername(rs.getString("user_name"));
				return u;
			}
		} catch (Exception e) {

		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e1) {
					logger.error(ExceptionUtils.getStackTrace(e1));
				}
		}
		return null;
	}
}
