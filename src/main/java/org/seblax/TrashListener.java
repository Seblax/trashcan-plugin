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
import org.seblax.utils.Messages;
import org.seblax.utils.RainbowParticleDust;

public class TrashListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(ChatColor.translateAlternateColorCodes( '&', e.getView().getTitle()).equalsIgnoreCase(Trash.TRASHCAN_NAME) &&
        e.getCurrentItem() != null) {
            int slot = e.getRawSlot();
            Player player = (Player) e.getWhoClicked();

            if((slot < 8 || slot > 36 || slot % 9 == 0 || slot % 9 == 8) && slot < 45) {
                e.setCancelled(true);

                if (slot == 44){
                    player.closeInventory();
                }else if(slot == 0){
                    if(ClearInventory(e.getInventory())){
                        deleteMessage(player);
                    };
                }
            }
        }
    }


    private boolean ClearInventory(Inventory inv){
        boolean object = false;
        for(int i = 10; i < 34 + 1; i ++){
            if(i % 9 != 0 && i % 9 != 8) {
                object |= !(inv.getItem(i) == null);
                inv.setItem(i, null);
            }
        }
        return object;
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if(ChatColor.translateAlternateColorCodes( '&', e.getView().getTitle()).equalsIgnoreCase(Trash.TRASHCAN_NAME)){
            if(e.getPlayer() instanceof Player) {
                Player player = (Player) e.getPlayer();
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
