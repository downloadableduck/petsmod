package com.jeff.pets.client.rendering;

import net.minecraft.client.render.model.Model;
import net.minecraft.entity.living.LivingEntity;

public abstract class PetModel<T extends LivingEntity> extends Model<T> {


    public PetModel() {
    }

    @Override
    public void setup(T entity, float f, float g, float h, float i, float j, float s) {

    }
}
