package com.jeff.pets.client.rendering.vanilla.chicken;

import com.jeff.pets.mob.vanilla.passive.ClientChicken;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.render.entity.model.ChickenEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientChickenModel extends ChickenEntityModel {

    public ClientChickenModel() {
        super();
    }

    public void setAngles(float f, float g, float h, float i, float j, float s, Entity entity) {
        ClientChicken chicken = (ClientChicken) entity;
        h = getBob(chicken, f);
        super.setAngles(f, g, h, i, j, s, entity);
    }

    protected float getBob(ClientChicken chicken, float f) {
        float g = (float) MathHelper.clamp(chicken.oFlap, chicken.flap, f);
        float h = (float) MathHelper.clamp(chicken.oFlapSpeed, chicken.flapSpeed, f);
        return (MathHelper.sin(g) + 1.0F) * h;
    }

    public void render(Entity entity, float f, float g, float h, float j, float k, float d) {
        super.render(entity, f, g, h, j, k, d);
        GL11.glPushMatrix();
        if (CONFIG.isBaby) {
            GL11.glScalef(2, 2, 2);
        } else {
            GL11.glScalef(1, 1, 1);
        }
        GL11.glPopMatrix();
    }
}