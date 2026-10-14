package com.jeff.pets.client.rendering.vanilla.blaze;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientBlaze;
import net.minecraft.client.render.entity.BlazeRenderer;
import net.minecraft.client.render.model.entity.BlazeModel;

import net.minecraft.client.render.texture.TextureAtlas;
import net.minecraft.client.render.texture.TextureAtlasSprite;
import org.jetbrains.annotations.NotNull;

public class ClientBlazeRenderer extends PetRenderer<ClientBlaze> {

    public ClientBlazeRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new BlazeModel(), 0.75f);
    }

    @Override
    public @NotNull String getTexture(net.minecraft.entity.Entity entity) {
        ClientBlaze livingEntityRenderState = (ClientBlaze) entity;
        return ("/mob/.png");
    }
}
