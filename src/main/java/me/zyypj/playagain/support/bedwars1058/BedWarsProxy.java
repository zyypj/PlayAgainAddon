package me.zyypj.playagain.support.bedwars1058;

import me.zyypj.playagain.PlayAgainAddon;
import me.zyypj.playagain.command.bedwars1058.PlayAgainCommand;
import me.zyypj.playagain.config.MainConfig;
import me.zyypj.playagain.config.proxy1058.MessagesData;
import me.zyypj.playagain.listeners.bedwars2023.ArenaEvent;
import me.zyypj.playagain.listeners.bedwars2023.InventoryListener;
import me.zyypj.playagain.utils.Support;
import me.zyypj.playagain.utils.Utility;
import org.bukkit.Bukkit;

import static me.zyypj.playagain.PlayAgainAddon.*;

public class BedWarsProxy {

    public BedWarsProxy() {
        start();
    }

    private void start() {
        Utility.info("BEDWARSPROXY1058 FOUND. LOADING SUPPORT");

        support = Support.BEDWARSPROXY;

        loadConfig();
        loadListener();
        loadCommands();

        Utility.info("SUPPORT LOADED!");
    }

    public void loadConfig() {
        long startTime = System.currentTimeMillis();
        Utility.info("&eLoading config...");
        mainConfig = new MainConfig(PlayAgainAddon.getPlugins(), "config", Bukkit.getWorldContainer().getPath() + "/plugins/BedWarsProxy/Addons/PlayAgain");
        Utility.info("&aConfig loaded!");
        long endTime = System.currentTimeMillis();
        Utility.info("&aConfig loaded! " + (endTime - startTime) + "ms");

        startTime = System.currentTimeMillis();
        Utility.info("&eLoading messages...");
        new MessagesData();
        endTime = System.currentTimeMillis();
        Utility.info("&aMessages loaded! " + (endTime - startTime) + "ms");
    }

    public void loadListener() {
        long startTime = System.currentTimeMillis();
        Utility.info("&eRegistering listeners...");
        Bukkit.getPluginManager().registerEvents(new ArenaEvent(), getPlugins());
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), getPlugins());
        long endTime = System.currentTimeMillis();
        Utility.info("&aListeners registered! " + (endTime - startTime) + "ms");
    }

    public void loadCommands() {
        long startTime = System.currentTimeMillis();
        Utility.info("&eRegistering commands...");
        PlayAgainAddon.getPlugins().getCommand("playagain").setExecutor(new PlayAgainCommand());
        long endTime = System.currentTimeMillis();
        Utility.info("&aCommands registered! " + (endTime - startTime) + "ms");
    }
}
