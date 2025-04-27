package com.fn10.pastcrafter.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class PastSaplingBlock extends SaplingBlock {

    public static final IntegerProperty PAST_EXP = IntegerProperty.create("past_exp", 0, 100);

    public PastSaplingBlock(Integer Past_EXP,TreeGrower p_311256_, Properties p_55979_) {
        super(p_311256_, p_55979_);
        this.registerDefaultState(this.stateDefinition.any()
        .setValue(PAST_EXP, Past_EXP)
        .setValue(STAGE, Integer.valueOf(0)));
    }

    @SuppressWarnings("null")
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(PAST_EXP);
        builder.add(STAGE);
    }

}
