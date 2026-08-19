package com.jeff.pets.rendering.vanilla.parrot;

import com.jeff.pets.mob.vanilla.passive.ClientParrot;
import com.jeff.pets.rendering.PetRenderer;
import net.minecraft.client.model.animal.parrot.ParrotModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ParrotRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.Central.CONFIG;

public class ClientParrotRenderer extends PetRenderer<@NotNull ClientParrot, @NotNull ParrotRenderState, @NotNull ParrotModel> {
    public static final ModelLayerLocation PARROT_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientparrot"), "main");

    String parrotTexturePath;

    public ClientParrotRenderer(EntityRendererProvider.Context context) {
        super(context, new ParrotModel(context.bakeLayer(ModelLayers.PARROT)), 0.3F);
    }

    public @NotNull Identifier getTextureLocation(ParrotRenderState parrotRenderState) {
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
        return Identifier.withDefaultNamespace(parrotTexturePath);

    }

    public ParrotRenderState createRenderState() {
        return new ParrotRenderState();
    }

    @Override
    public void extractRenderState(ClientParrot parrot, ParrotRenderState state, float f) {
        super.extractRenderState(parrot, state, f);
        float flap = Mth.lerp(f, parrot.oFlap, parrot.flap);
        float flapSpeed = Mth.lerp(f, parrot.oFlapSpeed, parrot.flapSpeed);
        state.flapAngle = (Mth.sin(flap) + 1.0F) * flapSpeed;
    }
}
