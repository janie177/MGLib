package net.minegusta.mglib.configs;

import org.bukkit.configuration.file.FileConfiguration;

public abstract class ConfigurationModel {

	public ConfigurationModel(){}

	private FileConfiguration conf;

	public void init(FileConfiguration conf)
	{
		this.conf = conf;
	}

	/**
	 * This method is ran when the plugin loads.
	 * Use 'conf' to access the file.
	 * @param conf The config instance.
	 */
	public abstract void onLoad(FileConfiguration conf);

	/**
	 * Save to config.
	 * @param conf The instance of the config that should be changed when saving.
	 */
	public abstract void onSave(FileConfiguration conf);

	public FileConfiguration getConfig() {
		return conf;
	}

}
