package com.jeff.pets.vanilla.parrot;

import com.jeff.pets.vanilla.passive.ClientParrot;
import net.minecraft.client.model.animal.parrot.ParrotModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.ParrotRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.Pet.CONFIG;

public class ClientParrotRenderer extends MobRenderer<@NotNull ClientParrot, @NotNull ParrotRenderState, @NotNull ParrotModel> {
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
    public void extractRenderState(ClientParrot parrot, ParrotRenderState parrotRenderState, float f) {
        super.extractRenderState(parrot, parrotRenderState, f);
        float g = Mth.lerp(f, parrot.oFlap, parrot.flap);
        float h = Mth.lerp(f, parrot.oFlapSpeed, parrot.flapSpeed);
        parrotRenderState.flapAngle = (Mth.sin(g) + 1.0F) * h;
    }
}
