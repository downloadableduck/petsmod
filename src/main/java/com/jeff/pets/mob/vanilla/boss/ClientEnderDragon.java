//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.mob.vanilla.boss;

import net.minecraft.core.world.World;
import com.jeff.pets.client.Math2;
import com.jeff.pets.client.Utils;
import com.jeff.pets.mob.FlyingPet;

public class ClientEnderDragon extends FlyingPet {
    public final double[][] positions = new double[64][3];
    public float oFlapTime;
    public float flapTime;
    public int posPointer = -1;

    public ClientEnderDragon(World world) {
        super(world);
        this.setBoundingBoxSpacing(16.0F, 8.0F);
        this.texture = "/mob/enderdragon/ender.png";
    }

    protected int stopDistance() {
        return 10;
    }

    protected float heartHeight() {
        return 3.0F;
    }

    protected String getRandomSound() {
        return "mob.enderdragon.wings";
    }

    public void tick() {
        super.tick();
        this.oFlapTime = this.flapTime;
        float g = 0.2F / ((float)this.yd * 10.0F + 1.0F);
        g *= (float)Math.pow(2.0, this.yd);
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
        double e = (double) Utils.wrapDegrees((float)(this.positions[k][0] - d));
        ds[0] = d + e * (double)f;
        d = this.positions[j][1];
        e = this.positions[k][1] - d;
        ds[1] = d + e * (double)f;
        ds[2] = Math2.lerp((double)f, this.positions[j][2], this.positions[k][2]);
        return ds;
    }

    public float getHeadPartYOffset(int i, double[] ds, double[] es) {
        return i == 6 ? 0.0F : (float)(ds[1] - es[1]);
    }
}
