package com.jeff.pets.client.rendering.vanilla.slime;

import com.jeff.pets.compat.render.model.Model;
import com.jeff.pets.compat.render.model.ModelPart;

public class SlimeModel extends Model {
    ModelPart body;
    ModelPart rightEye;
    ModelPart leftEye;
    ModelPart mouth;

    public SlimeModel(int v) {
        this.body = new ModelPart(this, 0, v);
        this.body.addBox(-4.0F, 16.0F, -4.0F, 8, 8, 8);
        if (v > 0) {
            this.body = new ModelPart(this, 0, v);
            this.body.addBox(-3.0F, 17.0F, -3.0F, 6, 6, 6);
            this.rightEye = new ModelPart(this, 32, 0);
            this.rightEye.addBox(-3.25F, 18.0F, -3.5F, 2, 2, 2);
            this.leftEye = new ModelPart(this, 32, 4);
            this.leftEye.addBox(1.25F, 18.0F, -3.5F, 2, 2, 2);
            this.mouth = new ModelPart(this, 32, 8);
            this.mouth.addBox(0.0F, 21.0F, -3.5F, 1, 1, 1);
        }

    }

    public void setupAnimation(float walkAnimationProgress, float walkAnimationSpeed, float bob, float yaw, float pitch, float scale) {
    }

    public void render(float walkAnimationProgress, float walkAnimationSpeed, float bob, float yaw, float pitch, float scale) {
        this.setupAnimation(walkAnimationProgress, walkAnimationSpeed, bob, yaw, pitch, scale);
        this.body.render(scale);
        if (this.rightEye != null) {
            this.rightEye.render(scale);
            this.leftEye.render(scale);
            this.mouth.render(scale);
        }

    }
}