package com.jeff.pets.client.mixin.client;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Render.class)
public abstract class RenderMixin {
    @Shadow
    protected abstract ResourceLocation func_110775_a(Entity p_110775_1_);

    @Inject(at = @At("HEAD"), method = "func_110777_b", cancellable = true)
    private void pets$onBindEntiyTexture(Entity p_110777_1_, CallbackInfo ci) {
        if (this.func_110775_a(p_110777_1_) == null) {
            ci.cancel();
        }
    }
}
