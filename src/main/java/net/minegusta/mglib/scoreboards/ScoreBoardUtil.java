package net.minegusta.mglib.scoreboards;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ScoreBoardUtil {


	public static HealthBoard createNewHealthboard(String healthName)
	{
		return new HealthBoard(healthName);
	}

	public static SideBarBoard createNewSideBarBoard(String name)
	{
		return new SideBarBoard(name);
	}

	public static void removeScoreBoard(Player player)
	{
		player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
	}
}
