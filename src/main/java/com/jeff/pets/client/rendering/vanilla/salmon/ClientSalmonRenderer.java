package com.jeff.pets.client.rendering.vanilla.salmon;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientSalmon;
import net.minecraft.client.renderer.entity.model.ModelSalmon;
import net.minecraft.util.ResourceLocation;

public class ClientSalmonRenderer extends PetRenderer<ClientSalmon, ModelSalmon> {

    public ClientSalmonRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ModelSalmon(), 0.4F);
    }

    @Override
    public ResourceLocation getEntityTexture(ClientSalmon salmonRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/fish/salmon.png");
    }

    @Override
    protected void applyRotations(ClientSalmon salmonRenderState, float ageInTicks, float g, float a) {
        super.applyRotations(salmonRenderState, ageInTicks, g, a);
        float h = 1.0F;
        float i = 1.0F;

        float j = h * 4.3F * net.minecraft.util.math.MathHelper.sin(i * 0.6F * ageInTicks);
    }
}
