package com.jeff.pets.client.rendering.vanilla.wither;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

import java.util.Arrays;

public class ClientWitherModel<T extends LivingEntity> extends EntityModel<T> {
    private final Cuboid[] upperBodyParts;
    private final Cuboid[] heads;
    private final ImmutableList<Cuboid> parts;

    public ClientWitherModel(float f) {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.upperBodyParts = new Cuboid[3];
        this.upperBodyParts[0] = new Cuboid(this, 0, 16);
        this.upperBodyParts[0].addBox(-10.0F, 3.9F, -0.5F, 20, 3, 3, f);
        this.upperBodyParts[1] = (new Cuboid(this)).setTextureSize(this.textureWidth, this.textureHeight);
        this.upperBodyParts[1].setRotationPoint(-2.0F, 6.9F, -0.5F);
        this.upperBodyParts[1].setTextureOffset(0, 22).addBox(0.0F, 0.0F, 0.0F, 3, 10, 3, f);
        this.upperBodyParts[1].setTextureOffset(24, 22).addBox(-4.0F, 1.5F, 0.5F, 11, 2, 2, f);
        this.upperBodyParts[1].setTextureOffset(24, 22).addBox(-4.0F, 4.0F, 0.5F, 11, 2, 2, f);
        this.upperBodyParts[1].setTextureOffset(24, 22).addBox(-4.0F, 6.5F, 0.5F, 11, 2, 2, f);
        this.upperBodyParts[2] = new Cuboid(this, 12, 22);
        this.upperBodyParts[2].addBox(0.0F, 0.0F, 0.0F, 3, 6, 3, f);
        this.heads = new Cuboid[3];
        this.heads[0] = new Cuboid(this, 0, 0);
        this.heads[0].addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, f);
        this.heads[1] = new Cuboid(this, 32, 0);
        this.heads[1].addBox(-4.0F, -4.0F, -4.0F, 6, 6, 6, f);
        this.heads[1].rotationPointX = -8.0F;
        this.heads[1].rotationPointY = 4.0F;
        this.heads[2] = new Cuboid(this, 32, 0);
        this.heads[2].addBox(-4.0F, -4.0F, -4.0F, 6, 6, 6, f);
        this.heads[2].rotationPointX = 10.0F;
        this.heads[2].rotationPointY = 4.0F;
        ImmutableList.Builder<Cuboid> builder = ImmutableList.builder();
        builder.addAll(Arrays.asList(this.heads));
        builder.addAll(Arrays.asList(this.upperBodyParts));
        this.parts = builder.build();
    }

    public ImmutableList<Cuboid> getParts() {
        return this.parts;
    }

    public void setAngles(T witherBoss, float f, float g, float h, float i, float j, float s) {
        float k = MathHelper.cos(h * 0.1F);
        this.upperBodyParts[1].pitch = (0.065F + 0.05F * k) * (float) Math.PI;
        this.upperBodyParts[2].setRotationPoint(-2.0F, 6.9F + MathHelper.cos(this.upperBodyParts[1].pitch) * 10.0F, -0.5F + MathHelper.sin(this.upperBodyParts[1].pitch) * 10.0F);
        this.upperBodyParts[2].pitch = (0.265F + 0.1F * k) * (float) Math.PI;
        this.heads[0].yaw = i * ((float) Math.PI / 180F);
        this.heads[0].pitch = j * ((float) Math.PI / 180F);
    }

    public void animateModel(T witherBoss, float f, float g, float h) {
        for (int i = 1; i < 3; ++i) {
            this.heads[i].yaw = (witherBoss.getHeadYaw() - witherBoss.yaw) * ((float) Math.PI / 180F);
            this.heads[i].pitch = witherBoss.pitch * ((float) Math.PI / 180F);
        }

    }

    @Override
    public void render(T t, float f, float g, float h, float i, float j, float k) {
        super.render(t, f, g, h, i, j, k);
        for (Cuboid cube : this.parts) {
            cube.render(k);
        }
    }
}
