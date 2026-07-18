package com.jeff.pets.client.rendering.vanilla.guardian;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientGuardian;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;


public class ClientGuardianRenderer extends PetRenderer<@NotNull ClientGuardian, @NotNull ClientGuardianModel<ClientGuardian>> {

    public ClientGuardianRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new ClientGuardianModel<>(), 0.75f);
    }

    @Override
    public @NotNull Identifier getTexture(ClientGuardian livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/guardian.png");
    }
}
