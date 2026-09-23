package com.ilyrac.pebblesandtwigs.worldgen;

import com.ilyrac.pebblesandtwigs.PebblesAndTwigs;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.function.Predicate;

public class ModWorldGeneration {

    // Pebble Keys
    public static final ResourceKey<PlacedFeature> STONE_PEBBLE_SURFACE_VERY_COMMON = createKey("stone_pebble_surface_very_common");
    public static final ResourceKey<PlacedFeature> STONE_PEBBLE_SURFACE_COMMON = createKey("stone_pebble_surface_common");
    public static final ResourceKey<PlacedFeature> ANDESITE_PEBBLE_SURFACE_COMMON = createKey("andesite_pebble_surface_common");
    public static final ResourceKey<PlacedFeature> ANDESITE_PEBBLE_SURFACE_LESS_COMMON = createKey("andesite_pebble_surface_less_common");
    public static final ResourceKey<PlacedFeature> DIORITE_PEBBLE_SURFACE_COMMON = createKey("diorite_pebble_surface_common");
    public static final ResourceKey<PlacedFeature> DIORITE_PEBBLE_SURFACE_LESS_COMMON = createKey("diorite_pebble_surface_less_common");
    public static final ResourceKey<PlacedFeature> GRANITE_PEBBLE_SURFACE_COMMON = createKey("granite_pebble_surface_common");
    public static final ResourceKey<PlacedFeature> GRANITE_PEBBLE_SURFACE_LESS_COMMON = createKey("granite_pebble_surface_less_common");
    public static final ResourceKey<PlacedFeature> SANDSTONE_PEBBLE_SURFACE_VERY_COMMON = createKey("sandstone_pebble_surface_very_common");
    public static final ResourceKey<PlacedFeature> RED_SANDSTONE_PEBBLE_SURFACE_VERY_COMMON = createKey("red_sandstone_pebble_surface_very_common");
    public static final ResourceKey<PlacedFeature> DEEPSLATE_PEBBLE_SURFACE_RARE = createKey("deepslate_pebble_surface_rare");
    public static final ResourceKey<PlacedFeature> TUFF_PEBBLE_SURFACE_RARE = createKey("tuff_pebble_surface_rare");

    public static final ResourceKey<PlacedFeature> STONE_PEBBLE_WATER_COMMON = createKey("stone_pebble_water_common");
    public static final ResourceKey<PlacedFeature> SANDSTONE_PEBBLE_WATER_COMMON = createKey("sandstone_pebble_water_common");
    public static final ResourceKey<PlacedFeature> RED_SANDSTONE_PEBBLE_WATER_COMMON = createKey("red_sandstone_pebble_water_common");
    public static final ResourceKey<PlacedFeature> ANDESITE_PEBBLE_WATER_RARE = createKey("andesite_pebble_water_rare");
    public static final ResourceKey<PlacedFeature> DIORITE_PEBBLE_WATER_RARE = createKey("diorite_pebble_water_rare");
    public static final ResourceKey<PlacedFeature> GRANITE_PEBBLE_WATER_RARE = createKey("granite_pebble_water_rare");

    public static final ResourceKey<PlacedFeature> STONE_PEBBLE_CAVE_SHALLOW = createKey("stone_pebble_cave_shallow");
    public static final ResourceKey<PlacedFeature> ANDESITE_PEBBLE_CAVE_SHALLOW = createKey("andesite_pebble_cave_shallow");
    public static final ResourceKey<PlacedFeature> DIORITE_PEBBLE_CAVE_SHALLOW = createKey("diorite_pebble_cave_shallow");
    public static final ResourceKey<PlacedFeature> GRANITE_PEBBLE_CAVE_SHALLOW = createKey("granite_pebble_cave_shallow");
    public static final ResourceKey<PlacedFeature> DEEPSLATE_PEBBLE_CAVE_SHALLOW = createKey("deepslate_pebble_cave_shallow");
    public static final ResourceKey<PlacedFeature> TUFF_PEBBLE_CAVE_SHALLOW = createKey("tuff_pebble_cave_shallow");

