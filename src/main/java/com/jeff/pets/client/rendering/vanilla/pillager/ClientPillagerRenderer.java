package com.jeff.pets.client.rendering.vanilla.pillager;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientPillager;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientPillagerRenderer extends PetRenderer<@NotNull ClientPillager, @NotNull ClientPillagerModel> {

    public ClientPillagerRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientPillagerModel(), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientPillager livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/illager/pillager.png");
    }
}
