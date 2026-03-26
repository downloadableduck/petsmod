package com.jeff.pets.vanilla.slime;

import com.jeff.pets.vanilla.hostile.ClientSlime;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.slime.SlimeModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SlimeOuterLayer;
import net.minecraft.client.renderer.entity.state.SlimeRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Pet.CONFIG;

public class ClientSlimeRenderer extends MobRenderer<@NotNull ClientSlime, @NotNull SlimeRenderState, @NotNull SlimeModel> {

    public static final ModelLayerLocation SLIME_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientslime"), "main");

    public ClientSlimeRenderer(EntityRendererProvider.Context context) {
        super(context, new SlimeModel(context.bakeLayer(ModelLayers.SLIME)), 0.75f);
        this.addLayer(new SlimeOuterLayer(this, EntityModelSet.vanilla()));
        this.scale(new SlimeRenderState(), new PoseStack());
    }

    protected void scale(SlimeRenderState slimeRenderState, @NotNull PoseStack poseStack) {
        int slimeScale = switch (CONFIG.slimeSkin) {
            case "small" -> 1;
            case "medium" -> 2;
            case "large" -> 4;
            case null, default -> 1;
        };
        float f = slimeRenderState.squish / ((float) slimeScale * 0.5F + 1.0F);
        float g = 1.0F / (f + 1.0F);
        poseStack.scale(g * (float) slimeScale, 1.0F / g * (float) slimeScale, g * (float) slimeScale);
    }

    @Override
    public @NotNull Identifier getTextureLocation(SlimeRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/slime/slime.png");
    }

    @Override
    public SlimeRenderState createRenderState() {
        return new SlimeRenderState();
    }
}
