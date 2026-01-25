package com.jeff.duck;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DuckInitializer implements ModInitializer {
    public static final String MOD_ID = "duck";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final ResourceKey<@NotNull EntityType<?>> DUCK_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "duck"));


    public static final EntityType<@NotNull Duck> DUCK = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "duck"),
            EntityType.Builder.of(Duck::new, MobCategory.CREATURE)
                    .sized(1f, 1f)
                    .eyeHeight(1)
                    .build(DUCK_KEY));

    @Override
    public void onInitialize() {

        FabricDefaultAttributeRegistry.register(DUCK, Duck.createAttributes().build());

        DuckSounds.initialize();

        DuckSpawns.addDuckSpawn();

        LOGGER.info("quack");
    }
}