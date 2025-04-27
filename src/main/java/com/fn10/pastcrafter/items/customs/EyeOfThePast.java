package com.fn10.pastcrafter.items.customs;

import com.mojang.datafixers.util.Pair;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.EyeOfEnder;
import net.minecraft.world.item.EnderEyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class EyeOfThePast extends EnderEyeItem {

    public EyeOfThePast(Properties p_41180_) {
        super(p_41180_);
    }

    @Override
    public InteractionResult useOn(@SuppressWarnings("null") UseOnContext p_41182_) {
        return InteractionResult.FAIL;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(@SuppressWarnings("null") Level p_41184_, @SuppressWarnings("null") Player p_41185_, @SuppressWarnings("null") InteractionHand p_41186_) {
        ItemStack itemstack = p_41185_.getItemInHand(p_41186_);
        BlockHitResult blockhitresult = getPlayerPOVHitResult(p_41184_, p_41185_, ClipContext.Fluid.NONE);
        if (blockhitresult.getType() == HitResult.Type.BLOCK
                && p_41184_.getBlockState(blockhitresult.getBlockPos()).is(Blocks.END_PORTAL_FRAME)) {
            return InteractionResultHolder.pass(itemstack);
        } else {
            p_41185_.startUsingItem(p_41186_);
            if (p_41184_ instanceof ServerLevel serverlevel) {
                Pair<BlockPos,Holder<Biome>> blockpos = serverlevel.findClosestBiome3d(biome -> biome.is(BiomeTags.IS_FOREST), p_41185_.blockPosition(), 10000, 400, 40000);
                if (blockpos != null) {
                    EyeOfEnder eyeofender = new EyeOfEnder(p_41184_, p_41185_.getX(), p_41185_.getY(0.5),
                            p_41185_.getZ());
                    eyeofender.setItem(itemstack);
                    eyeofender.signalTo(blockpos.getFirst());
                    p_41184_.gameEvent(GameEvent.PROJECTILE_SHOOT, eyeofender.position(),
                            GameEvent.Context.of(p_41185_));
                    p_41184_.addFreshEntity(eyeofender);
                    if (p_41185_ instanceof ServerPlayer serverplayer) {
                        CriteriaTriggers.USED_ENDER_EYE.trigger(serverplayer, blockpos.getFirst());
                    }

                    float f = Mth.lerp(p_41184_.random.nextFloat(), 0.33F, 0.5F);
                    p_41184_.playSound(null, p_41185_.getX(), p_41185_.getY(), p_41185_.getZ(),
                            SoundEvents.ENDER_EYE_LAUNCH, SoundSource.NEUTRAL, 1.0F, f);
                    itemstack.consume(1, p_41185_);
                    p_41185_.awardStat(Stats.ITEM_USED.get(this));
                    p_41185_.swing(p_41186_, true);
                    return InteractionResultHolder.success(itemstack);
                }
            }

            return InteractionResultHolder.consume(itemstack);
        }
    }
}
