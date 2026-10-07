package com.jeff.pets.client.rendering.vanilla.zombie_pigman;

import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.entity.Entity; import net.minecraft.client.renderer.entity.RenderManager; import com.jeff.pets.client.PetsClientInitializer;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class  ClientZOmbiePigmanRenderer extends PetRenderer {
    public ClientZOmbiePigmanRenderer(RenderManager renderManager, PetsClientInitializer.Context context) {
        super(new ClientZombiePigmanModel(), 0.75f);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture( final Entity p_110775_1_) {
        return new ResourceLocation("minecraft", "textures/entity/zombie_pigman.png");
    }
}

