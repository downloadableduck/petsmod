package com.jeff.pets.client.rendering.vanilla.drowned;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientDrowned;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientDrownedRenderer extends PetRenderer<@NotNull ClientDrowned, @NotNull ClientDrownedModel> {

    public ClientDrownedRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientDrownedModel(0.0F, 0.0F, 64, 64), 0.75f);
        this.addLayer(new ClientDrownedOuterLayer(this, context, context2));
    }

    @Override
    protected void applyScale(ClientDrowned state, float f) {
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientDrowned livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/zombie/drowned.png");
    }

    @Override
    public void applyRotation(ClientDrowned state, float f, float g, float h) {
        super.applyRotation(state, f, g, h);
        if (state.isRiding()) {
            com.mojang.blaze3d.platform.GlStateManager.translate(0, -0.5f, 0);
        }
    }
}
