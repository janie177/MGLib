package net.minegusta.mglib.sql;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.Statement;

public class SQLManager {
	/**
	 * Make an instance of thi class to create a database connection pool manager.
	 * Then simply call upon the getConnection method and insert SQL statements.
	 */


	private String password;
	private String user;
	private String driver;
	private String url;
	private String databaseName;

	private ComboPooledDataSource cpds = new ComboPooledDataSource();

	private SQLManager(String databaseName, String user, String password, String url, String driver)
	{
		this.user = user;
		this.password = password;
		this.url = url;
		this.driver = driver;
		this.databaseName = databaseName;

		//Database pool stuff
		initDB();

	}

	private SQLManager(String databaseName, String user, String password, String url)
	{
		this.user = user;
		this.password = password;
		this.url = url;
		this.driver = "com.mysql.jdbc.Driver";
		this.databaseName = databaseName;

		//Database pool stuff
		initDB();

	}

	private void initDB()
	{
		try {
			SQLUtil.createDB(user, password, url, databaseName);

			cpds.setDriverClass(driver);
			cpds.setJdbcUrl(url + databaseName);
			cpds.setUser(user);
			cpds.setPassword(password);
		} catch (Exception e){
			Bukkit.getLogger().info("[MGLib] Error while initializing connection pool for database.");
			e.printStackTrace();
			cpds.close();
		}
	}

	/**
	 * Create a new SQL manager instance.
	 * @param databaseName The database to connect to.
	 * @param user The user to connect with.
	 * @param password The password to connect with.
	 * @param url The url to connect with.
	 * @return A new SQL manager which allows for easy data pooling.
	 */
	public static SQLManager create(String databaseName, String user, String password, String url)
	{
		return new SQLManager(databaseName, user, password, url);
	}

	/**
	 * Create a new SQL manager instance.
	 * @param databaseName The database to connect to.
	 * @param user The user to connect with.
	 * @param password The password to connect with.
	 * @param url The url to connect with.
	 * @param driver The driver to connect with.
	 * @return A new SQL manager which allows for easy data pooling.
	 */
	public static SQLManager create(String databaseName, String user, String password, String url, String driver)
	{
		return new SQLManager(databaseName, user, password, url, driver);
	}

	//--// Getting the connection //--//

	/**
	 * Get a connection from the pool. Make sure to manually close everything.
	 * Use this to get ResultSets which will also have to be manually closed.
	 * @return a connection from the pool.
	 */
	public Connection getConnection()
	{
		try {
			return cpds.getConnection();
		} catch (Exception e)
		{
			Bukkit.getLogger().info("[MGLib] Error while getting DB Connection.");
			e.printStackTrace();
		}
		return null;
	}


	//--// Some generic methods //--//

	/**
	 * Create a new table in this database.
	 * @param table The name of the table that is to be created.
	 * @param columns The columns for the table in SQL format. (uuid VARCHAR(60), amount INTEGER, isMaus BOOLEAN, PRIMARY KEY(uuid))
	 * @return True if the creation was successful.
	 */
	public boolean createTable(String table, String columns) {
		String sqlCreate = "CREATE TABLE IF NOT EXISTS " + table + " " + columns;

		return executeStatement(sqlCreate);
	}

	/**
	 * Execute an SQL statement. This will automatically close after finishing.
	 * @param statement The statement to execute.
	 * @return True if successful.
	 */
	public boolean executeStatement(String statement)
	{
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			stmt.execute(statement);

			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


	/**
	 * Close the connection data pool, rendering it useless. Only use this if the SQLManager will not be used at all anymore.
	 */
	public void closePool()
	{
		try {
			cpds.close();
		} catch (Exception e)
		{
			Bukkit.getLogger().info("[MGLib] Error while trying to close SQL Database pool.");
			e.printStackTrace();
		}
	}

}
