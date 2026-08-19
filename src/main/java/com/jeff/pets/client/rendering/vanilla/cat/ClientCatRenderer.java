package com.jeff.pets.rendering.vanilla.cat;

import com.jeff.pets.PetsInitializer;
import com.jeff.pets.mob.vanilla.passive.ClientCat;
import com.jeff.pets.rendering.PetRenderer;
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
            case "black" -> Identifier.withDefaultNamespace("textures/entity/cat/cat_all_black.png");
            case "tuxedo" -> Identifier.withDefaultNamespace("textures/entity/cat/cat_black.png");
            case "british_shorthair" ->
                    Identifier.withDefaultNamespace("textures/entity/cat/cat_british_shorthair.png");
            case "calico" -> Identifier.withDefaultNamespace("textures/entity/cat/cat_calico.png");
            case "jellie" -> Identifier.withDefaultNamespace("textures/entity/cat/cat_jellie.png");
            case "ocelot" -> Identifier.withDefaultNamespace("textures/entity/cat/cat_ocelot.png");
            case "persian" -> Identifier.withDefaultNamespace("textures/entity/cat/cat_persian.png");
            case "ragdoll" -> Identifier.withDefaultNamespace("textures/entity/cat/cat_ragdoll.png");
            case "red" -> Identifier.withDefaultNamespace("textures/entity/cat/cat_red.png");
            case "siamese" -> Identifier.withDefaultNamespace("textures/entity/cat/cat_siamese.png");
            case "tabby" -> Identifier.withDefaultNamespace("textures/entity/cat/cat_tabby.png");
            case "white" -> Identifier.withDefaultNamespace("textures/entity/cat/cat_white.png");
            case null, default -> Identifier.withDefaultNamespace("textures/entity/cat/cat_black.png");
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
