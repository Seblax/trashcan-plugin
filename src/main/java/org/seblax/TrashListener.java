package org.seblax;

import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.seblax.utils.RainbowParticleDust;

public class TrashListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(ChatColor.translateAlternateColorCodes( '&', e.getView().getTitle()).equalsIgnoreCase(Trash.TRASHCAN_NAME) &&
        e.getCurrentItem() != null) {
            int slot = e.getRawSlot();
            Player player = (Player) e.getWhoClicked();

            if(Trashcan.UI_CONFIGURATION.isUiSlot(slot)) {
                e.setCancelled(true);

                if (Trashcan.UI_CONFIGURATION.canClose(slot)){
                    player.closeInventory();
                }else if(Trashcan.UI_CONFIGURATION.canErase(slot)){
                    if(ClearInventory(e.getInventory())){
                        deleteMessage(player);
                    };
                }
            }
        }
    }


    private boolean ClearInventory(Inventory inv){
        boolean object = false;
        for(int i = 0; i < Trashcan.UI_CONFIGURATION.getSizeLevel(); i ++){
            if(!Trashcan.UI_CONFIGURATION.isUiSlot(i)) {
                object |= !(inv.getItem(i) == null);
                inv.setItem(i, null);
            }
        }
        return object;
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if(ChatColor.translateAlternateColorCodes( '&', e.getView().getTitle()).equalsIgnoreCase(Trash.TRASHCAN_NAME)){
            if(e.getPlayer() instanceof Player player) {
                if(ClearInventory(e.getInventory())){
                    deleteMessage(player);
                }else {
                    player.playSound(player,Sound.BLOCK_NOTE_BLOCK_BANJO,1f,1f);
                    RainbowParticleDust.play(player, Particle.DUST);
                }
            }
        }
    }

    private void deleteMessage(Player player){
        player.sendMessage(Trashcan.MESSAGES.getRandomMessage());
        player.playSound(player,Sound.BLOCK_NOTE_BLOCK_BANJO,1f,1f);
        player.playSound(player,Sound.ENTITY_PLAYER_SPLASH_HIGH_SPEED,1f,1f);

        RainbowParticleDust.play(player, Particle.DUST);
    }
}
