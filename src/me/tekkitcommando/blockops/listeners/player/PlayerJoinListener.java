package me.tekkitcommando.blockops.listeners.player;

import me.tekkitcommando.blockops.LMS;
import me.tekkitcommando.blockops.handlers.GameHandler;
import me.tekkitcommando.blockops.listeners.GameListener;
import me.tekkitcommando.blockops.utils.InventoryUtil;
import me.tekkitcommando.blockops.utils.LocationUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Copyright (c) 2016 Ethan Smith
 */

public class PlayerJoinListener extends GameListener {

    public PlayerJoinListener(LMS game) {
        super(game);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        GameHandler.setCanStart(Bukkit.getOnlinePlayers().size() >= 2);

        LocationUtil.spawnTp(player);
        InventoryUtil.clearInventory(player);

        ItemStack is = new ItemStack(Material.NETHER_STAR);
        ItemMeta im = is.getItemMeta();

        im.setDisplayName(ChatColor.GOLD + "Kits");
        is.setItemMeta(im);

        player.getInventory().addItem(is);
        player.updateInventory();
    }
}
