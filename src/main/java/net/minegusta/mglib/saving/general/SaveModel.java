package net.minegusta.mglib.saving.general;

import org.bukkit.configuration.file.FileConfiguration;

public abstract class SaveModel {

	private FileConfiguration conf;

	public abstract void onLoad(FileConfiguration conf);

	public void init(FileConfiguration conf)
	{
		this.conf = conf;
	}

	public FileConfiguration getConf() {
		return conf;
	}

	public abstract void updateConf(FileConfiguration conf);
}
