package net.minegusta.mglib.saving.general;

import org.bukkit.configuration.file.FileConfiguration;

public abstract class SaveModel {

	private FileConfiguration conf;
	private String key;

	public abstract void onLoad(FileConfiguration conf);

	public void init(FileConfiguration conf, String key)
	{
		this.conf = conf;
		this.key = key;
	}

	public FileConfiguration getConf() {
		return conf;
	}

	public abstract void updateConf(FileConfiguration conf);

	public String getKey()
	{
		return key;
	}
}
