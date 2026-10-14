package com.jeff.pets.client.rendering.vanilla.witherskeleton;

import net.minecraft.src.Entity;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.skeleton.ModelSkeleton;

public class ClientWitherSkeletonRenderer extends PetRenderer {

    public ClientWitherSkeletonRenderer() {
        super(new ModelSkeleton(), 0.75f);
    }

    @Override
    public String getEntityTexture( final Entity livingEntityRenderState) {
        return "textures/entity/skeleton/wither_skeleton.png";
    }
}

