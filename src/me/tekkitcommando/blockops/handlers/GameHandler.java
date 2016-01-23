package me.tekkitcommando.blockops.handlers;

import me.tekkitcommando.blockops.utils.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Copyright (c) 2016 Ethan Smith
 */

public class GameHandler {

    private static boolean canStart = false;
    private static boolean hasStarted = false;

    private static String[] teams = new String[] {"USMC", "Spetsnaz"};

    public static void GameStart() {
        hasStarted = true;
        new TeamHandler(teams);

        int i = 0;
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(i >= TeamHandler.getAllTeams().size()) {
                i = 0;
            }
            TeamHandler.getAllTeams().get(i).addTeamPlayers(player);
            LocationUtil.tpToGame(player, TeamHandler.getAllTeams().get(i));
            KitHandler.getKit(player).giveKit(player);
            i++;
        }
    }

    public static void GameStop(TeamHandler team) {

        hasStarted = false;
    }

    public static boolean canStart() {
        return canStart;
    }

    public static boolean hasStarted() {
        return hasStarted;
    }

    public static void setCanStart(boolean b) {
        canStart = b;
    }
}
