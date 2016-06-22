package net.minegusta.mglib.saving.mgplayer;

import org.bukkit.configuration.file.FileConfiguration;

public abstract class MGPlayerModel {

	private FileConfiguration conf;

	public abstract void onLoad();

	public void init(FileConfiguration conf)
	{
		this.conf = conf;
	}

	public FileConfiguration getConf() {
		return conf;
	}

	public abstract void updateConf();
}
