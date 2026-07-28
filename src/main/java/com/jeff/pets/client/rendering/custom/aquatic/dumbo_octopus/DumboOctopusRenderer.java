package com.jeff.pets.client.rendering.custom.aquatic.dumbo_octopus;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.aquatic.DumboOctopus;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;
import static com.jeff.pets.client.Central.MOD_ID;

public class DumboOctopusRenderer extends PetRenderer<DumboOctopus, DumboOctopusModel> {

    double i = 45;
    float direction = 1;
    float speed = 0.5f;

    public DumboOctopusRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new DumboOctopusModel(), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(DumboOctopus state) {
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
    public void renderModel(DumboOctopus octopus, float f, float g, float h, float i, float j, float k) {
        super.renderModel(octopus, f, g, h, i, j, k);
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
