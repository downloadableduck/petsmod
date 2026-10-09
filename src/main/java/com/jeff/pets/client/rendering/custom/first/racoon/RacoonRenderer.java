package com.jeff.pets.client.rendering.custom.first.racoon;

import com.jeff.pets.mob.custom.first.Racoon;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

import java.util.Objects;

import static com.jeff.pets.PetsInitializer.MOD_ID;
import static com.jeff.pets.client.Central.CONFIG;

public class RacoonRenderer extends MobEntityRenderer {


    @Override
    public void render(Entity entity, double x, double y, double z, float yaw, float pitch) {
        super.render((LivingEntity) entity, x, y, z, yaw, pitch);
    }
    public RacoonRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(new RacoonModel(), 0.75f);
        this.setRenderDispatcher(context);
    }

    protected void preRenderCallback(@NotNull Racoon livingEntityRenderState, float f) {
        if ((CONFIG.isBaby && !livingEntityRenderState.isServerEntity()) || (livingEntityRenderState.isBaby() && livingEntityRenderState.isServerEntity())) {
            GL11.glScalef(0.5f, 0.5f, 0.5f);
        }
    }

    public @NotNull Identifier getTexture(Entity __e) {
        Racoon state = (Racoon) __e;
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
        return new Identifier(MOD_ID, racoonTexturePath);
    }
}