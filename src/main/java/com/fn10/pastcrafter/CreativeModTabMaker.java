package com.fn10.pastcrafter;

import com.fn10.pastcrafter.blocks.PastCrafterBlocks;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreativeModTabMaker {

    public static void register(IEventBus eventBus) {
        REGISTRAR.register(eventBus);
    }

    public static final String MID = PastCrafer.MID;
    public static final DeferredRegister<CreativeModeTab> REGISTRAR = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MID);

    public static final RegistryObject<CreativeModeTab> CREATIVE_TAB = REGISTRAR.register("pastcrafter", () -> CreativeModeTab.builder()
    // Set name of tab to display
    .title(Component.translatable("item_group." + MID + ".pastcrafter"))
    //Set icon of creative tab
    .icon(() -> new ItemStack(PastCrafterBlocks.Old_Oak_Sapling.get().asItem()))

    .displayItems((parameters, output) -> {
        PastCrafterBlocks.BLOCKS.getEntries().forEach( (blockRegistryObject) -> {
            output.accept(blockRegistryObject.get().asItem());
        });
        PastCrafer.ITEMS.getEntries().forEach( (itemRegistryObject) -> {
            output.accept(new ItemStack(itemRegistryObject.get()));
        });
    })

    .build()
    );

}
