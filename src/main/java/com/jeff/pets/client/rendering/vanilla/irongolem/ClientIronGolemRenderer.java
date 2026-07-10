package com.jeff.pets.client.rendering.vanilla.irongolem;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientIronGolem;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientIronGolemRenderer extends PetRenderer<@NotNull ClientIronGolem, @NotNull ClientIronGolemModel<ClientIronGolem>> {

    public ClientIronGolemRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry.Context context2) {
        super(context, new ClientIronGolemModel<>(), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientIronGolem livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/iron_golem/iron_golem.png");
    }
}
