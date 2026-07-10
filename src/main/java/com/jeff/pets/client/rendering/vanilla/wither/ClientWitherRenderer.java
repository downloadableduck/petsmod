package com.jeff.pets.client.rendering.vanilla.wither;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.boss.ClientWither;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientWitherRenderer extends PetRenderer<@NotNull ClientWither, @NotNull ClientWitherModel<ClientWither>> {

    public ClientWitherRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry.Context context2) {
        super(context, new ClientWitherModel<>(0), 0.75f);

    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientWither livingEntityRenderState) {
        String witherTexturePath;
        if (Objects.equals(CONFIG.witherSkin, "normal")) {
            witherTexturePath = "textures/entity/wither/wither.png";
        } else if (Objects.equals(CONFIG.witherSkin, "invulnerable")) {
            witherTexturePath = "textures/entity/wither/wither_invulnerable.png";
        } else {
            witherTexturePath = "textures/entity/wither/wither.png";
        }
        return new ResourceLocation("minecraft", witherTexturePath);
    }

    /*@Override
    public void extractRenderState(ClientWither wither, WitherRenderState state, float f) {
        super.extractRenderState(wither, state, f);
        state.yHeadRots = new float[]{wither.getYHeadRot(), wither.getYHeadRot(), wither.getYHeadRot()};
    }*/
}
