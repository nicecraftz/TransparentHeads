package me.alexmc;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import me.alexmc.commands.CommandManager;

public final class TransparentHeads extends JavaPlugin {

    public static TransparentHeads instance;

    public static TransparentHeads getInstance() {
        return instance;
    }

    private Config config;

    @Override
    public void onEnable() {
        instance = this;
        config = new Config(this, "config");

        getCommand("theads").setExecutor(new CommandManager(this));
    }

    public Config getConfigYml() {
        return config;
    }

    public void async(Runnable runnable) {
        Bukkit.getServer().getScheduler().runTaskAsynchronously(this, runnable);
    }

    public void sync(Runnable runnable) {
        Bukkit.getServer().getScheduler().runTask(this, runnable);
    }

}
