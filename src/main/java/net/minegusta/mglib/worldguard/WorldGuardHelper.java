package net.minegusta.mglib.worldguard;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class WorldGuardHelper {

	private static WorldGuardPlugin WorldGuardInstance = null;

	public static void init()
	{
		WorldGuardInstance = (WorldGuardPlugin) Bukkit.getPluginManager().getPlugin("WorldGuard");
	}

	public static boolean canPVP(Entity e) {
		if (!(e instanceof LivingEntity)) return false;
		Location loc = e.getLocation();
		ApplicableRegionSet set = WorldGuardPlugin.inst().getRegionManager(e.getWorld()).getApplicableRegions(loc);
		if (set.size() > 0) {
			for (ProtectedRegion r : set.getRegions()) {
				if (r.getFlags().containsKey(DefaultFlag.PVP) && r.getFlag(DefaultFlag.PVP) == StateFlag.State.DENY) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean canGetDamage(Entity e) {
		Location loc = e.getLocation();
		ApplicableRegionSet set = WorldGuardPlugin.inst().getRegionManager(e.getWorld()).getApplicableRegions(loc);
		if (set.size() > 0) {
			for (ProtectedRegion r : set.getRegions()) {
				if (r.getFlags().containsKey(DefaultFlag.INVINCIBILITY) && r.getFlag(DefaultFlag.INVINCIBILITY) == StateFlag.State.ALLOW) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean canPearl(Player p) {
		Location loc = p.getLocation();
		ApplicableRegionSet set = WorldGuardPlugin.inst().getRegionManager(p.getWorld()).getApplicableRegions(loc);
		if (set.size() > 0) {
			for (ProtectedRegion r : set.getRegions()) {
				if (r.getFlags().containsKey(DefaultFlag.ENDERPEARL) && r.getFlag(DefaultFlag.ENDERPEARL) == StateFlag.State.DENY) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean canBuild(Player p) {
		return canBuild(p, p.getLocation());
	}

	public static boolean canBuild(Player p, Location l) {
		return WorldGuardInstance.canBuild(p, l);
	}

	public static boolean canFightEachother(Entity e1, Entity e2) {
		return canPVP(e1) && canGetDamage(e1) && canPVP(e2) && canGetDamage(e2);
	}


}
