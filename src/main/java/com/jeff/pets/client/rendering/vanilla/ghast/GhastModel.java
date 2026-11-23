package com.jeff.pets.client.rendering.vanilla.ghast;

import com.jeff.pets.compat.render.model.Model;
import com.jeff.pets.compat.render.model.ModelPart;
import net.minecraft.core.util.helper.MathHelper;

import java.util.Random;

public class GhastModel extends Model {
    ModelPart body;
    ModelPart[] tentacles = new ModelPart[9];

    public GhastModel() {
        int i = -16;
        this.body = new ModelPart(this, 0, 0);
        this.body.addBox(-8.0F, -8.0F, -8.0F, 16, 16, 16);
        ModelPart var10000 = this.body;
        var10000.y += (float) (24 + i);
        Random random = new Random(1660L);

        for (int j = 0; j < this.tentacles.length; ++j) {
            this.tentacles[j] = new ModelPart(this, 0, 0);
            float f = (((float) (j % 3) - (float) (j / 3 % 2) * 0.5F + 0.25F) / 2.0F * 2.0F - 1.0F) * 5.0F;
            float g = ((float) (j / 3) / 2.0F * 2.0F - 1.0F) * 5.0F;
            int k = random.nextInt(7) + 8;
            this.tentacles[j].addBox(-1.0F, 0.0F, -1.0F, 2, k, 2);
            this.tentacles[j].x = f;
            this.tentacles[j].z = g;
            this.tentacles[j].y = (float) (31 + i);
        }

    }

    public void setupAnimation(float walkAnimationProgress, float walkAnimationSpeed, float bob, float yaw, float pitch, float scale) {
        for (int i = 0; i < this.tentacles.length; ++i) {
            this.tentacles[i].rotationX = 0.2F * MathHelper.sin(bob * 0.3F + (float) i) + 0.4F;
        }

    }

    public void render(float walkAnimationProgress, float walkAnimationSpeed, float bob, float yaw, float pitch, float scale) {
        this.setupAnimation(walkAnimationProgress, walkAnimationSpeed, bob, yaw, pitch, scale);
        this.body.render(scale);

        for (int i = 0; i < this.tentacles.length; ++i) {
            this.tentacles[i].render(scale);
        }
    }
}