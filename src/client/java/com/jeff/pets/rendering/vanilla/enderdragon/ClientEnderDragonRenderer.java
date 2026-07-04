package com.jeff.pets.rendering.vanilla.enderdragon;

import com.jeff.pets.mob.vanilla.boss.ClientEnderDragon;
import com.jeff.pets.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Central.CONFIG;


public class ClientEnderDragonRenderer extends PetRenderer<@NotNull ClientEnderDragon, ClientEnderDragonModel> {

    public static final ModelLayerLocation ENDER_DRAGON_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientenderdragon"), "main");

    public ClientEnderDragonRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientEnderDragonModel(context.bakeLayer(ModelLayers.ENDER_DRAGON)), 0.75f);
    }

    @Override
    protected void scale(@NotNull ClientEnderDragon livingEntityRenderState, @NotNull PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.25f, 0.25f, 0.25f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientEnderDragon livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/enderdragon/dragon.png");
    }
}
