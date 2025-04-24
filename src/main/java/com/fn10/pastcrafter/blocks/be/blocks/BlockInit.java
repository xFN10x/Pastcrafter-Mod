package com.fn10.pastcrafter.blocks.be.blocks;

import java.util.function.Supplier;

import com.fn10.pastcrafter.blocks.PastCrafterBlocks;
import com.fn10.pastcrafter.componate.PastCrafterComponets;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
//import net.minecraftforge.registries.DeferredRegister;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockInit {
@SubscribeEvent
public static void onRegisterItems(final RegisterEvent event) {
    if (event.getRegistryKey().equals(ForgeRegistries.Keys.ITEMS)){
        PastCrafterBlocks.BLOCKS.getEntries().forEach( (blockRegistryObject) -> {
            Block block = blockRegistryObject.get();
            Item.Properties properties = new Item.Properties();
            if (block.equals(PastCrafterBlocks.Old_Oak_Log.get())) {
                properties.component(PastCrafterComponets.PAST_EXP.get(), 2f);
            } else if (block.equals(PastCrafterBlocks.Old_Oak_Leaves.get())) {
                properties.component(PastCrafterComponets.PAST_EXP.get(), 1f);
            } else if (block.equals(PastCrafterBlocks.Old_Oak_Sapling.get())) {
                properties.component(PastCrafterComponets.PAST_EXP.get(), 1f);
            }
            Supplier<Item> blockItemFactory = () -> new BlockItem(block, properties);
            //if (event2.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            //    event2.accept(blockItemFactory);
            //    event2.accept(block);
            //  }
            event.register(ForgeRegistries.Keys.ITEMS, blockRegistryObject.getId(), blockItemFactory);
        });
    }
}
}
