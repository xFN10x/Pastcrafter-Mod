package com.fn10.pastcrafter;

import java.util.function.Supplier;

import com.fn10.pastcrafter.blocks.PastCrafterBlocks;

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
            Supplier<Item> blockItemFactory = () -> new BlockItem(block, properties);
            //if (event2.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            //    event2.accept(blockItemFactory);
            //    event2.accept(block); // Takes in an ItemLike, assumes block has registered item
            //  }
            event.register(ForgeRegistries.Keys.ITEMS, blockRegistryObject.getId(), blockItemFactory);
        });
    }
}
}
