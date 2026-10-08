package com.jeff.pets.mob.vanilla.boss;

import com.jeff.pets.CanFly;
import com.jeff.pets.mob.FlyingPet;
import net.minecraft.entity.boss.EntityDragon;

import net.minecraft.util.MathHelper;

import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.world.World;
import net.minecraft.util.Vec3;
import org.jetbrains.annotations.NotNull;

@CanFly
public class ClientEnderDragon extends FlyingPet {
    public final double[][] positions = new double[64][3];
    public float oFlapTime;
    public float flapTime;
    public int posPointer = -1;

    public ClientEnderDragon(World level) {
        super(level);
        this.setSize(16.0F, 8.0F);
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
    protected String getAmbientSound() {
        return "mob.enderdragon.growl";
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        this.oFlapTime = this.flapTime;
        Vec3 vec3 = this.getVelocity();
        float g = 0.2F / ((float) vec3.yCoord * 10.0F + 1.0F);
        g *= (float) Math.pow(2.0F, vec3.yCoord);
        if (this.isEntityInsideOpaqueBlock()) {
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
        double e = MathHelper.wrapAngleTo180_double(this.positions[k][0] - d);
        ds[0] = d + e * (double) f;
        d = this.positions[j][1];
        e = this.positions[k][1] - d;
        ds[1] = d + e * (double) f;
        ds[2] = this.positions[j][2] + (this.positions[k][2] - this.positions[j][2]) * (double) f;
        return ds;
    }

    public float getHeadPartYOffset(int i, double[] ds, double[] es) {
        double e;
        if (i == 6) {
            e = 0.0F;
        } else {
            e = es[1] - ds[1];
        }

        return (float) e;
    }
}
