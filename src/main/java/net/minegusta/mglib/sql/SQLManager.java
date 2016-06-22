package net.minegusta.mglib.sql;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

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

	public static SQLManager create(String databaseName, String user, String password, String url)
	{
		return new SQLManager(databaseName, user, password, url);
	}

	public static SQLManager create(String databaseName, String user, String password, String url, String driver)
	{
		return new SQLManager(databaseName, user, password, url, driver);
	}

	//--// Getting the connection //--//

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

	public boolean createTable(String table, String columns) {
		String sqlCreate = "CREATE TABLE IF NOT EXISTS " + table + " " + columns;

		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			stmt.execute(sqlCreate);

			stmt.close();
			conn.close();

		} catch (Exception e) {
			return false;
		}
		return true;
	}

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

	public Optional<ResultSet> executeQuery(String statement)
	{
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet set = stmt.executeQuery(statement);
			stmt.close();
			conn.close();

			return Optional.of(set);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}


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
