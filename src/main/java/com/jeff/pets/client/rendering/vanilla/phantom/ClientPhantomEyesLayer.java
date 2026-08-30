//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.phantom;

import com.jeff.pets.mob.vanilla.hostile.ClientPhantom;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.entity.PhantomRenderer;
import net.minecraft.client.render.entity.layer.EntityRenderLayer;
import net.minecraft.client.render.platform.GLX;
import net.minecraft.client.render.platform.GlStateManager;
import net.minecraft.client.render.platform.GlStateManager.DestFactor;
import net.minecraft.client.render.platform.GlStateManager.SourceFactor;
import net.minecraft.entity.living.mob.monster.PhantomEntity;
import net.minecraft.resource.Identifier;

@Environment(EnvType.CLIENT)
public class ClientPhantomEyesLayer implements EntityRenderLayer<ClientPhantom> {
    private static final Identifier f_16325707 = new Identifier("textures/entity/phantom_eyes.png");
    private final ClientPhantomRenderer f_99996981;

    public ClientPhantomEyesLayer(ClientPhantomRenderer phantomRenderer) {
        this.f_99996981 = phantomRenderer;
    }

    public void render(ClientPhantom phantomEntity, float f, float g, float h, float i, float j, float k, float l) {
        this.f_99996981.bindTexture(f_16325707);
        GlStateManager.enableBlend();
        GlStateManager.disableAlphaTest();
        GlStateManager.blendFunc(SourceFactor.ONE, DestFactor.ONE);
        GlStateManager.disableLighting();
        GlStateManager.depthMask(!phantomEntity.isInvisible());
        int m = 61680;
        int n = 61680;
        int o = 0;
        GLX.multiTexCoord2f(GLX.GL_TEXTURE1, 61680.0F, 0.0F);
        GlStateManager.enableLighting();
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getInstance().gameRenderer.resetFogColor(true);
        this.f_99996981.getModel().render(phantomEntity, f, g, i, j, k, l);
        Minecraft.getInstance().gameRenderer.resetFogColor(false);
        this.f_99996981.setLightColor(phantomEntity);
        GlStateManager.depthMask(true);
        GlStateManager.disableBlend();
        GlStateManager.enableAlphaTest();
    }

    public boolean colorsWhenDamaged() {
        return false;
    }
}
