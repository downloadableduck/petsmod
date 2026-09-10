package com.jeff.pets.client.rendering.vanilla.drowned;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientDrowned;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientDrownedRenderer extends PetRenderer<@NotNull ClientDrowned> {

    public ClientDrownedRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientDrownedModel(0.0F, 0.0F, 64, 64), 0.75f);
        this.addFeature(new ClientDrownedOuterLayer(this));
    }

    @Override
    public @NotNull Identifier getTexture(ClientDrowned livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/zombie/drowned.png");
    }

    @Override
    public void method_5777(ClientDrowned state, float f, float g, float h) {
        super.method_5777(state, f, g, h);
        if (state.getVehicle() != null) {
            com.mojang.blaze3d.platform.GlStateManager.translate(0, -0.5f, 0);
        }
    }
}