package com.jeff.pets.client.rendering.vanilla.irongolem;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientIronGolem;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientIronGolemRenderer extends PetRenderer<@NotNull ClientIronGolem, @NotNull ClientIronGolemModel<ClientIronGolem>> {

    public ClientIronGolemRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new ClientIronGolemModel<>(), 0.75f);
    }

    @Override
    public @NotNull Identifier getTexture(ClientIronGolem livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/iron_golem.png");
    }
}
