package com.jeff.pets.client.rendering.custom.aquatic.stingray;

import net.minecraft.entity.Entity;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.aquatic.Stingray;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.MOD_ID;

public class StingrayRenderer extends PetRenderer {

    public StingrayRenderer() {
        super(new StingrayModel(), 0.75f);
    }

    @Override
    public ResourceLocation getEntityTexture( final Entity state) {
        return new ResourceLocation(MOD_ID, "textures/entity/stingray/stingray.png");
    }
}

