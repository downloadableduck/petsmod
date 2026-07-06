package com.jeff.pets.client.rendering.vanilla.zombievillager;

import com.jeff.pets.mob.vanilla.hostile.ClientZombieVillager;
import com.jeff.pets.client.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientZombieVillagerRenderer extends PetRenderer<@NotNull ClientZombieVillager, @NotNull ClientZombieVillagerModel> {

    public static final ModelLayerLocation ZOMBIE_VILLAGER_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientzombievillager"), "main");

    public ClientZombieVillagerRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientZombieVillagerModel(context.bakeLayer(ModelLayers.ZOMBIE_VILLAGER)), 0.75f);
        this.addLayer((RenderLayer) new ClientZombieVillagerProfessionLayer((RenderLayerParent) this));
    }

    @Override
    protected void scale(@NotNull ClientZombieVillager livingEntityRenderState, @NotNull PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientZombieVillager livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/zombie_villager/zombie_villager.png");
    }

    @Override
    public void setupRotations(ClientZombieVillager state, @NotNull PoseStack poseStack, float f, float g, float h) {
        super.setupRotations(state, poseStack, f, g, h);
        if (state.isPassenger()) {
            poseStack.translate(0, -0.5, 0);
        }
    }
}
