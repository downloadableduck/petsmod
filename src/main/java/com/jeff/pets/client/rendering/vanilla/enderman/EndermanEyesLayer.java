//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.enderman;

import com.jeff.pets.mob.vanilla.neutral.ClientEnderman;

import net.minecraft.client.Minecraft;
import net.minecraft.client.render.entity.EndermanRenderer;
import com.jeff.pets.client.rendering.PetRenderLayer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.compat.GLX;
import com.jeff.pets.compat.GlStateManager;
import net.minecraft.entity.living.mob.monster.EndermanEntity;
import net.minecraft.client.resource.Identifier;


public class EndermanEyesLayer implements PetRenderLayer {
    private static final Identifier ENDERMAN_EYES_LOCATION = new Identifier("textures/entity/enderman/enderman_eyes.png");
    private final ClientEndermanRenderer parent;

    public EndermanEyesLayer(ClientEndermanRenderer parent) {
        this.parent = parent;
    }

    public void render(net.minecraft.entity.living.LivingEntity entity, float f, float g, float h, float i, float j, float k) {
        ClientEnderman endermanEntity = (ClientEnderman) entity;
        this.parent.bindTexture(ENDERMAN_EYES_LOCATION);
        GlStateManager.enableBlend();
        GlStateManager.disableAlphaTest();
        GlStateManager.blendFunc(1, 1);
        GlStateManager.disableLighting();
        GlStateManager.depthMask(!endermanEntity.isInvisible());
        int m = 61680;
        int n = 61680;
        int o = 0;
        GLX.multiTexCoord2f(GLX.GL_TEXTURE1, 61680.0F, 0.0F);
        GlStateManager.enableLighting();
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.parent.getModel().render(endermanEntity, f, g, h, i, j, k);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.depthMask(true);
        GlStateManager.disableBlend();
        GlStateManager.enableAlphaTest();
    }

    public boolean colorsWhenDamaged() {
        return false;
    }
}
