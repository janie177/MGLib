package net.minegusta.mglib.permissionsex;

import org.bukkit.entity.Player;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.util.List;

class PEXHelper {

	public static List<String> getGroupsForPlayer(Player player)
	{
		PermissionUser user = PermissionsEx.getUser(player);
		return user.getParentIdentifiers();
	}

	public static boolean addPermission(Player p, String permission)
	{
		PermissionUser user = PermissionsEx.getUser(p);
		user.addPermission(permission);
		return true;
	}

	public static boolean addPermission(Player p, String permission, String worldname)
	{
		PermissionUser user = PermissionsEx.getUser(p);
		user.addPermission(permission, worldname);
		return true;
	}

	public static boolean removePermission(Player p, String permission)
	{
		PermissionUser user = PermissionsEx.getUser(p);
		user.removePermission(permission);
		return true;
	}

	public static boolean removePermission(Player p, String permission, String worldname)
	{
		PermissionUser user = PermissionsEx.getUser(p);
		user.removePermission(permission, worldname);
		return true;
	}
}
