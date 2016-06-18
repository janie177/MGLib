package net.minegusta.mglib.main;

import net.minegusta.mglib.tasks.TaskManager;
import net.minegusta.mglib.vault.PEXUtil;
import net.minegusta.mglib.worldguard.WorldGuardHelper;
import net.minegusta.mglib.worldguard.WorldGuardUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	@Override
	public void onEnable()
	{
		Bukkit.getLogger().info("--[=== - - - - -|~~~~~~~~~~~~~~~~~~~~~~~~|- - - - - ===]--");
		Bukkit.getLogger().info("--[=== - - - - -| MGLib has been enabled |- - - - - ===]--");
		Bukkit.getLogger().info("--[=== - - - - -|________________________|- - - - - ===]--");

		if(Bukkit.getPluginManager().isPluginEnabled("WorldGuard"))
		{
			WorldGuardUtil.WG_ENABLED = true;
			WorldGuardHelper.init();
		}

		if(Bukkit.getPluginManager().isPluginEnabled("PermissionsEx"))
		{
			PEXUtil.PEX_ENABLED = true;
		}
	}

	@Override
	public void onDisable()
	{
		//Stop all running tasks
		TaskManager.getTaskIds().stream().forEach(TaskManager::removeTask);

	}

}
