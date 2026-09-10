package com.jeff.pets.client.mixin.client;

import com.jeff.pets.client.PetsClientInitializer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(EntityRenderDispatcher.class)
public abstract class EntityRendererDispatcherMixin {

    @Shadow
    @Final
    private Map<Class<? extends Entity>, EntityRenderer<? extends Entity>> renderers;

    @Inject(at = @At("TAIL"), method = "<init>")
    public void onFunc(net.minecraft.client.texture.TextureManager textureManager, HeldItemRenderer heldItemRenderer, CallbackInfo ci) {
        PetsClientInitializer.register();
        synchronized (PetsClientInitializer.renderManagerMap.keySet()) {
            for (Map.Entry<Class, PetsClientInitializer.Factory> entry : PetsClientInitializer.renderSupplierMap.entrySet()) {
                this.renderers.put(entry.getKey(), entry.getValue().create((EntityRenderDispatcher) (Object) this, new PetsClientInitializer.Context((Map) renderers)));
            }
        }
    }
}
