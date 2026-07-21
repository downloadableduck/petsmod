package com.jeff.pets.client.rendering.vanilla.shulker;

import com.jeff.pets.mob.vanilla.hostile.ClientShulker;
import net.minecraft.client.render.model.Model;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.util.math.MathHelper;

public class ClientShulkerModel extends Model<ClientShulker> {
    private final ModelPart field_3553;
    private final ModelPart field_3555;
    private final ModelPart field_3554;

    public ClientShulkerModel() {
        this.f_9233444 /*textureHeight*/ = 64;
        this.f_9972380 /*textureWidth*/ = 64;
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

    @Override
    public void setup(ClientShulker shulkerEntity, float f, float g, float h, float i, float j, float k) {
        float l = h - (float) shulkerEntity.ticks;
        float m = (0.5F + 180 * (float) Math.PI);
        float n = -1.0F + MathHelper.sin(m);
        float o = 0.0F;
        if (m > (float) Math.PI) {
            o = MathHelper.sin(h * 0.1F) * 0.7F;
        }

        this.field_3555.setPos(0.0F, 16.0F + MathHelper.sin(m) * 8.0F + o, 0.0F);
        if (180 > 0.3F) {
            this.field_3555.rotationY = n * n * n * n * (float) Math.PI * 0.125F;
        } else {
            this.field_3555.rotationY = 0.0F;
        }

        this.field_3554.rotationX = j * ((float) Math.PI / 180F);
        this.field_3554.rotationY = i * ((float) Math.PI / 180F);
    }

    @Override
    public void render(ClientShulker shulkerEntity, float f, float g, float h, float i, float j, float k) {
        this.field_3553.render(k);
        this.field_3554.render(k);
        this.field_3555.render(k);
    }
}
