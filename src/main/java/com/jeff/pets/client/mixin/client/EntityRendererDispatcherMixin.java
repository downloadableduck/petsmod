package com.jeff.pets.client.mixin.client;

import com.jeff.pets.client.PetsClientInitializer;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(value = RenderManager.class, remap = false)
public abstract class EntityRendererDispatcherMixin {

    @Shadow
    @Final
    private Map<Class<? extends Entity>, Render<? extends Entity>> k;

    @Inject(at = @At("RETURN"), method = "<init>")
    public void onFunc(TextureManager p_i46180_1_, RenderItem p_i46180_2_, CallbackInfo ci) {
        PetsClientInitializer.register();
        synchronized (PetsClientInitializer.renderManagerMap.keySet()) {
            for (Map.Entry<Class, PetsClientInitializer.Factory> entry : PetsClientInitializer.renderSupplierMap.entrySet()) {
                this.k.put(entry.getKey(), entry.getValue().create((RenderManager) (Object) this, new PetsClientInitializer.Context((Map) k)));
            }
        }
    }
}
