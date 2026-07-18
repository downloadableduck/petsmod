package com.jeff.pets.client.rendering.vanilla.pillager;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientPillager;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientPillagerRenderer extends PetRenderer<@NotNull ClientPillager, @NotNull ClientPillagerModel> {

    public ClientPillagerRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new ClientPillagerModel(), 0.75f);
    }

    @Override
    public @NotNull Identifier getTexture(ClientPillager livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/illager/pillager.png");
    }
}
