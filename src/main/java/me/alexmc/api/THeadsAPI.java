package me.alexmc.api;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.UUID;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class THeadsAPI implements ITheadsAPI {

    @Getter(lazy = true)
    private static final ITheadsAPI instance = new THeadsAPI();

    @Override
    public ItemStack getHeadItem(String location, boolean includeDefaultUrl) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\"textures\":{\"SKIN\":{\"url\":\"");
        stringBuilder.append(includeDefaultUrl ? "https://education.minecraft.net/wp-content/uploads/" : "");
        stringBuilder.append(location);
        stringBuilder.append("\"}}}");

        String toEncode = stringBuilder.toString();
        String value = Base64.getEncoder().encodeToString(toEncode.getBytes());

        ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), "");
        profile.getProperties().put("textures", new Property("textures", value));

        if (meta == null) return null;

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


}
