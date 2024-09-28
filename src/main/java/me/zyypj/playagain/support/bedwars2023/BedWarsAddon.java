package me.zyypj.playagain.support.bedwars2023;

import com.tomkeuper.bedwars.api.addon.Addon;
import me.zyypj.playagain.PlayAgainAddon;
import me.zyypj.playagain.command.bedwars2023.PlayAgainCommand;
import me.zyypj.playagain.config.MainConfig;
import me.zyypj.playagain.config.bedwars2023.MessagesData;
import me.zyypj.playagain.listeners.bedwars2023.ArenaEvent;
import me.zyypj.playagain.listeners.bedwars2023.InventoryListener;
import me.zyypj.playagain.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.File;

import static me.zyypj.playagain.PlayAgainAddon.*;

public class BedWarsAddon extends Addon {


    @Override
    public String getAuthor() {
        return "tadeu";
    }

    @Override
    public Plugin getPlugin() {
        return PlayAgainAddon.getPlugins();
    }

    @Override
    public String getVersion() {
        return getPlugin().getDescription().getVersion();
    }

    @Override
    public String getName() {
        return getPlugin().getName();
    }

    @Override
    public String getDescription() {
        return getPlugin().getDescription().getDescription();
    }

    @Override
    public void load() {
        Utility.info("BEDWARS2023 FOUND. LOADING SUPPORT");

        loadConfig();
        loadListener();
        loadCommands();

        Utility.info("SUPPORT LOADED!");
    }

    @Override
    public void unload() {
        Bukkit.getPluginManager().disablePlugin(getPlugin());
    }

    public void loadConfig() {
        long startTime = System.currentTimeMillis();
        Utility.info("&eLoading config...");
        mainConfig = new MainConfig(PlayAgainAddon.getPlugins(), "config", bw2023Api.getAddonsPath().getPath() + File.separator + "PlayAgain");
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
