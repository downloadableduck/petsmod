//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.enderman;

import net.minecraft.entity.LivingEntity;
import com.jeff.pets.client.rendering.PetRenderLayer;
import com.jeff.pets.compat.GLX;
import com.jeff.pets.compat.GlStateManager;
import com.jeff.pets.mob.vanilla.neutral.ClientEnderman;

public class EndermanEyesLayer implements PetRenderLayer {
    private static final String ENDERMAN_EYES_LOCATION = "textures/mob/enderman/enderman_eyes.png";
    private final ClientEndermanRenderer parent;

    public EndermanEyesLayer(ClientEndermanRenderer parent) {
        this.parent = parent;
    }

    public void render(LivingEntity entity, float f, float g, float h, float i, float j, float k) {
        ClientEnderman endermanEntity = (ClientEnderman)entity;
        this.parent.bindTexture("textures/mob/enderman/enderman_eyes.png");
        GlStateManager.enableBlend();
        GlStateManager.disableAlphaTest();
        GlStateManager.blendFunc(1, 1);
        GlStateManager.disableLighting();
        GlStateManager.depthMask(false);
        int m = '\uf0f0';
        int n = '\uf0f0';
        boolean o = false;
        GLX.multiTexCoord2f(33985, 61680.0F, 0.0F);
        GlStateManager.enableLighting();
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.parent.getModel().render(f, g, h, i, j, k);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.depthMask(true);
        GlStateManager.disableBlend();
        GlStateManager.enableAlphaTest();
    }

    public boolean colorsWhenDamaged() {
        return false;
    }
}
