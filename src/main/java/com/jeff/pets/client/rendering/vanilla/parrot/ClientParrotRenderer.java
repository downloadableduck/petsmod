package com.jeff.pets.client.rendering.vanilla.parrot;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientParrot;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientParrotRenderer extends PetRenderer<@NotNull ClientParrot> {

    public ClientParrotRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientParrotModel(), 0.75f);
    }

    @Override
    public @NotNull Identifier getTexture(ClientParrot livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/parrot/parrot_red_blue.png");
    }
}