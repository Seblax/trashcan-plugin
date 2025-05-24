package org.seblax.ui;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.seblax.utils.Head;
import org.seblax.utils.Item;

import java.util.List;

/**
 * Represents an item in a UI, such as a menu or inventory slot.
 * Can be a regular item or a custom player head.
 *
 * @param displayName The name shown on the item.
 * @param lore        The lore (description lines) of the item.
 * @param item        The name of the item material.
 * @param skull       The base64 or URL for a custom player head (optional).
 * @param enchanted   Whether the item should display an enchantment glint.
 * @param count       The quantity of the item stack (minimum 1).
 */
public record ItemUI(String displayName, List<String> lore, String item, String skull, boolean enchanted, int count) {

    /**
     * Constructs a new ItemUI with validation and defaults.
     *
     * @param displayName The name shown on the item (defaults to " " if null).
     * @param lore        Description lines (can be null or empty).
     * @param item        Bukkit Material name (defaults to "black_stained_glass_pane" if null).
     * @param skull       URL or base64 string for head texture (null if not a head).
     * @param enchanted   Whether to apply enchantment glint.
     * @param count       Quantity of the item stack (minimum 1).
     */
    public ItemUI(String displayName, List<String> lore, String item, String skull, boolean enchanted, int count) {
        this.displayName = displayName == null ? " " : displayName;
        this.lore = lore;
        this.item = item == null ? "black_stained_glass_pane" : item;
        this.skull = skull;
        this.enchanted = enchanted;
        this.count = Math.max(count, 1);
    }

    /**
     * Checks if the item is a custom head based on the presence of a skull texture.
     *
     * @return true if it's a player head; false otherwise.
     */
    public boolean isHead() {
        return this.skull != null;
    }

    /**
     * Builds and returns a Bukkit ItemStack based on the record's data.
     *
     * @return A customized ItemStack with name, lore, enchantment, and amount.
     */
    public ItemStack getItemStack() {
        ItemStack item;

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