package com.jeff.pets.client.rendering.vanilla.enderdragon;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.boss.enderdragon.DragonFlightHistory;

public class ClientEnderDragonRenderState extends LivingEntityRenderState {
    public final DragonFlightHistory flightHistory = new DragonFlightHistory();
    public float flapTime;
    public boolean isLandingOrTakingOff;
    public boolean isSitting;
    public double distanceToEgg;
    public float partialTicks;

    public DragonFlightHistory.Sample getHistoricalPos(int i) {
        return this.flightHistory.get(i, this.partialTicks);
    }

    public float getHeadPartYOffset(int i, DragonFlightHistory.Sample sample, DragonFlightHistory.Sample sample2) {
        double d;
        if (this.isLandingOrTakingOff) {
            d = (double) i / Math.max(this.distanceToEgg / (double) 4.0F, 1.0F);
        } else if (this.isSitting) {
            d = i;
        } else if (i == 6) {
            d = 0.0F;
        } else {
            d = sample2.y() - sample.y();
        }

        return (float) d;
    }
}
