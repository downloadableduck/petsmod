package com.jeff.pets.client.rendering.vanilla.wolf;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientWolf;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientWolfRenderer extends PetRenderer<@NotNull ClientWolf, @NotNull ClientWolfModel> {

    public ClientWolfRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientWolfModel(), 0.75f);
    }

    @Override
    protected void applyScale(@NotNull ClientWolf livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientWolf livingEntityRenderState) {
        String wolfTexturePath = "textures/entity/wolf/wolf.png";

        return new Identifier("minecraft", wolfTexturePath);
    }

    @Override
    public void renderModel(ClientWolf wolf, float f, float g, float h, float i, float j, float k) {
        super.renderModel(wolf, f, g, h, i, j, k);
        wolf.setSitting(wolf.isRiding());
    }
}
