package com.jeff.pets.client.rendering.vanilla.mooshroom;

import com.jeff.pets.mob.vanilla.passive.ClientMooshroom;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.cow.ClientCowModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientMooshroomRenderer extends PetRenderer<@NotNull ClientMooshroom, @NotNull ClientCowModel<ClientMooshroom>> {
    public static final ModelLayerLocation MOOSHROOM_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientmooshroom"), "main");

    String mooshroomTexturePath;

    public ClientMooshroomRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientCowModel<>(context.bakeLayer(ModelLayers.COW)), 0.7F);
        this.addLayer(new ClientMushroomCowMushroomLayer(this, context.getBlockRenderDispatcher()));
    }

    @Override
    protected void scale(ClientMooshroom state, @NotNull PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientMooshroom cowRenderState) {
        if (Objects.equals(CONFIG.mooshroomSkin, "red")) {
            mooshroomTexturePath = "textures/entity/cow/red_mooshroom.png";
        } else if (Objects.equals(CONFIG.mooshroomSkin, "brown")) {
            mooshroomTexturePath = "textures/entity/cow/brown_mooshroom.png";
        } else {
            mooshroomTexturePath = "textures/entity/cow/red_mooshroom.png";
        }
        return ResourceLocation.withDefaultNamespace(mooshroomTexturePath);
    }
}
