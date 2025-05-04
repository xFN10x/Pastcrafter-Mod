package com.fn10.pastcrafter.datagen;

import com.fn10.pastcrafter.PastCrafterTags;
import com.fn10.pastcrafter.blocks.PastCrafterBlocks;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class PCBlockLootTableP extends BlockLootSubProvider {
    protected PCBlockLootTableP(HolderLookup.Provider pRegistries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pRegistries);
    }

    @Override
    protected void generate() {
        dropSelf(PastCrafterBlocks.Past_Extracter.get());
        dropSelf(PastCrafterBlocks.Beta_Oak_Log.get());
        dropSelf(PastCrafterBlocks.Beta_Oak_Planks.get());
        dropSelf(PastCrafterBlocks.Beta_Oak_Sapling.get());
        dropSelf(PastCrafterBlocks.Beta_Dirt.get());
        dropSelf(PastCrafterBlocks.Beta_Cobblestone.get());
        dropSelf(PastCrafterBlocks.Binding_Table.get());
        dropOther(PastCrafterBlocks.Beta_Oak_Stairs.get(), PastCrafterBlocks.Beta_Oak_Planks.get().asItem());

        this.add(PastCrafterBlocks.Beta_Grass.get(), createSilkTouchOnlyTable(PastCrafterBlocks.Beta_Dirt.get()));
        this.add(PastCrafterBlocks.Beta_Oak_Leaves.get(),
                block -> createLeavesDrops(PastCrafterBlocks.Beta_Oak_Leaves.get(),
                        PastCrafterBlocks.Beta_Oak_Sapling.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        onlyDropWithOldItem(PastCrafterBlocks.Distant_Memory.get(), net.minecraft.world.level.block.Blocks.AIR);
    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                pBlock, this.applyExplosionDecay(
                        pBlock, LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                                .apply(ApplyBonusCount
                                        .addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }

    protected void onlyDropWithOldItem(Block p_249932_, Block p_252053_) {
        this.add(p_249932_, this.createPastItemOnlyTable(p_252053_));
    }

    protected LootTable.Builder createPastItemOnlyTable(ItemLike p_252216_) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool().when(this.isOldItem()).setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(p_252216_)));
    }

    protected LootItemCondition.Builder isOldItem() {
        return MatchTool.toolMatches(
                ItemPredicate.Builder.item()
                        .of(PastCrafterTags.OLD_ITEM));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return PastCrafterBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
