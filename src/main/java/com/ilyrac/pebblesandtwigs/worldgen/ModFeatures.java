package com.ilyrac.pebblesandtwigs.worldgen;

import com.ilyrac.pebblesandtwigs.PebblesAndTwigs;
import com.ilyrac.pebblesandtwigs.block.ModBlocks;
import com.ilyrac.pebblesandtwigs.block.PebbleBlock;
import com.ilyrac.pebblesandtwigs.block.TwigBlock;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.SimpleBlockFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.RandomizedIntStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;

public class ModFeatures {

    // --- PEBBLES (Dry) ---
    public static final ResourceKey<Feature> STONE_PEBBLE_KEY = createKey("stone_pebble");
    public static final ResourceKey<Feature> ANDESITE_PEBBLE_KEY = createKey("andesite_pebble");
    public static final ResourceKey<Feature> DIORITE_PEBBLE_KEY = createKey("diorite_pebble");
    public static final ResourceKey<Feature> GRANITE_PEBBLE_KEY = createKey("granite_pebble");
    public static final ResourceKey<Feature> SANDSTONE_PEBBLE_KEY = createKey("sandstone_pebble");
    public static final ResourceKey<Feature> RED_SANDSTONE_PEBBLE_KEY = createKey("red_sandstone_pebble");
    public static final ResourceKey<Feature> DEEPSLATE_PEBBLE_KEY = createKey("deepslate_pebble");
    public static final ResourceKey<Feature> TUFF_PEBBLE_KEY = createKey("tuff_pebble");
    public static final ResourceKey<Feature> BLACKSTONE_PEBBLE_KEY = createKey("blackstone_pebble");
    public static final ResourceKey<Feature> END_STONE_PEBBLE_KEY = createKey("end_stone_pebble");

    // --- PEBBLES (Waterlogged) ---
    public static final ResourceKey<Feature> STONE_PEBBLE_WATER_KEY = createKey("stone_pebble_water");
    public static final ResourceKey<Feature> ANDESITE_PEBBLE_WATER_KEY = createKey("andesite_pebble_water");
    public static final ResourceKey<Feature> DIORITE_PEBBLE_WATER_KEY = createKey("diorite_pebble_water");
    public static final ResourceKey<Feature> GRANITE_PEBBLE_WATER_KEY = createKey("granite_pebble_water");
    public static final ResourceKey<Feature> SANDSTONE_PEBBLE_WATER_KEY = createKey("sandstone_pebble_water");
    public static final ResourceKey<Feature> RED_SANDSTONE_PEBBLE_WATER_KEY = createKey("red_sandstone_pebble_water");

    // --- TWIGS ---
    public static final ResourceKey<Feature> ACACIA_TWIG_KEY = createKey("acacia_twig");
    public static final ResourceKey<Feature> BIRCH_TWIG_KEY = createKey("birch_twig");
    public static final ResourceKey<Feature> CHERRY_TWIG_KEY = createKey("cherry_twig");
    public static final ResourceKey<Feature> CRIMSON_TWIG_KEY = createKey("crimson_twig");
    public static final ResourceKey<Feature> DARK_OAK_TWIG_KEY = createKey("dark_oak_twig");
    public static final ResourceKey<Feature> JUNGLE_TWIG_KEY = createKey("jungle_twig");
    public static final ResourceKey<Feature> MANGROVE_TWIG_KEY = createKey("mangrove_twig");
    public static final ResourceKey<Feature> OAK_TWIG_KEY = createKey("oak_twig");
    public static final ResourceKey<Feature> PALE_OAK_TWIG_KEY = createKey("pale_oak_twig");
    public static final ResourceKey<Feature> POPLAR_TWIG_KEY = createKey("poplar_twig");
    public static final ResourceKey<Feature> SPRUCE_TWIG_KEY = createKey("spruce_twig");
    public static final ResourceKey<Feature> WARPED_TWIG_KEY = createKey("warped_twig");

