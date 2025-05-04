package com.fn10.pastcrafter.items.customs;

import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;

public class HammerTool extends DiggerItem {

    public HammerTool(Tier p_204110_, TagKey<Block> p_204111_, Properties p_204112_) {
        super(p_204110_, p_204111_, p_204112_);
        
    }

    @SuppressWarnings("null")
    private static boolean playerHasShieldUseIntent(UseOnContext p_343474_) {
        Player player = p_343474_.getPlayer();
        return p_343474_.getHand().equals(InteractionHand.MAIN_HAND) && player.getOffhandItem().is(Items.SHIELD) && !player.isSecondaryUseActive();
    }

    @Override
    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
        return true;
    }

}
