package net.minegusta.mglib.saving.mgplayer;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.UUID;

public abstract class MGPlayerModel {

	private FileConfiguration conf;
	private String uuid;

	public abstract void onLoad(FileConfiguration conf);

	public void init(FileConfiguration conf, String uuid)
	{
		this.conf = conf;
		this.uuid = uuid;
	}

	public FileConfiguration getConf() {
		return conf;
	}

	public String getUuidString()
	{
		return uuid;
	}

	public UUID getUuid()
	{
		return UUID.fromString(uuid);
	}

	public abstract void updateConf(FileConfiguration conf);
}
