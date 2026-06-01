package com.jeff.pets.client.rendering.custom.aquatic.dumbo_octopus;

import com.jeff.pets.mob.custom.aquatic.DumboOctopus;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;
import static com.jeff.pets.PetsInitializer.MOD_ID;

public class DumboOctopusRenderer extends PetRenderer<DumboOctopus, DumboOctopusRenderState, DumboOctopusModel> {

    public static final ModelLayerLocation DUMBO_OCTOPUS_LOCATION = new ModelLayerLocation(Identifier.fromNamespaceAndPath(MOD_ID, "dumbo_octopus"), "main");
    double i = 45;
    float direction = 1;
    float speed = 0.5f;

    public DumboOctopusRenderer(EntityRendererProvider.Context context) {
        super(context, new DumboOctopusModel(context.bakeLayer(DUMBO_OCTOPUS_LOCATION)), 0.5f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(@NotNull DumboOctopusRenderState state) {
        String path;
        String yellow = "textures/entity/dumbo_octopus/yellow.png";
        String red = "textures/entity/dumbo_octopus/red.png";
        String blue = "textures/entity/dumbo_octopus/blue.png";
        String green = "textures/entity/dumbo_octopus/green.png";
        String orange = "textures/entity/dumbo_octopus/orange.png";
        String pink = "textures/entity/dumbo_octopus/pink.png";
        if (!state.isServerEntity) {
            switch (CONFIG.dumboOctopusSkin) {
                case "yellow" -> path = yellow;
                case "red" -> path = red;
                case "blue" -> path = blue;
                case "green" -> path = green;
                case "orange" -> path = orange;
                case "pink" -> path = pink;
                case null, default -> path = yellow;
            }
        } else {
            switch (state.dumboOctopusSkin) {
                case 1 -> path = yellow;
                case 2 -> path = red;
                case 3 -> path = blue;
                case 4 -> path = green;
                case 5 -> path = orange;
                case 6 -> path = pink;
                default -> path = yellow;
            }
        }
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    @Override
    public @NotNull DumboOctopusRenderState createRenderState() {
        return new DumboOctopusRenderState();
    }

    @Override
    public void extractRenderState(DumboOctopus octopus, DumboOctopusRenderState state, float f) {
        super.extractRenderState(octopus, state, f);
        state.isServerEntity = octopus.isServerEntity();
        state.dumboOctopusSkin = octopus.getEntityData().get(DumboOctopus.OCTOPUS_SKIN);
        float currentSpeed;
        if (i > 67.5f) {
            currentSpeed = speed;
        } else {
            currentSpeed = 1.0f;
        }
        i += (direction * currentSpeed);
        if (i >= 90 || i <= 45) {
            direction *= -1;
        }
        state.tentacleAngle = (float) (i % 360);
    }
}
