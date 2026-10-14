package com.jeff.pets.client.rendering.custom.first.racoon;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.first.Racoon;
import org.lwjgl.opengl.GL11;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class RacoonRenderer extends PetRenderer {

    public RacoonRenderer() {
        super(new RacoonModel(), 0.75f);
    }

    @Override
    public void preRenderCallback( final EntityLiving livingEntityRenderState, float f) {
        if ((CONFIG.isBaby && !((Racoon) livingEntityRenderState).isServerEntity()) || (livingEntityRenderState.isChild() && ((Racoon) livingEntityRenderState).isServerEntity())) {
            GL11.glScalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public String getEntityTexture( final Entity state) {
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
        return racoonTexturePath;
    }

    @Override
    public void renderModel( final EntityLiving racoon, float g, float f, float k, float h, float i, float j) {
        super.renderModel(racoon, g, f, k, h, i, j);
        ((Racoon) racoon).setServerEntity(((Racoon) racoon).isServerEntity());
    }
}

