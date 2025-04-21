package com.fn10.pastcrafter.blocks.be.blocks;

import javax.annotation.Nullable;

import com.fn10.pastcrafter.blocks.PastCrafterBlocks.TileEntityInit;
import com.fn10.pastcrafter.blocks.be.tiles.PastExtracterTile;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class PastExtracterBlock extends Block implements EntityBlock {

    // public static final IntegerProperty ITEMS = IntegerProperty.create("items",
    // 0, 2);

    public PastExtracterBlock(BlockBehaviour.Properties properties) {
        super(properties);
        // this.registerDefaultState(this.stateDefinition
        // .any()
        // .setValue(ITEMS, Integer.valueOf(0))
        // );
    }

    protected ItemInteractionResult useItemOn(ItemStack pStack, BlockState pState, Level pLevel,
            BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult) {
        if (pLevel.getBlockEntity(pPos) instanceof PastExtracterTile be) {
            if (!pLevel.isClientSide()) {
                ((ServerPlayer) pPlayer).openMenu(new SimpleMenuProvider(be, Component.literal("Past Extracter")),
                        pPos);
                return ItemInteractionResult.SUCCESS;
            } else {
                return ItemInteractionResult.FAIL;
            }
        } else {
            return ItemInteractionResult.FAIL;
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return TileEntityInit.Past_Extracter_Entity.get().create(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state,
            BlockEntityType<T> type) {
        return type == TileEntityInit.Past_Extracter_Entity.get()
                ? (level, pos, blockState, tile) -> {
                    if (tile instanceof PastExtracterTile pastExtracterTile) {
                        PastExtracterTile.tick(level, pos, blockState, pastExtracterTile);
                    }
                }
                : null;
    }
}
