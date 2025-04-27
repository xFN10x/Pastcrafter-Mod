package com.fn10.pastcrafter.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class PastBlock extends Block {

    public static final IntegerProperty PAST_EXP = IntegerProperty.create("past_exp", 0, 100);

    public PastBlock(Integer Past_EXP, Properties p_49795_) {
        super(p_49795_);
        this.registerDefaultState(this.stateDefinition.any().setValue(PAST_EXP, Past_EXP));
    }

    @SuppressWarnings("null")
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(PAST_EXP);
    }

}
