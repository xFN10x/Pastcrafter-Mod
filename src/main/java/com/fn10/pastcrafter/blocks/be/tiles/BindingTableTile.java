package com.fn10.pastcrafter.blocks.be.tiles;

import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import com.fn10.pastcrafter.blocks.PastCrafterBlocks.TileEntityInit;
import com.fn10.pastcrafter.menu.BindingTableMenu;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

@Deprecated
public class BindingTableTile extends BlockEntity implements MenuProvider {


    public final ItemStackHandler inventory = new ItemStackHandler(3) {
        @Override
        protected int getStackLimit(int slot, @NotNull ItemStack stack) {
            if (slot == 0) {
                return Integer.valueOf(1);
            } else {
                return Integer.valueOf(64); 
            }
        }

        @SuppressWarnings("null")
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    public BindingTableTile(BlockPos pos, BlockState state) {
            super(TileEntityInit.Binding_Table_Entity.get(), pos, state);
        }

    @SuppressWarnings("null")
    @Override
    @Nullable
    public AbstractContainerMenu createMenu(int p_39954_, Inventory p_39955_, Player p_39956_) {
        return new BindingTableMenu(p_39954_, p_39955_, ContainerLevelAccess.create(level, worldPosition));
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.pastcrafter.binding_table");
    }
    
    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
public CompoundTag getUpdateTag(@SuppressWarnings("null") HolderLookup.Provider pRegistries) {
    CompoundTag tag = super.getUpdateTag(pRegistries);
    return tag;
}

    @Override
    protected void saveAdditional(@SuppressWarnings("null") CompoundTag pTag, @SuppressWarnings("null") HolderLookup.Provider pRegistries) {
        super.saveAdditional(pTag, pRegistries);
        pTag.put("inventory", inventory.serializeNBT(pRegistries));
    }

    @Override
    protected void loadAdditional(@SuppressWarnings("null") CompoundTag pTag, @SuppressWarnings("null") HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        inventory.deserializeNBT(pRegistries, pTag.getCompound("inventory"));
    }

    @SuppressWarnings("null")
    public void drops() {
        SimpleContainer inv = new SimpleContainer(inventory.getSlots());
        for(int i = 0; i < inventory.getSlots(); i++) {
            inv.setItem(i, inventory.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inv);
    }
}
