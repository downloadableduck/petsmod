package com.jeff.pets.client.rendering.vanilla.silverfish;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientSilverfish;
import net.minecraft.client.render.entity.model.SilverfishEntityModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientSilverfishRenderer extends PetRenderer<@NotNull ClientSilverfish, @NotNull SilverfishEntityModel<ClientSilverfish>> {

    public ClientSilverfishRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new SilverfishEntityModel<>(), 0.75f);
    }

    @Override
    public @NotNull Identifier getTexture(ClientSilverfish livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/silverfish.png");
    }
}
