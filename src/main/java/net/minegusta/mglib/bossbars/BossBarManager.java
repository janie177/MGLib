package net.minegusta.mglib.bossbars;

import com.google.common.collect.Maps;

import java.util.Collection;
import java.util.concurrent.ConcurrentMap;

class BossBarManager {

	private static ConcurrentMap<BossBarHolderModel, Boolean> bars = Maps.newConcurrentMap();

	public static void addBar(BossBarHolderModel holder)
	{
		bars.put(holder, true);
	}

	public static void removeBar(BossBarHolderModel holder)
	{
		if(bars.containsKey(holder)) bars.remove(holder);
	}

	public static Collection<BossBarHolderModel> getHolders()
	{
		return bars.keySet();
	}
}
