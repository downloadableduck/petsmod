package com.jeff.pets.client.rendering.vanilla.bat;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientBat;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientBatRenderer extends PetRenderer<@NotNull ClientBat, @NotNull ClientBatModel> {

    public ClientBatRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientBatModel(), 0.25F);
    }

    @Override
    protected void applyScale(ClientBat bat, float f) {
        super.applyScale(bat, f);
        com.mojang.blaze3d.platform.GlStateManager.scalef(0.35F, 0.35F, 0.35F);
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientBat batRenderState) {
        return new Identifier("minecraft", "textures/entity/bat.png");
    }
}
