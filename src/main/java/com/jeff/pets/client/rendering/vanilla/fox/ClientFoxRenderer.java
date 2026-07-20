package com.jeff.pets.client.rendering.vanilla.fox;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.neutral.ClientFox;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientFoxRenderer extends PetRenderer<@NotNull ClientFox, @NotNull ClientFoxModel> {
    public String foxTexturePath;

    public ClientFoxRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientFoxModel(), 0.75f);
    }

    @Override
    protected void applyScale(ClientFox state, float f) {
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientFox livingEntityRenderState) {
        if (Objects.equals(CONFIG.foxSkin, "red")) {
            foxTexturePath = "textures/entity/fox/fox.png";
        } else if (Objects.equals(CONFIG.foxSkin, "snow")) {
            foxTexturePath = "textures/entity/fox/fox_snow.png";
        } else {
            foxTexturePath = "textures/entity/fox/fox.png";
        }
        return new Identifier("minecraft", foxTexturePath);
    }

    @Override
    public void renderModel(ClientFox fox, float f, float g, float h, float i, float j, float k) {
        super.renderModel(fox, f, g, h, i, j, k);
        //fox.setPose(fox.isPassenger() ? Pose.SLEEPING  : fox.getPose());
    }
}
