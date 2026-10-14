package com.jeff.pets.client.rendering.vanilla.wither;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.boss.ClientWither;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientWitherRenderer extends PetRenderer<ClientWither> {

    public ClientWitherRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientWitherModel(0), 0.75f);

    }

    @Override
    public @NotNull String getTexture(net.minecraft.entity.Entity entity) {
        ClientWither livingEntityRenderState = (ClientWither) entity;
        String witherTexturePath;
        if (Objects.equals(CONFIG.witherSkin, "normal")) {
            witherTexturePath = "textures/mob/wither/wither.png";
        } else if (Objects.equals(CONFIG.witherSkin, "invulnerable")) {
            witherTexturePath = "textures/mob/wither/wither_invulnerable.png";
        } else {
            witherTexturePath = "textures/mob/wither/wither.png";
        }
        return (witherTexturePath);
    }

    /*@Override
    public void extractRenderState(ClientWither wither, WitherRenderState state, float f) {
        super.extractRenderState(wither, state, f);
        state.yHeadRots = new float[]{wither.getYHeadRot(), wither.getYHeadRot(), wither.getYHeadRot()};
    }*/
}
