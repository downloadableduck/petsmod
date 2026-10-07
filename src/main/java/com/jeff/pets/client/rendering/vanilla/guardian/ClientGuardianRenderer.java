package com.jeff.pets.client.rendering.vanilla.guardian;

import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.entity.Entity; import net.minecraft.client.renderer.entity.RenderManager; import com.jeff.pets.client.PetsClientInitializer;
import net.minecraft.util.ResourceLocation;


public class ClientGuardianRenderer extends PetRenderer {

    public ClientGuardianRenderer(RenderManager renderManager, PetsClientInitializer.Context context) {
        super(new ClientGuardianModel(), 0.75f);
    }

    @Override
    public ResourceLocation getEntityTexture( final Entity livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/guardian.png");
    }
}

