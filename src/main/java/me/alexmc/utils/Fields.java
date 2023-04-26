package me.alexmc.utils;

import me.alexmc.TransparentHeads;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public enum Fields {

    ADDED("added"),
    RELOADED("reloaded"),
    NO_PERM("no-perm"),
    GIVE_LINK("givelink"),
    UNKNOWN_ARG("unknown-arg"),
    LIST_DISABLED("list-disabled"),
    LIST_NUMBER_FORMATTING("list-number-formatting"),
    GET_STRING("get-string"),
    IS_LIST_ENABLED("server-list"),
    TUTORIAL_LIST("tutorial"),
    HEADS_LIST("heads-list");

    private static final TransparentHeads plugin = JavaPlugin.getPlugin(TransparentHeads.class);
    private final String path;
    Fields(String path) {
        this.path = path;
    }

    public boolean getBool() {
        return plugin.getConfig().getBoolean(path);
    }

    public String getFormattedString() {
        return ColorAPI.color(plugin.getConfig().getString(path));
    }

    public List<String> getStringList() {
        return plugin.getConfig().getStringList(path);
    }
}
