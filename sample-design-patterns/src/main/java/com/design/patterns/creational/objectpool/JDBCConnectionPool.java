package com.design.patterns.creational.objectpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnectionPool extends ObjectPool<Connection> {

	private String dsn, usr, pwd,driver;

	private JDBCConnectionPool() {
		super();
		this.driver = "org.h2.Driver";
		this.dsn = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE";
		this.usr = "sa";
		this.pwd = "";
		
		try {
			Class.forName(driver).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static class JDBCConnectionPoolHolder {
		private static final JDBCConnectionPool INSTANCE = new JDBCConnectionPool();
	}

	public synchronized static JDBCConnectionPool getInstance() {
		return JDBCConnectionPoolHolder.INSTANCE;
	}


	@Override
	protected Connection create() {
		try {
			return (DriverManager.getConnection(dsn, usr, pwd));
		} catch (SQLException e) {
			e.printStackTrace();
			return (null);
		}
	}

	@Override
	public void expire(Connection o) {
		try {
			((Connection) o).close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean validate(Connection o) {
		try {
			return (!((Connection) o).isClosed());
		} catch (SQLException e) {
			e.printStackTrace();
			return (false);
		}
	}

}
