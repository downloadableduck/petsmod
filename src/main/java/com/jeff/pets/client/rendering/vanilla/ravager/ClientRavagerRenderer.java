package com.jeff.pets.client.rendering.vanilla.ravager;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientRavager;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientRavagerRenderer extends PetRenderer<@NotNull ClientRavager, @NotNull ClientRavagerModel> {

    public ClientRavagerRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new ClientRavagerModel(), 0.75f);
    }

    @Override
    public @NotNull Identifier getTexture(ClientRavager livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/illager/ravager.png");
    }
}
