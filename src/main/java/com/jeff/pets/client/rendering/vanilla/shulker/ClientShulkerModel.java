package com.jeff.pets.client.rendering.vanilla.shulker;

import com.google.common.collect.ImmutableList;
import com.jeff.pets.mob.vanilla.hostile.ClientShulker;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;

public class ClientShulkerModel extends EntityModel<ClientShulker> {
    private final RendererModel base;
    private final RendererModel lid;
    private final RendererModel head;

    public ClientShulkerModel() {
        this.texHeight = 64;
        this.texWidth = 64;
        this.lid = new RendererModel(this);
        this.base = new RendererModel(this);
        this.head = new RendererModel(this);
        this.lid.texOffs(0, 0).addBox(-8.0F, -16.0F, -8.0F, 16, 12, 16);
        this.lid.setPos(0.0F, 24.0F, 0.0F);
        this.base.texOffs(0, 28).addBox(-8.0F, -8.0F, -8.0F, 16, 8, 16);
        this.base.setPos(0.0F, 24.0F, 0.0F);
        this.head.texOffs(0, 52).addBox(-3.0F, 0.0F, -3.0F, 6, 6, 6);
        this.head.setPos(0.0F, 12.0F, 0.0F);
    }

    @Override
    public void setupAnim(ClientShulker shulker, float f, float g, float h, float i, float j, float u) {
        float k = h - (float) shulker.tickCount;
        float l = (0.5F + 180 * (float) Math.PI);
        float m = -1.0F + net.minecraft.util.math.MathHelper.sin(l);
        float n = 0.0F;
        if (l > (float) Math.PI) {
            n = net.minecraft.util.math.MathHelper.sin(h * 0.1F) * 0.7F;
        }

        this.lid.setPos(0.0F, 16.0F + net.minecraft.util.math.MathHelper.sin(l) * 8.0F + n, 0.0F);
        if (1 > 0.3F) {
            this.lid.yRot = m * m * m * m * (float) Math.PI * 0.125F;
        } else {
            this.lid.yRot = 0.0F;
        }

        this.head.xRot = j * ((float) Math.PI / 180F);
        this.head.yRot = i * ((float) Math.PI / 180F);
    }

    public Iterable<RendererModel> parts() {
        return ImmutableList.of(this.base, this.lid);
    }

    public RendererModel getBase() {
        return this.base;
    }

    public RendererModel getLid() {
        return this.lid;
    }

    public RendererModel getHead() {
        return this.head;
    }

    @Override
    public void render(ClientShulker shulker, float f, float j, float a, float i, float u, float k) {
        super.render(shulker, f, j, a, i, u, k);
        this.base.render(k);
        this.head.render(k);
        this.lid.render(k);
    }
}
