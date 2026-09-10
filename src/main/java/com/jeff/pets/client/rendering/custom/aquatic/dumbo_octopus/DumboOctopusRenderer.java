package com.jeff.pets.client.rendering.custom.aquatic.dumbo_octopus;

import com.jeff.pets.PetsInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.aquatic.DumboOctopus;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class DumboOctopusRenderer extends PetRenderer<@NotNull DumboOctopus> {

    public DumboOctopusRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new DumboOctopusModel(), 0.3F);
    }

    @Override
    public void render(@NotNull DumboOctopus octopus, double x, double y, double z, float yaw, float pitch) {
        super.render(octopus, x, y, z, yaw, pitch);
    }

    @Override
    protected void scale(@NotNull DumboOctopus state, float f) {
        GlStateManager.scale(0.7f, 0.7f, 0.7f);
        if (CONFIG.isBaby) {
            GlStateManager.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTexture(DumboOctopus livingEntityRenderState) {
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
        return new Identifier(PetsInitializer.MOD_ID, path);
    }
}