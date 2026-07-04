package com.jeff.pets.rendering.vanilla.wanderingtrader;

import com.jeff.pets.mob.vanilla.passive.ClientWanderingTrader;
import com.jeff.pets.rendering.PetRenderer;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientWanderingTraderRenderer extends PetRenderer<@NotNull ClientWanderingTrader, @NotNull VillagerModel<ClientWanderingTrader>> {
    public static final ModelLayerLocation WANDERING_TRADER_LOCATION = new ModelLayerLocation(new ResourceLocation("minecraft", "clientwanderingtrader"), "main");

    public ClientWanderingTraderRenderer(EntityRendererProvider.Context context) {
        super(context, new VillagerModel<>(context.bakeLayer(ModelLayers.WANDERING_TRADER)), 0.5F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientWanderingTrader villagerRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/wandering_trader.png");
    }
}
