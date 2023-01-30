package org.opencommunity.envel.OpenPillagersLimit.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.opencommunity.envel.OpenPillagersLimit.LimitPillagers;

import java.lang.reflect.Field;

public class RegistrationManager {
    public void registerCommands(Command... command) {
        for (Command commands : command) {
            StringHelper stringHelper = LimitPillagers.getInstance().getStringHelper();
            try {
                Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
                commandMapField.setAccessible(true);
                CommandMap commandMap = (CommandMap) commandMapField.get(Bukkit.getServer());
                commandMap.register(commands.getLabel(), commands);
            } catch (Exception exception) {
                stringHelper.tellConsole("&c&lERROR REGISTERING COMMANDS!");
                exception.printStackTrace();
                stringHelper.tellConsole("");
            }
        }
    }

    public void registerEvents(Listener... listeners) {
        PluginManager pluginManager = LimitPillagers.getInstance().getServer().getPluginManager();
        StringHelper stringHelper = LimitPillagers.getInstance().getStringHelper();
        for (Listener listener : listeners) {
            try {
                pluginManager.registerEvents(listener, LimitPillagers.getInstance());
            } catch (Exception exception) {
                stringHelper.tellConsole("&c&lERROR REGISTERING LISTENERS!");
                exception.printStackTrace();
                stringHelper.tellConsole("");
            }
        }
    }
}
