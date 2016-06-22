package net.minegusta.mglib.gui;

import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.concurrent.ConcurrentMap;

class GUIRegistry {

	private static ConcurrentMap<String, InventoryGUI> inventories = Maps.newConcurrentMap();

	static void addGUI(String key, InventoryGUI gui)
	{
		inventories.put(key, gui);
	}

	static InventoryGUI getGUI(String key)
	{
		return inventories.get(key);
	}

	static boolean contains(String key)
	{
		return inventories.containsKey(key);
	}

	static Collection<InventoryGUI> getAllGUIs()
	{
		return inventories.values();
	}

	static void remove(String key)
	{
		if(inventories.containsKey(key)) inventories.remove(key);
	}
}
