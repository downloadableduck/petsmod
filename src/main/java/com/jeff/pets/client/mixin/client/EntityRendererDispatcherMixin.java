package com.jeff.pets.client.mixin.client;

import com.jeff.pets.client.PetsClientInitializer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(RenderManager.class)
public abstract class EntityRendererDispatcherMixin {

    @Shadow
    private Map entityRenderMap;

    @Inject(at = @At("TAIL"), method = "<init>")
    public void onFunc(CallbackInfo ci) {
        PetsClientInitializer.register();
        synchronized (PetsClientInitializer.renderManagerMap.keySet()) {
            for (Map.Entry<Class, PetsClientInitializer.Factory> entry : PetsClientInitializer.renderSupplierMap.entrySet()) {
                this.entityRenderMap.put(entry.getKey(), entry.getValue().create((RenderManager) (Object) this, new PetsClientInitializer.Context((Map) entityRenderMap)));
            }
        }
    }
}
