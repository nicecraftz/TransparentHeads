package me.alexmc.commands.subcommands;

import me.alexmc.TransparentHeads;
import me.alexmc.commands.SubCommand;
import me.alexmc.utils.Fields;
import org.bukkit.entity.Player;

public class ReloadCommand implements SubCommand {

    private final TransparentHeads plugin;

    public ReloadCommand(TransparentHeads plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "Reload Plugin Configuration";
    }

    @Override
    public String getSyntax() {
        return "/theads reload";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (player.hasPermission("theads.reload")) {
            plugin.async(() -> {
                plugin.getConfigYml().reload();
                player.sendMessage(plugin.getConfigYml().getFormattedString(Fields.RELOADED.getPath()));
            });
        } else {
            player.sendMessage(plugin.getConfigYml().getFormattedString(Fields.NO_PERM.getPath()));
        }
    }
}
