package com.jeff.pets.client.rendering.vanilla.pillager;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.vanilla.hostile.ClientPillager;
import net.minecraft.client.renderer.entity.model.RendererModel;

public class ClientPillagerModel extends PetModel<ClientPillager> {
    private final RendererModel waist;
    private final RendererModel Body;
    private final RendererModel head;
    private final RendererModel nose;
    private final RendererModel LeftLeg;
    private final RendererModel RightLeg;
    private final RendererModel RightArm;
    private final RendererModel LeftArm;

    public ClientPillagerModel() {
        texWidth = 64;
        texHeight = 64;

        waist = new RendererModel(this);
        waist.setPos(0.0F, 12.0F, 0.0F);


        Body = new RendererModel(this);
        Body.setPos(0.0F, 12.0F, 0.0F);
        waist.addChild(Body);
        Body.texOffs(16, 20).addBox(-4.0F, -24.0F, -3.0F, (int) 8.0F, (int) 12.0F, (int) 6.0F, 0.0F, false);
        Body.texOffs(0, 38).addBox(-4.0F, -24.0F, -3.0F, (int) 8.0F, (int) 18.0F, (int) 6.0F, 0.5F, false);

        head = new RendererModel(this);
        head.setPos(0.0F, 0.0F, 0.0F);
        head.texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, (int) 8.0F, (int) 10.0F, (int) 8.0F, 0.0F, false);

        nose = new RendererModel(this);
        nose.setPos(0.0F, -2.0F, 0.0F);
        head.addChild(nose);
        nose.texOffs(24, 0).addBox(-1.0F, -1.0F, -6.0F, (int) 2.0F, (int) 4.0F, (int) 2.0F, 0.0F, false);

        LeftLeg = new RendererModel(this);
        LeftLeg.setPos(2.0F, 12.0F, 0.0F);
        LeftLeg.texOffs(0, 22).addBox(-2.0F, 0.0F, -2.0F, (int) 4.0F, (int) 12.0F, (int) 4.0F, 0.0F, false);

        RightLeg = new RendererModel(this);
        RightLeg.setPos(-2.0F, 12.0F, 0.0F);
        RightLeg.texOffs(0, 22).addBox(-2.0F, 0.0F, -2.0F, (int) 4.0F, (int) 12.0F, (int) 4.0F, 0.0F, true);

        RightArm = new RendererModel(this);
        RightArm.setPos(-5.0F, 2.0F, 0.0F);
        RightArm.texOffs(40, 46).addBox(-3.0F, -2.0F, -2.0F, (int) 4.0F, (int) 12.0F, (int) 4.0F, 0.0F, false);

        LeftArm = new RendererModel(this);
        LeftArm.setPos(5.0F, 2.0F, 0.0F);
        LeftArm.texOffs(40, 46).addBox(-1.0F, -2.0F, -2.0F, (int) 4.0F, (int) 12.0F, (int) 4.0F, 0.0F, true);
    }

    @Override
    public void render(ClientPillager pillager, float packedLight, float packedOverlay, float red, float green, float blue, float alpha) {
        waist.render(alpha);
        head.render(alpha);
        LeftLeg.render(alpha);
        RightLeg.render(alpha);
        RightArm.render(alpha);
        LeftArm.render(alpha);
    }

    public void setRotationAngle(RendererModel RendererModel, float x, float y, float z) {
        RendererModel.xRot = x;
        RendererModel.yRot = y;
        RendererModel.zRot = z;
    }

    @Override
    public void setupAnim(ClientPillager illagerRenderState, float a, float c, float h, float b, float k, float u) {
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
