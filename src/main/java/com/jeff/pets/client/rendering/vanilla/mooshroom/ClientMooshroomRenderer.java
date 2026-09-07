package com.jeff.pets.client.rendering.vanilla.mooshroom;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.cow.ClientCowModel;
import com.jeff.pets.mob.vanilla.passive.ClientMooshroom;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientMooshroomRenderer extends PetRenderer<ClientMooshroom, ClientCowModel> {

    String mooshroomTexturePath;

    public ClientMooshroomRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientCowModel(), 0.7F);
        this.addLayer(new ClientMushroomCowMushroomLayer(this, Minecraft.getInstance().getBlockRendererDispatcher()));
    }

    @Override
    public void preRenderCallback(ClientMooshroom state, float f) {
        if (CONFIG.isBaby) {
            net.minecraft.client.renderer.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }

    }

    @Override
    public ResourceLocation getEntityTexture(ClientMooshroom cowRenderState) {
        mooshroomTexturePath = "textures/entity/cow/mooshroom.png";
        return new ResourceLocation("minecraft", mooshroomTexturePath);
    }
}
