package me.alexmc.commands.subcommands;

import me.alexmc.commands.SubCommand;
import me.alexmc.utils.Fields;
import me.alexmc.utils.Utils;
import org.bukkit.entity.Player;

public class InfoCommand implements SubCommand {

    @Override
    public String getPermission() {
        return "theads.info";
    }

    @Override
    public String getDescription() {
        return "Show infos about the plugin";
    }

    @Override
    public String getSyntax() {
        return "/theads info";
    }

    @Override
    public void perform(Player player, String[] args) {
        player.sendMessage(Utils.color(
                "&7&m--------------------------\n"
                        + "&6&l• &7Running &6&lTransparentHeads &7by NiceCraftz\n\n"
                        + "&6&l> &eAuthor: NiceCraftz\n"
                        + "&6&l> &eVersion: 1.1\n"
                        + "&6&l> &eSpigot Page: https://bit.ly/TransparentHeads\n"
                        + "&7&m--------------------------"));
    }
}
