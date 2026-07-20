package com.jeff.pets.client.rendering.vanilla.chicken;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientChicken;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientChickenRenderer extends PetRenderer<@NotNull ClientChicken, @NotNull ClientChickenModel<ClientChicken>> {

    public ClientChickenRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientChickenModel<>(), 0.3F);
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientChicken livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/chicken.png");
    }

    @Override
    protected void applyScale(ClientChicken state, float f) {
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scale(0.5f, 0.5f, 0.5f);
        }
    }
}
