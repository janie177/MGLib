package net.minegusta.mglib.sql;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.Statement;

public class SQLManager {

	String password;
	String user;
	String driver;
	String url;
	String databaseName;

	ComboPooledDataSource cpds = new ComboPooledDataSource();


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
			cpds.setJdbcUrl("jdbc:mysql://localhost:3306/" + databaseName);
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

		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public void closePool()
	{
		try {
			cpds.close();
		} catch (Exception e)
		{
			Bukkit.getLogger().info("[MGLib] Error while trying to close SQL Database");
			e.printStackTrace();
		}
	}

}
