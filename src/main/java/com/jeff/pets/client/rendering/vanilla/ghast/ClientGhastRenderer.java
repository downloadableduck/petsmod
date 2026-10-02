package com.jeff.pets.client.rendering.vanilla.ghast;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientGhast;
import net.minecraft.client.model.ModelGhast;
import net.minecraft.util.ResourceLocation;

public class ClientGhastRenderer extends PetRenderer<ClientGhast, ModelGhast> {

    public ClientGhastRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ModelGhast(), 0.75f);
    }

    @Override
    public ResourceLocation getEntityTexture(Entity __e) {
        ClientGhast livingEntityRenderState = (ClientGhast) __e;
        return new ResourceLocation("minecraft", "textures/entity/ghast/ghast.png");
    }

    @Override
    public void preRenderCallback(EntityLivingBase __e, float f) {
        ClientGhast ghast = (ClientGhast) __e;
        net.minecraft.client.renderer.GlStateManager.scalef(4.5F, 4.5F, 4.5F);

    }
}
