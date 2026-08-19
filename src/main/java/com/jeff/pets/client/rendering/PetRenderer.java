package com.jeff.pets.rendering;

import com.jeff.pets.client.NewPetsConfigScreen;
import com.jeff.pets.compat.ViaFabricPlusCompat;
import com.jeff.pets.mob.AbstractPet;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.sun.jna.platform.win32.Netapi32Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.Direction;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

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
        super.extractRenderState(entity, state, f);
        state.isUpsideDown = entity.getPlainTextName().equals("Grumm") || entity.getPlainTextName().equals("Dinnerbone");
        if (state.passengerOffset == null) {
            state.passengerOffset = new Vec3(0, 0, 0);
        }
        if (ViaFabricPlusCompat.shouldUpdateThingy() && entity.isPassenger()) {
            state.passengerOffset = new Vec3(state.passengerOffset.x, state.passengerOffset.y + 0.35, state.passengerOffset.z);
        }
    }

    public String getPetSkin(String petSkin) {
        System.out.println(this.isMyPet());
        if (this.entity == null || this.isMyPet()) {
            return petSkin;
        }
        return this.entity.petSkin;
    }

    protected boolean isMyPet() {
        if (this.entity == null || this.entity.getOwner() == null) {
            return true;
        }
        return this.entity.getOwner().equals(Minecraft.getInstance().player);
    }

    @Override
    public RenderType getRenderType(U state, boolean transparent, boolean force, boolean glowing) {
        if (Minecraft.getInstance().screen instanceof NewPetsConfigScreen) {
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
                poseStack.translate((float)(-bedOrientation.getStepX()) * headOffset, 0.0F, (float)(-bedOrientation.getStepZ()) * headOffset);
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

            for(RenderLayer<U, K> layer : this.layers) {
                layer.submit(poseStack, node, state.lightCoords, state, state.yRot, state.xRot);
            }
        }

        poseStack.popPose();
        if (state.leashStates != null) {
            for(EntityRenderState.LeashState leashState : state.leashStates) {
                node.submitLeash(poseStack, leashState);
            }
        }

        this.submitNameDisplay(state, poseStack, node, cameraRenderState);
    }

    private int getColor() {
        if (Minecraft.getInstance().screen instanceof NewPetsConfigScreen screen) {
            return screen.button.color;
        }
        return 0xFFFFFFFF;
    }
}
