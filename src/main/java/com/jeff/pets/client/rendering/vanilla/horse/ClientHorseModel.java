package com.jeff.pets.client.rendering.vanilla.horse;

import com.jeff.pets.client.Math2;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.model.ModelBase;
import net.minecraft.client.renderer.entity.model.ModelRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class ClientHorseModel extends ModelBase {
    protected final ModelRenderer field_3305;
    protected final ModelRenderer field_3307;
    private final ModelRenderer field_3306;
    private final ModelRenderer field_3303;
    private final ModelRenderer field_3302;
    private final ModelRenderer field_3308;
    private final ModelRenderer field_3300;
    private final ModelRenderer[] field_3304;
    private final ModelRenderer[] field_3301;

    public ClientHorseModel(float f) {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.field_3305 = new ModelRenderer(this, 0, 32);
        this.field_3305.addBox(-5.0F, -8.0F, -17.0F, 10, 10, 22, 0.05F);
        this.field_3305.setRotationPoint(0.0F, 11.0F, 5.0F);
        this.field_3307 = new ModelRenderer(this, 0, 35);
        this.field_3307.addBox(-2.05F, -6.0F, -2.0F, 4, 12, 7);
        this.field_3307.rotateAngleX = ((float) Math.PI / 6F);
        ModelRenderer ModelRenderer = new ModelRenderer(this, 0, 13);
        ModelRenderer.addBox(-3.0F, -11.0F, -2.0F, 6, 5, 7, f);
        ModelRenderer ModelRenderer2 = new ModelRenderer(this, 56, 36);
        ModelRenderer2.addBox(-1.0F, -11.0F, 5.01F, 2, 16, 2, f);
        ModelRenderer ModelRenderer3 = new ModelRenderer(this, 0, 25);
        ModelRenderer3.addBox(-2.0F, -11.0F, -7.0F, 4, 5, 5, f);
        this.field_3307.addChild(ModelRenderer);
        this.field_3307.addChild(ModelRenderer2);
        this.field_3307.addChild(ModelRenderer3);
        this.method_2789(this.field_3307);
        this.field_3306 = new ModelRenderer(this, 48, 21);
        this.field_3306.mirror = true;
        this.field_3306.addBox(-3.0F, -1.01F, -1.0F, 4, 11, 4, f);
        this.field_3306.setRotationPoint(4.0F, 14.0F, 7.0F);
        this.field_3303 = new ModelRenderer(this, 48, 21);
        this.field_3303.addBox(-1.0F, -1.01F, -1.0F, 4, 11, 4, f);
        this.field_3303.setRotationPoint(-4.0F, 14.0F, 7.0F);
        this.field_3302 = new ModelRenderer(this, 48, 21);
        this.field_3302.mirror = true;
        this.field_3302.addBox(-3.0F, -1.01F, -1.9F, 4, 11, 4, f);
        this.field_3302.setRotationPoint(4.0F, 6.0F, -12.0F);
        this.field_3308 = new ModelRenderer(this, 48, 21);
        this.field_3308.addBox(-1.0F, -1.01F, -1.9F, 4, 11, 4, f);
        this.field_3308.setRotationPoint(-4.0F, 6.0F, -12.0F);
        this.field_3300 = new ModelRenderer(this, 42, 36);
        this.field_3300.addBox(-1.5F, 0.0F, 0.0F, 3, 14, 4, f);
        this.field_3300.setRotationPoint(0.0F, -5.0F, 2.0F);
        this.field_3300.rotateAngleX = ((float) Math.PI / 6F);
        this.field_3305.addChild(this.field_3300);
        ModelRenderer ModelRenderer4 = new ModelRenderer(this, 26, 0);
        ModelRenderer4.addBox(-5.0F, -8.0F, -9.0F, 10, 9, 9, 0.5F);
        this.field_3305.addChild(ModelRenderer4);
        ModelRenderer ModelRenderer5 = new ModelRenderer(this, 29, 5);
        ModelRenderer5.addBox(2.0F, -9.0F, -6.0F, 1, 2, 2, f);
        this.field_3307.addChild(ModelRenderer5);
        ModelRenderer ModelRenderer6 = new ModelRenderer(this, 29, 5);
        ModelRenderer6.addBox(-3.0F, -9.0F, -6.0F, 1, 2, 2, f);
        this.field_3307.addChild(ModelRenderer6);
        ModelRenderer ModelRenderer7 = new ModelRenderer(this, 32, 2);
        ModelRenderer7.addBox(3.1F, -6.0F, -8.0F, 0, 3, 16, f);
        ModelRenderer7.rotateAngleX = (-(float) Math.PI / 6F);
        this.field_3307.addChild(ModelRenderer7);
        ModelRenderer ModelRenderer8 = new ModelRenderer(this, 32, 2);
        ModelRenderer8.addBox(-3.1F, -6.0F, -8.0F, 0, 3, 16, f);
        ModelRenderer8.rotateAngleX = (-(float) Math.PI / 6F);
        this.field_3307.addChild(ModelRenderer8);
        ModelRenderer ModelRenderer9 = new ModelRenderer(this, 1, 1);
        ModelRenderer9.addBox(-3.0F, -11.0F, -1.9F, 6, 5, 6, 0.2F);
        this.field_3307.addChild(ModelRenderer9);
        ModelRenderer ModelRenderer10 = new ModelRenderer(this, 19, 0);
        ModelRenderer10.addBox(-2.0F, -11.0F, -4.0F, 4, 5, 2, 0.2F);
        this.field_3307.addChild(ModelRenderer10);
        this.field_3304 = new ModelRenderer[]{ModelRenderer4, ModelRenderer5, ModelRenderer6, ModelRenderer9, ModelRenderer10};
        this.field_3301 = new ModelRenderer[]{ModelRenderer7, ModelRenderer8};
    }

    protected void method_2789(ModelRenderer ModelRenderer) {
        ModelRenderer ModelRenderer2 = new ModelRenderer(this, 19, 16);
        ModelRenderer2.addBox(0.55F, -13.0F, 4.0F, 2, 3, 1, -0.001F);
        ModelRenderer ModelRenderer3 = new ModelRenderer(this, 19, 16);
        ModelRenderer3.addBox(-2.55F, -13.0F, 4.0F, 2, 3, 1, -0.001F);
        ModelRenderer.addChild(ModelRenderer2);
        ModelRenderer.addChild(ModelRenderer3);
    }

    @Override
    public void render(net.minecraft.entity.Entity horseBaseEntity, float f, float g, float h, float i, float j, float k) {
        EntityLivingBase horseEntity = (EntityLivingBase) horseBaseEntity;
        boolean bl = horseEntity.isChild();
        float l = 1.0F;
        boolean bl2 = false;
        boolean bl3 = horseEntity.isBeingRidden();

        for (ModelRenderer ModelRenderer : this.field_3304) {
            ModelRenderer.showModel = bl2;
        }

        for (ModelRenderer ModelRenderer : this.field_3301) {
            ModelRenderer.showModel = bl3 && bl2;
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
    public void setLivingAnimations(EntityLivingBase horseBaseEntity, float f, float g, float h) {
        super.setLivingAnimations(horseBaseEntity, f, g, h);
        float i = this.method_2790(horseBaseEntity.prevRenderYawOffset, horseBaseEntity.renderYawOffset, h);
        float j = this.method_2790(horseBaseEntity.prevRotationYawHead, horseBaseEntity.rotationYawHead, h);
        float k = Math2.lerp(h, horseBaseEntity.prevRotationPitch, horseBaseEntity.rotationPitch);
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
        float r = (float) horseBaseEntity.ticksExisted + h;
        this.field_3307.rotationPointY = 4.0F;
        this.field_3307.rotationPointZ = -12.0F;
        this.field_3305.rotateAngleX = 0.0F;
        this.field_3307.rotateAngleX = ((float) Math.PI / 6F) + m;
        this.field_3307.rotateAngleY = l * ((float) Math.PI / 180F);
        float s = horseBaseEntity.isInWater() ? 0.2F : 1.0F;
        float t = MathHelper.cos(s * f * 0.6662F + (float) Math.PI);
        float u = t * 0.8F * g;
        float v = (1.0F - Math.max(o, n)) * (((float) Math.PI / 6F) + m + q * MathHelper.sin(r) * 0.05F);
        this.field_3307.rotateAngleX = o * (0.2617994F + m) + n * (2.1816616F + MathHelper.sin(r) * 0.05F) + v;
        this.field_3307.rotateAngleY = o * l * ((float) Math.PI / 180F) + (1.0F - Math.max(o, n)) * this.field_3307.rotateAngleY;
        this.field_3307.rotationPointY = o * -4.0F + n * 11.0F + (1.0F - Math.max(o, n)) * this.field_3307.rotationPointY;
        this.field_3307.rotationPointZ = o * -4.0F + n * -12.0F + (1.0F - Math.max(o, n)) * this.field_3307.rotationPointZ;
        this.field_3305.rotateAngleX = o * (-(float) Math.PI / 4F) + p * this.field_3305.rotateAngleX;
        float w = 0.2617994F * o;
        float x = MathHelper.cos(r * 0.6F + (float) Math.PI);
        this.field_3302.rotationPointY = 2.0F * o + 14.0F * p;
        this.field_3302.rotationPointZ = -6.0F * o - 10.0F * p;
        this.field_3308.rotationPointY = this.field_3302.rotationPointY;
        this.field_3308.rotationPointZ = this.field_3302.rotationPointZ;
        float y = ((-(float) Math.PI / 3F) + x) * o + u * p;
        float z = ((-(float) Math.PI / 3F) - x) * o - u * p;
        this.field_3306.rotateAngleX = w - t * 0.5F * g * p;
        this.field_3303.rotateAngleX = w + t * 0.5F * g * p;
        this.field_3302.rotateAngleX = y;
        this.field_3308.rotateAngleX = z;
        this.field_3300.rotateAngleX = ((float) Math.PI / 6F) + g * 0.75F;
        this.field_3300.rotationPointY = -5.0F + g;
        this.field_3300.rotationPointZ = 2.0F + g * 2.0F;
        if (bl) {
            this.field_3300.rotateAngleY = MathHelper.cos(r * 0.7F);
        } else {
            this.field_3300.rotateAngleY = 0.0F;
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
