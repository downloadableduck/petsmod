//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.custom.aprilfools.head;

import net.minecraft.core.entity.Entity;
import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.compat.render.model.ModelPart;

public class HeadModel extends PetModel {
    private final ModelPart Head;

    public HeadModel() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Head = new ModelPart(this);
        this.Head.setPos(5.0F, 0.0F, -5.0F);
        this.Head.setTextureCoords(0, 0).addBox(-8.0F, 16.0F, 0.0F, 8, 8, 8, 0.0F);
        this.Head.setTextureCoords(32, 0).addBox(-8.0F, 16.0F, 0.0F, 8, 8, 8, 0.5F);
    }

    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.Head.render(scale);
    }
}
