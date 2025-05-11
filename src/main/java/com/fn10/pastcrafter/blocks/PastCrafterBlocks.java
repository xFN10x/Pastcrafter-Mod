package com.fn10.pastcrafter.blocks;

import com.fn10.pastcrafter.PastCrafer;
import com.fn10.pastcrafter.blocks.be.tiles.BindingTableBlock;
import com.fn10.pastcrafter.blocks.be.tiles.PastExtracterBlock;
import com.fn10.pastcrafter.blocks.be.tiles.PastExtracterTile;
import com.fn10.pastcrafter.worldgen.tree.OldOakGrower;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PastCrafterBlocks {

        public static RegistryObject<Block> makePastBlock(String name, BlockBehaviour.Properties properties,
                        int pastExp) {
                return BLOCKS.register(name,
                                () -> new PastBlock(pastExp,
                                                properties));

        }

        public static RegistryObject<Block> makePastLeavesBlock(String name,
                        int pastExp, BlockBehaviour.Properties properties) {
                return BLOCKS.register(name,
                                () -> new PastLeavesBlock(pastExp,
                                                properties));

        }

        public class TileEntityInit {
                public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister
                                .create(ForgeRegistries.BLOCK_ENTITY_TYPES, PastCrafer.MID);

                @SuppressWarnings("null")
                public static final RegistryObject<BlockEntityType<PastExtracterTile>> Past_Extracter_Entity = TILE_ENTITY_TYPES
                                .register("past_extracter",
                                                () -> BlockEntityType.Builder
                                                                .of(PastExtracterTile::new,
                                                                                PastCrafterBlocks.Past_Extracter.get())
                                                                .build(null));
        }

        public static void register(IEventBus eventBus) {
                BLOCKS.register(eventBus);
        }

        public static final String MID = PastCrafer.MID;

        // Create a Deferred Register to hold Blocks which will all be registered under
        // the "examplemod" namespace
        public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MID);
        public static final DeferredRegister<BlockEntityType<?>> TILE_ENTITY_TYPES = TileEntityInit.TILE_ENTITY_TYPES;

        public static final RegistryObject<Block> Beta_Oak_Sapling = BLOCKS.register("beta_oak_sapling",
                        () -> new PastSaplingBlock(1, OldOakGrower.OLD_OAK, BlockBehaviour.Properties
                                        .ofFullCopy(Blocks.OAK_SAPLING).mapColor(MapColor.COLOR_GREEN)));

        public static final RegistryObject<Block> Beta_Oak_Log = BLOCKS.register("beta_oak_log",
                        () -> new PastBlock(2,
                                        BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG).mapColor(MapColor.WOOD)));

        public static final RegistryObject<Block> Beta_Oak_Leaves = makePastLeavesBlock("beta_oak_leaves", 1,
                        BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES).mapColor(MapColor.COLOR_GREEN));

        public static final RegistryObject<Block> Beta_Oak_Planks = BLOCKS.register("beta_oak_planks",
                        () -> new PastBlock(1, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)
                                        .mapColor(MapColor.WOOD)));
        public static final RegistryObject<Block> Beta_Grass = BLOCKS.register("beta_grass",
                        () -> new PastBlock(2, BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK)));
        public static final RegistryObject<Block> Beta_Dirt = makePastBlock("beta_dirt",
                        BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT), 0);
        public static final RegistryObject<Block> Beta_Cobblestone = makePastBlock("beta_cobblestone",
                        BlockBehaviour.Properties.ofFullCopy(Blocks.COBBLESTONE), 0);

        public static final RegistryObject<PastStairs> Beta_Oak_Stairs = BLOCKS.register("beta_oak_stairs",
                        () -> new PastStairs(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)
                                        .mapColor(MapColor.WOOD).noOcclusion(), 1) {
                                @Override
                                public int getLightBlock(@SuppressWarnings("null") BlockState state,
                                                @SuppressWarnings("null") BlockGetter world,
                                                @SuppressWarnings("null") BlockPos pos) {
                                        return 1;
                                }
                        });
        public static final RegistryObject<Block> Binding_Table = BLOCKS.register("binding_table",
                        () -> new BindingTableBlock(BlockBehaviour.Properties.of()
                                        .destroyTime(6).ignitedByLava().mapColor(MapColor.COLOR_GRAY)));

        public static final RegistryObject<Block> Past_Extracter = BLOCKS.register("past_extracter",
                        () -> new PastExtracterBlock(
                                        BlockBehaviour.Properties.of().destroyTime(2).sound(SoundType.STONE)
                                                        .noOcclusion().mapColor(MapColor.STONE)
                                                        .requiresCorrectToolForDrops()));
        public static final RegistryObject<Block> Distant_Memory = BLOCKS.register("distant_memory",
                        () -> new PastBlock(10, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE)
                                        .destroyTime(3).noTerrainParticles().noOcclusion().sound(SoundType.GLASS)) {
                                @Override
                                public int getLightBlock(@SuppressWarnings("null") BlockState state,
                                                @SuppressWarnings("null") BlockGetter world,
                                                @SuppressWarnings("null") BlockPos pos) {
                                        return 0;
                                }

                        });

}
