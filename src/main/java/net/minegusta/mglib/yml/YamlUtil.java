package net.minegusta.mglib.yml;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class YamlUtil {
    //Credits to CensoredSoftware for letting me use this!

    public static FileConfiguration getConfiguration(Plugin plugin, String path, String fileName) {
        File dataFile = new File(plugin.getDataFolder() + path + fileName);
        if (!(dataFile.exists())) createFile(plugin, dataFile);
        return YamlConfiguration.loadConfiguration(dataFile);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void createFile(Plugin plugin, File dataFile) {
        try {
            // Create the directories.
            if (!plugin.getDataFolder().exists())plugin.getDataFolder().mkdirs();
            (dataFile.getParentFile()).mkdirs();

            // Create the new file.
            dataFile.createNewFile();
        } catch (Exception errored) {
            throw new RuntimeException("Couldn't create a data file!", errored);
        }
    }

    public static boolean fileExists(Plugin plugin, String path, String fileName) {
        File dataFile = new File(plugin.getDataFolder() + path + fileName);
        return dataFile.exists();
    }

    public static boolean saveFile(Plugin plugin, String path, String fileName, FileConfiguration conf) {
        try {
            conf.save(plugin.getDataFolder() + path + fileName);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }
}
