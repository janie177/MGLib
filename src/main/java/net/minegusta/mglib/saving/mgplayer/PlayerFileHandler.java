package net.minegusta.mglib.saving.mgplayer;

import net.minegusta.mglib.yml.YamlUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

class PlayerFileHandler {

	protected static FileConfiguration getFile(Plugin plugin, String uuid) {
		return YamlUtil.getConfiguration(plugin, "/players/", uuid + ".yml");
	}

	protected static boolean fileExists(Plugin plugin, String uuid) {
		return YamlUtil.fileExists(plugin, "/players/", uuid + ".yml");
	}

	protected static FileConfiguration getFile(Plugin plugin, UUID uuid) {
		return getFile(plugin, uuid.toString());
	}

	protected static boolean save(Plugin plugin, String uuid, FileConfiguration f) {
		return YamlUtil.saveFile(plugin, "/players/", uuid + ".yml", f);
	}

	protected static boolean save(Plugin plugin, UUID uuid, FileConfiguration f) {
		return save(plugin, uuid.toString(), f);
	}

}
