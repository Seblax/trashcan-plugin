package org.seblax.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class Item {

    public static ItemStack newItem(Material m){
        return newItem(m," ");
    }

    public static ItemStack newItem(Material m, String itemName){
        return newItem(m,itemName, List.of());
    }

    public static ItemStack newItem(Material m, String itemName, List<String> lore){
        String[] arrayLore = new String[lore.size()];

        lore.forEach( x -> arrayLore[lore.indexOf(x)] = x);
        return newItem(m,itemName, arrayLore);
    }

    public static ItemStack newItem(Material m, String itemName, String[] lore){
        ItemStack item = new ItemStack(m);
        ItemMeta meta = item.getItemMeta();

        assert meta != null;
        meta.setDisplayName(itemName);

        if(lore != null) meta.setLore(Arrays.stream(lore).toList());

        item.setItemMeta(meta);
        return item;
    }
}
