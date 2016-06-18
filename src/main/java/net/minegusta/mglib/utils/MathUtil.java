package net.minegusta.mglib.utils;

import org.bukkit.Location;

public class MathUtil {

	/**
	 * Calculate a location on a circle around the given center, angle amount of degrees on the circle.
	 * @param l The center to start at.
	 * @param angle The angle the location should be on.
	 * @param radius The radius from the center the location should be on.
	 * @return The location on the circle.
	 */
	public static Location calculatePointOnCircle(Location l, int angle, double radius) {
		return new Location(l.getWorld(), l.getX() + radius * Math.sin(angle), l.getY(), l.getZ() + radius * Math.cos(angle));
	}


}
