package com.jeff.pets.client.rendering;

import net.minecraft.client.render.model.Model;
import net.minecraft.entity.Entity;

/**
 * 1.1 render bridge for pet models.
 * <p> 1.1's MobRenderer calls prepare(mob, limbSwing, limbSwingAmount, tickDelta) and then
 * render(entity, limbSwing, limbSwingAmount, age/bob, headYaw, headPitch, scale). It does not
 * call setupAnimation, so we drive animation from render.
 */
public abstract class PetModel extends Model {

    public PetModel() {
    }

    // 1.1 Model.setupAnimation signature (6 floats)
    @Override
    public void setupAnimation(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    }

    // Convenience overload used by many pet models in this codebase
    public void setupAnimation(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity) {
        this.setupAnimation(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setupAnimation(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
    }
}
