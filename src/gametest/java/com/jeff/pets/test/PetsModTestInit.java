package com.jeff.pets.test;

import com.jeff.pets.PetsInitializer;
import com.jeff.pets.test.mob.TestDummy;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.test.PetsModTestInit.MOD_ID;

@Mod(MOD_ID)
public class PetsModTestInit {

    public static final String MOD_ID = "pets_mod_test";

    public static final DeferredRegister<@NotNull EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, MOD_ID);


    public static final ResourceKey<@NotNull EntityType<?>> TEST_DUMMY_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "test-dummy"));
    public static final DeferredHolder<@NotNull EntityType<?>, @NotNull EntityType<@NotNull TestDummy>> TEST_DUMMY = ENTITY_TYPES.register(
            "dummy", () ->
            EntityType.Builder.of(TestDummy::new, MobCategory.CREATURE)
                    .sized(1f, 1f)
                    .eyeHeight(1)
                    .build(TEST_DUMMY_KEY));

    public PetsModTestInit(IEventBus bus) {
        ENTITY_TYPES.register(bus);
        bus.addListener(this::onInitialize);
    }

    public void onInitialize(EntityAttributeCreationEvent event) {
        event.put(TEST_DUMMY.get(), TestDummy.createAttributes().build());
    }
}
