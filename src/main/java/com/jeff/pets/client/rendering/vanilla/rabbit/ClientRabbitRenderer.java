package com.jeff.pets.client.rendering.vanilla.rabbit;

import com.jeff.pets.mob.vanilla.passive.ClientRabbit;
import com.jeff.pets.client.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.RabbitRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientRabbitRenderer extends PetRenderer<@NotNull ClientRabbit, @NotNull RabbitRenderState, @NotNull ClientRabbitModel> {
    public static final ModelLayerLocation RABBIT_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientrabbit"), "main");
    public String rabbitTextureLocation;

    public ClientRabbitRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientRabbitModel(context.bakeLayer(ModelLayers.RABBIT)), 0.3F);
    }

    public static LayerDefinition createBaseRabbitLayer() {
        ClientRabbitModel.createBodyLayer(false);
        return LayerDefinition.create(new MeshDefinition(), 64, 32);
    }

    @Override
    protected void scale(@NotNull RabbitRenderState livingEntityRenderState, @NotNull PoseStack poseStack) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTextureLocation(RabbitRenderState rabbitRenderState) {
        switch (CONFIG.activePet) {
            case "brown" -> rabbitTextureLocation = "textures/entity/rabbit/brown.png";
            case "white" -> rabbitTextureLocation = "textures/entity/rabbit/rabbit_white.png";
            case "black" -> rabbitTextureLocation = "textures/entity/rabbit/rabbit_black.png";
            case "gold" -> rabbitTextureLocation = "textures/entity/rabbit/rabbit_gold.png";
            case "salt" -> rabbitTextureLocation = "textures/entity/rabbit/rabbit_salt.png";
            case "splotched" -> rabbitTextureLocation = "textures/entity/rabbit/rabbit_white_splotched.png";
            case "killer" -> rabbitTextureLocation = "textures/entity/rabbit/rabbit_caerbannog.png";
            case "toast" -> rabbitTextureLocation = "textures/entity/rabbit/rabbit_toast.png";
            case null, default -> rabbitTextureLocation = "textures/entity/rabbit/rabbit_brown.png";
        }

        return Identifier.withDefaultNamespace(rabbitTextureLocation);
    }

    @Override
    public RabbitRenderState createRenderState() {
        return new RabbitRenderState();
    }

    @Override
    public void extractRenderState(ClientRabbit rabbit, RabbitRenderState state, float f) {
        super.extractRenderState(rabbit, state, f);
    }
}
