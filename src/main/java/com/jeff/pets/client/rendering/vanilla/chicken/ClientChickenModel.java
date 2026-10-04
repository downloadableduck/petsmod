package com.jeff.pets.client.rendering.vanilla.chicken;

import com.jeff.pets.client.Math2;
import net.minecraft.entity.Entity;

import com.jeff.pets.mob.vanilla.passive.ClientChicken;
import net.minecraft.client.model.ModelChicken;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientChickenModel extends ModelChicken {

    public ClientChickenModel() {
        super();
    }

    public void setRotationAngles(ClientChicken state, float f, float g, float h, float i, float j, float s) {
        h = getBob(state, f);
        super.setRotationAngles(f, g, h, i, j, s, state);
    }

    protected float getBob(ClientChicken chicken, float f) {
        float g = (float) Math2.clampedLerp(f, chicken.oFlap, chicken.flap);
        float h = (float) Math2.clampedLerp(f, chicken.oFlapSpeed, chicken.flapSpeed);
        return (net.minecraft.util.MathHelper.sin(g) + 1.0F) * h;
    }

    @Override
    public void render(net.minecraft.entity.Entity t, float i, float j, float f, float g, float h, float k) {
        super.render(t, i, j, f, g, h, k);
        GL11.glPushMatrix();
        if (CONFIG.isBaby) {
            GL11.glScalef(2, 2, 2);
        } else {
            GL11.glScalef(1, 1, 1);
        }
        GL11.glPopMatrix();
    }
}
