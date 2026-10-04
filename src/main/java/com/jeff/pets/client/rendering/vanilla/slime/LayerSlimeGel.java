package com.jeff.pets.client.rendering.vanilla.slime;

import com.jeff.pets.client.rendering.PetLayer;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.opengl.GL11;

/**
 * Translucent gel pass over the slime model. 1.7.10 has no {@code LayerRenderer}; vanilla
 * 1.7.10's {@code RenderSlime#doRender} simply re-renders the same model with blending on,
 * so this does the same against the parent's main model.
 */
public class LayerSlimeGel implements PetLayer {
    private final PetRenderer parent;

    public LayerSlimeGel(RenderManager context, PetRenderer parent) {
        this.parent = parent;
    }

    @Override
    public void render(EntityLivingBase slime, float limbSwing, float limbSwingAmount, float ageInTicks,
                       float netHeadYaw, float headPitch, float scale) {
        if (slime.isInvisible()) {
            return;
        }

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(GL11.GL_NORMALIZE);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        this.parent.getMainModel().render(slime, limbSwing, limbSwingAmount, ageInTicks,
                netHeadYaw, headPitch, scale);

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_NORMALIZE);
    }
}
