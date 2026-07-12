package com.jeff.pets.client.rendering.vanilla.parrot;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientParrot;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientParrotRenderer extends PetRenderer<@NotNull ClientParrot, @NotNull ClientParrotModel> {

    String parrotTexturePath;

    public ClientParrotRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context) {
        super(context, new ClientParrotModel(), 0.3F);
    }

    public @NotNull ResourceLocation getTextureLocation(ClientParrot parrotRenderState) {
        if (Objects.equals(CONFIG.parrotSkin, "red")) {
            parrotTexturePath = "textures/entity/parrot/parrot_red_blue.png";
        } else if (Objects.equals(CONFIG.parrotSkin, "blue")) {
            parrotTexturePath = "textures/entity/parrot/parrot_blue.png";
        } else if (Objects.equals(CONFIG.parrotSkin, "green")) {
            parrotTexturePath = "textures/entity/parrot/parrot_green.png";
        } else if (Objects.equals(CONFIG.parrotSkin, "yellow")) {
            parrotTexturePath = "textures/entity/parrot/parrot_yellow_blue.png";
        } else if (Objects.equals(CONFIG.parrotSkin, "gray")) {
            parrotTexturePath = "textures/entity/parrot/parrot_gray.png";
        }
        return new ResourceLocation("minecraft", parrotTexturePath);

    }


    /*@Override
    public void extractRenderState(ClientParrot parrot, ParrotRenderState state, float f) {
        super.extractRenderState(parrot, state, f);
        float flap = Mth.lerp(f, parrot.oFlap, parrot.flap);
        float flapSpeed = Mth.lerp(f, parrot.oFlapSpeed, parrot.flapSpeed);
        parrot.flapAngle = (Mth.sin(flap) + 1.0F) * flapSpeed;
    }*/
}
