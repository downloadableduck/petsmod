package com.jeff.pets.client.rendering.vanilla.endermite;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientEndermite;
import net.minecraft.client.render.entity.model.EndermiteEntityModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientEndermiteRenderer extends PetRenderer<@NotNull ClientEndermite> {

    public ClientEndermiteRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new EndermiteEntityModel(), 0.75f);
    }

    @Override
    protected void scale(ClientEndermite endermite, float f) {
        com.mojang.blaze3d.platform.GlStateManager.scale(0.7f, 0.7f, 0.7f);
    }

    @Override
    public @NotNull Identifier getTexture(@NotNull ClientEndermite endermiteEntity) {
        return new Identifier("minecraft", "textures/entity/endermite.png");
    }
}