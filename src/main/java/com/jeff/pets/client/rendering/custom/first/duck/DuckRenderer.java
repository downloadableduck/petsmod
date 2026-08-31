package com.jeff.pets.client.rendering.custom.first.duck;

import com.jeff.pets.client.Central;
import com.jeff.pets.client.Math2;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class DuckRenderer extends PetRenderer<Duck, DuckModel> {
    public String duckTexturePath;

    public DuckRenderer(final net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new DuckModel(), 0.3F);
    }

    @Override
    public void preRenderCallback(Duck livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            net.minecraft.client.renderer.GlStateManager.scale(0.6f, 0.6f, 0.6f);
        }
        
    }

    @Override
    public void renderModel(final Duck duck, float f, final float k, float u, float g, float h, float i) {
        float partialTick = Minecraft.getMinecraft().getRenderPartialTicks();
        duck.flap = Math2.lerp(partialTick, duck.oFlap, duck.flap);
        duck.flapSpeed = Math2.lerp(partialTick, duck.oFlapSpeed, duck.flapSpeed);
        super.renderModel(duck, f, k, u, g, h, i);
    }

    @Override
    public ResourceLocation getEntityTexture(final Duck state) {
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
        return new ResourceLocation(Central.MOD_ID, duckTexturePath);
    }
}