package com.jeff.pets.client.rendering.vanilla.rabbit;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientRabbit;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientRabbitRenderer extends PetRenderer<ClientRabbit, ClientRabbitModel> {
    public String rabbitTextureLocation;

    public ClientRabbitRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientRabbitModel(), 0.3F);
    }

    @Override
    public void preRenderCallback(ClientRabbit livingEntityRenderState, float f) {
        if (livingEntityRenderState.isChild()) {
            net.minecraft.client.renderer.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }

    }

    @Override
    public ResourceLocation getEntityTexture(ClientRabbit rabbitRenderState) {
        if (rabbitRenderState.petSkin.equals("brown")) {
            rabbitTextureLocation = "textures/entity/rabbit/brown.png";
        } else if (rabbitRenderState.petSkin.equals("white")) {
            rabbitTextureLocation = "textures/entity/rabbit/white.png";
        } else if (rabbitRenderState.petSkin.equals("black")) {
            rabbitTextureLocation = "textures/entity/rabbit/black.png";
        } else if (rabbitRenderState.petSkin.equals("gold")) {
            rabbitTextureLocation = "textures/entity/rabbit/gold.png";
        } else if (rabbitRenderState.petSkin.equals("salt")) {
            rabbitTextureLocation = "textures/entity/rabbit/salt.png";
        } else if (rabbitRenderState.petSkin.equals("splotched")) {
            rabbitTextureLocation = "textures/entity/rabbit/white_splotched.png";
        } else if (rabbitRenderState.petSkin.equals("killer")) {
            rabbitTextureLocation = "textures/entity/rabbit/caerbannog.png";
        } else if (rabbitRenderState.petSkin.equals("toast")) {
            rabbitTextureLocation = "textures/entity/rabbit/toast.png";
        } else {
            rabbitTextureLocation = "textures/entity/rabbit/brown.png";
        }

        return new ResourceLocation("minecraft", rabbitTextureLocation);
    }
}
