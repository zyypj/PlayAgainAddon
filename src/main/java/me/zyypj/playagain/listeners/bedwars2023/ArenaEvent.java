package me.zyypj.playagain.listeners.bedwars2023;

import com.tomkeuper.bedwars.api.arena.IArena;
import com.tomkeuper.bedwars.api.events.gameplay.GameEndEvent;
import me.zyypj.playagain.PlayAgainAddon;
import me.zyypj.playagain.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.UUID;

import static me.zyypj.playagain.config.ConfigPaths.*;

public class ArenaEvent implements Listener {

    @EventHandler
    public void onGameStateChange(GameEndEvent e) {
        List<UUID> winnersUUID = e.getWinners();
        if (winnersUUID == null) return;
        for (UUID winnerUUID : winnersUUID) {
            Player player = Bukkit.getPlayer(winnerUUID);
            if (player == null) return;
            Inventory inventory = player.getInventory();

            ItemStack playAgainItem = new ItemStack(Material.getMaterial(PlayAgainAddon.mainConfig.getString(PLAY_AGAIN_ITEM_TYPE)));
            ItemMeta playAgainMeta = playAgainItem.getItemMeta();
            playAgainMeta.setDisplayName(Utility.getMsg(player, PLAY_AGAIN_ITEM_NAME));
            playAgainMeta.setLore(Utility.getListMsg(player, PLAY_AGAIN_ITEM_LORE));
            playAgainItem.setItemMeta(playAgainMeta);
            inventory.setItem(PlayAgainAddon.mainConfig.getInt(PLAY_AGAIN_ITEM_SLOT) - 1, playAgainItem);

            ItemStack lobbyItem = new ItemStack(Material.getMaterial(PlayAgainAddon.mainConfig.getString(LEAVE_ITEM_TYPE)));
            ItemMeta lobbyMeta = lobbyItem.getItemMeta();
            lobbyMeta.setDisplayName(Utility.getMsg(player, LEAVE_ITEM_NAME));
            lobbyMeta.setLore(Utility.getListMsg(player, LEAVE_ITEM_LORE));
            lobbyItem.setItemMeta(lobbyMeta);
            inventory.setItem(PlayAgainAddon.mainConfig.getInt(LEAVE_ITEM_SLOT) - 1, lobbyItem);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        ItemStack clickedItem = event.getCurrentItem();
        if (clickedItem == null || !clickedItem.hasItemMeta()) return;

        String displayName = clickedItem.getItemMeta().getDisplayName();
        Player player = (Player) event.getWhoClicked();

        if (displayName.equals(Utility.getMsg(player, PLAY_AGAIN_ITEM_NAME)) ||
                displayName.equals(Utility.getMsg(player, LEAVE_ITEM_NAME))) {
            event.setCancelled(true); // Cancela a ação
        }
    }
}
