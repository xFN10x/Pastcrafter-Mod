package com.fn10.pastcrafter.menu;

import javax.annotation.Nullable;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class FilteredSlot extends SlotItemHandler {

    // some of this was taken from here:
    // https://forums.minecraftforge.net/topic/24980-creating-gui-slot-filter/
    // basicly just the class name and constructer

    public TagKey<Item> ALLOWED_TAG;

    public FilteredSlot(ItemStackHandler inventory, int id, int x, int y, @Nullable TagKey<Item> allowedTag) {
        super(inventory, id, x, y);
        this.ALLOWED_TAG = allowedTag;
    }

    @Override
    public boolean mayPlace(ItemStack itemstack) {

        if (itemstack.getTags().anyMatch(tag -> tag.equals(ALLOWED_TAG))) {
            return true;
        }

        return false;
    }
}