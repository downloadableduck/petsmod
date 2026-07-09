package com.jeff.pets.client.rendering.vanilla.pillager;

import com.jeff.pets.mob.vanilla.hostile.ClientPillager;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientPillagerRenderer extends PetRenderer<@NotNull ClientPillager, @NotNull ClientPillagerModel> {
    public static final ModelLayerLocation PILLAGER_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientpillager"), "main");

    public ClientPillagerRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientPillagerModel(context.bakeLayer(ModelLayers.PILLAGER)), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientPillager livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/illager/pillager.png");
    }
}
