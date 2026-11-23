package com.jeff.pets.client.rendering.vanilla.squid;

import com.jeff.pets.compat.render.model.Model;
import com.jeff.pets.compat.render.model.ModelPart;

public class SquidModel extends Model {
    ModelPart body;
    ModelPart[] tentacles = new ModelPart[8];

    public SquidModel() {
        int i = -16;
        this.body = new ModelPart(this, 0, 0);
        this.body.addBox(-6.0F, -8.0F, -6.0F, 12, 16, 12);
        ModelPart var10000 = this.body;
        var10000.y += (float)(24 + i);

        for(int j = 0; j < this.tentacles.length; ++j) {
            this.tentacles[j] = new ModelPart(this, 48, 0);
            double d = (double)j * Math.PI * 2.0 / (double)this.tentacles.length;
            float f = (float)Math.cos(d) * 5.0F;
            float g = (float)Math.sin(d) * 5.0F;
            this.tentacles[j].addBox(-1.0F, 0.0F, -1.0F, 2, 18, 2);
            this.tentacles[j].x = f;
            this.tentacles[j].z = g;
            this.tentacles[j].y = (float)(31 + i);
            d = (double)j * Math.PI * -2.0 / (double)this.tentacles.length + 1.5707963267948966;
            this.tentacles[j].rotationY = (float)d;
        }

    }

    public void setupAnimation(float walkAnimationProgress, float walkAnimationSpeed, float bob, float yaw, float pitch, float scale) {
        for(int i = 0; i < this.tentacles.length; ++i) {
            this.tentacles[i].rotationX = bob;
        }

    }

    public void render(float walkAnimationProgress, float walkAnimationSpeed, float bob, float yaw, float pitch, float scale) {
        this.setupAnimation(walkAnimationProgress, walkAnimationSpeed, bob, yaw, pitch, scale);
        this.body.render(scale);

        for(int i = 0; i < this.tentacles.length; ++i) {
            this.tentacles[i].render(scale);
        }

    }
}