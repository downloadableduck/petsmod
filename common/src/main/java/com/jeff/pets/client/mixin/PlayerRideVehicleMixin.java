package com.jeff.pets.client.mixin;

import com.jeff.pets.client.Central;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.VehicleEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.mojang.text2speech.Narrator.LOGGER;

@Mixin(Entity.class)
public class PlayerRideVehicleMixin {
    @Inject(at = @At("HEAD"), method = "startRiding(Lnet/minecraft/world/entity/Entity;ZZ)Z")
    private void onPlayerStartRiding(Entity entityToRide, boolean force, boolean sendEventAndTriggers, CallbackInfoReturnable<Boolean> cir) {
        if (((Entity) (Object) this) instanceof Player && entityToRide instanceof VehicleEntity) {
            LOGGER.info("player started riding!");
            Central.onGetInVehicle();
        }
    }
}
