package me.tekkitcommando.blockops.threads;

import me.tekkitcommando.blockops.GameState;
import me.tekkitcommando.blockops.LMS;
import me.tekkitcommando.blockops.handlers.GameHandler;
import me.tekkitcommando.blockops.utils.ChatUtil;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Copyright (c) 2016 Ethan Smith
 */

public class GameTimer extends BukkitRunnable {

    LMS plugin;

    public GameTimer(LMS game) {
        plugin = game;
    }

    public static int gameTimer;

    public void run() {

        if (gameTimer == 0) {
            if (!GameHandler.canStart()) {
                plugin.restartTimer();
                ChatUtil.broadcast("Cannot start game...\n Restarting Timer...");
                return;
            }
            GameHandler.GameStart();
        }

        if (gameTimer % 10 == 0 || gameTimer < 10) {
            ChatUtil.broadcast("Game starts in " + String.valueOf(gameTimer) + " seconds!");
        }

        gameTimer -= 1;
    }
}
