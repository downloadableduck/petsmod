package com.jeff.pets.client.rendering.vanilla.horse;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.HorseModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

public class ClientHorseModel<T extends LivingEntity> extends EntityModel<T> {
    protected final RendererModel field_3305;
    protected final RendererModel field_3307;
    private final RendererModel field_3306;
    private final RendererModel field_3303;
    private final RendererModel field_3302;
    private final RendererModel field_3308;
    private final RendererModel field_3300;
    private final RendererModel[] field_3304;
    private final RendererModel[] field_3301;

    public ClientHorseModel(float f) {
        this.texWidth = 64;
        this.texHeight = 64;
        this.field_3305 = new RendererModel(this, 0, 32);
        this.field_3305.addBox(-5.0F, -8.0F, -17.0F, 10, 10, 22, 0.05F);
        this.field_3305.setPos(0.0F, 11.0F, 5.0F);
        this.field_3307 = new RendererModel(this, 0, 35);
        this.field_3307.addBox(-2.05F, -6.0F, -2.0F, 4, 12, 7);
        this.field_3307.xRot = ((float) Math.PI / 6F);
        RendererModel RendererModel = new RendererModel(this, 0, 13);
        RendererModel.addBox(-3.0F, -11.0F, -2.0F, 6, 5, 7, f);
        RendererModel RendererModel2 = new RendererModel(this, 56, 36);
        RendererModel2.addBox(-1.0F, -11.0F, 5.01F, 2, 16, 2, f);
        RendererModel RendererModel3 = new RendererModel(this, 0, 25);
        RendererModel3.addBox(-2.0F, -11.0F, -7.0F, 4, 5, 5, f);
        this.field_3307.addChild(RendererModel);
        this.field_3307.addChild(RendererModel2);
        this.field_3307.addChild(RendererModel3);
        this.method_2789(this.field_3307);
        this.field_3306 = new RendererModel(this, 48, 21);
        this.field_3306.mirror = true;
        this.field_3306.addBox(-3.0F, -1.01F, -1.0F, 4, 11, 4, f);
        this.field_3306.setPos(4.0F, 14.0F, 7.0F);
        this.field_3303 = new RendererModel(this, 48, 21);
        this.field_3303.addBox(-1.0F, -1.01F, -1.0F, 4, 11, 4, f);
        this.field_3303.setPos(-4.0F, 14.0F, 7.0F);
        this.field_3302 = new RendererModel(this, 48, 21);
        this.field_3302.mirror = true;
        this.field_3302.addBox(-3.0F, -1.01F, -1.9F, 4, 11, 4, f);
        this.field_3302.setPos(4.0F, 6.0F, -12.0F);
        this.field_3308 = new RendererModel(this, 48, 21);
        this.field_3308.addBox(-1.0F, -1.01F, -1.9F, 4, 11, 4, f);
        this.field_3308.setPos(-4.0F, 6.0F, -12.0F);
        this.field_3300 = new RendererModel(this, 42, 36);
        this.field_3300.addBox(-1.5F, 0.0F, 0.0F, 3, 14, 4, f);
        this.field_3300.setPos(0.0F, -5.0F, 2.0F);
        this.field_3300.xRot = ((float) Math.PI / 6F);
        this.field_3305.addChild(this.field_3300);
        RendererModel RendererModel4 = new RendererModel(this, 26, 0);
        RendererModel4.addBox(-5.0F, -8.0F, -9.0F, 10, 9, 9, 0.5F);
        this.field_3305.addChild(RendererModel4);
        RendererModel RendererModel5 = new RendererModel(this, 29, 5);
        RendererModel5.addBox(2.0F, -9.0F, -6.0F, 1, 2, 2, f);
        this.field_3307.addChild(RendererModel5);
        RendererModel RendererModel6 = new RendererModel(this, 29, 5);
        RendererModel6.addBox(-3.0F, -9.0F, -6.0F, 1, 2, 2, f);
        this.field_3307.addChild(RendererModel6);
        RendererModel RendererModel7 = new RendererModel(this, 32, 2);
        RendererModel7.addBox(3.1F, -6.0F, -8.0F, 0, 3, 16, f);
        RendererModel7.xRot = (-(float) Math.PI / 6F);
        this.field_3307.addChild(RendererModel7);
        RendererModel RendererModel8 = new RendererModel(this, 32, 2);
        RendererModel8.addBox(-3.1F, -6.0F, -8.0F, 0, 3, 16, f);
        RendererModel8.xRot = (-(float) Math.PI / 6F);
        this.field_3307.addChild(RendererModel8);
        RendererModel RendererModel9 = new RendererModel(this, 1, 1);
        RendererModel9.addBox(-3.0F, -11.0F, -1.9F, 6, 5, 6, 0.2F);
        this.field_3307.addChild(RendererModel9);
        RendererModel RendererModel10 = new RendererModel(this, 19, 0);
        RendererModel10.addBox(-2.0F, -11.0F, -4.0F, 4, 5, 2, 0.2F);
        this.field_3307.addChild(RendererModel10);
        this.field_3304 = new RendererModel[]{RendererModel4, RendererModel5, RendererModel6, RendererModel9, RendererModel10};
        this.field_3301 = new RendererModel[]{RendererModel7, RendererModel8};
    }

