package com.jeff.pets.client.rendering.custom.first.penguin;

import com.jeff.pets.PetsInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.first.Penguin;
import net.minecraft.resource.Identifier;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class PenguinRenderer extends PetRenderer<@NotNull Penguin> {

    public PenguinRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new PenguinModel(), 0.5f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(Penguin livingEntityRenderState) {
        return new Identifier(PetsInitializer.MOD_ID, "textures/entity/penguin/penguin.png");
    }

    @Override
    protected void applyScale(@NotNull Penguin livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            net.minecraft.client.render.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public void renderModel(final Penguin penguin, float f, float partialTicks, float h, float i, float j, float k) {
        //penguin.flap = (float) Math2.lerp(partialTicks, penguin.oFlap, penguin.flap);
        //penguin.flapSpeed = (float) Math2.lerp(partialTicks, penguin.oFlapSpeed, penguin.flapSpeed);
        super.renderModel(penguin, f, partialTicks, h, i, j, k);
    }
}