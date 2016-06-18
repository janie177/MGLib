package net.minegusta.mglib.tasks;

import org.bukkit.Bukkit;

public class Task {
	private int id = -1;

	public void start(int taskId)
	{
		this.id = taskId;
		TaskManager.addTask(this);
	}

	public void stop()
	{
		if(id != -1)
		{
			Bukkit.getScheduler().cancelTask(id);
			TaskManager.removeTask(id);
		}

	}

	public int getId()
	{
		return id;
	}
}
