package com.jeff.pets.client.rendering.vanilla.pig;

import net.minecraft.client.render.entity.model.PigEntityModel;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientPigModel extends PigEntityModel {

    public ClientPigModel() {
        super();
    }

    public void setAngles(float f, float g, float h, float i, float k, float s, Entity entity) {
        super.setAngles(f, g, h, i, k, s, entity);
    }

    public void render(Entity pig, float i, float j, float f, float g, float h, float k) {
        super.render(pig, i, j, f, g, h, k);
        GL11.glPushMatrix();
        if (CONFIG.isBaby) {
            GL11.glScalef(1.5f, 1.5f, 1.5f);
        } else {
            GL11.glScalef(1, 1, 1);
        }
        GL11.glPopMatrix();
    }
}