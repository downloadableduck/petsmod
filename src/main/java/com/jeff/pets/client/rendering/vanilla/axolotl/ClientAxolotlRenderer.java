package com.jeff.pets.rendering.vanilla.axolotl;

import com.jeff.pets.mob.vanilla.passive.ClientAxolotl;
import com.jeff.pets.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.animal.axolotl.AdultAxolotlModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.AxolotlRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Central.CONFIG;

public class ClientAxolotlRenderer extends PetRenderer<@NotNull ClientAxolotl, @NotNull AxolotlRenderState, @NotNull AdultAxolotlModel> {
    public static final ModelLayerLocation AXOLOTL_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientaxolotl"), "main");

    String axolotlTextureLocation;

    public ClientAxolotlRenderer(EntityRendererProvider.Context context) {
        super(context, new AdultAxolotlModel(context.bakeLayer(ModelLayers.AXOLOTL)), 0.5F);
    }

    @Override
    public @NotNull Identifier getTextureLocation(AxolotlRenderState axolotlRenderState) {
        switch (this.getPetSkin(CONFIG.axolotlSkin)) {
            case "pink" -> axolotlTextureLocation = "textures/entity/axolotl/axolotl_lucy.png";
            case "brown" -> axolotlTextureLocation = "textures/entity/axolotl/axolotl_wild.png";
            case "gold" -> axolotlTextureLocation = "textures/entity/axolotl/axolotl_gold.png";
            case "cyan" -> axolotlTextureLocation = "textures/entity/axolotl/axolotl_cyan.png";
            case "blue" -> axolotlTextureLocation = "textures/entity/axolotl/axolotl_blue.png";
            case null, default -> axolotlTextureLocation = "textures/entity/axolotl/axolotl_lucy.png";
        }
        return Identifier.withDefaultNamespace(axolotlTextureLocation);
    }

    @Override
    protected void scale(AxolotlRenderState state, @NotNull PoseStack poseStack) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public AxolotlRenderState createRenderState() {
        return new AxolotlRenderState();
    }
}
