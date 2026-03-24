package com.ilyrac.pebblesandtwigs.world;

import com.ilyrac.pebblesandtwigs.PebblesAndTwigs;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> BLACKSTONE_PEBBLE_PLACED = registerKey(
            "blackstone_pebble_placed");
    public static final ResourceKey<PlacedFeature> END_STONE_PEBBLE_PLACED = registerKey(
            "end_stone_pebble_placed");
    public static final ResourceKey<PlacedFeature> CRIMSON_TWIG_PLACED = registerKey(
            "crimson_twig_placed");
    public static final ResourceKey<PlacedFeature> WARPED_TWIG_PLACED = registerKey(
            "warped_twig_placed");

    public static final ResourceKey<PlacedFeature> STONE_PEBBLE_PLACED = registerKey(
            "stone_pebble_placed");
    public static final ResourceKey<PlacedFeature> STONE_PEBBLE_TIER2 = registerKey(
            "stone_pebble_tier2");
    public static final ResourceKey<PlacedFeature> SANDSTONE_PEBBLE_PLACED = registerKey(
            "sandstone_pebble_placed");
    public static final ResourceKey<PlacedFeature> RED_SANDSTONE_PEBBLE_PLACED = registerKey(
            "red_sandstone_pebble_placed");
    public static final ResourceKey<PlacedFeature> ANDESITE_PEBBLE_TIER2 = registerKey(
            "andesite_pebble_tier2");
    public static final ResourceKey<PlacedFeature> GRANITE_PEBBLE_TIER2 = registerKey(
            "granite_pebble_tier2");
    public static final ResourceKey<PlacedFeature> DIORITE_PEBBLE_TIER2 = registerKey(
            "diorite_pebble_tier2");
    public static final ResourceKey<PlacedFeature> TUFF_PEBBLE_TIER3 = registerKey(
            "tuff_pebble_tier3");
    public static final ResourceKey<PlacedFeature> DEEPSLATE_PEBBLE_TIER3 = registerKey(
            "deepslate_pebble_tier3");

    public static final ResourceKey<PlacedFeature> STONE_PEBBLE_UNDERWATER = registerKey(
            "stone_pebble_underwater");
    public static final ResourceKey<PlacedFeature> ANDESITE_PEBBLE_UNDERWATER = registerKey(
            "andesite_pebble_underwater");
    public static final ResourceKey<PlacedFeature> GRANITE_PEBBLE_UNDERWATER = registerKey(
            "granite_pebble_underwater");
    public static final ResourceKey<PlacedFeature> DIORITE_PEBBLE_UNDERWATER = registerKey(
            "diorite_pebble_underwater");
    public static final ResourceKey<PlacedFeature> TUFF_PEBBLE_UNDERWATER = registerKey(
            "tuff_pebble_underwater");
    public static final ResourceKey<PlacedFeature> DEEPSLATE_PEBBLE_UNDERWATER = registerKey(
            "deepslate_pebble_underwater");

    public static final ResourceKey<PlacedFeature> STONE_PEBBLE_CAVE_KING = registerKey(
            "stone_pebble_cave_king");
    public static final ResourceKey<PlacedFeature> ANDESITE_PEBBLE_CAVE_SECONDARY = registerKey(
            "andesite_pebble_cave_secondary");
    public static final ResourceKey<PlacedFeature> GRANITE_PEBBLE_CAVE_SECONDARY = registerKey(
            "granite_pebble_cave_secondary");
    public static final ResourceKey<PlacedFeature> DIORITE_PEBBLE_CAVE_SECONDARY = registerKey(
            "diorite_pebble_cave_secondary");
    public static final ResourceKey<PlacedFeature> TUFF_PEBBLE_CAVE_UNCOMMON = registerKey(
            "tuff_pebble_cave_uncommon");
    public static final ResourceKey<PlacedFeature> DEEPSLATE_PEBBLE_CAVE_UNCOMMON = registerKey(
            "deepslate_pebble_cave_uncommon");

    public static final ResourceKey<PlacedFeature> DEEPSLATE_PEBBLE_DEEP_KING = registerKey(
            "deepslate_pebble_deep_king");
    public static final ResourceKey<PlacedFeature> TUFF_PEBBLE_DEEP_KING = registerKey(
            "tuff_pebble_deep_king");
    public static final ResourceKey<PlacedFeature> STONE_PEBBLE_DEEP_COMMON = registerKey(
            "stone_pebble_deep_common");
    public static final ResourceKey<PlacedFeature> ANDESITE_PEBBLE_DEEP_UNCOMMON = registerKey(
            "andesite_pebble_deep_uncommon");
    public static final ResourceKey<PlacedFeature> GRANITE_PEBBLE_DEEP_UNCOMMON = registerKey(
            "granite_pebble_deep_uncommon");
    public static final ResourceKey<PlacedFeature> DIORITE_PEBBLE_DEEP_UNCOMMON = registerKey(
            "diorite_pebble_deep_uncommon");

    public static final ResourceKey<PlacedFeature> OAK_TWIG_PLACED = registerKey(
            "oak_twig_placed");
    public static final ResourceKey<PlacedFeature> BIRCH_TWIG_PLACED = registerKey(
            "birch_twig_placed");
    public static final ResourceKey<PlacedFeature> SPRUCE_TWIG_PLACED = registerKey(
            "spruce_twig_placed");
    public static final ResourceKey<PlacedFeature> JUNGLE_TWIG_PLACED = registerKey(
            "jungle_twig_placed");
    public static final ResourceKey<PlacedFeature> ACACIA_TWIG_PLACED = registerKey(
            "acacia_twig_placed");
    public static final ResourceKey<PlacedFeature> DARK_OAK_TWIG_PLACED = registerKey(
            "dark_oak_twig_placed");
    public static final ResourceKey<PlacedFeature> CHERRY_TWIG_PLACED = registerKey(
            "cherry_twig_placed");
    public static final ResourceKey<PlacedFeature> MANGROVE_TWIG_PLACED = registerKey(
            "mangrove_twig_placed");
    public static final ResourceKey<PlacedFeature> PALE_OAK_TWIG_PLACED = registerKey(
            "pale_oak_twig_placed");

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(
                Registries.PLACED_FEATURE,
                Identifier.fromNamespaceAndPath(PebblesAndTwigs.MOD_ID, name)
        );
    }
}