package com.jeff.pets.client.rendering.vanilla.creeper;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientCreeper;
import net.minecraft.client.render.entity.feature.CreeperChargeFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.CreeperEntityModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCreeperRenderer extends PetRenderer<@NotNull ClientCreeper, @NotNull CreeperEntityModel<ClientCreeper>> {

    public ClientCreeperRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new CreeperEntityModel<>(), 0.75f);
        this.addFeature(new ClientCreeperChargeLayer(this));
    }

    @Override
    public @NotNull Identifier getTexture(ClientCreeper livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/creeper/creeper.png");
    }

    @Override
    public void render(ClientCreeper creeper, float f, float g, float h, float i, float j, float k) {
        super.render(creeper, f, g, h, i, j, k);
        creeper.isPowered = Objects.equals(CONFIG.creeperSkin, "charged");
    }
}
