package com.jeff.pets.client.rendering;

import net.minecraft.client.model.EntityModel;
import net.minecraft.world.entity.LivingEntity;

public abstract class PetModel<T extends LivingEntity> extends EntityModel<T> {


    public PetModel() {
    }

    @Override
    public void setupAnim(T entity, float f, float g, float h, float i, float j) {

    }
}
