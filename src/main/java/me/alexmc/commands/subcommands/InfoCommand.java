package me.alexmc.commands.subcommands;

import me.alexmc.TransparentHeads;
import me.alexmc.commands.SubCommand;
import me.alexmc.utils.Fields;
import me.alexmc.utils.Utils;
import org.bukkit.entity.Player;

public class InfoCommand implements SubCommand {

    private final TransparentHeads plugin;

    public InfoCommand(TransparentHeads plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "info";
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
        if (player.hasPermission("theads.info")) {
            player.sendMessage(Utils.toNColor(
                    "&7&m--------------------------\n"
                            + "&6&l• &7Running &6&lTransparentHeads &7by NiceCraftz\n\n"
                            + "&6&l> &eAuthor: NiceCraftz"
                            + "&6&l> &eVersion: 1.1\n"
                            + "&6&l> &eSpigot Page: https://bit.ly/TransparentHeads\n"
                            + "&7&m--------------------------"));
        } else {
            player.sendMessage(plugin.getConfigYml().getFormattedString(Fields.NO_PERM.getPath()));
        }

    }
}
