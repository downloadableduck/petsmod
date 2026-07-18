package com.jeff.pets.client.rendering.vanilla.polarbear;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientPolarBear;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientPolarBearRenderer extends PetRenderer<@NotNull ClientPolarBear, @NotNull ClientPolarBearModel> {

    public ClientPolarBearRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new ClientPolarBearModel(), 0.75f);
    }

    @Override
    protected void scale(@NotNull ClientPolarBear livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTexture(ClientPolarBear livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/bear/polarbear.png");
    }
}
