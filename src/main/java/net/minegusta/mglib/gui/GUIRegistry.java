package net.minegusta.mglib.gui;

import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.concurrent.ConcurrentMap;

class GUIRegistry {

	private static ConcurrentMap<String, InventoryGUI> inventories = Maps.newConcurrentMap();

	static void addGUI(String key, InventoryGUI gui)
	{
		inventories.put(key.toLowerCase(), gui);
	}

	static InventoryGUI getGUI(String key)
	{
		return inventories.get(key.toLowerCase());
	}

	static boolean contains(String key)
	{
		return inventories.containsKey(key.toLowerCase());
	}

	static Collection<InventoryGUI> getAllGUIs()
	{
		return inventories.values();
	}

	static void remove(String key)
	{
		if(inventories.containsKey(key.toLowerCase())) inventories.remove(key.toLowerCase());
	}
}
