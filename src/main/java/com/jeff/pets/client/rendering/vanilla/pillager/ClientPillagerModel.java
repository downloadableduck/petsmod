package com.jeff.pets.client.rendering.vanilla.pillager;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.vanilla.hostile.ClientPillager;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ClientPillagerModel extends PetModel<ClientPillager> {
    private final ModelRenderer waist;
    private final ModelRenderer Body;
    private final ModelRenderer head;
    private final ModelRenderer nose;
    private final ModelRenderer LeftLeg;
    private final ModelRenderer RightLeg;
    private final ModelRenderer RightArm;
    private final ModelRenderer LeftArm;

    public ClientPillagerModel() {
        texWidth = 64;
        texHeight = 64;

        waist = new ModelRenderer(this);
        waist.setPos(0.0F, 12.0F, 0.0F);


        Body = new ModelRenderer(this);
        Body.setPos(0.0F, 12.0F, 0.0F);
        waist.addChild(Body);
        Body.texOffs(16, 20).addBox(-4.0F, -24.0F, -3.0F, 8.0F, 12.0F, 6.0F, 0.0F, false);
        Body.texOffs(0, 38).addBox(-4.0F, -24.0F, -3.0F, 8.0F, 18.0F, 6.0F, 0.5F, false);

        head = new ModelRenderer(this);
        head.setPos(0.0F, 0.0F, 0.0F);
        head.texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, 0.0F, false);

        nose = new ModelRenderer(this);
        nose.setPos(0.0F, -2.0F, 0.0F);
        head.addChild(nose);
        nose.texOffs(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        LeftLeg = new ModelRenderer(this);
        LeftLeg.setPos(2.0F, 12.0F, 0.0F);
        LeftLeg.texOffs(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        RightLeg = new ModelRenderer(this);
        RightLeg.setPos(-2.0F, 12.0F, 0.0F);
        RightLeg.texOffs(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);

        RightArm = new ModelRenderer(this);
        RightArm.setPos(-5.0F, 2.0F, 0.0F);
        RightArm.texOffs(40, 46).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        LeftArm = new ModelRenderer(this);
        LeftArm.setPos(5.0F, 2.0F, 0.0F);
        LeftArm.texOffs(40, 46).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);
    }

    @Override
    public void renderToBuffer(com.mojang.blaze3d.matrix.MatrixStack matrixStack, com.mojang.blaze3d.vertex.IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        waist.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        LeftLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        RightLeg.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        RightArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        LeftArm.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setRotationAngle(ModelRenderer ModelRenderer, float x, float y, float z) {
        ModelRenderer.xRot = x;
        ModelRenderer.yRot = y;
        ModelRenderer.zRot = z;
    }

    @Override
    public void setupAnim(ClientPillager illagerRenderState, float a, float c, float h, float b, float k) {
        float f = illagerRenderState.animationSpeed;
        float g = illagerRenderState.animationPosition;
        this.RightArm.xRot = net.minecraft.util.math.MathHelper.cos(g * 0.6662F + (float) Math.PI) * 2.0F * f * 0.5F;
        this.RightArm.yRot = 0.0F;
        this.RightArm.zRot = 0.0F;
        this.LeftArm.xRot = net.minecraft.util.math.MathHelper.cos(g * 0.6662F) * 2.0F * f * 0.5F;
        this.LeftArm.yRot = 0.0F;
        this.LeftArm.zRot = 0.0F;
        this.RightLeg.xRot = net.minecraft.util.math.MathHelper.cos(g * 0.6662F) * 1.4F * f * 0.5F;
        this.RightLeg.yRot = 0.0F;
        this.RightLeg.zRot = 0.0F;
        this.LeftLeg.xRot = net.minecraft.util.math.MathHelper.cos(g * 0.6662F + (float) Math.PI) * 1.4F * f * 0.5F;
        this.LeftLeg.yRot = 0.0F;
        this.LeftLeg.zRot = 0.0F;
    }
}
