//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.custom.aquatic.dumbo_octopus;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.aquatic.DumboOctopus;

public class DumboOctopusRenderer extends PetRenderer<DumboOctopus> {
    double i = 45.0;
    float direction = 1.0F;
    float speed = 0.5F;

    public DumboOctopusRenderer(EntityRenderDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new DumboOctopusModel(), 0.5F);
    }

    public @NotNull String getTexture(Entity entity) {
        DumboOctopus state = (DumboOctopus)entity;
        String yellow = "textures/mob/dumbo_octopus/yellow.png";
        String red = "textures/mob/dumbo_octopus/red.png";
        String blue = "textures/mob/dumbo_octopus/blue.png";
        String green = "textures/mob/dumbo_octopus/green.png";
        String orange = "textures/mob/dumbo_octopus/orange.png";
        String pink = "textures/mob/dumbo_octopus/pink.png";
        String path;
        if (Central.CONFIG.dumboOctopusSkin.equals("yellow")) {
            path = yellow;
        } else if (Central.CONFIG.dumboOctopusSkin.equals("red")) {
            path = red;
        } else if (Central.CONFIG.dumboOctopusSkin.equals("blue")) {
            path = blue;
        } else if (Central.CONFIG.dumboOctopusSkin.equals("green")) {
            path = green;
        } else if (Central.CONFIG.dumboOctopusSkin.equals("orange")) {
            path = orange;
        } else if (Central.CONFIG.dumboOctopusSkin.equals("pink")) {
            path = pink;
        } else {
            path = yellow;
        }

        return path;
    }

    public void render(LivingEntity mobEntity, double f, double g, double h, float j, float i) {
        super.render(mobEntity, f, g, h, j, i);
        DumboOctopus octopus = (DumboOctopus)mobEntity;
        float currentSpeed;
        if (i > 67.5F) {
            currentSpeed = this.speed;
        } else {
            currentSpeed = 1.0F;
        }

        i += (float)((int)(this.direction * currentSpeed));
        if (i >= 90.0F || i <= 45.0F) {
            this.direction *= -1.0F;
        }

        octopus.tentacleAngle = i % 360.0F;
    }
}
