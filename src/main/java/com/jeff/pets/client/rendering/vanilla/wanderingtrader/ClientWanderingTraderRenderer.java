package com.jeff.pets.client.rendering.vanilla.wanderingtrader;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientWanderingTrader;
import net.minecraft.client.render.entity.model.VillagerResemblingModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientWanderingTraderRenderer extends PetRenderer<@NotNull ClientWanderingTrader, @NotNull VillagerResemblingModel<ClientWanderingTrader>> {

    public ClientWanderingTraderRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new VillagerResemblingModel<>(0), 0.5F);
    }

    @Override
    public @NotNull Identifier getTexture(ClientWanderingTrader villagerRenderState) {
        return new Identifier("minecraft", "textures/entity/wandering_trader.png");
    }
}
