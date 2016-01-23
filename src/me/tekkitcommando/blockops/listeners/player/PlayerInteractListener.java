package me.tekkitcommando.blockops.listeners.player;

import me.tekkitcommando.blockops.LMS;
import me.tekkitcommando.blockops.handlers.GameHandler;
import me.tekkitcommando.blockops.handlers.KitHandler;
import me.tekkitcommando.blockops.listeners.GameListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

/**
 * Copyright (c) 2016 Ethan Smith
 */

public class PlayerInteractListener extends GameListener {

    public PlayerInteractListener(LMS game) {
        super(game);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if(event.getItem() == null) {
            return;
        }

        if(event.getItem().getType() == Material.AIR || event.getItem().getType() == null) {
            return;
        }

        if(event.getItem().getType() == Material.NETHER_STAR && !GameHandler.hasStarted()) {
            Inventory inv = Bukkit.createInventory(null, 27, "Kit Selector");
            for(KitHandler k : KitHandler.getAllKits()) {
                inv.addItem(k.getDispayItem());
            }
            event.getPlayer().openInventory(inv);
        }
    }
}
