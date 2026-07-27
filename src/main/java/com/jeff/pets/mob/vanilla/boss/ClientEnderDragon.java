package com.jeff.pets.mob.vanilla.boss;

import com.jeff.pets.CanFly;
import com.jeff.pets.mob.FlyingPet;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.dragon.phase.IPhase;
import net.minecraft.entity.boss.dragon.phase.PhaseType;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.EndPodiumFeature;

@CanFly
public class ClientEnderDragon extends FlyingPet {
    public final double[][] positions = new double[64][3];
    public float oFlapTime;
    public float flapTime;
    public int posPointer = -1;

    public ClientEnderDragon(EntityType<? extends TameableEntity> entityType, net.minecraft.world.World level) {
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
        Vec3d vec3 = this.getDeltaMovement();
        float g = 0.2F / ((float) vec3.y() * 10.0F + 1.0F);
        g *= (float) Math.pow(2.0F, vec3.y);
        if (this.isInWall()) {
            this.flapTime += g * 0.5F;
        } else {
            this.flapTime += g;
        }
    }

    public double[] getLatencyPos(int i, float f) {
        if (this.dead) {
            f = 0.0F;
        }

        f = 1.0F - f;
        int j = this.posPointer - i & 63;
        int k = this.posPointer - i - 1 & 63;
        double[] ds = new double[3];
        double d = this.positions[j][0];
        double e = net.minecraft.util.math.MathHelper.wrapDegrees(this.positions[k][0] - d);
        ds[0] = d + e * (double) f;
        d = this.positions[j][1];
        e = this.positions[k][1] - d;
        ds[1] = d + e * (double) f;
        ds[2] = net.minecraft.util.math.MathHelper.lerp(f, this.positions[j][2], this.positions[k][2]);
        return ds;
    }

    public float getHeadPartYOffset(int i, double[] ds, double[] es) {
        PhaseType<? extends IPhase> enderDragonPhase = PhaseType.HOLDING_PATTERN;
        double e;
        if (enderDragonPhase != PhaseType.LANDING && enderDragonPhase != PhaseType.TAKEOFF) {
            if (i == 6) {
                e = 0.0F;
            } else {
                e = es[1] - ds[1];
            }
        } else {
            BlockPos blockPos = this.level.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EndPodiumFeature.END_PODIUM_LOCATION);
            double d = Math.max(Math.sqrt(blockPos.distSqr(new Vec3i(this.position().x, this.position().y, this.position().z))) / (double) 4.0F, 1.0F);
            e = (double) i / d;
        }

        return (float) e;
    }
}
