package com.jeff.pets.client.rendering.custom.first.duck;

import com.jeff.pets.PetsInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.resource.Identifier;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class DuckRenderer extends PetRenderer<@NotNull Duck, @NotNull DuckModel> {
    public String duckTexturePath;

    public DuckRenderer(final net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new DuckModel(), 0.3F);
    }

    @Override
    protected void applyScale(@NotNull Duck livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scalef(0.6f, 0.6f, 0.6f);
        }
    }

    @Override
    public void renderModel(final Duck duck, float f, final float partialTicks, float u, float g, float h, float i) {
        duck.flap = (float) MathHelper.m_4848186 /*lerp*/(partialTicks, duck.oFlap, duck.flap);
        duck.flapSpeed = (float) MathHelper.m_4848186 /*lerp*/(partialTicks, duck.oFlapSpeed, duck.flapSpeed);
        super.renderModel(duck, f, partialTicks, u, g, h, i);
    }

    @Override
    public @NotNull Identifier getTextureLocation(final Duck state) {
        if (Objects.equals(CONFIG.duckSkin, "pekin")) {
            duckTexturePath = "textures/entity/duck/pekin.png";
        } else if (Objects.equals(CONFIG.duckSkin, "mallard")) {
            duckTexturePath = "textures/entity/duck/mallard_male.png";
        } else if (Objects.equals(CONFIG.duckSkin, "rubber")) {
            duckTexturePath = "textures/entity/duck/rubber.png";
        } else if (CONFIG.duckSkin.equals("bronze")) {
            duckTexturePath = "textures/entity/duck/bronze.png";
        }
        return new Identifier(PetsInitializer.MOD_ID, duckTexturePath);
    }
}