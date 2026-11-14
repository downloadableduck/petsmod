package com.jeff.pets.client.rendering;

import net.minecraft.client.render.model.Model; import com.jeff.pets.client.rendering.PetModel;
import net.minecraft.entity.Entity;

public abstract class PetModel extends Model {

    public PetModel() {
    }

    public void setupAnimation(float f, float g, float h, float i, float j, float s, net.minecraft.entity.Entity entity) {

    }

    @Override
    public void render(Entity entity, float walkAnimationProgress, float walkAnimationSpeed, float bob, float yaw, float pitch, float scale) {
        this.setupAnimation(walkAnimationProgress, walkAnimationProgress, bob, yaw, pitch, scale, entity);
    }
}
