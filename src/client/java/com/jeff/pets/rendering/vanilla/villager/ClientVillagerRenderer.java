package com.jeff.pets.rendering.vanilla.villager;

import com.jeff.pets.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientVillager;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.CrossedArmsItemLayer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.state.VillagerRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Central.CONFIG;

public class ClientVillagerRenderer extends PetRenderer<@NotNull ClientVillager, @NotNull VillagerRenderState, VillagerModel> {
    public static final ModelLayerLocation VILLAGER_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientvillager"), "main");
    public static final CustomHeadLayer.Transforms CUSTOM_HEAD_TRANSFORMS = new CustomHeadLayer.Transforms(-0.1171875F, -0.07421875F, 1.0F);

    public ClientVillagerRenderer(EntityRendererProvider.Context context) {
        super(context, new VillagerModel(context.bakeLayer(ModelLayers.VILLAGER)), 0.5F);
        this.addLayer(new CustomHeadLayer(this, context.getModelSet(), CUSTOM_HEAD_TRANSFORMS));
        this.addLayer(new CrossedArmsItemLayer(this));
        this.addLayer(new ClientVillagerDefaultLayer(this));
        this.addLayer(new ClientVillagerProfessionLayer(this));
    }

    public static LayerDefinition createBaseVillagerLayer() {
        VillagerModel.createBodyModel();
        return LayerDefinition.create(new MeshDefinition(), 64, 64);
    }

    @Override
    protected void scale(VillagerRenderState state, @NotNull PoseStack poseStack) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(VillagerRenderState villagerRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/villager/villager.png");
    }

    @Override
    public VillagerRenderState createRenderState() {
        return new VillagerRenderState();
    }
}
