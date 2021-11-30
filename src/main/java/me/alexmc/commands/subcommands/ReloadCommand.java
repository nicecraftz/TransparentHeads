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
    public String getPermission() {
        return "theads.reload";
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
        plugin.async(() -> {
            plugin.getConfiguration().reload();
            player.sendMessage(Fields.RELOADED.getFormattedString());
        });
    }
}
