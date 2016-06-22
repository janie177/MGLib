package net.minegusta.mglib.bossbars;

import net.minegusta.mglib.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;

public class TimedBossBarHolder extends BossBarHolderModel {

	private int duration;
	private int interval;
	private double fillPerStep;
	private boolean fullToEmpty;
	private double filled = 1.0;

	protected TimedBossBarHolder(String title, BarColor color, BarStyle style, int duration, int interval, double fillPerStep, boolean fullToEmpty) {
		super(title, color, style);
		this.duration = duration;
		this.interval = interval;
		this.fillPerStep = fillPerStep;
		this.fullToEmpty = fullToEmpty;
		if(!fullToEmpty) filled = 0;

		BossBarManager.addBar(this);

		start();
	}

	private void start()
	{
		int amount = duration / interval;
		for(int i = 0; i < amount; i++)
		{
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), ()->
			{
				filled += fullToEmpty ? -fillPerStep : fillPerStep;
				if(filled <= 1 && filled >= 0){
					setProgress(filled);
				}
			}, i * interval);
		}

		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), ()->
				{
					removeAllPlayers();
					BossBarManager.removeBar(this);
				}, duration + 10);

	}
}
