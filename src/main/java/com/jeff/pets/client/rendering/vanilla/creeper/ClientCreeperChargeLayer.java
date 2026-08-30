package com.jeff.pets.client.rendering.vanilla.creeper;

import com.jeff.pets.mob.vanilla.hostile.ClientCreeper;
import net.minecraft.client.render.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.entity.layer.EntityRenderLayer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.model.entity.CreeperModel;
import net.minecraft.resource.Identifier;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCreeperChargeLayer implements EntityRenderLayer<ClientCreeper> {
    private static final Identifier SKIN = new Identifier("textures/entity/creeper/creeper_armor.png");
    private final net.minecraft.client.render.entity.MobRenderer renderer;

    public ClientCreeperChargeLayer(net.minecraft.client.render.entity.MobRenderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void render(ClientCreeper creeperEntity, float f, float g, float h, float i, float j, float k, float l) {
        if (CONFIG.creeperSkin.equals("charged")) {
            boolean bl = creeperEntity.isInvisible();
            GlStateManager.depthMask(!bl);
            this.renderer.bindTexture(SKIN);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            float m = (float)creeperEntity.ticks + h;
            GlStateManager.translatef(m * 0.01F, m * 0.01F, 0.0F);
            GlStateManager.matrixMode(5888);
            GlStateManager.enableBlend();
            float n = 0.5F;
            GlStateManager.color4f(0.5F, 0.5F, 0.5F, 1.0F);
            GlStateManager.disableLighting();
            GlStateManager.blendFunc(1, 1);
            (this.renderer.getModel()).copyPropertiesFrom(this.renderer.getModel());
            GameRenderer gameRenderer = Minecraft.getInstance().gameRenderer;
            gameRenderer.resetFogColor(true);
            this.renderer.getModel().render(creeperEntity, f, g, i, j, k, l);
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
