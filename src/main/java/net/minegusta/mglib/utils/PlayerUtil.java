package net.minegusta.mglib.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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

	public static boolean isInSunlight(Player p) {
		return p.getLocation().getBlock().getLightFromSky() == 15;
	}

	public static boolean isInLava(Player p) {
		Material mat = p.getLocation().getBlock().getType();
		return mat == Material.LAVA || mat == Material.STATIONARY_LAVA;
	}

	public static void removeOne(Player p, Material m) {
		int slot = 0;
		for (ItemStack i : p.getInventory().getContents()) {
			if (i != null && i.getType() == m) {
				if (i.getAmount() > 1) {
					i.setAmount(i.getAmount() - 1);
				} else {
					p.getInventory().setItem(slot, new ItemStack(Material.AIR));
				}
				break;
			}
			slot++;
		}
		p.updateInventory();
	}

	public static void removeAmount(Player p, Material m, int amount) {
		int remaining = amount;
		int slot = 0;
		for (ItemStack i : p.getInventory().getContents()) {
			if (i != null && i.getType() == m) {
				if (i.getAmount() > remaining) {
					i.setAmount(i.getAmount() - remaining);
					break;
				} else {
					remaining = remaining - i.getAmount();
					p.getInventory().setItem(slot, new ItemStack(Material.AIR));
				}
				if (remaining < 1) break;
			}
			slot++;
		}
		p.updateInventory();
	}

	public static boolean areEqualIgnoreAmount(ItemStack is1, ItemStack is2) {
		if (is1 == null || is2 == null || is1.getType() == Material.AIR || is2.getType() == Material.AIR) return false;

		if (is1.getType().equals(is2.getType())) {
			ItemMeta meta1 = is1.getItemMeta();
			ItemMeta meta2 = is2.getItemMeta();

			if (compareString(meta1.getDisplayName(), meta2.getDisplayName())) {
				if (!meta1.hasLore() && !meta2.hasLore()) {
					return true;
				}
				if (meta1.hasLore() && meta2.hasLore()) {
					if (compareString(meta1.getLore().toString(), meta2.getLore().toString())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private static boolean compareString(String one, String two) {
		if (one == null || two == null) {
			if (one == null && two == null) return true;
			return false;
		}
		return one.equals(two);
	}

	public static void removeOne(Player p, ItemStack is) {
		int slot = 0;
		for (ItemStack i : p.getInventory().getContents()) {
			if (i != null && areEqualIgnoreAmount(i, is)) {
				if (i.getAmount() > 1) {
					i.setAmount(i.getAmount() - 1);
				} else {
					p.getInventory().setItem(slot, new ItemStack(Material.AIR));
				}
				break;
			}
			slot++;
		}
		p.updateInventory();
	}



}
