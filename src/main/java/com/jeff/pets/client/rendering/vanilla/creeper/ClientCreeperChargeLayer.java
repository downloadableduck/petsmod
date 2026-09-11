package com.jeff.pets.client.rendering.vanilla.creeper;

import com.jeff.pets.mob.vanilla.hostile.ClientCreeper;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.model.CreeperEntityModel;
import net.minecraft.util.Identifier;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCreeperChargeLayer implements FeatureRenderer<ClientCreeper> {
    private static final Identifier SKIN = new Identifier("textures/entity/creeper/creeper_armor.png");
    private final ClientCreeperRenderer renderer;
    private final CreeperEntityModel model;

    public ClientCreeperChargeLayer(ClientCreeperRenderer renderer) {
        this.renderer = renderer;
        this.model = new CreeperEntityModel(2.0F);
    }

    @Override
    public void render(ClientCreeper creeperEntity, float f, float g, float h, float i, float j, float k, float l) {
        if (CONFIG.creeperSkin.equals("charged")) {
            boolean bl = creeperEntity.isInvisible();
            GlStateManager.depthMask(!bl);
            this.renderer.bindTexture(SKIN);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            float m = (float) creeperEntity.ticksAlive + h;
            GlStateManager.translate(m * 0.01F, m * 0.01F, 0.0F);
            GlStateManager.matrixMode(5888);
            GlStateManager.enableBlend();
            GlStateManager.color(0.5F, 0.5F, 0.5F, 1.0F);
            GlStateManager.disableLighting();
            GlStateManager.method_12287(GlStateManager.class_2870.ONE, GlStateManager.class_2866.ONE);
            this.model.copy(this.renderer.getModel());
            this.model.render(creeperEntity, f, g, i, j, k, l);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            GlStateManager.matrixMode(5888);
            GlStateManager.enableLighting();
            GlStateManager.disableBlend();
            GlStateManager.depthMask(true);
        }
    }

    @Override
    public boolean combineTextures() {
        return false;
    }
}