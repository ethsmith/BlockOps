package me.tekkitcommando.blockops;

import me.tekkitcommando.blockops.handlers.KitHandler;
import me.tekkitcommando.blockops.listeners.entity.EntityDamageByEntity;
import me.tekkitcommando.blockops.listeners.inventory.InventoryClickListener;
import me.tekkitcommando.blockops.listeners.player.*;
import me.tekkitcommando.blockops.threads.GameTimer;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Arrays;

/**
 * Copyright (c) 2016 Ethan Smith
 */

public class LMS extends JavaPlugin {

    public static int gameTimerId;

    public void onEnable() {
        initListeners();
        GameState.setState(GameState.LOBBY_STATE);
        initConfig();
        registerKits();
        startGameTimer();
    }

    public void onDisable() {
    }

    public void initListeners() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerJoinListener(this), this);
        pm.registerEvents(new PlayerLeaveListener(this), this);
        pm.registerEvents(new AsyncPlayerPreLogin(this), this);
        pm.registerEvents(new PlayerDeathListener(this), this);
        pm.registerEvents(new PlayerInteractListener(this), this);
        pm.registerEvents(new InventoryClickListener(this), this);
        pm.registerEvents(new EntityDamageByEntity(this), this);
    }

    public void initConfig() {
        File resetFile = new File(getDataFolder(), "RESET.FILE");
        if (resetFile.exists()) {
            return;
        }

        FileConfiguration c = getConfig();

        c.set("Kits.Infantry.Items", Arrays.asList("272", "297:5"));
        c.set("Kits.Infantry.Display Item", 272);
        c.set("Kits.Sniper.Items", Arrays.asList("272", "297:5"));
        c.set("Kits.Sniper.Display Item", 272);
        c.set("Kits.Assault.Items", Arrays.asList("272", "297:5"));
        c.set("Kits.Assault.Display Item", 272);

        saveConfig();

        try {
            resetFile.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registerKits() {
        FileConfiguration c = getConfig();
        for(String s : c.getConfigurationSection("Kits").getKeys(false)) {
            String path = "Kits." + s + ".";
            new KitHandler(s, c.getStringList(path + "Items"), c.getInt(path + "Display Item"));
        }
    }

    public void startGameTimer() {
        GameTimer.gameTimer = 30;
        gameTimerId = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new GameTimer(this), 20l, 20l);
    }

    public void stopGameTimer() {
        getServer().getScheduler().cancelTask(gameTimerId);
    }

    public void restartTimer() {
        stopGameTimer();
        startGameTimer();
    }
}
