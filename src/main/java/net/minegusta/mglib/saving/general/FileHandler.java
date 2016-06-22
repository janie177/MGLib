package net.minegusta.mglib.saving.general;

import net.minegusta.mglib.yml.YamlUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

class FileHandler {

	protected static FileConfiguration getFile(Plugin plugin, String fileName, String path) {
		return YamlUtil.getConfiguration(plugin, "/" + path + "/", fileName + ".yml");
	}

	protected static boolean fileExists(Plugin plugin, String fileName, String path) {
		return YamlUtil.fileExists(plugin, "/" + path + "/", fileName + ".yml");
	}

	protected static boolean save(Plugin plugin, String fileName, String path, FileConfiguration f) {
		return YamlUtil.saveFile(plugin, "/" + path + "/", fileName + ".yml", f);
	}
}
