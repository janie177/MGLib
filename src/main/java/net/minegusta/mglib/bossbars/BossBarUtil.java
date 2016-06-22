package net.minegusta.mglib.bossbars;

import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;

public class BossBarUtil {

	public static TimedBossBarHolder createTimedBar(String title, BarColor color, BarStyle style, int duration, int interval, double fillPerStep, boolean fullToEmpty)
	{
		return new TimedBossBarHolder(title, color, style, duration, interval, fillPerStep, fullToEmpty);
	}

	public static TimedBossBarHolder createSecondCountdown(String title, BarColor color, BarStyle style, int seconds)
	{
		return new TimedBossBarHolder(title, color, style, seconds * 20, 20, 1.0 / seconds, true);
	}

	public static PermanentBossBarHolder createPermanentBar(String title, BarColor color, BarStyle style)
	{
		return new PermanentBossBarHolder(title, color, style);
	}

	public static void removeAllBars(Player player)
	{
		String uuid = player.getUniqueId().toString();
		BossBarManager.getHolders().stream().filter(bbh -> bbh.hasPlayer(uuid)).forEach(bbh -> bbh.removePlayer(player));
	}

	public static void removePermanentBars(Player player)
	{
		String uuid = player.getUniqueId().toString();
		BossBarManager.getHolders().stream().filter(bbh -> bbh.hasPlayer(uuid) && bbh instanceof PermanentBossBarHolder).forEach(bbh -> bbh.removePlayer(player));
	}

	public static void removeTimedBars(Player player)
	{
		String uuid = player.getUniqueId().toString();
		BossBarManager.getHolders().stream().filter(bbh -> bbh.hasPlayer(uuid) && bbh instanceof TimedBossBarHolder).forEach(bbh -> bbh.removePlayer(player));
	}
}
