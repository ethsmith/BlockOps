package me.tekkitcommando.blockops.handlers;

import me.tekkitcommando.blockops.utils.ChatUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Copyright (c) 2016 Ethan Smith
 */

public class TeamHandler {

    private static List<TeamHandler> allTeams = new ArrayList<TeamHandler>();
    private static List<TeamHandler> activeTeams = new ArrayList<TeamHandler>();

    private List<String> members = new ArrayList<String>();

    private static HashMap<String, TeamHandler> playerTeams = new HashMap<String, TeamHandler>();

    private String teamName;

    private TeamHandler(String teamName) {
        this.teamName = teamName.trim();

        activeTeams.add(this);
        allTeams.add(this);
    }

    public TeamHandler(String[] teamNames) {
        for(String s : teamNames) {
            new TeamHandler(s);
        }
    }

    public int getTeamId() {
        for(int i = 0; i < allTeams.size(); i++) {
            if(this == allTeams.get(i)) {
                return i;
            }
        }
        return -1;
    }

    public String getTeamName() {
        return teamName;
    }

    public Location getTeamSpawn() {
        return MapHandler.getActiveMap().getSpawn(getTeamId());
    }
    public void addTeamPlayers(Player player) {
        playerTeams.put(player.getName(), this);
        members.add(player.getName());
    }

    public boolean removeTeamPlayers(Player player) {
        if (!hasTeam(player)) {
            return false;
        }
        playerTeams.remove(player.getName());
        members.remove(player.getName());

        if(members.isEmpty()) {
            ChatUtil.broadcast(getTeamName() + "has been defeated!");
            activeTeams.remove(this);
        }
        if(activeTeams.size() == 1) {
            GameHandler.GameStop(activeTeams.get(0));
        }
        return true;
    }

    public static boolean hasTeam(Player player) {
        return playerTeams.containsKey(player.getName());
    }

    public static TeamHandler getPlayerTeam(Player player) {
        if(!hasTeam(player)) {
            return null;
        }
        return playerTeams.get(player.getName());
    }

    public static TeamHandler getTeam(String name) {
        for (TeamHandler team : allTeams) {
            if(team.teamName.equalsIgnoreCase(name)) {
                return team;
            }
        }
        return null;
    }

    public static List<TeamHandler> getAllTeams() {
        return allTeams;
    }
}
