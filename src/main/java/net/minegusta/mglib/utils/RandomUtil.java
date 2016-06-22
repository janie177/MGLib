package net.minegusta.mglib.utils;

import java.util.Random;

public class RandomUtil {
	private static Random random = new Random();

	/**
	 * Return a random number between min and max, min being included and max included as well.
	 *
	 * @param min The minimum value.
	 * @param max The maximum value.
	 * @return A random number between min and max.
	 */
	public static int get(int min, int max) {
		return random.nextInt((max + 1) - min) + min;
	}

	/**
	 * Includes max and excludes 0.
	 *
	 * @param max The maximum number.
	 * @return A random Integer between 0 and max. Max being included.
	 */
	public static int get(int max) {
		return random.nextInt(max) + 1;
	}

	/**
	 * Return a random number that can be 0 but not the max.
	 * @param max The maximum number (excluded)
	 * @return a number between 0 and max, max excluded.
	 */
	public static int getZeroIncludedMaxExcluded(int max)
	{
		return random.nextInt(max);
	}

	/**
	 * Return true x times out of hundred, where x is the input.
	 * @param percentage the percentage chance to return true.
	 * @return true or false.
	 */
	public static boolean chance(int percentage) {
		return random.nextInt(100) + 1 <= percentage;
	}

	/**
	 * Get a boolean, with the promillage chance for true given.
	 * @param promillage The input. 1000 is always true, and 1 means one in a thousand times true.
	 * @return True if conditions are met.
	 */
	public static boolean promillage(int promillage) {
		return random.nextInt(1000) + 1 <= promillage;
	}

	/**
	 * Return a random double.
	 *
	 * @param max The maximum value.
	 * @return A random double.
	 */
	public static double getDouble(int max) {
		return ((double) random.nextInt(max) + 1);
	}

	/**
	 * It could both be true or false untill it is returned.
	 *
	 * @return true or false.
	 */
	public static boolean getBoolean() {
		return random.nextBoolean();
	}
}
