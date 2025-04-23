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

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture,
            CompletableFuture<TagLookup<Block>> lookupCompletableFuture,
            @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, completableFuture, lookupCompletableFuture, PastCrafer.MID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ItemTags.LOGS_THAT_BURN)
                .add(PastCrafterBlocks.Old_Oak_Log.get().asItem());

        tag(PastCrafterTags.OLD_ITEM)
                .add(PastCrafterBlocks.Old_Oak_Log.get().asItem())
                .add(PastCrafterBlocks.Old_Oak_Leaves.get().asItem())
                .add(PastCrafterBlocks.Old_Oak_Sapling.get().asItem());
        tag(PastCrafterTags.BINDABLE)
                .add(Items.BOOK);

    }
}
