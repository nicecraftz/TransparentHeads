package me.alexmc;

import me.alexmc.commands.CommandManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class TransparentHeads extends JavaPlugin {
    private Config config;

    @Override
    public void onEnable() {
        config = new Config(this, "config");

        CommandManager commandManager = new CommandManager(this);
        getCommand("theads").setExecutor(commandManager);
        getCommand("theads").setTabCompleter(commandManager);
    }

    @Override
    @NotNull
    public YamlConfiguration getConfig() {
        return config.getCustomConfig();
    }

    public Config getConfiguration() {
        return config;
    }

    public void async(Runnable runnable) {
        Bukkit.getServer().getScheduler().runTaskAsynchronously(this, runnable);
    }
}
