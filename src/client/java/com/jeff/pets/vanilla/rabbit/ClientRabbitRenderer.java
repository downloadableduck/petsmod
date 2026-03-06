package com.jeff.pets.vanilla.rabbit;

import com.jeff.pets.vanilla.passive.ClientRabbit;
import net.minecraft.client.model.animal.rabbit.RabbitModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.state.RabbitRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Pet.CONFIG;

public class ClientRabbitRenderer extends MobRenderer<@NotNull ClientRabbit, @NotNull RabbitRenderState, @NotNull RabbitModel> {
    public static final ModelLayerLocation RABBIT_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientrabbit"), "main");
    public String rabbitTextureLocation;

    public ClientRabbitRenderer(EntityRendererProvider.Context context) {
        super(context, new RabbitModel(context.bakeLayer(ModelLayers.RABBIT)), 0.3F);
    }

    public static LayerDefinition createBaseRabbitLayer() {
        RabbitModel.createBodyLayer(false);
        return LayerDefinition.create(new MeshDefinition(), 64, 32);
    }

    public @NotNull Identifier getTextureLocation(RabbitRenderState rabbitRenderState) {
        switch (CONFIG.activePet) {
            case "brown" -> rabbitTextureLocation = "textures/entity/rabbit/brown.png";
            case "white" -> rabbitTextureLocation = "textures/entity/rabbit/white.png";
            case "black" -> rabbitTextureLocation = "textures/entity/rabbit/black.png";
            case "gold" -> rabbitTextureLocation = "textures/entity/rabbit/gold.png";
            case "salt" -> rabbitTextureLocation = "textures/entity/rabbit/salt.png";
            case "splotched" -> rabbitTextureLocation = "textures/entity/rabbit/white_splotched.png";
            case "killer" -> rabbitTextureLocation = "textures/entity/rabbit/caerbannog.png";
            case "toast" -> rabbitTextureLocation = "textures/entity/rabbit/toast.png";
            case null, default -> rabbitTextureLocation = "textures/entity/rabbit/brown.png";
        }

        return Identifier.withDefaultNamespace(rabbitTextureLocation);
    }

    public RabbitRenderState createRenderState() {
        return new RabbitRenderState();
    }

}
