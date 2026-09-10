package com.jeff.pets.client.rendering.vanilla.vindicator;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.evoker.ClientEvokerModel;
import com.jeff.pets.mob.vanilla.hostile.ClientEvoker;
import com.jeff.pets.mob.vanilla.hostile.ClientVindicator;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientVindicatorRenderer extends PetRenderer<@NotNull ClientVindicator> {

    public ClientVindicatorRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientEvokerModel(0, 0, 64, 64), 0.75f);
    }

    @Override
    public @NotNull Identifier getTexture(ClientVindicator livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/vindicator/vindicator.png");
    }
}