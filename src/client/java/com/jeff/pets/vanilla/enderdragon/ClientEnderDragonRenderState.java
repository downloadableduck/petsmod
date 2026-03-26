package com.jeff.pets.vanilla.enderdragon;

import net.minecraft.client.renderer.entity.state.EnderDragonRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.boss.enderdragon.DragonFlightHistory;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public class ClientEnderDragonRenderState extends LivingEntityRenderState {
    public float flapTime;
    public float deathTime;
    public boolean hasRedOverlay;
    public @Nullable Vec3 beamOffset;
    public boolean isLandingOrTakingOff;
    public boolean isSitting;
    public double distanceToEgg;
    public float partialTicks;
    public final DragonFlightHistory flightHistory = new DragonFlightHistory();

    public DragonFlightHistory.Sample getHistoricalPos(int i) {
        return this.flightHistory.get(i, this.partialTicks);
    }

    public float getHeadPartYOffset(int i, DragonFlightHistory.Sample sample, DragonFlightHistory.Sample sample2) {
        double d;
        if (this.isLandingOrTakingOff) {
            d = (double)i / Math.max(this.distanceToEgg / (double)4.0F, (double)1.0F);
        } else if (this.isSitting) {
            d = (double)i;
        } else if (i == 6) {
            d = (double)0.0F;
        } else {
            d = sample2.y() - sample.y();
        }

        return (float)d;
    }
}
