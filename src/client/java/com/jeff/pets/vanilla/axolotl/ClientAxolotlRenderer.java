package com.jeff.pets.vanilla.axolotl;

import com.jeff.pets.vanilla.passive.ClientAxolotl;
import net.minecraft.client.model.animal.axolotl.AxolotlModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.AxolotlRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Pet.CONFIG;

public class ClientAxolotlRenderer extends MobRenderer<@NotNull ClientAxolotl, @NotNull AxolotlRenderState, @NotNull AxolotlModel> {
    public static final ModelLayerLocation AXOLOTL_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientaxolotl"), "main");

    String axolotlTextureLocation;

    public ClientAxolotlRenderer(EntityRendererProvider.Context context) {
        super(context, new AxolotlModel(context.bakeLayer(ModelLayers.AXOLOTL)), 0.5F);
    }

    public @NotNull Identifier getTextureLocation(AxolotlRenderState axolotlRenderState) {
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
        return Identifier.withDefaultNamespace(axolotlTextureLocation);
    }

    public AxolotlRenderState createRenderState() {
        return new AxolotlRenderState();
    }

    public void extractRenderState(ClientAxolotl axolotl, AxolotlRenderState axolotlRenderState, float f) {
        super.extractRenderState(axolotl, axolotlRenderState, f);
    }
}
