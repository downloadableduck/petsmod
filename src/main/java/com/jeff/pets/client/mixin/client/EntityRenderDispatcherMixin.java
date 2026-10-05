package com.jeff.pets.client.mixin.client;

import com.jeff.pets.client.PetsClientInitializer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Mixin(EntityRenderDispatcher.class)
public abstract class EntityRenderDispatcherMixin {

    @Shadow
    @Final
    private Map<Class<? extends Entity>, EntityRenderer> renderers;

    @Unique
    private final Set<Class<? extends Entity>> petsInstalled = new HashSet<>();

    @Unique
    private boolean petsInstalling;

    @Inject(at = @At("TAIL"), method = "<init>")
    private void onInit(CallbackInfo ci) {
        this.installPetRenderers();
    }

    /**
     * {@code EntityRenderDispatcher.INSTANCE} is class-initialised from Minecraft's constructor,
     * which runs long before {@code onInitializeClient()} fills {@code renderSupplierMap}. The
     * {@code <init>} injection therefore sees an empty map and registers nothing, leaving every pet
     * on {@code DefaultRenderer}. Re-checking here makes registration self-healing regardless of
     * which side wins the initialisation race.
     */
    @Inject(
            at = @At("HEAD"),
            method = "getRenderer(Lnet/minecraft/entity/Entity;)Lnet/minecraft/client/render/entity/EntityRenderer;")
    private void onGetRenderer(Entity entity, CallbackInfoReturnable<EntityRenderer> cir) {
        this.installPetRenderers();
    }

    @Unique
    private void installPetRenderers() {
        // Guards against a renderer constructor re-entering getRenderer().
        if (this.petsInstalling) {
            return;
        }

        Map<Class<? extends Entity>, PetsClientInitializer.Factory> suppliers =
                PetsClientInitializer.renderSupplierMap;

        if (suppliers.isEmpty() || suppliers.size() == this.petsInstalled.size()) {
            return;
        }

        this.petsInstalling = true;

        try {
            synchronized (PetsClientInitializer.renderManagerMap.keySet()) {
                for (Map.Entry<Class<? extends Entity>, PetsClientInitializer.Factory> entry : suppliers.entrySet()) {
                    if (this.petsInstalled.contains(entry.getKey())) {
                        continue;
                    }

                    this.renderers.put(entry.getKey(), entry.getValue().create(
                            (EntityRenderDispatcher) (Object) this,
                            new PetsClientInitializer.Context(null, null, new HashMap<>())));
                    this.petsInstalled.add(entry.getKey());
                }
            }
        } finally {
            this.petsInstalling = false;
        }
    }
}
