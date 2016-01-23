package me.tekkitcommando.blockops.listeners.player;

import me.tekkitcommando.blockops.LMS;
import me.tekkitcommando.blockops.handlers.GameHandler;
import me.tekkitcommando.blockops.listeners.GameListener;
import me.tekkitcommando.blockops.utils.ChatUtil;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

/**
 * Copyright (c) 2016 Ethan Smith
 */

public class AsyncPlayerPreLogin extends GameListener {

    public AsyncPlayerPreLogin(LMS game) {
        super(game);
    }

    @EventHandler
    public void playerPreLogin(AsyncPlayerPreLoginEvent event) {
        if(GameHandler.hasStarted()) {
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, ChatColor.RED + "Can't join while game state is IN_GAME_STATE!");
        }
    }
}
