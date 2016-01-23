package me.tekkitcommando.blockops.listeners.player;

import me.tekkitcommando.blockops.GameState;
import me.tekkitcommando.blockops.LMS;
import me.tekkitcommando.blockops.handlers.GameHandler;
import me.tekkitcommando.blockops.handlers.TeamHandler;
import me.tekkitcommando.blockops.listeners.GameListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Copyright (c) 2016 Ethan Smith
 */

public class PlayerLeaveListener extends GameListener {

    public PlayerLeaveListener(LMS game) {
        super(game);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (GameState.isState(GameState.LOBBY_STATE)) {
            GameHandler.setCanStart(Bukkit.getOnlinePlayers().size() - 1 >= 8);
        }

        Player player = event.getPlayer();

        if (GameHandler.hasStarted()) {
            TeamHandler.getPlayerTeam(player).removeTeamPlayers(player);
        }
    }
}
