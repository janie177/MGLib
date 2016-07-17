package net.minegusta.mglib.scoreboards;

import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.concurrent.ConcurrentMap;

public class SideBarBoard extends ScoreBoardModel {

	private ConcurrentMap<String, Scoreboard> boards = Maps.newConcurrentMap();
	private String displayName;

	public SideBarBoard(String displayname)
	{
		this.displayName = displayname;
	}

	public void updatePlayer(Player player, MGScore... scores)
	{
		if(!boards.containsKey(player.getUniqueId().toString()))
		{
			addPlayer(player, scores);
		}

		Scoreboard board = boards.get(player.getUniqueId().toString());
		Objective objective = board.getObjective("ob");
		for(MGScore score : scores)
		{
			Score s = objective.getScore(score.getName());
			s.setScore(score.getAmount());
		}

		//Add the player to the board just in case they somehow changed their board without being removed.
		player.setScoreboard(board);
	}

	@Override
	public void removePlayer(Player player)
	{
		if(boards.containsKey(player.getUniqueId().toString()))boards.remove(player.getUniqueId().toString());
		ScoreBoardUtil.removeScoreBoard(player);
	}

	public void addPlayer(Player player, MGScore... scores) {

		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

		Objective objective = board.registerNewObjective("ob", "dummy");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		objective.setDisplayName(displayName);
		Team team = board.registerNewTeam("team");
		team.addEntry(player.getName());

		for(MGScore score : scores)
		{
			Score s = objective.getScore(score.getName());
			s.setScore(score.getAmount());
		}


		boards.put(player.getUniqueId().toString(), board);

		player.setScoreboard(board);
	}
}