package net.minegusta.mglib.bossbars;

import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class BossBarHolderModel {

	private BossBar bar;
	private List<String> players = Lists.newArrayList();

	protected BossBarHolderModel(String title, BarColor color, BarStyle style)
	{
		bar = Bukkit.createBossBar(title, color, style);
	}

	public void addPlayer(Player player)
	{
		players.add(player.getUniqueId().toString());
		bar.addPlayer(player);
	}

	public boolean hasPlayer(Player player)
	{
		return players.contains(player.getUniqueId().toString());
	}

	public boolean hasPlayer(String uuid)
	{
		return players.contains(uuid);
	}

	public void removePlayer(Player player)
	{
		if(players.contains(player.getUniqueId().toString()))
		{
			players.remove(player.getUniqueId().toString());
			bar.removePlayer(player);
		}
	}

	public void removeAllPlayers()
	{
		bar.removeAll();
	}

	public void setProgress(double progress)
	{
		bar.setProgress(progress);
	}

	public void setDarkSky(boolean darkSky)
	{
		if(darkSky) bar.addFlag(BarFlag.DARKEN_SKY);
		else if(bar.hasFlag(BarFlag.DARKEN_SKY)) bar.removeFlag(BarFlag.DARKEN_SKY);
	}

	public void setFog(boolean fog)
	{
		if(fog) bar.addFlag(BarFlag.CREATE_FOG);
		else if(bar.hasFlag(BarFlag.CREATE_FOG)) bar.removeFlag(BarFlag.CREATE_FOG);
	}

	public List<Player> getPlayer()
	{
		return bar.getPlayers();
	}

	public void setVisible(boolean visible)
	{
		bar.setVisible(visible);
	}

	public void setTitle(String title)
	{
		bar.setTitle(title);
	}

	public void setColor(BarColor color)
	{
		bar.setColor(color);
	}

	public void setStyle(BarStyle style)
	{
		bar.setStyle(style);
	}

	public BarStyle getStyle()
	{
		return bar.getStyle();
	}

	public BossBar getBar()
	{
		return bar;
	}

	public String getTitle()
	{
		return bar.getTitle();
	}

	public BarColor getColor()
	{
		return bar.getColor();
	}
}
