package org.opencommunity.envel.OpenPillagersLimit.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.opencommunity.envel.OpenPillagersLimit.LimitPillagers;

import java.util.List;
import java.util.Objects;

public class Limiter implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void a(CreatureSpawnEvent event) {
        if (event.getEntityType().equals(EntityType.PILLAGER))
            if (LimitPillagers.getInstance().getConfig().getBoolean("Limiter.Enabled")) {
                List<Entity> nearbyEntites = (List<Entity>) event.getLocation().getWorld().getNearbyEntities(event.getLocation(), LimitPillagers.getInstance().getConfig().getInt("Limiter.Radius-X"), LimitPillagers.getInstance().getConfig().getInt("Limiter.Radius-Y"), LimitPillagers.getInstance().getConfig().getInt("Limiter.Radius-Z"));
                int pillagerCount = 0;
                for (Entity entity : nearbyEntites) {
                    EntityType entityType = entity.getType();
                    if (entityType.equals(EntityType.PILLAGER))
                        pillagerCount++;
                }
                if (pillagerCount >= LimitPillagers.getInstance().getConfig().getInt("Limiter.Stop-At-Amount")) {
                    if (!Objects.requireNonNull(event.getEntity().getEquipment()).getHelmet().getType().equals(Material.AIR) && LimitPillagers.getInstance().getConfig().getBoolean("Limiter.Ignore-Leaders"))
                        return;
                    if (event.getEntity().customName() != null && LimitPillagers.getInstance().getConfig().getBoolean("Limiter.Ignore-Named"))
                        return;
                    if (event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.RAID) && LimitPillagers.getInstance().getConfig().getBoolean("Limiter.Ignore-Raiders"))
                        return;
                    event.setCancelled(true);
                }
            }
    }
}
