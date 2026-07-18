package com.jeff.pets.client.rendering.vanilla.stray;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientStray;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.feature.StrayOverlayFeatureRenderer;
import net.minecraft.client.render.entity.model.StrayEntityModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientStrayRenderer extends PetRenderer<@NotNull ClientStray, @NotNull StrayEntityModel<@NotNull ClientStray>> {

    public ClientStrayRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new StrayEntityModel<>(), 0.75f);
        this.addFeature(new StrayOverlayFeatureRenderer<>((FeatureRendererContext) this));
    }

    @Override
    public @NotNull Identifier getTexture(ClientStray livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/skeleton/stray.png");
    }

    @Override
    public void setupTransforms(ClientStray state, float f, float g, float h) {
        super.setupTransforms(state, f, g, h);
        if (state.hasVehicle()) {
            com.mojang.blaze3d.platform.GlStateManager.translatef(0, -0.5f, 0);
        }
    }
}
