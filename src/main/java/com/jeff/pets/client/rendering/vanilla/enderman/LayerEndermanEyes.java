package com.jeff.pets.client.rendering.vanilla.enderman;

import net.minecraft.entity.EntityLivingBase;
import com.jeff.pets.mob.vanilla.neutral.ClientEnderman;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;



public class LayerEndermanEyes implements LayerRenderer {
    private static final ResourceLocation RES_ENDERMAN_EYES = new ResourceLocation("textures/entity/enderman/enderman_eyes.png");
    private final ClientEndermanRenderer endermanRenderer;

    public LayerEndermanEyes(ClientEndermanRenderer endermanRendererIn) {
        this.endermanRenderer = endermanRendererIn;
    }

    public void render(EntityLivingBase __e, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        ClientEnderman entitylivingbaseIn = (ClientEnderman) __e;
        this.endermanRenderer.bindTexture(RES_ENDERMAN_EYES);
        GlStateManager.enableBlend();
        GlStateManager.disableAlphaTest();
        GlStateManager.blendFunc(GL11.GL_ONE, GL11.GL_ONE);
        GlStateManager.disableLighting();
        GlStateManager.depthMask(!__e.isInvisible());
        int i = 61680;
        int j = 61680;
        int k = 0;
        OpenGlHelper.glMultiTexCoord2f(OpenGlHelper.GL_TEXTURE1, 61680.0F, 0.0F);
        GlStateManager.enableLighting();
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.endermanRenderer.getMainModel().render(__e, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.endermanRenderer.setLightmap(entitylivingbaseIn, ageInTicks);
        GlStateManager.depthMask(true);
        GlStateManager.disableBlend();
        GlStateManager.enableAlphaTest();
    }

    public boolean shouldCombineTextures() {
        return false;
    }
}