package net.minegusta.mglib.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class PlayerUtil {

	public static boolean isInOpenAir(Player p) {
		return LocationUtil.getHighestBlockYAt(p.getLocation()) <= p.getEyeLocation().getY();
	}

	public static boolean isInRain(Player p) {
		return WeatherUtil.isRaining(p.getWorld()) && isInOpenAir(p);
	}

	public static boolean isInWater(Player p) {
		Material mat = p.getLocation().getBlock().getType();
		return mat == Material.WATER || mat == Material.STATIONARY_WATER;
	}

	public static boolean isInLava(Player p) {
		Material mat = p.getLocation().getBlock().getType();
		return mat == Material.LAVA || mat == Material.STATIONARY_LAVA;
	}
}
