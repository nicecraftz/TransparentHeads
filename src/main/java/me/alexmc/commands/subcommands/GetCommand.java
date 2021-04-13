package me.alexmc.commands.subcommands;

import me.alexmc.TransparentHeads;
import me.alexmc.commands.SubCommand;
import me.alexmc.utils.Fields;
import me.alexmc.utils.Utils;
import org.bukkit.entity.Player;

import java.util.Base64;

public class GetCommand implements SubCommand {

    private final TransparentHeads plugin;

    public GetCommand(TransparentHeads plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "get";
    }

    @Override
    public String getDescription() {
        return "Get a Head by providing the name of an image!";
    }

    @Override
    public String getSyntax() {
        return "/theads get <image>";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (player.hasPermission("theads.get")) {
            if (args.length > 1) {
                String toencode = "{\"textures\":{\"SKIN\":{\"url\":\"https://education.minecraft.net/wp-content/uploads/%args%\"}}}".replaceAll("%args%", args[1]);
                String encoded = Base64.getEncoder().encodeToString(toencode.getBytes());
                player.getInventory().addItem(Utils.getCustomTextureHead(encoded));
                player.sendMessage(plugin.getConfigYml().getFormattedString(Fields.ADDED.getPath()));
            } else {
                player.sendMessage(plugin.getConfigYml().getFormattedString(Fields.GIVE_LINK.getPath()));
            }
        } else {
            player.sendMessage(plugin.getConfigYml().getFormattedString(Fields.NO_PERM.getPath()));
        }

    }
}
