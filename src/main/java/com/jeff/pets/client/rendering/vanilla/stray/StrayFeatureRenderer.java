//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.stray;

import com.jeff.pets.client.rendering.vanilla.skeleton.ModelSkeleton;
import com.jeff.pets.mob.vanilla.hostile.ClientStray;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class StrayFeatureRenderer implements LayerRenderer<ClientStray> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/skeleton/stray_overlay.png");
    private final RenderLiving<?> field_14989;
    private ModelSkeleton field_14990;

    public StrayFeatureRenderer(RenderLiving<?> livingEntityRenderer) {
        this.field_14989 = livingEntityRenderer;
        this.field_14990 = new ModelSkeleton(0.25F, true);
    }

    public void render(ClientStray skeletonEntity, float f, float g, float h, float i, float j, float k, float l) {
            this.field_14990.setModelAttributes(this.field_14989.getMainModel());
            this.field_14990.setLivingAnimations(skeletonEntity, f, g, h);
            this.field_14989.bindTexture(TEXTURE);
            this.field_14990.render(skeletonEntity, f, g, i, j, k, l);
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }

    public boolean combineTextures() {
        return true;
    }
}
