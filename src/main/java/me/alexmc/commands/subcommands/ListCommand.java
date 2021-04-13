package me.alexmc.commands.subcommands;

import me.alexmc.TransparentHeads;
import me.alexmc.commands.SubCommand;
import me.alexmc.utils.Fields;
import me.alexmc.utils.Utils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

import java.util.Base64;

public class ListCommand implements SubCommand {

    private final TransparentHeads plugin;

    public ListCommand(TransparentHeads plugin) {

        this.plugin = plugin;

    }

    @Override
    public String getName() {
        return "list";
    }

    @Override
    public String getDescription() {
        return "Show a list of default heads.";
    }

    @Override
    public String getSyntax() {
        return "/theads list";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (player.hasPermission("theads.list")) {
            if (plugin.getConfigYml().getBoolean(Fields.IS_LIST_ENABLED.getPath())) {
                if (args.length > 1) {
                    String toencode = "{\"textures\":{\"SKIN\":{\"url\":\"https://education.minecraft.net/wp-content/uploads/%args%\"}}}".replaceAll("%args%", args[1]);
                    String encoded = Base64.getEncoder().encodeToString(toencode.getBytes());
                    player.getInventory().addItem(Utils.getCustomTextureHead(encoded));
                } else {
                    int count = 1;
                    for (String s : plugin.getConfigYml().getStringList(Fields.HEADS_LIST.getPath())) {
                        String[] values = s.split(",");
                        if (!s.isEmpty() && !values[0].isEmpty() && !values[1].isEmpty() && s.contains(",")) {
                            TextComponent tc = new TextComponent(Utils.toNColor(values[0]));
                            tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(plugin.getConfigYml().getFormattedString(Fields.GET_STRING.getPath())).create()));
                            tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/theads list " + values[1]));

                            String listNumberFormatting = Fields.LIST_NUMBER_FORMATTING.getPath();
                            String val = plugin.getConfigYml().getFormattedString(listNumberFormatting);

                            TextComponent format = new TextComponent(val.replaceAll("%num%", String.valueOf(count)));
                            player.spigot().sendMessage(format, tc);
                            count++;
                        }
                    }
                }
            } else {
                player.sendMessage(plugin.getConfigYml().getFormattedString(Fields.LIST_DISABLED.getPath()));
            }
        } else {
            player.sendMessage(plugin.getConfigYml().getFormattedString(Fields.NO_PERM.getPath()));
        }
    }
}
