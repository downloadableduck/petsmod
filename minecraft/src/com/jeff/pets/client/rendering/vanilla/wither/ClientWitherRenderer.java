package com.jeff.pets.client.rendering.vanilla.wither;

import net.minecraft.src.Entity;
import com.jeff.pets.client.rendering.PetRenderer;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientWitherRenderer extends PetRenderer {

    public ClientWitherRenderer() {
        super(new ClientWitherModel(0), 0.75f);

    }

    @Override
    public String getEntityTexture( final Entity livingEntityRenderState) {
        String witherTexturePath;
        if (Objects.equals(CONFIG.witherSkin, "normal")) {
            witherTexturePath = "textures/entity/wither/wither.png";
        } else if (Objects.equals(CONFIG.witherSkin, "invulnerable")) {
            witherTexturePath = "textures/entity/wither/wither_invulnerable.png";
        } else {
            witherTexturePath = "textures/entity/wither/wither.png";
        }
        return witherTexturePath;
    }

    /*@Override
    public void extractRenderState(ClientWither wither, WitherRenderState state, float f) {
        super.extractRenderState(wither, state, f);
        state.yHeadRots = new float[]{wither.rotationYawHead, wither.rotationYawHead, wither.rotationYawHead};
    }*/
}

