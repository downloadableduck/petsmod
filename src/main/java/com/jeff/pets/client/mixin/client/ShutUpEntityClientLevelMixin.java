package com.jeff.pets.client.mixin.client;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.entity.TransientEntitySectionManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class ShutUpEntityClientLevelMixin {

    @Shadow
    public int id;

    @Inject(at = @At("HEAD"), method = "getId", cancellable = true)
    private void shutUp(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(this.id);
    }
}
