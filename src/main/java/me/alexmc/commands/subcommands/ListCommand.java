package me.alexmc.commands.subcommands;

import me.alexmc.commands.SubCommand;
import me.alexmc.utils.Fields;
import me.alexmc.utils.Utils;
import net.md_5.bungee.api.chat.*;
import org.bukkit.entity.Player;

import java.util.Base64;
import java.util.Objects;

public class ListCommand implements SubCommand {

    @Override
    public String getPermission() {
        return "theads.list";
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
        if (!Fields.IS_LIST_ENABLED.getBool()) {
            player.sendMessage(Fields.LIST_DISABLED.getFormattedString());
            return;
        }

        if (args.length >= 2) {
            player.getInventory().addItem(Objects.requireNonNull(Utils.getCustomTextureHead(args[1])));
            return;
        }

        int count = 1;

        for (String s : Fields.HEADS_LIST.getStringList()) {
            String[] split = s.split(",");
            if (s.isEmpty() || split.length < 2 || !s.contains(",")) continue;

            TextComponent textComponent = new TextComponent(Utils.color(split[0]));
            BaseComponent[] baseComponents = new ComponentBuilder(Fields.GET_STRING.getFormattedString()).create();

            textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, baseComponents));
            textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/theads list " + split[1]));

            String formatted = Fields.LIST_NUMBER_FORMATTING.getFormattedString().replace("%num%", count + "");
            TextComponent finalComp = new TextComponent(formatted);
            finalComp.addExtra(textComponent);

            player.spigot().sendMessage(finalComp);
            count++;
        }
    }
}
