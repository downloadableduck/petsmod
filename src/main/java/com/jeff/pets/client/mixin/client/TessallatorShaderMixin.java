package com.jeff.pets.client.mixin.client;

import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.render.tessellator.TessellatorShader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TessellatorShader.class)
public class TessallatorShaderMixin {
    @Inject(at = @At("CTOR_HEAD"), method = "<init>")
    private void onInit(String name, int initialCapacity, CallbackInfo ci) {
        PetRenderer.tessellatorShader = (TessellatorShader) (Object) this;
    }
}
