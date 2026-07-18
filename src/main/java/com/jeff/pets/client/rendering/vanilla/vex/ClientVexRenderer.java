package com.jeff.pets.client.rendering.vanilla.vex;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientVex;
import net.minecraft.client.render.entity.VexEntityRenderer;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientVexRenderer extends PetRenderer<@NotNull ClientVex, @NotNull ClientVexModel> {

    public ClientVexRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new ClientVexModel(), 0.75f);
    }

    @Override
    public @NotNull Identifier getTexture(ClientVex livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/illager/vex.png");
    }
}
