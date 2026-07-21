package com.jeff.pets.client.rendering.vanilla.horse;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.render.model.Model;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.entity.living.LivingEntity;
import net.minecraft.util.math.MathHelper;

public class ClientHorseModel<T extends LivingEntity> extends Model<T> {
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
        this.f_9972380 /*textureWidth*/ = 64;
        this.f_9233444 /*textureHeight*/ = 64;
        this.field_3305 = new ModelPart(this, 0, 32);
        this.field_3305.addBox(-5.0F, -8.0F, -17.0F, 10, 10, 22, 0.05F);
        this.field_3305.setPos(0.0F, 11.0F, 5.0F);
        this.field_3307 = new ModelPart(this, 0, 35);
        this.field_3307.addBox(-2.05F, -6.0F, -2.0F, 4, 12, 7);
        this.field_3307.rotationX = ((float) Math.PI / 6F);
        ModelPart ModelPart = new ModelPart(this, 0, 13);
        ModelPart.addBox(-3.0F, -11.0F, -2.0F, 6, 5, 7, f);
        ModelPart ModelPart2 = new ModelPart(this, 56, 36);
        ModelPart2.addBox(-1.0F, -11.0F, 5.01F, 2, 16, 2, f);
        ModelPart ModelPart3 = new ModelPart(this, 0, 25);
        ModelPart3.addBox(-2.0F, -11.0F, -7.0F, 4, 5, 5, f);
        this.field_3307.addChild(ModelPart);
        this.field_3307.addChild(ModelPart2);
        this.field_3307.addChild(ModelPart3);
        this.method_2789(this.field_3307);
        this.field_3306 = new ModelPart(this, 48, 21);
        this.field_3306.flipped = true;
        this.field_3306.addBox(-3.0F, -1.01F, -1.0F, 4, 11, 4, f);
        this.field_3306.setPos(4.0F, 14.0F, 7.0F);
        this.field_3303 = new ModelPart(this, 48, 21);
        this.field_3303.addBox(-1.0F, -1.01F, -1.0F, 4, 11, 4, f);
        this.field_3303.setPos(-4.0F, 14.0F, 7.0F);
        this.field_3302 = new ModelPart(this, 48, 21);
        this.field_3302.flipped = true;
        this.field_3302.addBox(-3.0F, -1.01F, -1.9F, 4, 11, 4, f);
        this.field_3302.setPos(4.0F, 6.0F, -12.0F);
        this.field_3308 = new ModelPart(this, 48, 21);
        this.field_3308.addBox(-1.0F, -1.01F, -1.9F, 4, 11, 4, f);
        this.field_3308.setPos(-4.0F, 6.0F, -12.0F);
        this.field_3300 = new ModelPart(this, 42, 36);
        this.field_3300.addBox(-1.5F, 0.0F, 0.0F, 3, 14, 4, f);
        this.field_3300.setPos(0.0F, -5.0F, 2.0F);
        this.field_3300.rotationX = ((float) Math.PI / 6F);
        this.field_3305.addChild(this.field_3300);
        ModelPart ModelPart4 = new ModelPart(this, 26, 0);
        ModelPart4.addBox(-5.0F, -8.0F, -9.0F, 10, 9, 9, 0.5F);
        this.field_3305.addChild(ModelPart4);
        ModelPart ModelPart5 = new ModelPart(this, 29, 5);
        ModelPart5.addBox(2.0F, -9.0F, -6.0F, 1, 2, 2, f);
        this.field_3307.addChild(ModelPart5);
        ModelPart ModelPart6 = new ModelPart(this, 29, 5);
        ModelPart6.addBox(-3.0F, -9.0F, -6.0F, 1, 2, 2, f);
        this.field_3307.addChild(ModelPart6);
        ModelPart ModelPart7 = new ModelPart(this, 32, 2);
        ModelPart7.addBox(3.1F, -6.0F, -8.0F, 0, 3, 16, f);
        ModelPart7.rotationX = (-(float) Math.PI / 6F);
        this.field_3307.addChild(ModelPart7);
        ModelPart ModelPart8 = new ModelPart(this, 32, 2);
        ModelPart8.addBox(-3.1F, -6.0F, -8.0F, 0, 3, 16, f);
        ModelPart8.rotationX = (-(float) Math.PI / 6F);
        this.field_3307.addChild(ModelPart8);
        ModelPart ModelPart9 = new ModelPart(this, 1, 1);
        ModelPart9.addBox(-3.0F, -11.0F, -1.9F, 6, 5, 6, 0.2F);
        this.field_3307.addChild(ModelPart9);
        ModelPart ModelPart10 = new ModelPart(this, 19, 0);
        ModelPart10.addBox(-2.0F, -11.0F, -4.0F, 4, 5, 2, 0.2F);
        this.field_3307.addChild(ModelPart10);
        this.field_3304 = new ModelPart[]{ModelPart4, ModelPart5, ModelPart6, ModelPart9, ModelPart10};
        this.field_3301 = new ModelPart[]{ModelPart7, ModelPart8};
    }

    protected void method_2789(ModelPart ModelPart) {
        ModelPart ModelPart2 = new ModelPart(this, 19, 16);
        ModelPart2.addBox(0.55F, -13.0F, 4.0F, 2, 3, 1, -0.001F);
        ModelPart ModelPart3 = new ModelPart(this, 19, 16);
        ModelPart3.addBox(-2.55F, -13.0F, 4.0F, 2, 3, 1, -0.001F);
        ModelPart.addChild(ModelPart2);
        ModelPart.addChild(ModelPart3);
    }

    @Override
    public void render(T horseBaseEntity, float f, float g, float h, float i, float j, float k) {
        boolean bl = horseBaseEntity.isBaby();
        float l = 1;
        boolean bl2 = false;
        boolean bl3 = horseBaseEntity.hasPassengers();

        for (ModelPart ModelPart : this.field_3304) {
            ModelPart.visible = bl2;
        }

        for (ModelPart ModelPart : this.field_3301) {
            ModelPart.visible = bl3 && bl2;
        }

        if (bl) {
            GlStateManager.pushMatrix();
            GlStateManager.scalef(l, 0.5F + l * 0.5F, l);
            GlStateManager.translatef(0.0F, 0.95F * (1.0F - l), 0.0F);
        }

        this.field_3306.render(k);
        this.field_3303.render(k);
        this.field_3302.render(k);
        this.field_3308.render(k);
        if (bl) {
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scalef(l, l, l);
            GlStateManager.translatef(0.0F, 2.3F * (1.0F - l), 0.0F);
        }

        this.field_3305.render(k);
        if (bl) {
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            float m = l + 0.1F * l;
            GlStateManager.scalef(m, m, m);
            GlStateManager.translatef(0.0F, 2.25F * (1.0F - m), 0.1F * (1.4F - m));
        }

        this.field_3307.render(k);
        if (bl) {
            GlStateManager.popMatrix();
        }

    }

    @Override
    public void prepare(T horseBaseEntity, float f, float g, float h) {
        super.prepare(horseBaseEntity, f, g, h);
        float i = this.method_2790(horseBaseEntity.lastBodyYaw, horseBaseEntity.bodyYaw, h);
        float j = this.method_2790(horseBaseEntity.lastHeadYaw, horseBaseEntity.headYaw, h);
        float k = (float) MathHelper.m_4848186 /*lerp*/(h, horseBaseEntity.lastPitch, horseBaseEntity.pitch);
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
        float r = (float) horseBaseEntity.ticks + h;
        this.field_3307.y = 4.0F;
        this.field_3307.z = -12.0F;
        this.field_3305.rotationX = 0.0F;
        this.field_3307.rotationX = ((float) Math.PI / 6F) + m;
        this.field_3307.rotationY = l * ((float) Math.PI / 180F);
        float s = horseBaseEntity.isInWater() ? 0.2F : 1.0F;
        float t = MathHelper.cos(s * f * 0.6662F + (float) Math.PI);
        float u = t * 0.8F * g;
        float v = (1.0F - Math.max(o, n)) * (((float) Math.PI / 6F) + m + q * MathHelper.sin(r) * 0.05F);
        this.field_3307.rotationX = o * (0.2617994F + m) + n * (2.1816616F + MathHelper.sin(r) * 0.05F) + v;
        this.field_3307.rotationY = o * l * ((float) Math.PI / 180F) + (1.0F - Math.max(o, n)) * this.field_3307.rotationY;
        this.field_3307.y = o * -4.0F + n * 11.0F + (1.0F - Math.max(o, n)) * this.field_3307.y;
        this.field_3307.z = o * -4.0F + n * -12.0F + (1.0F - Math.max(o, n)) * this.field_3307.z;
        this.field_3305.rotationX = o * (-(float) Math.PI / 4F) + p * this.field_3305.rotationX;
        float w = 0.2617994F * o;
        float x = MathHelper.cos(r * 0.6F + (float) Math.PI);
        this.field_3302.y = 2.0F * o + 14.0F * p;
        this.field_3302.z = -6.0F * o - 10.0F * p;
        this.field_3308.y = this.field_3302.y;
        this.field_3308.z = this.field_3302.z;
        float y = ((-(float) Math.PI / 3F) + x) * o + u * p;
        float z = ((-(float) Math.PI / 3F) - x) * o - u * p;
        this.field_3306.rotationX = w - t * 0.5F * g * p;
        this.field_3303.rotationX = w + t * 0.5F * g * p;
        this.field_3302.rotationX = y;
        this.field_3308.rotationX = z;
        this.field_3300.rotationX = ((float) Math.PI / 6F) + g * 0.75F;
        this.field_3300.y = -5.0F + g;
        this.field_3300.z = 2.0F + g * 2.0F;
        if (bl) {
            this.field_3300.rotationY = MathHelper.cos(r * 0.7F);
        } else {
            this.field_3300.rotationY = 0.0F;
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
