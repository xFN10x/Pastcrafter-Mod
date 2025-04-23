package com.fn10.pastcrafter;

import com.fn10.pastcrafter.blocks.PastCrafterBlocks;
import com.fn10.pastcrafter.blocks.PastCrafterBlocks.TileEntityInit;
import com.fn10.pastcrafter.menu.PastCrafterMenuTypes;
import com.fn10.pastcrafter.menu.PastExtracterScreen;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
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

    // Create a Deferred Register to hold Items which will all be registered under
    // the "examplemod" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MID);
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS,
            MID);

    public static final SoundEvent PAST_EXTRACTER_START_SOUND = SoundEvent.createVariableRangeEvent(
            ResourceLocation.fromNamespaceAndPath(PastCrafer.MID, "past_extracter_process_start"));
    public static final SoundEvent PAST_EXTRACTER_TICK_SOUND = SoundEvent.createVariableRangeEvent(
            ResourceLocation.fromNamespaceAndPath(PastCrafer.MID, "past_extracter_process_tick"));
            public static final SoundEvent PAST_EXTRACTER_STOP_SOUND = SoundEvent.createVariableRangeEvent(
            ResourceLocation.fromNamespaceAndPath(PastCrafer.MID, "past_extracter_process_finished"));

    public class Math {
        // Returns if isthis % mulofthis (Example: 120 % 4 = true)
        public static boolean isMultipleOf(int isthis, int mulofthis) {
            if (isthis % mulofthis == 0) {
                return true;
            } else {
                return false;
            }
        }

    }

    private void setup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {

            //TODO When adding new past blocks, add them to this list!
            ItemProperties.register(PastCrafterBlocks.Old_Oak_Log.get().asItem(),
                    ResourceLocation.fromNamespaceAndPath(MID, "past_exp"), (stack, level, living, id) -> {
                        Float past_exp = 0.02f; //Past EXP divided by 100, cause stupid limitations
                        
                        return past_exp;
                    });
        });
    }

    public PastCrafer(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        PastCrafterBlocks.register(modEventBus);

        CreativeModTabMaker.register(modEventBus);

        TileEntityInit.TILE_ENTITY_TYPES.register(modEventBus);

        PastCrafterMenuTypes.register(modEventBus);

        SOUNDS.register(modEventBus);

        modEventBus.register(ClientModEvents.class);

        // Register ourselves for server and other game events we are interested in (ok
        // copilot?)
    }

    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(PastCrafterMenuTypes.PAST_EXTRACTER_MENU.get(), PastExtracterScreen::new);
        }
    }
}
