package com.jeff.pets.client.rendering.custom.first.duck;

import com.jeff.pets.PetsInitializer;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.first.Duck;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

import java.util.Objects;
import net.minecraft.util.Identifier;

import static com.jeff.pets.client.Central.CONFIG;

public class DuckRenderer extends PetRenderer {

    public DuckRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new DuckModel(), 0.75f);
    }

    protected void preRenderCallback(@NotNull Duck livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            GL11.glScalef(0.5f, 0.5f, 0.5f);
        }
    }

    public @NotNull Identifier getTexture(net.minecraft.entity.Entity __e) {
        Duck livingEntityRenderState = (Duck) __e;
        String duckTexturePath;
        if (Objects.equals(CONFIG.duckSkin, "pekin")) {
            duckTexturePath = "textures/entity/duck/pekin.png";
        } else if (Objects.equals(CONFIG.duckSkin, "mallard")) {
            duckTexturePath = "textures/entity/duck/mallard_male.png";
        } else if (Objects.equals(CONFIG.duckSkin, "rubber")) {
            duckTexturePath = "textures/entity/duck/rubber.png";
        } else if (CONFIG.duckSkin.equals("bronze")) {
            duckTexturePath = "textures/entity/duck/bronze.png";
        } else {
            duckTexturePath = "textures/entity/duck/mallard_male.png";
        }
        return new Identifier(PetsInitializer.MOD_ID, duckTexturePath);
    }

    public void renderModel(final Duck duck, float f, final float k, float u, float g, float h, float i) {
        float partialTick = 1.0F;
        duck.flap = duck.oFlap + (duck.flap - duck.oFlap) * partialTick;
        duck.flapSpeed = duck.oFlapSpeed + (duck.flapSpeed - duck.oFlapSpeed) * partialTick;
        super.renderModel(duck, f, k, u, g, h, i);
    }
}