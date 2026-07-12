package com.jeff.pets.client.rendering.vanilla.hoglin;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientHoglin;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientHoglinRenderer extends PetRenderer<@NotNull ClientHoglin, @NotNull ClientHoglinModel> {

    public ClientHoglinRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context) {
        super(context, new ClientHoglinModel(), 0.75f);
    }

    @Override
    protected void scale(ClientHoglin state, @NotNull PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientHoglin livingEntityRenderState) {
        String hoglinTexturePath;
        if (Objects.equals(CONFIG.hoglinSkin, "hoglin")) {
            hoglinTexturePath = "textures/entity/hoglin/hoglin.png";
        } else if (Objects.equals(CONFIG.hoglinSkin, "zoglin")) {
            hoglinTexturePath = "textures/entity/hoglin/zoglin.png";
        } else {
            hoglinTexturePath = "textures/entity/hoglin/hoglin.png";
        }
        return new ResourceLocation("minecraft", hoglinTexturePath);
    }
}
