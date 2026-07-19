package com.jeff.pets.client.rendering.vanilla.pufferfish;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientPufferFish;
import net.minecraft.client.render.model.entity.LargePufferfishModel;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientPufferFishRenderer extends PetRenderer<@NotNull ClientPufferFish, @NotNull LargePufferfishModel<ClientPufferFish>> {

    public ClientPufferFishRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context) {
        super(context, new LargePufferfishModel<>(), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientPufferFish livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/fish/pufferfish.png");
    }
}