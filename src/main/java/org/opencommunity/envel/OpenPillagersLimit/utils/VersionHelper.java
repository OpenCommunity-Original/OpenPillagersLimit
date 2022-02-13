package org.opencommunity.envel.OpenPillagersLimit.utils;

import org.bukkit.Bukkit;

public class VersionHelper {
    String nmsVersion = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

    int majorVersionNumber = Integer.parseInt(this.nmsVersion.split("_")[1]);

    boolean isPaper;

    public VersionHelper() {
        this.isPaper = true;
    }

    public int getMajorVersionNumber() {
        return this.majorVersionNumber;
    }

    public boolean isPaper() {
        return this.isPaper;
    }
}
