package com.jeff.duck;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.Heightmap;

public class DuckSpawns {
    public static void addDuckSpawn() {
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.PLAINS, Biomes.CHERRY_GROVE, Biomes.BEACH, Biomes.DARK_FOREST, Biomes.FOREST, Biomes.SPARSE_JUNGLE),
                MobCategory.CREATURE, DuckInitializer.DUCK, 100, 2, 7);
        SpawnPlacements.register(DuckInitializer.DUCK, SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.WORLD_SURFACE, Animal::checkAnimalSpawnRules);
    }
}
