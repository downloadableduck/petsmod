package com.jeff.pets.client.rendering.vanilla.villager;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientVillager;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientVillagerRenderer extends PetRenderer<@NotNull ClientVillager, VillagerModel<ClientVillager>> {
    public static final ModelLayerLocation VILLAGER_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientvillager"), "main");

    public ClientVillagerRenderer(EntityRendererProvider.Context context) {
        super(context, new VillagerModel<>(context.bakeLayer(ModelLayers.VILLAGER)), 0.5F);
        this.addLayer((RenderLayer) new ClientVillagerDefaultLayer((RenderLayerParent) this));
        this.addLayer((RenderLayer) new ClientVillagerProfessionLayer((RenderLayerParent) this));
    }

    public static LayerDefinition createBaseVillagerLayer() {
        VillagerModel.createBodyModel();
        return LayerDefinition.create(new MeshDefinition(), 64, 64);
    }

    @Override
    protected void scale(ClientVillager state, @NotNull PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientVillager villagerRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/villager/villager.png");
    }
}
