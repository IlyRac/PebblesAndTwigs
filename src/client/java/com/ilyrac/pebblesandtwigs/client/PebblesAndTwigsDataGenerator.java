package com.ilyrac.pebblesandtwigs.client;

import com.ilyrac.pebblesandtwigs.datagen.ModWorldgenProvider;
import com.ilyrac.pebblesandtwigs.worldgen.ModFeatures;
import com.ilyrac.pebblesandtwigs.worldgen.ModPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class PebblesAndTwigsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		//pack.addProvider(ModItemTagProvider::new);
		//pack.addProvider(PebbleRecipeProvider::new);
		//pack.addProvider(TwigRecipeProvider::new);
		pack.addProvider(ModWorldgenProvider::new);
	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		registryBuilder.add(Registries.FEATURE, ModFeatures::bootstrap);
		registryBuilder.add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
	}
}