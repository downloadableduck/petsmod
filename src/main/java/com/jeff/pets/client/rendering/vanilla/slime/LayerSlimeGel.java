//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.slime;

import com.jeff.pets.mob.vanilla.hostile.ClientSlime;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.DestFactor;
import net.minecraft.client.renderer.GlStateManager.SourceFactor;
import net.minecraft.client.renderer.entity.RenderSlime;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.ModelBase;
import net.minecraft.client.renderer.entity.model.ModelSlime;
import net.minecraft.entity.monster.EntitySlime;



public class LayerSlimeGel implements LayerRenderer<ClientSlime> {
    private final ClientSlimeRenderer slimeRenderer;
    private final ModelBase slimeModel = new ModelSlime(0);

    public LayerSlimeGel(ClientSlimeRenderer p_i46111_1_) {
        this.slimeRenderer = p_i46111_1_;
    }

    @Override
    public void doRenderLayer(ClientSlime p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_) {
        if (!p_177141_1_.isInvisible()) {
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.enableNormalize();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);
            this.slimeModel.setModelAttributes(this.slimeRenderer.getMainModel());
            this.slimeModel.render(p_177141_1_, p_177141_2_, p_177141_3_, p_177141_5_, p_177141_6_, p_177141_7_, p_177141_8_);
            GlStateManager.disableBlend();
            GlStateManager.disableNormalize();
        }
    }

    public boolean shouldCombineTextures() {
        return true;
    }
}
