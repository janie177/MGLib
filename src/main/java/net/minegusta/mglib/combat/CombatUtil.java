package net.minegusta.mglib.combat;

import net.minegusta.mglib.main.Main;
import net.minegusta.mglib.tasks.Task;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;

import java.util.UUID;

public class CombatUtil {

	//Time till combat expires in seconds.
	private static long IN_COMBAT_TIME = 10000;
	private static Task cleanUpTask = new Task();

	/**
	 * Define in miliseconds how long it takes till combat expires.
	 * @param time The time in milliseconds.
	 */
	public static void setGlobalCombatTimer(long time)
	{
		IN_COMBAT_TIME = time;
	}

	/**
	 * Return the global combat cooldown time.
	 * @return The time it takes to be out of combat in milliseconds.
	 */
	public static long getGlobalCombatTime()
	{
		return IN_COMBAT_TIME;
	}

	/**
	 * See if the given UUID is currently in combat as defined by the timer.
	 * @param uuid The UUID of the entity.
	 * @return True if the entity is in combat.
	 */
	public static boolean getIfInCombat(String uuid)
	{
		return CombatListener.inCombat.containsKey(uuid) && CombatListener.inCombat.get(uuid) > System.currentTimeMillis();
	}

	/**
	 * Set an entity to be in combat, or remove combat status.
	 * @param uuid The UUID to add or remove.
	 * @param combat Whether to add or remove combat status.
	 * @param millis The amount of seconds in combat if added.
	 */
	public static void setInCombat(String uuid, boolean combat, long millis) {
		if (combat) {
			CombatListener.inCombat.put(uuid, System.currentTimeMillis() + millis);
		} else if(CombatListener.inCombat.containsKey(uuid))
		{
			CombatListener.inCombat.remove(uuid);
		}
	}
	/**
	 * Set an entity to be in combat, or remove combat status.
	 * @param uuid The UUID to add or remove.
	 * @param combat Whether to add or remove combat status.
	 */
	public static void setInCombat(String uuid, boolean combat) {
		if (combat) {
			CombatListener.inCombat.put(uuid, System.currentTimeMillis() + IN_COMBAT_TIME);
		} else if(CombatListener.inCombat.containsKey(uuid))
		{
			CombatListener.inCombat.remove(uuid);
		}
	}

	/**
	 * Simple task to prevent memory clogs.
	 */
	public static void enableCleanupTask()
	{

		cleanUpTask.start(Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), () -> {
			for(String s : CombatListener.inCombat.keySet())
			{
				UUID uuid = UUID.fromString(s);

				Entity ent;
				if((ent = Bukkit.getEntity(uuid)) == null || !ent.isValid() || ent.isDead() || CombatListener.inCombat.get(s) < System.currentTimeMillis())
				{
					CombatListener.inCombat.remove(s);
				}
			}
		}, 20 * 120, 20 * 120));
	}
}
