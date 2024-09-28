package me.zyypj.playagain.support.bedwars2023;

import com.tomkeuper.bedwars.api.addon.Addon;
import me.zyypj.playagain.PlayAgainAddon;
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

        Utility.info("SUPPORT LOADED!");
    }

    @Override
    public void unload() {
        Bukkit.getPluginManager().disablePlugin(getPlugin());
    }

    public void loadConfig() {
        Utility.info("&eLoading config...");
        mainConfig = new MainConfig(PlayAgainAddon.getPlugins(), "config", bw2023Api.getAddonsPath().getPath() + File.separator + "PlayAgain");
        Utility.info("&aConfig loaded!");
        Utility.info("&eLoading messages...");
        new MessagesData();
        Utility.info("&aMessages loaded!");
    }

    public void loadListener() {
        Utility.info("&eRegistering listeners...");
        Bukkit.getPluginManager().registerEvents(new ArenaEvent(), getPlugin());
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), getPlugin());
        Utility.info("&aListeners registered!");
    }
}
