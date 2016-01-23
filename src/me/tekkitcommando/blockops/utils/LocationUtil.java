package me.tekkitcommando.blockops.utils;

import me.tekkitcommando.blockops.handlers.TeamHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Copyright (c) 2016 Ethan Smith
 */

public class LocationUtil {

    private static Location spawnLoc = new Location(Bukkit.getWorld("lobby"), 0.5, 64, 0.5);

    public static void spawnTp(Player player) {
        player.teleport(spawnLoc);
    }

    public static void tpAllSpawnLoc() {
        for(Player p : Bukkit.getOnlinePlayers()) {
            spawnTp(p);
        }
    }

    public static void tpToGame(Player player, TeamHandler team) {
        player.teleport(team.getTeamSpawn());
    }
}
