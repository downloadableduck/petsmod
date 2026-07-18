package com.jeff.pets.mob.vanilla.boss;

import com.jeff.pets.CanFly;
import com.jeff.pets.mob.FlyingPet;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.boss.dragon.phase.Phase;
import net.minecraft.entity.boss.dragon.phase.PhaseType;
import net.minecraft.world.World;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.EndPortalFeature;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;

@CanFly
public class ClientEnderDragon extends FlyingPet {
    public final double[][] positions = new double[64][3];
    public float oFlapTime;
    public float flapTime;
    public int posPointer = -1;

    public ClientEnderDragon(EntityType<? extends @NotNull TameableEntity> entityType, World level) {
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
        return SoundEvents.ENTITY_ENDER_DRAGON_AMBIENT;
    }

    @Override
    public void tick() {
        super.tick();
        this.oFlapTime = this.flapTime;
        Vec3d vec3 = this.getVelocity();
        float g = 0.2F / ((float) vec3.y * 10.0F + 1.0F);
        g *= (float) Math.pow(2.0F, vec3.y);
        if (this.isInsideWall()) {
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
        double e = MathHelper.wrapDegrees(this.positions[k][0] - d);
        ds[0] = d + e * (double) f;
        d = this.positions[j][1];
        e = this.positions[k][1] - d;
        ds[1] = d + e * (double) f;
        ds[2] = MathHelper.lerp(f, this.positions[j][2], this.positions[k][2]);
        return ds;
    }

    public float getHeadPartYOffset(int i, double[] ds, double[] es) {
        PhaseType<? extends Phase> enderDragonPhase = PhaseType.HOLDING_PATTERN;
        double e;
        if (enderDragonPhase != PhaseType.LANDING && enderDragonPhase != PhaseType.TAKEOFF) {
            if (i == 6) {
                e = 0.0F;
            } else {
                e = es[1] - ds[1];
            }
        } else {
            BlockPos blockPos = this.world.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EndPortalFeature.ORIGIN);
            double d = Math.max(Math.sqrt(blockPos.getSquaredDistance(new Vec3i(this.getPos().x, this.getPos().y, this.getPos().z))) / (double) 4.0F, 1.0F);
            e = (double) i / d;
        }

        return (float) e;
    }
}
