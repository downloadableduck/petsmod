package com.jeff.pets.client.rendering.vanilla.horse;

import com.jeff.pets.client.Math2;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

public class ClientHorseModel extends EntityModel {
    protected final ModelPart field_3305;
    protected final ModelPart field_3307;
    private final ModelPart field_3306;
    private final ModelPart field_3303;
    private final ModelPart field_3302;
    private final ModelPart field_3308;
    private final ModelPart field_3300;
    private final ModelPart[] field_3304;
    private final ModelPart[] field_3301;

    public ClientHorseModel(float f) {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.field_3305 = new ModelPart(this, 0, 32);
        this.field_3305.addCuboid(-5.0F, -8.0F, -17.0F, 10, 10, 22, 0.05F);
        this.field_3305.setPivot(0.0F, 11.0F, 5.0F);
        this.field_3307 = new ModelPart(this, 0, 35);
        this.field_3307.addCuboid(-2.05F, -6.0F, -2.0F, 4, 12, 7);
        this.field_3307.posX = ((float) Math.PI / 6F);
        ModelPart ModelPart = new ModelPart(this, 0, 13);
        ModelPart.addCuboid(-3.0F, -11.0F, -2.0F, 6, 5, 7, f);
        ModelPart ModelPart2 = new ModelPart(this, 56, 36);
        ModelPart2.addCuboid(-1.0F, -11.0F, 5.01F, 2, 16, 2, f);
        ModelPart ModelPart3 = new ModelPart(this, 0, 25);
        ModelPart3.addCuboid(-2.0F, -11.0F, -7.0F, 4, 5, 5, f);
        this.field_3307.add(ModelPart);
        this.field_3307.add(ModelPart2);
        this.field_3307.add(ModelPart3);
        this.method_2789(this.field_3307);
        this.field_3306 = new ModelPart(this, 48, 21);
        this.field_3306.mirror = true;
        this.field_3306.addCuboid(-3.0F, -1.01F, -1.0F, 4, 11, 4, f);
        this.field_3306.setPivot(4.0F, 14.0F, 7.0F);
        this.field_3303 = new ModelPart(this, 48, 21);
        this.field_3303.addCuboid(-1.0F, -1.01F, -1.0F, 4, 11, 4, f);
        this.field_3303.setPivot(-4.0F, 14.0F, 7.0F);
        this.field_3302 = new ModelPart(this, 48, 21);
        this.field_3302.mirror = true;
        this.field_3302.addCuboid(-3.0F, -1.01F, -1.9F, 4, 11, 4, f);
        this.field_3302.setPivot(4.0F, 6.0F, -12.0F);
        this.field_3308 = new ModelPart(this, 48, 21);
        this.field_3308.addCuboid(-1.0F, -1.01F, -1.9F, 4, 11, 4, f);
        this.field_3308.setPivot(-4.0F, 6.0F, -12.0F);
        this.field_3300 = new ModelPart(this, 42, 36);
        this.field_3300.addCuboid(-1.5F, 0.0F, 0.0F, 3, 14, 4, f);
        this.field_3300.setPivot(0.0F, -5.0F, 2.0F);
        this.field_3300.posX = ((float) Math.PI / 6F);
        this.field_3305.add(this.field_3300);
        ModelPart ModelPart4 = new ModelPart(this, 26, 0);
        ModelPart4.addCuboid(-5.0F, -8.0F, -9.0F, 10, 9, 9, 0.5F);
        this.field_3305.add(ModelPart4);
        ModelPart ModelPart5 = new ModelPart(this, 29, 5);
        ModelPart5.addCuboid(2.0F, -9.0F, -6.0F, 1, 2, 2, f);
        this.field_3307.add(ModelPart5);
        ModelPart ModelPart6 = new ModelPart(this, 29, 5);
        ModelPart6.addCuboid(-3.0F, -9.0F, -6.0F, 1, 2, 2, f);
        this.field_3307.add(ModelPart6);
        ModelPart ModelPart7 = new ModelPart(this, 32, 2);
        ModelPart7.addCuboid(3.1F, -6.0F, -8.0F, 0, 3, 16, f);
        ModelPart7.posX = (-(float) Math.PI / 6F);
        this.field_3307.add(ModelPart7);
        ModelPart ModelPart8 = new ModelPart(this, 32, 2);
        ModelPart8.addCuboid(-3.1F, -6.0F, -8.0F, 0, 3, 16, f);
        ModelPart8.posX = (-(float) Math.PI / 6F);
        this.field_3307.add(ModelPart8);
        ModelPart ModelPart9 = new ModelPart(this, 1, 1);
        ModelPart9.addCuboid(-3.0F, -11.0F, -1.9F, 6, 5, 6, 0.2F);
        this.field_3307.add(ModelPart9);
        ModelPart ModelPart10 = new ModelPart(this, 19, 0);
        ModelPart10.addCuboid(-2.0F, -11.0F, -4.0F, 4, 5, 2, 0.2F);
        this.field_3307.add(ModelPart10);
        this.field_3304 = new ModelPart[]{ModelPart4, ModelPart5, ModelPart6, ModelPart9, ModelPart10};
        this.field_3301 = new ModelPart[]{ModelPart7, ModelPart8};
    }

