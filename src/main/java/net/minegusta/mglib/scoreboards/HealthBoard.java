package net.minegusta.mglib.scoreboards;

import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

public class HealthBoard extends ScoreBoardModel {

	private Scoreboard board;
	private Objective objective;
	private ConcurrentMap<String, Team> teams = Maps.newConcurrentMap();

	public HealthBoard(String displayName)
	{
		board = Bukkit.getScoreboardManager().getNewScoreboard();
		objective = board.registerNewObjective("showhealth", "health");
		objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
		objective.setDisplayName(displayName);
	}

	public void addTeam(String teamName, String prefix, String suffix, boolean canSeeSameTeamInvisible, boolean alwaysShowTag)
	{
		Team team = board.registerNewTeam(teamName.toLowerCase());
		team.setPrefix(prefix + " ");
		team.setSuffix(suffix);
		team.setDisplayName(prefix + " ");
		team.setCanSeeFriendlyInvisibles(canSeeSameTeamInvisible);
		if(alwaysShowTag) team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.ALWAYS);
		teams.put(teamName, team);
	}

	public Optional<Team> getTeam(String teamName)
	{
		if(teams.containsKey(teamName.toLowerCase()))
		{
			return Optional.of(teams.get(teamName.toLowerCase()));
		}
		return Optional.empty();
	}

	public boolean addPlayer(Player player, String team) {
		if(teams.containsKey(team.toLowerCase()))
		{
			Team t = teams.get(team.toLowerCase());
			t.addEntry(player.getName());
			player.setScoreboard(board);
			return true;
		}
		return false;
	}
}
