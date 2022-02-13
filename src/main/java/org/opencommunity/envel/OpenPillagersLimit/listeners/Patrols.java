package org.opencommunity.envel.OpenPillagersLimit.listeners;

import org.opencommunity.envel.OpenPillagersLimit.LimitPillagers;
import org.bukkit.entity.Raider;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class Patrols implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void a(CreatureSpawnEvent event) {
        if (event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.PATROL))
            if (LimitPillagers.getInstance().getConfig().getBoolean("Patrol-Remover.Enabled")) {
                if (LimitPillagers.getInstance().getConfig().getBoolean("Patrol-Remover.Only-Remove-Target")) {
                    Raider raider = (Raider)event.getEntity();
                    raider.setPatrolLeader(false);
                    raider.setPatrolTarget(null);
                    return;
                }
                event.setCancelled(true);
            }
    }
}
