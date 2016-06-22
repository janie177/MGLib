package net.minegusta.mglib.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

class GUIListener implements Listener {

	@EventHandler
	public void onMenuClick(InventoryClickEvent e)
	{
		if(e.getClickedInventory() == null || e.getCurrentItem() == null || !(e.getWhoClicked() instanceof Player) || e.getCurrentItem().getType() == Material.AIR)
		{
			return;
		}

		InventoryHolder holder = e.getClickedInventory().getHolder();
		if(holder instanceof GUIInventoryHolder)
		{
			String key = ((GUIInventoryHolder) holder).getKey();
			if(GUIRegistry.contains(key))
			{
				GUIRegistry.getGUI(key).processClick((Player) e.getWhoClicked(), e.getSlot(), e);
			}
		}
	}
}
