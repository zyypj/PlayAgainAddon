package me.zyypj.playagain.config;

import com.tomkeuper.bedwars.api.configuration.ConfigManager;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import static me.zyypj.playagain.config.ConfigPaths.*;

public class MainConfig extends ConfigManager {
    public MainConfig(Plugin plugin, String name, String dir) {
        super(plugin, name, dir);
        YamlConfiguration yml = getYml();
        yml.options().header("PlayAgainAddon By tadeu (@zyypj)");
        yml.addDefault(LEAVE_ITEM_TYPE, "BED");
        yml.addDefault(LEAVE_ITEM_SLOT, 9);
        yml.addDefault(LEAVE_ITEM_COMMAND, "bw leave");
        yml.addDefault(PLAY_AGAIN_ITEM_TYPE, "PAPER");
        yml.addDefault(PLAY_AGAIN_ITEM_SLOT, 8);
        yml.addDefault(PLAY_AGAIN_ITEM_COMMAND, "internal");
        yml.options().copyDefaults(true);
        save();
    }
}
