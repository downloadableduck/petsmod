package com.jeff.pets.client.rendering.vanilla.pufferfish;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientPufferFish;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientPufferFishRenderer extends PetRenderer<@NotNull ClientPufferFish> {

    public ClientPufferFishRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new net.minecraft.client.render.entity.model.SquidEntityModel(), 0.75f);
    }

    @Override
    public @NotNull Identifier getTexture(ClientPufferFish livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/fish/pufferfish.png");
    }
}