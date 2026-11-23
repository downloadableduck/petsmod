//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.mixin.client;

import net.minecraft.client.render.EntityRendererDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.core.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import com.jeff.pets.client.PetsClientInitializer;

import java.util.*;

@Mixin({EntityRendererDispatcher.class})
public abstract class EntityRenderDispatcherMixin {
    @Shadow
    @Final
    private Map<Class<? extends Entity>, EntityRenderer> renderers;
    @Unique
    private final Set<Class<? extends Entity>> petsInstalled = new HashSet();

    public EntityRenderDispatcherMixin() {
    }

    @Inject(
        at = {@At("HEAD")},
        method = {"getRenderer(Lnet/minecraft/core/entity/Entity;)Lnet/minecraft/client/render/entity/EntityRenderer;"}
    )
    private void onGetRenderer(Entity entity, CallbackInfoReturnable<EntityRenderer> cir) {
        Map<Class<? extends Entity>, PetsClientInitializer.Factory> suppliers = PetsClientInitializer.renderSupplierMap;
        if (!suppliers.isEmpty() && suppliers.size() != this.petsInstalled.size()) {

            try {
                synchronized(PetsClientInitializer.renderManagerMap.keySet()) {
                    Iterator var3 = suppliers.entrySet().iterator();

                    while(var3.hasNext()) {
                        Map.Entry<Class<? extends Entity>, PetsClientInitializer.Factory> entry = (Map.Entry)var3.next();
                        if (!this.petsInstalled.contains(entry.getKey())) {
                            this.renderers.put((Class)entry.getKey(), entry.getValue().create((EntityRendererDispatcher)(Object)this, new PetsClientInitializer.Context(new HashMap())));
                            this.petsInstalled.add((Class)entry.getKey());
                        }
                    }

                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
