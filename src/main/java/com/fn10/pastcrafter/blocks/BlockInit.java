package com.fn10.pastcrafter.blocks;

import java.util.function.Supplier;

import com.fn10.pastcrafter.componate.PastCrafterComponets;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockInit {
    @SubscribeEvent
    public static void onRegisterItems(final RegisterEvent event) {
        if (event.getRegistryKey().equals(ForgeRegistries.Keys.ITEMS)) {
            PastCrafterBlocks.BLOCKS.getEntries().forEach((blockRegistryObject) -> {
                Block block = blockRegistryObject.get();
                if (block instanceof PastBlock || block instanceof PastStairs || block instanceof PastLeavesBlock) {

                    Item.Properties properties = new Item.Properties();
                    if (block instanceof PastBlock) {
                        properties.component(PastCrafterComponets.PAST_EXP.get(),
                                Float.valueOf(block.defaultBlockState()
                                        .getValue(PastBlock.PAST_EXP)));
                    } else if (block instanceof PastStairs) {
                        properties.component(PastCrafterComponets.PAST_EXP.get(),
                                Float.valueOf(block.defaultBlockState()
                                        .getValue(PastStairs.PAST_EXP)));
                    } else if (block instanceof PastLeavesBlock) {
                        properties.component(PastCrafterComponets.PAST_EXP.get(),
                                Float.valueOf(block.defaultBlockState()
                                        .getValue(PastLeavesBlock.PAST_EXP)));
                    } else {
                        return;
                    }

                    Supplier<Item> blockItemFactory = () -> new BlockItem(block, properties);
                    event.register(ForgeRegistries.Keys.ITEMS, blockRegistryObject.getId(), blockItemFactory);
                } else {
                    Item.Properties properties = new Item.Properties();

                    Supplier<Item> blockItemFactory = () -> new BlockItem(block, properties);
                    // if (event2.getTabKey() == CreativeModeTabs.INGREDIENTS) {
                    // event2.accept(blockItemFactory);
                    // event2.accept(block);
                    // }
                    event.register(ForgeRegistries.Keys.ITEMS, blockRegistryObject.getId(), blockItemFactory);
                }
            });
        }
    }
}
