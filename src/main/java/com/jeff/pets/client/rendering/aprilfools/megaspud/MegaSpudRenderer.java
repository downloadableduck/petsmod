package com.jeff.pets.rendering.aprilfools.megaspud;

import com.jeff.pets.mob.aprilfools.MegaSpud;
import com.jeff.pets.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.SlimeRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class MegaSpudRenderer extends PetRenderer<@NotNull MegaSpud, @NotNull SlimeRenderState, @NotNull MegaSpudModel> {

    public static final ModelLayerLocation MEGA_SPUD_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("megaspoud"), "main");

    public MegaSpudRenderer(EntityRendererProvider.Context context) {
        super(context, new MegaSpudModel(context.bakeLayer(MEGA_SPUD_LOCATION)), 0.75f);
        this.addLayer(new MegaSpudOuterLayer(this, context));
        this.scale(new SlimeRenderState(), new PoseStack());
    }

    @Override
    protected void scale(SlimeRenderState state, PoseStack poseStack) {
        poseStack.scale(9, 12, 9);
    }

    @Override
    public @NotNull Identifier getTextureLocation(SlimeRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/slime/mega_spud.png");
    }

    @Override
    public SlimeRenderState createRenderState() {
        return new SlimeRenderState();
    }
}
