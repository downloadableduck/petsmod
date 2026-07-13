package com.jeff.pets.client.rendering.custom.aquatic.dumbo_octopus;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.custom.aquatic.DumboOctopus;
import net.minecraft.client.renderer.model.ModelRenderer;

public class DumboOctopusModel extends PetModel<DumboOctopus> {
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
        texWidth = 32;
        texHeight = 32;

        body = new ModelRenderer(this);
        body.setPos(0.0F, 22.0F, 0.0F);
        setRotationAngle(body, 0.0F, -1.5708F, 0.0F);
        body.texOffs(0, 0).addBox(-4.0F, -5.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);

        left_ear = new ModelRenderer(this);
        left_ear.setPos(-1.0F, 2.0F, 2.0F);
        body.addChild(left_ear);


        left_ear_r1 = new ModelRenderer(this);
        left_ear_r1.setPos(-1.0F, -7.0F, 1.0F);
        left_ear.addChild(left_ear_r1);
        setRotationAngle(left_ear_r1, -0.5236F, 0.0F, 0.0F);
        left_ear_r1.texOffs(8, 12).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);

        right_ear = new ModelRenderer(this);
        right_ear.setPos(-2.0F, -5.0F, 3.0F);
        body.addChild(right_ear);


        right_ear_r1 = new ModelRenderer(this);
        right_ear_r1.setPos(0.0F, 0.0F, -6.0F);
        right_ear.addChild(right_ear_r1);
        setRotationAngle(right_ear_r1, 0.5236F, 0.0F, 0.0F);
        right_ear_r1.texOffs(8, 12).addBox(-1.0F, -2.0F, -0.5F, 2.0F, 2.0F, 1.0F, 0.0F, false);

        leg1 = new ModelRenderer(this);
        leg1.setPos(-4.0F, 1.0F, 2.0F);
        body.addChild(leg1);
        leg1.texOffs(0, 12).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        leg2 = new ModelRenderer(this);
        leg2.setPos(-4.0F, 1.0F, -1.0F);
        body.addChild(leg2);
        leg2.texOffs(0, 12).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        leg3 = new ModelRenderer(this);
        leg3.setPos(-3.0F, 1.0F, -3.0F);
        body.addChild(leg3);
        leg3.texOffs(0, 12).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        leg4 = new ModelRenderer(this);
        leg4.setPos(0.0F, 1.0F, -3.0F);
        body.addChild(leg4);
        leg4.texOffs(0, 12).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        leg5 = new ModelRenderer(this);
        leg5.setPos(2.0F, 1.0F, -2.0F);
        body.addChild(leg5);
        leg5.texOffs(0, 12).addBox(0.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        leg6 = new ModelRenderer(this);
        leg6.setPos(2.0F, 1.0F, 1.0F);
        body.addChild(leg6);
        leg6.texOffs(0, 12).addBox(0.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        leg7 = new ModelRenderer(this);
        leg7.setPos(1.0F, 1.0F, 3.0F);
        body.addChild(leg7);
        leg7.texOffs(0, 12).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        leg8 = new ModelRenderer(this);
        leg8.setPos(-2.0F, 1.0F, 3.0F);
        body.addChild(leg8);
        leg8.texOffs(0, 12).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
    }

    @Override
    public void renderToBuffer(com.mojang.blaze3d.matrix.MatrixStack matrixStack, com.mojang.blaze3d.vertex.IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelRenderer ModelRenderer, float x, float y, float z) {
        ModelRenderer.xRot = x;
        ModelRenderer.yRot = y;
        ModelRenderer.zRot = z;
    }


    @Override
    public void setupAnim(DumboOctopus state, float f, float g, float h, float i, float k) {
        super.setupAnim(state, f, g, h, i, k);
        if (state.animationSpeed > 0) {
            leg1.zRot = -state.tentacleAngle / 10;
            float rot = leg1.zRot;
            leg2.zRot = rot;
            leg3.xRot = -rot;
            leg4.xRot = -rot;
            leg5.zRot = -rot;
            leg6.zRot = -rot;
            leg7.xRot = rot;
            leg8.xRot = rot;
        }
    }
}
