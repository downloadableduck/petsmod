package com.jeff.pets.client.rendering.vanilla.creeper;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.ModelCreeper;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientCreeper;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCreeperRenderer extends PetRenderer {

    public ClientCreeperRenderer() {
        super(new ModelCreeper(), 0.75f);
        this.setPetLayer(new ClientCreeperChargeLayer(this));
    }

    @Override
    public String getEntityTexture( final Entity livingEntityRenderState) {
        return "textures/entity/creeper/creeper.png";
    }

    @Override
    public void renderModel( final EntityLiving creeper, float f, float g, float h, float i, float j, float k) {
        super.renderModel(creeper, f, g, h, i, j, k);
        ((ClientCreeper) creeper).isPowered = Objects.equals(CONFIG.creeperSkin, "charged");
    }
}

