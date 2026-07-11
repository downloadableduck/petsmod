package com.jeff.pets.client.rendering.custom.aquatic.dumbo_octopus;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.aquatic.DumboOctopus;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.PetsInitializer.MOD_ID;
import static com.jeff.pets.client.Central.CONFIG;

public class DumboOctopusRenderer extends PetRenderer<DumboOctopus, DumboOctopusModel> {

    double i = 45;
    float direction = 1;
    float speed = 0.5f;

    public DumboOctopusRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry.Context context2) {
        super(context, new DumboOctopusModel(), 0.5f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull DumboOctopus state) {
        String path;
        String yellow = "textures/entity/dumbo_octopus/yellow.png";
        String red = "textures/entity/dumbo_octopus/red.png";
        String blue = "textures/entity/dumbo_octopus/blue.png";
        String green = "textures/entity/dumbo_octopus/green.png";
        String orange = "textures/entity/dumbo_octopus/orange.png";
        String pink = "textures/entity/dumbo_octopus/pink.png";
        if (CONFIG.dumboOctopusSkin.equals("yellow")) {
            path = yellow;
        } else if (CONFIG.dumboOctopusSkin.equals("red")) {
            path = red;
        } else if (CONFIG.dumboOctopusSkin.equals("blue")) {
            path = blue;
        } else if (CONFIG.dumboOctopusSkin.equals("green")) {
            path = green;
        } else if (CONFIG.dumboOctopusSkin.equals("orange")) {
            path = orange;
        } else if (CONFIG.dumboOctopusSkin.equals("pink")) {
            path = pink;
        } else {
            path = yellow;
        }
        return new ResourceLocation(MOD_ID, path);
    }

    @Override
    public void render(DumboOctopus octopus, float f, float g, PoseStack poseStack, MultiBufferSource source, int i) {
        super.render(octopus, f, g, poseStack, source, i);
        float currentSpeed;
        if (i > 67.5f) {
            currentSpeed = speed;
        } else {
            currentSpeed = 1.0f;
        }
        i += (int) (direction * currentSpeed);
        if (i >= 90 || i <= 45) {
            direction *= -1;
        }
        octopus.tentacleAngle = (float) (i % 360);
    }
}
