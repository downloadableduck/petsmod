package com.jeff.pets.vanilla.nautilus;

import com.jeff.pets.vanilla.neutral.ClientNautilus;
import net.minecraft.client.model.animal.nautilus.NautilusModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.NautilusRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Pet.CONFIG;

public class ClientNautilusRenderer extends MobRenderer<@NotNull ClientNautilus, @NotNull NautilusRenderState, @NotNull NautilusModel> {

    public static final ModelLayerLocation NAUTILUS_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientnautilus"), "main");

    public ClientNautilusRenderer(EntityRendererProvider.Context context) {
        super(context, new NautilusModel(context.bakeLayer(ModelLayers.NAUTILUS)), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(NautilusRenderState livingEntityRenderState) {
        NautilusRenderState state = new NautilusRenderState();
        state.yRot = 180;
        String nautilusTexturePath = switch (CONFIG.nautilusSkin) {
            case "nautilus" -> "textures/entity/nautilus/nautilus.png";
            case "zombie" -> "textures/entity/nautilus/zombie_nautilus.png";
            case "coral_zombie" -> "textures/entity/nautilus/zombie_nautilus_coral.png";
            case null, default -> "textures/entity/nautilus/nautilus.png";
        };
        return Identifier.withDefaultNamespace(nautilusTexturePath);
    }

    @Override
    public NautilusRenderState createRenderState() {
        return new NautilusRenderState();
    }
}
