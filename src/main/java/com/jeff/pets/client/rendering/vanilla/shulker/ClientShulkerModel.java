package com.jeff.pets.client.rendering.vanilla.shulker;

import com.google.common.collect.ImmutableList;
import com.jeff.pets.mob.vanilla.hostile.ClientShulker;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ClientShulkerModel extends SegmentedModel<ClientShulker> {
    private final ModelRenderer base = new ModelRenderer(64, 64, 0, 28);
    private final ModelRenderer lid = new ModelRenderer(64, 64, 0, 0);
    private final ModelRenderer head = new ModelRenderer(64, 64, 0, 52);

    public ClientShulkerModel() {
        super(RenderType::entityCutoutNoCullZOffset);
        this.lid.addBox(-8.0F, -16.0F, -8.0F, 16.0F, 12.0F, 16.0F);
        this.lid.setPos(0.0F, 24.0F, 0.0F);
        this.base.addBox(-8.0F, -8.0F, -8.0F, 16.0F, 8.0F, 16.0F);
        this.base.setPos(0.0F, 24.0F, 0.0F);
        this.head.addBox(-3.0F, 0.0F, -3.0F, 6.0F, 6.0F, 6.0F);
        this.head.setPos(0.0F, 12.0F, 0.0F);
    }

    public void setupAnim(ClientShulker shulker, float f, float g, float h, float i, float j) {
        float k = h - (float) shulker.tickCount;
        float l = (0.5F + 1) * (float) Math.PI;
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
        this.head.yRot = (shulker.yHeadRot - 180.0F - shulker.yBodyRot) * ((float) Math.PI / 180F);
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
}
