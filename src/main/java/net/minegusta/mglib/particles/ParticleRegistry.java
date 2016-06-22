package net.minegusta.mglib.particles;

import com.google.common.collect.Maps;
import net.minegusta.mglib.main.Main;
import net.minegusta.mglib.tasks.Task;
import org.bukkit.Bukkit;

import java.util.concurrent.ConcurrentMap;

public class ParticleRegistry {

	private static Task particleTask = new Task();
	private static ConcurrentMap<ParticleEffect, Boolean> effects = Maps.newConcurrentMap();

	static void add(ParticleEffect effect)
	{
		effects.put(effect, true);
	}

	static void remove(ParticleEffect effect)
	{
		if(effects.containsKey(effect)) effects.remove(effect);
	}

	public static void startTask()
	{
		particleTask.start(Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), ()->
		{
			effects.keySet().stream().forEach(ParticleEffect::run);
		}, 2, 2));
	}

	public static void stopTask()
	{
		particleTask.stop();
	}
}
