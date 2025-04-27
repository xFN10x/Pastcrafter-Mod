package com.fn10.pastcrafter.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import com.fn10.pastcrafter.PastCrafer;
import com.fn10.pastcrafter.PastCrafterTags;
import com.fn10.pastcrafter.blocks.PastCrafterBlocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
        public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                        @Nullable ExistingFileHelper existingFileHelper) {
                super(output, lookupProvider, PastCrafer.MID, existingFileHelper);
        }

        @Override
        protected void addTags(@SuppressWarnings("null") HolderLookup.Provider pProvider) {
                this.tag(BlockTags.LOGS_THAT_BURN)
                                .add(PastCrafterBlocks.Beta_Oak_Log.get());

                this.tag(BlockTags.PLANKS)
                                .add(PastCrafterBlocks.Beta_Oak_Planks.get());

                this.tag(BlockTags.WOODEN_STAIRS)
                                .add(PastCrafterBlocks.Beta_Oak_Stairs.get());

                this.tag(PastCrafterTags.OLD_BLOCK)
                                .add(PastCrafterBlocks.Beta_Oak_Log.get())
                                .add(PastCrafterBlocks.Beta_Oak_Planks.get())
                                .add(PastCrafterBlocks.Distant_Memory.get());

        }
}
