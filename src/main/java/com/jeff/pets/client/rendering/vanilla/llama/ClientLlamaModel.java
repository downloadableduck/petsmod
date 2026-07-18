package com.jeff.pets.client.rendering.vanilla.llama;

import com.google.common.collect.ImmutableList;
import com.jeff.pets.mob.vanilla.neutral.ClientLlama;
import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.math.MathHelper;

public class ClientLlamaModel extends EntityModel<ClientLlama> {
    private final Cuboid head;
    private final Cuboid body;
    private final Cuboid leg0;
    private final Cuboid leg1;
    private final Cuboid leg2;
    private final Cuboid leg3;
    private final Cuboid chest1;
    private final Cuboid chest2;
    private final boolean child = false;

    public ClientLlamaModel(float f) {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.head = new Cuboid(this, 0, 0);
        this.head.addBox(-2.0F, -14.0F, -10.0F, 4, 4, 9, f);
        this.head.setRotationPoint(0.0F, 7.0F, -6.0F);
        this.head.setTextureOffset(0, 14).addBox(-4.0F, -16.0F, -6.0F, 8, 18, 6, f);
        this.head.setTextureOffset(17, 0).addBox(-4.0F, -19.0F, -4.0F, 3, 3, 2, f);
        this.head.setTextureOffset(17, 0).addBox(1.0F, -19.0F, -4.0F, 3, 3, 2, f);
        this.body = new Cuboid(this, 29, 0);
        this.body.addBox(-6.0F, -10.0F, -7.0F, 12, 18, 10, f);
        this.body.setRotationPoint(0.0F, 5.0F, 2.0F);
        this.chest1 = new Cuboid(this, 45, 28);
        this.chest1.addBox(-3.0F, 0.0F, 0.0F, 8, 8, 3, f);
        this.chest1.setRotationPoint(-8.5F, 3.0F, 3.0F);
        this.chest1.yaw = ((float) Math.PI / 2F);
        this.chest2 = new Cuboid(this, 45, 41);
        this.chest2.addBox(-3.0F, 0.0F, 0.0F, 8, 8, 3, f);
        this.chest2.setRotationPoint(5.5F, 3.0F, 3.0F);
        this.chest2.yaw = ((float) Math.PI / 2F);
        int i = 4;
        int j = 14;
        this.leg0 = new Cuboid(this, 29, 29);
        this.leg0.addBox(-2.0F, 0.0F, -2.0F, 4, 14, 4, f);
        this.leg0.setRotationPoint(-2.5F, 10.0F, 6.0F);
        this.leg1 = new Cuboid(this, 29, 29);
        this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 14, 4, f);
        this.leg1.setRotationPoint(2.5F, 10.0F, 6.0F);
        this.leg2 = new Cuboid(this, 29, 29);
        this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 14, 4, f);
        this.leg2.setRotationPoint(-2.5F, 10.0F, -4.0F);
        this.leg3 = new Cuboid(this, 29, 29);
        this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 14, 4, f);
        this.leg3.setRotationPoint(2.5F, 10.0F, -4.0F);
        --this.leg0.rotationPointX;
        ++this.leg1.rotationPointX;
        Cuboid var10000 = this.leg0;
        var10000.rotationPointZ += 0.0F;
        var10000 = this.leg1;
        var10000.rotationPointZ += 0.0F;
        --this.leg2.rotationPointX;
        ++this.leg3.rotationPointX;
        --this.leg2.rotationPointZ;
        --this.leg3.rotationPointZ;
    }

    public void setAngles(ClientLlama abstractChestedHorse, float f, float g, float h, float i, float j, float s) {
        this.head.pitch = j * ((float) Math.PI / 180F);
        this.head.yaw = i * ((float) Math.PI / 180F);
        this.body.pitch = ((float) Math.PI / 2F);
        this.leg0.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * g;
        this.leg1.pitch = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
        this.leg2.pitch = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
        this.leg3.pitch = MathHelper.cos(f * 0.6662F) * 1.4F * g;
        boolean bl = !abstractChestedHorse.isBaby();
        this.chest1.visible = bl;
        this.chest2.visible = bl;
    }

    @Override
    public void render(ClientLlama llama, float i, float j, float f, float g, float h, float k) {
        if (this.child) {
            float l = 2.0F;
            com.mojang.blaze3d.platform.GlStateManager.pushMatrix();
            float m = 0.7F;
            com.mojang.blaze3d.platform.GlStateManager.scalef(0.71428573F, 0.64935064F, 0.7936508F);
            com.mojang.blaze3d.platform.GlStateManager.translatef(0.0F, 1.3125F, 0.22F);
            this.head.render(k);
            com.mojang.blaze3d.platform.GlStateManager.popMatrix();
            com.mojang.blaze3d.platform.GlStateManager.pushMatrix();
            float n = 1.1F;
            com.mojang.blaze3d.platform.GlStateManager.scalef(0.625F, 0.45454544F, 0.45454544F);
            com.mojang.blaze3d.platform.GlStateManager.translatef(0.0F, 2.0625F, 0.0F);
            this.body.render(k);
            com.mojang.blaze3d.platform.GlStateManager.popMatrix();
            com.mojang.blaze3d.platform.GlStateManager.pushMatrix();
            com.mojang.blaze3d.platform.GlStateManager.scalef(0.45454544F, 0.41322312F, 0.45454544F);
            com.mojang.blaze3d.platform.GlStateManager.translatef(0.0F, 2.0625F, 0.0F);
            ImmutableList.of(this.leg0, this.leg1, this.leg2, this.leg3, this.chest1, this.chest2).forEach((Cuboid) -> Cuboid.render(k));
            com.mojang.blaze3d.platform.GlStateManager.popMatrix();
        } else {
            ImmutableList.of(this.head, this.body, this.leg0, this.leg1, this.leg2, this.leg3, this.chest1, this.chest2).forEach((Cuboid) -> Cuboid.render(k));
        }

    }
}
