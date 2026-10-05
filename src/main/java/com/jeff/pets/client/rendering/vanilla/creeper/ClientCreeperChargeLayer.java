package com.jeff.pets.client.rendering.vanilla.creeper;

import com.jeff.pets.mob.vanilla.hostile.ClientCreeper;
import com.jeff.pets.compat.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.GameRenderer;
import com.jeff.pets.client.rendering.PetRenderLayer;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.model.entity.CreeperModel;
import net.minecraft.client.resource.Identifier;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCreeperChargeLayer implements PetRenderLayer {
    private static final Identifier SKIN = new Identifier("textures/entity/creeper/creeper_armor.png");
    private final PetRenderer renderer;
    private final CreeperModel model = new CreeperModel(2);

    public ClientCreeperChargeLayer(PetRenderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void render(net.minecraft.entity.living.LivingEntity entity, float f, float g, float h, float i, float j, float k) {
        ClientCreeper creeperEntity = (ClientCreeper) entity;
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
            
            this.model.render(creeperEntity, f, g, h, i, j, k);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            GlStateManager.matrixMode(5888);
            GlStateManager.enableLighting();
            GlStateManager.disableBlend();
            GlStateManager.depthMask(bl);
        }
    }

    @Override
    public boolean colorsWhenDamaged() {
        return false;
    }
}
