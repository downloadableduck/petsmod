//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.enderman;

import com.jeff.pets.mob.vanilla.neutral.ClientEnderman;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.entity.EndermanRenderer;
import net.minecraft.client.render.entity.layer.EntityRenderLayer;
import net.minecraft.client.render.platform.GLX;
import net.minecraft.client.render.platform.GlStateManager;
import net.minecraft.client.render.platform.GlStateManager.DestFactor;
import net.minecraft.client.render.platform.GlStateManager.SourceFactor;
import net.minecraft.entity.living.mob.monster.EndermanEntity;
import net.minecraft.resource.Identifier;

@Environment(EnvType.CLIENT)
public class EndermanEyesLayer implements EntityRenderLayer<ClientEnderman> {
    private static final Identifier ENDERMAN_EYES_LOCATION = new Identifier("textures/entity/enderman/enderman_eyes.png");
    private final ClientEndermanRenderer parent;

    public EndermanEyesLayer(ClientEndermanRenderer parent) {
        this.parent = parent;
    }

    public void render(ClientEnderman endermanEntity, float f, float g, float h, float i, float j, float k, float l) {
        this.parent.bindTexture(ENDERMAN_EYES_LOCATION);
        GlStateManager.enableBlend();
        GlStateManager.disableAlphaTest();
        GlStateManager.blendFunc(SourceFactor.ONE, DestFactor.ONE);
        GlStateManager.disableLighting();
        GlStateManager.depthMask(!endermanEntity.isInvisible());
        int m = 61680;
        int n = 61680;
        int o = 0;
        GLX.multiTexCoord2f(GLX.GL_TEXTURE1, 61680.0F, 0.0F);
        GlStateManager.enableLighting();
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getInstance().gameRenderer.resetFogColor(true);
        this.parent.getModel().render(endermanEntity, f, g, i, j, k, l);
        Minecraft.getInstance().gameRenderer.resetFogColor(false);
        this.parent.setLightColor(endermanEntity, i);
        GlStateManager.depthMask(true);
        GlStateManager.disableBlend();
        GlStateManager.enableAlphaTest();
    }

    public boolean colorsWhenDamaged() {
        return false;
    }
}
