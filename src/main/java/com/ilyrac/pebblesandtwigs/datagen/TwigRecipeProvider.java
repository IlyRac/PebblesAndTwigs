package com.ilyrac.pebblesandtwigs.datagen;

import com.ilyrac.pebblesandtwigs.PebblesAndTwigs;
import com.ilyrac.pebblesandtwigs.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class TwigRecipeProvider extends FabricRecipeProvider {

    public static final TagKey<Item> TWIGS = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(PebblesAndTwigs.MOD_ID, "twigs"));

    public TwigRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public @NonNull String getName() {
        return "Twig Recipes";
    }

    @Override
    protected @NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider registries, @NonNull BootstrapContext<Recipe<?>> recipes, @NonNull BootstrapContext<Advancement> advancements) {
        return new RecipeProvider(recipes, advancements) {

            @Override
            public void buildRecipes() {
                // Pass both regular and stripped twig outputs for each wood family
                offerWoodFamilyRecipes(ModItems.ACACIA_TWIG, ModItems.STRIPPED_ACACIA_TWIG, Items.ACACIA_LOG, Items.STRIPPED_ACACIA_LOG, Items.ACACIA_WOOD, Items.STRIPPED_ACACIA_WOOD);
                offerWoodFamilyRecipes(ModItems.BIRCH_TWIG, ModItems.STRIPPED_BIRCH_TWIG, Items.BIRCH_LOG, Items.STRIPPED_BIRCH_LOG, Items.BIRCH_WOOD, Items.STRIPPED_BIRCH_WOOD);
                offerWoodFamilyRecipes(ModItems.CHERRY_TWIG, ModItems.STRIPPED_CHERRY_TWIG, Items.CHERRY_LOG, Items.STRIPPED_CHERRY_LOG, Items.CHERRY_WOOD, Items.STRIPPED_CHERRY_WOOD);
                offerWoodFamilyRecipes(ModItems.CRIMSON_TWIG, ModItems.STRIPPED_CRIMSON_TWIG, Items.CRIMSON_STEM, Items.STRIPPED_CRIMSON_STEM, Items.CRIMSON_HYPHAE, Items.STRIPPED_CRIMSON_HYPHAE);
                offerWoodFamilyRecipes(ModItems.DARK_OAK_TWIG, ModItems.STRIPPED_DARK_OAK_TWIG, Items.DARK_OAK_LOG, Items.STRIPPED_DARK_OAK_LOG, Items.DARK_OAK_WOOD, Items.STRIPPED_DARK_OAK_WOOD);
                offerWoodFamilyRecipes(ModItems.JUNGLE_TWIG, ModItems.STRIPPED_JUNGLE_TWIG, Items.JUNGLE_LOG, Items.STRIPPED_JUNGLE_LOG, Items.JUNGLE_WOOD, Items.STRIPPED_JUNGLE_WOOD);
                offerWoodFamilyRecipes(ModItems.MANGROVE_TWIG, ModItems.STRIPPED_MANGROVE_TWIG, Items.MANGROVE_LOG, Items.STRIPPED_MANGROVE_LOG, Items.MANGROVE_WOOD, Items.STRIPPED_MANGROVE_WOOD);
                offerWoodFamilyRecipes(ModItems.OAK_TWIG, ModItems.STRIPPED_OAK_TWIG, Items.OAK_LOG, Items.STRIPPED_OAK_LOG, Items.OAK_WOOD, Items.STRIPPED_OAK_WOOD);
                offerWoodFamilyRecipes(ModItems.PALE_OAK_TWIG, ModItems.STRIPPED_PALE_OAK_TWIG, Items.PALE_OAK_LOG, Items.STRIPPED_PALE_OAK_LOG, Items.PALE_OAK_WOOD, Items.STRIPPED_PALE_OAK_WOOD);
                offerWoodFamilyRecipes(ModItems.POPLAR_TWIG, ModItems.STRIPPED_POPLAR_TWIG, Items.POPLAR_LOG, Items.STRIPPED_POPLAR_LOG, Items.POPLAR_WOOD, Items.STRIPPED_POPLAR_WOOD);
                offerWoodFamilyRecipes(ModItems.SPRUCE_TWIG, ModItems.STRIPPED_SPRUCE_TWIG, Items.SPRUCE_LOG, Items.STRIPPED_SPRUCE_LOG, Items.SPRUCE_WOOD, Items.STRIPPED_SPRUCE_WOOD);
                offerWoodFamilyRecipes(ModItems.WARPED_TWIG, ModItems.STRIPPED_WARPED_TWIG, Items.WARPED_STEM, Items.STRIPPED_WARPED_STEM, Items.WARPED_HYPHAE, Items.STRIPPED_WARPED_HYPHAE);

                createTwigToStickRecipe();
            }

            private void offerWoodFamilyRecipes(Item twig, Item strippedTwig, Item log, Item strippedLog, Item wood, Item strippedWood) {
                // Route regular logs/wood to regular twigs
                createLogToTwigRecipe(twig, log);
                createLogToTwigRecipe(twig, wood);

                // Route stripped logs/wood to stripped twigs
                createLogToTwigRecipe(strippedTwig, strippedLog);
                createLogToTwigRecipe(strippedTwig, strippedWood);
            }

            private void createLogToTwigRecipe(Item outputTwig, Item input) {
                this.shaped(RecipeCategory.MISC, outputTwig, 6)
                        .pattern("#")
                        .pattern("#")
                        .define('#', input)
                        .unlockedBy(getHasName(input), this.has(input))
                        .save(this.output, getItemName(outputTwig) + "_from_" + getItemName(input));
            }

            private void createTwigToStickRecipe() {
                // 1 Tagged Twig -> 2 Sticks (Changed from shaped to shapeless)
                this.shapeless(RecipeCategory.MISC, Items.STICK, 2)
                        .requires(TWIGS)
                        .unlockedBy("has_twig", this.has(TWIGS))
                        .save(this.output, "stick_from_twigs");
            }
        };
    }
}