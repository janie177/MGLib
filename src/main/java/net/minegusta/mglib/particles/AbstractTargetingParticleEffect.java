package net.minegusta.mglib.particles;

import net.minegusta.mglib.utils.EffectUtil;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public abstract class AbstractTargetingParticleEffect extends ParticleEffect{

	private Entity targetedEntity;
	private boolean removeOnHit;
	private boolean removeOnBlock;
	private Effect particleOnHit;

	public AbstractTargetingParticleEffect(int duration, Effect effect, Location location, double blocksPerSecond, Location target, Entity targetedEntity, boolean removeOnHit, boolean removeOnBlock, Effect particleOnHit) {
		super(duration, effect, location, blocksPerSecond, target);
		this.targetedEntity = targetedEntity;
		this.removeOnBlock = removeOnBlock;
		this.removeOnHit = removeOnHit;
		this.particleOnHit = particleOnHit;
	}

	@Override
	public void extra() {
		if(targetedEntity.isValid() && location.getWorld().getName().equals(targetedEntity.getWorld().getName()))
		{
			this.direction = targetedEntity.getLocation().add(0,1,0).toVector().subtract(location.toVector()).normalize().multiply(blocksPerSecond/10);
			if((removeOnHit && location.distance(targetedEntity.getLocation()) < 1.5) || (removeOnBlock && !location.getBlock().getType().isTransparent()))
			{
				if(location.distance(targetedEntity.getLocation()) < 1.5)
				{
					onHit(targetedEntity);
				}
				ParticleRegistry.remove(this);
				if(particleOnHit != null) {
					EffectUtil.playParticle(location, particleOnHit, 2, 2, 2, 0.5F, 26, 40);
				}
			}
		}
	}

	public abstract void onHit(Entity targetedEntity);
}
