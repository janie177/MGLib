package net.minegusta.mglib.particles;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class ParticleUtil {

	public static MovingParticleEffect createNewMovingParticle(int duration, Effect effect, Location location, double blocksPerSecond, Location target)
	{
		return new MovingParticleEffect(duration, effect, location, blocksPerSecond, target);
	}

	public static DefaultTargetingParticleEffect createNewTargetingParticle(int duration, Effect effect, Location location, double blocksPerSecond, Entity target, boolean removeOnHit, boolean removeOnBlock, Effect impactEffect)
	{
		return new DefaultTargetingParticleEffect(duration, effect, location, blocksPerSecond, target.getLocation(), target, removeOnHit, removeOnBlock, impactEffect);
	}
}
