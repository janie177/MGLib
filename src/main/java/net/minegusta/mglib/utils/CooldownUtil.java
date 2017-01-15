package net.minegusta.mglib.utils;

import com.google.common.collect.Maps;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

public class CooldownUtil {

    private static ConcurrentMap<String, Long> cooldown = Maps.newConcurrentMap();

	/**
     * Create a new cooldown.
     * @param cooldownName The unique name of this cooldown
     * @param id The ID of the player. Could be a name or UUID, as long as it's consistent.
     * @param seconds The amount of seconds to put the cooldown for.
     */
    public static void newCoolDown(String cooldownName, String id, int seconds) {
        String stored = cooldownName + id;
        long expireTime = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(seconds);

        cooldown.put(stored, expireTime);
    }

    /**
     * Create a new cooldown.
     * @param cooldownName The unique name of this cooldown
     * @param id The ID of the player. Could be a name or UUID, as long as it's consistent.
     * @param millis The amount of milliseconds to put the cooldown as.
     */
    public static void newCoolDown(String cooldownName, String id, long millis) {
        String stored = cooldownName + id;
        long expireTime = System.currentTimeMillis() + millis;
        cooldown.put(stored, expireTime);
    }

	/**
     * Get how many seconds are remaining for the given cooldown. If there is less than 1 second remaining it will default to 1.
     * @param cooldownName The cooldown name. Must be unique for this particular cooldown.
     * @param id The ID of the user. Can be UUID or name in string format.
     * @return The amount of seconds remaining till this cooldown expires.
     */
    public static int getRemainingSeconds(String cooldownName, String id) {
        if (!cooldown.containsKey(cooldownName + id)) return 0;
        long remainingSeconds = cooldown.get(cooldownName + id) - System.currentTimeMillis();
        int remaining = (int) TimeUnit.MILLISECONDS.toSeconds(remainingSeconds);
        if(remaining == 0) remaining = 1;
        return remaining;
    }

	/**
     * Returns a boolean that shows whether the cooldown is expired.
     * @param cooldownName THe unique name of this cooldown.
     * @param id The UUID or name of the user in string format.
     * @return Whether the cooldown has expired.
     */
    public static boolean isCooledDown(String cooldownName, String id) {
        if (!cooldown.containsKey(cooldownName + id)) return true;
        if (cooldown.get(cooldownName + id) <= System.currentTimeMillis()) {
            remove(cooldownName, id);
            return true;
        }
        return false;
    }

	/**
     * Remove a cooldown from a user without it having to slowly expire.
     * @param cooldownName The name of the cooldown.
     * @param id The UUID or Name of the user in question. Has to be in string format and consistent.
     */
    private static void remove(String cooldownName, String id) {
        if (!cooldown.containsKey(cooldownName + id)) cooldown.remove(cooldownName + id);
    }
}
