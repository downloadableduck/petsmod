package com.jeff.pets.client.rendering.vanilla.enderman;

import com.jeff.pets.mob.vanilla.neutral.ClientEnderman;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.util.ResourceLocation;

public class LayerEndermanEyes implements LayerRenderer<ClientEnderman> {
    private static final ResourceLocation RES_ENDERMAN_EYES = new ResourceLocation("textures/entity/enderman/enderman_eyes.png");
    private final ClientEndermanRenderer endermanRenderer;

    public LayerEndermanEyes(ClientEndermanRenderer endermanRendererIn) {
        this.endermanRenderer = endermanRendererIn;
    }

    @Override
    public void doRenderLayer(ClientEnderman p_doRenderLayer_1_, float p_doRenderLayer_2_, float p_doRenderLayer_3_, float p_doRenderLayer_4_, float p_doRenderLayer_5_, float p_doRenderLayer_6_, float p_doRenderLayer_7_, float p_doRenderLayer_8_) {
        this.endermanRenderer.bindTexture(RES_ENDERMAN_EYES);
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
        GlStateManager.disableLighting();
        GlStateManager.depthMask(!p_doRenderLayer_1_.isInvisible());
        int lvt_9_1_ = 61680;
        int lvt_10_1_ = 61680;
        int lvt_11_1_ = 0;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 61680.0F, 0.0F);
        GlStateManager.enableLighting();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
        this.endermanRenderer.getMainModel().render(p_doRenderLayer_1_, p_doRenderLayer_2_, p_doRenderLayer_3_, p_doRenderLayer_5_, p_doRenderLayer_6_, p_doRenderLayer_7_, p_doRenderLayer_8_);
        Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
        this.endermanRenderer.setLightmap(p_doRenderLayer_1_);
        GlStateManager.depthMask(true);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
    }

    public boolean shouldCombineTextures() {
        return false;
    }
}