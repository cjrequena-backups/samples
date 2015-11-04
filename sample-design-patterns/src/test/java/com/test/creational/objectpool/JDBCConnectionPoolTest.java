package com.test.creational.objectpool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;

import com.design.patterns.creational.objectpool.JDBCConnectionPool;

/*
 * 
 * The object pool pattern is a software creational design pattern that uses a set of initialized objects kept ready to use – a "pool" – rather than
 * allocating and destroying them on demand. A client of the pool will request an object from the pool and perform operations on the returned object. When the client has finished, it returns the object to the pool rather than destroying it; this can be done manually or automatically.
 * Object pools are primarily used for performance: in some circumstances, object pools significantly improve performance. Object pools complicate 
 * object lifetime, as objects obtained from and return to a pool are not actually created or destroyed at this time, and thus require care in 
 * implementation.
 * 
 */
public class JDBCConnectionPoolTest {

	Connection connection;
	JDBCConnectionPool pool;

	@Before
	public void setup() {
		
		try {
			pool = JDBCConnectionPool.getInstance();
			connection = pool.checkOut();
			Statement statement = connection.createStatement();
			statement.execute("CREATE TABLE sample (id INTEGER PRIMARY KEY, firstName VARCHAR(50), lastName VARCHAR(50), email  VARCHAR(50));");
			statement.execute("INSERT INTO sample VALUES (1, 'Carlos','Requena', 'crequena@hotelbeds.com');");
			pool.release(connection);
		} catch (SQLException e) {
			//e.printStackTrace();
		}
	}

	@Test
	public void jdbcConnectionPoolTest() {
		// Create the ConnectionPool:
		pool = JDBCConnectionPool.getInstance();

		for (int i = 0; i <= 10; i++) {
			// Get a connection:
			connection = pool.checkOut();
			try {
				Statement statement = connection.createStatement();
				// execute create SQL stetement
				ResultSet rs = statement.executeQuery("SELECT * FROM sample");

				while (rs.next()) {
					Integer id = rs.getInt("id");
					String firstName = rs.getString("firstName");
					String lastName = rs.getString("lastName");
					String email = rs.getString("email");
					System.out.println("email " + email);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Return the connection:
			pool.release(connection);
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
