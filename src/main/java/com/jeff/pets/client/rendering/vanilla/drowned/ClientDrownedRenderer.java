package com.jeff.pets.client.rendering.vanilla.drowned;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientDrowned;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientDrownedRenderer extends PetRenderer<@NotNull ClientDrowned, @NotNull ClientDrownedModel> {

    public ClientDrownedRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context) {
        super(context, new ClientDrownedModel(0.0F, 0.0F, 64, 64), 0.75f);
        this.addLayer(new ClientDrownedOuterLayer(this, context));
    }

    @Override
    protected void scale(ClientDrowned state, @NotNull PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientDrowned livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/zombie/drowned.png");
    }

    @Override
    public void setupRotations(ClientDrowned state, @NotNull PoseStack poseStack, float f, float g, float i) {
        super.setupRotations(state, poseStack, f, g, i);
        if (state.isPassenger()) {
            poseStack.translate(0, -0.5, 0);
        }
    }
}
