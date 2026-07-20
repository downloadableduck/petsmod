package com.jeff.pets.client.rendering.vanilla.wanderingtrader;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientWanderingTrader;
import net.minecraft.client.render.model.entity.VillagerModel;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientWanderingTraderRenderer extends PetRenderer<@NotNull ClientWanderingTrader, @NotNull VillagerModel<ClientWanderingTrader>> {

    public ClientWanderingTraderRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new VillagerModel<>(0), 0.5F);
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientWanderingTrader villagerRenderState) {
        return new Identifier("minecraft", "textures/entity/wandering_trader.png");
    }
}
