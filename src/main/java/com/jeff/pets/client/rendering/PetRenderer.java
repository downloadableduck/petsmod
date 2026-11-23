//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering;

import com.jeff.pets.compat.render.model.Model;
import net.minecraft.client.render.EntityRendererDispatcher;
import net.minecraft.client.render.entity.MobRenderer;
import net.minecraft.client.render.tessellator.TessellatorGeneral;
import net.minecraft.client.render.tessellator.TessellatorShader;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.Mob;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.jeff.pets.compat.GlStateManager;
import com.jeff.pets.mob.AbstractPet;
import org.useless.dragonfly.models.entity.StaticEntityModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class PetRenderer<D extends AbstractPet> extends MobRenderer<D> {
    protected final Model petModel;
    private final List<PetRenderLayer> petLayers = new ArrayList();
    public static TessellatorShader tessellatorShader;

    public PetRenderer(EntityRendererDispatcher context, Model model, float shadow) {
        super(shadow);
        this.renderDispatcher = context;
        this.petModel = model;
    }

    public abstract String getTexture(Entity var1);

    public void addLayer(PetRenderLayer layer) {
        this.petLayers.add(layer);
    }

    public Model getModel() {
        return this.petModel;
    }

    protected float getHandSwingProgress(Mob e, float t){ return e.swingProgress; }

    protected void applyTranslation(Mob e, double x,double y,double z){
        GlStateManager.translatef((float)x, (float)y, (float)z);
    }
    protected float getHeadBob(Mob e, float t){ return (float)e.tickCount + t; }
    protected void applyHandSwingRotation(Mob e, float a,float b,float c){}
    protected void applyScale(Mob e, float t){}

    @Override
    public void render(@NotNull TessellatorGeneral tessellator, @NotNull D entity, double x, double y, double z, float yRot, float partialTicks) {
        this.petModel.tessellator = tessellator;

        float yBodyRot = entity.yBodyRotO + (entity.yBodyRot - entity.yBodyRotO) * partialTicks;
        float headYaw = entity.yRotO + (entity.yRot - entity.yRotO) * partialTicks;
        float headPitch = entity.xRotO + (entity.xRot - entity.xRotO) * partialTicks;
        float netHeadYaw = headYaw - yBodyRot;

        float walkAmount = entity.walkAnimSpeedO + (entity.walkAnimSpeed - entity.walkAnimSpeedO) * partialTicks;
        float walkPos = entity.walkAnimPos - entity.walkAnimSpeed * (1.0F - partialTicks);
        if (walkAmount > 1.0F) {
            walkAmount = 1.0F;
        }

        float bob = this.getHeadBob(entity, partialTicks);
        float scale = 0.0625F;

        this.petModel.swingProgress = this.getHandSwingProgress(entity, partialTicks);
        this.petModel.riding = (entity.vehicle != null);

        GlStateManager.pushMatrix();
        GlStateManager.disableCull();

        GlStateManager.translatef((float) x, (float) y, (float) z);
        GlStateManager.rotatef(180.0F - yBodyRot, 0.0F, 1.0F, 0.0F);
        this.applyHandSwingRotation(entity, bob, yBodyRot, partialTicks);

        GlStateManager.scalef(-1.0F, -1.0F, 1.0F);
        this.applyScale(entity, partialTicks);
        GlStateManager.translatef(0.0F, -24.0F * scale - 0.0078125F, 0.0F);

        this.bindTexture(this.getTexture(entity));
        this.petModel.animateModel(entity, walkPos, walkAmount, partialTicks);
        this.petModel.render(walkPos, walkAmount, bob, netHeadYaw, headPitch, scale);
        Iterator var17 = this.petLayers.iterator();

        while(var17.hasNext()) {
            PetRenderLayer layer = (PetRenderLayer)var17.next();
            layer.render(entity, walkPos, walkAmount, bob, netHeadYaw, headPitch, scale);
        }

        GlStateManager.enableCull();
        GlStateManager.popMatrix();
    }

    public void bindTexture(String string) {
        super.bindTexture(string);
    }

    @Override
    protected @Nullable StaticEntityModel getAndSetupModelForLayer(@NotNull D mob, float v, float v1, int i) {
        return this.petModel;
    }
}
