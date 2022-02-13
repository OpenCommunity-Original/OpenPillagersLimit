package org.opencommunity.envel.OpenPillagersLimit.listeners;

import org.opencommunity.envel.OpenPillagersLimit.LimitPillagers;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pillager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class Stopper implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void a(CreatureSpawnEvent event) {
        if (event.getEntityType().equals(EntityType.PILLAGER))
            if (LimitPillagers.getInstance().getConfig().getBoolean("Stopper.Enabled")) {
                if (LimitPillagers.getInstance().getConfig().getBoolean("Stopper.Use-Hard-Limit")) {
                    int pillagerCount = 0;
                    for (World world : Bukkit.getWorlds()) {
                        pillagerCount += world.getEntitiesByClasses(new Class[] { Pillager.class }).size();
                    }
                    if (pillagerCount <= LimitPillagers.getInstance().getConfig().getInt("Stopper.Hard-Limit-Amount"))
                        return;
                    event.setCancelled(true);
                }
                if (!event.getEntity().getEquipment().getHelmet().getType().equals(Material.AIR) && LimitPillagers.getInstance().getConfig().getBoolean("Stopper.Ignore-Leaders"))
                    return;
                if (event.getEntity().getCustomName() != null && LimitPillagers.getInstance().getConfig().getBoolean("Stopper.Ignore-Named"))
                    return;
                if (event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.RAID) && LimitPillagers.getInstance().getConfig().getBoolean("Stopper.Ignore-Raiders"))
                    return;
                event.setCancelled(true);
            }
    }
}
