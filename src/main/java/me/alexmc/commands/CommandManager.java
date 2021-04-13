package me.alexmc.commands;

import me.alexmc.TransparentHeads;
import me.alexmc.commands.subcommands.*;
import me.alexmc.utils.Fields;
import me.alexmc.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements CommandExecutor {

    private final List<SubCommand> subCommands;
    private final TransparentHeads plugin;

    public CommandManager(TransparentHeads plugin) {
        this.plugin = plugin;
        subCommands = new ArrayList<>();
        subCommands.add(new GetCommand(plugin));
        subCommands.add(new ReloadCommand(plugin));
        subCommands.add(new InfoCommand(plugin));
        subCommands.add(new HowCommand(plugin));
        subCommands.add(new ListCommand(plugin));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length > 0) {
                for (int i = 0; i < getSubCommands().size(); i++) {
                    if (args[0].equalsIgnoreCase(getSubCommands().get(i).getName())) {
                        getSubCommands().get(i).perform(player, args);
                    }
                }
            } else {
                player.sendMessage(Utils.toNColor(
                        "&7&m--------------------------\n"
                                + "&6&l• &7Running &6&lTransparentHeads &7by NiceCraftz\n\n"
                                + "&6&l> &e/theads reload\n"
                                + "&6&l> &e/theads get <image>\n"
                                + "&6&l> &e/theads info\n"
                                + "&6&l> &e/theads howto\n"
                                + "&6&l> &e/theads list\n"
                                + "&7&m--------------------------"));
            }

        } else {
            sender.sendMessage(plugin.getConfigYml().getFormattedString(Fields.NO_PERM.getPath()));
        }

        return true;
    }

    public List<SubCommand> getSubCommands() {
        return subCommands;
    }
}
