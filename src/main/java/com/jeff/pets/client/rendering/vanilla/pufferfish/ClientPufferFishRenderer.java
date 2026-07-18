package com.jeff.pets.client.rendering.vanilla.pufferfish;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientPufferFish;
import net.minecraft.client.render.entity.model.LargePufferfishEntityModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientPufferFishRenderer extends PetRenderer<@NotNull ClientPufferFish, @NotNull LargePufferfishEntityModel<ClientPufferFish>> {

    public ClientPufferFishRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new LargePufferfishEntityModel<>(), 0.75f);
    }

    @Override
    public @NotNull Identifier getTexture(ClientPufferFish livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/fish/pufferfish.png");
    }
}