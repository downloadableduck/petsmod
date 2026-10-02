package com.jeff.pets.client.rendering.vanilla.creeper;

import net.minecraft.entity.EntityLivingBase;
import com.jeff.pets.mob.vanilla.hostile.ClientCreeper;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCreeperChargeLayer implements LayerRenderer {
    private static final ResourceLocation SKIN = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
    private final RenderLiving renderer;
    private final ModelCreeper creeperModel;

    public ClientCreeperChargeLayer(RenderLiving renderLayerParent) {
        this.renderer = renderLayerParent;
        this.creeperModel = new ModelCreeper(0.25F);
    }

    @Override
    public void render(EntityLivingBase __e, float f, float g, float h, float i, float j, float k, float l) {
        ClientCreeper creeperEntity = (ClientCreeper) __e;
        if (CONFIG.creeperSkin.equals("charged")) {
            boolean bl = __e.isInvisible();
            GlStateManager.depthMask(!bl);
            this.renderer.bindTexture(SKIN);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            float m = (float) __e.ticksExisted + h;
            GlStateManager.translatef(m * 0.01F, m * 0.01F, 0.0F);
            GlStateManager.matrixMode(5888);
            GlStateManager.enableBlend();
            float n = 0.5F;
            GlStateManager.color4f(0.5F, 0.5F, 0.5F, 1.0F);
            GlStateManager.disableLighting();
            GlStateManager.blendFunc(GL11.GL_ONE, GL11.GL_ONE);
            this.creeperModel.setModelAttributes(this.renderer.getMainModel());
            this.creeperModel.render(__e, f, g, i, j, k, l);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            GlStateManager.matrixMode(5888);
            GlStateManager.enableLighting();
            GlStateManager.disableBlend();
            GlStateManager.depthMask(true);
        }
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
