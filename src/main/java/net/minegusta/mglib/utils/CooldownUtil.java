package net.minegusta.mglib.utils;

import com.google.common.collect.Maps;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

public class CooldownUtil {

    private static ConcurrentMap<String, Long> cooldown = Maps.newConcurrentMap();

    public static void newCoolDown(String cooldownName, String id, int seconds) {
        String stored = cooldownName + id;
        long expireTime = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(seconds);

        cooldown.put(stored, expireTime);
    }

    public static void newCoolDown(String cooldownName, String id, long millis) {
        String stored = cooldownName + id;
        long expireTime = System.currentTimeMillis() + millis;
        cooldown.put(stored, expireTime);
    }

    public static int getRemainingSeconds(String cooldownName, String id) {
        if (!cooldown.containsKey(cooldownName + id)) return 0;
        long remainingSeconds = cooldown.get(cooldownName + id) - System.currentTimeMillis();
        int remaining = (int) TimeUnit.MILLISECONDS.toSeconds(remainingSeconds);
        if(remaining == 0) remaining = 1;
        return remaining;
    }

    public static boolean isCooledDown(String cooldownName, String id) {
        if (!cooldown.containsKey(cooldownName + id)) return true;
        if (cooldown.get(cooldownName + id) <= System.currentTimeMillis()) {
            remove(cooldownName, id);
            return true;
        }
        return false;
    }

    private static void remove(String cooldownName, String id) {
        if (!cooldown.containsKey(cooldownName + id)) cooldown.remove(cooldownName + id);
    }
}
