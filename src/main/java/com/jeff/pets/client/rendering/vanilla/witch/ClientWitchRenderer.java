package com.jeff.pets.client.rendering.vanilla.witch;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientWitch;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientWitchRenderer extends PetRenderer<@NotNull ClientWitch> {

    public ClientWitchRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new net.minecraft.client.render.entity.model.WitchEntityModel(0), 0.75f);
    }

    @Override
    protected void scale(ClientWitch witch, float f) {
        super.scale(witch, f);
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scale(0.25f, 0.25f, 0.25f);
        }
    }

    @Override
    public @NotNull Identifier getTexture(ClientWitch livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/witch.png");
    }
}