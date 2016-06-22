package net.minegusta.mglib.particles;

import net.minegusta.mglib.utils.EffectUtil;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.util.Vector;

public abstract class ParticleEffect {

	private int duration;
	private Effect effect;
	private int age;
	protected Location location;
	double blocksPerSecond;
	Vector direction;

	public ParticleEffect(int duration, Effect effect, Location location, double blocksPerSecond, Location target) {
		this.duration = duration;
		this.effect = effect;
		this.location = location.clone();
		this.blocksPerSecond = blocksPerSecond;
		this.direction = target.toVector().subtract(location.toVector()).normalize().multiply(blocksPerSecond/10);
		ParticleRegistry.add(this);
	}

	public abstract void extra();

	private void updateLocation()
	{
		location.add(direction);
	}

	void run()
	{
		EffectUtil.playParticle(location, effect);
		extra();
		updateLocation();
		age += 2;

		if(age >= duration)
		{
			ParticleRegistry.remove(this);
		}
	}
}
