package me.zyypj.playagain.command.bedwars1058;

import com.andrei1058.bedwars.api.arena.GameState;
import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.api.BedWars;
import me.zyypj.playagain.PlayAgainAddon;
import me.zyypj.playagain.config.ConfigPaths;
import me.zyypj.playagain.listeners.bedwars1058.ArenaEvent;
import me.zyypj.playagain.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

import static me.zyypj.playagain.config.ConfigPaths.*;

public class PlayAgainCommand implements CommandExecutor {

    BedWars bwAPI = PlayAgainAddon.bw1058Api;
    private static final Map<Player, Long> cooldowns = new HashMap<>();
    private static final int COOLDOWN_TIME = PlayAgainAddon.mainConfig.getInt(ConfigPaths.COOLDOWN_TIME);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player player)) return true;

        if (args.length == 0) {

            if (!PlayAgainAddon.mainConfig.getBoolean(USE_COMMAND)) {
                player.sendMessage(Utility.getMsg(player, COMMAND_NOT_FOUND));
                return true;
            }

            long currentTime = System.currentTimeMillis() / 1000;

            if (cooldowns.containsKey(player)) {
                long lastUsed = cooldowns.get(player);
                long timeLeft = COOLDOWN_TIME - (currentTime - lastUsed);

                if (timeLeft > 0) {
                    String cooldownMessage = Utility.getMsg(player, COMMAND_COOLDOWN).replace("{TIME}", String.valueOf(timeLeft));
                    player.sendMessage(cooldownMessage);
                    return true;
                }
            }

            IArena arena = bwAPI.getArenaUtil().getArenaByPlayer(player);

            cooldowns.put(player, currentTime);

            if (arena == null) {

                if (ArenaEvent.lastMatch.containsKey(player) &&
                        bwAPI.getArenaUtil().getArenaByPlayer(player) == null) {
                    String group = ArenaEvent.lastMatch.get(player);
                    if (!checkParty(player)) {
                        player.sendMessage(Utility.getMsg(player, NOT_PARTY_OWNER));
                        return true;
                    }
                    Bukkit.getScheduler().runTaskLater(PlayAgainAddon.getPlugins(), () -> bwAPI.getArenaUtil().joinRandomFromGroup(player, group), 1L);
                    return true;
                }

                player.sendMessage(Utility.getMsg(player, PLAYER_NOT_IN_ARENA));
                return true;
            }

            String group = arena.getGroup();

            if (arena.getStatus() == GameState.waiting) {
                player.sendMessage(Utility.getMsg(player, PLAYER_IS_IN_ARENA));
                return true;
            }

            if (arena.getStatus() == GameState.playing) {
                player.sendMessage(Utility.getMsg(player, PLAYER_IS_IN_ARENA));
                return true;
            }

            if (bwAPI.getArenaUtil().isSpectating(player)) {
                if (!checkParty(player)) {
                    player.sendMessage(Utility.getMsg(player, NOT_PARTY_OWNER));
                    return true;
                }
                arena.removeSpectator(player, true);
                Bukkit.getScheduler().runTaskLater(PlayAgainAddon.getPlugins(), () -> bwAPI.getArenaUtil().joinRandomFromGroup(player, group), 1L);
                return true;
            }

            if (!checkParty(player)) {
                player.sendMessage(Utility.getMsg(player, NOT_PARTY_OWNER));
                return true;
            }

            arena.removePlayer(player, true);
            Bukkit.getScheduler().runTaskLater(PlayAgainAddon.getPlugins(), () -> bwAPI.getArenaUtil().joinRandomFromGroup(player, group), 1L);
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {

            if (!player.hasPermission(PlayAgainAddon.mainConfig.getString(RELOAD_PERMISSION))) {
                player.sendMessage(Utility.getMsg(player, NO_PERM));
                return true;
            }

            player.sendMessage("§aConfig has been reload.");
            PlayAgainAddon.mainConfig.reload();
            return true;
        }

        player.sendMessage(Utility.getMsg(player, COMMAND_NOT_FOUND));
        return true;
    }

    private boolean checkParty(Player player) {
        return !bwAPI.getPartyUtil().hasParty(player) ||
                bwAPI.getPartyUtil().isOwner(player);
    }
}
