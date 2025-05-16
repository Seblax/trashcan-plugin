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
import org.seblax.utils.Head;
import org.seblax.utils.Item;

public class Trash implements CommandExecutor {

    public static final String TRASHCAN_NAME = ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Trashcan";
    public static final ItemStack BLACK_GLASS_PANE = glassPane(Material.BLACK_STAINED_GLASS_PANE);
    public static final ItemStack GRAY_GLASS_PANE = glassPane(Material.GRAY_STAINED_GLASS_PANE);

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;

            Inventory inventory = Bukkit.createInventory(player, 45, TRASHCAN_NAME);

            inventory.setItem(0, trashCanItem());
            inventory.setItem(44, barrierItem());

            for(int i = 0; i < 44; i ++){
                if(i != 0 && (i < 8 || i > 36 || i % 9 == 0 || i % 9 == 8)) {
                    ItemStack item = i % 2 == 0 ? BLACK_GLASS_PANE : GRAY_GLASS_PANE;
                    inventory.setItem(i, item);
                }
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
