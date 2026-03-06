package com.jeff.pets.vanilla.wanderingtrader;

import com.jeff.pets.vanilla.passive.ClientWanderingTrader;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientWanderingTraderRenderer extends MobRenderer<@NotNull ClientWanderingTrader, @NotNull LivingEntityRenderState, @NotNull ClientWanderingTraderModel> {
    private static final Identifier VILLAGER_BASE_SKIN = Identifier.withDefaultNamespace("textures/entity/wandering_trader.png");

    public ClientWanderingTraderRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientWanderingTraderModel(context.bakeLayer(ModelLayers.WANDERING_TRADER)), 0.5F);
        this.addLayer(new CustomHeadLayer(this, context.getModelSet(), context.getPlayerSkinRenderCache()));
    }

    public @NotNull Identifier getTextureLocation(LivingEntityRenderState villagerRenderState) {
        return VILLAGER_BASE_SKIN;
    }

    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }
}
