package me.tekkitcommando.blockops.listeners.inventory;

import me.tekkitcommando.blockops.LMS;
import me.tekkitcommando.blockops.handlers.KitHandler;
import me.tekkitcommando.blockops.listeners.GameListener;
import me.tekkitcommando.blockops.utils.ChatUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Copyright (c) 2016 Ethan Smith
 */

public class InventoryClickListener extends GameListener {

    public InventoryClickListener(LMS game) {
        super(game);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }
        if (!event.getInventory().getName().equalsIgnoreCase("Kit Selector")) {
            return;
        }

        event.setCancelled(true);
        Player player = (Player) event.getWhoClicked();

        ItemStack i = event.getCurrentItem();

        if (i == null) {
            return;
        }

        if(i.getType() == null || i.getType() == Material.AIR) {
            return;
        }

        KitHandler kit = KitHandler.getKit(i.getItemMeta().getDisplayName());
        KitHandler.setKit(player, kit);
        ChatUtil.sendMessage(player, "Kit: " + kit.getKitName() + " selected!");
    }
}
