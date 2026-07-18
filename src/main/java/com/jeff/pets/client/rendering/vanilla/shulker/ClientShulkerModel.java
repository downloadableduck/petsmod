package com.jeff.pets.client.rendering.vanilla.shulker;

import com.jeff.pets.mob.vanilla.hostile.ClientShulker;
import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.math.MathHelper;

public class ClientShulkerModel extends EntityModel<ClientShulker> {
    private final Cuboid field_3553;
    private final Cuboid field_3555;
    private final Cuboid field_3554;

    public ClientShulkerModel() {
        this.textureHeight = 64;
        this.textureWidth = 64;
        this.field_3555 = new Cuboid(this);
        this.field_3553 = new Cuboid(this);
        this.field_3554 = new Cuboid(this);
        this.field_3555.setTextureOffset(0, 0).addBox(-8.0F, -16.0F, -8.0F, 16, 12, 16);
        this.field_3555.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.field_3553.setTextureOffset(0, 28).addBox(-8.0F, -8.0F, -8.0F, 16, 8, 16);
        this.field_3553.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.field_3554.setTextureOffset(0, 52).addBox(-3.0F, 0.0F, -3.0F, 6, 6, 6);
        this.field_3554.setRotationPoint(0.0F, 12.0F, 0.0F);
    }

    @Override
    public void setAngles(ClientShulker shulkerEntity, float f, float g, float h, float i, float j, float k) {
        float l = h - (float) shulkerEntity.age;
        float m = (0.5F + 180 * (float) Math.PI);
        float n = -1.0F + MathHelper.sin(m);
        float o = 0.0F;
        if (m > (float) Math.PI) {
            o = MathHelper.sin(h * 0.1F) * 0.7F;
        }

        this.field_3555.setRotationPoint(0.0F, 16.0F + MathHelper.sin(m) * 8.0F + o, 0.0F);
        if (180 > 0.3F) {
            this.field_3555.yaw = n * n * n * n * (float) Math.PI * 0.125F;
        } else {
            this.field_3555.yaw = 0.0F;
        }

        this.field_3554.pitch = j * ((float) Math.PI / 180F);
        this.field_3554.yaw = i * ((float) Math.PI / 180F);
    }

    @Override
    public void render(ClientShulker shulkerEntity, float f, float g, float h, float i, float j, float k) {
        this.field_3553.render(k);
        this.field_3554.render(k);
        this.field_3555.render(k);
    }
}
