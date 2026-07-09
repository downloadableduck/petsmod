package com.jeff.pets.client.rendering.vanilla.elderguardian;

import com.jeff.pets.mob.vanilla.hostile.ClientElderGuardian;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.guardian.ClientGuardianModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;


public class ClientElderGuardianRenderer extends PetRenderer< ClientElderGuardian,  ClientGuardianModel<ClientElderGuardian>> {
    public static final ModelLayerLocation ELDER_GUARDIAN_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientelderguardian"), "main");

    public ClientElderGuardianRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientGuardianModel<>(context.bakeLayer(ModelLayers.ELDER_GUARDIAN)), 0.75f);
    }

    @Override
    public void scale(ClientElderGuardian elderGuardian, PoseStack poseStack, float f) {
        poseStack.scale(2.35f, 2.35f, 2.35f);
    }

    @Override
    public  ResourceLocation getTextureLocation(ClientElderGuardian livingEntityRenderState) {
        //livingEntityRenderState.spike = 1;
        return new ResourceLocation("minecraft", "textures/entity/guardian_elder.png");
    }
}
