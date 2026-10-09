package com.jeff.pets.client.rendering.vanilla.cow;

import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.render.model.ModelPart;

public class ClientCowModel extends CowEntityModel {

    public ClientCowModel() {
        super();
    }

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

    public ModelPart getHead() {
        return this.head;
    }
}
