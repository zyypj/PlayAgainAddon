package me.zyypj.playagain.listeners.bedwars1058;

import com.andrei1058.bedwars.api.BedWars;
import com.andrei1058.bedwars.api.arena.IArena;
import me.zyypj.playagain.PlayAgainAddon;
import me.zyypj.playagain.utils.Utility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import static me.zyypj.playagain.config.ConfigPaths.*;

public class InventoryListener implements Listener {

    BedWars bwAPI = PlayAgainAddon.bw1058Api;

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInHand();

        if (!bwAPI.getArenaUtil().isPlaying(player)) return;
        IArena arena = bwAPI.getArenaUtil().getArenaByPlayer(player);
        String group = arena.getGroup();

        if (item == null) return;

        if (item.getItemMeta().getDisplayName().equals(Utility.getMsg(player, PLAY_AGAIN_ITEM_NAME))) {
            String command = PlayAgainAddon.mainConfig.getString(PLAY_AGAIN_ITEM_COMMAND);
            if (command.equalsIgnoreCase("internal")) {
                arena.removePlayer(player,true);
                bwAPI.getArenaUtil().joinRandomFromGroup(player, group);
            } else {
                player.performCommand(command);
            }
        } else if (item.getItemMeta().getDisplayName().equals(Utility.getMsg(player, LEAVE_ITEM_NAME))) {
            player.performCommand(PlayAgainAddon.mainConfig.getString(LEAVE_ITEM_COMMAND));
        }
    }
}
