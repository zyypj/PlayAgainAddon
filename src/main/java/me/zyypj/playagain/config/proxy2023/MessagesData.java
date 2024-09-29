package me.zyypj.playagain.config.proxy2023;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static me.zyypj.playagain.config.ConfigPaths.*;

public class MessagesData {

    YamlConfiguration yml;
    File file;

    public MessagesData() {
        setupMessages();
    }

    public void setupMessages() {
        file = new File(Bukkit.getPluginManager().getPlugin("BWProxy2023").getDataFolder().getPath() + "/Languages/", "messages_en.yml");
        yml = YamlConfiguration.loadConfiguration(file);
        yml.addDefault(NO_PERM, "&cYou do not have permission to use this command");
        yml.addDefault(PLAYER_NOT_IN_ARENA, "&cYou are not in an arena!");
        yml.addDefault(PLAYER_IS_IN_ARENA, "&cYou are already in this arena!");
        yml.addDefault(COMMAND_NOT_FOUND, "&cCommand not found!");
        yml.addDefault(PLAY_AGAIN_ITEM_NAME, "&aPlay Again");
        yml.addDefault(PLAY_AGAIN_ITEM_LORE, Arrays.asList(" ", "&eClick to play again"));
        yml.addDefault(LEAVE_ITEM_NAME, "&aReturn to Lobby");
        yml.addDefault(LEAVE_ITEM_LORE, Arrays.asList(" ", "&eClick to return to lobby"));
        yml.addDefault(GROUP_BLOCKED, "&cPlayAgain is blocked in this mode!");
        yml.addDefault(COMMAND_COOLDOWN, "&cYou must wait {TIME}s before using that again!");
        yml.addDefault(NOT_PARTY_OWNER, "&cYou are not the owner of any party!");
        yml.options().copyDefaults(true);
        save();
    }

    public void save() {
        try {
            yml.save(file);
        } catch (IOException e) {
            throw new RuntimeException("Unable to save messages", e);
        }
    }
}
