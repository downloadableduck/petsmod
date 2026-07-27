package com.jeff.pets.client.rendering.custom.first.penguin;

import com.jeff.pets.PetsInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.first.Penguin;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class PenguinRenderer extends PetRenderer<@NotNull Penguin, @NotNull PenguinModel> {

    public PenguinRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new PenguinModel(), 0.5f);
    }

    @Override
    public @NotNull Identifier getTexture(Penguin livingEntityRenderState) {
        return new Identifier(PetsInitializer.MOD_ID, "textures/entity/penguin/penguin.png");
    }

    @Override
    protected void scale(@NotNull Penguin livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public void render(final Penguin penguin, float f, float partialTicks, float h, float i, float j, float k) {
        float partialTick = MinecraftClient.getInstance().getTickDelta();
        //penguin.flap = MathHelper.lerp(partialTick, penguin.oFlap, penguin.flap);
        //penguin.flapSpeed = MathHelper.lerp(partialTick, penguin.oFlapSpeed, penguin.flapSpeed);
        super.render(penguin, f, partialTicks, h, i, j, k);
    }
}
