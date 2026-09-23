package com.ilyrac.pebblesandtwigs.datagen;

import com.ilyrac.pebblesandtwigs.PebblesAndTwigs;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagsProvider.ItemTagsProvider {

    public ModItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider arg) {
        builder(TwigRecipeProvider.TWIGS)
                .add(createKey("acacia_twig"))
                .add(createKey("birch_twig"))
                .add(createKey("cherry_twig"))
                .add(createKey("crimson_twig"))
                .add(createKey("dark_oak_twig"))
                .add(createKey("jungle_twig"))
                .add(createKey("mangrove_twig"))
                .add(createKey("oak_twig"))
                .add(createKey("pale_oak_twig"))
                .add(createKey("poplar_twig"))
                .add(createKey("spruce_twig"))
                .add(createKey("warped_twig"));
    }

    // Helper method to fetch the ResourceKey instead of the raw Item
    private ResourceKey<Item> createKey(String name) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(PebblesAndTwigs.MOD_ID, name));
    }
}