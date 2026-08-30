package com.jeff.pets.client.rendering.vanilla.llama;

import com.google.common.collect.ImmutableList;
import com.jeff.pets.mob.vanilla.neutral.ClientLlama;
import net.minecraft.client.render.model.Model;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.util.math.MathHelper;

public class ClientLlamaModel extends Model {
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart leg0;
    private final ModelPart backRightLeg;
    private final ModelPart backLeftLeg;
    private final ModelPart frontRightLeg;
    private final ModelPart chest1;
    private final ModelPart chest2;
    private final boolean child = false;

    public ClientLlamaModel(float f) {
        this.textureWidth /*textureWidth*/ = 128;
        this.textureHeight /*textureHeight*/ = 64;
        this.head = new ModelPart(this, 0, 0);
        this.head.addBox(-2.0F, -14.0F, -10.0F, 4, 4, 9, f);
        this.head.setPos(0.0F, 7.0F, -6.0F);
        this.head.setTextureCoords(0, 14).addBox(-4.0F, -16.0F, -6.0F, 8, 18, 6, f);
        this.head.setTextureCoords(17, 0).addBox(-4.0F, -19.0F, -4.0F, 3, 3, 2, f);
        this.head.setTextureCoords(17, 0).addBox(1.0F, -19.0F, -4.0F, 3, 3, 2, f);
        this.body = new ModelPart(this, 29, 0);
        this.body.addBox(-6.0F, -10.0F, -7.0F, 12, 18, 10, f);
        this.body.setPos(0.0F, 5.0F, 2.0F);
        this.chest1 = new ModelPart(this, 45, 28);
        this.chest1.addBox(-3.0F, 0.0F, 0.0F, 8, 8, 3, f);
        this.chest1.setPos(-8.5F, 3.0F, 3.0F);
        this.chest1.rotationY = ((float) Math.PI / 2F);
        this.chest2 = new ModelPart(this, 45, 41);
        this.chest2.addBox(-3.0F, 0.0F, 0.0F, 8, 8, 3, f);
        this.chest2.setPos(5.5F, 3.0F, 3.0F);
        this.chest2.rotationY = ((float) Math.PI / 2F);
        int i = 4;
        int j = 14;
        this.leg0 = new ModelPart(this, 29, 29);
        this.leg0.addBox(-2.0F, 0.0F, -2.0F, 4, 14, 4, f);
        this.leg0.setPos(-2.5F, 10.0F, 6.0F);
        this.backRightLeg = new ModelPart(this, 29, 29);
        this.backRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 14, 4, f);
        this.backRightLeg.setPos(2.5F, 10.0F, 6.0F);
        this.backLeftLeg = new ModelPart(this, 29, 29);
        this.backLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 14, 4, f);
        this.backLeftLeg.setPos(-2.5F, 10.0F, -4.0F);
        this.frontRightLeg = new ModelPart(this, 29, 29);
        this.frontRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 14, 4, f);
        this.frontRightLeg.setPos(2.5F, 10.0F, -4.0F);
        --this.leg0.x;
        ++this.backRightLeg.x;
        ModelPart var10000 = this.leg0;
        var10000.z += 0.0F;
        var10000 = this.backRightLeg;
        var10000.z += 0.0F;
        --this.backLeftLeg.x;
        ++this.frontRightLeg.x;
        --this.backLeftLeg.z;
        --this.frontRightLeg.z;
    }

    public void setupAnimation(float f, float g, float h, float i, float j, float s, net.minecraft.entity.Entity entity) {
        ClientLlama abstractChestedHorse = (ClientLlama) entity;
        this.head.rotationX = j * ((float) Math.PI / 180F);
        this.head.rotationY = i * ((float) Math.PI / 180F);
        this.body.rotationX = ((float) Math.PI / 2F);
        this.leg0.rotationX = MathHelper.cos(f * 0.6662F) * 1.4F * g;
        this.backRightLeg.rotationX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
        this.backLeftLeg.rotationX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * g;
        this.frontRightLeg.rotationX = MathHelper.cos(f * 0.6662F) * 1.4F * g;
        boolean bl = !abstractChestedHorse.isBaby();
        this.chest1.visible = bl;
        this.chest2.visible = bl;
    }

    @Override
    public void render(net.minecraft.entity.Entity entity, float i, float j, float f, float g, float h, float k) {
        if (this.child) {
            float l = 2.0F;
            net.minecraft.client.render.platform.GlStateManager.pushMatrix();
            float m = 0.7F;
            net.minecraft.client.render.platform.GlStateManager.scalef(0.71428573F, 0.64935064F, 0.7936508F);
            net.minecraft.client.render.platform.GlStateManager.translatef(0.0F, 1.3125F, 0.22F);
            this.head.render(k);
            net.minecraft.client.render.platform.GlStateManager.popMatrix();
            net.minecraft.client.render.platform.GlStateManager.pushMatrix();
            float n = 1.1F;
            net.minecraft.client.render.platform.GlStateManager.scalef(0.625F, 0.45454544F, 0.45454544F);
            net.minecraft.client.render.platform.GlStateManager.translatef(0.0F, 2.0625F, 0.0F);
            this.body.render(k);
            net.minecraft.client.render.platform.GlStateManager.popMatrix();
            net.minecraft.client.render.platform.GlStateManager.pushMatrix();
            net.minecraft.client.render.platform.GlStateManager.scalef(0.45454544F, 0.41322312F, 0.45454544F);
            net.minecraft.client.render.platform.GlStateManager.translatef(0.0F, 2.0625F, 0.0F);
            ImmutableList.of(this.leg0, this.backRightLeg, this.backLeftLeg, this.frontRightLeg, this.chest1, this.chest2).forEach((ModelPart) -> ModelPart.render(k));
            net.minecraft.client.render.platform.GlStateManager.popMatrix();
        } else {
            ImmutableList.of(this.head, this.body, this.leg0, this.backRightLeg, this.backLeftLeg, this.frontRightLeg, this.chest1, this.chest2).forEach((ModelPart) -> ModelPart.render(k));
        }

    }
}
