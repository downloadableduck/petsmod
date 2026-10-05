package com.jeff.pets.client.rendering.custom.first.penguin;

import com.jeff.pets.PetsInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.first.Penguin;
import net.minecraft.client.resource.Identifier;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class PenguinRenderer extends PetRenderer<@NotNull Penguin> {

    public PenguinRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new PenguinModel(), 0.5f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(net.minecraft.entity.Entity entity) {
        Penguin livingEntityRenderState = (Penguin) entity;
        return new Identifier(PetsInitializer.MOD_ID, "textures/entity/penguin/penguin.png");
    }

    @Override
    protected void applyScale(net.minecraft.entity.living.LivingEntity entity, float f) {
        Penguin livingEntityRenderState = (Penguin) entity;
        if (CONFIG.isBaby) {
            com.jeff.pets.compat.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public void renderModel(net.minecraft.entity.living.LivingEntity entity, float f, float partialTicks, float h, float i, float j, float k) {
        Penguin penguin = (Penguin) entity;
        //penguin.flap = (float) Math2.lerp(partialTicks, penguin.oFlap, penguin.flap);
        //penguin.flapSpeed = (float) Math2.lerp(partialTicks, penguin.oFlapSpeed, penguin.flapSpeed);
        super.renderModel(penguin, f, partialTicks, h, i, j, k);
    }
}