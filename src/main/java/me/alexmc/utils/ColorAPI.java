package me.alexmc.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ColorAPI {

    public String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public List<String> color(List<String> stringList) {
        return stringList.stream().map(ColorAPI::color).collect(Collectors.toList());
    }
}
