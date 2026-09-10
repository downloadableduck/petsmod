package com.jeff.pets.client.rendering.vanilla.vex;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientVex;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientVexRenderer extends PetRenderer<@NotNull ClientVex> {

    public ClientVexRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientVexModel(), 0.75f);
    }

    @Override
    public @NotNull Identifier getTexture(ClientVex livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/illager/vex/vex.png");
    }
}