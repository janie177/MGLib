package net.minegusta.mglib.gui;

import com.google.common.collect.Maps;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.Collection;
import java.util.concurrent.ConcurrentMap;

public abstract class InventoryGUI{

	private String name;
	private int rows;
	private GUIInventoryHolder holder;
	private ConcurrentMap<Inventory, Boolean> openedInvs = Maps.newConcurrentMap();

	public InventoryGUI(String name, int rows, String key)
	{
		this.name = name;
		this.rows = rows;
		this.holder = new GUIInventoryHolder(key);

		GUIRegistry.addGUI(key, this);
	}

	public abstract Inventory buildInventory(Player player, String name);

	public void openInventory(Player player, String title)
	{
		Inventory inv = buildInventory(player, title);
		player.openInventory(inv);
		if(getAnimationInterval() > 0) addInv(inv);
	}

	public abstract void processClick(Player player, int slot, InventoryClickEvent e);

	public void remove()
	{
		GUIRegistry.remove(getKey());
	}



	protected void addInv(Inventory inv)
	{
		openedInvs.put(inv, true);
	}

	protected Collection<Inventory> getInventories()
	{
		return openedInvs.keySet();
	}

	protected void removeInv(Inventory inv)
	{
		if(openedInvs.containsKey(inv))openedInvs.remove(inv);
	}



	public abstract void animate(Inventory inv);

	public abstract int getAnimationInterval();

	public String getKey()
	{
		return holder.getKey();
	}
}
