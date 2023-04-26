package me.alexmc.commands.subcommands;

import me.alexmc.commands.SubCommand;
import me.alexmc.utils.ColorAPI;
import me.alexmc.utils.Fields;
import org.bukkit.entity.Player;

public class HowCommand implements SubCommand {

    @Override
    public String getPermission() {
        return "theads.howto";
    }

    @Override
    public String getDescription() {
        return "A quick tutorial on how to use the plugin";
    }

    @Override
    public String getSyntax() {
        return "/theads howto";
    }

    @Override
    public void perform(Player player, String[] args) {
        Fields.TUTORIAL_LIST.getStringList().forEach(s -> player.sendMessage(ColorAPI.color(s)));
    }
}
