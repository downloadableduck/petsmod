package com.jeff.pets.client.rendering.vanilla.drowned;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientDrowned;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientDrownedRenderer extends PetRenderer<@NotNull ClientDrowned, @NotNull ClientDrownedModel> {

    public ClientDrownedRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new ClientDrownedModel(0.0F, 0.0F, 64, 64), 0.75f);
        this.addFeature(new ClientDrownedOuterLayer(this, context, context2));
    }

    @Override
    protected void scale(ClientDrowned state, float f) {
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTexture(ClientDrowned livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/zombie/drowned.png");
    }

    @Override
    public void setupTransforms(ClientDrowned state, float f, float g, float h) {
        super.setupTransforms(state, f, g, h);
        if (state.hasVehicle()) {
            com.mojang.blaze3d.platform.GlStateManager.translatef(0, -0.5f, 0);
        }
    }
}
