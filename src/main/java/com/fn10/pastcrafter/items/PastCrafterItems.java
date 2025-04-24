package com.fn10.pastcrafter.items;

import java.util.List;

import com.fn10.pastcrafter.PastCrafer;
import com.fn10.pastcrafter.componate.PastCrafterComponets;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PastCrafterItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PastCrafer.MID);

    public static final RegistryObject<Item> HISTORY_BOOK = ITEMS.register("history_book",
            () -> new Item(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1)) {
                @Override
                public boolean isFoil(ItemStack stack) {
                    return true;
                }

                @Override
                public void appendHoverText(ItemStack pStack, TooltipContext pContext,
                        List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                        Float exp = pStack.get(PastCrafterComponets.PAST_EXP.get());

                        if (exp != null) {
                            pTooltipComponents.add(Component.literal(
                            "Past EXP: " + Mth.floor(exp * 100)));
                        } else {
                            pTooltipComponents.add(Component.literal("Past EXP: ???"));
                        }

                    

                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                }
            });

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
