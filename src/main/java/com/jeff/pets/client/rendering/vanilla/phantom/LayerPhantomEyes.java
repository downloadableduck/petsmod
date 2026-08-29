//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.phantom;

import com.jeff.pets.mob.vanilla.hostile.ClientPhantom;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.entity.RenderPhantom;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.monster.EntityPhantom;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LayerPhantomEyes implements LayerRenderer<ClientPhantom> {
    private static final ResourceLocation field_204248_a = new ResourceLocation("textures/entity/phantom_eyes.png");
    private final ClientPhantomRenderer phantomRenderer;

    public LayerPhantomEyes(ClientPhantomRenderer p_i48888_1_) {
        this.phantomRenderer = p_i48888_1_;
    }

    public void render(ClientPhantom p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_) {
        this.phantomRenderer.bindTexture(field_204248_a);
        GlStateManager.enableBlend();
        GlStateManager.disableAlphaTest();
        GlStateManager.blendFunc(SourceFactor.ONE, DestFactor.ONE);
        GlStateManager.disableLighting();
        GlStateManager.depthMask(!p_177141_1_.isInvisible());
        int lvt_9_1_ = 61680;
        int lvt_10_1_ = 61680;
        int lvt_11_1_ = 0;
        OpenGlHelper.glMultiTexCoord2f(OpenGlHelper.GL_TEXTURE1, 61680.0F, 0.0F);
        GlStateManager.enableLighting();
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getInstance().entityRenderer.setupFogColor(true);
        this.phantomRenderer.getMainModel().render(p_177141_1_, p_177141_2_, p_177141_3_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_);
        Minecraft.getInstance().entityRenderer.setupFogColor(false);
        this.phantomRenderer.setLightmap(p_177141_1_);
        GlStateManager.depthMask(true);
        GlStateManager.disableBlend();
        GlStateManager.enableAlphaTest();
    }

    public boolean shouldCombineTextures() {
        return false;
    }
}
