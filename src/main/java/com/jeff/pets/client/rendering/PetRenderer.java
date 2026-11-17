//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering;

import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.lwjgl.opengl.GL11;
import com.jeff.pets.compat.GlStateManager;
import com.jeff.pets.mob.AbstractPet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class PetRenderer<D extends AbstractPet> extends LivingEntityRenderer {
    protected final EntityModel petModel;
    private final List<PetRenderLayer> petLayers = new ArrayList();

    public PetRenderer(EntityRenderDispatcher context, EntityModel model, float shadow) {
        super(model, shadow);
        this.petModel = model;
        this.dispatcher = context;
    }

    public abstract String getTexture(Entity var1);

    public void addLayer(PetRenderLayer layer) {
        this.petLayers.add(layer);
    }

    public EntityModel getModel() {
        return this.petModel;
    }

    public BlockRenderManager getBlockRenderer() {
        return this.renderManager;
    }

    public void render(Entity entity, double x, double y, double z, float yaw, float partialTicks) {
        super.render((LivingEntity)entity, x, y, z, yaw, partialTicks);
    }

    public void render(LivingEntity entity, double x, double y, double z, float yaw, float partialTicks) {
        GlStateManager.pushMatrix();
        GL11.glDisable(2884);
        this.petModel.handSwingProgress = this.getHandSwingProgress(entity, partialTicks);
        this.petModel.riding = entity.hasVehicle();
        float bodyYaw = entity.lastBodyYaw + (entity.bodyYaw - entity.lastBodyYaw) * partialTicks;
        float headYaw = entity.prevYaw + (entity.yaw - entity.prevYaw) * partialTicks;
        float headPitch = entity.prevPitch + (entity.pitch - entity.prevPitch) * partialTicks;
        this.applyTranslation(entity, x, y, z);
        float bob = this.getHeadBob(entity, partialTicks);
        this.applyHandSwingRotation(entity, bob, bodyYaw, partialTicks);
        float scale = 0.0625F;
        GL11.glEnable(32826);
        GlStateManager.scalef(-1.0F, -1.0F, 1.0F);
        this.applyScale(entity, partialTicks);
        GlStateManager.translatef(0.0F, -24.0F * scale - 0.0078125F, 0.0F);
        float walkAmount = entity.lastWalkAnimationSpeed + (entity.walkAnimationSpeed - entity.lastWalkAnimationSpeed) * partialTicks;
        float walkPos = entity.walkAnimationProgress - entity.walkAnimationSpeed * (1.0F - partialTicks);
        if (walkAmount > 1.0F) {
            walkAmount = 1.0F;
        }

        this.bindTexture(this.getTexture(entity));
        this.petModel.animateModel(entity, walkPos, walkAmount, partialTicks);
        this.petModel.render(walkPos, walkAmount, bob, headYaw - bodyYaw, headPitch, scale);
        Iterator var17 = this.petLayers.iterator();

        while(var17.hasNext()) {
            PetRenderLayer layer = (PetRenderLayer)var17.next();
            layer.render(entity, walkPos, walkAmount, bob, headYaw - bodyYaw, headPitch, scale);
        }

        GL11.glDisable(32826);
        GL11.glEnable(2884);
        GlStateManager.popMatrix();
        this.renderNameTag(entity, x, y, z);
    }

    public void bindTexture(String string) {
        super.bindTexture(string);
    }
}
