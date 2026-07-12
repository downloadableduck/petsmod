package com.jeff.pets.client.rendering.vanilla.silverfish;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientSilverfish;
import net.minecraft.client.model.SilverfishModel;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientSilverfishRenderer extends PetRenderer<@NotNull ClientSilverfish, @NotNull SilverfishModel<ClientSilverfish>> {

    public ClientSilverfishRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context) {
        super(context, new SilverfishModel<>(), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientSilverfish livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/silverfish.png");
    }
}
