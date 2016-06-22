package net.minegusta.mglib.utils;

import org.bukkit.entity.Player;

import java.util.List;

public class TitleUtil {

	public static void sendTitle(Title title, List<Player> players)
	{
		players.stream().forEach(title::send);
	}

	public static void sendTitle(Title title, Player player)
	{
		title.send(player);
	}

	public static Title createTitle(String text, String subtext, int fadeIn, int duration, int fadeOut, boolean timeInTicks)
	{
		Title title = new Title();
		title.setTitle(text);
		if(!timeInTicks) title.setTimingsToSeconds();
		else title.setTimingsToTicks();
		title.setSubtitle(subtext);
		title.setFadeInTime(fadeIn);
		title.setStayTime(duration);
		title.setFadeOutTime(fadeOut);

		return title;
	}
}
