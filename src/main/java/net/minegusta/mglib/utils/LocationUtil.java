package net.minegusta.mglib.utils;

import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.List;

public class LocationUtil {
	/**
	 * Get the highest block at a location.
	 * @param l The location to check.
	 * @return The y of the highest block.
	 */
	public static int getHighestBlockYAt(Location l) {
		return l.getWorld().getHighestBlockYAt(l);
	}


	/**
	 * Return a list of locations that are in a circle around the center.
	 * @param center The center of the circle.
	 * @param degrees The amount of degrees between the points on the circle. Should be between 1 and 360
	 * @param radius The radius of the circle.
	 * @return A list of locations.
	 */
	public static List<Location> getPointsOnCircle(Location center, int degrees, double radius)
	{
		if(degrees == 0)
		{
			degrees = 1;
		}
		List<Location> locations = Lists.newArrayList();
		for(int i = 0; i < 361; i = i + degrees)
		{
			locations.add(MathUtil.calculatePointOnCircle(center, i, radius));
		}
		return locations;
	}


	/**
	 * Transform a location to a string
	 * @param l The location to transform
	 * @return A string representing a location. Can be converted back using StringToLocation
	 */
	public static String locationToString(Location l) {
		return Double.toString(l.getX()) + "," + Double.toString(l.getY()) + "," + Double.toString(l.getZ()) + "," + l.getWorld().getName();
	}

	/**
	 * Transform a string to a location. To be used with locationToString.
	 * @param s The string to transform into a location.
	 * @return A location.
	 */
	public static Location stringToLocation(String s) {
		String[] split = s.split(",");
		double x = Double.parseDouble(split[0]);
		double y = Double.parseDouble(split[1]);
		double z = Double.parseDouble(split[2]);
		World w;

		try {
			w = Bukkit.getWorld(split[3]);
		} catch (Exception ignored) {
			Bukkit.getLogger().info("[MGLib] World could not be found while converting location from string.");
			return null;
		}

		return new Location(w, x, y, z);
	}


}
