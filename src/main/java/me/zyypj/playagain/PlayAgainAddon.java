package me.zyypj.playagain;

import lombok.Getter;
import me.zyypj.playagain.config.MainConfig;
import me.zyypj.playagain.support.bedwars1058.BedWars1058;
import me.zyypj.playagain.support.bedwars2023.BedWars2023;
import me.zyypj.playagain.utils.Support;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlayAgainAddon extends JavaPlugin {

    @Getter
    public static Support support;
    @Getter
    public static MainConfig mainConfig;
    @Getter
    public static com.tomkeuper.bedwars.api.BedWars bw2023Api;
    @Getter
    public static com.andrei1058.bedwars.api.BedWars bw1058Api;


    @Override
    public void onEnable() {
        loadSupport();

    }

    @Override
    public void onDisable() {

    }

    private void loadSupport() {
        if (Bukkit.getPluginManager().getPlugin("BedWars2023") != null) {
            new BedWars2023();
        } else if (Bukkit.getPluginManager().getPlugin("BedWars1058") != null) {
            new BedWars1058();
        }
    }

    public static PlayAgainAddon getPlugins() {
        return PlayAgainAddon.getPlugin(PlayAgainAddon.class);
    }
}
