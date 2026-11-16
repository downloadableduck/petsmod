package com.jeff.pets.client.rendering.custom.aprilfools.head;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.compat.render.model.ModelPart;

public class HeadModel extends PetModel {
    private final ModelPart Head;

    public HeadModel() {
        textureWidth = 64;
        textureHeight = 64;

        Head = new ModelPart(this);
        Head.setPos(5.0F, 0.0F, -5.0F);
        Head.setTextureCoords(0, 0).addBox(-8.0F, 16.0F, 0.0F, 8, 8, 8, 0.0F);
        Head.setTextureCoords(32, 0).addBox(-8.0F, 16.0F, 0.0F, 8, 8, 8, 0.5F);
    }

    @Override
    public void render(net.minecraft.entity.Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        Head.render(scale);
    }
}
