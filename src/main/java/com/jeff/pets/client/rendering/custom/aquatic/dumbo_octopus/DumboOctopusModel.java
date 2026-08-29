package com.jeff.pets.client.rendering.custom.aquatic.dumbo_octopus;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.custom.aquatic.DumboOctopus;
import net.minecraft.client.renderer.entity.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class DumboOctopusModel extends PetModel {
    private final ModelRenderer body;
    private final ModelRenderer left_ear;
    private final ModelRenderer left_ear_r1;
    private final ModelRenderer right_ear;
    private final ModelRenderer right_ear_r1;
    private final ModelRenderer leg1;
    private final ModelRenderer leg2;
    private final ModelRenderer leg3;
    private final ModelRenderer leg4;
    private final ModelRenderer leg5;
    private final ModelRenderer leg6;
    private final ModelRenderer leg7;
    private final ModelRenderer leg8;

    public DumboOctopusModel() {
        textureWidth = 32;
        textureHeight = 32;

        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 22.0F, 0.0F);
        setRotationAngle(body, 0.0F, -1.5708F, 0.0F);
        body.setTextureOffset(0, 0).addBox(-4.0F, -5.0F, -3.0F, (int) 6.0F, (int) 6.0F, (int) 6.0F, 0.0F, false);

        left_ear = new ModelRenderer(this);
        left_ear.setRotationPoint(-1.0F, 2.0F, 2.0F);
        body.addChild(left_ear);


        left_ear_r1 = new ModelRenderer(this);
        left_ear_r1.setRotationPoint(-1.0F, -7.0F, 1.0F);
        left_ear.addChild(left_ear_r1);
        setRotationAngle(left_ear_r1, -0.5236F, 0.0F, 0.0F);
        left_ear_r1.setTextureOffset(8, 12).addBox(-1.0F, -2.0F, -0.5F, (int) 2.0F, (int) 2.0F, (int) 1.0F, 0.0F, false);

        right_ear = new ModelRenderer(this);
        right_ear.setRotationPoint(-2.0F, -5.0F, 3.0F);
        body.addChild(right_ear);


        right_ear_r1 = new ModelRenderer(this);
        right_ear_r1.setRotationPoint(0.0F, 0.0F, -6.0F);
        right_ear.addChild(right_ear_r1);
        setRotationAngle(right_ear_r1, 0.5236F, 0.0F, 0.0F);
        right_ear_r1.setTextureOffset(8, 12).addBox(-1.0F, -2.0F, -0.5F, (int) 2.0F, (int) 2.0F, (int) 1.0F, 0.0F, false);

        leg1 = new ModelRenderer(this);
        leg1.setRotationPoint(-4.0F, 1.0F, 2.0F);
        body.addChild(leg1);
        leg1.setTextureOffset(0, 12).addBox(-2.0F, -1.0F, -1.0F, (int) 2.0F, (int) 2.0F, (int) 2.0F, 0.0F, false);

        leg2 = new ModelRenderer(this);
        leg2.setRotationPoint(-4.0F, 1.0F, -1.0F);
        body.addChild(leg2);
        leg2.setTextureOffset(0, 12).addBox(-2.0F, -1.0F, -1.0F, (int) 2.0F, (int) 2.0F, (int) 2.0F, 0.0F, false);

        leg3 = new ModelRenderer(this);
        leg3.setRotationPoint(-3.0F, 1.0F, -3.0F);
        body.addChild(leg3);
        leg3.setTextureOffset(0, 12).addBox(-1.0F, -1.0F, -2.0F, (int) 2.0F, (int) 2.0F, (int) 2.0F, 0.0F, false);

        leg4 = new ModelRenderer(this);
        leg4.setRotationPoint(0.0F, 1.0F, -3.0F);
        body.addChild(leg4);
        leg4.setTextureOffset(0, 12).addBox(-1.0F, -1.0F, -2.0F, (int) 2.0F, (int) 2.0F, (int) 2.0F, 0.0F, false);

        leg5 = new ModelRenderer(this);
        leg5.setRotationPoint(2.0F, 1.0F, -2.0F);
        body.addChild(leg5);
        leg5.setTextureOffset(0, 12).addBox(0.0F, -1.0F, -1.0F, (int) 2.0F, (int) 2.0F, (int) 2.0F, 0.0F, false);

        leg6 = new ModelRenderer(this);
        leg6.setRotationPoint(2.0F, 1.0F, 1.0F);
        body.addChild(leg6);
        leg6.setTextureOffset(0, 12).addBox(0.0F, -1.0F, -1.0F, (int) 2.0F, (int) 2.0F, (int) 2.0F, 0.0F, false);

        leg7 = new ModelRenderer(this);
        leg7.setRotationPoint(1.0F, 1.0F, 3.0F);
        body.addChild(leg7);
        leg7.setTextureOffset(0, 12).addBox(-1.0F, -1.0F, 0.0F, (int) 2.0F, (int) 2.0F, (int) 2.0F, 0.0F, false);

        leg8 = new ModelRenderer(this);
        leg8.setRotationPoint(-2.0F, 1.0F, 3.0F);
        body.addChild(leg8);
        leg8.setTextureOffset(0, 12).addBox(-1.0F, -1.0F, 0.0F, (int) 2.0F, (int) 2.0F, (int) 2.0F, 0.0F, false);
    }

    @Override
    public void render(net.minecraft.entity.Entity entity, float f, float g, float red, float green, float blue, float alpha) {
        body.render(alpha);
    }

    public void setRotationAngle(ModelRenderer ModelRenderer, float x, float y, float z) {
        ModelRenderer.rotateAngleX = x;
        ModelRenderer.rotateAngleY = y;
        ModelRenderer.rotateAngleZ = z;
    }


    @Override
    public void setRotationAngles(float f, float g, float h, float i, float k, float u, Entity entity) {
        super.setRotationAngles(f, g, h, i, k, u, entity);
        DumboOctopus state = (DumboOctopus) entity;
        if (state.limbSwingAmount > 0) {
            leg1.rotateAngleZ = -state.tentacleAngle / 10;
            float rot = leg1.rotateAngleZ;
            leg2.rotateAngleZ = rot;
            leg3.rotateAngleX = -rot;
            leg4.rotateAngleX = -rot;
            leg5.rotateAngleZ = -rot;
            leg6.rotateAngleZ = -rot;
            leg7.rotateAngleX = rot;
            leg8.rotateAngleX = rot;
        }
    }
}
