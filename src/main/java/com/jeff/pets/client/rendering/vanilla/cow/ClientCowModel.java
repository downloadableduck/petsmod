package com.jeff.pets.client.rendering.vanilla.cow;

import net.minecraft.client.model.ModelCow;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCowModel extends ModelCow {

    public ClientCowModel() {
        super();
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
