package net.minegusta.mglib.permissionsex;

import com.google.common.collect.Lists;
import org.bukkit.entity.Player;

import java.util.List;

public class PEXUtil {

	public static boolean PEX_ENABLED = false;

	public static List<String> getGroupsForPlayer(Player p)
	{
		if(!PEX_ENABLED) return Lists.newArrayList();
		return PEXHelper.getGroupsForPlayer(p);
	}

	public static boolean addPermission(Player p, String permission)
	{
		if(!PEX_ENABLED) return false;
		return PEXHelper.addPermission(p, permission);
	}

	public static boolean addPermission(Player p, String permission, String worldname)
	{
		if(!PEX_ENABLED) return false;
		return PEXHelper.addPermission(p, permission, worldname);
	}

	public static boolean removePermission(Player p, String permission, String worldname)
	{
		if(!PEX_ENABLED) return false;
		return PEXHelper.removePermission(p, permission, worldname);
	}

	public static boolean removePermission(Player p, String permission)
	{
		if(!PEX_ENABLED) return false;
		return PEXHelper.removePermission(p, permission);
	}
}
