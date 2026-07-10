package com.jeff.pets.client.rendering.vanilla.slime;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientSlime;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.SlimeModel;
import net.minecraft.client.renderer.entity.layers.SlimeOuterLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientSlimeRenderer extends PetRenderer<@NotNull ClientSlime, @NotNull SlimeModel<ClientSlime>> {

    public ClientSlimeRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry.Context context2) {
        super(context, new SlimeModel<>(16), 0.75f);
        this.addLayer(new SlimeOuterLayer(this));
    }

    @Override
    protected void scale(ClientSlime slimeRenderState, @NotNull PoseStack poseStack, float f) {
        int slimeScale = switch (CONFIG.slimeSkin) {
            case "small" -> 1;
            case "medium" -> 2;
            case "large" -> 4;
            default -> 1;
        };
        poseStack.scale(slimeScale, slimeScale, slimeScale);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientSlime livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/slime/slime.png");
    }
}
