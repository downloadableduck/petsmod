package com.jeff.pets.test;

import com.jeff.pets.test.mob.TestDummy;
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

public class PetsModTestInit implements ModInitializer {

    public static final String MOD_ID = "pets-mod-test";

    public static final ResourceKey<@NotNull EntityType<?>> TEST_DUMMY_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "test-dummy"));
    public static final EntityType<@NotNull TestDummy> TEST_DUMMY = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(MOD_ID, "test-dummy"),
            EntityType.Builder.of(TestDummy::new, MobCategory.CREATURE)
                    .sized(1f, 1f)
                    .eyeHeight(1)
                    .build(TEST_DUMMY_KEY));

    @Override
    public void onInitialize() {
        FabricDefaultAttributeRegistry.register(TEST_DUMMY, TestDummy.createAttributes().build());
    }
}
