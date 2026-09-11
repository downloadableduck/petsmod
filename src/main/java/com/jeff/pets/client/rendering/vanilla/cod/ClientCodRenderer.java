package com.jeff.pets.client.rendering.vanilla.cod;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientCod;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientCodRenderer extends PetRenderer<@NotNull ClientCod> {

    public ClientCodRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new net.minecraft.client.render.entity.model.SquidEntityModel(), 0.3F);
    }

    @Override
    public @NotNull Identifier getTexture(ClientCod livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/fish/cod.png");
    }
}