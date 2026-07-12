package com.jeff.pets.client.rendering.vanilla.vex;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientVex;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientVexRenderer extends PetRenderer<@NotNull ClientVex, @NotNull ClientVexModel> {

    public ClientVexRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context) {
        super(context, new ClientVexModel(), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientVex livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/illager/vex.png");
    }
}
