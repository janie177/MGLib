package net.minegusta.mglib.permissionsex;

import com.google.common.collect.Lists;
import org.bukkit.entity.Player;

import java.util.List;

public class PEXUtil {

	public static boolean PEX_ENABLED = false;

	public List<String> getGroupsForPlayer(Player p)
	{
		if(!PEX_ENABLED) return Lists.newArrayList();
		return PEXHelper.getGroupsForPlayer(p);
	}
}
