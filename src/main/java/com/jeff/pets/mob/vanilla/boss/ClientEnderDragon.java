package com.jeff.pets.mob.vanilla.boss;

import com.jeff.pets.CanFly;
import com.jeff.pets.client.Math2;
import com.jeff.pets.mob.FlyingPet;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

@CanFly
public class ClientEnderDragon extends FlyingPet {
    public final double[][] positions = new double[64][3];
    public float oFlapTime;
    public float flapTime;
    public int posPointer = -1;

    public ClientEnderDragon(net.minecraft.world.World level) {
        super(level);
        this.setSize(16f, 8f);
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
    protected String func_70639_aQ() {
        return "mob.enderdragon.wings";
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
        double e = net.minecraft.util.MathHelper.wrapDegrees(this.positions[k][0] - d);
        ds[0] = d + e * (double) f;
        d = this.positions[j][1];
        e = this.positions[k][1] - d;
        ds[1] = d + e * (double) f;
        ds[2] = Math2.lerp(f, this.positions[j][2], this.positions[k][2]);
        return ds;
    }

    public float getHeadPartYOffset(int i, double[] ds, double[] es) {
        if (i == 6) {
            return 0.0F;
        } else {
            return (float) (ds[1] - es[1]);
        }
    }

    public Vec3 getHeadLookVec(float p_184665_1_) {
        return this.getLook(p_184665_1_);
    }
}
