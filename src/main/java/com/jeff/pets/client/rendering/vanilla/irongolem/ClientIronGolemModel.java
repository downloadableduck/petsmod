package com.jeff.pets.client.rendering.vanilla.irongolem;

import net.minecraft.client.render.model.Model;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.entity.living.LivingEntity;

public class ClientIronGolemModel extends Model {
    public final ModelPart field_3414;
    private final ModelPart field_3415;
    private final ModelPart field_3413;
    private final ModelPart field_3412;
    private final ModelPart field_3411;
    private final ModelPart field_3416;

    public ClientIronGolemModel() {
        this(0.0F);
    }

    public ClientIronGolemModel(float f) {
        this(f, -7.0F);
    }

    public ClientIronGolemModel(float f, float g) {
        int i = 128;
        int j = 128;
        this.field_3415 = (new ModelPart(this)).setTextureSize(128, 128);
        this.field_3415.setPos(0.0F, 0.0F + g, -2.0F);
        this.field_3415.setTextureCoords(0, 0).addBox(-4.0F, -12.0F, -5.5F, 8, 10, 8, f);
        this.field_3415.setTextureCoords(24, 0).addBox(-1.0F, -5.0F, -7.5F, 2, 4, 2, f);
        this.field_3413 = (new ModelPart(this)).setTextureSize(128, 128);
        this.field_3413.setPos(0.0F, 0.0F + g, 0.0F);
        this.field_3413.setTextureCoords(0, 40).addBox(-9.0F, -2.0F, -6.0F, 18, 12, 11, f);
        this.field_3413.setTextureCoords(0, 70).addBox(-4.5F, 10.0F, -3.0F, 9, 5, 6, f + 0.5F);
        this.field_3414 = (new ModelPart(this)).setTextureSize(128, 128);
        this.field_3414.setPos(0.0F, -7.0F, 0.0F);
        this.field_3414.setTextureCoords(60, 21).addBox(-13.0F, -2.5F, -3.0F, 4, 30, 6, f);
        this.field_3412 = (new ModelPart(this)).setTextureSize(128, 128);
        this.field_3412.setPos(0.0F, -7.0F, 0.0F);
        this.field_3412.setTextureCoords(60, 58).addBox(9.0F, -2.5F, -3.0F, 4, 30, 6, f);
        this.field_3411 = (new ModelPart(this, 0, 22)).setTextureSize(128, 128);
        this.field_3411.setPos(-4.0F, 18.0F + g, 0.0F);
        this.field_3411.setTextureCoords(37, 0).addBox(-3.5F, -3.0F, -3.0F, 6, 16, 5, f);
        this.field_3416 = (new ModelPart(this, 0, 22)).setTextureSize(128, 128);
        this.field_3416.flipped = true;
        this.field_3416.setTextureCoords(60, 0).setPos(5.0F, 18.0F + g, 0.0F);
        this.field_3416.addBox(-3.5F, -3.0F, -3.0F, 6, 16, 5, f);
    }

    public void render(net.minecraft.entity.Entity entity, float f, float g, float h, float i, float j, float k) {
        this.setupAnimation(f, g, h, i, j, k, entity);
        this.field_3415.render(k);
        this.field_3413.render(k);
        this.field_3411.render(k);
        this.field_3416.render(k);
        this.field_3414.render(k);
        this.field_3412.render(k);
    }

    @Override
    public void setupAnimation(float f, float g, float h, float i, float j, float k, net.minecraft.entity.Entity entity) {
        this.field_3415.rotationY = i * ((float) Math.PI / 180F);
        this.field_3415.rotationX = j * ((float) Math.PI / 180F);
        this.field_3411.rotationX = -1.5F * this.method_2810(f, 13.0F) * g;
        this.field_3416.rotationX = 1.5F * this.method_2810(f, 13.0F) * g;
        this.field_3411.rotationY = 0.0F;
        this.field_3416.rotationY = 0.0F;
    }

    public void prepare(net.minecraft.entity.living.LivingEntity entity, float f, float g, float h) {
        int i = 0;
        if (i > 0) {
            this.field_3414.rotationX = -2.0F + 1.5F * this.method_2810((float) i - h, 10.0F);
            this.field_3412.rotationX = -2.0F + 1.5F * this.method_2810((float) i - h, 10.0F);
        } else {
            int j = 0;
            if (j > 0) {
                this.field_3414.rotationX = -0.8F + 0.025F * this.method_2810((float) j, 70.0F);
                this.field_3412.rotationX = 0.0F;
            } else {
                this.field_3414.rotationX = (-0.2F + 1.5F * this.method_2810(f, 13.0F)) * g;
                this.field_3412.rotationX = (-0.2F - 1.5F * this.method_2810(f, 13.0F)) * g;
            }
        }

    }

    private float method_2810(float f, float g) {
        return (Math.abs(f % g - g * 0.5F) - g * 0.25F) / (g * 0.25F);
    }

    public ModelPart method_2809() {
        return this.field_3414;
    }
}
