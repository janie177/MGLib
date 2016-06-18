package net.minegusta.mglib.menu;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

class MenuInventoryHolder implements InventoryHolder{

	private String key;

	public String getKey()
	{
		return key;
	}

	public void setKey(String key)
	{
		this.key = key;
	}

	@Override
	public Inventory getInventory() {
		return null;
	}
}
