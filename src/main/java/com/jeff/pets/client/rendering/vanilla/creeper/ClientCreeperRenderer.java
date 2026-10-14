package com.jeff.pets.client.rendering.vanilla.creeper;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientCreeper;
import net.minecraft.client.render.model.entity.CreeperModel;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCreeperRenderer extends PetRenderer<ClientCreeper> {

    public ClientCreeperRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new CreeperModel(), 0.75f);
        this.addLayer(new ClientCreeperChargeLayer(this));
    }

    @Override
    public @NotNull String getTexture(net.minecraft.entity.Entity entity) {
        ClientCreeper livingEntityRenderState = (ClientCreeper) entity;
        return ("textures/mob/creeper/creeper.png");
    }

    @Override
    public void renderModel(net.minecraft.entity.mob.MobEntity entity, float f, float g, float h, float i, float j, float k) {
        ClientCreeper creeper = (ClientCreeper) entity;
        super.renderModel(creeper, f, g, h, i, j, k);
        creeper.isPowered = Objects.equals(CONFIG.creeperSkin, "charged");
    }
}
