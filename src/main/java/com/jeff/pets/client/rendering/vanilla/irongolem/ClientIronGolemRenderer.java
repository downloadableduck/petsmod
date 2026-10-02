package com.jeff.pets.client.rendering.vanilla.irongolem;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientIronGolem;
import net.minecraft.util.ResourceLocation;

public class ClientIronGolemRenderer extends PetRenderer<ClientIronGolem, ClientIronGolemModel> {

    public ClientIronGolemRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientIronGolemModel(), 0.75f);
    }

    @Override
    public ResourceLocation getEntityTexture(Entity __e) {
        ClientIronGolem livingEntityRenderState = (ClientIronGolem) __e;
        return new ResourceLocation("minecraft", "textures/entity/iron_golem.png");
    }
}
