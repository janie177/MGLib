package net.minegusta.mglib.saving;

import net.minegusta.mglib.saving.PlayerSaveManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

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
