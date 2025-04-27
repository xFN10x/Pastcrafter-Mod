package com.fn10.pastcrafter.blocks.be.tiles;

import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import com.fn10.pastcrafter.PastCrafer;
import com.fn10.pastcrafter.PastCrafterTags;
import com.fn10.pastcrafter.blocks.PastCrafterBlocks.TileEntityInit;
import com.fn10.pastcrafter.componate.PastCrafterComponets;
import com.fn10.pastcrafter.items.PastCrafterItems;
import com.fn10.pastcrafter.menu.PastExtracterMenu;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraftforge.items.ItemStackHandler;

public class PastExtracterTile extends BlockEntity implements MenuProvider {

    public int Timer = 0; 
    public final SoundEvent Block_Start = PastCrafer.PAST_EXTRACTER_START_SOUND;
    public final SoundEvent Block_Tick = PastCrafer.PAST_EXTRACTER_TICK_SOUND;
    public final SoundEvent Block_End = PastCrafer.PAST_EXTRACTER_STOP_SOUND;
    public boolean Started = false;
    public boolean CanPlayEndSound = false;
    public boolean CanStart = true;
    //public 

    public final ItemStackHandler inventory = new ItemStackHandler(2) {
        @Override
        protected int getStackLimit(int slot, @NotNull ItemStack stack) {
            if (slot == 0) {
                return Integer.valueOf(64);
            } else {
                return Integer.valueOf(1); 
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

    public PastExtracterTile(BlockPos pos, BlockState state) {
            super(TileEntityInit.Past_Extracter_Entity.get(), pos, state);
        }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T be) {
        PastExtracterTile tile = (PastExtracterTile) be;
        ItemStack ingre = tile.inventory.getStackInSlot(0);
        ItemStack out = tile.inventory.getStackInSlot(1);

        if (!level.isClientSide() && ingre.getTags().anyMatch(tag -> tag.equals(PastCrafterTags.OLD_ITEM)) & out.getTags().anyMatch(tag -> tag.equals(PastCrafterTags.BINDABLE))) { 
            
            if (!tile.Started && tile.CanStart) {
                tile.Started = true;
                level.playSound(null, pos, tile.Block_Start, SoundSource.BLOCKS);
            } else if (tile.Started) {
                tile.Timer++;
                tile.CanPlayEndSound = true;
                if (tile.Timer >= 600) {
                    level.playSound(null, pos, tile.Block_End, SoundSource.BLOCKS);
                    tile.inventory.extractItem(0, 1, false);

                    Float outexp = out.get(PastCrafterComponets.PAST_EXP.get());
                    Float ingreexp = ingre.get(PastCrafterComponets.PAST_EXP.get());

                    if (ingreexp == null) {
                        tile.Timer = 0;
                        return;
                    }
                    if (outexp == null) {
                        outexp = 0f;
                    }
                    
                    if (out.is(Items.BOOK)) {
                        tile.inventory.setStackInSlot(1, new ItemStack(PastCrafterItems.HISTORY_BOOK.get()));
                        out.set(PastCrafterComponets.PAST_EXP.get(), ingreexp);
                    } else if (out.is(PastCrafterItems.HISTORY_BOOK.get())) {
                        out.set(PastCrafterComponets.PAST_EXP.get(), outexp + ingreexp);
                    }
                    
                    tile.Timer = 0;
                }
            }
            if (PastCrafer.Math.isMultipleOf(tile.Timer, 28)) {
                level.playSound(null, pos, tile.Block_Tick, SoundSource.BLOCKS);
            }
        } else {
            tile.Started = false;
            tile.Timer--;
        }
        tile.Timer = Mth.clamp(tile.Timer, 0, 600);
        //System.out.println("Timer value (tile): " + tile.Timer);
        if (!level.isClientSide()) {
            level.sendBlockUpdated(pos, state, state, 3); // Notify the client of the update
        }
        if (tile.Timer == 0 && tile.CanPlayEndSound) {
            level.playSound(null, pos, tile.Block_End, SoundSource.BLOCKS);
            tile.CanPlayEndSound = false;
        }
    }
    @Override
    @Nullable
    public AbstractContainerMenu createMenu(int p_39954_, @SuppressWarnings("null") Inventory p_39955_, @SuppressWarnings("null") Player p_39956_) {
        return new PastExtracterMenu(p_39954_, p_39955_, this);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.pastcrafter.past_extracter");
    }
    
    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
public CompoundTag getUpdateTag(@SuppressWarnings("null") HolderLookup.Provider pRegistries) {
    CompoundTag tag = super.getUpdateTag(pRegistries);
    tag.putInt("Timer", this.Timer); // Add Timer to the tag
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
        this.Timer = pTag.getInt("Timer"); // Load Timer from the tag
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
