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

public class LayerPhantomEyes implements LayerRenderer<ClientPhantom> {
    private static final ResourceLocation field_204248_a = new ResourceLocation("textures/entity/phantom_eyes.png");
    private final ClientPhantomRenderer phantomRenderer;

    public LayerPhantomEyes(ClientPhantomRenderer p_i48888_1_) {
        this.phantomRenderer = p_i48888_1_;
    }

    @Override
    public void doRenderLayer(ClientPhantom p_doRenderLayer_1_, float p_doRenderLayer_2_, float p_doRenderLayer_3_, float p_doRenderLayer_4_, float p_doRenderLayer_5_, float p_doRenderLayer_6_, float p_doRenderLayer_7_, float p_doRenderLayer_8_) {
        this.phantomRenderer.bindTexture(field_204248_a);
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.blendFunc(SourceFactor.ONE, DestFactor.ONE);
        GlStateManager.disableLighting();
        GlStateManager.depthMask(!p_doRenderLayer_1_.isInvisible());
        int lvt_9_1_ = 61680;
        int lvt_10_1_ = 61680;
        int lvt_11_1_ = 0;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 61680.0F, 0.0F);
        GlStateManager.enableLighting();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
        this.phantomRenderer.getMainModel().render(p_doRenderLayer_1_, p_doRenderLayer_2_, p_doRenderLayer_3_, p_doRenderLayer_5_, p_doRenderLayer_6_, p_doRenderLayer_7_, p_doRenderLayer_8_);
        Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
        this.phantomRenderer.setLightmap(p_doRenderLayer_1_);
        GlStateManager.depthMask(true);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
    }

    public boolean shouldCombineTextures() {
        return false;
    }
}
