package com.jeff.pets.rendering.vanilla.fox;

import com.jeff.pets.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientFox;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.FoxRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.Central.CONFIG;

public class ClientFoxRenderer extends PetRenderer<@NotNull ClientFox, @NotNull FoxRenderState, @NotNull ClientFoxModel> {
    public static final ModelLayerLocation FOX_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientfox"), "main");
    public String foxTexturePath;

    public ClientFoxRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientFoxModel(context.bakeLayer(ModelLayers.FOX)), 0.75f);
    }

    @Override
    protected void scale(FoxRenderState state, @NotNull PoseStack poseStack) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(FoxRenderState livingEntityRenderState) {
        if (Objects.equals(CONFIG.foxSkin, "red")) {
            foxTexturePath = "textures/entity/fox/fox.png";
        } else if (Objects.equals(CONFIG.foxSkin, "snow")) {
            foxTexturePath = "textures/entity/fox/fox_snow.png";
        } else {
            foxTexturePath = "textures/entity/fox/fox.png";
        }
        return ResourceLocation.withDefaultNamespace(foxTexturePath);
    }

    @Override
    public FoxRenderState createRenderState() {
        return new FoxRenderState();
    }

    @Override
    public void extractRenderState(ClientFox fox, FoxRenderState state, float f) {
        super.extractRenderState(fox, state, f);
        state.isSleeping = fox.isPassenger();
    }
}
