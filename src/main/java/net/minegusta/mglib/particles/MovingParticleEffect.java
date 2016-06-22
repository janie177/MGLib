package net.minegusta.mglib.particles;

import org.bukkit.Effect;
import org.bukkit.Location;

public class MovingParticleEffect extends ParticleEffect{

	public MovingParticleEffect(int duration, Effect effect, Location location, double blocksPerSecond, Location target) {
		super(duration, effect, location, blocksPerSecond, target);
	}

	@Override
	public void extra() {
		//Nothing
	}
}
