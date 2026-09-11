package com.jeff.pets.client.rendering.vanilla.enderman;

import com.jeff.pets.mob.vanilla.neutral.ClientEnderman;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerEndermanEyes implements LayerRenderer<ClientEnderman> {
    private static final ResourceLocation RES_ENDERMAN_EYES = new ResourceLocation("textures/entity/enderman/enderman_eyes.png");
    private final ClientEndermanRenderer endermanRenderer;

    public LayerEndermanEyes(ClientEndermanRenderer endermanRendererIn) {
        this.endermanRenderer = endermanRendererIn;
    }

    public void render(ClientEnderman entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.endermanRenderer.bindTexture(RES_ENDERMAN_EYES);
        GlStateManager.enableBlend();
        GlStateManager.disableAlphaTest();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
        GlStateManager.disableLighting();
        GlStateManager.depthMask(!entitylivingbaseIn.isInvisible());
        int i = 61680;
        int j = 61680;
        int k = 0;
        OpenGlHelper.glMultiTexCoord2f(OpenGlHelper.GL_TEXTURE1, 61680.0F, 0.0F);
        GlStateManager.enableLighting();
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.endermanRenderer.getMainModel().render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.endermanRenderer.setLightmap(entitylivingbaseIn, ageInTicks);
        GlStateManager.depthMask(true);
        GlStateManager.disableBlend();
        GlStateManager.enableAlphaTest();
    }

    public boolean shouldCombineTextures() {
        return false;
    }
}