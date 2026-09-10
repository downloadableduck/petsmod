package com.jeff.pets.client.rendering.vanilla.bat;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientBat;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientBatRenderer extends PetRenderer<@NotNull ClientBat> {

    public ClientBatRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientBatModel(), 0.25F);
    }

    @Override
    protected void scale(ClientBat bat, float f) {
        super.scale(bat, f);
        com.mojang.blaze3d.platform.GlStateManager.scale(0.35F, 0.35F, 0.35F);
    }

    @Override
    public @NotNull Identifier getTexture(ClientBat batRenderState) {
        return new Identifier("minecraft", "textures/entity/bat.png");
    }
}