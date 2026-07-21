package com.jeff.pets.client.rendering.vanilla.stray;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientStray;
import net.minecraft.client.render.entity.layer.StrayOverlayLayer;
import net.minecraft.client.render.model.entity.SkeletonModel;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientStrayRenderer extends PetRenderer<@NotNull ClientStray, @NotNull SkeletonModel<@NotNull ClientStray>> {

    public ClientStrayRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new SkeletonModel<>(), 0.75f);
        this.addLayer(new StrayOverlayLayer<>(this));
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientStray livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/skeleton/stray.png");
    }

    @Override
    public void applyRotation(ClientStray state, float f, float g, float h) {
        super.applyRotation(state, f, g, h);
        if (state.isRiding()) {
            com.mojang.blaze3d.platform.GlStateManager.translatef(0, -0.5f, 0);
        }
    }
}
