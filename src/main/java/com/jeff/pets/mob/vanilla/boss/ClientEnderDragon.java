package com.jeff.pets.mob.vanilla.boss;

import com.jeff.pets.CanFly;
import com.jeff.pets.mob.FlyingPet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.boss.enderdragon.phases.DragonPhaseInstance;
import net.minecraft.world.entity.boss.enderdragon.phases.EnderDragonPhase;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.EndPodiumFeature;
import net.minecraft.world.phys.Vec3;


@CanFly
public class ClientEnderDragon extends FlyingPet {
    public final double[][] positions = new double[64][3];
    public float oFlapTime;
    public float flapTime;
    public int posPointer = -1;

    public ClientEnderDragon(EntityType<? extends  TamableAnimal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected int stopDistance() {
        return 10;
    }

    @Override
    protected float heartHeight() {
        return 3;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENDER_DRAGON_AMBIENT;
    }

    @Override
    public void tick() {
        super.tick();
        this.oFlapTime = this.flapTime;
        Vec3 vec3 = this.getDeltaMovement();
        float g = 0.2F / ((float) vec3.horizontalDistance() * 10.0F + 1.0F);
        g *= (float) Math.pow((double) 2.0F, vec3.y);
        if (this.isInWall()) {
            this.flapTime += g * 0.5F;
        } else {
            this.flapTime += g;
        }
    }

    public double[] getLatencyPos(int i, float f) {
        if (this.isDeadOrDying()) {
            f = 0.0F;
        }

        f = 1.0F - f;
        int j = this.posPointer - i & 63;
        int k = this.posPointer - i - 1 & 63;
        double[] ds = new double[3];
        double d = this.positions[j][0];
        double e = Mth.wrapDegrees(this.positions[k][0] - d);
        ds[0] = d + e * (double) f;
        d = this.positions[j][1];
        e = this.positions[k][1] - d;
        ds[1] = d + e * (double) f;
        ds[2] = Mth.lerp((double) f, this.positions[j][2], this.positions[k][2]);
        return ds;
    }

    public float getHeadPartYOffset(int i, double[] ds, double[] es) {
        EnderDragonPhase<? extends DragonPhaseInstance> enderDragonPhase = EnderDragonPhase.HOLDING_PATTERN;
        double e;
        if (enderDragonPhase != EnderDragonPhase.LANDING && enderDragonPhase != EnderDragonPhase.TAKEOFF) {
            if (i == 6) {
                e = (double) 0.0F;
            } else {
                e = es[1] - ds[1];
            }
        } else {
            BlockPos blockPos = this.level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EndPodiumFeature.END_PODIUM_LOCATION);
            double d = Math.max(Math.sqrt(blockPos.distSqr(new Vec3i(this.position().x, this.position().y, this.position().z)) / (double) 4.0F), (double) 1.0F);
            e = (double) i / d;
        }

        return (float) e;
    }
}
