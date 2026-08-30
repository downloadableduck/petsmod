//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.stray;

import com.jeff.pets.client.rendering.vanilla.skeleton.SkeletonModel;
import com.jeff.pets.mob.vanilla.hostile.ClientStray;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.layer.EntityRenderLayer;
import net.minecraft.client.render.platform.GlStateManager;
import net.minecraft.entity.living.mob.monster.StrayEntity;
import net.minecraft.resource.Identifier;

@Environment(EnvType.CLIENT)
public class StrayOverlayLayer implements EntityRenderLayer<ClientStray> {
    private static final Identifier OVERLAY_LOCATION = new Identifier("textures/entity/skeleton/stray_overlay.png");
    private final LivingEntityRenderer<?> renderer;
    private final SkeletonModel model = new SkeletonModel(0.25F, true);

    public StrayOverlayLayer(LivingEntityRenderer<?> renderer) {
        this.renderer = renderer;
    }

    public void render(ClientStray strayEntity, float f, float g, float h, float i, float j, float k, float l) {
        this.model.copyPropertiesFrom(this.renderer.getModel());
        this.model.prepare(strayEntity, f, g, h);
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.renderer.bindTexture(OVERLAY_LOCATION);
        this.model.render(strayEntity, f, g, i, j, k, l);
    }

    public boolean colorsWhenDamaged() {
        return true;
    }
}
