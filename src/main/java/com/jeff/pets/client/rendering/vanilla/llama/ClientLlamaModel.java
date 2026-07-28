package com.jeff.pets.client.rendering.vanilla.llama;

import com.google.common.collect.ImmutableList;
import com.jeff.pets.mob.vanilla.neutral.ClientLlama;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.LlamaModel;
import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;

public class ClientLlamaModel extends QuadrupedModel<ClientLlama> {
    private final RendererModel chest1;
    private final RendererModel chest2;

    public ClientLlamaModel(float p_i47226_1_) {
        super(15, p_i47226_1_);
        this.texWidth = 128;
        this.texHeight = 64;
        this.head = new RendererModel(this, 0, 0);
        this.head.addBox(-2.0F, -14.0F, -10.0F, 4, 4, 9, p_i47226_1_);
        this.head.setPos(0.0F, 7.0F, -6.0F);
        this.head.texOffs(0, 14).addBox(-4.0F, -16.0F, -6.0F, 8, 18, 6, p_i47226_1_);
        this.head.texOffs(17, 0).addBox(-4.0F, -19.0F, -4.0F, 3, 3, 2, p_i47226_1_);
        this.head.texOffs(17, 0).addBox(1.0F, -19.0F, -4.0F, 3, 3, 2, p_i47226_1_);
        this.body = new RendererModel(this, 29, 0);
        this.body.addBox(-6.0F, -10.0F, -7.0F, 12, 18, 10, p_i47226_1_);
        this.body.setPos(0.0F, 5.0F, 2.0F);
        this.chest1 = new RendererModel(this, 45, 28);
        this.chest1.addBox(-3.0F, 0.0F, 0.0F, 8, 8, 3, p_i47226_1_);
        this.chest1.setPos(-8.5F, 3.0F, 3.0F);
        this.chest1.yRot = ((float)Math.PI / 2F);
        this.chest2 = new RendererModel(this, 45, 41);
        this.chest2.addBox(-3.0F, 0.0F, 0.0F, 8, 8, 3, p_i47226_1_);
        this.chest2.setPos(5.5F, 3.0F, 3.0F);
        this.chest2.yRot = ((float)Math.PI / 2F);
        int i = 4;
        int j = 14;
        this.leg0 = new RendererModel(this, 29, 29);
        this.leg0.addBox(-2.0F, 0.0F, -2.0F, 4, 14, 4, p_i47226_1_);
        this.leg0.setPos(-2.5F, 10.0F, 6.0F);
        this.leg1 = new RendererModel(this, 29, 29);
        this.leg1.addBox(-2.0F, 0.0F, -2.0F, 4, 14, 4, p_i47226_1_);
        this.leg1.setPos(2.5F, 10.0F, 6.0F);
        this.leg2 = new RendererModel(this, 29, 29);
        this.leg2.addBox(-2.0F, 0.0F, -2.0F, 4, 14, 4, p_i47226_1_);
        this.leg2.setPos(-2.5F, 10.0F, -4.0F);
        this.leg3 = new RendererModel(this, 29, 29);
        this.leg3.addBox(-2.0F, 0.0F, -2.0F, 4, 14, 4, p_i47226_1_);
        this.leg3.setPos(2.5F, 10.0F, -4.0F);
        --this.leg0.x;
        ++this.leg1.x;
        this.leg0.z += 0.0F;
        this.leg1.z += 0.0F;
        --this.leg2.x;
        ++this.leg3.x;
        --this.leg2.z;
        --this.leg3.z;
        this.zHeadOffs += 2.0F;
    }

    @Override
    public void render(ClientLlama p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
        boolean flag = false;
        this.setupAnim(p_78088_1_, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_);
        if (this.young) {
            float f = 2.0F;
            GlStateManager.pushMatrix();
            GlStateManager.translatef(0.0F, this.yHeadOffs * p_78088_7_, this.zHeadOffs * p_78088_7_);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            float f1 = 0.7F;
            GlStateManager.scalef(0.71428573F, 0.64935064F, 0.7936508F);
            GlStateManager.translatef(0.0F, 21.0F * p_78088_7_, 0.22F);
            this.head.render(p_78088_7_);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            float f2 = 1.1F;
            GlStateManager.scalef(0.625F, 0.45454544F, 0.45454544F);
            GlStateManager.translatef(0.0F, 33.0F * p_78088_7_, 0.0F);
            this.body.render(p_78088_7_);
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scalef(0.45454544F, 0.41322312F, 0.45454544F);
            GlStateManager.translatef(0.0F, 33.0F * p_78088_7_, 0.0F);
            this.leg0.render(p_78088_7_);
            this.leg1.render(p_78088_7_);
            this.leg2.render(p_78088_7_);
            this.leg3.render(p_78088_7_);
            GlStateManager.popMatrix();
        } else {
            this.head.render(p_78088_7_);
            this.body.render(p_78088_7_);
            this.leg0.render(p_78088_7_);
            this.leg1.render(p_78088_7_);
            this.leg2.render(p_78088_7_);
            this.leg3.render(p_78088_7_);
        }

        if (flag) {
            this.chest1.render(p_78088_7_);
            this.chest2.render(p_78088_7_);
        }

    }
}
