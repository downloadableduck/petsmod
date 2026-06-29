package com.jeff.pets.client.rendering.vanilla.cat;

import com.jeff.pets.PetsInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientCat;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCatRenderer extends PetRenderer<@NotNull ClientCat, @NotNull ClientCatModel> {
    public static final ModelLayerLocation CAT_LOCATION = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(PetsInitializer.MOD_ID, "clientcat"), "main"
    );

    public ClientCatRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientCatModel(context.bakeLayer(ModelLayers.CAT)), 0.7F);
    }

    public static LayerDefinition createCatBodyLayer() {
        ClientCatModel.createBodyMesh(CubeDeformation.NONE);
        return LayerDefinition.create(new MeshDefinition(), 64, 32);
    }

    @Override
    protected void scale(ClientCat state, @NotNull PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientCat livingEntityRenderState) {
        return switch (CONFIG.catSkin) {
            case "black" -> ResourceLocation.withDefaultNamespace("textures/entity/cat/all_black.png");
            case "tuxedo" -> ResourceLocation.withDefaultNamespace("textures/entity/cat/black.png");
            case "british_shorthair" ->
                    ResourceLocation.withDefaultNamespace("textures/entity/cat/british_shorthair.png");
            case "calico" -> ResourceLocation.withDefaultNamespace("textures/entity/cat/calico.png");
            case "jellie" -> ResourceLocation.withDefaultNamespace("textures/entity/cat/jellie.png");
            case "ocelot" -> ResourceLocation.withDefaultNamespace("textures/entity/cat/ocelot.png");
            case "persian" -> ResourceLocation.withDefaultNamespace("textures/entity/cat/persian.png");
            case "ragdoll" -> ResourceLocation.withDefaultNamespace("textures/entity/cat/ragdoll.png");
            case "red" -> ResourceLocation.withDefaultNamespace("textures/entity/cat/red.png");
            case "siamese" -> ResourceLocation.withDefaultNamespace("textures/entity/cat/siamese.png");
            case "tabby" -> ResourceLocation.withDefaultNamespace("textures/entity/cat/tabby.png");
            case "white" -> ResourceLocation.withDefaultNamespace("textures/entity/cat/white.png");
            case null, default -> ResourceLocation.withDefaultNamespace("textures/entity/cat/black.png");
        };
    }

    @Override
    public void render(ClientCat cat, float f, float g, PoseStack poseStack, MultiBufferSource source, int i) {
        super.render(cat, f, g, poseStack, source, i);
        cat.setInSittingPose(cat.isPassenger());
    }
}
