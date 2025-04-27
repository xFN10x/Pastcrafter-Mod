package com.fn10.pastcrafter.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

import com.fn10.pastcrafter.PastCrafer;
import com.fn10.pastcrafter.blocks.PastCrafterBlocks;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> OLD_OAK_KEY_PLACED = registerKey("old_oak_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
        register(context, OLD_OAK_KEY_PLACED, configuredFeatures.getOrThrow(ModConfiguredFeatures.OLD_OAK_TREE),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.05f, 0),
                        PastCrafterBlocks.Beta_Oak_Sapling.get()));
    }


    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(PastCrafer.MID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
