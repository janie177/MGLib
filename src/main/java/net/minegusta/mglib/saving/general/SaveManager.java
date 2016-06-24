package net.minegusta.mglib.saving.general;

import com.google.common.collect.Maps;
import net.minegusta.mglib.tasks.Task;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.Collection;
import java.util.concurrent.ConcurrentMap;

public class SaveManager<T extends SaveModel> {


	/**
	 * Make an instance of this class to create a save manager.
	 *
	 * The save manager will handle files and automatically save at an interval. Loading and unloading has to be manually done.
	 * For player saves, use the PlayerSaveManager class instead.
	 */


	private Plugin plugin;
	private Class<T> saveClass;
	private Task savetask = new Task();
	private String path;

	public SaveManager(Plugin plugin, Class<T> saveClass, String path, int saveInterval)
	{
		this.plugin = plugin;
		this.saveClass = saveClass;
		this.path = path;
		if(saveInterval > 0) savetask.start(Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this::saveAll, 20 * saveInterval, 20 * saveInterval));
	}

	private ConcurrentMap<String, T> data = Maps.newConcurrentMap();

	public T get(String key)
	{
		if(data.containsKey(key.toLowerCase()))
		{
			return data.get(key.toLowerCase());
		}
		load(key);
		return data.get(key.toLowerCase());
	}

	public Collection<T> getAll()
	{
		return data.values();
	}

	public void load(String key)
	{
		try {
			T save = saveClass.newInstance();
			save.init(FileHandler.getFile(plugin, key.toLowerCase(), path), key);
			save.onLoad(save.getConf());
			data.put(key.toLowerCase(), save);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void save(String key)
	{
		SaveModel save = get(key);
		save.updateConf(save.getConf());
		FileHandler.save(plugin, key.toLowerCase(), path, save.getConf());
	}

	public void saveAll()
	{
		data.keySet().stream().forEach(this::save);
	}

	public void unload(String key)
	{
		save(key);
		if(data.containsKey(key.toLowerCase())) data.remove(key);
	}
}
