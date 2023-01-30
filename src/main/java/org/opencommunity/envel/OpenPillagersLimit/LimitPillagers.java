package org.opencommunity.envel.OpenPillagersLimit;

import org.bukkit.plugin.java.JavaPlugin;
import org.opencommunity.envel.OpenPillagersLimit.commands.MainCommand;
import org.opencommunity.envel.OpenPillagersLimit.listeners.Limiter;
import org.opencommunity.envel.OpenPillagersLimit.listeners.Patrols;
import org.opencommunity.envel.OpenPillagersLimit.listeners.Remover;
import org.opencommunity.envel.OpenPillagersLimit.listeners.Stopper;
import org.opencommunity.envel.OpenPillagersLimit.utils.RegistrationManager;
import org.opencommunity.envel.OpenPillagersLimit.utils.StringHelper;
import org.opencommunity.envel.OpenPillagersLimit.utils.VersionHelper;

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
        getRegistrationManager().registerCommands(new MainCommand());
        getRegistrationManager().registerEvents(new Stopper(), new Limiter(), new Remover(), new Patrols());
        saveDefaultConfig();
    }

    public void onDisable() {
    }
}
