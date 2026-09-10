package com.jeff.pets.client.rendering.vanilla.guardian;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientGuardian;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientGuardianRenderer extends PetRenderer<@NotNull ClientGuardian> {

    public ClientGuardianRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientGuardianModel(), 0.7F);
    }

    @Override
    public @NotNull Identifier getTexture(ClientGuardian livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/guardian.png");
    }
}