    public static void bootstrap(BootstrapContext<Feature> context) {
        // Pebbles
        registerPebble(context, STONE_PEBBLE_KEY, ModBlocks.STONE_PEBBLE);
        registerPebble(context, ANDESITE_PEBBLE_KEY, ModBlocks.ANDESITE_PEBBLE);
        registerPebble(context, DIORITE_PEBBLE_KEY, ModBlocks.DIORITE_PEBBLE);
        registerPebble(context, GRANITE_PEBBLE_KEY, ModBlocks.GRANITE_PEBBLE);
        registerPebble(context, SANDSTONE_PEBBLE_KEY, ModBlocks.SANDSTONE_PEBBLE);
        registerPebble(context, RED_SANDSTONE_PEBBLE_KEY, ModBlocks.RED_SANDSTONE_PEBBLE);
        registerPebble(context, DEEPSLATE_PEBBLE_KEY, ModBlocks.DEEPSLATE_PEBBLE);
        registerPebble(context, TUFF_PEBBLE_KEY, ModBlocks.TUFF_PEBBLE);
        registerPebble(context, BLACKSTONE_PEBBLE_KEY, ModBlocks.BLACKSTONE_PEBBLE);
        registerPebble(context, END_STONE_PEBBLE_KEY, ModBlocks.END_STONE_PEBBLE);

        registerWaterPebble(context, STONE_PEBBLE_WATER_KEY, ModBlocks.STONE_PEBBLE);
        registerWaterPebble(context, ANDESITE_PEBBLE_WATER_KEY, ModBlocks.ANDESITE_PEBBLE);
        registerWaterPebble(context, DIORITE_PEBBLE_WATER_KEY, ModBlocks.DIORITE_PEBBLE);
        registerWaterPebble(context, GRANITE_PEBBLE_WATER_KEY, ModBlocks.GRANITE_PEBBLE);
        registerWaterPebble(context, SANDSTONE_PEBBLE_WATER_KEY, ModBlocks.SANDSTONE_PEBBLE);
        registerWaterPebble(context, RED_SANDSTONE_PEBBLE_WATER_KEY, ModBlocks.RED_SANDSTONE_PEBBLE);

        // Twigs
        registerTwig(context, ACACIA_TWIG_KEY, ModBlocks.ACACIA_TWIG, ModBlocks.STRIPPED_ACACIA_TWIG);
        registerTwig(context, BIRCH_TWIG_KEY, ModBlocks.BIRCH_TWIG, ModBlocks.STRIPPED_BIRCH_TWIG);
        registerTwig(context, CHERRY_TWIG_KEY, ModBlocks.CHERRY_TWIG, ModBlocks.STRIPPED_CHERRY_TWIG);
        registerTwig(context, CRIMSON_TWIG_KEY, ModBlocks.CRIMSON_TWIG, ModBlocks.STRIPPED_CRIMSON_TWIG);
        registerTwig(context, DARK_OAK_TWIG_KEY, ModBlocks.DARK_OAK_TWIG, ModBlocks.STRIPPED_DARK_OAK_TWIG);
        registerTwig(context, JUNGLE_TWIG_KEY, ModBlocks.JUNGLE_TWIG, ModBlocks.STRIPPED_JUNGLE_TWIG);
        registerTwig(context, MANGROVE_TWIG_KEY, ModBlocks.MANGROVE_TWIG, ModBlocks.STRIPPED_MANGROVE_TWIG);
        registerTwig(context, OAK_TWIG_KEY, ModBlocks.OAK_TWIG, ModBlocks.STRIPPED_OAK_TWIG);
        registerTwig(context, PALE_OAK_TWIG_KEY, ModBlocks.PALE_OAK_TWIG, ModBlocks.STRIPPED_PALE_OAK_TWIG);
        registerTwig(context, POPLAR_TWIG_KEY, ModBlocks.POPLAR_TWIG, ModBlocks.STRIPPED_POPLAR_TWIG);
        registerTwig(context, SPRUCE_TWIG_KEY, ModBlocks.SPRUCE_TWIG, ModBlocks.STRIPPED_SPRUCE_TWIG);
        registerTwig(context, WARPED_TWIG_KEY, ModBlocks.WARPED_TWIG, ModBlocks.STRIPPED_WARPED_TWIG);
    }

    private static void registerPebble(BootstrapContext<Feature> context, ResourceKey<Feature> key, Block block) {
        context.register(key, new SimpleBlockFeature(new RandomizedIntStateProvider(new SimpleStateProvider(block.defaultBlockState()), PebbleBlock.AMOUNT, UniformInt.of(1, 4))));
    }

    private static void registerWaterPebble(BootstrapContext<Feature> context, ResourceKey<Feature> key, Block block) {
        context.register(key, new SimpleBlockFeature(new RandomizedIntStateProvider(new SimpleStateProvider(block.defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, true)), PebbleBlock.AMOUNT, UniformInt.of(1, 4))));
    }

    private static void registerTwig(BootstrapContext<Feature> context, ResourceKey<Feature> key, Block bark, Block stripped) {
        WeightedList.Builder<BlockState> builder = WeightedList.builder();
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            // 24 weight for bark, 1 for stripped (per direction)
            builder.add(bark.defaultBlockState().setValue(TwigBlock.FACING, dir).setValue(TwigBlock.STANDING, false).setValue(TwigBlock.HALF, Half.BOTTOM).setValue(TwigBlock.WATERLOGGED, false), 24);
            builder.add(stripped.defaultBlockState().setValue(TwigBlock.FACING, dir).setValue(TwigBlock.STANDING, false).setValue(TwigBlock.HALF, Half.BOTTOM).setValue(TwigBlock.WATERLOGGED, false), 1);
        }
        context.register(key, new SimpleBlockFeature(new WeightedStateProvider(builder)));
    }

    private static ResourceKey<Feature> createKey(String name) {
        return ResourceKey.create(Registries.FEATURE, Identifier.fromNamespaceAndPath(PebblesAndTwigs.MOD_ID, name));
    }
}