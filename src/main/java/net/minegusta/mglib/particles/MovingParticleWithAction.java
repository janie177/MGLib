package net.minegusta.mglib.particles;

import com.google.common.collect.Lists;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class MovingParticleWithAction extends ParticleEffect {

	private List<Entity> targets = Lists.newArrayList();
	private int interval = 20;
	private int counter = 0;
	private boolean blocks;
	private String casterUuid;

	public MovingParticleWithAction(int duration, Effect effect, Location location, double blocksPerSecond, Location target, Player caster, boolean blocks) {
		super(duration, effect, location, blocksPerSecond, target);
		this.blocks = blocks;
		this.casterUuid =caster.getUniqueId().toString();
		scanForEntities();
	}

	public String getCasterUuid()
	{
		return casterUuid;
	}

	@Override
	public void extra() {

		//Check for hits
		if(counter % 2 == 0)
		{
			if(blocks && !location.getBlock().getType().isTransparent())
			{
				end();
				return;
			}
			for(Entity ent : targets)
			{
				if(ent.getLocation().distance(location) < 1)
				{
					end();
					break;
				}
			}
		}


		counter++;
		if(counter >= interval)
		{
			scanForEntities();
			counter = 0;
		}
	}

	private void end()
	{
		onHit();
		ParticleRegistry.remove(this);
	}

	private void scanForEntities()
	{
		targets.clear();
		location.getWorld().getLivingEntities().stream().filter(ent -> ent.getLocation().distance(location) < 20).forEach(targets::add);
	}

	public abstract void onHit();
}
