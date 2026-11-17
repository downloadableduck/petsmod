//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.shulker;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.compat.render.model.ModelPart;
import com.jeff.pets.mob.vanilla.hostile.ClientShulker;

public class ClientShulkerModel extends PetModel {
    private final ModelPart field_3553;
    private final ModelPart field_3555;
    private final ModelPart field_3554;

    public ClientShulkerModel() {
        this.textureHeight = 64;
        this.textureWidth = 64;
        this.field_3555 = new ModelPart(this);
        this.field_3553 = new ModelPart(this);
        this.field_3554 = new ModelPart(this);
        this.field_3555.setTextureCoords(0, 0).addBox(-8.0F, -16.0F, -8.0F, 16, 12, 16);
        this.field_3555.setPos(0.0F, 24.0F, 0.0F);
        this.field_3553.setTextureCoords(0, 28).addBox(-8.0F, -8.0F, -8.0F, 16, 8, 16);
        this.field_3553.setPos(0.0F, 24.0F, 0.0F);
        this.field_3554.setTextureCoords(0, 52).addBox(-3.0F, 0.0F, -3.0F, 6, 6, 6);
        this.field_3554.setPos(0.0F, 12.0F, 0.0F);
    }

    public void setupAnimation(float f, float g, float h, float i, float j, float k, Entity entity) {
        ClientShulker shulkerEntity = (ClientShulker)entity;
        float var10000 = h - (float)shulkerEntity.age;
        float m = 565.9867F;
        float n = -1.0F + MathHelper.sin(m);
        float o = 0.0F;
        if (m > 3.1415927F) {
            o = MathHelper.sin(h * 0.1F) * 0.7F;
        }

        this.field_3555.setPos(0.0F, 16.0F + MathHelper.sin(m) * 8.0F + o, 0.0F);
        this.field_3555.rotationY = n * n * n * n * 3.1415927F * 0.125F;
        this.field_3554.rotationX = j * 0.017453292F;
        this.field_3554.rotationY = i * 0.017453292F;
    }

    public void render(Entity entity, float f, float g, float h, float i, float j, float k) {
        this.field_3553.render(k);
        this.field_3554.render(k);
        this.field_3555.render(k);
    }
}
