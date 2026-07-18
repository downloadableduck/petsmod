package com.jeff.pets.client.rendering.vanilla.evoker;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.AbstractPet;
import net.minecraft.client.model.Cuboid;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;


public class ClientEvokerModel<T extends AbstractPet> extends PetModel<@NotNull T> {
    private final Cuboid body;
    private final Cuboid Head;
    private final Cuboid nose;
    private final Cuboid arms;
    private final Cuboid leftLeg;
    private final Cuboid rightLeg;
    private final Cuboid RightArm;
    private final Cuboid LeftArm;

    public ClientEvokerModel() {
        textureWidth = 64;
        textureHeight = 64;

        body = new Cuboid(this);
        body.setRotationPoint(0.0F, 0.0F, 0.0F);
        body.setTextureOffset(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, 0.0F, false);
        body.setTextureOffset(0, 38).addBox(-4.0F, 0.0F, -3.0F, 8, 18, 6, 0.5F, false);

        Head = new Cuboid(this);
        Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        body.addChild(Head);
        Head.setTextureOffset(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.0F, false);

        nose = new Cuboid(this);
        nose.setRotationPoint(0.0F, -2.0F, 0.0F);
        Head.addChild(nose);
        nose.setTextureOffset(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2, 4, 2, 0.0F, false);

        arms = new Cuboid(this);
        arms.setRotationPoint(0.0F, 2.0F, 0.0F);
        body.addChild(arms);
        arms.setTextureOffset(44, 22).addBox(-8.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F, false);
        arms.setTextureOffset(44, 22).addBox(4.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F, false);
        arms.setTextureOffset(40, 38).addBox(-4.0F, 2.0F, -2.0F, 8, 4, 4, 0.0F, false);

        leftLeg = new Cuboid(this);
        leftLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        body.addChild(leftLeg);
        leftLeg.setTextureOffset(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false);

        rightLeg = new Cuboid(this);
        rightLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        body.addChild(rightLeg);
        rightLeg.setTextureOffset(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, true);

        RightArm = new Cuboid(this);
        RightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        body.addChild(RightArm);
        RightArm.setTextureOffset(40, 46).addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F, false);

        LeftArm = new Cuboid(this);
        LeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        body.addChild(LeftArm);
        LeftArm.setTextureOffset(40, 46).addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F, true);
    }

    @Override
    public void render(T t, float f, float g, float h, float i, float j, float alpha) {
        body.render(alpha);
    }

    public void setAngles(T illagerRenderState, float x, float z, float h, float m, float i, float s) {
        float f = illagerRenderState.limbDistance;
        float g = illagerRenderState.limbAngle;
        this.rightLeg.pitch = MathHelper.cos(g * 0.6662F) * 1.4F * f * 0.5F;
        this.rightLeg.yaw = 0.0F;
        this.rightLeg.roll = 0.0F;
        this.leftLeg.pitch = MathHelper.cos(g * 0.6662F + (float) Math.PI) * 1.4F * f * 0.5F;
        this.leftLeg.yaw = 0.0F;
        this.leftLeg.roll = 0.0F;
        this.RightArm.visible = false;
        this.LeftArm.visible = false;
    }
}
