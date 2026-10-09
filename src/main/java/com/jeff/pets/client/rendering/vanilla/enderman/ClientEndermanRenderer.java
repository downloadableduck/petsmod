package com.jeff.pets.client.rendering.vanilla.enderman;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientEnderman;
import net.minecraft.client.render.entity.model.EndermanEntityModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientEndermanRenderer extends PetRenderer {

    public ClientEndermanRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new EndermanEntityModel(), 0.5f);
    }

    public @NotNull Identifier getTexture(net.minecraft.entity.Entity __e) {
        ClientEnderman livingEntityRenderState = (ClientEnderman) __e;
        return new Identifier("minecraft", "textures/entity/enderman/enderman.png");
    }
}