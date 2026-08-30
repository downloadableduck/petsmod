package com.jeff.pets.client.rendering.vanilla.vex;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientVex;
import net.minecraft.entity.Entity;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientVexRenderer extends PetRenderer {

    public ClientVexRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientVexModel(), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(Entity livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/illager/vex.png");
    }
}
