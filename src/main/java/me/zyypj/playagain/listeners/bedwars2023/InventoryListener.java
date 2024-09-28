package me.zyypj.playagain.listeners.bedwars2023;

import com.tomkeuper.bedwars.api.BedWars;
import com.tomkeuper.bedwars.api.arena.IArena;
import me.zyypj.playagain.PlayAgainAddon;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import static me.zyypj.playagain.config.ConfigPaths.*;

public class InventoryListener implements Listener {

    BedWars bwAPI = PlayAgainAddon.bw2023Api;

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInHand();

        if (!bwAPI.getArenaUtil().isPlaying(player)) return;
        IArena arena = bwAPI.getArenaUtil().getArenaByPlayer(player);
        String group = arena.getGroup();

        if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
            if (item.getItemMeta().getDisplayName().equals(PLAY_AGAIN_ITEM_NAME)) {
                String command = PlayAgainAddon.mainConfig.getString(PLAY_AGAIN_ITEM_COMMAND);
                if (command.equalsIgnoreCase("internal")) {
                    bwAPI.getArenaUtil().joinRandomFromGroup(player, group);
                } else {
                    player.performCommand(command);
                }
            } else if (item.getItemMeta().getDisplayName().equals(LEAVE_ITEM_NAME)) {
                player.performCommand(PlayAgainAddon.mainConfig.getString(LEAVE_ITEM_COMMAND));
            }
        }
    }
}
