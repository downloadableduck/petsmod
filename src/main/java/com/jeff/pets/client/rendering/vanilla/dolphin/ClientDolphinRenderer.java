package com.jeff.pets.client.rendering.vanilla.dolphin;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientDolphin;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientDolphinRenderer extends PetRenderer<@NotNull ClientDolphin> {

    public ClientDolphinRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new net.minecraft.client.render.entity.model.SquidEntityModel(), 0.7f);
    }

    @Override
    public @NotNull Identifier getTexture(ClientDolphin livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/dolphin.png");
    }

    @Override
    protected void scale(ClientDolphin state, float f) {
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scale(0.5f, 0.5f, 0.5f);
        }
    }
}