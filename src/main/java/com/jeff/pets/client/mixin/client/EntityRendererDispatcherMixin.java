package com.jeff.pets.client.mixin.client;

import com.jeff.pets.client.PetsClientInitializer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.resources.IReloadableResourceManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

@Mixin(EntityRendererManager.class)
public abstract class EntityRendererDispatcherMixin {

    @Shadow
    @Final
    public TextureManager field_78724_e;

    @Shadow
    public abstract <T extends Entity> void func_217782_a(Class<T> p_217782_1_, EntityRenderer<? super T> p_217782_2_);

    @Inject(at = @At("TAIL"), method = "<init>")
    public void onFunc(TextureManager textureManager, ItemRenderer itemRenderer, IReloadableResourceManager reloadableResourceManager, CallbackInfo ci) {
        PetsClientInitializer.register();
        synchronized (PetsClientInitializer.renderManagerMap.keySet()) {
            for (Map.Entry<Class, PetsClientInitializer.Factory> entry : PetsClientInitializer.renderSupplierMap.entrySet()) {
                this.func_217782_a(entry.getKey(), (EntityRenderer) entry.getValue().create((EntityRendererManager) (Object) this, new PetsClientInitializer.Context(field_78724_e, reloadableResourceManager, itemRenderer, new HashMap<>())));
            }
        }
    }

    @Inject(at = @At("TAIL"), method = "registerRenderers", require = 0)
    public void onInit(ItemRenderer itemRenderer, IReloadableResourceManager reloadableResourceManager, CallbackInfo ci) {
        synchronized (PetsClientInitializer.renderManagerMap.keySet()) {
            for (Map.Entry<Class, PetsClientInitializer.Factory> entry : PetsClientInitializer.renderSupplierMap.entrySet()) {
                this.func_217782_a(entry.getKey(), (EntityRenderer) entry.getValue().create((EntityRendererManager) (Object) this, new PetsClientInitializer.Context(field_78724_e, reloadableResourceManager, itemRenderer, new HashMap<>())));
            }
        }
    }
}
