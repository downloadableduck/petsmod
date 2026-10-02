package com.jeff.pets.client.rendering.vanilla.pig;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientPig;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientPigRenderer extends PetRenderer<ClientPig, ClientPigModel> {
    public String pigTexturePath;

    public ClientPigRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientPigModel(), 0.7F);
    }

    @Override
    public void preRenderCallback(EntityLivingBase __e, float f) {
        ClientPig livingEntityRenderState = (ClientPig) __e;
        if (CONFIG.isBaby) {
            net.minecraft.client.renderer.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }

    }

    public ResourceLocation getEntityTexture(Entity __e) {
        ClientPig pigRenderState = (ClientPig) __e;
        return new ResourceLocation("minecraft", "textures/entity/pig/pig.png");
    }
}
