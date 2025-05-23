package org.seblax.ui;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.seblax.utils.Head;
import org.seblax.utils.Item;

import java.util.List;

public record ItemUI(String displayName, List<String> lore, String item, String skull, boolean enchanted, int count) {
    public ItemUI(String displayName, List<String> lore, String item, String skull, boolean enchanted, int count) {
        this.displayName = displayName == null ? " ": displayName;
        this.lore = lore;
        this.item = item == null ? "black_stained_glass_pane": item;
        this.skull = skull;
        this.enchanted = enchanted;
        this.count = Math.max(count, 1);
    }

    public boolean isHead() {
        return this.skull != null;
    }

    public ItemStack getItemStack() {
        ItemStack item = null;

        if (isHead()) {
            item = Head.getPlayerHeadByURL(skull, this.displayName, this.lore);
        } else {
            item = Item.newItem(Material.valueOf(this.item.toUpperCase().trim()), this.displayName, this.lore);
        }

        ItemMeta meta = item.getItemMeta();

        assert meta != null;
        meta.setEnchantmentGlintOverride(enchanted());
        item.setItemMeta(meta);
        item.setAmount(this.count);

        return item;
    }

    @Override
    public String toString() {
        return "[" +
                "DisplayName: " + displayName + ", " +
                "Lore: " + lore + ", " +
                "Item: " + item + ", " +
                "Skull: " + skull + ", " +
                "Enchanted: " + enchanted + ", " +
                "Count: " + count + "]";
    }
}
