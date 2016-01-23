package me.tekkitcommando.blockops.handlers;

import me.tekkitcommando.blockops.utils.ChatUtil;
import me.tekkitcommando.blockops.utils.InventoryUtil;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.Permission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Copyright (c) 2016 Ethan Smith
 */

public class KitHandler {

    private static List<KitHandler> allKits = new ArrayList<KitHandler>();

    private static HashMap<String, KitHandler> playerKits = new HashMap<String, KitHandler>();

    private List<ItemStack> items = new ArrayList<ItemStack>();

    private String name, permission;

    private int displayItem;

    public KitHandler(String name, List<String> items, int displayItem) {
        this.name = name;
        this.permission = "blockops.kit." + name;
        this.displayItem = displayItem;

        for (String s : items) {
            int id = 0, amount = 1;
            if (s.contains(":")) {
                String[] splitItem = s.split(":");
                id = Integer.valueOf(splitItem[0].trim());
                amount = Integer.valueOf(splitItem[1].trim());
            } else {
                id = Integer.valueOf(s.trim());
            }
            this.items.add(new ItemStack(id, amount));
        }
        allKits.add(this);
    }

    public static boolean isKit(String name) {
        for (KitHandler k : allKits) {
            if (name.equalsIgnoreCase(k.getKitName())) {
                return true;
            }
        }
        return false;
    }

    public static KitHandler getKit(String name) {
        for (KitHandler k : allKits) {
            if (name.equalsIgnoreCase(name)) {
                return k;
            }
        }
        return null;
    }

    public static void setKit(Player player, KitHandler kit) {
        if (!player.hasPermission(kit.getPermissionNode())) {
            ChatUtil.sendMessage(player, "You do not have permission for that kit!");
            return;
        }
        playerKits.put(player.getName(), kit);
    }

    public static KitHandler getKit(Player player) {
        return playerKits.get(player.getName()) == null ? allKits.get(0) : playerKits.get(player.getName());
    }

    public void setKit(Player player) {
        setKit(player, this);
    }

    public String getKitName() {
        return name;
    }

    public static List<KitHandler> getAllKits() {
        return allKits;
    }

    public ItemStack getDispayItem() {
        ItemStack is = new ItemStack(displayItem);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(name);

        is.setItemMeta(im);
        return is;
    }

    public Permission getPermissionNode() {
        return new Permission(permission);
    }

    public void giveKit(Player player) {
        InventoryUtil.clearInventory(player);
        for (ItemStack is : items) {
            player.getInventory().addItem(is);
        }
    }
}
