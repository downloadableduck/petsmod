package com.jeff.pets.client.rendering.vanilla.wanderingtrader;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientWanderingTrader;
import net.minecraft.client.renderer.entity.model.VillagerModel;
import net.minecraft.util.ResourceLocation;

public class ClientWanderingTraderRenderer extends PetRenderer<ClientWanderingTrader, VillagerModel<ClientWanderingTrader>> {

    public ClientWanderingTraderRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context) {
        super(context, new VillagerModel<>(0), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(ClientWanderingTrader villagerRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/wandering_trader.png");
    }
}
