package me.tekkitcommando.blockops.handlers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2016 Ethan Smith
 */

public class MapHandler {

    private static MapHandler activeMap = null;

    private static List<MapHandler> allMaps = new ArrayList<MapHandler>();

    private List<String> spawns;

    private String fileName, mapName;

    public MapHandler(String mapName, String fileName, List<String> spawns) {
        this.spawns = spawns;

        this.fileName = fileName;
        this.mapName = mapName;

        allMaps.add(this);
    }

    public static List<MapHandler> getAllMaps() {
        return allMaps;
    }

    public static void setActiveMap(MapHandler map) {
        activeMap = map;
    }

    public static MapHandler getActiveMap() {
        return activeMap;
    }

    public String getMapName() {
        return mapName;
    }

    public World getWorld() {
        return Bukkit.getWorld(fileName);
    }

    public Location getSpawn(int teamId) {
        for(TeamHandler team : TeamHandler.getAllTeams()) {
            int tId = team.getTeamId();
            if(tId != teamId) {
                continue;
            }

            int actualTeamId = tId;
            if(spawns.get(tId) == null) {
                actualTeamId = 0;
            }
            String teamSpawn = spawns.get(actualTeamId);
            String[] teamSpawns = teamSpawn.split(",");
            return new Location(getWorld(), Integer.valueOf(teamSpawns[0])+0.5, Integer.valueOf(teamSpawns[1])+0.5, Integer.valueOf(teamSpawns[2])+0.5);
        }
        return null;
    }
}
