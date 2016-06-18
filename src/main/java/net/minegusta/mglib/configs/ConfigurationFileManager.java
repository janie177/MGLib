package net.minegusta.mglib.configs;

import net.minegusta.mglib.tasks.Task;
import net.minegusta.mglib.yml.YamlUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class ConfigurationFileManager<T extends ConfigurationModel> {

	private Plugin plugin;
	private Task savetask = new Task();
	private T model;
	private String fileName;

	public ConfigurationFileManager(Plugin plugin, Class<T> configClass, int saveInterval, String fileName)
	{
		this.plugin = plugin;
		this.fileName = fileName;
		savetask.start(Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this::saveConfig, 20 * saveInterval, 20 * saveInterval));
		try {
			model = configClass.newInstance();
			model.init(YamlUtil.getConfiguration(plugin, "", fileName + ".yml"));
			getConfigClass().onLoad(getConfigClass().getConfig());
		} catch (Exception e)
		{
			Bukkit.getLogger().info("[MGLib] Error occurred while trying ot initialized config file.");
			e.printStackTrace();
		}

		if(saveInterval > 0) savetask.start(Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this::saveConfig, 20 * saveInterval, 20 * saveInterval));
	}

	public void reloadFromFile()
	{
		model.init(YamlUtil.getConfiguration(plugin, "", fileName + ".yml"));
		getConfigClass().onLoad(getConfigClass().getConfig());
	}

	public T getConfigClass()
	{
		return model;
	}

	public void saveConfig()
	{
		getConfigClass().onSave(getConfigClass().getConfig());
		YamlUtil.saveFile(plugin, "", fileName + ".yml", getConfigClass().getConfig());
	}
}
