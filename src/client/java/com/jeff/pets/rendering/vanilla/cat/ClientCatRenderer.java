package com.jeff.pets.rendering.vanilla.cat;

import com.jeff.pets.PetsInitializer;
import com.jeff.pets.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientCat;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.CatRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Central.CONFIG;

public class ClientCatRenderer extends PetRenderer<@NotNull ClientCat, @NotNull CatRenderState, @NotNull ClientCatModel> {
    public static final ModelLayerLocation CAT_LOCATION = new ModelLayerLocation(
            Identifier.fromNamespaceAndPath(PetsInitializer.MOD_ID, "clientcat"), "main"
    );

    public ClientCatRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientCatModel(context.bakeLayer(ModelLayers.CAT)), 0.7F);
    }

    public static LayerDefinition createCatBodyLayer() {
        ClientCatModel.createBodyMesh(CubeDeformation.NONE);
        return LayerDefinition.create(new MeshDefinition(), 64, 32);
    }

    @Override
    protected void scale(CatRenderState state, @NotNull PoseStack poseStack) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
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

    @Override
    public void extractRenderState(ClientCat cat, CatRenderState state, float f) {
        super.extractRenderState(cat, state, f);
        state.isSitting = cat.isPassenger();
    }
}
