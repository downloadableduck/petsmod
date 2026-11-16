package com.jeff.pets.client.rendering;

import com.jeff.pets.compat.render.model.Model;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;

public abstract class PetModel extends Model {

    public PetModel() {
    }

    @Override
    public void setupAnimation(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
    }

    public void setupAnimation(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity) {
        this.setupAnimation(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setupAnimation(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
    }

    @Override
    public void prepare(MobEntity entity, float limbSwing, float limbSwingAmount, float tickDelta) {
    }
}
