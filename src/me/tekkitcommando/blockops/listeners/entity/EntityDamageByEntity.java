package me.tekkitcommando.blockops.listeners.entity;

import me.tekkitcommando.blockops.LMS;
import me.tekkitcommando.blockops.handlers.GameHandler;
import me.tekkitcommando.blockops.handlers.TeamHandler;
import me.tekkitcommando.blockops.listeners.GameListener;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Copyright (c) 2016 Ethan Smith
 */

public class EntityDamageByEntity extends GameListener {

    public EntityDamageByEntity(LMS game) {
        super(game);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if(!(event.getEntity() instanceof Player && event.getDamager() instanceof Player)) {
            event.setCancelled(true);
            return;
        }
        if(!GameHandler.hasStarted()) {
            event.setCancelled(true);
            return;
        }
        Player player = (Player) event.getEntity();
        Player damager = (Player) event.getDamager();

        if(TeamHandler.getPlayerTeam(player) == TeamHandler.getPlayerTeam(damager)) {
            event.setCancelled(true);
            damager.sendMessage(ChatColor.RED + "Friendly Fire Disabled!");
            return;
        }
    }
}
