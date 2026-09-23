package com.ilyrac.pebblesandtwigs.datagen;

import com.ilyrac.pebblesandtwigs.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class PebbleRecipeProvider extends FabricRecipeProvider {

    public PebbleRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected @NonNull RecipeProvider createRecipeProvider(
            HolderLookup.@NonNull Provider registries,
            @NonNull BootstrapContext<Recipe<?>> recipes,
            @NonNull BootstrapContext<Advancement> advancements) {
        return new RecipeProvider(recipes, advancements) {

            @Override
            public void buildRecipes() {
                offerReversiblePebbleRecipes(Items.ANDESITE, ModItems.ANDESITE_PEBBLE);
                offerReversiblePebbleRecipes(Items.BLACKSTONE, ModItems.BLACKSTONE_PEBBLE);
                offerReversiblePebbleRecipes(Items.COBBLED_DEEPSLATE, ModItems.DEEPSLATE_PEBBLE);
                offerReversiblePebbleRecipes(Items.DIORITE, ModItems.DIORITE_PEBBLE);
                offerReversiblePebbleRecipes(Items.END_STONE, ModItems.END_STONE_PEBBLE);
                offerReversiblePebbleRecipes(Items.GRANITE, ModItems.GRANITE_PEBBLE);
                offerReversiblePebbleRecipes(Items.RED_SANDSTONE, ModItems.RED_SANDSTONE_PEBBLE);
                offerReversiblePebbleRecipes(Items.SANDSTONE, ModItems.SANDSTONE_PEBBLE);
                offerReversiblePebbleRecipes(Items.COBBLESTONE, ModItems.STONE_PEBBLE);
                offerReversiblePebbleRecipes(Items.TUFF, ModItems.TUFF_PEBBLE);
            }

            private void offerReversiblePebbleRecipes(Item block, Item pebble) {
                String blockName = getItemName(block);
                String pebbleName = getItemName(pebble);

                // 1 Block -> 9 Pebbles
                this.shapeless(RecipeCategory.MISC, pebble, 9)
                        .requires(block)
                        .unlockedBy(getHasName(block), this.has(block))
                        .save(this.output, pebbleName + "_from_" + blockName);

                // 9 Pebbles -> 1 Block
                this.shaped(RecipeCategory.BUILDING_BLOCKS, block, 1)
                        .pattern("###")
                        .pattern("###")
                        .pattern("###")
                        .define('#', pebble)
                        .unlockedBy(getHasName(pebble), this.has(pebble))
                        .save(this.output, blockName + "_from_" + pebbleName);
            }
        };
    }

    @Override
    public @NonNull String getName() {
        return "Pebble Recipes";
    }
}