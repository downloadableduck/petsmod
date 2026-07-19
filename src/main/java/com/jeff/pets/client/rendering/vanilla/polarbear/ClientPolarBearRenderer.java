package com.jeff.pets.client.rendering.vanilla.polarbear;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientPolarBear;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientPolarBearRenderer extends PetRenderer<@NotNull ClientPolarBear, @NotNull ClientPolarBearModel> {

    public ClientPolarBearRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context) {
        super(context, new ClientPolarBearModel(), 0.75f);
    }

    @Override
    protected void applyScale(@NotNull ClientPolarBear livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientPolarBear livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/bear/polarbear.png");
    }
}
