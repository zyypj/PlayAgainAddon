package me.zyypj.playagain.command.bedwars2023;

import com.tomkeuper.bedwars.api.arena.IArena;
import com.tomkeuper.bedwars.api.BedWars;
import me.zyypj.playagain.PlayAgainAddon;
import me.zyypj.playagain.listeners.bedwars2023.ArenaEvent;
import me.zyypj.playagain.utils.Utility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static me.zyypj.playagain.config.ConfigPaths.*;

public class PlayAgainCommand implements CommandExecutor {

    BedWars bwAPI = PlayAgainAddon.bw2023Api;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player player)) return true;

        if (args.length == 0) {

            if (!PlayAgainAddon.mainConfig.getBoolean(USE_COMMAND)) {
                player.sendMessage(Utility.getMsg(player, COMMAND_NOT_FOUND));
                return true;
            }

            IArena arena = bwAPI.getArenaUtil().getArenaByPlayer(player);

            if (arena == null) {

                if (ArenaEvent.lastMatch.containsKey(player)) {
                    String group = ArenaEvent.lastMatch.get(player);
                    bwAPI.getArenaUtil().joinRandomFromGroup(player, group);
                    return true;
                }

                player.sendMessage(Utility.getMsg(player, PLAYER_NOT_IN_ARENA));
                return true;
            }

            String group = arena.getGroup();
            arena.removePlayer(player, true);
            bwAPI.getArenaUtil().joinRandomFromGroup(player, group);
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
}
