package com.jeff.pets.client.rendering.vanilla.pillager;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientPillager;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientPillagerRenderer extends PetRenderer<@NotNull ClientPillager, @NotNull ClientPillagerModel> {
    public static final ModelLayerLocation PILLAGER_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientpillager"), "main");

    public ClientPillagerRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientPillagerModel(context.bakeLayer(ModelLayers.PILLAGER)), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientPillager livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/illager/pillager.png");
    }
}
