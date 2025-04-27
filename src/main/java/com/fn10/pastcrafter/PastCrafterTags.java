package com.fn10.pastcrafter;

import net.minecraft.world.level.block.Block;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class PastCrafterTags {
    //Item Tags
    public static final TagKey<Item> OLD_ITEM = bind("old_item", Registries.ITEM);
    public static final TagKey<Item> BINDABLE = bind("bindable_item", Registries.ITEM);
    

    //Block Tags
    public static final TagKey<Block> OLD_BLOCK = bind("old_block", Registries.BLOCK);

    private PastCrafterTags() {
    }

    private static <T> TagKey<T> bind(String p_203855_, ResourceKey<Registry<T>> reg) {
        return TagKey.create(reg, ResourceLocation.fromNamespaceAndPath(PastCrafer.MID, p_203855_));
    }

    public static <T> TagKey<T> create(ResourceLocation name, ResourceKey<Registry<T>> reg) {
        return TagKey.create(reg, name);
    }
}
