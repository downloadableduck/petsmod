package com.jeff.pets.vanilla.cat;

import com.jeff.pets.PetsInitializer;
import com.jeff.pets.vanilla.passive.ClientCat;
import net.minecraft.client.model.animal.feline.CatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.CatRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.animal.feline.Cat;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.Pet.CONFIG;

public class ClientCatRenderer extends MobRenderer<@NotNull ClientCat, @NotNull CatRenderState, @NotNull CatModel> {
    public static final ModelLayerLocation CAT_LOCATION = new ModelLayerLocation(
            Identifier.fromNamespaceAndPath(PetsInitializer.MOD_ID, "clientcat"), "main"
    );

    public ClientCatRenderer(EntityRendererProvider.Context context) {
        super(context, new CatModel(context.bakeLayer(ModelLayers.CAT)), 0.7F);
    }

    public static LayerDefinition createCatBodyLayer() {
        CatModel.createBodyMesh(CubeDeformation.NONE);
        return LayerDefinition.create(new MeshDefinition(), 64, 32);
    }

    @Override
    public @NotNull Identifier getTextureLocation(CatRenderState livingEntityRenderState) {
        return switch (CONFIG.catSkin) {
            case "black" -> Identifier.withDefaultNamespace("textures/entity/cat/all_black.png");
            case "tuxedo" -> Identifier.withDefaultNamespace("textures/entity/cat/black.png");
            case "british_shorthair" -> Identifier.withDefaultNamespace("textures/entity/cat/british_shorthair.png");
            case "calico" -> Identifier.withDefaultNamespace("textures/entity/cat/calico.png");
            case "jellie" -> Identifier.withDefaultNamespace("textures/entity/cat/jellie.png");
            case "ocelot" -> Identifier.withDefaultNamespace("textures/entity/cat/ocelot.png");
            case "persian" -> Identifier.withDefaultNamespace("textures/entity/cat/persian.png");
            case "ragdoll" -> Identifier.withDefaultNamespace("textures/entity/cat/ragdoll.png");
            case "red" -> Identifier.withDefaultNamespace("textures/entity/cat/red.png");
            case "siamese" -> Identifier.withDefaultNamespace("textures/entity/cat/siamese.png");
            case "tabby" -> Identifier.withDefaultNamespace("textures/entity/cat/tabby.png");
            case "white" -> Identifier.withDefaultNamespace("textures/entity/cat/white.png");
            case null, default -> Identifier.withDefaultNamespace("textures/entity/cat/black.png");
        };
    }

    @Override
    public CatRenderState createRenderState() {
        return new CatRenderState();
    }
}
