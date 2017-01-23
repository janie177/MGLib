package net.minegusta.mglib.gui;

import com.google.common.collect.Maps;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.Collection;
import java.util.concurrent.ConcurrentMap;

public abstract class InventoryGUI {

	private String name;
	private int rows;
	private GUIInventoryHolder holder;
	private ConcurrentMap<Inventory, Boolean> openedInvs = Maps.newConcurrentMap();

	/**
	 * Create a new insance of a GUI.
	 * @param name The name of the GUI.
	 * @param rows The amount of rows in the GUI. Every row has 9 slots.
	 * @param key The key for this GUI. Has to be unique.
	 */
	public InventoryGUI(String name, int rows, String key)
	{
		this.name = name;
		this.rows = rows;
		this.holder = new GUIInventoryHolder(key);

		GUIRegistry.addGUI(key, this);
	}

	/**
	 * Build the inventory for a player.
	 * @param player The player to build the inventory for.
	 * @param slots The amount of slots for the inventory.
	 * @param holder The inventory holder.
	 * @param name The name of the inventory.
	 * @return A newly made inventory.
	 */
	public abstract Inventory buildInventory(Player player, int slots, InventoryHolder holder, String name);

	/**
	 * Open this GUI for a player.
	 * @param player The player to open it for.
	 */
	public void openInventory(Player player)
	{
		Inventory inv = buildInventory(player, rows * 9, holder, name);
		player.openInventory(inv);
		if(getAnimationInterval() > 0) addInv(inv);
	}

	/**
	 * Process a click for this inventory.
	 * @param player The player who clicked.
	 * @param slot The slot that was clicked.
	 * @param e The event, can be used in advanced cases.
	 */
	public abstract void processClick(Player player, int slot, InventoryClickEvent e);

	public void remove()
	{
		GUIRegistry.remove(getKey());
	}

	/**
	 * Get the inventory holder.
 	 * @return The instance of GUIInventoryHolder for this inventory.
	 */
	public GUIInventoryHolder getHolder()
	{
		return holder;
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

	/**
	 * Animate the inventory. This is called automatically.
	 * @param inv The inventory to animate.
	 */
	public abstract void animate(Inventory inv);

	public abstract int getAnimationInterval();

	public String getKey()
	{
		return holder.getKey();
	}

	/**
	 * Get the amount of rows in this inventory.
	 * @return The amount of rows. Rows * 9 = Slots.
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * Get the name of the inventory.
	 * @return The name of this inventory GUI in String format.
	 */
	public String getName() {
		return name;
	}
}
