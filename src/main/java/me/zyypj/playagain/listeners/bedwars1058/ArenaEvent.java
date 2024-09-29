package me.zyypj.playagain.listeners.bedwars1058;

import com.cryptomorin.xseries.XMaterial;
import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.api.events.gameplay.GameEndEvent;
import me.zyypj.playagain.PlayAgainAddon;
import me.zyypj.playagain.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static me.zyypj.playagain.config.ConfigPaths.*;

public class ArenaEvent implements Listener {

    public static Map<Player, String> lastMatch = new HashMap<>();

    @EventHandler
    public void onGameStateChange(GameEndEvent e) {
        IArena arena = e.getArena();
        String group = arena.getGroup();

        List<UUID> winnersUUID = e.getWinners();
        if (winnersUUID == null) return;
        for (UUID winnerUUID : winnersUUID) {
            Player player = Bukkit.getPlayer(winnerUUID);
            if (player == null) return;

            if (lastMatch.containsKey(player)) {
                lastMatch.remove(player);
                lastMatch.put(player, group);
            }

            Inventory inventory = player.getInventory();

            int playAgainSlot = PlayAgainAddon.mainConfig.getInt(PLAY_AGAIN_ITEM_SLOT) - 1;
            int lobbySlot = PlayAgainAddon.mainConfig.getInt(LEAVE_ITEM_SLOT) - 1;

            Optional<XMaterial> playAgainXMaterial = XMaterial.matchXMaterial(PlayAgainAddon.mainConfig.getString(PLAY_AGAIN_ITEM_TYPE));
            if (playAgainXMaterial.isEmpty()) {
                Utility.warn("Invalid material for PLAY_AGAIN_ITEM_TYPE: " + PlayAgainAddon.mainConfig.getString(PLAY_AGAIN_ITEM_TYPE));
                continue;
            }
            ItemStack playAgainItem = playAgainXMaterial.get().parseItem();
            assert playAgainItem != null;
            ItemMeta playAgainMeta = playAgainItem.getItemMeta();
            playAgainMeta.setDisplayName(Utility.getMsg(player, PLAY_AGAIN_ITEM_NAME));
            playAgainMeta.setLore(Utility.getListMsg(player, PLAY_AGAIN_ITEM_LORE));
            playAgainItem.setItemMeta(playAgainMeta);

            Optional<XMaterial> lobbyXMaterial = XMaterial.matchXMaterial(PlayAgainAddon.mainConfig.getString(LEAVE_ITEM_TYPE));
            if (lobbyXMaterial.isEmpty()) {
                Utility.warn("Invalid material for LEAVE_ITEM_TYPE: " + PlayAgainAddon.mainConfig.getString(LEAVE_ITEM_TYPE));
                continue;
            }
            ItemStack lobbyItem = lobbyXMaterial.get().parseItem();
            assert lobbyItem != null;
            ItemMeta lobbyMeta = lobbyItem.getItemMeta();
            lobbyMeta.setDisplayName(Utility.getMsg(player, LEAVE_ITEM_NAME));
            lobbyMeta.setLore(Utility.getListMsg(player, LEAVE_ITEM_LORE));
            lobbyItem.setItemMeta(lobbyMeta);

            Bukkit.getScheduler().runTaskLater(PlayAgainAddon.getPlugins(), () -> {

                if (playAgainSlot >= 0 && playAgainSlot < inventory.getSize()) {
                    inventory.setItem(playAgainSlot, playAgainItem);
                } else {
                    Utility.warn("Invalid PLAY_AGAIN_ITEM_SLOT: " + playAgainSlot);
                }

                if (lobbySlot >= 0 && lobbySlot < inventory.getSize()) {
                    inventory.setItem(lobbySlot, lobbyItem);
                } else {
                    Utility.warn("Invalid LEAVE_ITEM_SLOT: " + lobbySlot);
                }
            }, 1L);
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
            event.setCancelled(true);
        }
    }
}