    protected void method_2789(ModelPart ModelPart) {
        ModelPart ModelPart2 = new ModelPart(this, 19, 16);
        ModelPart2.addCuboid(0.55F, -13.0F, 4.0F, 2, 3, 1, -0.001F);
        ModelPart ModelPart3 = new ModelPart(this, 19, 16);
        ModelPart3.addCuboid(-2.55F, -13.0F, 4.0F, 2, 3, 1, -0.001F);
        ModelPart.add(ModelPart2);
        ModelPart.add(ModelPart3);
    }

    @Override
    public void render(net.minecraft.entity.Entity horseBaseEntity, float f, float g, float h, float i, float j, float k) {
        LivingEntity horseEntity = (LivingEntity) horseBaseEntity;
        boolean bl = horseEntity.isBaby();
        float l = 1.0F;
        boolean bl2 = false;
        boolean bl3 = horseEntity.hasPassengers();

        for (ModelPart ModelPart : this.field_3304) {
            ModelPart.visible = bl2;
        }

        for (ModelPart ModelPart : this.field_3301) {
            ModelPart.visible = bl3 && bl2;
        }

        if (bl) {
            GlStateManager.pushMatrix();
            GlStateManager.scale(l, 0.5F + l * 0.5F, l);
            GlStateManager.translate(0.0F, 0.95F * (1.0F - l), 0.0F);
        }

        this.field_3306.render(k);
        this.field_3303.render(k);
        this.field_3302.render(k);
        this.field_3308.render(k);
        if (bl) {
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale(l, l, l);
            GlStateManager.translate(0.0F, 2.3F * (1.0F - l), 0.0F);
        }

        this.field_3305.render(k);
        if (bl) {
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            float m = l + 0.1F * l;
            GlStateManager.scale(m, m, m);
            GlStateManager.translate(0.0F, 2.25F * (1.0F - m), 0.1F * (1.4F - m));
        }

        this.field_3307.render(k);
        if (bl) {
            GlStateManager.popMatrix();
        }

    }

    @Override
    public void animateModel(LivingEntity horseBaseEntity, float f, float g, float h) {
        super.animateModel(horseBaseEntity, f, g, h);
        float i = this.method_2790(horseBaseEntity.prevBodyYaw, horseBaseEntity.bodyYaw, h);
        float j = this.method_2790(horseBaseEntity.prevHeadYaw, horseBaseEntity.headYaw, h);
        float k = Math2.lerp(h, horseBaseEntity.prevPitch, horseBaseEntity.pitch);
        float l = j - i;
        float m = k * ((float) Math.PI / 180F);
        if (l > 20.0F) {
            l = 20.0F;
        }

        if (l < -20.0F) {
            l = -20.0F;
        }

        if (g > 0.2F) {
            m += MathHelper.cos(f * 0.4F) * 0.15F * g;
        }

        float n = 0;
        float o = 0;
        float p = 1.0F - o;
        float q = 0;
        boolean bl = true;
        float r = (float) horseBaseEntity.ticksAlive + h;
        this.field_3307.pivotY = 4.0F;
        this.field_3307.pivotZ = -12.0F;
        this.field_3305.posX = 0.0F;
        this.field_3307.posX = ((float) Math.PI / 6F) + m;
        this.field_3307.posY = l * ((float) Math.PI / 180F);
        float s = horseBaseEntity.isTouchingWater() ? 0.2F : 1.0F;
        float t = MathHelper.cos(s * f * 0.6662F + (float) Math.PI);
        float u = t * 0.8F * g;
        float v = (1.0F - Math.max(o, n)) * (((float) Math.PI / 6F) + m + q * MathHelper.sin(r) * 0.05F);
        this.field_3307.posX = o * (0.2617994F + m) + n * (2.1816616F + MathHelper.sin(r) * 0.05F) + v;
        this.field_3307.posY = o * l * ((float) Math.PI / 180F) + (1.0F - Math.max(o, n)) * this.field_3307.posY;
        this.field_3307.pivotY = o * -4.0F + n * 11.0F + (1.0F - Math.max(o, n)) * this.field_3307.pivotY;
        this.field_3307.pivotZ = o * -4.0F + n * -12.0F + (1.0F - Math.max(o, n)) * this.field_3307.pivotZ;
        this.field_3305.posX = o * (-(float) Math.PI / 4F) + p * this.field_3305.posX;
        float w = 0.2617994F * o;
        float x = MathHelper.cos(r * 0.6F + (float) Math.PI);
        this.field_3302.pivotY = 2.0F * o + 14.0F * p;
        this.field_3302.pivotZ = -6.0F * o - 10.0F * p;
        this.field_3308.pivotY = this.field_3302.pivotY;
        this.field_3308.pivotZ = this.field_3302.pivotZ;
        float y = ((-(float) Math.PI / 3F) + x) * o + u * p;
        float z = ((-(float) Math.PI / 3F) - x) * o - u * p;
        this.field_3306.posX = w - t * 0.5F * g * p;
        this.field_3303.posX = w + t * 0.5F * g * p;
        this.field_3302.posX = y;
        this.field_3308.posX = z;
        this.field_3300.posX = ((float) Math.PI / 6F) + g * 0.75F;
        this.field_3300.pivotY = -5.0F + g;
        this.field_3300.pivotZ = 2.0F + g * 2.0F;
        if (bl) {
            this.field_3300.posY = MathHelper.cos(r * 0.7F);
        } else {
            this.field_3300.posY = 0.0F;
        }

    }

    private float method_2790(float f, float g, float h) {
        float i;
        for (i = g - f; i < -180.0F; i += 360.0F) {
        }

        while (i >= 180.0F) {
            i -= 360.0F;
        }

        return f + h * i;
    }
}
