package net.minegusta.mglib.utils;

import com.google.common.collect.Lists;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.List;

public class BlockUtil {


	/**
	 * Look in a radius around a block and count for a needed material. Return if there is enough.
	 *
	 * @param center       The block that is centered.
	 * @param radius       The distance to search in in all directions.
	 * @param searched     The desired material.
	 * @param neededAmount The desired amount of the material specified.
	 * @return If there is enough blocks.
	 */
	public static boolean radiusCheck(Block center, int radius, Material searched, int neededAmount) {
		int count = 0;

		for (int x = -(radius); x <= radius; x++) {
			for (int y = -(radius); y <= radius; y++) {
				for (int z = -(radius); z <= radius; z++) {
					if (center.getRelative(x, y, z).getType() == searched) {
						count++;
					}
				}
			}
		}
		return count >= neededAmount;
	}


	/**
	 * Replace blocks in a radius with effects.
	 * @param center The center to run at.
	 * @param radius The radius around the center.
	 * @param searched The materials to search for.
	 * @param replacement The material to replace with.
	 * @param effect The effect to play per block.
	 */
	public static void replaceBlocks(Block center, int radius, List<Material> searched, Material replacement, Effect effect) {
		for (int x = -(radius); x <= radius; x++) {
			for (int y = -(radius); y <= radius; y++) {
				for (int z = -(radius); z <= radius; z++) {
					Block block = center.getRelative(x, y, z);
					if (searched.contains(block.getType())) {
						EffectUtil.playParticle(block.getLocation(), effect, 1, 1, 1, 10);
						block.setType(replacement);
					}
				}
			}
		}
	}


	/**
	 *
	 * @param l The center to run at.
	 * @param radius The radius around the center.
	 * @param materials A nested array. {{1, 0}, {2, 3}, {material, datavalue}}
	 * @param players The players that will recieve the block change.
	 */
	private static void sendBlockUpdates(Location l, int radius, int[][] materials, List<Player> players)
	{
		List<Block> blocks = Lists.newArrayList();

		for(int x = -radius; x < radius; x++)
		{
			for(int y = -radius; y < radius; y++)
			{
				for(int z = -radius; z < radius; z++)
				{
					Block b = l.getBlock().getRelative(x,y,z);

					if(b.getLocation().distance(l) > 4 ) continue;

					if(b.getType() != Material.AIR)
					{
						blocks.add(b);
					}
				}
			}
		}

		players.stream().forEach(p ->
		{
			blocks.stream().forEach(b ->
			{
				int index = RandomUtil.getZeroIncludedMaxExcluded(materials.length);
				p.sendBlockChange(b.getLocation(), materials[index][0], (byte) materials[index][1]);
			});
		});

	}


}
