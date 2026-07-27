package com.jeff.pets.client.mixin.client;

import com.jeff.pets.PetsInitializer;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.custom.first.racoon.RacoonRenderer;
import net.minecraft.client.GameSettings;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.resources.IReloadableResourceManager;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
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
    public abstract <T extends Entity> void func_229087_a_(EntityType<T> p_229087_1_, EntityRenderer<? super T> p_229087_2_);

    @Shadow
    @Final
    public TextureManager field_78724_e;

    @Inject(at = @At("TAIL"), method = "func_229097_a_")
    public void onFunc(ItemRenderer itemRenderer, IReloadableResourceManager reloadableResourceManager, CallbackInfo ci) {
        PetsClientInitializer.register();
        synchronized (PetsClientInitializer.renderManagerMap.keySet()) {
            for (Map.Entry<EntityType, PetsClientInitializer.Factory> entry : PetsClientInitializer.renderSupplierMap.entrySet()) {
                this.func_229087_a_((EntityType) entry.getKey(), (EntityRenderer) entry.getValue().create((EntityRendererManager) (Object) this, new PetsClientInitializer.Context(field_78724_e, reloadableResourceManager, itemRenderer, new HashMap<>())));
            }
        }
    }

    @Inject(at = @At("TAIL"), method = "registerRenderers", require = 0)
    public void onInit(ItemRenderer itemRenderer, IReloadableResourceManager reloadableResourceManager, CallbackInfo ci) {
        synchronized (PetsClientInitializer.renderManagerMap.keySet()) {
            for (Map.Entry<EntityType, PetsClientInitializer.Factory> entry : PetsClientInitializer.renderSupplierMap.entrySet()) {
                this.func_229087_a_((EntityType) entry.getKey(), (EntityRenderer) entry.getValue().create((EntityRendererManager) (Object) this, new PetsClientInitializer.Context(field_78724_e, reloadableResourceManager, itemRenderer, new HashMap<>())));
            }
        }
    }
}
