package com.jeff.pets.client.rendering;

import com.jeff.pets.compat.GlStateManager;
import com.jeff.pets.mob.AbstractPet;
import net.minecraft.client.render.entity.MobRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * b1.7.3-compatible pet renderer base.
 */
public abstract class PetRenderer<D extends AbstractPet> extends MobRenderer {

    protected final net.minecraft.client.render.model.Model petModel;
    private final List<PetRenderLayer> petLayers = new ArrayList<>();

    public PetRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.minecraft.client.render.model.Model model, float shadow) {
        super(model, shadow);
        this.petModel = model;
        this.dispatcher = context;
    }

    public abstract String getTexture(Entity entity);

    public void addLayer(PetRenderLayer layer) {
        this.petLayers.add(layer);
    }

    public net.minecraft.client.render.model.Model getModel() {
        return this.petModel;
    }

    public net.minecraft.client.render.block.BlockRenderer getBlockRenderer() {
        return this.blockRenderer;
    }

    @Override
    public void render(Entity entity, double x, double y, double z, float yaw, float partialTicks) {
        super.render((MobEntity) entity, x, y, z, yaw, partialTicks);
    }

    @Override
    public void render(MobEntity entity, double x, double y, double z, float yaw, float partialTicks) {
        GlStateManager.pushMatrix();
        org.lwjgl.opengl.GL11.glDisable(2884);
        this.petModel.attackAnimationProgress = this.getAttackAnimationProgress(entity, partialTicks);
        this.petModel.riding = entity.isRiding();

        float bodyYaw = entity.lastBodyYaw + (entity.bodyYaw - entity.lastBodyYaw) * partialTicks;
        float headYaw = entity.lastYaw + (entity.yaw - entity.lastYaw) * partialTicks;
        float headPitch = entity.lastPitch + (entity.pitch - entity.lastPitch) * partialTicks;

        this.applyTranslation(entity, x, y, z);
        float bob = this.getBob(entity, partialTicks);
        this.applyRotation(entity, bob, bodyYaw, partialTicks);

        float scale = 0.0625F;
        org.lwjgl.opengl.GL11.glEnable(32826);
        GlStateManager.scalef(-1.0F, -1.0F, 1.0F);
        this.applyScale(entity, partialTicks);
        GlStateManager.translatef(0.0F, -24.0F * scale - 0.0078125F, 0.0F);

        float walkAmount = entity.lastWalkAnimationSpeed + (entity.walkAnimationSpeed - entity.lastWalkAnimationSpeed) * partialTicks;
        float walkPos = entity.walkAnimationProgress - entity.walkAnimationSpeed * (1.0F - partialTicks);
        if (walkAmount > 1.0F) walkAmount = 1.0F;

        this.bindTexture(this.getTexture(entity));
        this.petModel.prepare(entity, walkPos, walkAmount, partialTicks);
        this.petModel.render(walkPos, walkAmount, bob, headYaw - bodyYaw, headPitch, scale);

        for (PetRenderLayer layer : this.petLayers) {
            layer.render(entity, walkPos, walkAmount, bob, headYaw - bodyYaw, headPitch, scale);
        }

        org.lwjgl.opengl.GL11.glDisable(32826);
        org.lwjgl.opengl.GL11.glEnable(2884);
        GlStateManager.popMatrix();

        this.renderNameTag(entity, x, y, z);
    }

    @Override
    public void bindTexture(String string) {
        super.bindTexture(string);
    }
}
