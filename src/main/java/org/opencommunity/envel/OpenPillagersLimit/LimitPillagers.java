package org.opencommunity.envel.OpenPillagersLimit;

import org.opencommunity.envel.OpenPillagersLimit.commands.MainCommand;
import org.opencommunity.envel.OpenPillagersLimit.listeners.Limiter;
import org.opencommunity.envel.OpenPillagersLimit.listeners.Patrols;
import org.opencommunity.envel.OpenPillagersLimit.listeners.Remover;
import org.opencommunity.envel.OpenPillagersLimit.listeners.Stopper;
import org.opencommunity.envel.OpenPillagersLimit.utils.RegistrationManager;
import org.opencommunity.envel.OpenPillagersLimit.utils.StringHelper;
import org.opencommunity.envel.OpenPillagersLimit.utils.VersionHelper;
import org.bukkit.command.Command;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class LimitPillagers extends JavaPlugin {
    private static LimitPillagers INSTANCE;

    public static LimitPillagers getInstance() {
        return INSTANCE;
    }

    private final VersionHelper versionHelper = new VersionHelper();

    public VersionHelper getVersionHelper() {
        return this.versionHelper;
    }

    private final StringHelper stringHelper = new StringHelper();

    public StringHelper getStringHelper() {
        return this.stringHelper;
    }

    private final RegistrationManager registrationManager = new RegistrationManager();

    public RegistrationManager getRegistrationManager() {
        return this.registrationManager;
    }

    public void onEnable() {
        INSTANCE = this;
        getRegistrationManager().registerCommands(new Command[] { (Command)new MainCommand() });
        getRegistrationManager().registerEvents(new Listener[] { (Listener)new Stopper(), (Listener)new Limiter(), (Listener)new Remover(), (Listener)new Patrols() });
        saveDefaultConfig();
    }

    public void onDisable() {
    }
}
