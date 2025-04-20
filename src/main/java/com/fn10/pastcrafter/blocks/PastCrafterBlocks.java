package com.fn10.pastcrafter.blocks;

import com.fn10.pastcrafter.PastCrafer;
import com.fn10.pastcrafter.worldgen.tree.OldOakGrower;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PastCrafterBlocks {

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    public static final String MID = PastCrafer.MID;

    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MID);

    public static final RegistryObject<Block> Old_Oak_Log = BLOCKS.register("old_oak_log",
     () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).mapColor(MapColor.WOOD)));
    //() -> new Block(BlockBehaviour.Properties.of().destroyTime(2)));
    public static final RegistryObject<Block> Old_Oak_Sapling = BLOCKS.register("old_oak_sapling",
     () -> new SaplingBlock(OldOakGrower.OLD_OAK,BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING).mapColor(MapColor.COLOR_GREEN)));

}
