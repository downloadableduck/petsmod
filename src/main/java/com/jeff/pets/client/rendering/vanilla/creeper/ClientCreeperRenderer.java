package com.jeff.pets.client.rendering.vanilla.creeper;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientCreeper;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCreeperRenderer extends PetRenderer<ClientCreeper, ModelCreeper> {

    public ClientCreeperRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ModelCreeper(), 0.75f);
        this.addLayer(new ClientCreeperChargeLayer(this));
    }

    @Override
    public ResourceLocation getEntityTexture(Entity __e) {
        ClientCreeper livingEntityRenderState = (ClientCreeper) __e;
        return new ResourceLocation("minecraft", "textures/entity/creeper/creeper.png");
    }

    @Override
    public void renderModel(EntityLivingBase __e, float f, float g, float h, float i, float j, float k) {
        ClientCreeper creeper = (ClientCreeper) __e;
        super.renderModel(creeper, f, g, h, i, j, k);
        creeper.isPowered = Objects.equals(CONFIG.creeperSkin, "charged");
    }
}
