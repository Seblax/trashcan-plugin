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

/**
 * Listener class that handles player interactions with the Trashcan inventory UI.
 * Manages clicks inside the Trashcan inventory and inventory close events.
 */
public class TrashListener implements Listener {

    /**
     * Handles clicks inside the Trashcan inventory.
     * Cancels interaction with UI control slots, triggers inventory clearing or closes UI.
     *
     * @param e The inventory click event.
     */
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        // Check if the inventory is the Trashcan UI and the clicked item is not null
        if (ChatColor.translateAlternateColorCodes('&', e.getView().getTitle())
                .equalsIgnoreCase(Trash.TRASHCAN_NAME) && e.getCurrentItem() != null) {

            int slot = e.getRawSlot();
            Player player = (Player) e.getWhoClicked();

            // If clicked slot is part of the UI control slots
            if (Trashcan.UI_CONFIGURATION.isUiSlot(slot)) {
                // Cancel any default behavior to prevent item movement
                e.setCancelled(true);

                // If the slot is configured to close the inventory, close it for the player
                if (Trashcan.UI_CONFIGURATION.canClose(slot)) {
                    player.closeInventory();

                    // If the slot is configured to erase contents, clear the inventory and notify player
                } else if (Trashcan.UI_CONFIGURATION.canErase(slot)) {
                    if (ClearInventory(e.getInventory())) {
                        deleteMessage(player);
                    }
                }
            }
        }
    }

    /**
     * Clears all non-UI slots in the given inventory.
     *
     * @param inv The inventory to clear.
     * @return true if any item was removed, false otherwise.
     */
    private boolean ClearInventory(Inventory inv) {
        boolean removedAny = false;

        // Iterate through all slots based on configured inventory size
        for (int i = 0; i < Trashcan.UI_CONFIGURATION.getSizeLevel(); i++) {
            // Only clear slots that are not part of the UI control slots
            if (!Trashcan.UI_CONFIGURATION.isUiSlot(i)) {
                // Check if slot contains an item
                removedAny |= (inv.getItem(i) != null);
                inv.setItem(i, null);  // Remove the item
            }
        }

        return removedAny;
    }

    /**
     * Handles the event when the Trashcan inventory is closed by the player.
     * Automatically clears the trash slots and plays sounds/particles as feedback.
     *
     * @param e The inventory close event.
     */
    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        // Check if the closed inventory is the Trashcan UI
        if (ChatColor.translateAlternateColorCodes('&', e.getView().getTitle())
                .equalsIgnoreCase(Trash.TRASHCAN_NAME)) {

            if (e.getPlayer() instanceof Player player) {
                // Clear inventory and notify player if any items were removed
                if (ClearInventory(e.getInventory())) {
                    deleteMessage(player);
                } else {
                    // Play feedback sounds and particles if no items were removed
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BANJO, 1f, 1f);
                    RainbowParticleDust.play(player, Particle.DUST);
                }
            }
        }
    }

    /**
     * Sends a message and plays sounds/particles to inform the player that their trash was cleared.
     *
     * @param player The player to notify.
     */
    private void deleteMessage(Player player) {
        player.sendMessage(Trashcan.MESSAGES.getRandomMessage());
        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BANJO, 1f, 1f);
        player.playSound(player, Sound.ENTITY_PLAYER_SPLASH_HIGH_SPEED, 1f, 1f);

        RainbowParticleDust.play(player, Particle.DUST);
    }
}