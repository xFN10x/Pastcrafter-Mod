package com.fn10.pastcrafter;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class PastCrafterTags {

    public static final TagKey<Item> OLD_ITEM = bind("old_item");
    public static final TagKey<Item> BINDABLE = bind("bindable_item");

    private PastCrafterTags() {
    }

    private static TagKey<Item> bind(String p_203855_) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(PastCrafer.MID, p_203855_));
    }

    public static TagKey<Item> create(String namepsace, String path) {
        return create(ResourceLocation.fromNamespaceAndPath(namepsace, path));
    }

    public static TagKey<Item> create(ResourceLocation name) {
        return TagKey.create(Registries.ITEM, name);
    }
}
