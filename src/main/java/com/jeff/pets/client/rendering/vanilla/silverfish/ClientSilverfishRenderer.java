package com.jeff.pets.client.rendering.vanilla.silverfish;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientSilverfish;
import net.minecraft.client.model.ModelSilverfish;
import net.minecraft.util.ResourceLocation;

public class ClientSilverfishRenderer extends PetRenderer<ClientSilverfish, ModelSilverfish> {

    public ClientSilverfishRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ModelSilverfish(), 0.75f);
    }

    @Override
    public ResourceLocation getEntityTexture(Entity __e) {
        ClientSilverfish livingEntityRenderState = (ClientSilverfish) __e;
        return new ResourceLocation("minecraft", "textures/entity/silverfish.png");
    }
}
