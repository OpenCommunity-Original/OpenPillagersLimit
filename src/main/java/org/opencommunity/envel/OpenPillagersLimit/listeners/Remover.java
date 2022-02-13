package org.opencommunity.envel.OpenPillagersLimit.listeners;

import org.opencommunity.envel.OpenPillagersLimit.LimitPillagers;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

public class Remover implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void a(ChunkLoadEvent event) {
        if (LimitPillagers.getInstance().getConfig().getBoolean("Remover.Enabled"))
            for (Entity entity : event.getChunk().getEntities()) {
                if (entity.getType().equals(EntityType.PILLAGER)) {
                    LivingEntity pillager = (LivingEntity)entity;
                    if (!pillager.getEquipment().getHelmet().getType().equals(Material.AIR) && LimitPillagers.getInstance().getConfig().getBoolean("Remover.Ignore-Leaders"))
                        return;
                    if (pillager.getCustomName() != null && LimitPillagers.getInstance().getConfig().getBoolean("Remover.Ignore-Named"))
                        return;
                    entity.remove();
                }
            }
    }
}
