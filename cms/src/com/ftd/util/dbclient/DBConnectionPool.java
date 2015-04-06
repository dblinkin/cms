package com.ftd.util.dbclient;

import java.sql.Connection;
import java.sql.SQLException;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public final class DBConnectionPool
{
	private int MIN_CONNECTIONS_PER_PARTITION = 5;
	private int MAX_CONNECTIONS_PER_PARTITION = 20;
	private static final int PARTITION_COUNT = 1;

	private BoneCP connectionPool;

	public DBConnectionPool(String jdbcUrl, String userName, String password, int maxConnections, int minConnections) throws SQLException,
			ClassNotFoundException
	{
		this.MAX_CONNECTIONS_PER_PARTITION = maxConnections;
		this.MIN_CONNECTIONS_PER_PARTITION = minConnections;
		this.loadDriver();
		this.initializeConnectionPool(jdbcUrl, userName, password);
	}

	public DBConnectionPool(String jdbcUrl, String userName, String password) throws SQLException, ClassNotFoundException
	{
		this.loadDriver();
		this.initializeConnectionPool(jdbcUrl, userName, password);
	}

	private void loadDriver() throws ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
	}

	private void initializeConnectionPool(String jdbcUrl, String userName, String password) throws SQLException
	{
		BoneCPConfig config = new BoneCPConfig();
		config.setJdbcUrl(jdbcUrl
				+ "?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&socketTimeout=120000");

		config.setUsername(userName);
		config.setPassword(password);
		config.setMinConnectionsPerPartition(this.MIN_CONNECTIONS_PER_PARTITION);
		config.setMaxConnectionsPerPartition(this.MAX_CONNECTIONS_PER_PARTITION);
		config.setPartitionCount(PARTITION_COUNT);
		config.setIdleMaxAgeInSeconds(600);
		config.setIdleConnectionTestPeriodInSeconds(60);
		this.connectionPool = new BoneCP(config);
	}

	public Connection getConnection() throws SQLException
	{
		Connection c = this.connectionPool.getConnection();
		return c;
	}

	public void closeConnection(Connection connection) throws SQLException
	{
		connection.close();
	}
}
