package com.jeff.pets.mob.vanilla.boss;

import com.jeff.pets.CanFly;
import com.jeff.pets.client.Math2;
import com.jeff.pets.mob.FlyingPet;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.dragon.phase.IPhase;
import net.minecraft.entity.boss.dragon.phase.PhaseList;
import net.minecraft.entity.boss.dragon.phase.PhaseManager;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.end.DragonFightManager;
import net.minecraft.world.gen.feature.WorldGenEndPodium;

@CanFly
public class ClientEnderDragon extends FlyingPet {
    public final double[][] positions = new double[64][3];
    public float oFlapTime;
    public float flapTime;
    public int posPointer = -1;
    private PhaseManager phaseManager;

    public ClientEnderDragon(net.minecraft.world.World level) {
        super(level);
        this.setSize(16f, 8f);
        this.phaseManager = new PhaseManager(new EntityDragon(level));
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
        return SoundEvents.ENTITY_ENDER_DRAGON_FLAP;
    }

    @Override
    public void tick() {
        super.tick();
        this.oFlapTime = this.flapTime;
        float g = 0.2F / ((float) this.motionY * 10.0F + 1.0F);
        g *= (float) Math.pow(2.0F, this.motionY);
        this.flapTime += g;
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
        ds[2] = Math2.lerp(f, this.positions[j][2], this.positions[k][2]);
        return ds;
    }

    public float getHeadPartYOffset(int i, double[] ds, double[] es) {
        IPhase iphase = this.phaseManager.getCurrentPhase();
        PhaseList<? extends IPhase> phaselist = iphase.getType();
        double d0;
        if (phaselist != PhaseList.LANDING && phaselist != PhaseList.TAKEOFF) {
            if (iphase.getIsStationary()) {
                d0 = (double)i;
            } else if (i == 6) {
                d0 = (double)0.0F;
            } else {
                d0 = ds[1] - es[1];
            }
        } else {
            BlockPos blockpos = this.world.func_175672_r(WorldGenEndPodium.END_PODIUM_LOCATION);
            float f = Math.max(MathHelper.sqrt(this.getDistanceSqToCenter(blockpos)) / 4.0F, 1.0F);
            d0 = (double)((float)i / f);
        }

        return (float)d0;
    }

    public Vec3d getHeadLookVec(float p_184665_1_) {
        IPhase iphase = this.phaseManager.getCurrentPhase();
        PhaseList<? extends IPhase> phaselist = iphase.getType();
        Vec3d vec3d;
        if (phaselist != PhaseList.LANDING && phaselist != PhaseList.TAKEOFF) {
            if (iphase.getIsStationary()) {
                float f4 = this.rotationPitch;
                float f5 = 1.5F;
                this.rotationPitch = -45.0F;
                vec3d = this.getLook(p_184665_1_);
                this.rotationPitch = f4;
            } else {
                vec3d = this.getLook(p_184665_1_);
            }
        } else {
            BlockPos blockpos = this.world.func_175672_r(WorldGenEndPodium.END_PODIUM_LOCATION);
            float f = Math.max(MathHelper.sqrt(this.getDistanceSqToCenter(blockpos)) / 4.0F, 1.0F);
            float f1 = 6.0F / f;
            float f2 = this.rotationPitch;
            float f3 = 1.5F;
            this.rotationPitch = -f1 * 1.5F * 5.0F;
            vec3d = this.getLook(p_184665_1_);
            this.rotationPitch = f2;
        }

        return vec3d;
    }
}
