package net.minegusta.mglib.configs;

import org.bukkit.configuration.file.FileConfiguration;

public abstract class ConfigurationModel {

	public ConfigurationModel(){}

	private FileConfiguration conf;

	public void init(FileConfiguration conf)
	{
		this.conf = conf;
	}

	public abstract void onLoad(FileConfiguration conf);

	public abstract void onSave(FileConfiguration conf);

	public FileConfiguration getConfig() {
		return conf;
	}

}
