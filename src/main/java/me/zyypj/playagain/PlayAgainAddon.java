package me.zyypj.playagain;

import lombok.Getter;
import me.zyypj.playagain.config.MainConfig;
import me.zyypj.playagain.support.bedwars2023.BedWars2023;
import me.zyypj.playagain.utils.Support;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class PlayAgainAddon extends JavaPlugin {

    @Getter
    public static Support support;
    public static MainConfig mainConfig;
    @Getter
    public static com.tomkeuper.bedwars.api.BedWars bw2023Api;

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
        }
    }

    public static PlayAgainAddon getPlugins() {
        return PlayAgainAddon.getPlugin(PlayAgainAddon.class);
    }
}
