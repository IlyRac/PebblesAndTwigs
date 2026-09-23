package com.ilyrac.pebblesandtwigs.worldgen;

import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var features = context.lookup(Registries.FEATURE);

        // --- PEBBLE PLACEMENTS (AIR) ---
        register(context, ModWorldGeneration.STONE_PEBBLE_SURFACE_VERY_COMMON, features.getOrThrow(ModFeatures.STONE_PEBBLE_KEY), surfaceAirCount(4));
        register(context, ModWorldGeneration.STONE_PEBBLE_SURFACE_COMMON, features.getOrThrow(ModFeatures.STONE_PEBBLE_KEY), surfaceAirCount(1));
        register(context, ModWorldGeneration.ANDESITE_PEBBLE_SURFACE_COMMON, features.getOrThrow(ModFeatures.ANDESITE_PEBBLE_KEY), surfaceAirCount(1));
        register(context, ModWorldGeneration.ANDESITE_PEBBLE_SURFACE_LESS_COMMON, features.getOrThrow(ModFeatures.ANDESITE_PEBBLE_KEY), surfaceAirRarity(3));
        register(context, ModWorldGeneration.DIORITE_PEBBLE_SURFACE_COMMON, features.getOrThrow(ModFeatures.DIORITE_PEBBLE_KEY), surfaceAirCount(1));
        register(context, ModWorldGeneration.DIORITE_PEBBLE_SURFACE_LESS_COMMON, features.getOrThrow(ModFeatures.DIORITE_PEBBLE_KEY), surfaceAirRarity(3));
        register(context, ModWorldGeneration.GRANITE_PEBBLE_SURFACE_COMMON, features.getOrThrow(ModFeatures.GRANITE_PEBBLE_KEY), surfaceAirCount(1));
        register(context, ModWorldGeneration.GRANITE_PEBBLE_SURFACE_LESS_COMMON, features.getOrThrow(ModFeatures.GRANITE_PEBBLE_KEY), surfaceAirRarity(3));
        register(context, ModWorldGeneration.SANDSTONE_PEBBLE_SURFACE_VERY_COMMON, features.getOrThrow(ModFeatures.SANDSTONE_PEBBLE_KEY), surfaceAirCount(4));
        register(context, ModWorldGeneration.RED_SANDSTONE_PEBBLE_SURFACE_VERY_COMMON, features.getOrThrow(ModFeatures.RED_SANDSTONE_PEBBLE_KEY), surfaceAirCount(4));
        register(context, ModWorldGeneration.DEEPSLATE_PEBBLE_SURFACE_RARE, features.getOrThrow(ModFeatures.DEEPSLATE_PEBBLE_KEY), surfaceAirRarity(5));
        register(context, ModWorldGeneration.TUFF_PEBBLE_SURFACE_RARE, features.getOrThrow(ModFeatures.TUFF_PEBBLE_KEY), surfaceAirRarity(5));

        // --- PEBBLE PLACEMENTS (WATER) ---
        register(context, ModWorldGeneration.STONE_PEBBLE_WATER_COMMON, features.getOrThrow(ModFeatures.STONE_PEBBLE_WATER_KEY), surfaceWaterCount(15));
        register(context, ModWorldGeneration.SANDSTONE_PEBBLE_WATER_COMMON, features.getOrThrow(ModFeatures.SANDSTONE_PEBBLE_WATER_KEY), surfaceWaterCount(20));
        register(context, ModWorldGeneration.RED_SANDSTONE_PEBBLE_WATER_COMMON, features.getOrThrow(ModFeatures.RED_SANDSTONE_PEBBLE_WATER_KEY), surfaceWaterCount(20));
        register(context, ModWorldGeneration.ANDESITE_PEBBLE_WATER_RARE, features.getOrThrow(ModFeatures.ANDESITE_PEBBLE_WATER_KEY), surfaceWaterCount(4));
        register(context, ModWorldGeneration.DIORITE_PEBBLE_WATER_RARE, features.getOrThrow(ModFeatures.DIORITE_PEBBLE_WATER_KEY), surfaceWaterCount(4));
        register(context, ModWorldGeneration.GRANITE_PEBBLE_WATER_RARE, features.getOrThrow(ModFeatures.GRANITE_PEBBLE_WATER_KEY), surfaceWaterCount(4));

        // --- PEBBLE PLACEMENTS (CAVES) ---
        // Counts raised back up so they successfully hit the cave floors organically
        VerticalAnchor shallowMin = VerticalAnchor.absolute(0);
        VerticalAnchor shallowMax = VerticalAnchor.absolute(320);
        register(context, ModWorldGeneration.STONE_PEBBLE_CAVE_SHALLOW, features.getOrThrow(ModFeatures.STONE_PEBBLE_KEY), caveAir(180, shallowMin, shallowMax));
        register(context, ModWorldGeneration.ANDESITE_PEBBLE_CAVE_SHALLOW, features.getOrThrow(ModFeatures.ANDESITE_PEBBLE_KEY), caveAir(90, shallowMin, shallowMax));
        register(context, ModWorldGeneration.DIORITE_PEBBLE_CAVE_SHALLOW, features.getOrThrow(ModFeatures.DIORITE_PEBBLE_KEY), caveAir(90, shallowMin, shallowMax));
        register(context, ModWorldGeneration.GRANITE_PEBBLE_CAVE_SHALLOW, features.getOrThrow(ModFeatures.GRANITE_PEBBLE_KEY), caveAir(90, shallowMin, shallowMax));
        register(context, ModWorldGeneration.DEEPSLATE_PEBBLE_CAVE_SHALLOW, features.getOrThrow(ModFeatures.DEEPSLATE_PEBBLE_KEY), caveAir(120, shallowMin, shallowMax));
        register(context, ModWorldGeneration.TUFF_PEBBLE_CAVE_SHALLOW, features.getOrThrow(ModFeatures.TUFF_PEBBLE_KEY), caveAir(120, shallowMin, shallowMax));

        VerticalAnchor deepMin = VerticalAnchor.bottom();
        VerticalAnchor deepMax = VerticalAnchor.absolute(0);
        register(context, ModWorldGeneration.STONE_PEBBLE_CAVE_DEEP, features.getOrThrow(ModFeatures.STONE_PEBBLE_KEY), caveAir(40, deepMin, deepMax));
        register(context, ModWorldGeneration.ANDESITE_PEBBLE_CAVE_DEEP, features.getOrThrow(ModFeatures.ANDESITE_PEBBLE_KEY), caveAir(20, deepMin, deepMax));
        register(context, ModWorldGeneration.DIORITE_PEBBLE_CAVE_DEEP, features.getOrThrow(ModFeatures.DIORITE_PEBBLE_KEY), caveAir(20, deepMin, deepMax));
        register(context, ModWorldGeneration.GRANITE_PEBBLE_CAVE_DEEP, features.getOrThrow(ModFeatures.GRANITE_PEBBLE_KEY), caveAir(20, deepMin, deepMax));
        register(context, ModWorldGeneration.DEEPSLATE_PEBBLE_CAVE_DEEP, features.getOrThrow(ModFeatures.DEEPSLATE_PEBBLE_KEY), caveAir(200, deepMin, deepMax));
        register(context, ModWorldGeneration.TUFF_PEBBLE_CAVE_DEEP, features.getOrThrow(ModFeatures.TUFF_PEBBLE_KEY), caveAir(200, deepMin, deepMax));

        // Using netherFloor guarantees they never spawn midair and avoid the roof (Y=115)
        register(context, ModWorldGeneration.BLACKSTONE_PEBBLE_NETHER, features.getOrThrow(ModFeatures.BLACKSTONE_PEBBLE_KEY), netherFloor(300, VerticalAnchor.bottom(), VerticalAnchor.absolute(115)));
        register(context, ModWorldGeneration.END_STONE_PEBBLE_END, features.getOrThrow(ModFeatures.END_STONE_PEBBLE_KEY), surfaceAirCount(12));

        // --- TWIG PLACEMENTS ---
        register(context, ModWorldGeneration.OAK_TWIG_PRIMARY, features.getOrThrow(ModFeatures.OAK_TWIG_KEY), surfaceAirCount(4));
        register(context, ModWorldGeneration.OAK_TWIG_SECONDARY, features.getOrThrow(ModFeatures.OAK_TWIG_KEY), surfaceAirCount(1));
        register(context, ModWorldGeneration.ACACIA_TWIG_PRIMARY, features.getOrThrow(ModFeatures.ACACIA_TWIG_KEY), surfaceAirCount(2));
        register(context, ModWorldGeneration.BIRCH_TWIG_PRIMARY, features.getOrThrow(ModFeatures.BIRCH_TWIG_KEY), surfaceAirCount(2));
        register(context, ModWorldGeneration.CHERRY_TWIG_PRIMARY, features.getOrThrow(ModFeatures.CHERRY_TWIG_KEY), surfaceAirCount(2));
        register(context, ModWorldGeneration.POPLAR_TWIG_PRIMARY, features.getOrThrow(ModFeatures.POPLAR_TWIG_KEY), surfaceAirCount(2));
        register(context, ModWorldGeneration.DARK_OAK_TWIG_PRIMARY, features.getOrThrow(ModFeatures.DARK_OAK_TWIG_KEY), surfaceAirCount(5));
        register(context, ModWorldGeneration.JUNGLE_TWIG_PRIMARY, features.getOrThrow(ModFeatures.JUNGLE_TWIG_KEY), surfaceAirCount(5));
        register(context, ModWorldGeneration.MANGROVE_TWIG_PRIMARY, features.getOrThrow(ModFeatures.MANGROVE_TWIG_KEY), surfaceAirCount(5));
        register(context, ModWorldGeneration.PALE_OAK_TWIG_PRIMARY, features.getOrThrow(ModFeatures.PALE_OAK_TWIG_KEY), surfaceAirCount(5));
        register(context, ModWorldGeneration.SPRUCE_TWIG_PRIMARY, features.getOrThrow(ModFeatures.SPRUCE_TWIG_KEY), surfaceAirCount(5));

        // Nether twigs use the ground-check filter!
        register(context, ModWorldGeneration.CRIMSON_TWIG_PRIMARY, features.getOrThrow(ModFeatures.CRIMSON_TWIG_KEY), netherFloor(600, VerticalAnchor.bottom(), VerticalAnchor.absolute(115)));
        register(context, ModWorldGeneration.WARPED_TWIG_PRIMARY, features.getOrThrow(ModFeatures.WARPED_TWIG_KEY), netherFloor(600, VerticalAnchor.bottom(), VerticalAnchor.absolute(115)));
    }

    // --- HELPER METHODS ---
    private static List<PlacementModifier> surfaceAirCount(int count) {
        return List.of(CountPlacement.of(count), InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR_WG), BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.AIR, Blocks.CAVE_AIR)), BiomeFilter.biome());
    }

    private static List<PlacementModifier> surfaceAirRarity(int chance) {
        return List.of(RarityFilter.onAverageOnceEvery(chance), InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR_WG), BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.AIR, Blocks.CAVE_AIR)), BiomeFilter.biome());
    }

    private static List<PlacementModifier> surfaceWaterCount(int count) {
        return List.of(CountPlacement.of(count), InSquarePlacement.spread(), HeightmapPlacement.onHeightmap(Heightmap.Types.OCEAN_FLOOR_WG), BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.WATER)), BiomeFilter.biome());
    }

    private static List<PlacementModifier> caveAir(int count, VerticalAnchor min, VerticalAnchor max) {
        // Returned to normal! Pebbles will use their internal canSurvive safely.
        return List.of(CountPlacement.of(count), InSquarePlacement.spread(), HeightRangePlacement.uniform(min, max), BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.AIR, Blocks.CAVE_AIR)), BiomeFilter.biome());
    }

    private static List<PlacementModifier> netherFloor(int count, VerticalAnchor min, VerticalAnchor max) {
        // We use Direction.DOWN to check if the block directly below the Air block is solid!
        return List.of(
                CountPlacement.of(count),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(min, max),
                BlockPredicateFilter.forPredicate(BlockPredicate.allOf(
                        BlockPredicate.matchesBlocks(Blocks.AIR, Blocks.CAVE_AIR),
                        BlockPredicate.solid(Direction.DOWN)
                )),
                BiomeFilter.biome()
        );
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<Feature> feature, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(feature, modifiers));
    }
}