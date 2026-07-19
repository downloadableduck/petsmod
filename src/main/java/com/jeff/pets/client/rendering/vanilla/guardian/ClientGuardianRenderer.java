package com.jeff.pets.client.rendering.vanilla.guardian;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientGuardian;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;


public class ClientGuardianRenderer extends PetRenderer<@NotNull ClientGuardian, @NotNull ClientGuardianModel<ClientGuardian>> {

    public ClientGuardianRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context) {
        super(context, new ClientGuardianModel<>(), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientGuardian livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/guardian.png");
    }
}
