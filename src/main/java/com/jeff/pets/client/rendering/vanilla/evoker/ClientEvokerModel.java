package com.jeff.pets.client.rendering.vanilla.evoker;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.AbstractPet;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;


public class ClientEvokerModel<T extends AbstractPet> extends PetModel<@NotNull T> {
    private final ModelPart body;
    private final ModelPart Head;
    private final ModelPart nose;
    private final ModelPart arms;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;
    private final ModelPart RightArm;
    private final ModelPart LeftArm;

    public ClientEvokerModel() {
        f_9972380 /*textureWidth*/ = 64;
        f_9233444 /*textureHeight*/ = 64;

        body = new ModelPart(this);
        body.setPos(0.0F, 0.0F, 0.0F);
        body.setTextureCoords(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, 0.0F, false);
        body.setTextureCoords(0, 38).addBox(-4.0F, 0.0F, -3.0F, 8, 18, 6, 0.5F, false);

        Head = new ModelPart(this);
        Head.setPos(0.0F, 0.0F, 0.0F);
        body.addChild(Head);
        Head.setTextureCoords(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.0F, false);

        nose = new ModelPart(this);
        nose.setPos(0.0F, -2.0F, 0.0F);
        Head.addChild(nose);
        nose.setTextureCoords(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2, 4, 2, 0.0F, false);

        arms = new ModelPart(this);
        arms.setPos(0.0F, 2.0F, 0.0F);
        body.addChild(arms);
        arms.setTextureCoords(44, 22).addBox(-8.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F, false);
        arms.setTextureCoords(44, 22).addBox(4.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F, false);
        arms.setTextureCoords(40, 38).addBox(-4.0F, 2.0F, -2.0F, 8, 4, 4, 0.0F, false);

        leftLeg = new ModelPart(this);
        leftLeg.setPos(-2.0F, 12.0F, 0.0F);
        body.addChild(leftLeg);
        leftLeg.setTextureCoords(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false);

        rightLeg = new ModelPart(this);
        rightLeg.setPos(2.0F, 12.0F, 0.0F);
        body.addChild(rightLeg);
        rightLeg.setTextureCoords(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, true);

        RightArm = new ModelPart(this);
        RightArm.setPos(-5.0F, 2.0F, 0.0F);
        body.addChild(RightArm);
        RightArm.setTextureCoords(40, 46).addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F, false);

        LeftArm = new ModelPart(this);
        LeftArm.setPos(5.0F, 2.0F, 0.0F);
        body.addChild(LeftArm);
        LeftArm.setTextureCoords(40, 46).addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F, true);
    }

    @Override
    public void render(T t, float f, float g, float h, float i, float j, float alpha) {
        body.render(alpha);
    }

    public void setup(T illagerRenderState, float x, float z, float h, float m, float i, float s) {
        float f = illagerRenderState.walkAnimationSpeed;
        float g = illagerRenderState.walkAnimationProgress;
        this.rightLeg.rotationX = MathHelper.cos(g * 0.6662F) * 1.4F * f * 0.5F;
        this.rightLeg.rotationY = 0.0F;
        this.rightLeg.rotationZ = 0.0F;
        this.leftLeg.rotationX = MathHelper.cos(g * 0.6662F + (float) Math.PI) * 1.4F * f * 0.5F;
        this.leftLeg.rotationY = 0.0F;
        this.leftLeg.rotationZ = 0.0F;
        this.RightArm.visible = false;
        this.LeftArm.visible = false;
    }
}
