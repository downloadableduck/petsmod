//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.creeper;

import net.minecraft.core.entity.Mob;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.rendering.PetRenderLayer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.compat.GlStateManager;
import com.jeff.pets.mob.vanilla.hostile.ClientCreeper;

public class ClientCreeperChargeLayer implements PetRenderLayer {
    private static final String SKIN = "/mob/creeper/creeper_armor.png";
    private final PetRenderer renderer;
    private final CreeperModel model = new CreeperModel(2.0F);

    public ClientCreeperChargeLayer(PetRenderer renderer) {
        this.renderer = renderer;
    }

    public void render(Mob entity, float f, float g, float h, float i, float j, float k) {
        ClientCreeper creeperEntity = (ClientCreeper)entity;
        if (Central.CONFIG.creeperSkin.equals("charged")) {
            boolean bl = false;
            GlStateManager.depthMask(!bl);
            this.renderer.bindTexture("/mob/creeper/creeper_armor.png");
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            float m = (float)creeperEntity.tickCount + h;
            GlStateManager.translatef(m * 0.01F, m * 0.01F, 0.0F);
            GlStateManager.matrixMode(5888);
            GlStateManager.enableBlend();
            float n = 0.5F;
            GlStateManager.color4f(0.5F, 0.5F, 0.5F, 1.0F);
            GlStateManager.disableLighting();
            GlStateManager.blendFunc(1, 1);
            this.model.render(f, g, h, i, j, k);
            GlStateManager.matrixMode(5890);
            GlStateManager.loadIdentity();
            GlStateManager.matrixMode(5888);
            GlStateManager.enableLighting();
            GlStateManager.disableBlend();
            GlStateManager.depthMask(bl);
        }

    }

    public boolean colorsWhenDamaged() {
        return false;
    }
}
