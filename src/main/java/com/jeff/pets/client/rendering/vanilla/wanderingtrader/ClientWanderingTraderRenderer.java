package com.jeff.pets.client.rendering.vanilla.wanderingtrader;

import com.jeff.pets.mob.vanilla.passive.ClientWanderingTrader;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.state.VillagerRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientWanderingTraderRenderer extends PetRenderer<@NotNull ClientWanderingTrader, @NotNull VillagerRenderState, @NotNull VillagerModel> {
    public static final ModelLayerLocation WANDERING_TRADER_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientwanderingtrader"), "main");

    public ClientWanderingTraderRenderer(EntityRendererProvider.Context context) {
        super(context, new VillagerModel(context.bakeLayer(ModelLayers.WANDERING_TRADER)), 0.5F);
        this.addLayer(new CustomHeadLayer<>(this, context.getModelSet(), context.getPlayerSkinRenderCache()));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(VillagerRenderState villagerRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/wandering_trader.png");
    }

    @Override
    public VillagerRenderState createRenderState() {
        return new VillagerRenderState();
    }
}
