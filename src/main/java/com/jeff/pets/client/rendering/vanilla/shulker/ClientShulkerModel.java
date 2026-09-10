package com.jeff.pets.client.rendering.vanilla.shulker;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.entity.Entity;

public class ClientShulkerModel extends EntityModel {
    private final ModelPart base;
    private final ModelPart lid;
    private final ModelPart head;

    public ClientShulkerModel() {
        this.textureHeight = 64;
        this.textureWidth = 64;
        this.lid = new ModelPart(this);
        this.base = new ModelPart(this);
        this.head = new ModelPart(this);
        this.lid.setTextureOffset(0, 0).addCuboid(-8.0F, -16.0F, -8.0F, 16, 12, 16);
        this.lid.setPivot(0.0F, 24.0F, 0.0F);
        this.base.setTextureOffset(0, 28).addCuboid(-8.0F, -8.0F, -8.0F, 16, 8, 16);
        this.base.setPivot(0.0F, 24.0F, 0.0F);
        this.head.setTextureOffset(0, 52).addCuboid(-3.0F, 0.0F, -3.0F, 6, 6, 6);
        this.head.setPivot(0.0F, 12.0F, 0.0F);
    }

    @Override
    public void setAngles(float f, float g, float h, float i, float j, float u, Entity entity) {
        float k = h - (float) entity.ticksAlive;
        float l = (0.5F + 180 * (float) Math.PI);
        float m = -1.0F + net.minecraft.util.math.MathHelper.sin(l);
        float n = 0.0F;
        if (l > (float) Math.PI) {
            n = net.minecraft.util.math.MathHelper.sin(h * 0.1F) * 0.7F;
        }

        this.lid.setPivot(0.0F, 16.0F + net.minecraft.util.math.MathHelper.sin(l) * 8.0F + n, 0.0F);
        if (1 > 0.3F) {
            this.lid.posY = m * m * m * m * (float) Math.PI * 0.125F;
        } else {
            this.lid.posY = 0.0F;
        }

        this.head.posX = j * ((float) Math.PI / 180F);
        this.head.posY = i * ((float) Math.PI / 180F);
    }

    public Iterable<ModelPart> parts() {
        return ImmutableList.of(this.base, this.lid);
    }

    public ModelPart getBase() {
        return this.base;
    }

    public ModelPart getLid() {
        return this.lid;
    }

    public ModelPart getHead() {
        return this.head;
    }

    @Override
    public void render(Entity shulker, float f, float j, float a, float i, float u, float k) {
        super.render(shulker, f, j, a, i, u, k);
        this.base.render(k);
        this.head.render(k);
        this.lid.render(k);
    }
}
