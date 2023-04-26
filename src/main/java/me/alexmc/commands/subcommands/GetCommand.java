package me.alexmc.commands.subcommands;

import me.alexmc.api.THeadsAPI;
import me.alexmc.commands.SubCommand;
import me.alexmc.utils.Fields;
import org.bukkit.entity.Player;

import java.util.Objects;

public class GetCommand implements SubCommand {

    @Override
    public String getPermission() {
        return "theads.get";
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
        if(args.length < 2) {
            player.sendMessage(Fields.GIVE_LINK.getFormattedString());
            return;
        }
        player.getInventory().addItem(Objects.requireNonNull(THeadsAPI.getInstance().getHeadItem(args[1])));
        player.sendMessage(Fields.ADDED.getFormattedString());
    }
}
