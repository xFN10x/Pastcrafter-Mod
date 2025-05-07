package com.fn10.pastcrafter.menu;

import java.util.ArrayList;
import java.util.List;

import com.fn10.pastcrafter.PastCrafterTags;
import com.fn10.pastcrafter.blocks.PastCrafterBlocks;
import com.fn10.pastcrafter.classes.BindingTableRecipe;
import com.fn10.pastcrafter.component.PastCrafterComponents;
import com.fn10.pastcrafter.datagen.PCSoundDefinitionsP;
import com.fn10.pastcrafter.items.PastCrafterItems;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.ItemCombinerMenuSlotDefinition;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class BindingTableMenu extends ItemCombinerMenu {

    public List<BindingTableRecipe> recipes = new ArrayList<>();
    private final Level level;
    protected final DataSlot targetRecipe = DataSlot.standalone();
    private BlockPos blockpos;
    private ContainerLevelAccess ACCESS;

    public BindingTableMenu(int pContainerId, Inventory inv, ContainerLevelAccess access) {
        super(PastCrafterMenuTypes.BINDING_TABLE_MENU.get(), pContainerId, inv, access);
        this.level = inv.player.level();
        this.ACCESS = access;

        addPlayerInventory(inv);
        addPlayerHotbar(inv);
        this.addDataSlot(targetRecipe);

        this.addRecipe(Items.OAK_PLANKS, PastCrafterBlocks.Beta_Oak_Planks.get().asItem(), 3f);
        this.addRecipe(Blocks.OAK_LOG.asItem(), PastCrafterBlocks.Beta_Oak_Log.get().asItem(), 4f);
    }

    public BindingTableMenu(int id, Inventory inventory, FriendlyByteBuf buf) {
        this(id, inventory, ContainerLevelAccess.NULL);
        blockpos = buf != null ? buf.readBlockPos() : blockpos;
    }

    private void addRecipe(Item thisitem, Item turnsintothis, Float pastexp) {
        recipes.add(new BindingTableRecipe(thisitem, turnsintothis, pastexp));
    }

    @Override
    protected boolean mayPickup(@SuppressWarnings("null") Player p_39798_, boolean p_39799_) {
        Float inputexp = this.inputSlots.getItem(1).get(PastCrafterComponents.PAST_EXP.get());
        if (inputexp == null)
            return false;
        return inputexp >= recipes.get(targetRecipe.get()).pastExp;
    }


    @SuppressWarnings("null")
    @Override
    protected void onTake(Player p_150601_, ItemStack p_150602_) {
        this.inputSlots.getItem(0).shrink(1);
        this.inputSlots.getItem(1).set(PastCrafterComponents.PAST_EXP.get(),
                this.inputSlots.getItem(1).get(PastCrafterComponents.PAST_EXP.get())
                        - (recipes.get(targetRecipe.get()).pastExp * 0.5f));
        level.playSound(player, blockpos != null ? blockpos : player.blockPosition(),
                PCSoundDefinitionsP.BINDING_TABLE_BIND_SOUND, SoundSource.BLOCKS);
        this.createResult();
    }

    private boolean nullOrAir(ItemStack item) {
        return item.is(Blocks.AIR.asItem()) || item == null;
    }

    @Override
    protected boolean isValidBlock(@SuppressWarnings("null") BlockState bs) {
        return bs.is(PastCrafterBlocks.Binding_Table.get());
    }

    @SuppressWarnings("null")
    @Override
    public void createResult() {
        if (this.nullOrAir(this.inputSlots.getItem(0)) || this.nullOrAir(this.inputSlots.getItem(1)) ||
                this.nullOrAir(this.inputSlots.getItem(2))) {
            // System.out.println("no items");
            this.resultSlots.setItem(0, new ItemStack(Items.AIR));
            return;
        }
        List<BindingTableRecipe> recipesforitem = new ArrayList<>();
        for (BindingTableRecipe k : recipes) {
            if (k.input.equals(this.slots.get(0).getItem().getItem())) {
                recipesforitem.add(k);
            }
        }

        if (!recipesforitem.isEmpty() && this.inputSlots.getItem(2).is(PastCrafterItems.Binding_Hammer.get())) {
            float targetExp = 0f;
            BindingTableRecipe closestRecipe = null;
            float closestDifference = Float.MAX_VALUE;

            targetExp = this.inputSlots.getItem(1).get(PastCrafterComponents.PAST_EXP.get());

            for (BindingTableRecipe recipe : recipesforitem) {
                float difference = Math.abs(recipe.pastExp - targetExp);
                if (closestRecipe == null || difference < closestDifference) {
                    closestRecipe = recipe;
                    closestDifference = difference;
                }
            }

            if (closestRecipe != null) {

                targetRecipe.set(recipes.indexOf(closestRecipe));
                this.resultSlots.setItem(0, new ItemStack(closestRecipe.output));
            } else {
                this.resultSlots.setItem(0, new ItemStack(Items.AIR));
            }
        } else if (recipesforitem.isEmpty()) {
            this.resultSlots.setItem(0, new ItemStack(Items.AIR));
            System.out.println("no recipes for item " + this.inputSlots.getItem(0).getHoverName());
        }
    }

    @Override
    protected ItemCombinerMenuSlotDefinition createInputSlotDefinitions() {
        return ItemCombinerMenuSlotDefinition.create()
                .withSlot(0, 14, 45, p_266635_ -> true)
                .withSlot(1, 80, 45,
                        p_266634_ -> p_266634_.getTags().anyMatch(tag -> tag.equals(PastCrafterTags.BINDABLE)))
                .withSlot(2, 131, 14, p_266634_ -> p_266634_.is(PastCrafterItems.Binding_Hammer.get()))
                .withResultSlot(2, 140, 45)
                .build();
    }

    // ---------------------------------------------------------------------------------------

    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the
    // player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the
    // slotIndex, which means
    // 0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 -
    // 8)
    // 9 - 35 = player inventory slots (which map to the InventoryPlayer slot
    // numbers 9 - 35)
    // 36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 -
    // 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 2; // must be the number of slots you have!


    @Override
    public boolean stillValid(@SuppressWarnings("null") Player pPlayer) {
        return stillValid(this.ACCESS,
                pPlayer, PastCrafterBlocks.Binding_Table.get());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
}
