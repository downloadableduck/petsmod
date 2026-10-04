package com.jeff.pets.client.rendering.vanilla.creeper;

import com.jeff.pets.client.rendering.PetLayer;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

/**
 * Re-draws the creeper model with the charged armour texture. 1.7.10 has no
 * {@code LayerRenderer}, so this is driven from {@link PetRenderer#renderModel} and
 * re-renders the parent's main model, matching vanilla 1.7.10's
 * {@code RenderCreeper#renderPoweredCreeper}.
 */
public class ClientCreeperChargeLayer implements PetLayer {
    private static final ResourceLocation SKIN = new ResourceLocation("minecraft", "textures/entity/creeper/creeper_armor.png");

    private final PetRenderer parent;

    public ClientCreeperChargeLayer(RenderManager context, PetRenderer parent) {
        this.parent = parent;
    }

    @Override
    public void render(EntityLivingBase creeperEntity, float limbSwing, float limbSwingAmount, float ageInTicks,
                       float netHeadYaw, float headPitch, float scale) {
        if (!Objects.equals(CONFIG.creeperSkin, "charged")) {
            return;
        }

        GL11.glPushMatrix();
        float bob = creeperEntity.ticksExisted + ageInTicks;
        GL11.glMatrixMode(GL11.GL_TEXTURE);
        GL11.glTranslatef(bob * 0.01F, bob * 0.01F, 0.0F);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
        GL11.glColor4f(0.5F, 0.5F, 0.5F, 1.0F);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDepthMask(false);

        this.parent.bindTexture(SKIN);
        this.parent.getMainModel().render(creeperEntity, limbSwing, limbSwingAmount, ageInTicks,
                netHeadYaw, headPitch, scale);

        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glMatrixMode(GL11.GL_TEXTURE);
        GL11.glLoadIdentity();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glPopMatrix();
    }
}
