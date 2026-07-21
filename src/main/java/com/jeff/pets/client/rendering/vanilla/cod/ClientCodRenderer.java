package com.jeff.pets.client.rendering.vanilla.cod;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientCod;
import net.minecraft.resource.Identifier;
import net.minecraft.unmapped.C_4554347;
import org.jetbrains.annotations.NotNull;

public class ClientCodRenderer extends PetRenderer<@NotNull ClientCod, @NotNull C_4554347<ClientCod>> {

    public ClientCodRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new C_4554347(), 0.3F);
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientCod livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/fish/cod.png");
    }
}
