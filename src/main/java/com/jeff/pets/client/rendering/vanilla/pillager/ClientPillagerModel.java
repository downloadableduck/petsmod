package com.jeff.pets.client.rendering.vanilla.pillager;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.vanilla.hostile.ClientPillager;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.util.math.MathHelper;

public class ClientPillagerModel extends PetModel<ClientPillager> {
    private final ModelPart waist;
    private final ModelPart Body;
    private final ModelPart head;
    private final ModelPart nose;
    private final ModelPart LeftLeg;
    private final ModelPart RightLeg;
    private final ModelPart RightArm;
    private final ModelPart LeftArm;

    public ClientPillagerModel() {
        f_9972380 /*textureWidth*/ = 64;
        f_9233444 /*textureHeight*/ = 64;

        waist = new ModelPart(this);
        waist.setPos(0.0F, 12.0F, 0.0F);


        Body = new ModelPart(this);
        Body.setPos(0.0F, 12.0F, 0.0F);
        waist.addChild(Body);
        Body.setTextureCoords(16, 20).addBox(-4.0F, -24.0F, -3.0F, 8, 12, 6, 0.0F, false);
        Body.setTextureCoords(0, 38).addBox(-4.0F, -24.0F, -3.0F, 8, 18, 6, 0.5F, false);

        head = new ModelPart(this);
        head.setPos(0.0F, 0.0F, 0.0F);
        head.setTextureCoords(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.0F, false);

        nose = new ModelPart(this);
        nose.setPos(0.0F, -2.0F, 0.0F);
        head.addChild(nose);
        nose.setTextureCoords(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2, 4, 2, 0.0F, false);

        LeftLeg = new ModelPart(this);
        LeftLeg.setPos(2.0F, 12.0F, 0.0F);
        LeftLeg.setTextureCoords(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false);

        RightLeg = new ModelPart(this);
        RightLeg.setPos(-2.0F, 12.0F, 0.0F);
        RightLeg.setTextureCoords(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, true);

        RightArm = new ModelPart(this);
        RightArm.setPos(-5.0F, 2.0F, 0.0F);
        RightArm.setTextureCoords(40, 46).addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F, false);

        LeftArm = new ModelPart(this);
        LeftArm.setPos(5.0F, 2.0F, 0.0F);
        LeftArm.setTextureCoords(40, 46).addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F, true);
    }

    @Override
    public void render(ClientPillager pillager, float f, float g, float h, float i, float j, float alpha) {
        waist.render(alpha);
        head.render(alpha);
        LeftLeg.render(alpha);
        RightLeg.render(alpha);
        RightArm.render(alpha);
        LeftArm.render(alpha);
    }

    public void setRotationAngle(ModelPart ModelPart, float x, float y, float z) {
        ModelPart.rotationX = x;
        ModelPart.rotationY = y;
        ModelPart.rotationZ = z;
    }

    @Override
    public void setup(ClientPillager illagerRenderState, float a, float c, float h, float b, float k, float s) {
        float f = illagerRenderState.walkAnimationSpeed;
        float g = illagerRenderState.walkAnimationProgress;
        this.RightArm.rotationX = MathHelper.cos(g * 0.6662F + (float) Math.PI) * 2.0F * f * 0.5F;
        this.RightArm.rotationY = 0.0F;
        this.RightArm.rotationZ = 0.0F;
        this.LeftArm.rotationX = MathHelper.cos(g * 0.6662F) * 2.0F * f * 0.5F;
        this.LeftArm.rotationY = 0.0F;
        this.LeftArm.rotationZ = 0.0F;
        this.RightLeg.rotationX = MathHelper.cos(g * 0.6662F) * 1.4F * f * 0.5F;
        this.RightLeg.rotationY = 0.0F;
        this.RightLeg.rotationZ = 0.0F;
        this.LeftLeg.rotationX = MathHelper.cos(g * 0.6662F + (float) Math.PI) * 1.4F * f * 0.5F;
        this.LeftLeg.rotationY = 0.0F;
        this.LeftLeg.rotationZ = 0.0F;
    }
}
