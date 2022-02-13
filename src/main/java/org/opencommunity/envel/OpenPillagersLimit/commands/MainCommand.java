package org.opencommunity.envel.OpenPillagersLimit.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.opencommunity.envel.OpenPillagersLimit.LimitPillagers;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Pillager;

public class MainCommand extends Command {
    public MainCommand() {
        super("LimitPillagers");
        setDescription("Main command of the plugin.");
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission("limitpillagers.admin")) {
            LimitPillagers.getInstance().getStringHelper().tellPlayer(sender, LimitPillagers.getInstance().getConfig().getString("Messages.Command.No-Permission"));
            return true;
        }
        if (args.length < 1 || args.length > 1 || (

                !args[0].equalsIgnoreCase("reload") &&
                        !args[0].equalsIgnoreCase("count") &&
                        !args[0].equalsIgnoreCase("remove"))) {
            LimitPillagers.getInstance().getStringHelper().tellPlayer(sender, LimitPillagers.getInstance().getConfig().getString("Messages.Command.Usage"));
            return true;
        }
        if (args[0].equalsIgnoreCase("reload")) {
            LimitPillagers.getInstance().reloadConfig();
            LimitPillagers.getInstance().getStringHelper().tellPlayer(sender, LimitPillagers.getInstance().getConfig().getString("Messages.Command.Reloaded"));
            return true;
        }
        if (args[0].equalsIgnoreCase("count")) {
            int pillagerCount = 0;
            for (World world : Bukkit.getWorlds()) {
                pillagerCount += world.getEntitiesByClasses(new Class[] { Pillager.class }).size();
            }
            LimitPillagers.getInstance().getStringHelper().tellPlayer(sender, LimitPillagers.getInstance().getConfig().getString("Messages.Command.Count").replace("%count%", Integer.toString(pillagerCount)));
            return true;
        }
        if (args[0].equalsIgnoreCase("remove")) {
            int pillagerRemoved = 0;
            for (World world : Bukkit.getWorlds()) {
                for (Entity entity : world.getEntitiesByClasses(new Class[] { Pillager.class })) {
                    entity.remove();
                    pillagerRemoved++;
                }
            }
            LimitPillagers.getInstance().getStringHelper().tellPlayer(sender, LimitPillagers.getInstance().getConfig().getString("Messages.Command.Remove").replace("%amount%", Integer.toString(pillagerRemoved)));
            return true;
        }
        return false;
    }

    public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
        if (!sender.hasPermission("limitpillagers.admin"))
            return null;
        List<String> list = new ArrayList<>();
        if (args.length == 1) {
            list.add("reload");
            list.add("count");
            list.add("remove");
        }
        return args[args.length - 1].isEmpty() ? list : (List<String>)list.stream().filter(string -> string.toLowerCase().startsWith(args[args.length - 1].toLowerCase())).collect(Collectors.toList());
    }
}
