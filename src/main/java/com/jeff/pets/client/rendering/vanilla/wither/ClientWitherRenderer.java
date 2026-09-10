package com.jeff.pets.client.rendering.vanilla.wither;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.boss.ClientWither;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientWitherRenderer extends PetRenderer<@NotNull ClientWither> {

    public ClientWitherRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientWitherModel(0), 0.75f);
    }

    @Override
    protected void scale(ClientWither livingEntityRenderState, float f) {
        com.mojang.blaze3d.platform.GlStateManager.scale(0.25f, 0.25f, 0.25f);
    }

    @Override
    public @NotNull Identifier getTexture(ClientWither livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/wither/wither.png");
    }
}