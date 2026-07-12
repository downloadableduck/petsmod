package com.jeff.pets.client.rendering.vanilla.pig;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientPig;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientPigRenderer extends PetRenderer<@NotNull ClientPig, @NotNull ClientPigModel> {
    public String pigTexturePath;

    public ClientPigRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context) {
        super(context, new ClientPigModel(), 0.7F);
    }

    @Override
    protected void scale(@NotNull ClientPig livingEntityRenderState, @NotNull PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    public @NotNull ResourceLocation getTextureLocation(ClientPig pigRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/pig/pig.png");
    }
}
