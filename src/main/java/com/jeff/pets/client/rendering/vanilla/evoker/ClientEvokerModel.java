package com.jeff.pets.client.rendering.vanilla.evoker;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.AbstractPet;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ClientEvokerModel<T extends AbstractPet> extends PetModel<T> {
    private final ModelRenderer body;
    private final ModelRenderer Head;
    private final ModelRenderer nose;
    private final ModelRenderer arms;
    private final ModelRenderer leftLeg;
    private final ModelRenderer rightLeg;
    private final ModelRenderer RightArm;
    private final ModelRenderer LeftArm;

    public ClientEvokerModel() {
        texWidth = 64;
        texHeight = 64;

        body = new ModelRenderer(this);
        body.setPos(0.0F, 0.0F, 0.0F);
        body.texOffs(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F, 0.0F, false);
        body.texOffs(0, 38).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 18.0F, 6.0F, 0.5F, false);

        Head = new ModelRenderer(this);
        Head.setPos(0.0F, 0.0F, 0.0F);
        body.addChild(Head);
        Head.texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, 0.0F, false);

        nose = new ModelRenderer(this);
        nose.setPos(0.0F, -2.0F, 0.0F);
        Head.addChild(nose);
        nose.texOffs(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

        arms = new ModelRenderer(this);
        arms.setPos(0.0F, 2.0F, 0.0F);
        body.addChild(arms);
        arms.texOffs(44, 22).addBox(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
        arms.texOffs(44, 22).addBox(4.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
        arms.texOffs(40, 38).addBox(-4.0F, 2.0F, -2.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);

        leftLeg = new ModelRenderer(this);
        leftLeg.setPos(-2.0F, 12.0F, 0.0F);
        body.addChild(leftLeg);
        leftLeg.texOffs(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        rightLeg = new ModelRenderer(this);
        rightLeg.setPos(2.0F, 12.0F, 0.0F);
        body.addChild(rightLeg);
        rightLeg.texOffs(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);

        RightArm = new ModelRenderer(this);
        RightArm.setPos(-5.0F, 2.0F, 0.0F);
        body.addChild(RightArm);
        RightArm.texOffs(40, 46).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

        LeftArm = new ModelRenderer(this);
        LeftArm.setPos(5.0F, 2.0F, 0.0F);
        body.addChild(LeftArm);
        LeftArm.texOffs(40, 46).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void setupAnim(T illagerRenderState, float x, float z, float h, float m, float i) {
        float f = illagerRenderState.animationSpeed;
        float g = illagerRenderState.animationPosition;
        this.rightLeg.xRot = net.minecraft.util.math.MathHelper.cos(g * 0.6662F) * 1.4F * f * 0.5F;
        this.rightLeg.yRot = 0.0F;
        this.rightLeg.zRot = 0.0F;
        this.leftLeg.xRot = net.minecraft.util.math.MathHelper.cos(g * 0.6662F + (float) Math.PI) * 1.4F * f * 0.5F;
        this.leftLeg.yRot = 0.0F;
        this.leftLeg.zRot = 0.0F;
        this.RightArm.visible = false;
        this.LeftArm.visible = false;
    }
}
