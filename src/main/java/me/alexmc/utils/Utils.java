package me.alexmc.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public final class Utils {

    public static ItemStack getCustomTextureHead(String arg) {
        String toEncode = "{\"textures\":{\"SKIN\":{\"url\":\"https://education.minecraft.net/wp-content/uploads/%args%\"}}}".replaceAll("%args%", arg);
        String value = Base64.getEncoder().encodeToString(toEncode.getBytes());

        ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), "");
        profile.getProperties().put("textures", new Property("textures", value));
        if (meta == null)
            return null;

        Field profileField;
        try {
            profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
            profileField.setAccessible(false);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }

        head.setItemMeta(meta);
        return head;
    }

    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static List<String> color(List<String> stringList) {
        return stringList.stream().map(Utils::color).collect(Collectors.toList());
    }
}
