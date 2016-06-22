package net.minegusta.mglib.gui;

import net.minegusta.mglib.main.Main;
import org.bukkit.Bukkit;

public class GUITask {

	private static int id = -1;
	private static long ticks = 0;

	public static void start()
	{
		//Task for animation
		id = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), ()->
		{
			for(InventoryGUI gui : GUIRegistry.getAllGUIs())
			{
				int interval = gui.getAnimationInterval();
				if(interval > 0 && ticks % interval == 0)
				{
					gui.getInventories().stream().forEach(inv ->
					{
						if(inv.getViewers().isEmpty())
						{
							gui.removeInv(inv);
						}
						else
						{
							gui.animate(inv);
						}
					});
				}
			}
			ticks++;
		},5, 5);
	}

	public static void stop()
	{
		if(id != -1)
		{
			Bukkit.getScheduler().cancelTask(id);
		}
	}
}
