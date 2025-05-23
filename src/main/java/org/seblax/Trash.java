package org.seblax;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.seblax.ui.SlotUI;
import org.seblax.utils.Head;
import org.seblax.utils.Item;

import java.util.List;
import java.util.Map;

public class Trash implements CommandExecutor {

    public static final String TRASHCAN_NAME = Trashcan.UI_CONFIGURATION.getTrashcanName();
    public static final int TRASHCAN_SIZE = Trashcan.UI_CONFIGURATION.getSizeLevel();
    public static final List<SlotUI> TRASHCAN_UI_ITEMS = Trashcan.UI_CONFIGURATION.getInventory();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(commandSender instanceof Player player) {
            Inventory inventory = Bukkit.createInventory(player, TRASHCAN_SIZE, TRASHCAN_NAME);

            for (SlotUI slot: TRASHCAN_UI_ITEMS){
                if(slot.getPosition() > TRASHCAN_SIZE - 1) continue;
                inventory.setItem(slot.getPosition(), slot.getItemStack());
            }

            player.openInventory(inventory);
        }

        return false;
    }

    private ItemStack trashCanItem(){
        String name = String.format("%s%sTrashcan",ChatColor.DARK_RED, ChatColor.BOLD);

        String[] lore = {
            "Put your trash here to remove it.",
            "Click on me to clear all your shit!"
        };

        return Head.getPlayerHeadByURL("be0fd10199e8e4fcdabcae4f85c85918127a7c5553ad235f01c56d18bb9470d3",name, lore);
    }

    private ItemStack barrierItem(){
        String name = String.format("%s%s%s",ChatColor.DARK_RED, ChatColor.BOLD, "Close Trashcan");
        return Item.newItem(Material.BARRIER, name);
    }

    public static ItemStack glassPane(Material material){
        return Item.newItem(material);
    }
}
