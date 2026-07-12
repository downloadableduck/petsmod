package com.jeff.pets.client.rendering.vanilla.wanderingtrader;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientWanderingTrader;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientWanderingTraderRenderer extends PetRenderer<@NotNull ClientWanderingTrader, @NotNull VillagerModel<ClientWanderingTrader>> {

    public ClientWanderingTraderRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context) {
        super(context, new VillagerModel<>(0), 0.5F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientWanderingTrader villagerRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/wandering_trader.png");
    }
}
