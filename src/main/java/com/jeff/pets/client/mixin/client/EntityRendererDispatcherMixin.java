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

@Mixin(RenderManager.class)
public abstract class EntityRendererDispatcherMixin {

    @Shadow
    @Final
    public Map<Class<? extends Entity>, Render<? extends Entity>> field_78729_o;

    @Inject(at = @At("TAIL"), method = "<init>(Lnet/minecraft/client/renderer/texture/TextureManager;Lnet/minecraft/client/renderer/RenderItem;)V")
    public void onFunc(TextureManager p_i46180_1_, RenderItem p_i46180_2_, CallbackInfo ci) {
        PetsClientInitializer.register();
        synchronized (PetsClientInitializer.renderManagerMap.keySet()) {
            for (Map.Entry<Class, PetsClientInitializer.Factory> entry : PetsClientInitializer.renderSupplierMap.entrySet()) {
                this.field_78729_o.put(entry.getKey(), entry.getValue().create((RenderManager) (Object) this, new PetsClientInitializer.Context((Map) field_78729_o)));
            }
        }
    }
}
