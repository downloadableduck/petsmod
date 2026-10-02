package com.jeff.pets.client.rendering.vanilla.mooshroom;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
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
    public void preRenderCallback(EntityLivingBase __e, float f) {
        ClientMooshroom state = (ClientMooshroom) __e;
        if (CONFIG.isBaby) {
            net.minecraft.client.renderer.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }

    }

    @Override
    public ResourceLocation getEntityTexture(Entity __e) {
        ClientMooshroom cowRenderState = (ClientMooshroom) __e;
        mooshroomTexturePath = "textures/entity/cow/mooshroom.png";
        return new ResourceLocation("minecraft", mooshroomTexturePath);
    }
}
