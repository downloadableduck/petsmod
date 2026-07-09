package com.jeff.pets.client.rendering.vanilla.strider;

import com.jeff.pets.mob.vanilla.passive.ClientStrider;
import com.jeff.pets.client.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;


import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientStriderRenderer extends PetRenderer< ClientStrider,  ClientStriderMOdel> {
    public static ModelLayerLocation STRIDER_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientstrider"), "main");

    public String striderTexturePath;

    public ClientStriderRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientStriderMOdel(context.bakeLayer(ModelLayers.STRIDER)), 0.5F);
    }

    @Override
    protected void scale( ClientStrider livingEntityRenderState,  PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    public  ResourceLocation getTextureLocation(ClientStrider striderRenderState) {
        if (Objects.equals(CONFIG.striderSkin, "warm")) {
            striderTexturePath = "textures/entity/strider/strider.png";
        } else if (Objects.equals(CONFIG.striderSkin, "cold")) {
            striderTexturePath = "textures/entity/strider/strider_cold.png";
        }
        return new ResourceLocation("minecraft", striderTexturePath);
    }
}
