package com.fn10.pastcrafter;

import com.fn10.pastcrafter.blocks.PastCrafterBlocks;
import com.fn10.pastcrafter.blocks.PastCrafterBlocks.TileEntityInit;
import com.fn10.pastcrafter.componate.PastCrafterComponets;
import com.fn10.pastcrafter.items.PastCrafterItems;
import com.fn10.pastcrafter.menu.PastCrafterMenuTypes;
import com.fn10.pastcrafter.menu.PastExtracterScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.FoliageColor;
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
    BlockColors blockcolors = new BlockColors();

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

    public PastCrafer(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        PastCrafterBlocks.register(modEventBus);

        CreativeModTabMaker.register(modEventBus);

        TileEntityInit.TILE_ENTITY_TYPES.register(modEventBus);

        PastCrafterMenuTypes.register(modEventBus);

        SOUNDS.register(modEventBus);

        PastCrafterItems.register(modEventBus);

        PastCrafterComponets.register(modEventBus);

        modEventBus.register(ClientModEvents.class);

        // Register ourselves for server and other game events we are interested in (ok
        // copilot?)
    }

    public static class ClientModEvents {
        @SuppressWarnings("deprecation")
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(PastCrafterMenuTypes.PAST_EXTRACTER_MENU.get(), PastExtracterScreen::new);
            // Register block color handler for Beta_Oak_Leaves
            BlockColors blockColors = Minecraft.getInstance().getBlockColors();
            blockColors.register((state, level, pos, tintIndex) -> {
                if (level != null && pos != null) {
                    // Get the biome foliage color
                    return BiomeColors.getAverageFoliageColor(level, pos);
                }
                // Default foliage color
                return FoliageColor.getDefaultColor();
            }, PastCrafterBlocks.Beta_Oak_Leaves.get());
        }

    }
}
