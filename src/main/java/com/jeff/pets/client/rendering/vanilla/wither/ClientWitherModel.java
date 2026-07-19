package com.jeff.pets.client.rendering.vanilla.wither;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.render.model.Model;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.entity.living.LivingEntity;
import net.minecraft.util.math.MathHelper;

import java.util.Arrays;

public class ClientWitherModel<T extends LivingEntity> extends Model<T> {
    private final ModelPart[] upperBodyParts;
    private final ModelPart[] heads;
    private final ImmutableList<ModelPart> parts;

    public ClientWitherModel(float f) {
        this.f_35376783 /*textureWidth*/ = 64;
        this.f_50207596 /*textureHeight*/ = 64;
        this.upperBodyParts = new ModelPart[3];
        this.upperBodyParts[0] = new ModelPart(this, 0, 16);
        this.upperBodyParts[0].addBox(-10.0F, 3.9F, -0.5F, 20, 3, 3, f);
        this.upperBodyParts[1] = (new ModelPart(this)).setTextureSize(this.f_35376783 /*textureWidth*/, this.f_50207596 /*textureHeight*/);
        this.upperBodyParts[1].setPos(-2.0F, 6.9F, -0.5F);
        this.upperBodyParts[1].setTextureCoords(0, 22).addBox(0.0F, 0.0F, 0.0F, 3, 10, 3, f);
        this.upperBodyParts[1].setTextureCoords(24, 22).addBox(-4.0F, 1.5F, 0.5F, 11, 2, 2, f);
        this.upperBodyParts[1].setTextureCoords(24, 22).addBox(-4.0F, 4.0F, 0.5F, 11, 2, 2, f);
        this.upperBodyParts[1].setTextureCoords(24, 22).addBox(-4.0F, 6.5F, 0.5F, 11, 2, 2, f);
        this.upperBodyParts[2] = new ModelPart(this, 12, 22);
        this.upperBodyParts[2].addBox(0.0F, 0.0F, 0.0F, 3, 6, 3, f);
        this.heads = new ModelPart[3];
        this.heads[0] = new ModelPart(this, 0, 0);
        this.heads[0].addBox(-4.0F, -4.0F, -4.0F, 8, 8, 8, f);
        this.heads[1] = new ModelPart(this, 32, 0);
        this.heads[1].addBox(-4.0F, -4.0F, -4.0F, 6, 6, 6, f);
        this.heads[1].x = -8.0F;
        this.heads[1].y = 4.0F;
        this.heads[2] = new ModelPart(this, 32, 0);
        this.heads[2].addBox(-4.0F, -4.0F, -4.0F, 6, 6, 6, f);
        this.heads[2].x = 10.0F;
        this.heads[2].y = 4.0F;
        ImmutableList.Builder<ModelPart> builder = ImmutableList.builder();
        builder.addAll(Arrays.asList(this.heads));
        builder.addAll(Arrays.asList(this.upperBodyParts));
        this.parts = builder.build();
    }

    public ImmutableList<ModelPart> getParts() {
        return this.parts;
    }

    public void setup(T witherBoss, float f, float g, float h, float i, float j, float s) {
        float k = MathHelper.cos(h * 0.1F);
        this.upperBodyParts[1].rotationX = (0.065F + 0.05F * k) * (float) Math.PI;
        this.upperBodyParts[2].setPos(-2.0F, 6.9F + MathHelper.cos(this.upperBodyParts[1].rotationX) * 10.0F, -0.5F + MathHelper.sin(this.upperBodyParts[1].rotationX) * 10.0F);
        this.upperBodyParts[2].rotationX = (0.265F + 0.1F * k) * (float) Math.PI;
        this.heads[0].rotationY = i * ((float) Math.PI / 180F);
        this.heads[0].rotationX = j * ((float) Math.PI / 180F);
    }

    public void prepare(T witherBoss, float f, float g, float h) {
        for (int i = 1; i < 3; ++i) {
            this.heads[i].rotationY = (witherBoss.getHeadYaw() - witherBoss.yaw) * ((float) Math.PI / 180F);
            this.heads[i].rotationX = witherBoss.pitch * ((float) Math.PI / 180F);
        }

    }

    @Override
    public void render(T t, float f, float g, float h, float i, float j, float k) {
        super.render(t, f, g, h, i, j, k);
        for (ModelPart cube : this.parts) {
            cube.render(k);
        }
    }
}
