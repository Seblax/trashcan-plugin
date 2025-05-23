package org.seblax.ui;

import org.bukkit.inventory.ItemStack;

public class SlotUI {
    ItemUI itemUI;
    int position;
    boolean closeTrash;
    boolean emptyTrash;


    public SlotUI(int position, ItemUI itemUI, boolean closeTrash, boolean emptyTrash) {
        this.position = position;
        this.itemUI = itemUI;
        this.closeTrash = closeTrash;
        this.emptyTrash = emptyTrash;
    }

    public ItemStack getItemStack() {
        return this.itemUI.getItemStack();
    }

    public boolean canCloseTrash() {
        return closeTrash;
    }

    public boolean canEmptyTrash() {
        return closeTrash || emptyTrash;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "[" +
                "Position: " + getPosition() +
                " Can Close Trash: " + canCloseTrash() +
                " Can Empty Trrash: " + canEmptyTrash() +
                " Item: " + itemUI + "]";
    }
}
