package com.jeff.pets.client.rendering.vanilla.rabbit;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientRabbit;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientRabbitRenderer extends PetRenderer<@NotNull ClientRabbit, @NotNull ClientRabbitModel> {
    public String rabbitTextureLocation;

    public ClientRabbitRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new ClientRabbitModel(), 0.3F);
    }

    @Override
    protected void scale(@NotNull ClientRabbit livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTexture(ClientRabbit rabbitRenderState) {
        if (CONFIG.activePet.equals("brown")) {
            rabbitTextureLocation = "textures/entity/rabbit/brown.png";
        } else if (CONFIG.activePet.equals("white")) {
            rabbitTextureLocation = "textures/entity/rabbit/white.png";
        } else if (CONFIG.activePet.equals("black")) {
            rabbitTextureLocation = "textures/entity/rabbit/black.png";
        } else if (CONFIG.activePet.equals("gold")) {
            rabbitTextureLocation = "textures/entity/rabbit/gold.png";
        } else if (CONFIG.activePet.equals("salt")) {
            rabbitTextureLocation = "textures/entity/rabbit/salt.png";
        } else if (CONFIG.activePet.equals("splotched")) {
            rabbitTextureLocation = "textures/entity/rabbit/white_splotched.png";
        } else if (CONFIG.activePet.equals("killer")) {
            rabbitTextureLocation = "textures/entity/rabbit/caerbannog.png";
        } else if (CONFIG.activePet.equals("toast")) {
            rabbitTextureLocation = "textures/entity/rabbit/toast.png";
        } else {
            rabbitTextureLocation = "textures/entity/rabbit/brown.png";
        }

        return new Identifier("minecraft", rabbitTextureLocation);
    }
}
