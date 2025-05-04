package com.fn10.pastcrafter.datagen;

import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import com.fn10.pastcrafter.PastCrafer;
import com.fn10.pastcrafter.blocks.PastCrafterBlocks;
import com.fn10.pastcrafter.blocks.PastStairs;

public class PCBlockStateP extends BlockStateProvider {
    public PCBlockStateP(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, PastCrafer.MID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // thanks Kaupenjoe for the datagen stuff, (the base scripts, which i modified)
        // some blocks were already made
        // manually, dont add them!

        logblockmaker(PastCrafterBlocks.Beta_Oak_Log, "block/beta_oak_log", "block/beta_log_top");
        blockWithItem(PastCrafterBlocks.Beta_Oak_Planks);
        blockWithItem(PastCrafterBlocks.Beta_Cobblestone);
        saplingBlock(PastCrafterBlocks.Beta_Oak_Sapling);
        leavesBlock(PastCrafterBlocks.Beta_Oak_Leaves);
        blockWithItem(PastCrafterBlocks.Beta_Dirt);
        blockWithItemCustom(PastCrafterBlocks.Beta_Grass, new ModelFile.UncheckedModelFile(
                "pastcrafter:block/beta_grass_model"));
        blockWithItemCustom(PastCrafterBlocks.Binding_Table, new ModelFile.UncheckedModelFile(
                "pastcrafter:block/binding_table_model"));

        yaxisBlock(PastCrafterBlocks.Beta_Oak_Stairs.get(), new ModelFile.UncheckedModelFile(
                "pastcrafter:block/beta_stairs"));

        blockWithItemCustom(PastCrafterBlocks.Distant_Memory, new ModelFile.UncheckedModelFile(
                "pastcrafter:block/distant_memory"));
    }

    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),
                        blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),
                        ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void logblockmaker(RegistryObject<Block> blockRegistryObject, String sidetexter, String toptexter) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().cubeColumn(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),
                        ResourceLocation.fromNamespaceAndPath(PastCrafer.MID, sidetexter),
                        ResourceLocation.fromNamespaceAndPath(PastCrafer.MID, toptexter)));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    public void yaxisBlock(PastStairs block, ModelFile vertical) {
        getVariantBuilder(block)
                .partialState().with(PastStairs.FACING, Direction.NORTH)
                .modelForState().modelFile(vertical).rotationY(0).addModel()
                .partialState().with(PastStairs.FACING, Direction.EAST)
                .modelForState().modelFile(vertical).rotationY(90).addModel()
                .partialState().with(PastStairs.FACING, Direction.SOUTH)
                .modelForState().modelFile(vertical).rotationY(180).addModel()
                .partialState().with(PastStairs.FACING, Direction.WEST)
                .modelForState().modelFile(vertical).rotationY(270).addModel();
        simpleBlockItem(block, vertical);
    }

    private void blockWithItemCustom(RegistryObject<Block> blockRegistryObject, ModelFile model) {
        simpleBlockWithItem(blockRegistryObject.get(), model);
    }

    @SuppressWarnings("unused")
    private void blockItem(RegistryObject<? extends Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("pastcrafter:block/" +
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    @SuppressWarnings("unused")
    private void blockItem(RegistryObject<? extends Block> blockRegistryObject, String appendix) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("pastcrafter:block/" +
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath() + appendix));
    }
}
