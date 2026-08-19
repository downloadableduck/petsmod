package com.jeff.pets.rendering.vanilla.irongolem;

import com.jeff.pets.mob.vanilla.neutral.ClientIronGolem;
import com.jeff.pets.rendering.PetRenderer;
import net.minecraft.client.model.animal.golem.IronGolemModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.IronGolemRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientIronGolemRenderer extends PetRenderer<@NotNull ClientIronGolem, @NotNull IronGolemRenderState, @NotNull IronGolemModel> {

    public static final ModelLayerLocation IRON_GOLEM_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientirongolem"), "main");

    public ClientIronGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new IronGolemModel(context.bakeLayer(ModelLayers.IRON_GOLEM)), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(IronGolemRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/iron_golem/iron_golem.png");
    }

    @Override
    public IronGolemRenderState createRenderState() {
        return new IronGolemRenderState();
    }
}
