package com.jeff.pets.vanilla.bee;

import com.jeff.pets.vanilla.neutral.ClientBee;
import net.minecraft.client.model.animal.bee.BeeModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.BeeRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.Pet.CONFIG;

public class ClientBeeRenderer extends MobRenderer<@NotNull ClientBee, @NotNull BeeRenderState, @NotNull BeeModel> {
    public static final ModelLayerLocation BEE_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientbee"), "main");
    public String beeTexturePath;

    public ClientBeeRenderer(EntityRendererProvider.Context context) {
        super(context, new BeeModel(context.bakeLayer(ModelLayers.BEE)), 0.4f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(BeeRenderState beeRenderState) {
        if (Objects.equals(CONFIG.beeSkin, "happy")) {
            beeTexturePath = "textures/entity/bee/bee.png";
        } else if (Objects.equals(CONFIG.beeSkin, "angry")) {
            beeTexturePath = "textures/entity/bee/bee_angry.png";
        }
        return Identifier.withDefaultNamespace(beeTexturePath);
    }

    @Override
    public BeeRenderState createRenderState() {
        return new BeeRenderState();
    }
}
