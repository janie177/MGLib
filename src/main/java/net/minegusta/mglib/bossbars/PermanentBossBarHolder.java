package net.minegusta.mglib.bossbars;

import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;

public class PermanentBossBarHolder extends BossBarHolderModel {

	protected PermanentBossBarHolder(String title, BarColor color, BarStyle style) {
		super(title, color, style);
		BossBarManager.addBar(this);
	}
}
