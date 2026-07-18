package com.jeff.pets.client.rendering.vanilla.ghast;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientGhast;
import net.minecraft.client.render.entity.model.GhastEntityModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientGhastRenderer extends PetRenderer<@NotNull ClientGhast, @NotNull GhastEntityModel<ClientGhast>> {

    public ClientGhastRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new GhastEntityModel<>(), 0.75f);
    }

    @Override
    public @NotNull Identifier getTexture(ClientGhast livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/ghast/ghast.png");
    }

    @Override
    public void scale(ClientGhast ghast, float f) {
        com.mojang.blaze3d.platform.GlStateManager.scalef(4.5F, 4.5F, 4.5F);
    }
}
