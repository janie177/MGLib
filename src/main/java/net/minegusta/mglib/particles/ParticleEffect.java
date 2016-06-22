package net.minegusta.mglib.particles;

import net.minegusta.mglib.main.Main;
import net.minegusta.mglib.utils.EffectUtil;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.util.Vector;

public abstract class ParticleEffect {

	protected int duration;
	protected Effect effect;
	protected Location location;
	protected double blocksPerSecond;
	protected Vector direction;

	public ParticleEffect(int duration, Effect effect, Location location, double blocksPerSecond, Location target) {
		this.duration = duration;
		this.effect = effect;
		this.location = location.clone();
		this.blocksPerSecond = blocksPerSecond;
		this.direction = target.toVector().subtract(location.toVector()).normalize().multiply(blocksPerSecond/10);

		Bukkit.broadcastMessage("X: " + direction.getX() + " Y: " + direction.getY() + " Z: " + direction.getZ());

		start();
	}

	public abstract void extra();

	private void updateLocation()
	{
		location.add(direction);
	}

	private void start()
	{
		for(int i = 0; i < duration; i += 2)
		{
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), ()->
			{
				EffectUtil.playParticle(location, effect);
				extra();
				updateLocation();
			}, i);
		}
	}
}