    public static final ResourceKey<PlacedFeature> STONE_PEBBLE_CAVE_DEEP = createKey("stone_pebble_cave_deep");
    public static final ResourceKey<PlacedFeature> ANDESITE_PEBBLE_CAVE_DEEP = createKey("andesite_pebble_cave_deep");
    public static final ResourceKey<PlacedFeature> DIORITE_PEBBLE_CAVE_DEEP = createKey("diorite_pebble_cave_deep");
    public static final ResourceKey<PlacedFeature> GRANITE_PEBBLE_CAVE_DEEP = createKey("granite_pebble_cave_deep");
    public static final ResourceKey<PlacedFeature> DEEPSLATE_PEBBLE_CAVE_DEEP = createKey("deepslate_pebble_cave_deep");
    public static final ResourceKey<PlacedFeature> TUFF_PEBBLE_CAVE_DEEP = createKey("tuff_pebble_cave_deep");

    public static final ResourceKey<PlacedFeature> BLACKSTONE_PEBBLE_NETHER = createKey("blackstone_pebble_nether");
    public static final ResourceKey<PlacedFeature> END_STONE_PEBBLE_END = createKey("end_stone_pebble_end");

    // Twig Keys
    public static final ResourceKey<PlacedFeature> OAK_TWIG_PRIMARY = createKey("oak_twig_primary");
    public static final ResourceKey<PlacedFeature> OAK_TWIG_SECONDARY = createKey("oak_twig_secondary");
    public static final ResourceKey<PlacedFeature> ACACIA_TWIG_PRIMARY = createKey("acacia_twig_primary");
    public static final ResourceKey<PlacedFeature> BIRCH_TWIG_PRIMARY = createKey("birch_twig_primary");
    public static final ResourceKey<PlacedFeature> CHERRY_TWIG_PRIMARY = createKey("cherry_twig_primary");
    public static final ResourceKey<PlacedFeature> DARK_OAK_TWIG_PRIMARY = createKey("dark_oak_twig_primary");
    public static final ResourceKey<PlacedFeature> JUNGLE_TWIG_PRIMARY = createKey("jungle_twig_primary");
    public static final ResourceKey<PlacedFeature> MANGROVE_TWIG_PRIMARY = createKey("mangrove_twig_primary");
    public static final ResourceKey<PlacedFeature> PALE_OAK_TWIG_PRIMARY = createKey("pale_oak_twig_primary");
    public static final ResourceKey<PlacedFeature> POPLAR_TWIG_PRIMARY = createKey("poplar_twig_primary");
    public static final ResourceKey<PlacedFeature> SPRUCE_TWIG_PRIMARY = createKey("spruce_twig_primary");
    public static final ResourceKey<PlacedFeature> CRIMSON_TWIG_PRIMARY = createKey("crimson_twig_primary");
    public static final ResourceKey<PlacedFeature> WARPED_TWIG_PRIMARY = createKey("warped_twig_primary");

    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(PebblesAndTwigs.MOD_ID, name));
    }

    public static void initializer() {
        TagKey<Biome> SNOWY_BIOMES = TagKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath("c", "snowy"));

        // Pebble Filters
        Predicate<BiomeSelectionContext> isDesert = BiomeSelectors.includeByKey(Biomes.DESERT);
        Predicate<BiomeSelectionContext> isBadlands = BiomeSelectors.tag(BiomeTags.IS_BADLANDS);
        Predicate<BiomeSelectionContext> isSnowy = BiomeSelectors.tag(SNOWY_BIOMES);

        Predicate<BiomeSelectionContext> normalSurface = BiomeSelectors.foundInOverworld().and(isDesert.negate()).and(isBadlands.negate()).and(isSnowy.negate());
        Predicate<BiomeSelectionContext> desertSurface = isDesert.and(isSnowy.negate());
        Predicate<BiomeSelectionContext> badlandsSurface = isBadlands.and(isSnowy.negate());
        Predicate<BiomeSelectionContext> allCaves = BiomeSelectors.foundInOverworld();

        // Twig Filters
        Predicate<BiomeSelectionContext> isOcean = BiomeSelectors.tag(BiomeTags.IS_OCEAN).or(BiomeSelectors.tag(BiomeTags.IS_DEEP_OCEAN));
        Predicate<BiomeSelectionContext> isBirch = BiomeSelectors.includeByKey(Biomes.BIRCH_FOREST, Biomes.OLD_GROWTH_BIRCH_FOREST);
        Predicate<BiomeSelectionContext> isSpruce = BiomeSelectors.includeByKey(Biomes.TAIGA, Biomes.SNOWY_TAIGA, Biomes.OLD_GROWTH_PINE_TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA, Biomes.WINDSWEPT_FOREST, Biomes.GROVE);
        Predicate<BiomeSelectionContext> isJungle = BiomeSelectors.includeByKey(Biomes.JUNGLE, Biomes.SPARSE_JUNGLE, Biomes.BAMBOO_JUNGLE);
        Predicate<BiomeSelectionContext> isAcacia = BiomeSelectors.includeByKey(Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU, Biomes.WINDSWEPT_SAVANNA);
        Predicate<BiomeSelectionContext> isDarkOak = BiomeSelectors.includeByKey(Biomes.DARK_FOREST);
        Predicate<BiomeSelectionContext> isMangrove = BiomeSelectors.includeByKey(Biomes.MANGROVE_SWAMP);
        Predicate<BiomeSelectionContext> isCherry = BiomeSelectors.includeByKey(Biomes.CHERRY_GROVE);
        Predicate<BiomeSelectionContext> isPaleOak = BiomeSelectors.includeByKey(Biomes.PALE_GARDEN);
        Predicate<BiomeSelectionContext> isPoplar = BiomeSelectors.includeByKey(Biomes.DAPPLED_FOREST);

        Predicate<BiomeSelectionContext> hasSpecificWood = isBirch.or(isSpruce).or(isJungle).or(isAcacia).or(isDarkOak).or(isMangrove).or(isCherry).or(isPaleOak).or(isPoplar);

        Predicate<BiomeSelectionContext> oakPrimarySurface = BiomeSelectors.foundInOverworld().and(isSnowy.negate()).and(isOcean.negate()).and(hasSpecificWood.negate());
        Predicate<BiomeSelectionContext> oakSecondarySurface = BiomeSelectors.foundInOverworld().and(isSnowy.negate()).and(isOcean.negate()).and(hasSpecificWood);

        /* --- NORMAL BIOME PEBBLES --- */
        BiomeModifications.addFeature(normalSurface, GenerationStep.Decoration.VEGETAL_DECORATION, STONE_PEBBLE_SURFACE_VERY_COMMON);
        BiomeModifications.addFeature(normalSurface, GenerationStep.Decoration.VEGETAL_DECORATION, ANDESITE_PEBBLE_SURFACE_COMMON);
        BiomeModifications.addFeature(normalSurface, GenerationStep.Decoration.VEGETAL_DECORATION, DIORITE_PEBBLE_SURFACE_COMMON);
        BiomeModifications.addFeature(normalSurface, GenerationStep.Decoration.VEGETAL_DECORATION, GRANITE_PEBBLE_SURFACE_COMMON);
        BiomeModifications.addFeature(normalSurface, GenerationStep.Decoration.VEGETAL_DECORATION, DEEPSLATE_PEBBLE_SURFACE_RARE);
        BiomeModifications.addFeature(normalSurface, GenerationStep.Decoration.VEGETAL_DECORATION, TUFF_PEBBLE_SURFACE_RARE);
        BiomeModifications.addFeature(normalSurface, GenerationStep.Decoration.VEGETAL_DECORATION, STONE_PEBBLE_WATER_COMMON);
        BiomeModifications.addFeature(normalSurface, GenerationStep.Decoration.VEGETAL_DECORATION, ANDESITE_PEBBLE_WATER_RARE);
        BiomeModifications.addFeature(normalSurface, GenerationStep.Decoration.VEGETAL_DECORATION, DIORITE_PEBBLE_WATER_RARE);
        BiomeModifications.addFeature(normalSurface, GenerationStep.Decoration.VEGETAL_DECORATION, GRANITE_PEBBLE_WATER_RARE);

        /* --- DESERT BIOME PEBBLES --- */
        BiomeModifications.addFeature(desertSurface, GenerationStep.Decoration.VEGETAL_DECORATION, SANDSTONE_PEBBLE_SURFACE_VERY_COMMON);
        BiomeModifications.addFeature(desertSurface, GenerationStep.Decoration.VEGETAL_DECORATION, STONE_PEBBLE_SURFACE_COMMON);
        BiomeModifications.addFeature(desertSurface, GenerationStep.Decoration.VEGETAL_DECORATION, ANDESITE_PEBBLE_SURFACE_LESS_COMMON);
        BiomeModifications.addFeature(desertSurface, GenerationStep.Decoration.VEGETAL_DECORATION, DIORITE_PEBBLE_SURFACE_LESS_COMMON);
        BiomeModifications.addFeature(desertSurface, GenerationStep.Decoration.VEGETAL_DECORATION, GRANITE_PEBBLE_SURFACE_LESS_COMMON);
        BiomeModifications.addFeature(desertSurface, GenerationStep.Decoration.VEGETAL_DECORATION, DEEPSLATE_PEBBLE_SURFACE_RARE);
        BiomeModifications.addFeature(desertSurface, GenerationStep.Decoration.VEGETAL_DECORATION, TUFF_PEBBLE_SURFACE_RARE);
        BiomeModifications.addFeature(desertSurface, GenerationStep.Decoration.VEGETAL_DECORATION, SANDSTONE_PEBBLE_WATER_COMMON);

        /* --- BADLANDS BIOME PEBBLES --- */
        BiomeModifications.addFeature(badlandsSurface, GenerationStep.Decoration.VEGETAL_DECORATION, RED_SANDSTONE_PEBBLE_SURFACE_VERY_COMMON);
        BiomeModifications.addFeature(badlandsSurface, GenerationStep.Decoration.VEGETAL_DECORATION, STONE_PEBBLE_SURFACE_COMMON);
        BiomeModifications.addFeature(badlandsSurface, GenerationStep.Decoration.VEGETAL_DECORATION, ANDESITE_PEBBLE_SURFACE_LESS_COMMON);
        BiomeModifications.addFeature(badlandsSurface, GenerationStep.Decoration.VEGETAL_DECORATION, DIORITE_PEBBLE_SURFACE_LESS_COMMON);
        BiomeModifications.addFeature(badlandsSurface, GenerationStep.Decoration.VEGETAL_DECORATION, GRANITE_PEBBLE_SURFACE_LESS_COMMON);
        BiomeModifications.addFeature(badlandsSurface, GenerationStep.Decoration.VEGETAL_DECORATION, DEEPSLATE_PEBBLE_SURFACE_RARE);
        BiomeModifications.addFeature(badlandsSurface, GenerationStep.Decoration.VEGETAL_DECORATION, TUFF_PEBBLE_SURFACE_RARE);
        BiomeModifications.addFeature(badlandsSurface, GenerationStep.Decoration.VEGETAL_DECORATION, RED_SANDSTONE_PEBBLE_WATER_COMMON);

        /* --- CAVE & DIMENSION PEBBLES --- */
        BiomeModifications.addFeature(allCaves, GenerationStep.Decoration.UNDERGROUND_DECORATION, STONE_PEBBLE_CAVE_SHALLOW);
        BiomeModifications.addFeature(allCaves, GenerationStep.Decoration.UNDERGROUND_DECORATION, ANDESITE_PEBBLE_CAVE_SHALLOW);
        BiomeModifications.addFeature(allCaves, GenerationStep.Decoration.UNDERGROUND_DECORATION, DIORITE_PEBBLE_CAVE_SHALLOW);
        BiomeModifications.addFeature(allCaves, GenerationStep.Decoration.UNDERGROUND_DECORATION, GRANITE_PEBBLE_CAVE_SHALLOW);
        BiomeModifications.addFeature(allCaves, GenerationStep.Decoration.UNDERGROUND_DECORATION, DEEPSLATE_PEBBLE_CAVE_SHALLOW);
        BiomeModifications.addFeature(allCaves, GenerationStep.Decoration.UNDERGROUND_DECORATION, TUFF_PEBBLE_CAVE_SHALLOW);

        BiomeModifications.addFeature(allCaves, GenerationStep.Decoration.UNDERGROUND_DECORATION, STONE_PEBBLE_CAVE_DEEP);
        BiomeModifications.addFeature(allCaves, GenerationStep.Decoration.UNDERGROUND_DECORATION, ANDESITE_PEBBLE_CAVE_DEEP);
        BiomeModifications.addFeature(allCaves, GenerationStep.Decoration.UNDERGROUND_DECORATION, DIORITE_PEBBLE_CAVE_DEEP);
        BiomeModifications.addFeature(allCaves, GenerationStep.Decoration.UNDERGROUND_DECORATION, GRANITE_PEBBLE_CAVE_DEEP);
        BiomeModifications.addFeature(allCaves, GenerationStep.Decoration.UNDERGROUND_DECORATION, DEEPSLATE_PEBBLE_CAVE_DEEP);
        BiomeModifications.addFeature(allCaves, GenerationStep.Decoration.UNDERGROUND_DECORATION, TUFF_PEBBLE_CAVE_DEEP);

        BiomeModifications.addFeature(BiomeSelectors.foundInTheNether(), GenerationStep.Decoration.UNDERGROUND_DECORATION, BLACKSTONE_PEBBLE_NETHER);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Decoration.SURFACE_STRUCTURES, END_STONE_PEBBLE_END);

        /* --- TWIG INJECTIONS --- */
        BiomeModifications.addFeature(oakPrimarySurface, GenerationStep.Decoration.VEGETAL_DECORATION, OAK_TWIG_PRIMARY);
        BiomeModifications.addFeature(oakSecondarySurface, GenerationStep.Decoration.VEGETAL_DECORATION, OAK_TWIG_SECONDARY);
        BiomeModifications.addFeature(isAcacia.and(isSnowy.negate()), GenerationStep.Decoration.VEGETAL_DECORATION, ACACIA_TWIG_PRIMARY);
        BiomeModifications.addFeature(isBirch.and(isSnowy.negate()), GenerationStep.Decoration.VEGETAL_DECORATION, BIRCH_TWIG_PRIMARY);
        BiomeModifications.addFeature(isCherry.and(isSnowy.negate()), GenerationStep.Decoration.VEGETAL_DECORATION, CHERRY_TWIG_PRIMARY);
        BiomeModifications.addFeature(isDarkOak.and(isSnowy.negate()), GenerationStep.Decoration.VEGETAL_DECORATION, DARK_OAK_TWIG_PRIMARY);
        BiomeModifications.addFeature(isJungle.and(isSnowy.negate()), GenerationStep.Decoration.VEGETAL_DECORATION, JUNGLE_TWIG_PRIMARY);
        BiomeModifications.addFeature(isMangrove.and(isSnowy.negate()), GenerationStep.Decoration.VEGETAL_DECORATION, MANGROVE_TWIG_PRIMARY);
        BiomeModifications.addFeature(isPaleOak.and(isSnowy.negate()), GenerationStep.Decoration.VEGETAL_DECORATION, PALE_OAK_TWIG_PRIMARY);
        BiomeModifications.addFeature(isPoplar.and(isSnowy.negate()), GenerationStep.Decoration.VEGETAL_DECORATION, POPLAR_TWIG_PRIMARY);
        BiomeModifications.addFeature(isSpruce.and(isSnowy.negate()), GenerationStep.Decoration.VEGETAL_DECORATION, SPRUCE_TWIG_PRIMARY);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.CRIMSON_FOREST), GenerationStep.Decoration.VEGETAL_DECORATION, CRIMSON_TWIG_PRIMARY);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.WARPED_FOREST), GenerationStep.Decoration.VEGETAL_DECORATION, WARPED_TWIG_PRIMARY);
    }
}