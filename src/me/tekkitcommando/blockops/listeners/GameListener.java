package me.tekkitcommando.blockops.listeners;

import me.tekkitcommando.blockops.LMS;
import org.bukkit.event.Listener;

/**
 * Copyright (c) 2016 Ethan Smith
 */

public class GameListener implements Listener {

    LMS plugin;

    public GameListener(LMS game) {
        plugin = game;
    }
}
