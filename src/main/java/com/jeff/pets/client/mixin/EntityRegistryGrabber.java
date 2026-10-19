package com.jeff.pets.client.mixin;

import com.jeff.pets.client.PetsClientInitializer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.Registry;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityRenderers.class)
public class EntityRegistryGrabber {
    @Inject(at = @At("HEAD"), method = "bootstrap")
    private static void pets$onBootstrap(Registry<@NotNull EntityRendererProvider<?>> registry, CallbackInfoReturnable<EntityRendererProvider<?>> cir) {
        PetsClientInitializer.entityRegistry = registry;
    }
}
