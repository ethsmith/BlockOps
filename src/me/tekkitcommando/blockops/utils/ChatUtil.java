package me.tekkitcommando.blockops.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.*;

/**
 * Copyright (c) 2016 Ethan Smith
 */

public class ChatUtil {

    public static void broadcast(String msg) {
        for(Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(theme() + msg);
        }
    }

    public static void sendMessage(Player player, String msg) {
        player.sendMessage(theme() + msg);
    }

    private static String theme() {
        return DARK_RED + "" + BOLD + "[" + DARK_GRAY + "Block Ops" + DARK_RED + "" + BOLD + "] " + RED;
    }
}
