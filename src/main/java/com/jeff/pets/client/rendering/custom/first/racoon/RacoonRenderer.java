package com.jeff.pets.client.rendering.custom.first.racoon;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.first.Racoon;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;
import static com.jeff.pets.client.Central.MOD_ID;

public class RacoonRenderer extends PetRenderer {

    public RacoonRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new RacoonModel(), 0.75f);
    }

    @Override
    public void preRenderCallback( final EntityLivingBase livingEntityRenderState, float f) {
        if ((CONFIG.isBaby && !((Racoon) livingEntityRenderState).isServerEntity()) || (livingEntityRenderState.isChild() && ((Racoon) livingEntityRenderState).isServerEntity())) {
            GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public ResourceLocation getEntityTexture( final Entity state) {
        String racoonTexturePath;
        Racoon racoon = (Racoon) state;
        if (!racoon.isServerEntity()) {
            if (Objects.equals(CONFIG.racoonSkin, "normal")) {
                racoonTexturePath = "textures/entity/racoon/racoon.png";
            } else if (Objects.equals(CONFIG.racoonSkin, "albino")) {
                racoonTexturePath = "textures/entity/racoon/albino.png";
            } else {
                racoonTexturePath = "textures/entity/racoon/racoon.png";
            }
        } else {
            racoonTexturePath = "textures/entity/racoon/racoon.png";
        }
        return new ResourceLocation(MOD_ID, racoonTexturePath);
    }

    @Override
    public void renderModel( final EntityLivingBase racoon, float g, float f, float k, float h, float i, float j) {
        super.renderModel(racoon, g, f, k, h, i, j);
        ((Racoon) racoon).setServerEntity(((Racoon) racoon).isServerEntity());
    }
}

