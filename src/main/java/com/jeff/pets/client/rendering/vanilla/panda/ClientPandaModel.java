package com.jeff.pets.client.rendering.vanilla.panda;

import com.jeff.pets.client.rendering.AnimationUtils;
import com.jeff.pets.mob.vanilla.neutral.ClientPanda;
import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;
import net.minecraft.util.math.MathHelper;

public class ClientPandaModel extends QuadrupedEntityModel<ClientPanda> {
    private float sitAmount;
    private float lieOnBackAmount;
    private float rollAmount;

    public ClientPandaModel(int i, float f) {
        super(i, f);
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.head = new Cuboid(this, 0, 6);
        this.head.addBox(-6.5F, -5.0F, -4.0F, 13, 10, 9);
        this.head.setRotationPoint(0.0F, 11.5F, -17.0F);
        this.head.setTextureOffset(45, 16).addBox(-3.5F, 0.0F, -6.0F, 7, 5, 2);
        this.head.setTextureOffset(52, 25).addBox(-8.5F, -8.0F, -1.0F, 5, 4, 1);
        this.head.setTextureOffset(52, 25).addBox(3.5F, -8.0F, -1.0F, 5, 4, 1);
        this.body = new Cuboid(this, 0, 25);
        this.body.addBox(-9.5F, -13.0F, -6.5F, 19, 26, 13);
        this.body.setRotationPoint(0.0F, 10.0F, 0.0F);
        int j = 9;
        int k = 6;
        this.leg1 = new Cuboid(this, 40, 0);
        this.leg1.addBox(-3.0F, 0.0F, -3.0F, 6, 9, 6);
        this.leg1.setRotationPoint(-5.5F, 15.0F, 9.0F);
        this.leg2 = new Cuboid(this, 40, 0);
        this.leg2.addBox(-3.0F, 0.0F, -3.0F, 6, 9, 6);
        this.leg2.setRotationPoint(5.5F, 15.0F, 9.0F);
        this.leg3 = new Cuboid(this, 40, 0);
        this.leg3.addBox(-3.0F, 0.0F, -3.0F, 6, 9, 6);
        this.leg3.setRotationPoint(-5.5F, 15.0F, -9.0F);
        this.leg4 = new Cuboid(this, 40, 0);
        this.leg4.addBox(-3.0F, 0.0F, -3.0F, 6, 9, 6);
        this.leg4.setRotationPoint(5.5F, 15.0F, -9.0F);
    }

    public void animateModel(ClientPanda panda, float f, float g, float h) {
        super.animateModel(panda, f, g, h);
        this.sitAmount = 0;
        this.lieOnBackAmount = 0;
        this.rollAmount = panda.isBaby() ? 0.0F : 0;
    }

    public void setAngles(ClientPanda panda, float f, float g, float h, float i, float j, float s) {
        super.setAngles(panda, f, g, h, i, j, s);
        boolean bl = false;
        boolean bl2 = false;
        int k = 0;
        boolean bl3 = false;
        boolean bl4 = false;
        if (bl) {
            this.head.yaw = 0.35F * MathHelper.sin(0.6F * h);
            this.head.roll = 0.35F * MathHelper.sin(0.6F * h);
            this.leg3.pitch = -0.75F * MathHelper.sin(0.3F * h);
            this.leg4.pitch = 0.75F * MathHelper.sin(0.3F * h);
        } else {
            this.head.roll = 0.0F;
        }

        if (bl2) {
            if (k < 15) {
                this.head.pitch = (-(float) Math.PI / 4F) * (float) k / 14.0F;
            } else if (k < 20) {
                float l = (float) ((k - 15) / 5);
                this.head.pitch = (-(float) Math.PI / 4F) + ((float) Math.PI / 4F) * l;
            }
        }

        if (this.sitAmount > 0.0F) {
            this.body.pitch = AnimationUtils.rotlerpRad(this.body.pitch, 1.7407963F, this.sitAmount);
            this.head.pitch = AnimationUtils.rotlerpRad(this.head.pitch, ((float) Math.PI / 2F), this.sitAmount);
            this.leg3.roll = -0.27079642F;
            this.leg4.roll = 0.27079642F;
            this.leg1.roll = 0.5707964F;
            this.leg2.roll = -0.5707964F;
            if (bl3) {
                this.head.pitch = ((float) Math.PI / 2F) + 0.2F * MathHelper.sin(h * 0.6F);
                this.leg3.pitch = -0.4F - 0.2F * MathHelper.sin(h * 0.6F);
                this.leg4.pitch = -0.4F - 0.2F * MathHelper.sin(h * 0.6F);
            }

            if (bl4) {
                this.head.pitch = 2.1707964F;
                this.leg3.pitch = -0.9F;
                this.leg4.pitch = -0.9F;
            }
        } else {
            this.leg1.roll = 0.0F;
            this.leg2.roll = 0.0F;
            this.leg3.roll = 0.0F;
            this.leg4.roll = 0.0F;
        }

        if (this.lieOnBackAmount > 0.0F) {
            this.leg1.pitch = -0.6F * MathHelper.sin(h * 0.15F);
            this.leg2.pitch = 0.6F * MathHelper.sin(h * 0.15F);
            this.leg3.pitch = 0.3F * MathHelper.sin(h * 0.25F);
            this.leg4.pitch = -0.3F * MathHelper.sin(h * 0.25F);
            this.head.pitch = AnimationUtils.rotlerpRad(this.head.pitch, ((float) Math.PI / 2F), this.lieOnBackAmount);
        }

        if (this.rollAmount > 0.0F) {
            this.head.pitch = AnimationUtils.rotlerpRad(this.head.pitch, 2.0561945F, this.rollAmount);
            this.leg1.pitch = -0.5F * MathHelper.sin(h * 0.5F);
            this.leg2.pitch = 0.5F * MathHelper.sin(h * 0.5F);
            this.leg3.pitch = 0.5F * MathHelper.sin(h * 0.5F);
            this.leg4.pitch = -0.5F * MathHelper.sin(h * 0.5F);
        }

    }
}
