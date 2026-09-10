package com.jeff.pets.client.rendering.custom.first.duck;

import com.jeff.pets.PetsInitializer;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.first.Duck;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class DuckRenderer extends PetRenderer<@NotNull Duck> {

    public DuckRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new DuckModel(), 0.75f);
    }

    @Override
    protected void scale(@NotNull Duck livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            GlStateManager.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTexture(Duck livingEntityRenderState) {
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

    @Override
    public void renderModel(final Duck duck, float f, final float k, float u, float g, float h, float i) {
        float partialTick = MinecraftClient.getInstance().method_12143();
        duck.flap = duck.oFlap + (duck.flap - duck.oFlap) * partialTick;
        duck.flapSpeed = duck.oFlapSpeed + (duck.flapSpeed - duck.oFlapSpeed) * partialTick;
        super.renderModel(duck, f, k, u, g, h, i);
    }
}