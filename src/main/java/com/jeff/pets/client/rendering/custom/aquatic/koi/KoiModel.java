package com.jeff.pets.client.rendering.custom.aquatic.koi;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.custom.aquatic.Koi;
import net.minecraft.client.renderer.entity.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class KoiModel extends PetModel {
    private final ModelRenderer body;
    private final ModelRenderer top_fin;
    private final ModelRenderer left_hind_fin;
    private final ModelRenderer left_hind_fin_r1;
    private final ModelRenderer right_hind_fin;
    private final ModelRenderer right_hind_fin_r1;
    private final ModelRenderer left_fin;
    private final ModelRenderer left_fin_r1;
    private final ModelRenderer right_fin;
    private final ModelRenderer right_fin_r1;
    private final ModelRenderer tail_fin;

    public KoiModel() {
        textureWidth = 64;
        textureHeight = 64;

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 20.0F, -7.0F);
        body.setTextureOffset(0, 0).addBox(-2.0F, -4.0F, -2.0F, (int) 4.0F, (int) 4.0F, (int) 14.0F, 0.0F, false);

        top_fin = new ModelRenderer(this);
        top_fin.setRotationPoint(-4.0F, 0.0F, 8.0F);
        body.addChild(top_fin);
        top_fin.setTextureOffset(16, 26).addBox(4.0F, -6.0F, -6.0F, (int) 0.0F, (int) 2.0F, (int) 4.0F, 0.0F, false);

        left_hind_fin = new ModelRenderer(this);
        left_hind_fin.setRotationPoint(4.0F, 0.0F, 2.0F);
        body.addChild(left_hind_fin);


        left_hind_fin_r1 = new ModelRenderer(this);
        left_hind_fin_r1.setRotationPoint(-8.0F, 0.0F, 6.0F);
        left_hind_fin.addChild(left_hind_fin_r1);
        setRotationAngle(left_hind_fin_r1, 0.0F, 0.0F, -0.6981F);
        left_hind_fin_r1.setTextureOffset(24, 26).addBox(0.6F, 0.0F, 0.0F, (int) 2.0F, (int) 0.0F, (int) 2.0F, 0.0F, false);

        right_hind_fin = new ModelRenderer(this);
        right_hind_fin.setRotationPoint(4.0F, 0.0F, 2.0F);
        body.addChild(right_hind_fin);


        right_hind_fin_r1 = new ModelRenderer(this);
        right_hind_fin_r1.setRotationPoint(-2.0F, -2.0F, 6.0F);
        right_hind_fin.addChild(right_hind_fin_r1);
        setRotationAngle(right_hind_fin_r1, 0.0F, 0.0F, 0.829F);
        right_hind_fin_r1.setTextureOffset(24, 28).addBox(0.0F, 0.0F, 0.0F, (int) 2.0F, (int) 0.0F, (int) 2.0F, 0.0F, false);

        left_fin = new ModelRenderer(this);
        left_fin.setRotationPoint(0.0F, 0.0F, 0.0F);
        body.addChild(left_fin);


        left_fin_r1 = new ModelRenderer(this);
        left_fin_r1.setRotationPoint(-4.0F, 0.0F, 2.0F);
        left_fin.addChild(left_fin_r1);
        setRotationAngle(left_fin_r1, 0.0F, 0.0F, -0.6981F);
        left_fin_r1.setTextureOffset(16, 22).addBox(-1.4F, 0.0F, -2.0F, (int) 4.0F, (int) 0.0F, (int) 4.0F, 0.0F, false);

        right_fin = new ModelRenderer(this);
        right_fin.setRotationPoint(-4.0F, 0.0F, 2.0F);
        body.addChild(right_fin);


        right_fin_r1 = new ModelRenderer(this);
        right_fin_r1.setRotationPoint(8.0F, 0.0F, 0.0F);
        right_fin.addChild(right_fin_r1);
        setRotationAngle(right_fin_r1, 0.0F, 0.0F, 0.6109F);
        right_fin_r1.setTextureOffset(16, 18).addBox(-2.6F, 0.0F, -2.0F, (int) 4.0F, (int) 0.0F, (int) 4.0F, 0.0F, false);

        tail_fin = new ModelRenderer(this);
        tail_fin.setRotationPoint(0.0F, 0.0F, 14.0F);
        body.addChild(tail_fin);
        tail_fin.setTextureOffset(0, 18).addBox(0.0F, -8.0F, -2.0F, (int) 0.0F, (int) 12.0F, (int) 8.0F, 0.0F, false);
    }

    @Override
    public void render(net.minecraft.entity.Entity entity, float packedLight, float packedOverlay, float red, float green, float blue, float alpha) {
        body.render(alpha);
    }

    public void setRotationAngle(ModelRenderer ModelRenderer, float x, float y, float z) {
        ModelRenderer.rotateAngleX = x;
        ModelRenderer.rotateAngleY = y;
        ModelRenderer.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float g, float ageInTicks, float m, float k, float u, Entity entity) {
        Koi state = (Koi) entity;
        this.body.rotateAngleY = -1.0f * 0.25F * net.minecraft.util.math.MathHelper.sin(1.0f * 0.6F * ageInTicks);
        this.tail_fin.rotateAngleY = -this.body.rotateAngleY * 1.75f;
    }
}
