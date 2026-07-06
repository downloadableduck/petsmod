package com.jeff.pets.client.rendering.custom.aquatic.dumbo_octopus;

import com.jeff.pets.mob.custom.aquatic.DumboOctopus;
import com.jeff.pets.client.rendering.PetRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;
import static com.jeff.pets.PetsInitializer.MOD_ID;

public class DumboOctopusRenderer extends PetRenderer<DumboOctopus, DumboOctopusModel> {

    public static final ModelLayerLocation DUMBO_OCTOPUS_LOCATION = new ModelLayerLocation(new ResourceLocation(MOD_ID, "dumbo_octopus"), "main");
    double i = 45;
    float direction = 1;
    float speed = 0.5f;

    public DumboOctopusRenderer(EntityRendererProvider.Context context) {
        super(context, new DumboOctopusModel(context.bakeLayer(DUMBO_OCTOPUS_LOCATION)), 0.5f);
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
        switch (CONFIG.dumboOctopusSkin) {
            case "yellow" -> path = yellow;
            case "red" -> path = red;
            case "blue" -> path = blue;
            case "green" -> path = green;
            case "orange" -> path = orange;
            case "pink" -> path = pink;
            default -> path = yellow;
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
