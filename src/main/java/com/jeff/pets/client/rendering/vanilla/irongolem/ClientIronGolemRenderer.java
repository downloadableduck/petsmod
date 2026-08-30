package com.jeff.pets.client.rendering.vanilla.irongolem;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientIronGolem;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientIronGolemRenderer extends PetRenderer<ClientIronGolem> {

    public ClientIronGolemRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientIronGolemModel(), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientIronGolem livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/iron_golem.png");
    }
}
