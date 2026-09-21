package com.jeff.pets.client.mixin.client;

import com.jeff.pets.mob.AbstractPet;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.jeff.pets.client.Central.CONFIG;

@Mixin(RenderManager.class)
public class NoHitboxMixin {
    @Inject(at = @At("HEAD"), method = "func_85094_b", cancellable = true)
    private void petsmod$aabb(Entity p_renderDebugBoundingBox_1_, double p_renderDebugBoundingBox_2_, double p_renderDebugBoundingBox_3_, double p_renderDebugBoundingBox_4_, float p_renderDebugBoundingBox_6_, float p_renderDebugBoundingBox_7_, CallbackInfo ci) {
        if (!CONFIG.renderPetHitbox && p_renderDebugBoundingBox_1_ instanceof AbstractPet) {
            ci.cancel();
        }
    }
}
