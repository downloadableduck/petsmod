package com.jeff.pets.client.rendering.vanilla.pillager;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientPillager;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientPillagerRenderer extends PetRenderer<@NotNull ClientPillager, @NotNull ClientPillagerModel> {

    public ClientPillagerRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context) {
        super(context, new ClientPillagerModel(), 0.75f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientPillager livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/illager/pillager.png");
    }
}
