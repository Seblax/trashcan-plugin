package org.seblax.ui;

import org.bukkit.inventory.ItemStack;

/**
 * Represents a single slot in a custom UI.
 * Each slot has a position, an item, and optional behavior flags for closing or emptying trash.
 */
public class SlotUI {
    ItemUI itemUI;
    int position;
    boolean closeTrash;
    boolean emptyTrash;

    /**
     * Constructs a SlotUI with its position and item configuration.
     *
     * @param position    The index of the slot in the inventory.
     * @param itemUI      The visual/item configuration to display in this slot.
     * @param closeTrash  If true, clicking this slot should trigger the "close trash" action.
     * @param emptyTrash  If true, clicking this slot should trigger the "empty trash" action.
     */
    public SlotUI(int position, ItemUI itemUI, boolean closeTrash, boolean emptyTrash) {
        this.position = position;
        this.itemUI = itemUI;
        this.closeTrash = closeTrash;
        this.emptyTrash = emptyTrash;
    }

    /**
     * Returns the ItemStack representation of this slot's item.
     *
     * @return The ItemStack to be placed in the UI slot.
     */
    public ItemStack getItemStack() {
        return this.itemUI.getItemStack();
    }

    /**
     * Indicates whether this slot triggers the "close trash" action.
     *
     * @return true if it can close trash; false otherwise.
     */
    public boolean canCloseTrash() {
        return closeTrash;
    }

    /**
     * Indicates whether this slot triggers the "empty trash" action.
     *
     * @return true if it can close or empty trash.
     */
    public boolean canEmptyTrash() {
        return closeTrash || emptyTrash;
    }

    /**
     * Returns the slot's inventory index.
     *
     * @return The position index.
     */
    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "[" +
                "Position: " + getPosition() +
                " Can Close Trash: " + canCloseTrash() +
                " Can Empty Trash: " + canEmptyTrash() +
                " Item: " + itemUI + "]";
    }
}