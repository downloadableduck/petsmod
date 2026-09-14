package com.jeff.pets.client.mixin.client;

import com.jeff.pets.mob.AbstractPet;
import net.minecraft.client.renderer.debug.EntityHitboxDebugRenderer;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.jeff.pets.client.Central.CONFIG;

@Mixin(EntityHitboxDebugRenderer.class)
public class NoHitboxMixin {
    @Inject(at = @At("HEAD"), method = "showHitboxes", cancellable = true)
    private void onShowHitboxes(Entity entity, float partialTicks, boolean isServerEntity, CallbackInfo ci) {
        if (entity instanceof AbstractPet && !CONFIG.renderPetHitbox) {
            ci.cancel();
        }
    }
}
