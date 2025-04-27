package com.fn10.pastcrafter.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class PastLeavesBlock extends LeavesBlock {

    public static final IntegerProperty PAST_EXP = IntegerProperty.create("past_exp", 0, 100);

    public PastLeavesBlock(Integer pastexp,Properties p_54422_) {
        super(p_54422_);
        this.registerDefaultState(
            this.stateDefinition
                .any()
                .setValue(DISTANCE, Integer.valueOf(7))
                .setValue(PERSISTENT, Boolean.valueOf(false))
                .setValue(WATERLOGGED, Boolean.valueOf(false))
                .setValue(PAST_EXP, pastexp)
        );
    }

    @Override
    protected void createBlockStateDefinition(@SuppressWarnings("null") StateDefinition.Builder<Block, BlockState> p_54447_) {
        p_54447_.add(DISTANCE, PERSISTENT, WATERLOGGED, PAST_EXP);
    }

}
