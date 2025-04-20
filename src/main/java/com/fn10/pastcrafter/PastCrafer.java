package com.fn10.pastcrafter;


import com.fn10.pastcrafter.blocks.PastCrafterBlocks;

//import java.rmi.registry.Registry;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(PastCrafer.MID)
public class PastCrafer {

    public static final String MID = "pastcrafter"; 

    
    // Create a Deferred Register to hold Items which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MID);





    public PastCrafer(FMLJavaModLoadingContext context)  {
        IEventBus modEventBus = context.getModEventBus();
        
        PastCrafterBlocks.register(modEventBus);

        CreativeModTabMaker.register(modEventBus);
    }
}

