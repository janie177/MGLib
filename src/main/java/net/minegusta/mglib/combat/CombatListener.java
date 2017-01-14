package net.minegusta.mglib.combat;

import com.google.common.collect.Maps;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.concurrent.ConcurrentMap;

public class CombatListener implements Listener {

	//Map containing all the combat data.
	static ConcurrentMap<String, Long> inCombat = Maps.newConcurrentMap();

	@EventHandler(priority = EventPriority.MONITOR)
	public void onTag(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player && !e.isCancelled() && e.getDamage() != 0) {

			inCombat.put(e.getEntity().getUniqueId().toString(), System.currentTimeMillis() + CombatUtil.getGlobalCombatTime());
			inCombat.put(e.getDamager().getUniqueId().toString(), System.currentTimeMillis() + CombatUtil.getGlobalCombatTime());
		}
	}

}
