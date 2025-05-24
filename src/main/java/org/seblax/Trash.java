package org.seblax;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.seblax.ui.SlotUI;

import java.util.List;

/**
 * Command executor for the trashcan command.
 * Opens a custom trashcan inventory UI for the player.
 */
public class Trash implements CommandExecutor {

    /**
     * The display name of the trashcan inventory, loaded from configuration.
     */
    public static final String TRASHCAN_NAME = Trashcan.UI_CONFIGURATION.getTrashcanName();

    /**
     * The size (number of slots) of the trashcan inventory, loaded from configuration.
     */
    public static final int TRASHCAN_SIZE = Trashcan.UI_CONFIGURATION.getSizeLevel();

    /**
     * The list of UI slots (items with position and behavior) configured for the trashcan.
     */
    public static final List<SlotUI> TRASHCAN_UI_ITEMS = Trashcan.UI_CONFIGURATION.getInventory();

    /**
     * Handles the /trashcan command execution.
     * Opens a trashcan GUI inventory for the player with configured items.
     *
     * @param commandSender The sender of the command, expected to be a Player.
     * @param command       The command object.
     * @param s             The command label/alias used.
     * @param strings       Command arguments.
     * @return true if the command was successfully handled by a player, false otherwise.
     */
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(commandSender instanceof Player player) {
            Inventory inventory = Bukkit.createInventory(player, TRASHCAN_SIZE, TRASHCAN_NAME);

            // Populate the inventory slots with the configured SlotUI items
            for (SlotUI slot: TRASHCAN_UI_ITEMS){
                if(slot.getPosition() > TRASHCAN_SIZE - 1) continue;
                inventory.setItem(slot.getPosition(), slot.getItemStack());
            }

            player.openInventory(inventory);

            return true;
        }

        return false;
    }
}