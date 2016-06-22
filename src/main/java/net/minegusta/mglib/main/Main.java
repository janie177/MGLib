package net.minegusta.mglib.main;

import net.minegusta.mglib.gui.GUIListener;
import net.minegusta.mglib.gui.GUITask;
import net.minegusta.mglib.sql.SQLManager;
import net.minegusta.mglib.tasks.TaskManager;
import net.minegusta.mglib.permissionsex.PEXUtil;
import net.minegusta.mglib.worldguard.WorldGuardHelper;
import net.minegusta.mglib.worldguard.WorldGuardUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	private static Plugin plugin;

	@Override
	public void onEnable()
	{
		Bukkit.getLogger().info("--[=== - - - - -|~~~~~~~~~~~~~~~~~~~~~~~~|- - - - - ===]--");
		Bukkit.getLogger().info("--[=== - - - - -| MGLib has been enabled |- - - - - ===]--");
		Bukkit.getLogger().info("--[=== - - - - -|________________________|- - - - - ===]--");

		plugin = this;

		if(Bukkit.getPluginManager().isPluginEnabled("WorldGuard"))
		{
			WorldGuardUtil.WG_ENABLED = true;
			WorldGuardHelper.init();
		}

		if(Bukkit.getPluginManager().isPluginEnabled("PermissionsEx")) {
			PEXUtil.PEX_ENABLED = true;
		}

		//Listen for gui clicks
		Bukkit.getPluginManager().registerEvents(new GUIListener(), this);

		//Enable task for inventory animation
		GUITask.start();

		//TODO REMOVE
		SQLManager manager = SQLManager.create("Minegusta", "root", "", "jdbc:mysql://localhost:3306/");
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, ()->
		{
			manager.createTable("boopdoop", "(amount INTEGER, name VARCHAR(40), PRIMARY_KEY(name))");
			manager.executeStatement("REPLACE INTO boopdoop (amount, name) VALUES ('16', 'hallo daar ;D')");
		}, 300);
	}

	@Override
	public void onDisable()
	{
		//Stop all running tasks
		TaskManager.getTaskIds().stream().forEach(TaskManager::removeTask);

		//Stop inventory animation task
		GUITask.stop();

	}

	public static Plugin getPlugin()
	{
		return plugin;
	}

}
