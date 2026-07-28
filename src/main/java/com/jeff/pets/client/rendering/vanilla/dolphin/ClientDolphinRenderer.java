package com.jeff.pets.client.rendering.vanilla.dolphin;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientDolphin;
import net.minecraft.client.renderer.entity.model.DolphinModel;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientDolphinRenderer extends PetRenderer<ClientDolphin, DolphinModel<ClientDolphin>> {

    public ClientDolphinRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new DolphinModel(), 0.7f);
    }

    @Override
    public ResourceLocation getTextureLocation(ClientDolphin dolphinRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/dolphin.png");
    }

    @Override
    protected void scale(ClientDolphin state, float i) {
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }
}
