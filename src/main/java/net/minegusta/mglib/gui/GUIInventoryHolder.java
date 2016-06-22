package net.minegusta.mglib.gui;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

class GUIInventoryHolder implements InventoryHolder {

	private String key;

	GUIInventoryHolder(String key)
	{
		super();
		this.key = key;
	}

	@Override
	public Inventory getInventory() {
		return null;
	}

	String getKey() {
		return key;
	}
}
