package net.minegusta.mglib.menu;

import com.google.common.collect.Maps;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.concurrent.ConcurrentMap;

public class GUIHandler {

	private MenuInventoryHolder holder;
	private MenuListener listener;
	
	private ConcurrentMap<Integer, GUIAction> actionMap = Maps.newConcurrentMap();

	public GUIHandler(Plugin plugin, String name, int rows)
	{
		this.holder = new MenuInventoryHolder();
		holder.setKey(name);
		listener = new MenuListener(holder.getKey(), this);
		Bukkit.getPluginManager().registerEvents(listener, plugin);
	}

	public void processClick(int slot, Player player, Inventory inventory, ItemStack item, ItemStack cursor) {

	}
}
