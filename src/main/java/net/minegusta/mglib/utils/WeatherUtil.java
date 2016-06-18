package net.minegusta.mglib.utils;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Biome;

public class WeatherUtil {

	public static boolean isHell(Location loc) {
		return loc.getBlock().getBiome() == Biome.HELL;
	}

	public static boolean isEnd(Location loc) {
		return loc.getBlock().getBiome() == Biome.SKY;
	}

	public static boolean isOverWorld(Location loc) {
		return !isEnd(loc) && !isHell(loc);
	}

	public static boolean isRaining(World w) {
		return w.hasStorm();
	}

	public static boolean isFullMoon(World w) {
		return (w.getFullTime() / 24000) % 8 == 0;
	}

	public static MoonPhase getMoonPhase(World w) {
		int day = (int) (w.getFullTime() / 24000) % 8;
		MoonPhase phase;

		switch (day) {
			case 7:
				phase = MoonPhase.QUARTER;
				break;
			case 6:
				phase = MoonPhase.HALF;
				break;
			case 5:
				phase = MoonPhase.QUARTER;
				break;
			case 4:
				phase = MoonPhase.NEW;
				break;
			case 3:
				phase = MoonPhase.QUARTER;
				break;
			case 2:
				phase = MoonPhase.HALF;
				break;
			case 1:
				phase = MoonPhase.QUARTER;
				break;
			case 0:
				phase = MoonPhase.FULL;
				break;
			default:
				phase = MoonPhase.NEW;
				break;
		}
		return phase;
	}

	public enum MoonPhase {
		FULL, QUARTER, HALF, NEW
	}
}
