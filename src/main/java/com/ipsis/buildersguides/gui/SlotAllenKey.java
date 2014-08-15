package com.ipsis.buildersguides.gui;

import com.ipsis.buildersguides.item.BGItems;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotAllenKey extends Slot {

    public SlotAllenKey(IInventory inventory, int x, int y, int z) {

        super(inventory, x, y, z);
    }

    @Override
    public boolean isItemValid(ItemStack itemStack) {

        return itemStack.getItem() == BGItems.itemAllenKey;
    }
}
