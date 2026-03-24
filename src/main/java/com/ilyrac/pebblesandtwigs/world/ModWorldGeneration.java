package com.ilyrac.pebblesandtwigs.world;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;

import java.util.function.Predicate;

public class ModWorldGeneration {
    public static void initializer() {
        // NETHER & END
        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.BLACKSTONE_PEBBLE_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.END_STONE_PEBBLE_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.CRIMSON_FOREST), GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.CRIMSON_TWIG_PLACED);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.WARPED_FOREST), GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.WARPED_TWIG_PLACED);

        // OVERWORLD SURFACE
        Predicate<BiomeSelectionContext> isSnowy = BiomeSelectors.includeByKey(
                Biomes.SNOWY_PLAINS, Biomes.ICE_SPIKES, Biomes.SNOWY_TAIGA,
                Biomes.GROVE, Biomes.SNOWY_SLOPES, Biomes.JAGGED_PEAKS,
                Biomes.FROZEN_PEAKS, Biomes.SNOWY_BEACH, Biomes.FROZEN_RIVER
        );
        Predicate<BiomeSelectionContext> isSnowyOrWater = BiomeSelectors.tag(BiomeTags.IS_OCEAN)
                .or(BiomeSelectors.tag(BiomeTags.IS_RIVER))
                .or(BiomeSelectors.includeByKey(
                        Biomes.SNOWY_PLAINS, Biomes.ICE_SPIKES, Biomes.SNOWY_TAIGA,
                        Biomes.GROVE, Biomes.SNOWY_SLOPES, Biomes.FROZEN_PEAKS, Biomes.SNOWY_BEACH
        ));
        var isSpruceBiome = BiomeSelectors.tag(BiomeTags.IS_TAIGA)
                .or(BiomeSelectors.includeByKey(Biomes.WINDSWEPT_HILLS, Biomes.WINDSWEPT_FOREST, Biomes.WINDSWEPT_GRAVELLY_HILLS));
        var isOverworld = BiomeSelectors.tag(BiomeTags.IS_OVERWORLD);
        var isDesert = BiomeSelectors.includeByKey(Biomes.DESERT);
        var isBadlands = BiomeSelectors.tag(BiomeTags.IS_BADLANDS);
        var validSurface = isOverworld.and(isSnowy.negate());
        var isStandard = validSurface.and(isDesert.negate()).and(isBadlands.negate());
        var validTwigArea = isOverworld.and(isSnowyOrWater.negate());


        // --- TIER 1 (KINGS) ---
        BiomeModifications.addFeature(isStandard, GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.STONE_PEBBLE_PLACED);
        BiomeModifications.addFeature(isDesert.and(validSurface), GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.SANDSTONE_PEBBLE_PLACED);
        BiomeModifications.addFeature(isBadlands.and(validSurface), GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.RED_SANDSTONE_PEBBLE_PLACED);

        // --- TIER 2 (SECONDARY) ---
        BiomeModifications.addFeature(isDesert.or(isBadlands).and(validSurface), GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.STONE_PEBBLE_TIER2);
        BiomeModifications.addFeature(isStandard, GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.ANDESITE_PEBBLE_TIER2);
        BiomeModifications.addFeature(isStandard, GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.GRANITE_PEBBLE_TIER2);
        BiomeModifications.addFeature(isStandard, GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.DIORITE_PEBBLE_TIER2);

        // --- TIER 3 (UNCOMMON) ---
        BiomeModifications.addFeature(validSurface, GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.TUFF_PEBBLE_TIER3);
        BiomeModifications.addFeature(validSurface, GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.DEEPSLATE_PEBBLE_TIER3);

        // OVERWORLD UNDERWATER
        var isWater = BiomeSelectors.tag(BiomeTags.IS_OCEAN).or(BiomeSelectors.tag(BiomeTags.IS_RIVER));

        BiomeModifications.addFeature(isWater, GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.STONE_PEBBLE_UNDERWATER);
        BiomeModifications.addFeature(isWater, GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.ANDESITE_PEBBLE_UNDERWATER);
        BiomeModifications.addFeature(isWater, GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.GRANITE_PEBBLE_UNDERWATER);
        BiomeModifications.addFeature(isWater, GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.DIORITE_PEBBLE_UNDERWATER);
        BiomeModifications.addFeature(isWater, GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.TUFF_PEBBLE_UNDERWATER);
        BiomeModifications.addFeature(isWater, GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.DEEPSLATE_PEBBLE_UNDERWATER);

        // OVERWORLD CAVES
        // --- NORMAL CAVES LOGIC ---
        BiomeModifications.addFeature(isOverworld, GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.STONE_PEBBLE_CAVE_KING);
        BiomeModifications.addFeature(isOverworld, GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.ANDESITE_PEBBLE_CAVE_SECONDARY);
        BiomeModifications.addFeature(isOverworld, GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.GRANITE_PEBBLE_CAVE_SECONDARY);
        BiomeModifications.addFeature(isOverworld, GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.DIORITE_PEBBLE_CAVE_SECONDARY);
        BiomeModifications.addFeature(isOverworld, GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.TUFF_PEBBLE_CAVE_UNCOMMON);
        BiomeModifications.addFeature(isOverworld, GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.DEEPSLATE_PEBBLE_CAVE_UNCOMMON);

        // --- DEEP CAVES LOGIC ---
        BiomeModifications.addFeature(isOverworld, GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.DEEPSLATE_PEBBLE_DEEP_KING);
        BiomeModifications.addFeature(isOverworld, GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.TUFF_PEBBLE_DEEP_KING);
        BiomeModifications.addFeature(isOverworld, GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.STONE_PEBBLE_DEEP_COMMON);
        BiomeModifications.addFeature(isOverworld, GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.ANDESITE_PEBBLE_DEEP_UNCOMMON);
        BiomeModifications.addFeature(isOverworld, GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.GRANITE_PEBBLE_DEEP_UNCOMMON);
        BiomeModifications.addFeature(isOverworld, GenerationStep.Decoration.UNDERGROUND_DECORATION, ModPlacedFeatures.DIORITE_PEBBLE_DEEP_UNCOMMON);

        // OVERWORLD TWIGS

        // 1. OAK (Universal King - High rate in forests, low rate in desert/plains)
        BiomeModifications.addFeature(validTwigArea, GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.OAK_TWIG_PLACED);

        // 2. SPRUCE (Taigas + Windswept)
        BiomeModifications.addFeature(validTwigArea.and(isSpruceBiome), GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.SPRUCE_TWIG_PLACED);

        // 3. BIRCH
        BiomeModifications.addFeature(validTwigArea.and(BiomeSelectors.includeByKey(Biomes.BIRCH_FOREST, Biomes.OLD_GROWTH_BIRCH_FOREST)),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.BIRCH_TWIG_PLACED);

        // 4. DARK OAK
        BiomeModifications.addFeature(validTwigArea.and(BiomeSelectors.includeByKey(Biomes.DARK_FOREST)),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.DARK_OAK_TWIG_PLACED);

        // 5. JUNGLE (Includes Bamboo & Sparse)
        BiomeModifications.addFeature(validTwigArea.and(BiomeSelectors.tag(BiomeTags.IS_JUNGLE)),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.JUNGLE_TWIG_PLACED);

        // 6. ACACIA (Savannas)
        BiomeModifications.addFeature(validTwigArea.and(BiomeSelectors.tag(BiomeTags.IS_SAVANNA)),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.ACACIA_TWIG_PLACED);

        // 7. CHERRY
        BiomeModifications.addFeature(validTwigArea.and(BiomeSelectors.includeByKey(Biomes.CHERRY_GROVE)),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.CHERRY_TWIG_PLACED);

        // 8. MANGROVE
        BiomeModifications.addFeature(validTwigArea.and(BiomeSelectors.includeByKey(Biomes.MANGROVE_SWAMP)),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.MANGROVE_TWIG_PLACED);

        // 9. PALE OAK (Pale Garden)
        BiomeModifications.addFeature(validTwigArea.and(BiomeSelectors.includeByKey(Biomes.PALE_GARDEN)),
                GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.PALE_OAK_TWIG_PLACED);
    }
}
