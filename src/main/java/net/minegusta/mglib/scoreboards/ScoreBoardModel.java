package net.minegusta.mglib.scoreboards;

import org.bukkit.entity.Player;

abstract class ScoreBoardModel {

	public void removePlayer(Player player)
	{
		ScoreBoardUtil.removeScoreBoard(player);
	}
}
