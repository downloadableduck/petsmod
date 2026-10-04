package com.jeff.pets.client.rendering.vanilla.enderman;

import com.jeff.pets.client.rendering.PetLayer;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Additive glow pass for the enderman eyes. 1.7.10 has no {@code LayerRenderer} and no
 * {@code OpenGlHelper#glMultiTexCoord2f}/{@code setLightmap} lightmap API, so this uses the
 * 1.7.10 idiom of suppressing the lightmap with {@code GL11.glDisable(GL11.GL_LIGHTMAP)}
 * around an additive re-render of the main model.
 */
public class LayerEndermanEyes implements PetLayer {
    private static final ResourceLocation RES_ENDERMAN_EYES =
            new ResourceLocation("minecraft", "textures/entity/enderman/enderman_eyes.png");

    private final PetRenderer parent;

    public LayerEndermanEyes(RenderManager context, PetRenderer parent) {
        this.parent = parent;
    }

    @Override
    public void render(EntityLivingBase entity, float limbSwing, float limbSwingAmount, float ageInTicks,
                       float netHeadYaw, float headPitch, float scale) {
        GL11.glPushMatrix();
        this.parent.bindTexture(RES_ENDERMAN_EYES);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
        GL11.glDepthMask(false);
        Minecraft.getMinecraft().entityRenderer.disableLightmap(0);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.parent.getMainModel().render(entity, limbSwing, limbSwingAmount, ageInTicks,
                netHeadYaw, headPitch, scale);

        Minecraft.getMinecraft().entityRenderer.enableLightmap(0);
        GL11.glDepthMask(true);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }
}
