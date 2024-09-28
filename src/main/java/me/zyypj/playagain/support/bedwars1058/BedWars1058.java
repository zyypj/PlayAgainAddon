package me.zyypj.playagain.support.bedwars1058;

import com.andrei1058.bedwars.api.BedWars;
import me.zyypj.playagain.PlayAgainAddon;
import me.zyypj.playagain.config.MainConfig;
import me.zyypj.playagain.config.bedwars1058.MessagesData;
import me.zyypj.playagain.listeners.bedwars2023.ArenaEvent;
import me.zyypj.playagain.listeners.bedwars2023.InventoryListener;
import me.zyypj.playagain.utils.Support;
import me.zyypj.playagain.utils.Utility;
import org.bukkit.Bukkit;

import java.io.File;

import static me.zyypj.playagain.PlayAgainAddon.*;

public class BedWars1058 {

    public BedWars1058() {
        start();
    }

    public void start() {
        Utility.info("BEDWARS1058 FOUND. LOADING SUPPORT");

        support = Support.BEDWARS1058;
        bw1058Api = Bukkit.getServicesManager().getRegistration(BedWars.class).getProvider();

        loadConfig();
        loadListener();

        Utility.info("SUPPORT LOADED!");
    }

    public void loadConfig() {
        Utility.info("&eLoading config...");
        mainConfig = new MainConfig(PlayAgainAddon.getPlugins(), "config", bw1058Api.getAddonsPath().getPath() + File.separator + "PlayAgain");
        Utility.info("&aConfig loaded!");
        Utility.info("&eLoading messages...");
        new MessagesData();
        Utility.info("&aMessages loaded!");
    }

    public void loadListener() {
        Utility.info("&eRegistering listeners...");
        Bukkit.getPluginManager().registerEvents(new ArenaEvent(), getPlugins());
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), getPlugins());
        Utility.info("&aListeners registered!");
    }
}
