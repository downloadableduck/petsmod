package com.jeff.pets.rendering.vanilla.pillager;

import com.jeff.pets.mob.vanilla.hostile.ClientPillager;
import com.jeff.pets.rendering.PetRenderer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.IllagerRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientPillagerRenderer extends PetRenderer<@NotNull ClientPillager, @NotNull IllagerRenderState, @NotNull ClientPillagerModel> {
    public static final ModelLayerLocation PILLAGER_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientpillager"), "main");

    public ClientPillagerRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientPillagerModel(context.bakeLayer(ModelLayers.PILLAGER)), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(IllagerRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/illager/pillager.png");
    }

    @Override
    public IllagerRenderState createRenderState() {
        return new IllagerRenderState();
    }
}
