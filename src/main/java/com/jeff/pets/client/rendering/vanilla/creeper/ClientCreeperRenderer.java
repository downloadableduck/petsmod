package com.jeff.pets.client.rendering.vanilla.creeper;

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
    public ResourceLocation getEntityTexture(ClientCreeper livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/creeper/creeper.png");
    }

    @Override
    public void renderModel(ClientCreeper creeper, float f, float g, float h, float i, float j, float k) {
        super.renderModel(creeper, f, g, h, i, j, k);
        creeper.isPowered = Objects.equals(CONFIG.creeperSkin, "charged");
    }
}
