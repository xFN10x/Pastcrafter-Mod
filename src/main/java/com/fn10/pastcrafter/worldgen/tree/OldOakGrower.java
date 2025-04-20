package com.fn10.pastcrafter.worldgen.tree;

import java.util.Optional;

import com.fn10.pastcrafter.PastCrafer;
import com.fn10.pastcrafter.worldgen.ModConfiguredFeatures;

import net.minecraft.world.level.block.grower.TreeGrower;

public class OldOakGrower {
    public static final TreeGrower OLD_OAK = new TreeGrower(PastCrafer.MID + ":old_oak_tree",
    Optional.empty(), Optional.of(ModConfiguredFeatures.OLD_OAK_TREE), Optional.empty()
    );
}