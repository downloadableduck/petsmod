package com.jeff.pets.client.rendering.vanilla.silverfish;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientSilverfish;
import net.minecraft.client.render.model.entity.SilverfishModel;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientSilverfishRenderer extends PetRenderer<@NotNull ClientSilverfish, @NotNull SilverfishModel<ClientSilverfish>> {

    public ClientSilverfishRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context) {
        super(context, new SilverfishModel<>(), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientSilverfish livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/silverfish.png");
    }
}
