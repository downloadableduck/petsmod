package com.jeff.pets.client.rendering.vanilla.axolotl;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientAxolotl;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientAxolotlRenderer extends PetRenderer<@NotNull ClientAxolotl, @NotNull ClientAxolotlModel> {
    public static final ModelLayerLocation AXOLOTL_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientaxolotl"), "main");

    String axolotlTextureLocation;

    public ClientAxolotlRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientAxolotlModel(context.bakeLayer(ModelLayers.AXOLOTL)), 0.5F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientAxolotl axolotlRenderState) {
        switch (CONFIG.axolotlSkin) {
            case "pink" -> axolotlTextureLocation = "textures/entity/axolotl/axolotl_lucy.png";
            case "brown" -> axolotlTextureLocation = "textures/entity/axolotl/axolotl_wild.png";
            case "gold" -> axolotlTextureLocation = "textures/entity/axolotl/axolotl_gold.png";
            case "cyan" -> axolotlTextureLocation = "textures/entity/axolotl/axolotl_cyan.png";
            case "blue" -> axolotlTextureLocation = "textures/entity/axolotl/axolotl_blue.png";
            case null, default -> {
                CONFIG.axolotlSkin = "pink";
                axolotlTextureLocation = "textures/entity/axolotl/axolotl_lucy.png";
            }
        }
        return ResourceLocation.withDefaultNamespace(axolotlTextureLocation);
    }

    @Override
    protected void scale(ClientAxolotl state, @NotNull PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }
}
