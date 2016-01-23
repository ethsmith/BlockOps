package me.tekkitcommando.blockops.listeners.player;

import me.tekkitcommando.blockops.LMS;
import me.tekkitcommando.blockops.handlers.TeamHandler;
import me.tekkitcommando.blockops.listeners.GameListener;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Copyright (c) 2016 Ethan Smith
 */

public class PlayerDeathListener extends GameListener {

    public PlayerDeathListener(LMS game) {
        super(game);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        TeamHandler.getPlayerTeam(player).removeTeamPlayers(player);

        player.kickPlayer(ChatColor.RED + "You Died Soldier, Thank you for serving!");
    }
}
