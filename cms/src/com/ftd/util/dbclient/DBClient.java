package com.ftd.util.dbclient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.rowset.CachedRowSetImpl;

public class DBClient
{
	private static Logger logger = LoggerFactory.getLogger(DBClient.class);

	private DBConnectionPool connectionPool;

	public DBClient(String jdbcUrl, String userName, String password, int dbWriterPartNum, int dbCacheWriterPartNum, int maxConnections, int minConnections)
			throws SQLException, ClassNotFoundException
	{
		this.connectionPool = new DBConnectionPool(jdbcUrl, userName, password, maxConnections, minConnections);
	}

	public DBClient(String jdbcUrl, String userName, String password) throws SQLException, ClassNotFoundException
	{
		this.connectionPool = new DBConnectionPool(jdbcUrl, userName, password);
	}

	public int executeUpdate(String command) throws SQLException
	{
		Connection connection = null;
		Statement statement = null;
		int affectedLine = 0;
		try
		{
			connection = this.connectionPool.getConnection();
			statement = connection.createStatement();
			logger.info(statement.toString());
			affectedLine = statement.executeUpdate(command);
		}
		finally
		{
			if (statement != null)
			{
				try
				{
					statement.close();
				}
				catch (Exception e)
				{

				}
			}

			if (connection != null)
			{
				try
				{
					this.connectionPool.closeConnection(connection);
				}
				catch (Exception e)
				{

				}
			}
		}
		return affectedLine;
	}

	public int executeUpdate(String command, Object... objs) throws SQLException
	{
		Connection connection = null;
		PreparedStatement statement = null;
		int affectedLine = 0;
		try
		{
			connection = this.connectionPool.getConnection();
			statement = connection.prepareStatement(command);
			for (int i = 0; i < objs.length; i++)
			{
				if (objs[i] instanceof Integer)
					statement.setInt(i + 1, (Integer) objs[i]);
				if (objs[i] instanceof Boolean)
					statement.setBoolean(i + 1, (boolean) objs[i]);
				else
					statement.setString(i + 1, (objs[i] == null) ? null : objs[i].toString());
			}
			logger.info(statement.toString());
			affectedLine = statement.executeUpdate();
		}
		finally
		{
			if (statement != null)
			{
				try
				{
					statement.close();
				}
				catch (Exception e)
				{

				}
			}

			if (connection != null)
			{
				try
				{
					this.connectionPool.closeConnection(connection);
				}
				catch (Exception e)
				{

				}
			}
		}
		return affectedLine;
	}

	public CachedRowSet executeQuery(String sqlCommand) throws SQLException
	{
		Connection connection = null;
		Statement statement = null;
		CachedRowSet cachedRowSet = null;
		try
		{
			connection = this.connectionPool.getConnection();
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sqlCommand);
			logger.info(sqlCommand);
			cachedRowSet = new CachedRowSetImpl();
			cachedRowSet.populate(resultSet);
		}
		finally
		{
			if (statement != null)
			{
				try
				{
					statement.close();
				}
				catch (Exception e)
				{
				}
			} 

			if (connection != null)
			{
				try
				{
					this.connectionPool.closeConnection(connection);
				}
				catch (Exception e)
				{
				}
			}
		}
		return cachedRowSet;
	}

	public long executeInsertAndReturnIncrementID(String command) throws SQLException
	{
		Connection connection = null;
		Statement statement = null;
		long autoIncrementKey = 0;
		try
		{
			connection = this.connectionPool.getConnection();
			statement = connection.createStatement();
			logger.info(statement.toString());
			statement.executeUpdate(command, Statement.RETURN_GENERATED_KEYS);

			ResultSet resultSet = statement.getGeneratedKeys();

			if (resultSet.next())
			{
				autoIncrementKey = resultSet.getInt(1);
			}
		}
		finally
		{
			if (statement != null)
			{
				try
				{
					statement.close();
				}
				catch (Exception e)
				{
				}
			}

			if (connection != null)
			{
				try
				{
					this.connectionPool.closeConnection(connection);
				}
				catch (Exception e)
				{
				}
			}
		}

		return autoIncrementKey;
	}

	public Connection getConnection() throws SQLException
	{
		return this.connectionPool.getConnection();
	}

	public void returnConnection(Connection connection) throws SQLException
	{
		this.connectionPool.closeConnection(connection);
	}
}
