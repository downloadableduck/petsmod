package com.jeff.pets.client.rendering.vanilla.shulker;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ClientShulkerModel extends ModelBase {
    private final ModelRenderer base;
    private final ModelRenderer lid;
    private final ModelRenderer head;

    public ClientShulkerModel() {
        this.textureHeight = 64;
        this.textureWidth = 64;
        this.lid = new ModelRenderer(this);
        this.base = new ModelRenderer(this);
        this.head = new ModelRenderer(this);
        this.lid.setTextureOffset(0, 0).addBox(-8.0F, -16.0F, -8.0F, 16, 12, 16);
        this.lid.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.base.setTextureOffset(0, 28).addBox(-8.0F, -8.0F, -8.0F, 16, 8, 16);
        this.base.setRotationPoint(0.0F, 24.0F, 0.0F);
        this.head.setTextureOffset(0, 52).addBox(-3.0F, 0.0F, -3.0F, 6, 6, 6);
        this.head.setRotationPoint(0.0F, 12.0F, 0.0F);
    }

    @Override
    public void setRotationAngles(float f, float g, float h, float i, float j, float u, Entity entity) {
        float k = h - (float) entity.ticksExisted;
        float l = (0.5F + 180 * (float) Math.PI);
        float m = -1.0F + net.minecraft.util.math.MathHelper.sin(l);
        float n = 0.0F;
        if (l > (float) Math.PI) {
            n = net.minecraft.util.math.MathHelper.sin(h * 0.1F) * 0.7F;
        }

        this.lid.setRotationPoint(0.0F, 16.0F + net.minecraft.util.math.MathHelper.sin(l) * 8.0F + n, 0.0F);
        if (1 > 0.3F) {
            this.lid.rotateAngleY = m * m * m * m * (float) Math.PI * 0.125F;
        } else {
            this.lid.rotateAngleY = 0.0F;
        }

        this.head.rotateAngleX = j * ((float) Math.PI / 180F);
        this.head.rotateAngleY = i * ((float) Math.PI / 180F);
    }

    public Iterable<ModelRenderer> parts() {
        return ImmutableList.of(this.base, this.lid);
    }

    public ModelRenderer getBase() {
        return this.base;
    }

    public ModelRenderer getLid() {
        return this.lid;
    }

    public ModelRenderer getHead() {
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
