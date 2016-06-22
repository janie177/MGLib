package net.minegusta.mglib.saving.mgplayer;

import com.google.common.collect.Maps;
import net.minegusta.mglib.tasks.Task;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.concurrent.ConcurrentMap;

public class PlayerSaveManager<T extends MGPlayerModel> {



	/**
	 * Make an instance of this class to create a save manager for players.
	 *
	 * The save manager will handle files and automatically save at an interval.
	 * Loading and unloading will happen on player join and quit, but can also be manually done at other times.
	 */


	private Plugin plugin;
	private Class<T> playerClass;
	private Task savetask = new Task();

	public PlayerSaveManager(Plugin plugin, Class<T> playerClass, int saveInterval)
	{
		this.plugin = plugin;
		this.playerClass = playerClass;
		if(saveInterval > 0) savetask.start(Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this::saveAllMGPlayers, 20 * saveInterval, 20 * saveInterval));
		Bukkit.getPluginManager().registerEvents(new PlayerSaveListener(this), plugin);
	}

	private ConcurrentMap<String, T> data = Maps.newConcurrentMap();

	public T getMGPlayer(Player player)
	{
		String uuid = player.getUniqueId().toString();
		if(data.containsKey(uuid))
		{
			return data.get(uuid);
		}
		loadMGPlayer(player);
		return data.get(uuid);
	}

	public void loadMGPlayer(Player player)
	{
		try {
			T mgp = playerClass.newInstance();
			mgp.init(PlayerFileHandler.getFile(plugin, player.getUniqueId()));
			mgp.onLoad();
			data.put(player.getUniqueId().toString(), mgp);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void saveMGPlayer(Player player)
	{
		MGPlayerModel mgp = getMGPlayer(player);
		mgp.updateConf();
		PlayerFileHandler.save(plugin, player.getUniqueId(), mgp.getConf());
	}

	public void saveAllMGPlayers()
	{
		Bukkit.getOnlinePlayers().forEach(this::saveMGPlayer);
	}

	public void unloadPlayer(Player player)
	{
		saveMGPlayer(player);
		String uuid = player.getUniqueId().toString();
		if(data.containsKey(uuid)) data.remove(uuid);
	}
}
