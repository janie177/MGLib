package net.minegusta.mglib.saving.mgplayer;

import net.minegusta.mglib.main.Main;
import net.minegusta.mglib.scoreboards.HealthBoard;
import net.minegusta.mglib.scoreboards.MGScore;
import net.minegusta.mglib.scoreboards.ScoreBoardUtil;
import net.minegusta.mglib.scoreboards.SideBarBoard;
import net.minegusta.mglib.utils.RandomUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.scoreboard.Scoreboard;

public class PlayerSaveListener implements Listener {

	private PlayerSaveManager manager;

	protected PlayerSaveListener(PlayerSaveManager manager)
	{
		super();
		this.manager = manager;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e)
	{
		manager.loadMGPlayer(e.getPlayer());
	}

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e)
	{
		manager.unloadPlayer(e.getPlayer());
	}

}
