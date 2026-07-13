package com.jeff.pets.client.rendering.vanilla.slime;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientSlime;
import net.minecraft.client.renderer.entity.layers.SlimeGelLayer;
import net.minecraft.client.renderer.entity.model.SlimeModel;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientSlimeRenderer extends PetRenderer<ClientSlime, SlimeModel<ClientSlime>> {

    public ClientSlimeRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context) {
        super(context, new SlimeModel<>(16), 0.75f);
        this.addLayer(new SlimeGelLayer<>(this));
    }

    @Override
    protected void scale(ClientSlime slimeRenderState, com.mojang.blaze3d.matrix.MatrixStack poseStack, float f) {
        int slimeScale;
        switch (CONFIG.slimeSkin) {
            case "small":
                slimeScale = 1;
                break;
            case "medium":
                slimeScale = 2;
                break;
            case "large":
                slimeScale = 4;
                break;
            default:
                slimeScale = 1;
                break;
        }
        poseStack.scale(slimeScale, slimeScale, slimeScale);
    }

    @Override
    public ResourceLocation getTextureLocation(ClientSlime livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/slime/slime.png");
    }
}
