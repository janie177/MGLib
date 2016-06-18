package net.minegusta.mglib.worldguard;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class WorldGuardUtil {
	public static boolean WG_ENABLED = false;


	public static boolean isWorldGuardEnabled()
	{
		return WG_ENABLED;
	}

	public static boolean canPVP(Entity e) {
		if (!WG_ENABLED) return true;
		return WorldGuardHelper.canPVP(e);
	}

	public static boolean canGetDamage(Entity e) {
		if (!WG_ENABLED) return true;
		return WorldGuardHelper.canGetDamage(e);
	}

	public static boolean canPearl(Player p) {
		if (!WG_ENABLED) return true;
		return WorldGuardHelper.canPearl(p);
	}

	public static boolean canBuild(Player p) {
		return canBuild(p, p.getLocation());
	}

	public static boolean canBuild(Player p, Location l) {
		if (!WG_ENABLED) return true;
		return WorldGuardHelper.canBuild(p, l);
	}

	public static boolean canFightEachother(Entity e1, Entity e2) {
		if (!WG_ENABLED) return true;
		return WorldGuardHelper.canPVP(e1) && WorldGuardHelper.canGetDamage(e1) && WorldGuardHelper.canPVP(e2) && WorldGuardHelper.canGetDamage(e2);
	}
}
