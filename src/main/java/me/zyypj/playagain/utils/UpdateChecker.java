package me.zyypj.playagain.utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class UpdateChecker {

    private final Plugin plugin;
    private final int resourceId;

    public UpdateChecker(Plugin plugin, int resourceId) {
        this.plugin = plugin;
        this.resourceId = resourceId;
    }

    public void checkForUpdates() {
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    HttpURLConnection connection = (HttpURLConnection) new URL("https://api.spigotmc.org/legacy/update.php?resource=" + resourceId).openConnection();
                    connection.setDoOutput(true);
                    connection.setRequestMethod("GET");

                    InputStream inputStream = connection.getInputStream();
                    Scanner scanner = new Scanner(inputStream);
                    if (scanner.hasNext()) {
                        String latestVersion = scanner.next();

                        String currentVersion = "v" + plugin.getDescription().getVersion();

                        if (!currentVersion.equalsIgnoreCase(latestVersion)) {
                            Utility.warn("There is a new update available: " + latestVersion);
                        }
                    }
                } catch (IOException e) {
                    Bukkit.getLogger().severe("Unable to check for updates: " + e.getMessage());
                }
            }
        }.runTaskAsynchronously(plugin);
    }
}