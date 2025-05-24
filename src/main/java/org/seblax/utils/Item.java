package org.seblax.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

/**
 * Utility class for creating custom ItemStacks with optional names and lore.
 */
public class Item {

    /**
     * Creates a new item with the given material and a blank name.
     *
     * @param m The material of the item.
     * @return The created ItemStack.
     */
    public static ItemStack newItem(Material m){
        return newItem(m, " ");
    }

    /**
     * Creates a new item with the given material and display name.
     *
     * @param m The material of the item.
     * @param itemName The display name of the item.
     * @return The created ItemStack.
     */
    public static ItemStack newItem(Material m, String itemName){
        return newItem(m, itemName, List.of());
    }

    /**
     * Creates a new item with the given material, name, and lore list.
     *
     * @param m The material of the item.
     * @param itemName The display name of the item.
     * @param lore The lore (description lines) of the item.
     * @return The created ItemStack.
     */
    public static ItemStack newItem(Material m, String itemName, List<String> lore){
//        String[] arrayLore = new String[lore.size()];
//        lore.forEach(x -> arrayLore[lore.indexOf(x)] = x); // Less efficient for duplicate strings.
        String[] arrayLore = lore.toArray(new String[0]);
        return newItem(m, itemName, arrayLore);
    }

    /**
     * Creates a new item with the given material, name, and lore array.
     *
     * @param m The material of the item.
     * @param itemName The display name of the item.
     * @param lore The lore (description lines) of the item.
     * @return The created ItemStack.
     */
    public static ItemStack newItem(Material m, String itemName, String[] lore){
        ItemStack item = new ItemStack(m);
        ItemMeta meta = item.getItemMeta();

        assert meta != null;
        meta.setDisplayName(itemName);

        if (lore != null)
            meta.setLore(Arrays.stream(lore).toList());

        item.setItemMeta(meta);
        return item;
    }
}