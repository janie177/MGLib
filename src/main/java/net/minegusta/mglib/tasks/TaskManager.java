package net.minegusta.mglib.tasks;

import com.google.common.collect.Maps;
import org.bukkit.Bukkit;

import java.util.Collection;
import java.util.concurrent.ConcurrentMap;

public class TaskManager {
	private static ConcurrentMap<Integer, Task> tasks = Maps.newConcurrentMap();

	protected static void addTask(Task task)
	{
		tasks.put(task.getId(), task);
	}

	public static void removeTask(int id)
	{
		try {
			if(tasks.containsKey(id))tasks.remove(id);
		} catch (Exception ignored)
		{
			Bukkit.getLogger().info("[MGLib] Unable to stop task. Possibly already removed.");
		}

	}

	public static Collection<Task> getTasks()
	{
		return tasks.values();
	}

	public static Collection<Integer> getTaskIds()
	{
		return tasks.keySet();
	}
}

