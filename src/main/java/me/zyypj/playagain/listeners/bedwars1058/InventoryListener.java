package me.zyypj.playagain.listeners.bedwars1058;

import com.andrei1058.bedwars.api.BedWars;
import com.andrei1058.bedwars.api.arena.IArena;
import me.zyypj.playagain.PlayAgainAddon;
import me.zyypj.playagain.config.ConfigPaths;
import me.zyypj.playagain.utils.Utility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static me.zyypj.playagain.config.ConfigPaths.*;

public class InventoryListener implements Listener {

    BedWars bwAPI = PlayAgainAddon.bw1058Api;
    private static final Map<Player, Long> cooldowns = new HashMap<>();
    private static final int COOLDOWN_TIME = PlayAgainAddon.mainConfig.getInt(ConfigPaths.COOLDOWN_TIME);

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInHand();

        if (bwAPI.getArenaUtil().getArenaByPlayer(player) == null) return;
        IArena arena = bwAPI.getArenaUtil().getArenaByPlayer(player);
        String group = arena.getGroup();

        if (item == null || !item.hasItemMeta() || !item.getItemMeta().hasDisplayName()) return;

        if (item.getItemMeta().getDisplayName().equals(Utility.getMsg(player, PLAY_AGAIN_ITEM_NAME))) {

            if (!PlayAgainAddon.mainConfig.getBoolean(USE_COMMAND)) {
                player.sendMessage(Utility.getMsg(player, COMMAND_NOT_FOUND));
                return;
            }

            long currentTime = System.currentTimeMillis() / 1000;

            if (cooldowns.containsKey(player)) {
                long lastUsed = cooldowns.get(player);
                long timeLeft = COOLDOWN_TIME - (currentTime - lastUsed);

                if (timeLeft > 0) {
                    String cooldownMessage = Utility.getMsg(player, COMMAND_COOLDOWN).replace("{TIME}", String.valueOf(timeLeft));
                    player.sendMessage(cooldownMessage);
                    return;
                }
            }

            cooldowns.put(player, currentTime);

            List<String> blockedGroups = PlayAgainAddon.mainConfig.getList(GROUPS_BLOCKED);
            if (blockedGroups.contains(group)) {
                player.sendMessage(Utility.getMsg(player, GROUP_BLOCKED));
                return;
            }

            String command = PlayAgainAddon.mainConfig.getString(PLAY_AGAIN_ITEM_COMMAND);
            if (command.equalsIgnoreCase("internal")) {

                if (!checkParty(player)) {
                    player.sendMessage(Utility.getMsg(player, NOT_PARTY_OWNER));
                    return;
                }

                arena.removePlayer(player,true);
                bwAPI.getArenaUtil().joinRandomFromGroup(player, group);
            } else {

                if (!checkParty(player)) {
                    player.sendMessage(Utility.getMsg(player, NOT_PARTY_OWNER));
                    return;
                }

                player.performCommand(command);
            }
        } else if (item.getItemMeta().getDisplayName().equals(Utility.getMsg(player, LEAVE_ITEM_NAME))) {
            player.performCommand(PlayAgainAddon.mainConfig.getString(LEAVE_ITEM_COMMAND));
        }
    }

    private boolean checkParty(Player player) {
        return !bwAPI.getPartyUtil().hasParty(player) ||
                bwAPI.getPartyUtil().isOwner(player);
    }
}
