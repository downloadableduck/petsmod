package com.jeff.pets.client.rendering.custom.first.penguin;

import com.jeff.pets.client.Central;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.first.Penguin;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.PetsInitializer.MOD_ID;
import static com.jeff.pets.client.Central.CONFIG;

public class PenguinRenderer extends PetRenderer<@NotNull Penguin> {

    public PenguinRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new PenguinModel(), 0.75f);
    }

    @Override
    public void renderModel(final Penguin penguin, float f, float partialTicks, float h, float i, float j, float k) {
        float partialTick = MinecraftClient.getInstance().method_12143();
        super.renderModel(penguin, f, partialTicks, h, i, j, k);
    }

    @Override
    protected void scale(@NotNull Penguin livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            GlStateManager.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTexture(Penguin livingEntityRenderState) {
        return new Identifier(MOD_ID, "textures/entity/penguin/penguin.png");
    }
}