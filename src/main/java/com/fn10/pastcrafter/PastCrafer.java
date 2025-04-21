package com.fn10.pastcrafter;


import com.fn10.pastcrafter.blocks.PastCrafterBlocks;
import com.fn10.pastcrafter.blocks.PastCrafterBlocks.TileEntityInit;
import com.fn10.pastcrafter.menu.PastCrafterMenuTypes;
import com.fn10.pastcrafter.menu.PastExtracterScreen;

import net.minecraft.client.gui.screens.MenuScreens;


//import java.rmi.registry.Registry;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
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

        TileEntityInit.TILE_ENTITY_TYPES.register(modEventBus);

        PastCrafterMenuTypes.register(modEventBus);
        
        modEventBus.register(ClientModEvents.class);

        // Register ourselves for server and other game events we are interested in (ok copilot?)
    }

    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(PastCrafterMenuTypes.PAST_EXTRACTER_MENU.get(), PastExtracterScreen::new);
        }
    }
}

