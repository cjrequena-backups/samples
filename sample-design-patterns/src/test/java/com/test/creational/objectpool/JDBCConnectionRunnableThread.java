package com.test.creational.objectpool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.design.patterns.creational.objectpool.JDBCConnectionPool;

public class JDBCConnectionRunnableThread implements Runnable {

	private JDBCConnectionPool pool;
	public static int count = 0;

	public JDBCConnectionRunnableThread() {
		// Create the ConnectionPool:
		pool = JDBCConnectionPool.getInstance();
	}

	@Override
	public void run() {
		try {

			for (int i = 0; i <= 10; i++) {
				// Get a connection:
				Connection con = pool.checkOut();
				try {
					Statement statement = con.createStatement();
					// execute create SQL stetement
					ResultSet rs = statement.executeQuery("SELECT * FROM Album");

					while (rs.next()) {
						Integer id = rs.getInt("AlbumId");
						String title = rs.getString("Title");
						System.out.println("ID " + id + " TITLE " + title);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// Return the connection:
				pool.checkIn(con);
			}
			Thread.sleep(100);
		} catch (InterruptedException iex) {
			System.out.println("Exception in thread: " + iex.getMessage());
		}

	}

}
