//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.wither;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import com.jeff.pets.client.rendering.PetModel;
import com.jeff.pets.compat.render.model.ModelPart;
import com.jeff.pets.mob.vanilla.boss.ClientWither;

import java.util.Arrays;

public class ClientWitherModel extends PetModel {
    private final ModelPart[] upperBodyParts;
    private final ModelPart[] heads;
    private final ImmutableList<ModelPart> parts;

    public ClientWitherModel(float f) {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.upperBodyParts = new ModelPart[3];
        this.upperBodyParts[0] = new ModelPart(this, 0, 16);
        this.upperBodyParts[0].addBox(-10.0F, 3.9F, -0.5F, 20, 3, 3, f);
        this.upperBodyParts[1] = (new ModelPart(this)).setTextureSize(this.textureWidth, this.textureHeight);
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

    public void setupAnimation(float f, float g, float h, float i, float j, float s, Entity entity) {
        float k = MathHelper.cos(h * 0.1F);
        this.upperBodyParts[1].rotationX = (0.065F + 0.05F * k) * 3.1415927F;
        this.upperBodyParts[2].setPos(-2.0F, 6.9F + MathHelper.cos(this.upperBodyParts[1].rotationX) * 10.0F, -0.5F + MathHelper.sin(this.upperBodyParts[1].rotationX) * 10.0F);
        this.upperBodyParts[2].rotationX = (0.265F + 0.1F * k) * 3.1415927F;
        this.heads[0].rotationY = i * 0.017453292F;
        this.heads[0].rotationX = j * 0.017453292F;
    }

    public void animateModel(LivingEntity entity, float f, float g, float h) {
        ClientWither witherBoss = (ClientWither)entity;

        for(int i = 1; i < 3; ++i) {
            this.heads[i].rotationY = (witherBoss.yaw - witherBoss.yaw) * 0.017453292F;
            this.heads[i].rotationX = witherBoss.pitch * 0.017453292F;
        }

    }

    public void render(Entity entity, float f, float g, float h, float i, float j, float k) {
        super.render(entity, f, g, h, i, j, k);
        UnmodifiableIterator var8 = this.parts.iterator();

        while(var8.hasNext()) {
            ModelPart cube = (ModelPart)var8.next();
            cube.render(k);
        }

    }
}
