package com.jeff.pets.client.rendering.vanilla.creeper;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientCreeper;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.entity.Entity; import net.minecraft.client.renderer.entity.RenderManager; import com.jeff.pets.client.PetsClientInitializer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCreeperRenderer extends PetRenderer {

    public ClientCreeperRenderer(RenderManager renderManager, PetsClientInitializer.Context context) {
        super(new ModelCreeper(), 0.75f);
        this.setPetLayer(new ClientCreeperChargeLayer(this));
    }

    @Override
    public ResourceLocation getEntityTexture( final Entity livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/creeper/creeper.png");
    }

    @Override
    public void renderModel( final EntityLivingBase creeper, float f, float g, float h, float i, float j, float k) {
        super.renderModel(creeper, f, g, h, i, j, k);
        ((ClientCreeper) creeper).isPowered = Objects.equals(CONFIG.creeperSkin, "charged");
    }
}

