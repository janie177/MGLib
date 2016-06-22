package net.minegusta.mglib.particles;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class ParticleUtil {

	public static MovingParticleEffect createNewMovingParticle(int duration, Effect effect, Location location, double blocksPerSecond, Location target)
	{
		return new MovingParticleEffect(duration, effect, location, blocksPerSecond, target);
	}

	public static TargetingParticleEffect createNewTargetingParticle(int duration, Effect effect, Location location, double blocksPerSecond, Entity target)
	{
		return new TargetingParticleEffect(duration, effect, location, blocksPerSecond, target.getLocation(), target);
	}
}
