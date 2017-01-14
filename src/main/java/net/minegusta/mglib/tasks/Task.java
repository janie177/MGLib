package net.minegusta.mglib.tasks;

import org.bukkit.Bukkit;

public class Task {

	private int id = -1;

	/**
	 * Start a task. Add Bukkit.getScheduler().schedulySync... etc in here as the int.
	 * Tasks are automatically ended on server stop. End the task by calling the .stop(); method.
	 * @param taskId The id for the task. This is given as a bukkit scheduler returned int.
	 */
	public void start(int taskId)
	{
		this.id = taskId;
		TaskManager.addTask(this);
	}

	/**
	 * Stop this task.
	 */
	public void stop()
	{
		if(id != -1)
		{
			Bukkit.getScheduler().cancelTask(id);
			TaskManager.removeTask(id);
		}

	}

	/**
	 * Return the ID of this task.
	 * @return The task ID.
	 */
	public int getId()
	{
		return id;
	}
}
