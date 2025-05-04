package com.fn10.pastcrafter.blocks.be.tiles;

import javax.annotation.Nullable;

import com.fn10.pastcrafter.menu.BindingTableMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class BindingTableBlock extends Block {

    // public static final IntegerProperty ITEMS = IntegerProperty.create("items",
    // 0, 2);

    public BindingTableBlock(BlockBehaviour.Properties properties) {
        super(properties);
        // this.registerDefaultState(this.stateDefinition
        // .any()
        // .setValue(ITEMS, Integer.valueOf(0))
        // );
    }

    @SuppressWarnings("null")
    @Override
    protected InteractionResult useWithoutItem(BlockState p_48804_, Level p_48805_, BlockPos p_48806_, Player p_48807_, BlockHitResult p_48809_) {
        if (p_48805_.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            p_48807_.openMenu(p_48804_.getMenuProvider(p_48805_, p_48806_));
            return InteractionResult.CONSUME;
        }
    }



    @SuppressWarnings("null")
    @Nullable
    @Override
    protected MenuProvider getMenuProvider(BlockState p_48821_, Level p_48822_, BlockPos p_48823_) {
        return new SimpleMenuProvider(
            (p_48785_, p_48786_, p_48787_) -> new BindingTableMenu(p_48785_, p_48786_, ContainerLevelAccess.create(p_48822_, p_48823_)), Component.translatable("pastcrafter.binding_table")
        );
    }
}
