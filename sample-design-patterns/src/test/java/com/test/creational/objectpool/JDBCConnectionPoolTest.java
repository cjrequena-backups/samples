package com.test.creational.objectpool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.design.patterns.creational.objectpool.JDBCConnectionPool;

public class JDBCConnectionPoolTest {

	@Test
	public void jdbcConnectionPoolTest() {
		// Create the ConnectionPool:
		JDBCConnectionPool pool = new JDBCConnectionPool("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/Chinook", "root", "root");

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
	}

	@SuppressWarnings("static-access")
	@Test
	public void jdbcConnectionPoolThreadTest() {

		try {
			JDBCConnectionRunnableThread rt = new JDBCConnectionRunnableThread();

			Thread[] threads = new Thread[10];
			for (int i = 0; i < threads.length; i++) {
				threads[i] = new Thread(rt);
				threads[i].start();
				threads[i].sleep(100);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
