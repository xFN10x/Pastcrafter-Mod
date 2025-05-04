package com.fn10.pastcrafter.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import com.fn10.pastcrafter.PastCrafer;
import com.fn10.pastcrafter.PastCrafterTags;
import com.fn10.pastcrafter.blocks.PastCrafterBlocks;
import com.fn10.pastcrafter.items.PastCrafterItems;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
        public ModItemTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture,
                        CompletableFuture<TagLookup<Block>> lookupCompletableFuture,
                        @Nullable ExistingFileHelper existingFileHelper) {
                super(packOutput, completableFuture, lookupCompletableFuture, PastCrafer.MID, existingFileHelper);
        }

        @Override
        protected void addTags(@SuppressWarnings("null") HolderLookup.Provider pProvider) {
                tag(ItemTags.LOGS_THAT_BURN)
                                .add(PastCrafterBlocks.Beta_Oak_Log.get().asItem());

                tag(PastCrafterTags.OLD_ITEM)
                                .add(PastCrafterBlocks.Beta_Oak_Log.get().asItem())
                                .add(PastCrafterBlocks.Beta_Oak_Planks.get().asItem())
                                .add(PastCrafterBlocks.Distant_Memory.get().asItem())
                                .add(PastCrafterBlocks.Beta_Cobblestone.get().asItem())
                                .add(PastCrafterBlocks.Beta_Dirt.get().asItem())
                                .add(PastCrafterBlocks.Beta_Grass.get().asItem())
                                .add(PastCrafterBlocks.Beta_Oak_Leaves.get().asItem())
                                .add(PastCrafterBlocks.Beta_Oak_Stairs.get().asItem())
                                .add(PastCrafterBlocks.Beta_Oak_Sapling.get().asItem())
                                .add(PastCrafterBlocks.Beta_Grass.get().asItem())

                                .add(PastCrafterItems.Old_Wooden_Sword.get());
                tag(PastCrafterTags.BINDABLE)
                                .add(Items.BOOK)
                                .add(PastCrafterItems.HISTORY_BOOK.get());
                tag(ItemTags.PLANKS)
                                .add(PastCrafterBlocks.Beta_Oak_Planks.get().asItem());

        }
}
