package com.jeff.pets.client.rendering.vanilla.pig;

import net.minecraft.entity.Entity;

import com.jeff.pets.mob.vanilla.passive.ClientPig;
import net.minecraft.client.model.ModelPig;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientPigModel extends ModelPig {

    public ClientPigModel() {
        super();
    }

    public void setRotationAngles(ClientPig state, float f, float g, float h, float i, float k, float j) {
        super.setRotationAngles(f, g, h, i, k, j, state);
    }

    @Override
    public void render(net.minecraft.entity.Entity entity, float i, float j, float f, float g, float h, float k) {
        super.render(entity, i, j, f, g, h, k);
        GL11.glPushMatrix();
        if (CONFIG.isBaby) {
            GL11.glScalef(1.5f, 1.5f, 1.5f);
        } else {
            GL11.glScalef(1, 1, 1);
        }
        GL11.glPopMatrix();
    }
}
