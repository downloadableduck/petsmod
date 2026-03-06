package com.jeff.pets.vanilla.piglin;

import com.jeff.pets.vanilla.neutral.ClientPiglin;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.monster.piglin.PiglinModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.PiglinRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Pet.CONFIG;

public class ClientPiglinRenderer extends MobRenderer<@NotNull ClientPiglin, @NotNull PiglinRenderState, @NotNull PiglinModel> {

    public static ModelLayerLocation PIGLIN_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientpiglin"), "main");
    private String piglinTexturePath;

    public ClientPiglinRenderer(EntityRendererProvider.Context context) {
        super(context, new PiglinModel(context.bakeLayer(ModelLayers.PIGLIN)), 0.75f);
    }

    public static LayerDefinition createBodyLayer() {
        PiglinModel.createMesh(CubeDeformation.NONE);
        PiglinModel.addHead(CubeDeformation.NONE, new MeshDefinition());
        return LayerDefinition.create(new MeshDefinition(), 64, 64);
    }

    @Override
    public @NotNull Identifier getTextureLocation(PiglinRenderState livingEntityRenderState) {
        switch (CONFIG.piglinSkin) {
            case "piglin" -> piglinTexturePath = "textures/entity/piglin/piglin.png";
            case "zombified" -> {
                piglinTexturePath = "textures/entity/piglin/zombified_piglin.png";
                livingEntityRenderState.isConverting = true;
            }
            case "brute" -> {
                piglinTexturePath = "textures/entity/piglin/piglin_brute.png";
                livingEntityRenderState.isBrute = true;
            }
        }
        return Identifier.withDefaultNamespace(piglinTexturePath);
    }

    @Override
    public PiglinRenderState createRenderState() {
        return new PiglinRenderState();
    }
}
