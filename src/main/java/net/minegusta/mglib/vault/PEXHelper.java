package net.minegusta.mglib.vault;

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
}
