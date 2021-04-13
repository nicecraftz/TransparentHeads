package me.alexmc.commands.subcommands;

import me.alexmc.TransparentHeads;
import me.alexmc.commands.SubCommand;
import me.alexmc.utils.Fields;
import me.alexmc.utils.Utils;
import org.bukkit.entity.Player;

import java.util.List;

public class HowCommand implements SubCommand {

    private final TransparentHeads plugin;

    public HowCommand(TransparentHeads plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "howto";
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
        if (player.hasPermission("theads.howto")) {
            List<String> tutorialList = plugin.getConfigYml().getStringList(Fields.TUTORIAL_LIST.getPath());
            for (String s : tutorialList) {
                player.sendMessage(Utils.toNColor(s));
            }

        } else {
            player.sendMessage(plugin.getConfigYml().getFormattedString(Fields.NO_PERM.getPath()));
        }
    }
}
