package com.jeff.pets.client.rendering.vanilla.ghast;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientGhast;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientGhastRenderer extends PetRenderer<@NotNull ClientGhast> {

    public ClientGhastRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new net.minecraft.client.render.entity.model.GhastEntityModel(), 0.75f);
    }

    @Override
    protected void scale(ClientGhast state, float f) {
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scale(0.25f, 0.25f, 0.25f);
        }
    }

    @Override
    public @NotNull Identifier getTexture(ClientGhast livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/ghast/ghast.png");
    }
}