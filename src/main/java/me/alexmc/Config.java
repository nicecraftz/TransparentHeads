package me.alexmc;

import me.alexmc.utils.Fields;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Config {

    private final TransparentHeads plugin;
    private File customConfigFile;
    private final YamlConfiguration customConfig;

    public Config(TransparentHeads plugin, String configFileName) {
        this.plugin = plugin;
        File customConfigFile = new File(plugin.getDataFolder(), configFileName + ".yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            plugin.saveResource(configFileName + ".yml", false);
        }
        customConfig = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            customConfig.save(customConfigFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public boolean getBoolean(String path) {
        return customConfig.getBoolean(path);
    }

    public boolean getBoolean(Fields fields) {
        return customConfig.getBoolean(fields.getPath());
    }

    public double getFloat(String path) {
        return customConfig.getDouble(path);
    }

    public List<String> getStringList(String path) {
        return customConfig.getStringList(path);
    }

    public String getString(String path) {
        return customConfig.getString(path);
    }

    public String getFormattedString(String path) {
        return ChatColor.translateAlternateColorCodes('&', customConfig.getString(path));
    }

    public YamlConfiguration getYaml() {
        return customConfig;
    }

}
