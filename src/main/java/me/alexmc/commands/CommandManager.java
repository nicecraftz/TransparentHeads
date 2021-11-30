package me.alexmc.commands;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import me.alexmc.TransparentHeads;
import me.alexmc.commands.subcommands.*;
import me.alexmc.utils.Fields;
import me.alexmc.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class CommandManager implements CommandExecutor, TabCompleter {
    private final Map<String, SubCommand> subCommands;

    public CommandManager(TransparentHeads plugin) {
        subCommands = Maps.newHashMap();

        subCommands.put("get", new GetCommand());
        subCommands.put("reload", new ReloadCommand(plugin));
        subCommands.put("info", new InfoCommand());
        subCommands.put("howto", new HowCommand());
        subCommands.put("list", new ListCommand());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Fields.NO_PERM.getFormattedString());
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            return true;
        }

        if (!subCommands.containsKey(args[0].toLowerCase())) {
            player.sendMessage(Fields.UNKNOWN_ARG.getFormattedString());
            return true;
        }

        SubCommand subCommand = subCommands.get(args[0].toLowerCase());
        if (!player.hasPermission(subCommand.getPermission())) {
            player.sendMessage(Fields.NO_PERM.getFormattedString());
            return true;
        }

        subCommand.perform(player, args);
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(args.length > 1) return null;
        List<String> completitions = Lists.newArrayList();
        StringUtil.copyPartialMatches(args[0], subCommands.keySet(), completitions);
        return completitions;
    }
}
