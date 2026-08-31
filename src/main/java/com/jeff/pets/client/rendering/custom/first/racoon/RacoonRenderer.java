package com.jeff.pets.client.rendering.custom.first.racoon;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.first.Racoon;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;
import static com.jeff.pets.client.Central.MOD_ID;

public class RacoonRenderer extends PetRenderer<Racoon, RacoonModel> {

    public RacoonRenderer(net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new RacoonModel(), 0.75f);
    }

    @Override
    public void preRenderCallback(Racoon livingEntityRenderState, float f) {
        if ((CONFIG.isBaby && !livingEntityRenderState.isServerEntity()) || (livingEntityRenderState.isChild() && livingEntityRenderState.isServerEntity())) {
            GlStateManager.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public ResourceLocation getEntityTexture(Racoon state) {
        String racoonTexturePath;
        if (!state.isServerEntity()) {
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
    public void renderModel(Racoon racoon, float g, float f, float k, float h, float i, float j) {
        super.renderModel(racoon, g, f, k, h, i, j);
        racoon.setServerEntity(racoon.getDataManager().get(Racoon.IS_SERVER_ENTITY));
    }
}
