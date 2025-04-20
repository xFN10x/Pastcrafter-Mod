package com.fn10.pastcrafter;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(PastCrafer.MID)
public class PastCrafer {

    public static final String MID = "pastcrafter"; 

    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MID);
    // Create a Deferred Register to hold Items which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MID);

    //Blocks
    public static final RegistryObject<Block> Old_Oak_Log = BLOCKS.register("old_oak_log", () -> new Block(BlockBehaviour.Properties.of().isRedstoneConductor(null).explosionResistance(2).ignitedByLava().mapColor(MapColor.WOOD).strength(2)));

    public PastCrafer(FMLJavaModLoadingContext context)  {
        IEventBus modEventBus = context.getModEventBus();
        
        BLOCKS.register(modEventBus);
    }
}

