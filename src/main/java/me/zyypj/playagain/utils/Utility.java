package me.zyypj.playagain.utils;

import me.zyypj.playagain.PlayAgainAddon;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Utility {

    public static void info(String text) {
        Bukkit.getConsoleSender().sendMessage("[" + PlayAgainAddon.getPlugins().getName() + "] " + c(text));
    }

    public static void warn(String text) {
        PlayAgainAddon.getPlugins().getLogger().warning(c(text));
    }

    public static String c(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static String p(Player player, String text) {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) return c(text);
        return c(PlaceholderAPI.setPlaceholders(player, text));
    }

    public static String getMsg(Player player, String path) {
        if (PlayAgainAddon.getSupport() == Support.BEDWARS2023) return p(player, PlayAgainAddon.getBw2023Api().getPlayerLanguage(player).getString(path));
        if (PlayAgainAddon.getSupport() == Support.BEDWARS1058) return p(player, PlayAgainAddon.getBw1058Api().getPlayerLanguage(player).getString(path));
        else return c("&cMISSING");
    }

    public static List<String> getListMsg(Player player, String path) {
        if (PlayAgainAddon.getSupport() == Support.BEDWARS2023) return PlayAgainAddon.getBw2023Api().getPlayerLanguage(player).getList(path).stream().map(s -> p(player, s)).collect(Collectors.toList());
        if (PlayAgainAddon.getSupport() == Support.BEDWARS1058) return PlayAgainAddon.getBw1058Api().getPlayerLanguage(player).getList(path).stream().map(s -> p(player, s)).collect(Collectors.toList());
        else return Collections.singletonList(c("&cMISSING"));
    }
}
