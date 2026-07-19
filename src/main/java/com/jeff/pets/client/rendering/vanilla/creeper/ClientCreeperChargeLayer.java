package com.jeff.pets.client.rendering.vanilla.creeper;

import com.jeff.pets.mob.vanilla.hostile.ClientCreeper;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.entity.layer.EntityRenderLayer;
import net.minecraft.client.render.entity.layer.EntityRenderLayerParent;
import net.minecraft.client.render.model.entity.CreeperModel;
import net.minecraft.resource.Identifier;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCreeperChargeLayer extends EntityRenderLayer<ClientCreeper, CreeperModel<ClientCreeper>> {
    private static final Identifier SKIN = new Identifier("textures/entity/creeper/creeper_armor.png");
    public ClientCreeperChargeLayer(EntityRenderLayerParent<ClientCreeper, CreeperModel<ClientCreeper>> EntityRenderLayerContext) {
        super(EntityRenderLayerContext);
    }

    @Override
    public void render(ClientCreeper creeperEntity, float f, float g, float h, float i, float j, float k, float l) {
        if (CONFIG.creeperSkin.equals("charged")) {
            boolean bl = creeperEntity.isInvisible();
            GlStateManager.depthMask(!bl);
            this.bindTexture(SKIN);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            float m = (float)creeperEntity.ticks + h;
            GlStateManager.translate(m * 0.01F, m * 0.01F, 0.0F);
            GlStateManager.matrixMode(5888);
            GlStateManager.enableBlend();
            float n = 0.5F;
            GlStateManager.color(0.5F, 0.5F, 0.5F, 1.0F);
            GlStateManager.disableLighting();
            GlStateManager.blendFunc(1, 1);
            (this.getModel()).m_95427286(this.getModel());
            GameRenderer gameRenderer = Minecraft.getInstance().gameRenderer;
            gameRenderer.resetFogColor(true);
            this.getModel().render(creeperEntity, f, g, i, j, k, l);
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
    public boolean colorsWhenDamaged() {
        return false;
    }
}
