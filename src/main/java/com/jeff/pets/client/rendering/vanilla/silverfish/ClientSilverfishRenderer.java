package com.jeff.pets.client.rendering.vanilla.silverfish;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientSilverfish;
import net.minecraft.client.render.entity.model.SilverfishEntityModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientSilverfishRenderer extends PetRenderer<@NotNull ClientSilverfish> {

    public ClientSilverfishRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new SilverfishEntityModel(), 0.75f);
    }

    @Override
    protected void scale(ClientSilverfish silverfish, float f) {
        com.mojang.blaze3d.platform.GlStateManager.scale(0.7f, 0.7f, 0.7f);
    }

    @Override
    public @NotNull Identifier getTexture(ClientSilverfish livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/silverfish.png");
    }
}