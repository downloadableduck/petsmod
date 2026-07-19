package com.jeff.pets.client.rendering.vanilla.husk;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.zombie.ClientZombieModel;
import com.jeff.pets.mob.vanilla.hostile.ClientHusk;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientHuskRenderer extends PetRenderer<@NotNull ClientHusk, @NotNull ClientZombieModel<ClientHusk>> {

    public ClientHuskRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context) {
        super(context, new ClientZombieModel<>(), 0.75F);
    }

    @Override
    protected void applyScale(ClientHusk state, float f) {
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientHusk livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/zombie/husk.png");
    }

    @Override
    public void applyRotation(ClientHusk husk, float f, float g, float i) {
        super.applyRotation(husk, f, g, i);
        if (husk.isRiding()) {
            com.mojang.blaze3d.platform.GlStateManager.translate(0, -0.5f, 0);
        }
    }
}
