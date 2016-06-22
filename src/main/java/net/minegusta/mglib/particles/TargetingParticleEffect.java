package net.minegusta.mglib.particles;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class TargetingParticleEffect extends ParticleEffect{

	private Entity targetedEntity;

	public TargetingParticleEffect(int duration, Effect effect, Location location, double blocksPerSecond, Location target, Entity targetedEntity) {
		super(duration, effect, location, blocksPerSecond, target);
		this.location = targetedEntity.getLocation();
		this.targetedEntity = targetedEntity;

	}

	@Override
	public void extra() {
		if(targetedEntity.isValid() && location.getWorld().getName().equals(targetedEntity.getWorld().getName()))
		{
			this.location = targetedEntity.getLocation();
		}
	}
}
