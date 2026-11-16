package com.jeff.pets.client.rendering;

import com.jeff.pets.compat.render.model.Model;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;

/**
 * b1.7.3 render bridge for pet models.
 */
public abstract class PetModel extends Model {

    @Override
    public void render(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        Entity e = this.currentEntity;
        this.setupAnimation(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, e);
        this.renderModel(e, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }

    protected void renderModel(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }

    // legacy/entity-aware hook kept for compatibility
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        // default no-op
    }

    @Override
    public void setupAnimation(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    }

    public void setupAnimation(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity) {
        this.setupAnimation(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }

    @Override
    public void prepare(MobEntity entity, float limbSwing, float limbSwingAmount, float tickDelta) {
        super.prepare(entity, limbSwing, limbSwingAmount, tickDelta);
    }
}
