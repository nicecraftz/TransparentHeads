package me.alexmc.api;

import org.bukkit.inventory.ItemStack;

public interface ITheadsAPI {

    default ItemStack getHeadItem(String location) {
        return getHeadItem(location, true);
    }

    ItemStack getHeadItem(String location, boolean includeDefaultUrl);
}
