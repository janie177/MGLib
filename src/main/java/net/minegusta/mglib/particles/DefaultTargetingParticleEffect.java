package net.minegusta.mglib.particles;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class DefaultTargetingParticleEffect extends AbstractTargetingParticleEffect {
	public DefaultTargetingParticleEffect(int duration, Effect effect, Location location, double blocksPerSecond, Location target, Entity targetedEntity, boolean removeOnHit, boolean removeOnBlock, Effect particleOnHit) {
		super(duration, effect, location, blocksPerSecond, target, targetedEntity, removeOnHit, removeOnBlock, particleOnHit);
	}

	@Override
	public void onHit(Entity targetedEntity) {
		//Nothing
	}
}
