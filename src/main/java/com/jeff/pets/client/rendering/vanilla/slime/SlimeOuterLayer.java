//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.slime;

import com.jeff.pets.mob.vanilla.hostile.ClientSlime;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.SlimeRenderer;
import net.minecraft.client.render.entity.layer.EntityRenderLayer;
import net.minecraft.client.render.model.Model;
import net.minecraft.client.render.model.entity.SlimeModel;
import net.minecraft.client.render.platform.GlStateManager;
import net.minecraft.client.render.platform.GlStateManager.DestFactor;
import net.minecraft.client.render.platform.GlStateManager.SourceFactor;
import net.minecraft.entity.living.mob.monster.SlimeEntity;

@Environment(EnvType.CLIENT)
public class SlimeOuterLayer implements EntityRenderLayer<ClientSlime> {
    private final ClientSlimeRenderer parent;
    private final Model model = new SlimeModel(0);

    public SlimeOuterLayer(ClientSlimeRenderer parent) {
        this.parent = parent;
    }

    public void render(ClientSlime slimeEntity, float f, float g, float h, float i, float j, float k, float l) {
        if (!slimeEntity.isInvisible()) {
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.enableNormalize();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
            this.model.copyPropertiesFrom(this.parent.getModel());
            this.model.render(slimeEntity, f, g, i, j, k, l);
            GlStateManager.disableBlend();
            GlStateManager.disableNormalize();
        }
    }

    public boolean colorsWhenDamaged() {
        return true;
    }
}
