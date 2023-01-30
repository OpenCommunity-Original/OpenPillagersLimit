package org.opencommunity.envel.OpenPillagersLimit.utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.opencommunity.envel.OpenPillagersLimit.LimitPillagers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelper {
    public void tellConsole(String message) {
        message = colorize(message);
        if (!LimitPillagers.getInstance().getVersionHelper().isPaper())
            message = ChatColor.stripColor(message);
    }

    public void tellPlayer(CommandSender sender, String message) {
        if (sender instanceof Player) {
            sender.sendMessage(colorize(message));
        } else {
            tellConsole(message);
        }
    }

    private static final Pattern HEX_REGEX = Pattern.compile("<#([A-Fa-f0-9]){6}>");

    public String colorize(String message) {
        if (message == null || message.equals(""))
            return message;
        if (LimitPillagers.getInstance().getVersionHelper().getMajorVersionNumber() >= 16) {
            Matcher matcher = HEX_REGEX.matcher(message);
            while (matcher.find()) {
                ChatColor hexColor = ChatColor.of(matcher.group().substring(1, matcher.group().length() - 1));
                String before = message.substring(0, matcher.start());
                String after = message.substring(matcher.end());
                message = before + hexColor + after;
                matcher = HEX_REGEX.matcher(message);
            }
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
