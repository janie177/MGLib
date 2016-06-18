package net.minegusta.mglib.menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

public class MenuListener implements Listener {

	private String key;
	private GUIHandler handler;

	public MenuListener(String key, GUIHandler handler)
	{
		this.key = key;
		this.handler = handler;
	}

	@EventHandler
	public void onMenuClick(InventoryClickEvent e)
	{
		if(e.getClickedInventory() == null || e.getCurrentItem() == null || !(e.getWhoClicked() instanceof Player) || e.getCurrentItem().getType() == Material.AIR)
		{
			return;
		}

		//Its the spawn menu
		InventoryHolder holder = e.getClickedInventory().getHolder();
		if(holder instanceof MenuInventoryHolder && ((MenuInventoryHolder) holder).getKey().equalsIgnoreCase(key))
		{
			e.setCancelled(true);
			handler.processClick(e.getSlot(), (Player) e.getWhoClicked(), e.getClickedInventory(), e.getCurrentItem(), e.getCursor());
		}
	}

}