    protected void method_2789(RendererModel RendererModel) {
        RendererModel RendererModel2 = new RendererModel(this, 19, 16);
        RendererModel2.addBox(0.55F, -13.0F, 4.0F, 2, 3, 1, -0.001F);
        RendererModel RendererModel3 = new RendererModel(this, 19, 16);
        RendererModel3.addBox(-2.55F, -13.0F, 4.0F, 2, 3, 1, -0.001F);
        RendererModel.addChild(RendererModel2);
        RendererModel.addChild(RendererModel3);
    }

    @Override
    public void render(T horseBaseEntity, float f, float g, float h, float i, float j, float k) {
        boolean bl = horseBaseEntity.isBaby();
        float l = horseBaseEntity.getScale();
        boolean bl2 = false;
        boolean bl3 = horseBaseEntity.isVehicle();

        for (RendererModel RendererModel : this.field_3304) {
            RendererModel.visible = bl2;
        }

        for (RendererModel RendererModel : this.field_3301) {
            RendererModel.visible = bl3 && bl2;
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
    public void prepareMobModel(T horseBaseEntity, float f, float g, float h) {
        super.prepareMobModel(horseBaseEntity, f, g, h);
        float i = this.method_2790(horseBaseEntity.yBodyRotO, horseBaseEntity.yBodyRot, h);
        float j = this.method_2790(horseBaseEntity.yHeadRotO, horseBaseEntity.yHeadRot, h);
        float k = MathHelper.lerp(h, horseBaseEntity.xRotO, horseBaseEntity.xRot);
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
        float r = (float) horseBaseEntity.tickCount + h;
        this.field_3307.y = 4.0F;
        this.field_3307.z = -12.0F;
        this.field_3305.xRot = 0.0F;
        this.field_3307.xRot = ((float) Math.PI / 6F) + m;
        this.field_3307.yRot = l * ((float) Math.PI / 180F);
        float s = horseBaseEntity.isInWater() ? 0.2F : 1.0F;
        float t = MathHelper.cos(s * f * 0.6662F + (float) Math.PI);
        float u = t * 0.8F * g;
        float v = (1.0F - Math.max(o, n)) * (((float) Math.PI / 6F) + m + q * MathHelper.sin(r) * 0.05F);
        this.field_3307.xRot = o * (0.2617994F + m) + n * (2.1816616F + MathHelper.sin(r) * 0.05F) + v;
        this.field_3307.yRot = o * l * ((float) Math.PI / 180F) + (1.0F - Math.max(o, n)) * this.field_3307.yRot;
        this.field_3307.y = o * -4.0F + n * 11.0F + (1.0F - Math.max(o, n)) * this.field_3307.y;
        this.field_3307.z = o * -4.0F + n * -12.0F + (1.0F - Math.max(o, n)) * this.field_3307.z;
        this.field_3305.xRot = o * (-(float) Math.PI / 4F) + p * this.field_3305.xRot;
        float w = 0.2617994F * o;
        float x = MathHelper.cos(r * 0.6F + (float) Math.PI);
        this.field_3302.y = 2.0F * o + 14.0F * p;
        this.field_3302.z = -6.0F * o - 10.0F * p;
        this.field_3308.y = this.field_3302.y;
        this.field_3308.z = this.field_3302.z;
        float y = ((-(float) Math.PI / 3F) + x) * o + u * p;
        float z = ((-(float) Math.PI / 3F) - x) * o - u * p;
        this.field_3306.xRot = w - t * 0.5F * g * p;
        this.field_3303.xRot = w + t * 0.5F * g * p;
        this.field_3302.xRot = y;
        this.field_3308.xRot = z;
        this.field_3300.xRot = ((float) Math.PI / 6F) + g * 0.75F;
        this.field_3300.y = -5.0F + g;
        this.field_3300.z = 2.0F + g * 2.0F;
        if (bl) {
            this.field_3300.yRot = MathHelper.cos(r * 0.7F);
        } else {
            this.field_3300.yRot = 0.0F;
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
