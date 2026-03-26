package com.jeff.pets.vanilla.elderguardian;

import com.jeff.pets.vanilla.hostile.ClientElderGuardian;
import com.jeff.pets.vanilla.hostile.ClientEndermite;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.guardian.GuardianModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.GuardianRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientElderGuardianRenderer extends MobRenderer<@NotNull ClientElderGuardian, @NotNull GuardianRenderState, @NotNull GuardianModel> {
    public static final ModelLayerLocation ELDER_GUARDIAN_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientelderguardian"), "main");

    public ClientElderGuardianRenderer(EntityRendererProvider.Context context) {
        super(context, new GuardianModel(context.bakeLayer(ModelLayers.ELDER_GUARDIAN)), 0.75f);
        this.scale(new PoseStack());
    }

    private void scale(PoseStack poseStack) {
        poseStack.scale(2.35f, 2.35f, 2.35f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(GuardianRenderState livingEntityRenderState) {
        livingEntityRenderState.spikesAnimation = 1;
        return Identifier.withDefaultNamespace("textures/entity/guardian_elder.png");
    }

    @Override
    public GuardianRenderState createRenderState() {
        return new GuardianRenderState();
    }
}
