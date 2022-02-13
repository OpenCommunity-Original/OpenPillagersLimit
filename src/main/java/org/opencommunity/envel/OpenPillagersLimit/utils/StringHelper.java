package org.opencommunity.envel.OpenPillagersLimit.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.opencommunity.envel.OpenPillagersLimit.LimitPillagers;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StringHelper {
    public void tellConsole(Level level, String message) {
        message = colorize(message);
        if (!LimitPillagers.getInstance().getVersionHelper().isPaper())
            message = ChatColor.stripColor(message);
    }

    public void tellConsole(Level level, String... message) {
        for (String string : message)
            tellConsole(level, string);
    }

    public void tellPlayer(Player player, String message) {
        player.sendMessage(colorize(message));
    }

    public void tellPlayer(Player player, String... message) {
        for (String string : message)
            tellPlayer(player, string);
    }

    public void tellPlayer(CommandSender sender, String message) {
        if (sender instanceof Player) {
            sender.sendMessage(colorize(message));
        } else {
            tellConsole(Level.INFO, message);
        }
    }

    public void tellPlayer(CommandSender sender, String... message) {
        for (String string : message)
            tellPlayer(sender, string);
    }

    private static final Pattern HEX_REGEX = Pattern.compile("<#([A-Fa-f0-9]){6}>");

    public String colorize(String message) {
        if (message == null || message == "")
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

    public List<String> colorize(List<String> list) {
        List<String> tempList = new ArrayList<>();
        for (String string : list)
            tempList.add(colorize(string));
        return tempList;
    }

    public String nanosToMillis(long nanoseconds) {
        return (new DecimalFormat("0.00")).format(nanoseconds / 1000000.0D);
    }
}
