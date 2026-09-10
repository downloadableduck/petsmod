package com.jeff.pets.client.rendering;

import com.jeff.pets.client.PetsConfigScreen;
import com.jeff.pets.mob.AbstractPet;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.core.Direction;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

/**
 * Used as a shared piece of code across all of the renderers. The main point of this class
 * is to provide a {@code state.isUpsideDown} check for all mobs.
 */
public abstract class PetRenderer<D extends AbstractPet, U extends LivingEntityRenderState, K extends EntityModel<? super U>> extends MobRenderer<@NotNull D, @NotNull U, @NotNull K> {

    D entity;

    public PetRenderer(EntityRendererProvider.Context context, K model, float shadow) {
        super(context, model, shadow);
    }

    @Override
    public void extractRenderState(D entity, U state, float f) {
        if (this.entity == null) {
            this.entity = entity;
        }
        try {
            super.extractRenderState(entity, state, f);
        } catch (Exception e) {
        }

        state.isUpsideDown = entity.getPlainTextName().equals("Grumm") || entity.getPlainTextName().equals("Dinnerbone");
        if (state.passengerOffset == null) {
            state.passengerOffset = new Vec3(0, 0, 0);
        }
        IPetRenderState petRenderState = (IPetRenderState) state;
            boolean isOwner = entity.getOwner() != null &&
                    entity.getOwner().equals(Minecraft.getInstance().player);

            petRenderState.pets$setMyPet(isOwner);
            petRenderState.pets$setPetSkin(entity.petSkin != null ? entity.petSkin : "");
            if (petRenderState.pets$isMyPet()) {
                state.isBaby = CONFIG.isBaby;
            } else {
                state.isBaby = entity.isBaby();
            }
    }

    @Override
    public RenderType getRenderType(U state, boolean transparent, boolean force, boolean glowing) {
        if (Minecraft.getInstance().gui.screen() instanceof PetsConfigScreen) {
            return RenderTypes.entityTranslucent(this.getTextureLocation(state));
        }
        return super.getRenderType(state, transparent, force, glowing);
    }

    @Override
    public void submit(U state, PoseStack poseStack, SubmitNodeCollector node, CameraRenderState cameraRenderState) {
        poseStack.pushPose();
        if (state.hasPose(Pose.SLEEPING)) {
            Direction bedOrientation = state.bedOrientation;
            if (bedOrientation != null) {
                float headOffset = state.eyeHeight - 0.1F;
                poseStack.translate((float) (-bedOrientation.getStepX()) * headOffset, 0.0F, (float) (-bedOrientation.getStepZ()) * headOffset);
            }
        }

        float scale = state.scale;
        poseStack.scale(scale, scale, scale);
        this.setupRotations(state, poseStack, state.bodyRot, scale);
        poseStack.scale(-1.0F, -1.0F, 1.0F);
        this.scale(state, poseStack);
        poseStack.translate(0.0F, -1.501F, 0.0F);
        boolean isBodyVisible = this.isBodyVisible(state);
        boolean forceTransparent = !isBodyVisible && !state.isInvisibleToPlayer;
        RenderType renderType = this.getRenderType(state, isBodyVisible, forceTransparent, state.appearsGlowing());
        if (renderType != null) {
            int overlayCoords = getOverlayCoords(state, this.getWhiteOverlayProgress(state));
            int baseColor = forceTransparent ? 654311423 : -1;
            int tintedColor = ARGB.multiply(baseColor, this.getModelTint(state));
            node.submitModel(this.model, state, poseStack, renderType, state.lightCoords, overlayCoords, this.getColor(), null, state.outlineColor, null);
        }

        if (this.shouldRenderLayers(state) && !this.layers.isEmpty()) {
            this.model.setupAnim(state);

            for (RenderLayer<U, K> layer : this.layers) {
                layer.submit(poseStack, node, state.lightCoords, state, state.yRot, state.xRot);
            }
        }

        poseStack.popPose();
        if (state.leashStates != null) {
            for (EntityRenderState.LeashState leashState : state.leashStates) {
                node.submitLeash(poseStack, leashState);
            }
        }

        this.submitNameDisplay(state, poseStack, node, cameraRenderState);
    }

    private int getColor() {
        if (Minecraft.getInstance().gui.screen() instanceof PetsConfigScreen screen) {
            return screen.button.color;
        }
        return 0xFFFFFFFF;
    }

    public String getPetSkin(String traitorSkin) {
        return traitorSkin;
    }
}
