package com.fn10.pastcrafter.items;

import java.util.List;
import com.fn10.pastcrafter.PastCrafer;
import com.fn10.pastcrafter.component.PastCrafterComponents;
import com.fn10.pastcrafter.items.customs.EyeOfThePast;
import com.fn10.pastcrafter.items.customs.HammerTool;

import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PastCrafterItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PastCrafer.MID);

    public static final RegistryObject<Item> EYE_OF_THE_PAST = ITEMS.register("eye_of_the_past",
            () -> new EyeOfThePast(new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> Old_Wooden_Sword = ITEMS.register("old_wooden_sword",
            () -> new SwordItem(Tiers.WOOD,
                    new Item.Properties().attributes(SwordItem.createAttributes(Tiers.WOOD, 3, -2.4F))));
    public static final RegistryObject<Item> Binding_Hammer = ITEMS.register("binding_hammer",
            () -> new HammerTool(Tiers.IRON,BlockTags.BASE_STONE_OVERWORLD,
                    new Item.Properties().attributes(HammerTool.createAttributes(Tiers.IRON, 13, -3.6f))));

    public static final RegistryObject<Item> HISTORY_BOOK = ITEMS.register("history_book",
            () -> new Item(new Item.Properties().rarity(Rarity.EPIC).stacksTo(1)) {
                @Override
                public boolean isFoil(@SuppressWarnings("null") ItemStack stack) {
                    return true;
                }

                @Override
                public void appendHoverText(@SuppressWarnings("null") ItemStack pStack,
                        @SuppressWarnings("null") TooltipContext pContext,
                        @SuppressWarnings("null") List<Component> pTooltipComponents,
                        @SuppressWarnings("null") TooltipFlag pTooltipFlag) {
                    Float exp = pStack.get(PastCrafterComponents.PAST_EXP.get());

                    if (exp != null) {
                        pTooltipComponents.add(Component.literal("Past EXP: " + Mth.floor(exp)));
                    } else {
                        pTooltipComponents.add(Component.literal("Past EXP: 0"));
                    }

                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                }
            });

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
