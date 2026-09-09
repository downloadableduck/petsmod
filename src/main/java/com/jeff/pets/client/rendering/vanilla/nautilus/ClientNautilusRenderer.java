package com.jeff.pets.client.rendering.vanilla.nautilus;

import com.jeff.pets.client.rendering.IPetRenderState;
import com.jeff.pets.mob.vanilla.neutral.ClientNautilus;
import com.jeff.pets.client.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.animal.nautilus.NautilusModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.NautilusRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientNautilusRenderer extends PetRenderer<@NotNull ClientNautilus, @NotNull NautilusRenderState, @NotNull NautilusModel> {

    public static final ModelLayerLocation NAUTILUS_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientnautilus"), "main");

    public ClientNautilusRenderer(EntityRendererProvider.Context context) {
        super(context, new NautilusModel(context.bakeLayer(ModelLayers.NAUTILUS)), 0.75f);
    }

    protected void scale(NautilusRenderState state, @NotNull PoseStack poseStack) {
        if (state.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTextureLocation(NautilusRenderState state) {
        state.yRot = 180;
        String skin = ((IPetRenderState) state).pets$getPetSkin();
        String nautilusTexturePath = switch (skin) {
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
