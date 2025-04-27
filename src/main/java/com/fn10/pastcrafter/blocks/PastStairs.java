package com.fn10.pastcrafter.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PastStairs extends Block {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final IntegerProperty PAST_EXP = IntegerProperty.create("past_exp", 0, 100);

    // public YAxisBlock(Properties p_49795_) {
    // super(p_49795_);
    // this.registerDefaultState(this.stateDefinition.any()
    // .setValue(FACING, Direction.NORTH));
    // }

    public PastStairs(Properties p_49795_, int PAST_EXPY) {
        super(p_49795_);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(PAST_EXP, PAST_EXPY));
    }

    @Override
    protected void createBlockStateDefinition(
            @SuppressWarnings("null") StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(PAST_EXP);

    }

    @Override
    protected VoxelShape getShape(@SuppressWarnings("null") BlockState bs, @SuppressWarnings("null") BlockGetter p_56957_, @SuppressWarnings("null") BlockPos p_56958_, @SuppressWarnings("null") CollisionContext p_56959_) {
        if (bs.getValue(FACING).equals(Direction.SOUTH)) {
            return Shapes.join(Shapes.create(0, 0, 0, 1, 0.5, 1), Shapes.create(0, 0, 0, 1, 1, .5), BooleanOp.OR);
        } else if (bs.getValue(FACING).equals(Direction.NORTH)) {
            return Shapes.join(Shapes.create(0, 0, 0, 1, 0.5, 1), Shapes.create(0, 0, .5, 1, 1, 1), BooleanOp.OR);
        } else if (bs.getValue(FACING).equals(Direction.EAST)) {
            return Shapes.join(Shapes.create(0, 0, 0, 1, 0.5, 1), Shapes.create(0, 0, 0, .5, 1, 1), BooleanOp.OR);
        } else if (bs.getValue(FACING).equals(Direction.WEST)) {
            return Shapes.join(Shapes.create(0, 0, 0, 1, 0.5, 1), Shapes.create(0.5, 0, 0, 1, 1, 1), BooleanOp.OR);
        } else {
            return Shapes.join(Shapes.create(0, 0, 0, 1, 0.5, 1), Shapes.create(0, 0, 0, 1, 1, .5), BooleanOp.OR);
        }

    }

    @Override
    public BlockState getStateForPlacement(@SuppressWarnings("null") BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
}
