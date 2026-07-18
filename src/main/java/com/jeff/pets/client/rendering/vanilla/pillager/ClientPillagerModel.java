package com.jeff.pets.client.rendering.vanilla.pillager;

import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.mob.vanilla.hostile.ClientPillager;
import net.minecraft.client.model.Cuboid;
import net.minecraft.util.math.MathHelper;

public class ClientPillagerModel extends PetModel<ClientPillager> {
    private final Cuboid waist;
    private final Cuboid Body;
    private final Cuboid head;
    private final Cuboid nose;
    private final Cuboid LeftLeg;
    private final Cuboid RightLeg;
    private final Cuboid RightArm;
    private final Cuboid LeftArm;

    public ClientPillagerModel() {
        textureWidth = 64;
        textureHeight = 64;

        waist = new Cuboid(this);
        waist.setRotationPoint(0.0F, 12.0F, 0.0F);


        Body = new Cuboid(this);
        Body.setRotationPoint(0.0F, 12.0F, 0.0F);
        waist.addChild(Body);
        Body.setTextureOffset(16, 20).addBox(-4.0F, -24.0F, -3.0F, 8, 12, 6, 0.0F, false);
        Body.setTextureOffset(0, 38).addBox(-4.0F, -24.0F, -3.0F, 8, 18, 6, 0.5F, false);

        head = new Cuboid(this);
        head.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.setTextureOffset(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.0F, false);

        nose = new Cuboid(this);
        nose.setRotationPoint(0.0F, -2.0F, 0.0F);
        head.addChild(nose);
        nose.setTextureOffset(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2, 4, 2, 0.0F, false);

        LeftLeg = new Cuboid(this);
        LeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        LeftLeg.setTextureOffset(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false);

        RightLeg = new Cuboid(this);
        RightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        RightLeg.setTextureOffset(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, true);

        RightArm = new Cuboid(this);
        RightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
        RightArm.setTextureOffset(40, 46).addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F, false);

        LeftArm = new Cuboid(this);
        LeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
        LeftArm.setTextureOffset(40, 46).addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F, true);
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

    public void setRotationAngle(Cuboid Cuboid, float x, float y, float z) {
        Cuboid.pitch = x;
        Cuboid.yaw = y;
        Cuboid.roll = z;
    }

    @Override
    public void setAngles(ClientPillager illagerRenderState, float a, float c, float h, float b, float k, float s) {
        float f = illagerRenderState.limbDistance;
        float g = illagerRenderState.limbAngle;
        this.RightArm.pitch = MathHelper.cos(g * 0.6662F + (float) Math.PI) * 2.0F * f * 0.5F;
        this.RightArm.yaw = 0.0F;
        this.RightArm.roll = 0.0F;
        this.LeftArm.pitch = MathHelper.cos(g * 0.6662F) * 2.0F * f * 0.5F;
        this.LeftArm.yaw = 0.0F;
        this.LeftArm.roll = 0.0F;
        this.RightLeg.pitch = MathHelper.cos(g * 0.6662F) * 1.4F * f * 0.5F;
        this.RightLeg.yaw = 0.0F;
        this.RightLeg.roll = 0.0F;
        this.LeftLeg.pitch = MathHelper.cos(g * 0.6662F + (float) Math.PI) * 1.4F * f * 0.5F;
        this.LeftLeg.yaw = 0.0F;
        this.LeftLeg.roll = 0.0F;
    }
}
