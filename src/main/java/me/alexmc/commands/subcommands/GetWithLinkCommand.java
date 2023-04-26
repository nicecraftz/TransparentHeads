package me.alexmc.commands.subcommands;

import me.alexmc.commands.SubCommand;
import me.alexmc.utils.Fields;
import me.alexmc.utils.Utils;
import org.bukkit.entity.Player;

import java.util.Objects;

public class GetWithLinkCommand implements SubCommand {
    @Override
    public String getPermission() {
        return "theads.getwithlink";
    }

    @Override
    public String getDescription() {
        return "Gets the head with the given link";
    }

    @Override
    public String getSyntax() {
        return "/theads getwithlink <link>";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage(Fields.GIVE_LINK.getFormattedString());
            return;
        }

        player.getInventory().addItem(Objects.requireNonNull(Utils.getCustomTextureHead(args[1], false)));
        player.sendMessage(Fields.ADDED.getFormattedString());
    }
}
