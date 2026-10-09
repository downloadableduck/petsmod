package com.jeff.pets.client.rendering.vanilla.shulker;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientShulker;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientShulkerRenderer extends PetRenderer {

    public ClientShulkerRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientShulkerModel(), 0.75f);
    }

    public @NotNull Identifier getTexture(net.minecraft.entity.Entity __e) {
        ClientShulker livingEntityRenderState = (ClientShulker) __e;
        return new Identifier("minecraft", "textures/entity/shulker/shulker_purple.png");
    }

    public void renderModel(ClientShulker shulker, float f, float g, float h, float i, float j, float k) {
        super.renderModel(shulker, f, g, h, i, j, k);
    }
}