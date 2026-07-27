package com.jeff.pets.client.rendering.vanilla.wither;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

import java.util.Arrays;

public class ClientWitherModel<T extends LivingEntity> extends SegmentedModel<T> {
    private final ModelRenderer[] upperBodyParts;
    private final ModelRenderer[] heads;
    private final ImmutableList<ModelRenderer> parts;

    public ClientWitherModel(float f) {
        this.texWidth = 64;
        this.texHeight = 64;
        this.upperBodyParts = new ModelRenderer[3];
        this.upperBodyParts[0] = new ModelRenderer(this, 0, 16);
        this.upperBodyParts[0].addBox(-10.0F, 3.9F, -0.5F, 20.0F, 3.0F, 3.0F, f);
        this.upperBodyParts[1] = (new ModelRenderer(this)).setTexSize(this.texWidth, this.texHeight);
        this.upperBodyParts[1].setPos(-2.0F, 6.9F, -0.5F);
        this.upperBodyParts[1].texOffs(0, 22).addBox(0.0F, 0.0F, 0.0F, 3.0F, 10.0F, 3.0F, f);
        this.upperBodyParts[1].texOffs(24, 22).addBox(-4.0F, 1.5F, 0.5F, 11.0F, 2.0F, 2.0F, f);
        this.upperBodyParts[1].texOffs(24, 22).addBox(-4.0F, 4.0F, 0.5F, 11.0F, 2.0F, 2.0F, f);
        this.upperBodyParts[1].texOffs(24, 22).addBox(-4.0F, 6.5F, 0.5F, 11.0F, 2.0F, 2.0F, f);
        this.upperBodyParts[2] = new ModelRenderer(this, 12, 22);
        this.upperBodyParts[2].addBox(0.0F, 0.0F, 0.0F, 3.0F, 6.0F, 3.0F, f);
        this.heads = new ModelRenderer[3];
        this.heads[0] = new ModelRenderer(this, 0, 0);
        this.heads[0].addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, f);
        this.heads[1] = new ModelRenderer(this, 32, 0);
        this.heads[1].addBox(-4.0F, -4.0F, -4.0F, 6.0F, 6.0F, 6.0F, f);
        this.heads[1].x = -8.0F;
        this.heads[1].y = 4.0F;
        this.heads[2] = new ModelRenderer(this, 32, 0);
        this.heads[2].addBox(-4.0F, -4.0F, -4.0F, 6.0F, 6.0F, 6.0F, f);
        this.heads[2].x = 10.0F;
        this.heads[2].y = 4.0F;
        ImmutableList.Builder<ModelRenderer> builder = ImmutableList.builder();
        builder.addAll(Arrays.asList(this.heads));
        builder.addAll(Arrays.asList(this.upperBodyParts));
        this.parts = builder.build();
    }

    public ImmutableList<ModelRenderer> parts() {
        return this.parts;
    }

    public void setupAnim(T witherBoss, float f, float g, float h, float i, float j) {
        float k = net.minecraft.util.math.MathHelper.cos(h * 0.1F);
        this.upperBodyParts[1].xRot = (0.065F + 0.05F * k) * (float) Math.PI;
        this.upperBodyParts[2].setPos(-2.0F, 6.9F + net.minecraft.util.math.MathHelper.cos(this.upperBodyParts[1].xRot) * 10.0F, -0.5F + net.minecraft.util.math.MathHelper.sin(this.upperBodyParts[1].xRot) * 10.0F);
        this.upperBodyParts[2].xRot = (0.265F + 0.1F * k) * (float) Math.PI;
        this.heads[0].yRot = i * ((float) Math.PI / 180F);
        this.heads[0].xRot = j * ((float) Math.PI / 180F);
    }

    public void prepareMobModel(T witherBoss, float f, float g, float h) {
        for (int i = 1; i < 3; ++i) {
            this.heads[i].yRot = (witherBoss.getYHeadRot() - witherBoss.yBodyRot) * ((float) Math.PI / 180F);
            this.heads[i].xRot = witherBoss.xRot * ((float) Math.PI / 180F);
        }

    }
}
