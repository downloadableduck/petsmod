package com.jeff.pets.client.rendering.vanilla.creeper;

import com.jeff.pets.mob.vanilla.hostile.ClientCreeper;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.CreeperModel;
import net.minecraft.util.ResourceLocation;

import javax.swing.text.LayeredHighlighter;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCreeperChargeLayer extends LayerRenderer<ClientCreeper, CreeperModel<ClientCreeper>> {
    private static final ResourceLocation SKIN = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
    public ClientCreeperChargeLayer(IEntityRenderer<ClientCreeper, CreeperModel<ClientCreeper>> featureRendererContext) {
        super(featureRendererContext);
    }

    @Override
    public void render(ClientCreeper creeperEntity, float f, float g, float h, float i, float j, float k, float l) {
        if (CONFIG.creeperSkin.equals("charged")) {
            boolean bl = creeperEntity.isInvisible();
            GlStateManager.depthMask(!bl);
            this.bindTexture(SKIN);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            float m = (float) creeperEntity.tickCount + h;
            GlStateManager.translatef(m * 0.01F, m * 0.01F, 0.0F);
            GlStateManager.matrixMode(5888);
            GlStateManager.enableBlend();
            float n = 0.5F;
            GlStateManager.color4f(0.5F, 0.5F, 0.5F, 1.0F);
            GlStateManager.disableLighting();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
            (this.getParentModel()).copyPropertiesTo(this.getParentModel());
            GameRenderer gameRenderer = Minecraft.getInstance().gameRenderer;
            gameRenderer.resetFogColor(true);
            this.getParentModel().render(creeperEntity, f, g, i, j, k, l);
            gameRenderer.resetFogColor(false);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            GlStateManager.matrixMode(5888);
            GlStateManager.enableLighting();
            GlStateManager.disableBlend();
            GlStateManager.depthMask(true);
        }
    }

    @Override
    public boolean colorsOnDamage() {
        return false;
    }
}
